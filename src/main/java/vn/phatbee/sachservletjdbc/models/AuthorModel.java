package vn.phatbee.sachservletjdbc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthorModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private int author_id;
    private String author_name;
    private Date date_of_birth;

    @Override
    public String toString() {
        return "AuthorModel{" +
                "author_id=" + author_id +
                ", author_name='" + author_name + '\'' +
                ", date_of_birth=" + date_of_birth +
                '}';
    }
}
