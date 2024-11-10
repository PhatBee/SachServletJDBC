package vn.phatbee.sachservletjdbc.dao.impl;

import vn.phatbee.sachservletjdbc.configs.DBConnectSQL;
import vn.phatbee.sachservletjdbc.dao.IUserDao;
import vn.phatbee.sachservletjdbc.models.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements IUserDao {
    public Connection conn = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;

    @Override
    public UserModel findById(int id) {
        String sql = "select * from users where id = ?";
        try
        {
            conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()){
                UserModel user = new UserModel();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("passwd"));
                user.setFullname(rs.getString("fullname"));
                user.setSignup_date(rs.getTimestamp("signup_date"));
                user.setLast_login(rs.getTimestamp("last_login"));
                user.set_admin(rs.getBoolean("_admin"));
                return user;
            }


        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserModel findByEmail(String email) {
        String sql = "select * from users where email = ?";
        try
        {
            conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()){
                UserModel user = new UserModel();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("passwd"));
                user.setFullname(rs.getString("fullname"));
                user.setSignup_date(rs.getTimestamp("signup_date"));
                user.setLast_login(rs.getTimestamp("last_login"));
                user.set_admin(rs.getBoolean("is_admin"));
                return user;
            }


        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
