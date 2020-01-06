package com.blitzkriegproject.gocanteen.model;

public class User {

    private int id;
    private String name, email, saldo;

    public User(int id, String name, String email, String saldo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.saldo = saldo;
    }



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSaldo() {
        return saldo;
    }

}