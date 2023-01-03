package com.example.apisqlserver;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectSQLServer {
    Statement statement;
    Connection conn = null;

    // context để Toast thông báo hoàn thành ra màn hình
    Context context;
    public ConnectSQLServer(Context context) {
        this.context = context;
    }

    @SuppressLint("NewApi")
    // biến branch là db của sql server
    public void connectionclass(String branch) {
        // thông tin của sql server
        String ip = "125.212.238.246";
        String port = "9898";
        String db = "TVApp_" + branch;
        String un = "dangld";
        String password = "Dang@123";
        // build
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String ConnectionURL = null;
        // kết nối đến sql server thông qua jdbc
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";" + "databaseName=" + db + ";user=" + un + ";password=" + password + ";";
            conn = DriverManager.getConnection(ConnectionURL);

        } catch (Exception ex) {
            Log.e("ERROR", ex.getMessage());
        }
    }

    // kiểm tra tính giá thành từ sql server
    public void CheckKho(String branch) {
        // khi call CheckKho sẽ đẩy vào giá trị db và truyền giá trị db vào cho connectionclass
        connectionclass(branch);
        try {
            // query cho sql server
            String query = "select PerNbr,SiteID,Crtd_Datetime,Crtd_User from IN_WrkCosting";
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                // code get data
                break;
            }
            rs.close();
        } catch (Exception ex) {
            Log.e("Error: ", ex.getMessage());
        } finally {
            try {
                // chờ xử lý thành công rồi Toast ra context
                Toast.makeText(context, "Xử lý thành công", Toast.LENGTH_SHORT).show();
                statement.close();
                conn.close();
            } catch (Exception e) {
            }
        }
    }
}

