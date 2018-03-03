package io.inthelab.sampleproject.api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
/**
* Interface Service for Retrofit calls
*
* */
public interface AppService {

    @GET("/albums/1/photos")
    Call<ArrayList<PhotoModel>> fetchPhotos();


    @FormUrlEncoded
    @POST("/login")
    Call<LogInResponseModel> userLogIn(@Field("username") String username, @Field("password") String password);


    @GET("/api/?results=20&inc=name,email,picture")
    Call<UserModel.AppUserListResults> fetchUsers();

}
