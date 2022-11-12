/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import lombok.SneakyThrows;

/**
 *
 * @author admin
 */
public abstract class AbstractDAO {

    @SneakyThrows
    public Connection connect() {
        Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/resume";
        String username = "root";
        String password = "12345";
        Connection c = DriverManager.getConnection(url, username, password);
        return c;
    }
}
