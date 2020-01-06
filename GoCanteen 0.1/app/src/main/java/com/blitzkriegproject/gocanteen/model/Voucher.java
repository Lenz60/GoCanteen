package com.blitzkriegproject.gocanteen.model;

public class Voucher {
    private int id;
    private String amount,code;

    public Voucher(int id, String amount, String code) {
        this.id = id;
        this.amount = amount;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public String getAmount() {
        return amount;
    }

    public String getCode() {
        return code;
    }
}
