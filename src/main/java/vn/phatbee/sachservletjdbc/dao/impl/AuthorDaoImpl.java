package vn.phatbee.sachservletjdbc.dao.impl;

import vn.phatbee.sachservletjdbc.configs.DBConnectSQL;
import vn.phatbee.sachservletjdbc.dao.IAuthorDao;
import vn.phatbee.sachservletjdbc.models.AuthorModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoImpl implements IAuthorDao {
    public Connection conn = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;

    @Override
    public void insert(AuthorModel author) {
        String sql = "insert into author(author_name, date_of_birth) values(?,?)";
        try{
            conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, author.getAuthor_name());
            ps.setDate(2, new java.sql.Date(author.getDate_of_birth().getTime()));
            ps.executeUpdate();
            conn.close();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(AuthorModel author) {
        String sql = "update author set author_name=?,date_of_birth=? where author_id=?";
        try{
            conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, author.getAuthor_name());
            ps.setDate(2, new java.sql.Date(author.getDate_of_birth().getTime()));
            ps.setInt(3, author.getAuthor_id());
            ps.executeUpdate();
            conn.close();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(AuthorModel author) {
        String sql = "delete from author where author_id=?";
        try{
            conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, author.getAuthor_id());
            ps.executeUpdate();
            conn.close();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public List<AuthorModel> getAllAuthors(int currentPage, int size) {
        String sql = "SELECT * FROM author ORDER BY author_id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        List<AuthorModel> list = new ArrayList<>();
        try{
            conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);

            // Tính toán chỉ số bắt đầu cho phân trang
            int start = (currentPage - 1) * size;

            ps.setInt(1, start);
            ps.setInt(2, size);

            rs = ps.executeQuery();
            while(rs.next()) {
                AuthorModel author = new AuthorModel();
                author.setAuthor_id(rs.getInt("author_id"));
                author.setAuthor_name(rs.getString("author_name"));
                author.setDate_of_birth(rs.getDate("date_of_birth"));
                list.add(author);
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public int count() {
        String sql = "select count(*) from author";
        int count = 0;
        try {
            conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public AuthorModel getAuthor(int id) {
        String sql = "select * from author where author_id=?";
        try{
            conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            while(rs.next()) {
                AuthorModel author = new AuthorModel();
                author.setAuthor_id(rs.getInt("author_id"));
                author.setAuthor_name(rs.getString("author_name"));
                author.setDate_of_birth(rs.getDate("date_of_birth"));
                return author;
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
