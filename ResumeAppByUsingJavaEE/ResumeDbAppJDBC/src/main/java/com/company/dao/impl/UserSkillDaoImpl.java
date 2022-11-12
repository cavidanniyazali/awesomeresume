/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.dao.impl;

import com.company.entity.Skill;
import com.company.entity.User;
import com.company.entity.UserSkill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import com.company.dao.inter.UserSkillDaoInter;

/**
 *
 * @author admin
 */
public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter {

	@SneakyThrows
	private UserSkill getUserSkill(ResultSet rs) {
		int userSkillId = rs.getInt("userSkillId");
		int userId = rs.getInt("id");
		int skillId = rs.getInt("skill_id");
		String skillName = rs.getString("skill_name");
		int power = rs.getInt("power");

		User user = new User(userId);
		Skill skill = new Skill(skillId, skillName);
		return new UserSkill(userSkillId, user, skill, power);
	}

	@Override
	public List<UserSkill> getAllSkillByUserId(int userId) {
		List<UserSkill> result = new ArrayList<>();
		try (Connection c = connect()) {
			PreparedStatement stmt = c.prepareStatement("SELECT"
				+ "	us.id as userSkillId,"
				+ "	u.*,"
				+ "	us.skill_id,"
				+ "	s.NAME AS skill_name,"
				+ "	us.power "
				+ "FROM"
				+ "	user_skill us"
				+ "	LEFT JOIN USER u ON us.user_id = u.id"
				+ "	LEFT JOIN skill s ON us.skill_id = s.id "
				+ "WHERE"
				+ "	us.user_id = ?");
			stmt.setInt(1, userId);
			stmt.execute();

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				UserSkill u = getUserSkill(rs);
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

	public boolean insertUserSkill(UserSkill u) {
		Connection conn;
		boolean b = true;
		try {
			conn = connect();
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO user_skill (skill_id , user_id ,power) VALUES (? , ? ,  ? ) ;");

			stmt.setInt(1, u.getSkill().getId());
			stmt.setInt(2, u.getUser().getId());
			stmt.setInt(3, u.getPower());

			b = stmt.execute();

		} catch (Exception ex) {
			System.err.println(ex);
			b = false;
		}
		return b;
	}

	public boolean updateUserSkill(UserSkill u) {
		Connection conn;
		boolean b = true;
		try {
			conn = connect();
			PreparedStatement stmt = conn.prepareStatement("UPDATE user_skill SET skill_id = ?,user_id =?,power =? WHERE id=?;");

			stmt.setInt(1, u.getSkill().getId());
			stmt.setInt(2, u.getUser().getId());
			stmt.setInt(3, u.getPower());

			stmt.setInt(4, u.getId());

			b = stmt.execute();

		} catch (Exception ex) {
			System.err.println(ex);
			b = false;
		}
		return b;
	}

	@Override
	public boolean removeUserSkill(int id) {
		Connection conn;
		try {
			conn = connect();

			PreparedStatement stmt = conn.prepareStatement("DELETE FROM user_skill  WHERE ID=?;");
			stmt.setInt(1, id);
			System.out.println("id :" + String.valueOf(id));
			return stmt.execute();

		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
	}
}
