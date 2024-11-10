package vn.phatbee.sachservletjdbc.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.phatbee.sachservletjdbc.models.BookModel;
import vn.phatbee.sachservletjdbc.services.IBookService;
import vn.phatbee.sachservletjdbc.services.impl.BookServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/products")
public class ProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    IBookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String url = req.getRequestURI();
        if (url.contains("products")){
            int pageSize = 3; // Số sách trên mỗi trang
            int pageNumber = 1; // Mặc định trang đầu tiên

            // Lấy số trang từ tham số query
            String pageParam = req.getParameter("page");
            if (pageParam != null) {
                try {
                    pageNumber = Integer.parseInt(pageParam);
                } catch (NumberFormatException e) {
                    pageNumber = 1;
                }
            }

            // Lấy sách từ dịch vụ với phân trang
            List<BookModel> bookList = bookService.findAll(pageNumber, pageSize);
            int totalBooks = bookService.count(); // Giả sử có phương thức đếm tổng số sách
            int totalPages = (int) Math.ceil((double) totalBooks / pageSize);

            // Gửi danh sách sách và thông tin phân trang vào request
            req.setAttribute("bookList", bookList);
            req.setAttribute("pageNumber", pageNumber);
            req.setAttribute("totalPages", totalPages);

            req.getRequestDispatcher("/views/product-list.jsp").forward(req, resp);
        }
    }
}
