/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.dao.inter;

import com.company.entity.Skill;
import java.util.List;

/**
 *
 * @author admin
 */
public interface SkillDaoInter {

	List<Skill> getAll();

	public Skill getById(int id);

	boolean updateSkill(Skill u);

	boolean removeSkill(int id);

	public List<Skill> getByName(String name);

	public boolean insertSkill(Skill skl);
}
