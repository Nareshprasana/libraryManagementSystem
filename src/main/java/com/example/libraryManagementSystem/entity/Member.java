package com.example.libraryManagementSystem.entity;

import java.time.LocalDate;

public class Member {
    private int id;
    private String name;
    private String phone;
    private LocalDate registeredDate;

    // Constructors
    public Member() {
    }

    public Member(int id, String name, String phone, LocalDate registeredDate) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.registeredDate = registeredDate;
    }

    // Getters and Setters
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(LocalDate registeredDate) {
        this.registeredDate = registeredDate;
    }

    // toString method
    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", registeredDate=" + registeredDate +
                '}';
    }
}
