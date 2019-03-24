package com.example.smartsurvey;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.CustomCap;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener, GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener {

    private GoogleMap mMap; Polyline polyline2; int polyCount; LatLng first; LatLng second;Polygon polygon; LatLng firstp,secondp,thirdp;
    LocationManager lm;
    LocationListener ls;
    boolean areaMade= false;
    int service;
    EditText areaName;
    Button ok;
    Button done;
    String name;

    ArrayList <Marker> markerArray= new ArrayList<>();
    private static final int COLOR_BLACK_ARGB = 0xff000000;
    private static final int COLOR_WHITE_ARGB = 0xffffffff;
    private static final int COLOR_GREEN_ARGB = 0xff388E3C;
    private static final int COLOR_PURPLE_ARGB = 0xff81C784;
    private static final int COLOR_ORANGE_ARGB = 0xffF57F17;
    private static final int COLOR_BLUE_ARGB = 0xffF9A825;

    private static final int POLYLINE_STROKE_WIDTH_PX = 12;
    private static final int POLYGON_STROKE_WIDTH_PX = 8;
    private static final int PATTERN_DASH_LENGTH_PX = 20;
    private static final int PATTERN_GAP_LENGTH_PX = 20;
    private static final PatternItem DOT = new Dot();
    private static final PatternItem DASH = new Dash(PATTERN_DASH_LENGTH_PX);
    private static final PatternItem GAP = new Gap(PATTERN_GAP_LENGTH_PX);
    // Create a stroke pattern of a gap followed by a dot.
    private static final List<PatternItem> PATTERN_POLYLINE_DOTTED = Arrays.asList(GAP, DOT);

    // Create a stroke pattern of a gap followed by a dash.
    private static final List<PatternItem> PATTERN_POLYGON_ALPHA = Arrays.asList(GAP, DASH);

    // Create a stroke pattern of a dot followed by a gap, a dash, and another gap.
    private static final List<PatternItem> PATTERN_POLYGON_BETA =
            Arrays.asList(DOT, GAP, DASH, GAP);
    List<LatLng> polyco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        areaName =(EditText) findViewById(R.id.areaNameField);
        ok= (Button)findViewById(R.id.okButton);
        done =(Button) findViewById(R.id.doneButton);
        done.setVisibility(View.INVISIBLE);
        areaName.setVisibility(View.INVISIBLE);
        ok.setVisibility(View.INVISIBLE);

        mMap = googleMap;
        mMap.setPadding(0,90,0,0);
        Intent intent = getIntent();
        service = intent.getIntExtra("service", -1);
        lm= (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        mMap.setOnMapLongClickListener(this);
        ls= new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if(service==1) {
                    CircleOptions circleOptions = new CircleOptions()
                            .center(new LatLng(location.getLatitude(), location.getLongitude()))
                            .fillColor(Color.BLUE)
                            .radius(2)
                            .strokeColor(Color.CYAN);
                    mMap.addCircle(circleOptions);
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if(service == 0){

        }




        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);

        }
        else{
            mMap.setMyLocationEnabled(true);
            mMap.setOnMyLocationButtonClickListener(this);
            mMap.setOnMyLocationClickListener(this);
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,10,ls);
        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length==1 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                mMap.setMyLocationEnabled(true);
                mMap.setOnMyLocationButtonClickListener(this);
                mMap.setOnMyLocationClickListener(this);
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,10,ls);
            }
        }

    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        if (areaMade == false) {
            MarkerOptions markerOptions = new MarkerOptions().position(latLng);
            markerArray.add(mMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))));
            dynamicPolyline(latLng);
        }
    }

    public void dynamicPolyline(LatLng latLng) {
        if (areaMade == false) {
            if (polyCount == 0) {
                first = latLng;
                polyCount++;
            } else if (polyCount > 0) {
                second = latLng;
                polyline2 = mMap.addPolyline(new PolylineOptions().clickable(true)
                        .width(4)
                        .add(
                                new LatLng(first.latitude, first.longitude),
                                new LatLng(second.latitude, second.longitude)
                        ));
                first = second;
            }
        }
    }

    public  void polygonMake(View view){
        List polyco= polyline2.getPoints();
        if(areaMade==false) {
            if (markerArray.size() < 2) {
                Toast.makeText(this, polyco.toString(), Toast.LENGTH_SHORT).show();
            } else {
                PolygonOptions polygonOptions = new PolygonOptions().fillColor(0x3342f4cb).strokeWidth(4);
                for (int i = 0; i < markerArray.size(); i++) {
                    polygonOptions.add(markerArray.get(i).getPosition());
                }
                polygon = mMap.addPolygon(polygonOptions);
            }

            areaName.setVisibility(View.VISIBLE);
            ok.setVisibility(View.VISIBLE);
            areaMade=true;
            //mMap.addMarker(new MarkerOptions().position(new LatLng(center[0],center[1])));
        }
    }

    public  void onOk(View view){
        name = areaName.getText().toString();
        areaName.setVisibility(View.INVISIBLE);
        ok.setVisibility(View.INVISIBLE);
        double[] center=centroid(markerArray);
        mMap.addMarker(new MarkerOptions().position(new LatLng(center[0],center[1])).title(name));
        done.setVisibility(View.VISIBLE);
    }

    public void onClear(View view){
        polyline2.remove();
        polygon.remove();
        markerArray.clear();
        mMap.clear();
        areaMade=false;
        polyCount=0;
        done.setVisibility(View.INVISIBLE);
    }

    public void onDone(View view){
        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
        SurveyPlanner.polygons.add(markerArray);
        SurveyPlanner.areaNames.add(name);
       /* Intent intent = new Intent(this, SurveyPlanner.class);
        intent.putExtra("value","1");
        startActivity(intent);*/
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }

    public static double[] centroid(ArrayList<Marker> points) {
        double[] centroid = { 0.0, 0.0 };

        for (int i = 0; i < points.size(); i++) {
            centroid[0] += points.get(i).getPosition().latitude;
            centroid[1] += points.get(i).getPosition().longitude;
        }

        int totalPoints = points.size();
        centroid[0] = centroid[0] / totalPoints;
        centroid[1] = centroid[1] / totalPoints;

        return centroid;
    }
}