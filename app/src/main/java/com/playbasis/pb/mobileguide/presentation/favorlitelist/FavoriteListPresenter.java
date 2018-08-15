package com.playbasis.pb.mobileguide.presentation.favorlitelist;

import com.playbasis.pb.mobileguide.data.entity.Mobile;
import com.playbasis.pb.mobileguide.data.local.MobileDataStore;
import com.playbasis.pb.mobileguide.data.remote.Api;
import com.playbasis.pb.mobileguide.presentation.mobilelist.MobileListPresenter;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteListPresenter implements IFavoriteList.Presenter {

    private IFavoriteList.View view;
    private MobileDataStore mobileDataStore;
    private ArrayList<Mobile> favoriteArrayList = new ArrayList<>();


    public FavoriteListPresenter(IFavoriteList.View view, MobileDataStore mobileDataStore) {
        this.view = view;
        this.mobileDataStore = mobileDataStore;


    }

    @Override
    public void getFavoriteMobiles() {

        favoriteArrayList.clear();
        favoriteArrayList.addAll(mobileDataStore.getFavoriteMobile());

        view.populateList(favoriteArrayList);

    }


    @Override
    public void getFavoriteMobileByPriceAscending() {

        favoriteArrayList.clear();
        favoriteArrayList.addAll(mobileDataStore.getFavoriteMobileByPriceAscending());

        view.populateList(favoriteArrayList);

    }

    @Override
    public void getFavoriteMobileByPriceDescending() {

        favoriteArrayList.clear();
        favoriteArrayList.addAll(mobileDataStore.getFavoriteMobileByPriceDescending());

        view.populateList(favoriteArrayList);

    }

    @Override
    public void getFavoriteByRating() {

        favoriteArrayList.clear();
        favoriteArrayList.addAll(mobileDataStore.getFavoriteByRating());

        view.populateList(favoriteArrayList);

    }
}
