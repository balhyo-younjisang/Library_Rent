package ctrl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.DBConnection;
import vo.BookVO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class BookAddCtrl
 */
public class BookAddCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookAddCtrl() {
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
			String sql = "SELECT SYSDATE FROM BOOK_TBL_001";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				BookVO vo = new BookVO();
				vo.setIn_date(rs.getDate(1));

				request.setAttribute("vo", vo);
				request.getRequestDispatcher("bookAdd.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, stmt, conn);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String book_code = "";
		String book_name = request.getParameter("book_name");
		String book_type = request.getParameter("book_type");
		String book_author = request.getParameter("book_author");
		String stat_fg = request.getParameter("stat_fg");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			String sql = "SELECT NVL(MAX(BOOK_CODE),0) FROM BOOK_TBL_001 WHERE BOOK_CODE LIKE";

			if (book_type.equals("A")) {
				sql += " 'A%'";
			} else if (book_type.equals("B")) {
				sql += " 'B%'";
			} else if (book_type.equals("C")) {
				sql += " 'C%'";
			}

			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				book_code = String.format("%4s", Integer.toString(Integer.parseInt(rs.getString(1).substring(2)) + 1))
						.replace(" ", "0");
				System.out.println(book_code);
			}

			rs.close();
			stmt.close();

			sql = "INSERT INTO BOOK_TBL_001 VALUES(?,?,?,?,SYSDATE,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, book_type + book_code);
			stmt.setString(2, book_name);
			stmt.setString(3, book_type);
			stmt.setString(4, book_author);
			stmt.setString(5, stat_fg);

			if (stmt.executeUpdate() > 0) {
				response.sendRedirect("index.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, stmt, conn);
		}
	}

}
