package com.playbasis.pb.mobilephonescb.presentation.detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.playbasis.pb.mobilephonescb.data.entity.Image;
import com.playbasis.pb.mobilephonescb.R;

import java.util.ArrayList;
import java.util.List;

public class ImageSlideAdapter extends RecyclerView.Adapter<ImageSlideAdapter.MyViewHolder> {

    private List<Image> images;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView url;

        public MyViewHolder(View view) {

            super(view);
            url = (ImageView) view.findViewById(R.id.imageView);

        }

    }

    public ImageSlideAdapter(ArrayList<Image> imageArrayList) {
        this.images = imageArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_imageslide_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        Image image = images.get(position);

        // set image
        Glide.with(holder.url.getContext())
                .load(image.getUrl())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.wait)
                        .error(R.drawable.user)
                        .fitCenter()
                )
                .into(holder.url);

    }

    @Override
    public int getItemCount() {
        return images.size();
    }
}
