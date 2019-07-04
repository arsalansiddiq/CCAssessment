package com.example.android.weather.ui.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.weather.R;
import com.example.android.weather.databinding.ListItemWeatherBinding;
import com.example.android.weather.utils.configuration.Constants;
import com.example.android.weather.viewmodels.WeatherViewModel;

import java.util.List;


public class AdapterWeather extends
        RecyclerView.Adapter<AdapterWeather.ViewHolder>
       {

    private List<com.example.android.weather.data.network.model.response.weather.List> weatherList;
    private WeatherViewModel weatherViewModel;
    private Context context;

    public AdapterWeather(List<com.example.android.weather.data.network.model.response.weather.List> weatherList, WeatherViewModel weatherViewModel, Context context){
        this.weatherList = weatherList;
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
        com.example.android.weather.data.network.model.response.weather.List flight = weatherList.get(position);


        Integer maxTemp = (int) Math.round(flight.getMain().getTempMax());
        Integer minTemp = (int) Math.floor(flight.getMain().getTempMin());

        String url = Constants.OPEN_WEATHER_ICON_BASE_URL + flight.getWeather().get(0).getIcon() + ".png";
        Glide.with(context).load(url).into(holder.listItemWeatherBinding.imgViewWeatherImage);
        holder.listItemWeatherBinding.txtViewDayTemperature.setText(String.valueOf(maxTemp) + "/");
        holder.listItemWeatherBinding.txtViewNightTemperature.setText(String.valueOf(minTemp));
        holder.listItemWeatherBinding.txtViewDay.setText(weatherViewModel.formatDay(flight.getDt()));
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ListItemWeatherBinding listItemWeatherBinding;

        public ViewHolder(ListItemWeatherBinding flightItemLayoutBinding) {
            super(flightItemLayoutBinding.getRoot());
            listItemWeatherBinding = flightItemLayoutBinding;
        }
    }
}