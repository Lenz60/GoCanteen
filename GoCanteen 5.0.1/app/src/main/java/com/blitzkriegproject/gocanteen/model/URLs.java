package com.blitzkriegproject.gocanteen.model;

public class URLs {
//Ganti Base Url berdasarkan IP local anda, dicek di cmd menggunakan "ipconfig -all"
//Lalu copy ip dari "IPv4 Address. . . . . . . . . . . : 192.168.1.101(Preferred)"
//Kemudian paste ip tersebut ke BASE_URL
    private static final String BASE_URL = "http://pasarjasa-id.000webhostapp.com/api/";
    private static final String ROOT_URL = BASE_URL + "api.php?apicall=";

    public static final String URL_REGISTER = ROOT_URL + "signup";
    public static final String URL_LOGIN = ROOT_URL + "login";
    public static final String UPLOAD_URL = BASE_URL + "uplod.php";
    public static final String URL_TOPUP = ROOT_URL + "topup";
    public static final String URL_PRODUCT1 = ROOT_URL + "product=mieayam";
    public static final String URL_PRODUCT2 = ROOT_URL + "product=bakso";
    public static final String URL_PRODUCT3 = ROOT_URL + "product=magelangan";
    public static final String URL_ORDER = ROOT_URL + "order";
    public static final String URL_NOTIF= ROOT_URL + "order=notification";


}
