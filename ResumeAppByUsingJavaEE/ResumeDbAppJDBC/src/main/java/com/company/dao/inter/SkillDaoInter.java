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

    public List<Skill> getAllSkills();

    public Skill getSkillById(int id);

    public boolean updateSkill(Skill u);

    public boolean addSkill(Skill u);

    public boolean removeSkill(int id);
}
