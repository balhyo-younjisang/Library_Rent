package ctrl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.DBConnection;
import vo.RentBookVO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

/**
 * Servlet implementation class ViewCtrl
 */
public class ViewCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewCtrl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<RentBookVO> list = new ArrayList<RentBookVO>();
		
		try {
			conn = DBConnection.getConnection();
			String sql = "SELECT rental.rent_ymd, rental.rent_no, rental.rent_book, rental.rent_rent, rental.close_ymd, book.book_name, cust.cust_name FROM rental_tbl_001 rental, BOOK_TBL_001 book, MEMBER_TBL_001 cust WHERE rental.rent_book = book.book_code AND rental.RENT_RENT = cust.cust_no  ORDER BY rental.rent_ymd, rental.rent_no, rental.rent_book";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				RentBookVO vo = new RentBookVO();
				
				vo.setBook_code(rs.getString("RENT_BOOK"));
				vo.setBook_name(rs.getString("BOOK_NAME"));
				vo.setClose_ymd(rs.getString("CLOSE_YMD"));
				vo.setCust_name(rs.getString("CUST_NAME"));
				vo.setCust_no(rs.getString("RENT_RENT"));
				vo.setRent_no(rs.getString("RENT_NO"));
				vo.setRent_ymd(rs.getString("RENT_YMD"));
				list.add(vo);
			}
			
			request.setAttribute("list", list);
			request.getRequestDispatcher("view.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs, stmt, conn);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
