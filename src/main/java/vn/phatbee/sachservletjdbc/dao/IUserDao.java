package vn.phatbee.sachservletjdbc.dao;

import vn.phatbee.sachservletjdbc.models.UserModel;

public interface IUserDao {
    UserModel findById(int id);
    UserModel findByEmail(String email);
}
