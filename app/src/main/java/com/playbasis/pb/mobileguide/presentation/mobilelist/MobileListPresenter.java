package com.playbasis.pb.mobileguide.presentation.mobilelist;

import com.playbasis.pb.mobileguide.data.entity.Mobile;
import com.playbasis.pb.mobileguide.data.local.MobileDataStore;
import com.playbasis.pb.mobileguide.data.remote.Api;
import com.playbasis.pb.mobileguide.data.remote.ServiceFactory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MobileListPresenter implements IMobileList.Presenter {

    private IMobileList.View view;
    private MobileDataStore mobileDataStore;
    private Api api;
    private ArrayList<Mobile> mobileArrayList = new ArrayList<>();

    public MobileListPresenter(IMobileList.View view, MobileDataStore mobileDataStore, Api api){
        this.view = view;
        this.mobileDataStore = mobileDataStore;
        this.api = api;
    }

    @Override
    public void getMobileList() {

        mobileArrayList.addAll(mobileDataStore.getAllMobiles());

        if(mobileArrayList.isEmpty()){
            getMobileFromApi();
        } else{
            view.populateList(mobileArrayList);
        }
    }

    private void getMobileFromApi(){

        Call<List<Mobile>> call = api.getMobiles();

        call.enqueue(new Callback<List<Mobile>>() {
            @Override
            public void onResponse(Call<List<Mobile>> call, Response<List<Mobile>> response) {

                List<Mobile> mobiles = response.body();
                mobileArrayList.addAll(mobiles);

                for (Mobile mobile : mobiles) {
                    mobileDataStore.saveMobile(mobile);
                }

                view.populateList(mobileArrayList);
            }

            @Override
            public void onFailure(Call<List<Mobile>> call, Throwable t) {

            }
        });
    }

    @Override
    public void makeFavorite(Mobile mobile) {

        mobileDataStore.makeFavorite(mobile);
    }

    @Override
    public void removeFromFavorite(Mobile mobile) {

        mobileDataStore.removeFromFavorite(mobile);
    }

    @Override
    public void getMobileListByPriceAscending() {

        mobileArrayList.clear();
        mobileArrayList.addAll(mobileDataStore.getAllMobilesByPriceAscending());
        view.populateList(mobileArrayList);
    }

    @Override
    public void getMobileListByPriceDescending() {

        mobileArrayList.clear();
        mobileArrayList.addAll(mobileDataStore.getAllMobilesByPriceDescending());
        view.populateList(mobileArrayList);
    }

    @Override
    public void getMobileListByRating() {

        mobileArrayList.clear();
        mobileArrayList.addAll(mobileDataStore.getAllMobilesByRating());
        view.populateList(mobileArrayList);
    }
}
