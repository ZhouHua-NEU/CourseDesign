package community;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datastruct.Community;

/**
 * Servlet implementation class CommunityInsert
 */
public class CommunityUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String communityname = request.getParameter("community_name");
		String communityaddress = request.getParameter("community_address");
		String communitymanager = request.getParameter("community_leader");
		String communityphone = request.getParameter("community_leader_phone");


		Connection conn = null;
        //User类的list集合
        
        //尝试导入驱动，建立数据库连接，建立连接的密码为空，建立user表
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/streetmanagersystem?useUnicode=true&characterEncoding=utf-8","root","");

			//定义prepat	
			java.sql.PreparedStatement pstmt = conn.prepareStatement("update  community set communityName=?,communityAddress=?,communityManager=?,communityManagerPhone=? where communityName=?");
			//执行查询
			pstmt.setString(1, communityname);
			pstmt.setString(2, communityaddress);
			pstmt.setString(3, communitymanager);
			pstmt.setString(4, communityphone);
			pstmt.setString(5, communityname);
			pstmt.execute();
			//关闭连接
			pstmt.close();
			conn.close();
			//将list集合存入request中

			request.getRequestDispatcher("right/community/community_update.jsp").forward(request, response);


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
