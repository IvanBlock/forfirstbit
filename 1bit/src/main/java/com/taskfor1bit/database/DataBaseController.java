package com.taskfor1bit.database;


import com.taskfor1bit.models.ModelNews;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;


public class DataBaseController {

    private static final String URL = "jdbc:mysql://localhost:3306/newsdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private DataBaseController() {  //запрещаем создавать объекты класса БД

    }

    public static void insertToDBNews(List<ModelNews> modelNewses){  //заносим в БД лист новостей

        Properties prop = new Properties();
        System.out.println("test");
        try {
            prop.load(new FileInputStream(System.getProperty("user.home") + "/mydb.cfg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("user.home: "+System.getProperty("user.home"));
        String host = prop.getProperty("host").toString();
        String username = prop.getProperty("username").toString();
        String password = prop.getProperty("password").toString();
        String driver = prop.getProperty("driver").toString();

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try(Connection  connection = DriverManager.getConnection(host, username, password); PreparedStatement statement = connection.prepareStatement("INSERT INTO news(title, url) VALUES(?,?);")) {

            for (ModelNews model : modelNewses){
                statement.setString(1, model.getTitle());
                statement.setString(2, model.getUrl());
                statement.executeUpdate();
            }



        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public static List<ModelNews> getDataFromDBToArray() {  //забираем новости из БД

        List<ModelNews> modelNewses = new ArrayList<>();

        Properties prop = new Properties();
        System.out.println("test");
        try {
            prop.load(new FileInputStream(System.getProperty("user.home") + "/mydb.cfg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("user.home: "+System.getProperty("user.home"));
        String host = prop.getProperty("host").toString();
        String username = prop.getProperty("username").toString();
        String password = prop.getProperty("password").toString();
        String driver = prop.getProperty("driver").toString();

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(host, username, password)) {

            Statement statement = connection.createStatement();
            String sql = ("SELECT * FROM news");
            ResultSet set = statement.executeQuery(sql);

            while (set!=null && set.next()){
                modelNewses.add(new ModelNews(set.getNString("title"), set.getNString("url")));
            }


        } catch (SQLException e) {

            e.printStackTrace();
        }

        Collections.reverse(modelNewses);
        return modelNewses;
    }
}
