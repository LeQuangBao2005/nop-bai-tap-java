/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package My;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class ViewServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//b1.lấy yêu cầu từ client
//b2. xu ly yeu cau
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            String kq = "";
            try {
//1.Nap driver
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//System.out.println("Nap driver OK");
//2.Thiet lap ket noi CSDL
                conn = DriverManager.getConnection("jdbc:sqlserver://MSI;databaseName=demodb", "sa", "sa");
// System.out.println("Ket noi OK");
//3.Tạo đối tượng thi hành truy vấn
                ps = conn.prepareStatement("select * from users");
//4.Thi hành truy vấn
                rs = ps.executeQuery();
//5.Xu ly ket qua tra ve
                kq += "<table border=1>";

                kq += "<tr>";
                kq +=
"<td>Id</td><td>Name</td><td>Password</td><td>Email</td><td>Country</td><td>Edit</td><td>Delete</td";

               
kq += "</tr>";
                while (rs.next()) {
                    kq += "<tr>";
                    kq += "<td>" + rs.getInt("id") + "</td><td>"
                            + rs.getString("name") + "</td><td>" + rs.getString(3) + "</td><td>"
                            + rs.getString(4) + "</td><td>" + rs.getString(5) + "</td><td>"
                            + "<a href=EditServlet?id=" + rs.getInt(1) + ">Edit</a>"
                            + "</td><td><a href=DeleteServlet?id=" + rs.getInt(1) + ">Delete</a></td>";
                    kq += "</tr>";
                }
                kq += "</table>";
//6.dong ket noi
                conn.close();
            } catch (Exception e) {
                System.out.println("Loi:" + e.toString());
            }
//b3.phản hồi kết quả
            out.println("<html>");
            out.println("<body>");
            out.println("<a href='index.html'>Add New User</a>");
            out.println("<h1>Users List</h1>");
            out.println(kq);
            out.println("</body>");
            out.println("</html>");
        }
    
}}
