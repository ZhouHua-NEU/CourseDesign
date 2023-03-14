package present;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datastruct.House;

/**
 * Servlet implementation class HousePresent
 */
public class HousePresent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HousePresent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
        //User类的list集合
        List <House> comlist = new ArrayList<House>();
        
        //尝试导入驱动，建立数据库连接，建立连接的密码为空，建立user表
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/streetmanagersystem?useUnicode=true&characterEncoding=utf-8","root","");

			//定义prepat	
			java.sql.PreparedStatement pstmt = conn.prepareStatement("select * from house");
			//执行查询
			java.sql.ResultSet rs = pstmt.executeQuery();
			//如果查询到了，就将存入list集合中
			while(rs.next()){
				House house = new House();
				house.setHouseid(rs.getString("houseNumber"));
				house.setHouseaddress(rs.getString("houseAddress"));
				house.setHousearea(rs.getString("houseArea"));
				house.setHousetype(rs.getString("houseType"));
				house.setHousecommunity(rs.getString("houseCommunity"));
				house.setHousestreet(rs.getString("houseStreet"));
				comlist.add(house);}
			//关闭连接
			rs.close();
			pstmt.close();
			conn.close();
			//将list集合存入request中
			request.setAttribute("comlist", comlist);
			//转发到house.jsp页面
			request.getRequestDispatcher("center/house.jsp").forward(request, response);
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
