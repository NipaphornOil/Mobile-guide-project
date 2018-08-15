package com.playbasis.pb.mobileguide.presentation.detail;

import com.playbasis.pb.mobileguide.data.entity.Image;
import com.playbasis.pb.mobileguide.data.entity.Mobile;

import java.util.ArrayList;

public interface IMobileDetail {

    interface View{

        void populateList(ArrayList<Image> imageArrayList);

    }

    interface Presenter{

        void callWebService(String id);
    }
}
