package com.mobile.restaruant.ui.restsurant;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mobile.restaruant.R;
import com.mobile.restaruant.databinding.ActivityRestaurantBinding;
import com.mobile.restaruant.utils.LocationListener;
import com.mobile.restaruant.data.APIResponseRestaurant;
import com.mobile.restaruant.data.network.model.response.restaurantresponse.RestaurantResponse;
import com.mobile.restaruant.viewmodels.RestaurantViewModel;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class RestaurantActivity extends AppCompatActivity {

    ActivityRestaurantBinding activityRestaurantBinding;
    RestaurantViewModel restaurantViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            getLocationUpdates();
        }
        init();

        restaurantViewModel.getResponseLiveData().observe(this, new Observer<APIResponseRestaurant>() {
            @Override
            public void onChanged(@Nullable APIResponseRestaurant apiResponseRestaurant) {
                consumeResponseRestaurant(apiResponseRestaurant);
            }
        });
    }

    private void init() {
        activityRestaurantBinding = DataBindingUtil.setContentView(this, R.layout.activity_restaurant);
        restaurantViewModel = ViewModelProviders.of(this).get(RestaurantViewModel.class);
        activityRestaurantBinding.setRestaurantViewModel(restaurantViewModel);
        activityRestaurantBinding.setLifecycleOwner(this);

    }

    private void getLocationUpdates() {
        LocationListener.getInstance(this.getApplicationContext()).observe((LifecycleOwner) RestaurantActivity.this, new Observer<Location>() {
            @Override
            public void onChanged(@androidx.annotation.Nullable Location location) {
                if (location != null) {
                    Log.i("LocationCoor ", location.getLatitude() +" " + location.getLongitude());
                    LocationListener.getInstance(RestaurantActivity.this.getApplicationContext()).removeObserver(this);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(location.getLatitude()).append(",").append(location.getLongitude());
                        restaurantViewModel.getRestaurants(stringBuilder.toString());

                } else
                    Log.i("LocationCoor ", "null");
            }
        });
    }


    private void consumeResponseRestaurant(APIResponseRestaurant apiResponseRestaurant) {

        switch (apiResponseRestaurant.status) {

            case LOADING:
                Toast.makeText(this, "loading...", Toast.LENGTH_SHORT).show();
                break;

            case SUCCESS:
                renderSuccessResponse(apiResponseRestaurant.data);
                break;

            case ERROR:
                Toast.makeText(this,"something went wrong", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }

    /*
     * method to handle success response
     * */
    private void renderSuccessResponse(RestaurantResponse response) {
        if (response != null) {
            Log.d("response=", response.toString());
            activityRestaurantBinding.recyclerViewRestaurant.setLayoutManager(new LinearLayoutManager(this));
            AdapterRestaurant adapter =
                    new AdapterRestaurant(response.getResults(), restaurantViewModel,this);
            activityRestaurantBinding.recyclerViewRestaurant.setAdapter(adapter);
        } else {
            Toast.makeText(this,getResources().getString(R.string.errorString), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "granted!", Toast.LENGTH_SHORT).show();
            getLocationUpdates();
        } else {
            Toast.makeText(this, "not granted!", Toast.LENGTH_SHORT).show();
        }
    }
}
