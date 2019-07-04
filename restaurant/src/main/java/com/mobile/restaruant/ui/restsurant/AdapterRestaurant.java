package com.mobile.restaruant.ui.restsurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mobile.restaruant.R;
import com.mobile.restaruant.databinding.ListItemRestaurantBinding;
import com.mobile.restaruant.data.network.model.response.restaurantresponse.Result;
import com.mobile.restaruant.viewmodels.RestaurantViewModel;


import java.util.List;

public class AdapterRestaurant extends
        RecyclerView.Adapter<AdapterRestaurant.ViewHolder>
{

    private List<Result> restaurantList;
    private RestaurantViewModel restaurantViewModel;
    private Context context;

    public AdapterRestaurant(List<Result> restaurantList, RestaurantViewModel restaurantViewModel, Context context){
        this.restaurantList = restaurantList;
        this.context = context;
        this.restaurantViewModel = restaurantViewModel;
    }

    @Override
    public AdapterRestaurant.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        ListItemRestaurantBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_item_restaurant, parent, false);

        AdapterRestaurant.ViewHolder viewHolder = new AdapterRestaurant.ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterRestaurant.ViewHolder holder, int position) {

        Result result = restaurantList.get(position);

        Glide.with(context).load(result.getIcon()).into(holder.listItemRestaurantBinding.imageViewRestaurantImage);
        holder.listItemRestaurantBinding.txtViewRestaurantName.setText(result.getName());
        holder.listItemRestaurantBinding.txtViewCity.setText(restaurantViewModel.getCityName(result.getPlusCode().getCompoundCode()));
        holder.listItemRestaurantBinding.ratingBarRestaurant.setRating(result.getRating());

    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ListItemRestaurantBinding listItemRestaurantBinding;

        public ViewHolder(ListItemRestaurantBinding flightItemLayoutBinding) {
            super(flightItemLayoutBinding.getRoot());
            listItemRestaurantBinding = flightItemLayoutBinding;
        }
    }
}