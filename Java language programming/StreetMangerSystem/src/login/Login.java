package login;

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
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        
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
                user.setUsername(rs.getString("userName"));
                user.setPassword(rs.getString("userPassword"));
                list.add(user);
            }

            //如果list集合中有数据，就跳转到主页面，否则就跳转到登录页面
            if(list.size() > 0){
                request.getSession().setAttribute("user", username);
                response.sendRedirect("main.jsp");
            }else{
                response.sendRedirect("login.jsp");
            }

            //关闭结果集
            rs.close();

            //关闭连接
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();}

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
