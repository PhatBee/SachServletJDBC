package vn.phatbee.sachservletjdbc.services.impl;

import vn.phatbee.sachservletjdbc.dao.IRatingDao;
import vn.phatbee.sachservletjdbc.dao.impl.RatingDaoImpl;
import vn.phatbee.sachservletjdbc.models.RatingModel;
import vn.phatbee.sachservletjdbc.services.IRatingService;

import java.util.List;

public class RatingServiceImpl implements IRatingService {
    IRatingDao ratingDao = new RatingDaoImpl();

    @Override
    public List<RatingModel> getReviewByBook(int id) {
        return ratingDao.getReviewByBook(id);
    }

    @Override
    public void addRating(RatingModel rating) {
        ratingDao.addRating(rating);
    }

    @Override
    public List<RatingModel> getRatingsByBookId(int id) {
        return ratingDao.getRatingsByBookId(id);
    }
}
