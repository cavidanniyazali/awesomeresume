/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.entity.Country;
import com.company.entity.User;
import com.company.dao.inter.UserDaoInter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class UserDaoImpl extends AbstractDAO implements UserDaoInter {

    private User getUser(ResultSet rs) throws Exception{
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        String profileDescription = rs.getString("profile_description");
        String address = rs.getString("address");
        int nationalityId = rs.getInt("nationality_id");
        int birtplaceId = rs.getInt("birthplace_id");
        String nationalityStr = rs.getString("nationality");
        String birthplaceStr = rs.getString("birthplace");
        Date birthDate = rs.getDate("birthdate");

        Country nationality = new Country(nationalityId, null, nationalityStr);
        Country birthPlace = new Country(birtplaceId, birthplaceStr, null);

        return new User(id, name, surname, email, phone, profileDescription, address, nationality, birthDate, birthPlace);
    }

    private User getUserSimple(ResultSet rs) throws Exception{
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        String profileDescription = rs.getString("profile_description");
        String address = rs.getString("address");
        int nationalityId = rs.getInt("nationality_id");
        int birtplaceId = rs.getInt("birthplace_id");
        Date birthDate = rs.getDate("birthdate");

        User user= new User(id, name, surname, email, phone, profileDescription, address, null, birthDate, null);
        user.setPassword(rs.getString("password"));

        return user;
    }

    @Override
    public List<User> getAll(String name,String surname,Integer nationalityId) {
        List<User> result = new ArrayList<>();
        try (Connection c = connect()) {
            String sql="SELECT"
                    + "	u.*,"
                    + "	n.nationality,"
                    + "	c.name AS birthplace "
                    + "FROM"
                    + "	USER u"
                    + "	LEFT JOIN country n ON u.nationality_id = n.id"
                    + "	LEFT JOIN country c ON u.birthplace_id = c.id where 1=1";
            if(name!=null && !name.trim().isEmpty()){
                sql+=" and u.name=?";
            }
            if(surname!=null && !surname.trim().isEmpty()){
                sql+=" and u.surname=?";
            }
            if(nationalityId!=null){
                sql+=" and u.nationality_id=?";
            }

            PreparedStatement stmt = c.prepareStatement(sql);

            int i=1;
            if(name!=null && !name.trim().isEmpty()){
                stmt.setString(i,name);
                i++;//2
            }
            if(surname!=null && !surname.trim().isEmpty()){
                stmt.setString(i,surname );
                i++;//3
            }
            if(nationalityId!=null){
                stmt.setInt(i,nationalityId);
            }
            stmt.execute();


            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                User u = getUser(rs);
                result.add(u);
            }
//            System.out.println(c.getClass().getName());
//            c.close();
//            rs.close();
//            stmt.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        User result=null;
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select * from user where email=? and password=?");
            stmt.setString(1,email);
            stmt.setString(2,password);

            ResultSet rs=stmt.executeQuery();
            while (rs.next()){
                result=getUserSimple(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public User findByEmail(String email) {
        User result=null;
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select * from user where email=?");
            stmt.setString(1,email);

            ResultSet rs=stmt.executeQuery();
            while (rs.next()){
                result=getUserSimple(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public boolean updateUser(User u) {
        try (Connection c = connect()) {
//            Statement stmt = c.createStatement();
            PreparedStatement stmt = c.prepareStatement("update user set name=?,surname=?,email=?,phone=?,profile_description=?,address=?,birthdate=?,birthplace_id=?,nationality_id=? where id=?");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getPhone());
            stmt.setString(5, u.getProfileDesc());
            stmt.setString(6, u.getAddress());
            stmt.setDate(7, u.getBirthDate());
            stmt.setInt(8, u.getBirthPlace().getId());
            stmt.setInt(9, u.getNationality().getId());
            stmt.setInt(10, u.getId());
//            return stmt.execute("update user set name = 'JAVIDAN' where id = 1");
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeUser(int id) {
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            return stmt.execute("delete from user  where id = " + id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public User getById(int userId) {
        User result = null;
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.execute("SELECT"
                    + "	u.*,"
                    + "	n.nationality,"
                    + "	c.name AS birthplace "
                    + "FROM"
                    + "	USER u"
                    + "	LEFT JOIN country n ON u.nationality_id = n.id"
                    + "	LEFT JOIN country c ON u.birthplace_id = c.id where u.id=" + userId);
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                result = getUser(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    private static BCrypt.Hasher crypt=BCrypt.withDefaults();

    @Override
    public boolean addUser(User u) {
        try (Connection c = connect()) {
//            Statement stmt = c.createStatement();
            PreparedStatement stmt = c.prepareStatement("insert into user(name,surname,email,password,phone,profile_description,address,birthdate) values(?,?,?,?,?,?,?,?)");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, crypt.hashToString(4,u.getPassword().toCharArray()));
            stmt.setString(5, u.getPhone());
            stmt.setString(6, u.getProfileDesc());
            stmt.setString(7, u.getAddress());
            stmt.setDate(8, u.getBirthDate());
//            return stmt.execute("update user set name = 'JAVIDAN' where id = 1");
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
//        User u=new User(0,  "test","test" ,"test" ,"test",null,null,null,null,null);
//        u.setPassword("12345");
//        new UserDaoImpl().addUser(u);
        System.out.println(crypt.hashToString(4,"12345".toCharArray()));
    }

}
