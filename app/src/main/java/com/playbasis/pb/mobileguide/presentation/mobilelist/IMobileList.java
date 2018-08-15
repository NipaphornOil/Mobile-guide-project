package com.playbasis.pb.mobileguide.presentation.mobilelist;

import com.playbasis.pb.mobileguide.data.entity.Mobile;

import java.util.ArrayList;

public interface IMobileList {

    interface View {

        void populateList(ArrayList<Mobile> mobileArrayList);
    }

    interface Presenter {

        void getMobileList();

        void makeFavorite(Mobile mobile);

        void removeFromFavorite(Mobile mobile);

        void getMobileListByPriceAscending();

        void getMobileListByPriceDescending();

        void getMobileListByRating();
    }
}
