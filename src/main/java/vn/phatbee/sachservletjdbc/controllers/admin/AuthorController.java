package vn.phatbee.sachservletjdbc.controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.phatbee.sachservletjdbc.models.AuthorModel;
import vn.phatbee.sachservletjdbc.services.IAuthorService;
import vn.phatbee.sachservletjdbc.services.impl.AuthorServiceImpl;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/authors", "/admin/author/add", "/admin/author/insert", "/admin/author/edit", "/admin/author/update", "/admin/author/delete"})
public class AuthorController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    IAuthorService authorService = new AuthorServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String url = req.getRequestURI();
        if(url.contains("admin/authors")){
            // Lấy số trang từ request (nếu không có thì mặc định là trang 1)
            int currentPage = 1;
            int recordsPerPage = 6; // Số lượng tác giả trên mỗi trang
            if (req.getParameter("page") != null) {
                currentPage = Integer.parseInt(req.getParameter("page"));
            }
            // Lấy danh sách đã phân trang
            List<AuthorModel> pagedAuthors = authorService.getAllAuthors(currentPage, recordsPerPage);

            // Tính tổng số trang
            int totalRecords = authorService.count();
            int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

            // Set các thuộc tính cho request
            req.setAttribute("pagedAuthors", pagedAuthors);
            req.setAttribute("totalPages", totalPages);
            req.setAttribute("currentPage", currentPage);

            // Forward đến trang JSP
            req.getRequestDispatcher("/views/admin/author-list.jsp").forward(req, resp);
        } else if (url.contains("/admin/author/add")) {
            req.getRequestDispatcher("/views/admin/author-add.jsp").forward(req, resp);
        } else if (url.contains("/admin/author/edit")) {
            int authorId = Integer.parseInt(req.getParameter("authorId"));
            AuthorModel author = authorService.getAuthor(authorId);
            req.setAttribute("author", author);
            req.getRequestDispatcher("/views/admin/author-edit.jsp").forward(req, resp);
        } else if (url.contains("/admin/author/delete")) {
            int authorId = Integer.parseInt(req.getParameter("authorId"));
            AuthorModel author = authorService.getAuthor(authorId);
            authorService.delete(author);
            resp.sendRedirect("/admin/authors");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String url = req.getRequestURI();
        if (url.contains("/admin/author/insert")){
            String authorname = req.getParameter("authorName");
            String dob = req.getParameter("dateOfBirth");
            Date dateofbirth = Date.valueOf(dob);

            AuthorModel author = new AuthorModel();
            author.setAuthor_name(authorname);
            author.setDate_of_birth(dateofbirth);

            authorService.insert(author);
            // Trả về view
            resp.sendRedirect(req.getContextPath() + "/admin/authors");
        } else if (url.contains("/admin/author/update")) {
            int authorId = Integer.parseInt(req.getParameter("authorId"));
            String authorname = req.getParameter("authorName");
            String dob = req.getParameter("dateOfBirth");
            Date dateofbirth = Date.valueOf(dob);

            AuthorModel author = new AuthorModel();
            author.setAuthor_id(authorId);
            author.setAuthor_name(authorname);
            author.setDate_of_birth(dateofbirth);
            authorService.update(author);

            resp.sendRedirect(req.getContextPath() + "/admin/authors");
        }
    }
}
