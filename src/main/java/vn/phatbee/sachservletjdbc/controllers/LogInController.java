package vn.phatbee.sachservletjdbc.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.phatbee.sachservletjdbc.models.UserModel;
import vn.phatbee.sachservletjdbc.services.IUserService;
import vn.phatbee.sachservletjdbc.services.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LogInController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    IUserService service     = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        // Lấy tham số từ view
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember"); // Lấy trạng thái checkbox "Remember me"

        String alertMsg="";

        if(email.isEmpty() || password.isEmpty()) {
            alertMsg = "Email hoặc mật khẩu không được rỗng";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
            return;
        }

        // Xu ly bai toan
        UserModel user = service.login(email, password);
        if(user!=null){
            HttpSession session = req.getSession(true);
            session.setAttribute("account", user);
            System.out.println(session.getAttribute("account"));

            // Tạo Cookies nếu người dùng chọn "Remember me"
            if ("on".equals(remember)) {
                Cookie emailCookie = new Cookie("email", email);
                Cookie passwordCookie = new Cookie("password", password);

                // Đặt thời gian sống cho Cookies (ví dụ: 7 ngày)
                emailCookie.setMaxAge(7 * 24 * 60 * 60);
                passwordCookie.setMaxAge(7 * 24 * 60 * 60);

                // Thêm Cookies vào response
                resp.addCookie(emailCookie);
                resp.addCookie(passwordCookie);
            } else {
                // Xóa Cookies nếu người dùng không chọn "Remember me"
                Cookie emailCookie = new Cookie("email", "");
                Cookie passwordCookie = new Cookie("password", "");
                emailCookie.setMaxAge(0);
                passwordCookie.setMaxAge(0);
                resp.addCookie(emailCookie);
                resp.addCookie(passwordCookie);
            }

            resp.sendRedirect(req.getContextPath()+"/waiting");
        }else{
            alertMsg = "Email hoặc mật khẩu không đúng";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }
}
