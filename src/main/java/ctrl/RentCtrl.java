package ctrl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.DBConnection;
import vo.RentVO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;

import org.apache.tomcat.jni.Local;

/**
 * Servlet implementation class RentCtrl
 */
public class RentCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RentCtrl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			String sql = "SELECT NVL(MAX(rent_no), 0) + 1 FROM rental_tbl_001";
			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();

			if (rs.next()) {
				RentVO vo = new RentVO();
				LocalDate rent = LocalDate.now();
				LocalDate close = LocalDate.now().plusDays(7);

				vo.setRent_no(String.format("%5s", rs.getString(1)).replace(" ", "0"));
				vo.setRent_ymd(rent.toString().replace("-", ""));
				vo.setClose_ymd(close.toString().replace("-", ""));

				request.setAttribute("vo", vo);
			}

			request.getRequestDispatcher("rent.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(stmt, conn);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String rent_book = request.getParameter("rent_book");
		String rent_rent = request.getParameter("rent_rent");
		String alertMsg = "";

		if (rent_book.isEmpty() || rent_book.isBlank())
			alertMsg = "도서코드를 입력하지 않았습니다.";
		else if (rent_rent.isEmpty() || rent_rent.isBlank())
			alertMsg = "고객번호를 입력하지 않았습니다.";

		if (!alertMsg.isEmpty()) {
			request.setAttribute("msg", alertMsg);
			request.getRequestDispatcher("rent.jsp").forward(request, response);
			return;
		}

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String rent_no = null;
		String rent_ymd = LocalDate.now().toString().replace("-", "");
		String close_ymd = LocalDate.now().plusDays(7).toString().replace("-", "");

		try {
			conn = DBConnection.getConnection();
			String sql = "SELECT NVL(MAX(rent_no), 0) + 1 FROM rental_tbl_001";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				rent_no = String.format("%5s", rs.getString(1)).replace(" ", "0");
			}
			
			rs.close();
			stmt.close();

			sql = "INSERT INTO rental_tbl_001 VALUES(?, ?, ?, ?, ?, ?, 0)";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, rent_ymd);
			stmt.setString(2, rent_no);
			stmt.setString(3, rent_book);
			stmt.setString(4, rent_rent);
			stmt.setObject(5, close_ymd);
			stmt.setObject(6, null);

			rs = stmt.executeQuery();

			if (rs.next()) {
				alertMsg = "대여도서등록이 완료되었습니다.";
				request.setAttribute("msg", alertMsg);
				request.getRequestDispatcher("rent.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, stmt, conn);
		}
	}

}
