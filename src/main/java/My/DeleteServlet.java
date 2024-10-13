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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class DeleteServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String id = request.getParameter("id"); // Lấy ID từ URL
            
            try {
                // Nạp driver JDBC cho SQL Server
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                
                // Kết nối với cơ sở dữ liệu
                try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://MSI;databaseName=demodb", "sa", "sa");
                     // Tạo PreparedStatement cho câu lệnh DELETE
                     PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE id = ?")) {
                    
                    ps.setInt(1, Integer.parseInt(id)); // Gán giá trị id vào dấu chấm hỏi trong câu lệnh DELETE
                    
                    // Thực hiện lệnh DELETE và kiểm tra kết quả
                    int kq = ps.executeUpdate();

                    if (kq > 0) {
                        out.println("<h2>Xóa user thành công</h2>");
                    } else {
                        out.println("<h2>Xóa user thất bại</h2>");
                    }
                    
                } catch (Exception e) {
                    System.out.println("Lỗi: " + e.toString());
                    out.println("<h2>Xóa user thất bại</h2>");
                }

            } catch (ClassNotFoundException e) {
                out.println("<h2>Không tìm thấy driver</h2>");
            }

            // Chuyển hướng về ViewServlet để hiển thị lại danh sách người dùng
            request.getRequestDispatcher("ViewServlet").include(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet xử lý xóa user từ database";
    }
}
