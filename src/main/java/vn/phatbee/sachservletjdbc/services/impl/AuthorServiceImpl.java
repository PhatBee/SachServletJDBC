package vn.phatbee.sachservletjdbc.services.impl;

import vn.phatbee.sachservletjdbc.dao.IAuthorDao;
import vn.phatbee.sachservletjdbc.dao.impl.AuthorDaoImpl;
import vn.phatbee.sachservletjdbc.models.AuthorModel;
import vn.phatbee.sachservletjdbc.services.IAuthorService;

import java.util.List;

public class AuthorServiceImpl implements IAuthorService {
    private final IAuthorDao authorDao = new AuthorDaoImpl();

    @Override
    public void insert(AuthorModel author) {
        authorDao.insert(author);
    }

    @Override
    public void update(AuthorModel author) {
        authorDao.update(author);
    }

    @Override
    public void delete(AuthorModel author) {
        authorDao.delete(author);
    }

    @Override
    public List<AuthorModel> getAllAuthors(int currentPage, int size) {
        return authorDao.getAllAuthors(currentPage, size);
    }

    @Override
    public int count() {
        return authorDao.count();
    }

    @Override
    public AuthorModel getAuthor(int id) {
        return authorDao.getAuthor(id);
    }
}
