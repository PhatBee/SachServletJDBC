package vn.phatbee.sachservletjdbc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserModel implements Serializable {
    private int id;
    private String email;
    private String fullname;
    private String password;
    private Timestamp signup_date;
    private Timestamp last_login;
    private boolean is_admin;
    private List<RatingModel> ratings = new ArrayList<>();

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", fullname='" + fullname + '\'' +
                ", password='" + password + '\'' +
                ", signup_date=" + signup_date +
                ", last_login=" + last_login +
                ", is_admin=" + is_admin +
                '}';
    }

    public boolean isIs_admin() {
        return is_admin;
    }
}
