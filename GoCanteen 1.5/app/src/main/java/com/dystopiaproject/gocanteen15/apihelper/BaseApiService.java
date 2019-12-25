package com.dystopiaproject.gocanteen15.apihelper;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BaseApiService {

    //call api login
    @FormUrlEncoded
    @POST("api.php?apicall=login")
    Call<ResponseBody> loginRequest(@Field("nim") String nim,
                                    @Field("password") String password);

    //call api register
    @FormUrlEncoded
    @POST("api.php?apicall=signup")
    Call<ResponseBody> registerRequest(@Field("nim") String nim,
                                       @Field("nama") String nama,
                                       @Field("email") String email,
                                       @Field("password") String password,
                                       @Field("gender") String gender);

}
