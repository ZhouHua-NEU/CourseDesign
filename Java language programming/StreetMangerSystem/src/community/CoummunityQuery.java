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
public class CoummunityQuery extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CoummunityQuery() {
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

		Connection conn = null;
		
		List <Community> comlist = new ArrayList<Community>();
        
        //尝试导入驱动，建立数据库连接，建立连接的密码为空，建立user表
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/streetmanagersystem?useUnicode=true&characterEncoding=utf-8","root","");

			///定义prepat	
			java.sql.PreparedStatement pstmt = conn.prepareStatement("select * from community where communityName = ?");

			//设置参数
			pstmt.setString(1, communityname);

			//执行查询
			java.sql.ResultSet rs = pstmt.executeQuery();
			//如果查询到了，就将存入list集合中
			while(rs.next()){
				Community community = new Community();
				community.setCommunityname(rs.getString("communityName"));
				community.setCommunityaddress(rs.getString("communityAddress"));
				community.setCommunitymanager(rs.getString("communityManager"));
				community.setCommunityphone(rs.getString("communityManagerPhone"));
				comlist.add(community);}
			//关闭连接
			pstmt.close();
			conn.close();
			//将list集合存入request中

			//将list集合存入request中
			request.setAttribute("comlist", comlist);
			//转发到community.jsp页面
			request.getRequestDispatcher("center/community.jsp").forward(request, response);


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
