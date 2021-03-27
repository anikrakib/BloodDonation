package com.anikrakib.blooddonation.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.anikrakib.blooddonation.R;
import com.anikrakib.blooddonation.Utills.JsonParser;
import com.anikrakib.blooddonation.databinding.ActivityMapBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MapActivity extends AppCompatActivity {
    ActivityMapBinding activity_map;
    SupportMapFragment supportMapFragment;
    static GoogleMap map;
    FusedLocationProviderClient fusedLocationProviderClient;
    double currentLat = 0, currentLong = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity_map = ActivityMapBinding.inflate(getLayoutInflater());
        setContentView(activity_map.getRoot());

        activity_map.back.setOnClickListener(v -> {
            finish();
        });

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentByTag("googleMap");

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getLocation();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getLocation();
        }
    }

    public void getLocation(){
        @SuppressLint("MissingPermission") Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLat = location.getLatitude();
                    currentLong = location.getLongitude();

                    String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json" + "?location=" + currentLat + "," + currentLong
                            + "&radius=5000" + "&type=" + "hospital" + "&sensor=true" + "&key=" + getResources().getString(R.string.google_maps_api_key);

                    //new PlaceTask().execute(url);

                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            map = googleMap;
                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(currentLat, currentLong), 20
                            ));
                            //drawCircle(new LatLng(currentLat, currentLong));
                            //map.setMapStyle(MapStyleOptions.loadRawResourceStyle(getApplicationContext(), R.raw.map_style));
                            map.addMarker(new MarkerOptions().position(new LatLng(currentLat,currentLong)).icon(BitmapFromVector(getApplicationContext(), R.drawable.custom_location_marker)));
//                            map.setMapStyle(
//                                    MapStyleOptions.loadRawResourceStyle(
//                                            getApplicationContext(), R.raw.map_style));

                            //                                MarkerOptions options = new MarkerOptions();
//
//                                options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
//                                map.addMarker(options);
                            // map.addMarker(new MarkerOptions().icon(BitmapFromVector(getApplicationContext(), R.drawable.switch_thumb)));

                        }
                    });
                }
            }
        });
    }

    private static BitmapDescriptor BitmapFromVector(Context context, int vectorResId) {
        // below line is use to generate a drawable.
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);

        // below line is use to set bounds to our vector drawable.
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());

        // below line is use to create a bitmap for our
        // drawable which we have added.
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        // below line is use to add bitmap in our canvas.
        Canvas canvas = new Canvas(bitmap);

        // below line is use to draw our
        // vector drawable in canvas.
        vectorDrawable.draw(canvas);

        // after generating our bitmap we are returning our bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap);

    }

    private class PlaceTask extends AsyncTask<String,Integer,String> {
        @Override
        protected String doInBackground(String... strings) {
            String data = null;
            try {
                data = downloadUrl(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            new ParserTask().execute(s);
        }

        private String downloadUrl(String string) throws IOException {
            URL url = new URL(string);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();
            String line = "";
            while ((line = reader.readLine())!= null){
                builder.append(line);
            }
            String data = builder.toString();
            reader.close();
            return data;

        }

        private class ParserTask extends AsyncTask<String,Integer, List<HashMap<String,String>>>{
            @Override
            protected List<HashMap<String, String>> doInBackground(String... strings) {
                JsonParser jsonParser = new JsonParser();

                List<HashMap<String,String>> mapList = null;
                JSONObject object = null;
                try {
                    object = new JSONObject(strings[0]);
                    mapList = jsonParser.parseResult(object);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return mapList;
            }

            @Override
            protected void onPostExecute(List<HashMap<String, String>> hashMaps) {
                for(int i = 0; i<hashMaps.size();i++){
                    HashMap<String,String> hashMapList = hashMaps.get(i);
                    double lat = Double.parseDouble(Objects.requireNonNull(hashMapList.get("lat")));
                    double lng = Double.parseDouble(Objects.requireNonNull(hashMapList.get("lng")));
                    String name = hashMapList.get("name");

                    Log.d("data",name+" "+lat);

                    LatLng latLng = new LatLng(lat,lng);
                    MarkerOptions options = new MarkerOptions();
                    options.position(latLng);
                    options.title(name);
                    map.addMarker(options.icon(BitmapFromVector(MapActivity.this, R.drawable.custom_hospital_location_marker)));
                }
            }
        }
    }
}