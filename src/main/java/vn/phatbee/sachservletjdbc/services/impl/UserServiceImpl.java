package vn.phatbee.sachservletjdbc.services.impl;

import vn.phatbee.sachservletjdbc.dao.IUserDao;
import vn.phatbee.sachservletjdbc.dao.impl.UserDaoImpl;
import vn.phatbee.sachservletjdbc.models.UserModel;
import vn.phatbee.sachservletjdbc.services.IUserService;

public class UserServiceImpl implements IUserService {
    IUserDao userDao = new UserDaoImpl();

    @Override
    public UserModel findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public UserModel login(String email, String password) {
        UserModel user = this.findByEmail(email);
        if (user != null && password.equals(user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public UserModel findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
