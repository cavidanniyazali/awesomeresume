/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.dao.impl;

import com.company.entity.Skill;
import com.company.dao.inter.SkillDaoInter;
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
public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter {

    @SneakyThrows
    private Skill getSkill(ResultSet rs) {
        int id = rs.getInt("id");
        String name = rs.getString("name");

        return new Skill(id, name);
    }

    @Override
    public List<Skill> getAllSkills() {
        List<Skill> result = new ArrayList<>();
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.execute("SELECT * from skill;");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                Skill u = getSkill(rs);
                result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

//    @Override
//    public boolean updateSkill(Skill u) {
//        try (Connection c = connect()) {
//            PreparedStatement stmt = c.prepareStatement("update skill set name=? where id=?");
//            stmt.setString(1, u.getName());
//            stmt.setInt(2, u.getId());
//            return stmt.execute();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return false;
//        }
//    }
    @Override
    public boolean removeSkill(int id) {
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            return stmt.execute("delete from skill where id = " + id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

//    @Override
//    public Skill getSkillById(int id) {
//        Skill result = null;
//        try (Connection c = connect()) {
//            Statement stmt = c.createStatement();
//            stmt.execute("select name from skill where id=" + id);
//            ResultSet rs = stmt.getResultSet();
//
//            while (rs.next()) {
//                result = getSkill(rs);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return result;
//    }
    @Override
    public Skill getSkillById(int userId) {
        Skill usr = null;
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM skill WHERE ID = ?");
            stmt.setInt(1, userId);
            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                int id = rs.getInt("Id");
                String name = rs.getString("name");

                usr = new Skill(id, name);

            }
        } catch (Exception ex) {

        }
        return usr;
    }

    @Override
    public boolean updateSkill(Skill u) {
        Connection conn;
        boolean b = true;
        try {
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("UPDATE skill SET name=? WHERE id= ?");
            stmt.setString(1, u.getName());
            stmt.setInt(2, u.getId());
            b = stmt.execute();

        } catch (Exception ex) {
            System.err.println(ex);
            b = false;
        }
        return b;
    }

    @Override
    public boolean addSkill(Skill u) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into skill(name) values(?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, u.getName());
            stmt.execute();

            ResultSet generatedKeys = stmt.getGeneratedKeys();

            if (generatedKeys.next()) {
                u.setId(generatedKeys.getInt(1));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
