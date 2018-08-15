package com.playbasis.pb.mobileguide.presentation.detail;

import com.playbasis.pb.mobileguide.data.entity.Image;
import com.playbasis.pb.mobileguide.data.remote.Api;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MobileDetailPresenter implements IMobileDetail.Presenter{

    private IMobileDetail.View view;
    private ArrayList<Image> imageArrayList = new ArrayList<>();
    private Api api;


    public MobileDetailPresenter(IMobileDetail.View view, Api api) {
        this.view = view;
        this.api = api;
    }

    @Override
    public void callWebService(String id) {

        Call<List<Image>> call = api.getImageSlide(id);
        call.enqueue(new Callback<List<Image>>() {
            @Override
            public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {

                List<Image> images = response.body();
                imageArrayList.addAll(images);

                view.populateList(imageArrayList);

            }

            @Override
            public void onFailure(Call<List<Image>> call, Throwable t) {

            }
        });

    }
}
