
package register;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datastruct.Users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
			//获取表单中的用户名和密码
			String username = request.getParameter("username");
			String password = request.getParameter("password");

		//建立数据库连接
		Connection conn = null;
		//User类的list集合
		List<Users> list = new ArrayList<Users>();
		
		//尝试导入驱动，建立数据库连接，建立连接的密码为空，建立user表
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/streetmanagersystem?useUnicode=true&characterEncoding=utf-8","root","");
			
			PreparedStatement stmt = conn.prepareStatement("select * from user where userName = ? and userPassword = ?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
			
			//如果查询到了，就将用户名和密码存入list集合中
			while(rs.next()){
				Users user = new Users();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				list.add(user);
			}
			//如果list集合中无数据，就将数据写入到User表中，并且跳转到登录页面
			if(list.size()==0){

				PreparedStatement stmt1 = conn.prepareStatement("insert into user values(?,?)");
				stmt1.setString(1, username);
				stmt1.setString(2, password);
				stmt1.execute();
				response.sendRedirect("login.jsp");

			}else{
				response.sendRedirect("register.jsp");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
	}
		catch (SQLException e) {
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
