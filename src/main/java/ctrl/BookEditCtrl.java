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
 * Servlet implementation class BookEditCtrl
 */
public class BookEditCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookEditCtrl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String book_code = request.getParameter("book_code");
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getConnection();
			String sql = "SELECT * FROM BOOK_TBL_001 WHERE BOOK_CODE = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, book_code);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				BookVO vo = new BookVO();
				vo.setBook_code(rs.getString("BOOK_CODE"));
				vo.setBook_name(rs.getString("BOOK_NAME"));
				vo.setBook_type(rs.getString("BOOK_TYPE"));
				vo.setIn_date(rs.getDate("IN_DATE"));
				vo.setStat_fg(rs.getString("STAT_FG"));
				vo.setBook_author(rs.getString("BOOK_AUTHOR"));
				
				request.setAttribute("vo", vo);
				request.getRequestDispatcher("bookEdit.jsp").forward(request, response);
			}
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
		String book_code = request.getParameter("book_code");
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DBConnection.getConnection();
			String sql = "DELETE FROM BOOK_TBL_001 WHERE BOOK_CODE = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, book_code);
			
			if(stmt.executeUpdate() > 0) {
				response.sendRedirect("index.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(stmt, conn);
		}
	}

}
