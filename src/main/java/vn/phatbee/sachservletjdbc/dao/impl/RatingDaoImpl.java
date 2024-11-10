package vn.phatbee.sachservletjdbc.dao.impl;

import vn.phatbee.sachservletjdbc.configs.DBConnectSQL;
import vn.phatbee.sachservletjdbc.dao.IRatingDao;
import vn.phatbee.sachservletjdbc.models.RatingModel;
import vn.phatbee.sachservletjdbc.models.UserModel;
import vn.phatbee.sachservletjdbc.services.IUserService;
import vn.phatbee.sachservletjdbc.services.impl.UserServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RatingDaoImpl implements IRatingDao {
    public Connection conn = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;

    IUserService userService = new UserServiceImpl();


    @Override
    public List<RatingModel> getReviewByBook(int id) {
        List<RatingModel> ratings = findByBookId(id);
        for (RatingModel rating : ratings) {
            UserModel user = userService.findById(rating.getUserid());
            rating.setUser(user);
        }
        return ratings;
    }

    @Override
    public void addRating(RatingModel rating) {
        String sql = "INSERT INTO rating (userid,bookid,rating,review_text) VALUES (?,?,?,?)";
        try{
            conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, rating.getUserid());
            ps.setInt(2, rating.getBookid());
            ps.setInt(3, rating.getRating());
            ps.setString(4, rating.getReview_text());
            ps.executeUpdate();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public List<RatingModel> findByBookId(int id) {
        List<RatingModel> ratings = new ArrayList<>();
        String query = "SELECT r.*, u.email, u.id, u.fullname FROM rating r JOIN users u ON r.userid = u.id WHERE r.bookid = ?";

        try {
            conn =  new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();


            while (rs.next()) {
                RatingModel rating = new RatingModel();
                rating.setUserid(rs.getInt("userid"));
                rating.setBookid(rs.getInt("bookid"));
                rating.setRating(rs.getInt("rating"));
                rating.setReview_text(rs.getString("review_text"));

                // Lấy thông tin người dùng
                UserModel user = new UserModel();
                user.setId(rs.getInt("id"));
                user.setFullname(rs.getString("fullname"));
                user.setEmail(rs.getString("email"));
                rating.setUser(user);

                ratings.add(rating);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ratings;
    }

    @Override
    public List<RatingModel> getRatingsByBookId(int bookId) {
        return findByBookId(bookId);

    }
}

