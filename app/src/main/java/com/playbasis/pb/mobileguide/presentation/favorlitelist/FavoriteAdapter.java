package com.playbasis.pb.mobileguide.presentation.favorlitelist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.playbasis.pb.mobileguide.R;
import com.playbasis.pb.mobileguide.data.entity.Mobile;


import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyViewHolder> {

    private List<Mobile> favorites;
    private FavoriteAdapter.ClickListener clickListener;

    interface ClickListener {
        void onClick(int position);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView price, name, rating;
        ImageView thumbImageURL;
        RelativeLayout buttonItem;

        public MyViewHolder(View view) {

            super(view);

            price = view.findViewById(R.id.priceFov);
            name = view.findViewById(R.id.nameFov);
            rating = view.findViewById(R.id.ratingFov);
            thumbImageURL = view.findViewById(R.id.thumbImageURLFov);

        }

    }

    public FavoriteAdapter(List<Mobile> favoriteList, FavoriteAdapter.ClickListener clickListener) {
        this.favorites = favoriteList;
        this.clickListener = clickListener;
    }

    @Override
    public FavoriteAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_favoritelist_item, parent, false);

        return new FavoriteAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        Mobile favorite = favorites.get(position);

        holder.price.setText(favorite.getPrice());
        holder.name.setText(favorite.getName());
        holder.rating.setText(favorite.getRating() + "");

        // set image
        Glide.with(holder.thumbImageURL.getContext())
                .load(favorite.getThumbImageURL())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.wait)
                        .error(R.drawable.user)
                        .fitCenter()
                )
                .into(holder.thumbImageURL);

        holder.thumbImageURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }
}
