package com.playbasis.pb.mobileguide.data.remote;

import com.playbasis.pb.mobileguide.data.entity.Image;
import com.playbasis.pb.mobileguide.data.entity.Mobile;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MobileRemoteDataStore {

    private Api mobileApi;

    public interface MobileDataResult<E> {
        void onSuccess(ArrayList<E> result);
        void onError(String error);
    }


    public MobileRemoteDataStore(){
        this.mobileApi = ServiceFactory.getRetrofit().create(Api.class);
    }

    public void getMobiles(final MobileDataResult mobileDataResult){

        Call<List<Mobile>> call = mobileApi.getMobiles();

        call.enqueue(new Callback<List<Mobile>>() {
            @Override
            public void onResponse(Call<List<Mobile>> call, Response<List<Mobile>> response) {

                ArrayList<Mobile> mobileArrayList = new ArrayList<>();

                List<Mobile> mobiles = response.body();
                mobileArrayList.addAll(mobiles);
                mobileDataResult.onSuccess(mobileArrayList);
            }

            @Override
            public void onFailure(Call<List<Mobile>> call, Throwable t) {
                mobileDataResult.onError(t.getMessage());
            }
        });

    }


    public void getImages(String id, final MobileDataResult mobileDataResult){

        Call<List<Image>> call = mobileApi.getImageSlide(id);
        call.enqueue(new Callback<List<Image>>() {
            @Override
            public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {
                ArrayList<Image> imageArrayList = new ArrayList<>();
                List<Image> images = response.body();
                imageArrayList.addAll(images);
                mobileDataResult.onSuccess(imageArrayList);
            }

            @Override
            public void onFailure(Call<List<Image>> call, Throwable t) {
                mobileDataResult.onError(t.getMessage());
            }
        });

    }

}
