package com.playbasis.pb.mobilephonescb.presentation.detail;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.playbasis.pb.mobilephonescb.data.entity.Image;
import com.playbasis.pb.mobilephonescb.R;
import com.playbasis.pb.mobilephonescb.data.remote.Api;
import com.playbasis.pb.mobilephonescb.data.remote.ServiceFactory;
import com.playbasis.pb.mobilephonescb.databinding.LayoutMobileDetailsActivityBinding;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.support.v7.widget.LinearLayoutManager;

public class MobileDetailsActivity extends AppCompatActivity {

    private ImageSlideAdapter ImageAdapter;
    private ArrayList<Image> imageArrayList = new ArrayList<>();
    private LayoutMobileDetailsActivityBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding = DataBindingUtil.setContentView(this, R.layout.layout_mobile_details_activity);
        MobileDetailViewModel viewModel = new MobileDetailViewModel(getIntent().getStringExtra("name"), getIntent().getStringExtra("brand"), getIntent().getStringExtra("description"));
        binding.setViewmodel(viewModel);

        ImageAdapter = new ImageSlideAdapter(imageArrayList);

        // add to recyclerView
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerView.setLayoutManager(mLayoutManager);
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerView.setAdapter((RecyclerView.Adapter) ImageAdapter);

        callWebService();

    }

    private void callWebService() {

        String id = getIntent().getStringExtra("id");
        Api api = ServiceFactory.getRetrofit().create(Api.class);

        Call<List<Image>> call = api.getImageSlide(id);
        call.enqueue(new Callback<List<Image>>() {
            @Override
            public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {

                List<Image> images = response.body();
                imageArrayList.addAll(images);
                ImageAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Image>> call, Throwable t) {

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return (super.onOptionsItemSelected(item));
    }

}
