package com.blitzkriegproject.gocanteen.model;

public class User {

   private int id;
   private String NIM, nama, email, gender;

    public User(int id, String NIM, String nama, String email, String gender) {
        this.id = id;
        this.NIM = NIM;
        this.nama = nama;
        this.email = email;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getNIM() {
        return NIM;
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
