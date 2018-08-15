package com.playbasis.pb.mobileguide.presentation.favorlitelist;


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
import android.widget.Toast;

import com.playbasis.pb.mobileguide.R;
import com.playbasis.pb.mobileguide.data.entity.Mobile;
import com.playbasis.pb.mobileguide.data.local.MobileDataStore;
import com.playbasis.pb.mobileguide.data.remote.Api;
import com.playbasis.pb.mobileguide.data.remote.ServiceFactory;
import com.playbasis.pb.mobileguide.databinding.LayoutFavoritelistFragmentBinding;
import com.playbasis.pb.mobileguide.presentation.detail.MobileDetailsActivity;
import com.playbasis.pb.mobileguide.presentation.mobilelist.MobileListPresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteListFragment extends Fragment implements IFavoriteList.View {

    private ArrayList<Mobile> favoriteArrayList = new ArrayList<>();
    private FavoriteAdapter favoriteAdapter;
    private LayoutFavoritelistFragmentBinding binding;
    private IFavoriteList.Presenter presenter;
    private SORT_BY sortBy = SORT_BY.DEFAULT;

    private enum SORT_BY {
        PRICE_ASCENDING,
        PRICE_DESCENDING,
        RATING_DESCENDING,
        DEFAULT
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.layout_favoritelist_fragment, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new FavoriteListPresenter(this, new MobileDataStore(getActivity()));

        favoriteAdapter = new FavoriteAdapter(favoriteArrayList, new FavoriteAdapter.ClickListener() {
            @Override
            public void onClick(int position) {

                goToDetail(position);

            }
        });

        //add to RecyclerView
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        binding.recycleView.setLayoutManager(mLayoutManager);
        binding.recycleView.setItemAnimator(new DefaultItemAnimator());
        binding.recycleView.setAdapter((RecyclerView.Adapter) favoriteAdapter);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            getFavoriteMobiles();
        }
    }

    @Override
    public void populateList(ArrayList<Mobile> favoriteList) {

        favoriteArrayList.clear();
        favoriteArrayList.addAll(favoriteList);
        favoriteAdapter.notifyDataSetChanged();

    }

    private void getFavoriteMobiles() {

        switch (sortBy) {

            case DEFAULT:
                presenter.getFavoriteMobiles();

                break;

            case PRICE_ASCENDING:
                presenter.getFavoriteMobileByPriceAscending();

                break;

            case PRICE_DESCENDING:
                presenter.getFavoriteMobileByPriceDescending();

                break;

            case RATING_DESCENDING:
                presenter.getFavoriteByRating();

                break;

        }
        favoriteAdapter.notifyDataSetChanged();
    }

    private void goToDetail(int position) {

        Mobile favorite = favoriteArrayList.get(position);

        Intent intent = new Intent(getActivity(), MobileDetailsActivity.class);
        intent.putExtra("id", favorite.getId());
        intent.putExtra("name", favorite.getName());
        intent.putExtra("description", favorite.getDescription());
        intent.putExtra("brand", favorite.getBrand());
        getActivity().startActivity(intent);

    }

    public void sortByPriceAscending() {

        sortBy = SORT_BY.PRICE_ASCENDING;

    }

    public void sortByPriceDescending() {

        sortBy = SORT_BY.PRICE_DESCENDING;

    }

    public void sortFavoriteByRating() {

        sortBy = SORT_BY.RATING_DESCENDING;

    }

}
