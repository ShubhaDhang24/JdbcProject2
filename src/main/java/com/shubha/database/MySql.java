package com.shubha.database;

import java.sql.*;

public class MySql {
    private  static  final String userName="root";
    private static final String password="2424";
    private static final String url="jdbc:mysql://localHost:3306/todoit";


    public static Connection getConnection()
        {
            Connection connection=null;
            try {
                connection = DriverManager.getConnection(url,userName,password);
            } catch (SQLException e) {
                System.out.println(e.getStackTrace());
            }
            return connection;
        }
    }