package vn.phatbee.sachservletjdbc.services;

import vn.phatbee.sachservletjdbc.models.BookModel;

import java.util.List;

public interface IBookService {
    List<BookModel> findAll();
    List<BookModel> findAll(int currentPage, int size);
    BookModel findById(int id);
    int count();
    void insert(BookModel book);
    void update(BookModel book);
    void delete(int bookid);
}
