package com.dystopiaproject.gocanteen15.apihelper;

public class UtilsApi {
    //10.0.2.2 localhost
    //ganti IP dengan IPv4 pc masing masing
    public static final String BASE_URL_API = "http://192.168.1.101/";

    //Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
