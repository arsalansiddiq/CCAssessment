package com.mobile.restaruant.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mobile.restaruant.data.APIResponseRestaurant;
import com.mobile.restaruant.data.NetworkCalls;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class RestaurantViewModel extends ViewModel {

    private NetworkCalls networkCalls = new NetworkCalls();
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<APIResponseRestaurant> responseLiveData = new MutableLiveData<>();
    public MutableLiveData<APIResponseRestaurant> getResponseLiveData() {
        return responseLiveData;
    }



    public void getRestaurants(String latlong) {
        disposables.add(networkCalls.getRestaurants(latlong)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((r) -> responseLiveData.setValue(APIResponseRestaurant.loading()))
                .subscribe(
                        result -> responseLiveData.setValue(APIResponseRestaurant.success(result)),
                        throwable -> responseLiveData.setValue(APIResponseRestaurant.error(throwable))
                ));
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }

    public String getCityName(String compoundCode) {

        return compoundCode.split(",")[1];
    }
}
