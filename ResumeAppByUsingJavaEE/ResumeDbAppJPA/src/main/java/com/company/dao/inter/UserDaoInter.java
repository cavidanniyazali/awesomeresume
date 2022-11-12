/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.dao.inter;

import com.company.entity.User;
import java.util.List;

/**
 *
 * @author admin
 */
public interface UserDaoInter {

	public List<User> getAll(String name, String surname, Integer nationalityId);

	public User findByEmailAndPassword(String email, String password);

	public User getById(int id);

	public boolean addUser(User u);

	public boolean updateUser(User u);

	public boolean removeUser(int id);

	public User findByEmail(String email);
}
