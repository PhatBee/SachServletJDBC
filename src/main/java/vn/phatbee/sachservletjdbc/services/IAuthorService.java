package vn.phatbee.sachservletjdbc.services;

import vn.phatbee.sachservletjdbc.models.AuthorModel;

import java.util.List;

public interface IAuthorService {
    void insert(AuthorModel author);
    void update(AuthorModel author);
    void delete(AuthorModel author);
    List<AuthorModel> getAllAuthors(int currentPage, int size);
    int count();
    AuthorModel getAuthor(int id);
}
