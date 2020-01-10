package com.blitzkriegproject.gocanteen.model.NotificationOrder;


//"id":13,"name_user":"Rafly
//        Andrian","name_product":"Paket Mieayam & Esteh","place":"Basement
//        5","seat_number":5,"quantity":2,"total_price":24000,"status":"Menunggu"
public class OrderNotif {
    Integer id, quantity, total_price;
    String name_user, name_product, place, seat_number, status;

    public OrderNotif(Integer id, Integer quantity, Integer total_price, String name_user, String name_product, String place, String seat_number, String status) {
        this.id = id;
        this.quantity = quantity;
        this.total_price = total_price;
        this.name_user = name_user;
        this.name_product = name_product;
        this.place = place;
        this.seat_number = seat_number;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getTotal_price() {
        return total_price;
    }

    public String getName_user() {
        return name_user;
    }

    public String getName_product() {
        return name_product;
    }

    public String getPlace() {
        return place;
    }

    public String getSeat_number() {
        return seat_number;
    }

    public String getStatus() {
        return status;
    }
}
