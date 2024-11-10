package vn.phatbee.sachservletjdbc.services;

import vn.phatbee.sachservletjdbc.models.UserModel;

public interface IUserService {
    UserModel findById(int id);
    UserModel login(String username, String password);
    UserModel findByEmail(String email);

}
