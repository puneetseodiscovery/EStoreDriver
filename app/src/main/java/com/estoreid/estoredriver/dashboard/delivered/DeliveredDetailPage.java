package com.estoreid.estoredriver.dashboard.delivered;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.estoreid.estoredriver.R;
import com.estoreid.estoredriver.baseactivity.BaseActivity;
import com.estoreid.estoredriver.dashboard.MainActivity;
import com.estoreid.estoredriver.dashboard.accept.Accept;
import com.estoreid.estoredriver.dashboard.otp.OTP_Screen;
import com.estoreid.estoredriver.utils.Utils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class DeliveredDetailPage extends BaseActivity implements OnMapReadyCallback {

    MapView mapView;
    ImageButton deliver_backbt;
    String lat = "20.5937";
    String lng = "78.9629";
    GoogleMap Gmap;
    Button deliver_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivereddetailpage);

        init();
        listeners();
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        MapsInitializer.initialize(this);
    }

    private void listeners() {
        deliver_backbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeliveredDetailPage.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        deliver_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeliveredDetailPage.this, OTP_Screen.class);
                startActivity(intent);
            }
        });
    }

    private void init() {

        deliver_backbt = findViewById(R.id.deliver_backbt);
        mapView = findViewById(R.id.delivermapview);
        deliver_bt = findViewById(R.id.deliver_bt);
    }

    @Override
    protected void onResume() {
        super.onResume();
        statusCheck();
    }

    public void statusCheck() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        } else {
            CurrentLocation();
        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.dismiss();
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }


    private void CurrentLocation() {
        final FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        } else {
            Task<Location> locationTask = client.getLastLocation();
            if (locationTask != null) {
                locationTask.addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {

                        task.addOnSuccessListener(new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                // location(String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()));

                                if (location == null) {
                                    statusCheck();
                                    Toast.makeText(DeliveredDetailPage.this, "Unable to get Location", Toast.LENGTH_SHORT).show();
                                } else {
                                    lat = String.valueOf(location.getLatitude());
                                    lng = String.valueOf(location.getLongitude());
                                    //controller.setVendorList("Bearer " + getStringVal(Constants.TOKEN), lat, lng, "");
                                }

                                Log.d("Location", "" + lat + "" + lng);
                                Geocoder geocoder = new Geocoder(DeliveredDetailPage.this, Locale.getDefault());
                                try {
                                    List<Address> address = (List<Address>) geocoder.getFromLocation(Double.parseDouble(lat), Double.parseDouble(lng), 1);

                                    if (address != null) {
                                        //citystr = address.get(0).getAdminArea();
                                        //cityname.setText(address.get(0).getLocality());
                                    } else {
                                        //cityname.setText("Address not found");
                                    }

                                    if (mapView != null) {
                                        mapView.onCreate(null);
                                        mapView.onResume();
                                        mapView.getMapAsync(DeliveredDetailPage.this);
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                        task.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(DeliveredDetailPage.this, "Location Not Found...,Enter Location Manually...", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Gmap = googleMap;
        Gmap.getUiSettings().setMyLocationButtonEnabled(false);
        Gmap.getUiSettings().setZoomControlsEnabled(false);
        Gmap.getUiSettings().setCompassEnabled(false);
        Gmap.setMyLocationEnabled(true);

        CameraPosition cameraPosition = CameraPosition.builder().target(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng))).zoom(15).bearing(0).tilt(40).build();
        Gmap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
    }
}
