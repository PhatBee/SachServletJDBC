package vn.phatbee.sachservletjdbc.configs;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectSQL {
    private final String serverName = "LAPTOP-1BLCIFVL\\PHATBEE";
    private final String dbName = "ltwebst2";
    private final String portNumber = "1433";
    private final String userID = "sa";
    private final String password = "0000";

    public Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://" + serverName + ";databaseName=" + dbName;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);
    }

    public static void main(String[] args) {
        try {
            System.out.println(new DBConnectSQL().getConnection());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
