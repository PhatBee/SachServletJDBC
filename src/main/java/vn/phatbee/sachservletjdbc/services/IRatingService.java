package vn.phatbee.sachservletjdbc.services;

import vn.phatbee.sachservletjdbc.models.RatingModel;

import java.util.List;

public interface IRatingService {
    List<RatingModel> getReviewByBook(int id);
    void addRating(RatingModel rating);

    List<RatingModel> getRatingsByBookId(int id);
}
