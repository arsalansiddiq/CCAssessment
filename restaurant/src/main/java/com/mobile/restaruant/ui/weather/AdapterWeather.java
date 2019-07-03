package com.mobile.restaruant.ui.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mobile.restaruant.R;
import com.mobile.restaruant.databinding.ListItemWeatherBinding;
import com.mobile.restaruant.utils.configuration.Constants;
import com.mobile.restaruant.viewmodels.WeatherViewModel;

import java.util.List;


public class AdapterWeather extends
        RecyclerView.Adapter<AdapterWeather.ViewHolder>
       {

    private List<com.mobile.restaruant.data.network.model.response.weather.List> flightsList;
    private WeatherViewModel weatherViewModel;
    private Context context;

    public AdapterWeather(List<com.mobile.restaruant.data.network.model.response.weather.List> flsLst, WeatherViewModel weatherViewModel, Context context){
        flightsList = flsLst;
        this.context = context;
        this.weatherViewModel = weatherViewModel;
    }

    @Override
    public AdapterWeather.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        ListItemWeatherBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_item_weather, parent, false);

        ViewHolder viewHolder = new ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        com.mobile.restaruant.data.network.model.response.weather.List flight = flightsList.get(position);


        Integer maxTemp = (int) Math.round(flight.getMain().getTempMax());
        Integer minTemp = (int) Math.floor(flight.getMain().getTempMin());
//        holder.flightItemBinding.imgViewWeatherImage.setImageDrawable(context.getResources().getDrawable(R.drawable.cc));
        String url = Constants.OPEN_WEATHER_ICON_BASE_URL + flight.getWeather().get(0).getIcon() + ".png";
        Glide.with(context).load(url).into(holder.flightItemBinding.imgViewWeatherImage);
        holder.flightItemBinding.txtViewDayTemperature.setText(String.valueOf(maxTemp) + "/");
        holder.flightItemBinding.txtViewNightTemperature.setText(String.valueOf(minTemp));
        holder.flightItemBinding.txtViewDay.setText(weatherViewModel.formatDay(flight.getDt()));
    }

    @Override
    public int getItemCount() {
        return flightsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ListItemWeatherBinding flightItemBinding;

        public ViewHolder(ListItemWeatherBinding flightItemLayoutBinding) {
            super(flightItemLayoutBinding.getRoot());
            flightItemBinding = flightItemLayoutBinding;
        }
    }
}