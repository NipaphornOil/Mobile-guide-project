package com.playbasis.pb.mobileguide.presentation.detail;

import com.playbasis.pb.mobileguide.data.entity.Image;
import com.playbasis.pb.mobileguide.data.remote.Api;
import com.playbasis.pb.mobileguide.data.remote.MobileRemoteDataStore;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MobileDetailPresenter implements IMobileDetail.Presenter{

    private IMobileDetail.View view;
    private MobileRemoteDataStore mobileRemoteDataStore;

    public MobileDetailPresenter(IMobileDetail.View view, MobileRemoteDataStore mobileRemoteDataStore) {
        this.view = view;
        this.mobileRemoteDataStore = mobileRemoteDataStore;
    }

    @Override
    public void callWebService(String id) {

        mobileRemoteDataStore.getImages(id, new MobileRemoteDataStore.MobileDataResult<Image>() {
            @Override
            public void onSuccess(ArrayList<Image> result) {
                view.populateList(result);
            }

            @Override
            public void onError(String error) {

            }
        });

    }
}
