package vn.phatbee.sachservletjdbc.dao;

import vn.phatbee.sachservletjdbc.models.AuthorModel;

import java.util.List;

public interface IAuthorDao {
    void insert(AuthorModel author);
    void update(AuthorModel author);
    void delete(AuthorModel author);
    List<AuthorModel> getAllAuthors(int currentPage, int size);
    int count();
    AuthorModel getAuthor(int id);

}
