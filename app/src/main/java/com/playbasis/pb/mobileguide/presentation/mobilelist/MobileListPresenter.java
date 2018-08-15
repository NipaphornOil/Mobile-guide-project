package com.playbasis.pb.mobileguide.presentation.mobilelist;

import com.playbasis.pb.mobileguide.data.entity.Mobile;
import com.playbasis.pb.mobileguide.data.local.MobileLocalDataStore;
import com.playbasis.pb.mobileguide.data.remote.Api;
import com.playbasis.pb.mobileguide.data.remote.MobileRemoteDataStore;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MobileListPresenter implements IMobileList.Presenter {

    private IMobileList.View view;
    private MobileLocalDataStore mobileLocalDataStore;
    private MobileRemoteDataStore mobileRemoteDataStore;
    private ArrayList<Mobile> mobileArrayList = new ArrayList<>();

    public MobileListPresenter(IMobileList.View view, MobileLocalDataStore mobileLocalDataStore, MobileRemoteDataStore mobileRemoteDataStore){
        this.view = view;
        this.mobileLocalDataStore = mobileLocalDataStore;
        this.mobileRemoteDataStore = mobileRemoteDataStore;
    }

    @Override
    public void getMobileList() {

        mobileArrayList.addAll(mobileLocalDataStore.getAllMobiles());

        if(mobileArrayList.isEmpty()){
            getMobileFromApi();
        } else{
            view.populateList(mobileArrayList);
        }
    }

    private void getMobileFromApi(){

        mobileRemoteDataStore.getMobiles(new MobileRemoteDataStore.MobileDataResult<Mobile>() {
            @Override
            public void onSuccess(ArrayList<Mobile> result) {
                saveMobileToLocalDatabase(result);
                view.populateList(result);
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private void saveMobileToLocalDatabase(ArrayList<Mobile> mobiles){
        for (Mobile mobile : mobiles) {
            mobileLocalDataStore.saveMobile(mobile);
        }
    }

    @Override
    public void makeFavorite(Mobile mobile) {
        mobileLocalDataStore.makeFavorite(mobile);
    }

    @Override
    public void removeFromFavorite(Mobile mobile) {

        mobileLocalDataStore.removeFromFavorite(mobile);
    }

    @Override
    public void getMobileListByPriceAscending() {

        mobileArrayList.clear();
        mobileArrayList.addAll(mobileLocalDataStore.getAllMobilesByPriceAscending());
        view.populateList(mobileArrayList);
    }

    @Override
    public void getMobileListByPriceDescending() {

        mobileArrayList.clear();
        mobileArrayList.addAll(mobileLocalDataStore.getAllMobilesByPriceDescending());
        view.populateList(mobileArrayList);
    }

    @Override
    public void getMobileListByRating() {

        mobileArrayList.clear();
        mobileArrayList.addAll(mobileLocalDataStore.getAllMobilesByRating());
        view.populateList(mobileArrayList);
    }
}
