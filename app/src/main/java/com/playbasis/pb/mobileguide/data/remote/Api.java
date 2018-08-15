package com.playbasis.pb.mobileguide.data.remote;


import com.playbasis.pb.mobileguide.data.entity.Image;
import com.playbasis.pb.mobileguide.data.entity.Mobile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    String BASE_URL = "https://scb-test-mobile.herokuapp.com/api/";

    @GET("mobiles/")
    Call<List<Mobile>> getMobiles();


    @GET("mobiles/{id}/images/")
    Call<List<Image>> getImageSlide(@Path("id") String id);


}
