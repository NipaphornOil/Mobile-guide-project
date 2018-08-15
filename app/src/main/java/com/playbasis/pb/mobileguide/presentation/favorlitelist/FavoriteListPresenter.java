package com.playbasis.pb.mobileguide.presentation.favorlitelist;

import com.playbasis.pb.mobileguide.data.entity.Mobile;
import com.playbasis.pb.mobileguide.data.local.MobileLocalDataStore;


import java.util.ArrayList;

public class FavoriteListPresenter implements IFavoriteList.Presenter {

    private IFavoriteList.View view;
    private MobileLocalDataStore mobileLocalDataStore;
    private ArrayList<Mobile> favoriteArrayList = new ArrayList<>();


    public FavoriteListPresenter(IFavoriteList.View view, MobileLocalDataStore mobileLocalDataStore) {
        this.view = view;
        this.mobileLocalDataStore = mobileLocalDataStore;


    }

    @Override
    public void getFavoriteMobiles() {

        favoriteArrayList.clear();
        favoriteArrayList.addAll(mobileLocalDataStore.getFavoriteMobile());

        view.populateList(favoriteArrayList);

    }


    @Override
    public void getFavoriteMobileByPriceAscending() {

        favoriteArrayList.clear();
        favoriteArrayList.addAll(mobileLocalDataStore.getFavoriteMobileByPriceAscending());

        view.populateList(favoriteArrayList);

    }

    @Override
    public void getFavoriteMobileByPriceDescending() {

        favoriteArrayList.clear();
        favoriteArrayList.addAll(mobileLocalDataStore.getFavoriteMobileByPriceDescending());

        view.populateList(favoriteArrayList);

    }

    @Override
    public void getFavoriteByRating() {

        favoriteArrayList.clear();
        favoriteArrayList.addAll(mobileLocalDataStore.getFavoriteByRating());

        view.populateList(favoriteArrayList);

    }
}
