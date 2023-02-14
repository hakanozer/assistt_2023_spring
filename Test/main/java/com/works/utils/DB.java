package com.works.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {

    final private String driver = "org.h2.Driver";
    final private String url = "jdbc:h2:file:~/paycore_2_test;OLD_INFORMATION_SCHEMA=TRUE";
    final private String username = "sa";
    final private String password = "sa";

    public boolean connect() {
        boolean status = false;
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, username, password);
            status = true;
        }catch (Exception ex) {
            System.err.println("connect error : " + ex);
        }
        return status;
    }


}
