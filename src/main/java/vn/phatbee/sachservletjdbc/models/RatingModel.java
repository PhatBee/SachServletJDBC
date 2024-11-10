package vn.phatbee.sachservletjdbc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RatingModel implements Serializable {
    private int userid;
    private int bookid;
    private int rating;
    private String review_text;
    private UserModel user; // Thêm thuộc tính user để hiển thị thông tin người dùng

    @Override
    public String toString() {
        return "RatingModel{" +
                "userid=" + userid +
                ", bookid=" + bookid +
                ", rating=" + rating +
                ", review_text='" + review_text + '\'' +
                '}';
    }
}
