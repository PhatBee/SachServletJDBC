package vn.phatbee.sachservletjdbc.dao;

import vn.phatbee.sachservletjdbc.models.RatingModel;

import java.util.List;

public interface IRatingDao {
    List<RatingModel> getReviewByBook(int id);
    void addRating(RatingModel rating);
    List<RatingModel> findByBookId(int id);
    List<RatingModel> getRatingsByBookId(int bookId);
}
