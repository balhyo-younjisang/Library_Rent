package ctrl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.DBConnection;
import vo.MemberVO;
import vo.RentBookVO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

/**
 * Servlet implementation class MemberAddCtrl
 */
public class MemberAddCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberAddCtrl() {
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
		
		try {
			conn = DBConnection.getConnection();
			String sql = "SELECT NVL(MAX(cust_no), 0) + 1, SYSDATE FROM MEMBER_TBL_001";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setCust_no(rs.getInt(1));
				vo.setJoin_date(rs.getDate(2));
				
				request.setAttribute("vo", vo);
				request.getRequestDispatcher("memberAdd.jsp").forward(request, response);
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
		String cust_name = request.getParameter("cust_name");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String stat_fg = request.getParameter("stat_fg");
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DBConnection.getConnection();
			String sql = "INSERT INTO MEMBER_TBL_001 VALUES((SELECT NVL(MAX(CUST_NO),0) + 1 FROM MEMBER_TBL_001), ?, ?, ?, SYSDATE, ?)";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cust_name);
			stmt.setString(2, phone);
			stmt.setString(3, address);
			stmt.setString(4, stat_fg);
			
			stmt.executeQuery();
			
			response.sendRedirect("index.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(stmt, conn);
		}
	}

}
