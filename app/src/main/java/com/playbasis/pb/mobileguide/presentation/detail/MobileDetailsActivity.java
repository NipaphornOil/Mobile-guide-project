package com.playbasis.pb.mobileguide.presentation.detail;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.playbasis.pb.mobileguide.data.entity.Image;
import com.playbasis.pb.mobileguide.R;
import com.playbasis.pb.mobileguide.data.remote.Api;
import com.playbasis.pb.mobileguide.data.remote.ServiceFactory;
import com.playbasis.pb.mobileguide.databinding.LayoutMobileDetailsActivityBinding;

import java.util.ArrayList;

import android.support.v7.widget.LinearLayoutManager;

public class MobileDetailsActivity extends AppCompatActivity implements IMobileDetail.View {

    private ImageSlideAdapter imageAdapter;
    private ArrayList<Image> imageArrayList = new ArrayList<>();
    private LayoutMobileDetailsActivityBinding binding;
    private IMobileDetail.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding = DataBindingUtil.setContentView(this, R.layout.layout_mobile_details_activity);
        MobileDetailViewModel viewModel = new MobileDetailViewModel(getIntent().getStringExtra("name"), getIntent().getStringExtra("brand"), getIntent().getStringExtra("description"));
        binding.setViewmodel(viewModel);

        imageAdapter = new ImageSlideAdapter(imageArrayList);

        // add to recyclerView
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerView.setLayoutManager(mLayoutManager);
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerView.setAdapter((RecyclerView.Adapter) imageAdapter);

        Api api = ServiceFactory.getRetrofit().create(Api.class);
        presenter = new MobileDetailPresenter(this, api);
        String id = getIntent().getStringExtra("id");

        presenter.callWebService(id);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return (super.onOptionsItemSelected(item));
    }

    @Override
    public void populateList(ArrayList<Image> imageList) {

        imageArrayList.addAll(imageList);
        imageAdapter.notifyDataSetChanged();

    }
}
