package com.mobile.restaruant.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mobile.restaruant.data.APIResponseRestaurant;
import com.mobile.restaruant.data.APIResponseWeather;
import com.mobile.restaruant.data.NetworkCalls;
import com.mobile.restaruant.data.network.model.response.weather.List;
import com.mobile.restaruant.data.network.model.response.weather.Main;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class WeatherViewModel extends ViewModel {
    private NetworkCalls networkCalls = new NetworkCalls();
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<APIResponseRestaurant> responseLiveData = new MutableLiveData<>();
    private final MutableLiveData<APIResponseWeather> responseLiveDataWeather = new MutableLiveData<>();
    DecimalFormat numberFormat = new DecimalFormat("#");

    public MutableLiveData<APIResponseRestaurant> getResponseLiveData() {
        return responseLiveData;
    }

    public MutableLiveData<APIResponseWeather> getWeather() {
        return responseLiveDataWeather;
    }

//    public void getRestaurants(String latlng) {
//        disposables.add(networkCalls.getRestaurants(latlng)
//        .subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())
//        .doOnSubscribe((r) -> responseLiveData.setValue(APIResponseRestaurant.loading()))
//        .subscribe(
//                result -> responseLiveData.setValue(APIResponseRestaurant.success(result)),
//                        throwable -> responseLiveData.setValue(APIResponseRestaurant.error(throwable))
//                ));
//    }


    public void getWeatherForecast(double lat ,double lon) {
        disposables.add(networkCalls.getForecast(lat,lon)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((r) -> responseLiveDataWeather.setValue(APIResponseWeather.loading()))
                .subscribe(
                        result -> responseLiveDataWeather.setValue(APIResponseWeather.success(result)),
                        throwable -> responseLiveDataWeather.setValue(APIResponseWeather.error(throwable))
                ));
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }

    public java.util.List<List> formatList(java.util.List<List> list) {

        java.util.List<List> listsFormated = new ArrayList<>();


        for(int i = 0 ; i<list.size(); i=i+8){
            com.mobile.restaruant.data.network.model.response.weather.List list1 = new com.mobile.restaruant.data.network.model.response.weather.List();
            Main main = new Main();
            list1.setClouds(list.get(i).getClouds());
            list1.setDt(list.get(i).getDt());
            list1.setDtTxt(list.get(i).getDtTxt());
            list1.setSys(list.get(i).getSys());
            list1.setWind(list.get(i).getWind());
            list1.setWeather(list.get(i).getWeather());
            main.setGrndLevel(list.get(i).getMain().getGrndLevel());
            main.setHumidity(list.get(i).getMain().getHumidity());
            main.setPressure(list.get(i).getMain().getPressure());
            main.setSeaLevel(list.get(i).getMain().getSeaLevel());
            main.setTemp(list.get(i).getMain().getTemp() - 273.15);
            main.setTempKf(list.get(i).getMain().getTempKf() - 273.15);
            main.setTempMin(list.get(i).getMain().getTempMin() - 273.15);
            main.setTempMax(list.get(i).getMain().getTempMax() - 273.15);
            list1.setMain(main);

            listsFormated.add(list1);
        }

        return listsFormated;
    }

    public String formatDay(Long dt) {
        java.util.Date time=new java.util.Date((long)dt*1000);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, MMM d, ''yy");
        String newstring = simpleDateFormat.format(time);
        return newstring.substring(0,3);
    }
}
