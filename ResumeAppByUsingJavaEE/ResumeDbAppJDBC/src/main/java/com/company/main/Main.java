/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.main;

import com.company.entity.Country;
import com.company.dao.inter.CountryDaoInter;

/**
 *
 * @author admin
 */
public class Main {
    
    public static void main(String[] args) {
//        System.out.println("\"");
//        UserDaoInter userDao = new UserDaoImpl();//thightly coupling
//        UserDaoInter userDao = Context.instanceUserDao();//loosely coupling
//        List<User> list = userDao.getAll();
//        
//        userDao.removeUser(1);
//        List<User> list2 = userDao.getAll();
//        
//        System.out.println("list=" + list);
//        System.out.println("list2=" + list2);

//        User u = new User();
//        u.setId(3);
//        u.setName("Azay';DELETE FROM user;commit;SELECT'");
//        userDao.updateUser(u);
//
//        User u2 = userDao.getById(2);
//        u2.setName("Ferid");
//        userDao.updateUser(u2);
//
//        User u3 = new User(0, "Kenan", "Niyazali", "kananniyazali@gmail.com", "+99455-565-63-92");
//        userDao.addUser(u3);
//
//        System.out.println(userDao.getAll());
//  
//        UserSkillDaoInter userDao = Context.instanceUserSkillDao();
//        System.out.println(userDao.getAllSkillByUserId(1));
//
//        EmploymentHistoryDaoInter Dao = Context.instanceEmploymentHistoryDao();
//        System.out.println(Dao.getAllEmploymentHistoryByUserId(1));
//
//        SkillDaoInter dao = Context.instanceSkillDao();
////        System.out.println(dao.getAllSkills());
//        Skill s = dao.getSkillById(1);
//        System.out.println(s);
////        s.setName("SQL");
////        dao.updateSkill(s);
        CountryDaoInter dao = Context.instanceCountryDao();
//        dao.getCountryById(2);
        Country c = dao.getCountryById(2);
//        dao.updateCountry(c);
        System.out.println(c);
    }
}
