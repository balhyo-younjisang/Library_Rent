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
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class BookCtrl
 */
public class BookCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookCtrl() {
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
		List<BookVO> list = new ArrayList<BookVO>();
		
		try {
			conn = DBConnection.getConnection();
			String sql = "SELECT * FROM book_tbl_001 ORDER BY book_code";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				BookVO vo = new BookVO();
				vo.setBook_code(rs.getString("book_code"));
				vo.setBook_name(rs.getString("book_name"));
				vo.setBook_author(rs.getString("book_author"));
				vo.setBook_type(rs.getString("book_type"));
				vo.setIn_date(rs.getDate("in_date"));
				vo.setStat_fg(rs.getString("stat_fg"));
				list.add(vo);
			}
			
			request.setAttribute("list", list);
			request.getRequestDispatcher("book.jsp").forward(request, response);
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
