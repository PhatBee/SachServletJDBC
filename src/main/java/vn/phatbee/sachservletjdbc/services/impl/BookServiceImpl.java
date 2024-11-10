package vn.phatbee.sachservletjdbc.services.impl;

import vn.phatbee.sachservletjdbc.dao.IBookDao;
import vn.phatbee.sachservletjdbc.dao.impl.BookDaoImpl;
import vn.phatbee.sachservletjdbc.models.BookModel;
import vn.phatbee.sachservletjdbc.services.IBookService;

import java.util.List;

public class BookServiceImpl implements IBookService {
    IBookDao bookDao = new BookDaoImpl();
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
        return bookDao.findById(id);
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
