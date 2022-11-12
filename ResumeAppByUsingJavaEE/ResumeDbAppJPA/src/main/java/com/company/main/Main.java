/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.main;

import com.company.dao.inter.AbstractDAO;
import com.company.entity.User;
import com.company.dao.inter.UserDaoInter;
import com.company.entity.Country;

/**
 *
 * @author admin
 */
public class Main {

	public static void main(String[] args) {
		UserDaoInter dao = Context.instanceUserDao();

		User b = dao.findByEmail("cavidan.niyazali@gmail.com");
		Country c = b.getBirthPlace();

//		System.out.println(c.getId());
//		System.out.println(dao.findByEmail("cavidan.niyazali@gmail.com"));
//		System.out.println(dao.findByEmail("cavidan.niyazali@gmail.com"));
//		System.out.println(dao.findByEmail("cavidan.niyazali@gmail.com"));
//		System.out.println(dao.findByEmail("cavidan.niyazali@gmail.com"));
//		System.out.println(dao.findByEmail("cavidan.niyazali@gmail.com"));

//		AbstractDAO.closeEmf();
	}

}
