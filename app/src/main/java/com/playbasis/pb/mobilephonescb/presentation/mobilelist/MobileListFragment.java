package com.playbasis.pb.mobilephonescb.presentation.mobilelist;


import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.playbasis.pb.mobilephonescb.data.entity.Mobile;
import com.playbasis.pb.mobilephonescb.data.local.MobileDataStore;
import com.playbasis.pb.mobilephonescb.data.remote.ServiceFactory;
import com.playbasis.pb.mobilephonescb.databinding.LayoutMobilelistFragmentBinding;
import com.playbasis.pb.mobilephonescb.presentation.detail.MobileDetailsActivity;
import com.playbasis.pb.mobilephonescb.R;
import com.playbasis.pb.mobilephonescb.data.remote.Api;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MobileListFragment extends Fragment {

    private ArrayList<Mobile> mobilesList = new ArrayList<>();
    private MobileAdapter mobileAdapter;
    private LayoutMobilelistFragmentBinding binding;
    private MobileDataStore mobileDataStore;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.layout_mobilelist_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mobileDataStore = new MobileDataStore(getActivity());

        mobileAdapter = new MobileAdapter(mobilesList, new MobileAdapter.ClickListener() {
            @Override
            public void onClick(int position) {
                goToDetail(position);
            }

            @Override
            public void onMakeFavorite(int position) {
                onClickHeart(position);
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        binding.recycleview.setLayoutManager(mLayoutManager);
        binding.recycleview.setItemAnimator(new DefaultItemAnimator());
        binding.recycleview.setAdapter((RecyclerView.Adapter) mobileAdapter);

    }

    private void onClickHeart(int position) {

        Mobile mobile = mobilesList.get(position);

        if (mobile.isFavorite()) {
            mobileDataStore.removeFromFavorite(mobilesList.get(position));
        } else {
            mobileDataStore.makeFavorite(mobilesList.get(position));
        }


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        prepareMobileData();
    }

    private void prepareMobileData() {

        mobilesList.addAll(mobileDataStore.getAllMobiles());

        if (mobilesList.isEmpty()) {
            loadMobileFromApi();
        }
    }

    private void loadMobileFromApi() {

        Api api = ServiceFactory.getRetrofit().create(Api.class);

        Call<List<Mobile>> call = api.getMobiles();

        call.enqueue(new Callback<List<Mobile>>() {
            @Override
            public void onResponse(Call<List<Mobile>> call, Response<List<Mobile>> response) {

                List<Mobile> mobiles = response.body();
                mobilesList.addAll(mobiles);
                mobileAdapter.notifyDataSetChanged();

                for (Mobile mobile : mobiles) {
                    mobileDataStore.saveMobile(mobile);
                }
            }

            @Override
            public void onFailure(Call<List<Mobile>> call, Throwable t) {

            }
        });
    }

    private void goToDetail(int position) {

        Mobile mobile = mobilesList.get(position);

        Intent intent = new Intent(getActivity(), MobileDetailsActivity.class);
        intent.putExtra("id", mobile.getId());
        intent.putExtra("name", mobile.getName());
        intent.putExtra("brand", mobile.getBrand());
        intent.putExtra("description", mobile.getDescription());
        getActivity().startActivity(intent);
    }

    public void sortByPriceAscending() {

        // set sorted list
        mobilesList.clear();
        mobilesList.addAll(mobileDataStore.getAllMobilesByPriceAscending());
        mobileAdapter.notifyDataSetChanged();
    }

    public void sortByPriceDescending() {

        // set sorted list
        mobilesList.clear();
        mobilesList.addAll(mobileDataStore.getAllMobilesByPriceDescending());
        mobileAdapter.notifyDataSetChanged();
    }

    public void sortMobileByRating() {

        //set sorted list
        mobilesList.clear();
        mobilesList.addAll(mobileDataStore.getAllMobilesByRating());
        mobileAdapter.notifyDataSetChanged();

    }


}

