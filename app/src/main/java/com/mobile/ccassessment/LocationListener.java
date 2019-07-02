//package com.mobile.ccassessment;
//
//import android.Manifest;
//import android.app.Activity;
//import android.content.Context;
//import android.content.pm.PackageManager;
//import android.location.Location;
//import android.support.annotation.NonNull;
//import android.support.v4.app.ActivityCompat;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.google.android.gms.common.api.ApiException;
//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationCallback;
//import com.google.android.gms.location.LocationRequest;
//import com.google.android.gms.location.LocationResult;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.location.LocationSettingsRequest;
//import com.google.android.gms.location.LocationSettingsResponse;
//import com.google.android.gms.location.SettingsClient;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//
//import static com.google.android.gms.common.api.CommonStatusCodes.RESOLUTION_REQUIRED;
//
//public class LocationListener {
//
//
//    //Location Api
//    private FusedLocationProviderClient fusedLocationProviderClient;
//    private LocationRequest locationRequest;
////    private LocationCallback locationCallback;
//    private Context context;
//
//    public LocationListener(FusedLocationProviderClient fusedLocationProviderClient, LocationRequest locationRequest, Context context) {
//        this.fusedLocationProviderClient = fusedLocationProviderClient;
//        this.locationRequest = locationRequest;
////        this.locationCallback = locationCallback;
//        this.context = context;
//    }
//
//    public void getUserLocation() {
//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
//        locationRequest = new LocationRequest();
////        locationRequest.setInterval(1000);
////        locationRequest.setFastestInterval(500);
//        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//
//        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
//                .addLocationRequest(locationRequest);
//
//        SettingsClient settingsClient = LocationServices.getSettingsClient(context);
//        com.google.android.gms.tasks.Task<LocationSettingsResponse> task = settingsClient
//                .checkLocationSettings(builder.build());
//
//        task.addOnSuccessListener((Activity) context, new OnSuccessListener<LocationSettingsResponse>() {
//            @Override
//            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
//                Log.i("LocationClient", "SettingsSatisfied!");
//                startLocationUpdates();
//            }
//        });
//
//        task.addOnFailureListener((Activity) context, new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                ApiException apiException = (ApiException) e;
//
//                if (apiException.getStatusCode() == RESOLUTION_REQUIRED) {
//                    Log.i("exception on Failure", e.getMessage().toString());
////                    startLocationUpdates(isSUP);
//                }
////                Toast.makeText(NavigationDrawerActivity.this, "Please check your Location settings!", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void startLocationUpdates() {
//        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            Toast.makeText(context, "Please check Lcoation Permission", Toast.LENGTH_SHORT).show();
//        } else {
//
//        }
//    }
//
//    public void onLocationUpdateCallback(LocationCallback locationCallback) {
//
//        fusedLocationProviderClient.requestLocationUpdates(locationRequest, this.locationRequest, null);
//
//        locationCallback = new LocationCallback(){
//            @Override
//            public void onLocationResult(LocationResult locationResult) {
//                if (locationResult == null) {
//                    Log.i("LocationCallBack", "Failed!");
//                    Toast.makeText(NavigationDrawerActivity.this, "Failed to get Lcoation", Toast.LENGTH_SHORT).show();
//                } else {
////                    for (Location location : locationResult.getLastLocation())) {
//
//                    Location location = locationResult.getLastLocation();
//                    latitude = 0;
//                    longitude = 0;
//                    latitude = location.getLatitude();
//                    longitude = location.getLongitude();
//
//                    Log.i("LocCoor", latitude + " " + longitude);
//
//                    fusedLocationProviderClient.removeLocationUpdates(this);
//
//                    hideProgress();
//                    if (isSUP == null) {
//                        meetingMerhcant();
//                    } else if (isSUP) {
//                        attendanceSupervisor();
//                    } else {
//                        dispatchTakePictureIntent();
//                    }
//
//                }
//            }
//        };
//    }
//}
