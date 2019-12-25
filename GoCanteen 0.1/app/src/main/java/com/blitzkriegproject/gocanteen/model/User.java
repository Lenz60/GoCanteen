package com.blitzkriegproject.gocanteen.model;

public class User {

   private int id;
   private String nim, nama, email, gender;


    public User(int id, String nim, String nama, String email, String gender) {
        this.id = id;
        this.nim = nim;
        this.nama = nama;
        this.email = email;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }
}
