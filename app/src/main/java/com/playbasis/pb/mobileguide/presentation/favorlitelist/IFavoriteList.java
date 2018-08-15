package com.playbasis.pb.mobileguide.presentation.favorlitelist;

import com.playbasis.pb.mobileguide.data.entity.Mobile;

import java.util.ArrayList;

public interface IFavoriteList {

    interface View {

        void populateList(ArrayList<Mobile> favoriteArrayList);

    }

    interface Presenter {

        void getFavoriteMobiles();

        void getFavoriteMobileByPriceAscending();

        void getFavoriteMobileByPriceDescending();

        void getFavoriteByRating();
    }

}
