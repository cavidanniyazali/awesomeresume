/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.entity;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author admin
 */
public class User {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String profileDesc;
    private String password;
    private String address;
    private Country nationality;
    private Date birthDate;
    private Country birthPlace;
    private List<UserSkill> skills;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(int id, String name, String surname, String email, String phone, String profileDesc, String address, Country nationality, Date birthDate, Country birthPlace) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.profileDesc = profileDesc;
        this.address = address;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Country getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(Country birthPlace) {
        this.birthPlace = birthPlace;
    }

    public List<UserSkill> getSkills() {
        return skills;
    }

    public void setSkills(List<UserSkill> skills) {
        this.skills = skills;
    }

    public String getProfileDesc() {
        return profileDesc;
    }

    public void setProfileDesc(String profileDesc) {
        this.profileDesc = profileDesc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + ", phone=" + phone + ", profileDesc=" + profileDesc + ", address=" + address + ", birthDate=" + birthDate + ", nationality=" + nationality + ", birthPlace=" + birthPlace + "}";
    }
}
