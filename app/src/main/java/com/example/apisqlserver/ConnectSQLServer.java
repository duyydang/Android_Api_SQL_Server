package com.example.apisqlserver;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectSQLServer {
    Connection connection;

    @SuppressLint("NewApi")

    public Connection connectionclass() {
        String ip = "tuanviet-trading.com";
        String port = "1445";
        String db = "TV_Onboard";
        String un = "system";
        String password = "Tu@nVi#t@2021";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnectionURL = null;

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";" + "databaseName=" + db + ";user=" + un + ";password=" + password + ";";
            conn = DriverManager.getConnection(ConnectionURL);

        } catch (Exception ex) {
            Log.e("ERROR", ex.getMessage());
        }
        return conn;
    }

    public void GetTextFromSQL(String user, String pass) {
        String ConnectionResult = "";
        try {
            ConnectSQLServer connectionHelper = new ConnectSQLServer();
            connection = connectionHelper.connectionclass();
            if (connection != null) {
                String query = "SELECT * FROM Account ";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);

                while (rs.next()) {
                    // Code cần thiết

                }
            } else {
                ConnectionResult = "Check Connection";
            }
        } catch (Exception ex) {
            Log.e("Error: ", ex.getMessage());
        }
    }
}

