package com.blitzkriegproject.gocanteen.model.Menu;

public class Mieayam {
    private int id, id_shop, price, stock, total_sold;
    private String name, category;

    public Mieayam(int id, int id_shop, int price, int stock, int total_sold, String name, String category) {
        this.id = id;
        this.id_shop = id_shop;
        this.price = price;
        this.stock = stock;
        this.total_sold = total_sold;
        this.name = name;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public int getId_shop() {
        return id_shop;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getTotal_sold() {
        return total_sold;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }
}
