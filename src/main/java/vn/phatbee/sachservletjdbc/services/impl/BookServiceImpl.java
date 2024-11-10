package vn.phatbee.sachservletjdbc.services.impl;

import vn.phatbee.sachservletjdbc.dao.IBookDao;
import vn.phatbee.sachservletjdbc.dao.impl.BookDaoImpl;
import vn.phatbee.sachservletjdbc.models.BookModel;
import vn.phatbee.sachservletjdbc.models.RatingModel;
import vn.phatbee.sachservletjdbc.services.IBookService;
import vn.phatbee.sachservletjdbc.services.IRatingService;

import java.util.List;

public class BookServiceImpl implements IBookService {
    IBookDao bookDao = new BookDaoImpl();
    IRatingService ratingService = new RatingServiceImpl();
    @Override
    public List<BookModel> findAll() {
        return bookDao.findAll();
    }

    @Override
    public List<BookModel> findAll(int currentPage, int size) {
        return bookDao.findAll(currentPage, size);
    }

    @Override
    public BookModel findById(int id) {
        BookModel book = bookDao.findById(id);
        if (book != null) {
            // Lấy danh sách ratings cho sách
            List<RatingModel> ratings = ratingService.getReviewByBook(id);
            book.setRatings(ratings);
        }
        return book;
    }

    @Override
    public int count() {
        return bookDao.count();
    }

    @Override
    public void insert(BookModel book) {
        bookDao.insert(book);
    }

    @Override
    public void update(BookModel book) {
        bookDao.update(book);
    }

    @Override
    public void delete(int bookid) {
        bookDao.delete(bookid);
    }
}
