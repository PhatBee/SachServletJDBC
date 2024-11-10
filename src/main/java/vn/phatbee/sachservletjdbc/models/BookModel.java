package vn.phatbee.sachservletjdbc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookModel implements Serializable {
    private int bookid;
    private int isbn;
    private String title;
    private String publisher;
    private double price;
    private String description;
    private Date publish_date;
    private String cover_image;
    private int quantity;
    private List<AuthorModel> authors; // Thêm thuộc tính authors
    private List<RatingModel> ratings = new ArrayList<>();


    @Override
    public String toString() {
        return "BookModel{" +
                "bookid=" + bookid +
                ", isbn=" + isbn +
                ", title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", publish_date=" + publish_date +
                ", cover_image='" + cover_image + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
