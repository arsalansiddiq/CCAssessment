package com.mobile.restaruant.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;

import androidx.lifecycle.LiveData;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import io.reactivex.annotations.NonNull;

public class LocationListener extends LiveData<Location> {

    private static LocationListener instance;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest mLocationRequest;

    public static LocationListener getInstance(Context appContext) {
        if (instance == null) {
            instance = new LocationListener(appContext);
        }
        return instance;
    }

    @SuppressLint("MissingPermission")
    private LocationListener(final Context appContext) {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(appContext);
        createLocationRequest();
            mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    if (task.isSuccessful() && task.getResult() != null) {
                        setValue(task.getResult());
                    } else {
                            mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, null);

                    }
                }
            });


    }

    private void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            for (Location location : locationResult.getLocations()) {
                if (location != null)
                    setValue(location);
            }
        }
    };

    @Override
    protected void onInactive() {
        super.onInactive();
        if (mLocationCallback != null)
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
    }
}
