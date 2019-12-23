package com.blitzkriegproject.gocanteen.model;

public class User {

   private int id;
   private String NIM, nama, email, gender;

   public User(int id, String NIM, String nama, String email, String gender){
       this.id = id;
       this.NIM = NIM;
       this.nama = nama;
       this.email = email;
       this.gender = gender;
   }
}
