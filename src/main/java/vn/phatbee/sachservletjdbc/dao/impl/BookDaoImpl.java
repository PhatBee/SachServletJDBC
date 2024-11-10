package vn.phatbee.sachservletjdbc.dao.impl;

import vn.phatbee.sachservletjdbc.configs.DBConnectSQL;
import vn.phatbee.sachservletjdbc.dao.IBookDao;
import vn.phatbee.sachservletjdbc.models.AuthorModel;
import vn.phatbee.sachservletjdbc.models.BookModel;
import vn.phatbee.sachservletjdbc.services.IAuthorService;
import vn.phatbee.sachservletjdbc.services.IBookService;
import vn.phatbee.sachservletjdbc.services.IRatingService;
import vn.phatbee.sachservletjdbc.services.impl.AuthorServiceImpl;
import vn.phatbee.sachservletjdbc.services.impl.BookServiceImpl;
import vn.phatbee.sachservletjdbc.services.impl.RatingServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookDaoImpl implements IBookDao {
    public Connection conn = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;

    IAuthorService authorService = new AuthorServiceImpl();
    IRatingService ratingService = new RatingServiceImpl();

    @Override
    public List<BookModel> findAll() {
        return List.of();
    }

    @Override
    public List<BookModel> findAll(int currentPage, int size) {
        String sql = "SELECT b.*, a.author_id, a.author_name, a.date_of_birth " +
                "FROM books b " +
                "LEFT JOIN book_author ba ON b.bookid = ba.bookid " +
                "LEFT JOIN author a ON ba.authorid = a.author_id " +
                "ORDER BY b.bookid OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        Map<Integer, BookModel> bookMap = new HashMap<>();
        try{
            conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);

            // Tính toán chỉ số bắt đầu cho phân trang
            int start = (currentPage - 1) * size;

            ps.setInt(1, start);
            ps.setInt(2, size);

            rs = ps.executeQuery();
            while(rs.next()) {
                int bookId = rs.getInt("bookid");
                BookModel book = bookMap.getOrDefault(bookId, new BookModel());

                if (!bookMap.containsKey(bookId)) {
                    book.setBookid(bookId);
                    book.setIsbn(rs.getInt("isbn"));
                    book.setTitle(rs.getString("title"));
                    book.setPublisher(rs.getString("publisher"));
                    book.setPrice(rs.getDouble("price"));
                    book.setDescription(rs.getString("description"));
                    book.setPublish_date(rs.getDate("publish_date"));
                    book.setCover_image(rs.getString("cover_image"));
                    book.setQuantity(rs.getInt("quantity"));
                    book.setAuthors(new ArrayList<>()); // Thêm danh sách tác giả
                    bookMap.put(bookId, book);
                }

                // Thêm thông tin tác giả vào danh sách tác giả của sách
                if (rs.getInt("author_id") != 0) {
                    AuthorModel author = new AuthorModel();
                    author.setAuthor_id(rs.getInt("author_id"));
                    author.setAuthor_name(rs.getString("author_name"));
                    author.setDate_of_birth(rs.getDate("date_of_birth"));
                    book.getAuthors().add(author);
                }

            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>(bookMap.values());
    }

    @Override
    public BookModel findById(int id) {
        String query = "SELECT * FROM books WHERE bookid = ?";
        BookModel book = null;

        try {
            conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                book = new BookModel();
                book.setBookid(rs.getInt("bookid"));
                book.setIsbn(rs.getInt("isbn"));
                book.setTitle(rs.getString("title"));
                book.setPublisher(rs.getString("publisher"));
                book.setPrice(rs.getDouble("price"));
                book.setDescription(rs.getString("description"));
                book.setPublish_date(rs.getDate("publish_date"));
                book.setCover_image(rs.getString("cover_image"));
                book.setQuantity(rs.getInt("quantity"));

                // Lấy các tác giả (nếu có)
                book.setAuthors(authorService.getAuthorsByBookId(id));

                // Lấy các đánh giá (nếu có)
                book.setRatings(ratingService.getRatingsByBookId(id));
            }   
        } catch (Exception e) {
            e.printStackTrace();
        }

        return book;
    }

    @Override
    public int count() {
        String sql = "SELECT COUNT(*) FROM books";
        try{
            conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);

        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void insert(BookModel book) {

    }

    @Override
    public void update(BookModel book) {

    }

    @Override
    public void delete(int bookid) {

    }
}
