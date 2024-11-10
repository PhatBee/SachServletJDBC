package vn.phatbee.sachservletjdbc.controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.phatbee.sachservletjdbc.HelloServlet;
import vn.phatbee.sachservletjdbc.models.UserModel;

import java.io.IOException;

@WebServlet(urlPatterns = "/waiting")
public class WaitingController extends HelloServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        if (session != null && session.getAttribute("account") != null) {
            UserModel user = (UserModel) session.getAttribute("account");
            req.setAttribute("user", user.getFullname());
            if (user.isIs_admin()){
                resp.sendRedirect(req.getContextPath() + "/admin/home");
            } else if (!user.isIs_admin()) {
                resp.sendRedirect(req.getContextPath() + "/user/home");
            }
        }
    }
}
