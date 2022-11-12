/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.dao.impl;

import com.company.entity.Country;
import com.company.dao.inter.CountryDaoInter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;

/**
 *
 * @author admin
 */
public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter {
    
    @SneakyThrows
    private Country getCountry(ResultSet rs) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String nationality = rs.getString("nationality");

//        return new Country(null, name, null);
        Country country = new Country(id, name, nationality);
        System.out.println(country);
        return country;
    }
    
    @Override
    public List<Country> getAllCountries() {
        List<Country> result = new ArrayList<>();
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.execute("SELECT * from country;");
            ResultSet rs = stmt.getResultSet();
            
            while (rs.next()) {
                Country u = getCountry(rs);
                result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    @Override
    public boolean updateCountry(Country u) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("update country set name=?,nationality=? where id=?");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getNationality());
            stmt.setInt(3, u.getId());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean removeCountry(int id) {
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            return stmt.execute("delete from country where id = " + id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    @Override
    public Country getCountryById(int id) {
        Country result = null;
        try (Connection c = connect()) {
//            Statement stmt = c.createStatement();
//            stmt.execute("select name from country where id=" + id);
            PreparedStatement stmt = c.prepareStatement("select * from country where id=?");
            stmt.setInt(1, id);
            stmt.execute();

//            stmt.execute("select name from country where id=" + id);
            ResultSet rs = stmt.getResultSet();
            
            while (rs.next()) {
                result = getCountry(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    @Override
    public boolean addCountry(Country u) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into country(name,nationality) values(?,?)");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getNationality());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
