package com.playbasis.pb.mobileguide.presentation.mobilelist;

import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.playbasis.pb.mobileguide.data.entity.Mobile;
import com.playbasis.pb.mobileguide.R;
import com.playbasis.pb.mobileguide.presentation.favorlitelist.FavoriteAdapter;

import java.util.List;

public class MobileAdapter extends RecyclerView.Adapter<MobileAdapter.MyViewHolder> {

    private List<Mobile> mobiles;
    private ClickListener clickListener;

    interface ClickListener {
        void onClick(int position);

        void onMakeFavorite(int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView price, brand, name, description, rating;

        ImageButton buttonFavorite;

        public ImageView thumbImageURL;

        RelativeLayout buttonItem;

        public MyViewHolder(View view) {

            super(view);

            buttonFavorite = (ImageButton) view.findViewById(R.id.button_favorite);
            price = (TextView) view.findViewById(R.id.price1);
            brand = (TextView) view.findViewById(R.id.brand1);
            name = (TextView) view.findViewById(R.id.name1);
            thumbImageURL = (ImageView) view.findViewById(R.id.thumbImageURL1);
            description = (TextView) view.findViewById(R.id.description1);
            rating = (TextView) view.findViewById(R.id.rating1);
            buttonItem = view.findViewById(R.id.button_item);

        }

    }


    public MobileAdapter(List<Mobile> moviesList, MobileAdapter.ClickListener clickListener) {
        this.mobiles = moviesList;
        this.clickListener = clickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_mobilelist_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final Mobile mobile = mobiles.get(position);

        holder.price.setText(mobile.getPrice());
        holder.brand.setText(mobile.getBrand());
        holder.name.setText(mobile.getName());
        holder.description.setText(mobile.getDescription());
        holder.rating.setText(mobile.getRating() + " ");

        if (mobile.isFavorite()) {
            holder.buttonFavorite.setBackgroundResource(R.drawable.heart);
        }
        else {
            holder.buttonFavorite.setBackgroundResource(R.drawable.favorheart);
        }

        // set image
        Glide.with(holder.thumbImageURL.getContext())
                .load(mobile.getThumbImageURL())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.wait)
                        .error(R.drawable.user)
                        .fitCenter()
                )
                .into(holder.thumbImageURL);

        holder.buttonItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(position);
            }
        });

        holder.buttonFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onMakeFavorite(position);

                if (mobiles.get(position).isFavorite()) {
                    holder.buttonFavorite.setBackgroundResource(R.drawable.heart);
                }
                else {
                    holder.buttonFavorite.setBackgroundResource(R.drawable.favorheart);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mobiles.size();
    }
}
