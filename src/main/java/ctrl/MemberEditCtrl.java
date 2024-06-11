package ctrl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.DBConnection;
import vo.MemberVO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class MemberEditCtrl
 */
public class MemberEditCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberEditCtrl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cust_no = request.getParameter("cust_no");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getConnection();
			String sql = "SELECT * FROM MEMBER_TBL_001 WHERE CUST_NO = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cust_no);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setCust_no(Integer.parseInt(rs.getString("CUST_NO")));
				vo.setCust_name(rs.getString("CUST_NAME"));
				vo.setPhone(rs.getString("PHONE"));
				vo.setAddress(rs.getString("ADDRESS"));
				vo.setJoin_date(rs.getDate("JOIN_DATE"));
				vo.setStat_fg(rs.getString("STAT_FG"));
				
				request.setAttribute("vo", vo);
				request.getRequestDispatcher("memberEdit.jsp").forward(request, response);
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
		String cust_no = request.getParameter("cust_no");
		String cust_name = request.getParameter("cust_name");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String stat_fg = request.getParameter("stat_fg");
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DBConnection.getConnection();
			String sql = "UPDATE MEMBER_TBL_001 SET CUST_NAME = ?, PHONE = ?, ADDRESS = ?, STAT_FG = ? WHERE CUST_NO = ?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cust_name);
			stmt.setString(2, phone);
			stmt.setString(3, address);
			stmt.setString(4, stat_fg);
			stmt.setString(5, cust_no);
			
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
