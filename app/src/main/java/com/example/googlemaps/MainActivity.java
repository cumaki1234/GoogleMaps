package com.example.googlemaps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
implements OnMapReadyCallback, GoogleMap.OnMapClickListener
{
    GoogleMap Mapa;
    List<LatLng> Lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Lista=new ArrayList<>();




    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Mapa=googleMap;
        //esta conectado al mapa
        Mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        Mapa.getUiSettings().setZoomControlsEnabled(true);
        //Mover el mapa a una ubicacion
        CameraUpdate camUpd1 =
                CameraUpdateFactory
                        .newLatLngZoom(new LatLng(40.689377979707885, -74.04424526217305), 19);
        Mapa.moveCamera(camUpd1);
        LatLng madrid = new LatLng(40.689377979707885, -74.04424526217305);
        CameraPosition camPos = new CameraPosition.Builder()
                .target(madrid)
                .zoom(19)
                .bearing(45) //noreste arriba
                .tilt(70) //punto de vista de la cámara 70 grados
                .build();
        CameraUpdate camUpd3 =
                CameraUpdateFactory.newCameraPosition(camPos);
        Mapa.animateCamera(camUpd3);
        Mapa.setOnMapClickListener(this);


    }


    @Override
    public void onMapClick(@NonNull LatLng latLng) {


        LatLng punto= new LatLng(latLng.latitude, latLng.longitude);
        Mapa.addMarker(new MarkerOptions().position(punto).title("punto"));


Lista.add(latLng);
if(Lista.size()==6) {
    PolylineOptions lineas = new
            PolylineOptions()
            .add(new LatLng(Lista.get(0).latitude, Lista.get(0).longitude))
            .add(new LatLng(Lista.get(1).latitude, Lista.get(1).longitude))
            .add(new LatLng(Lista.get(2).latitude, Lista.get(2).longitude))
            .add(new LatLng(Lista.get(3).latitude, Lista.get(3).longitude))
            .add(new LatLng(Lista.get(4).latitude, Lista.get(4).longitude))
            .add(new LatLng(Lista.get(5).latitude, Lista.get(5).longitude))
            .add(new LatLng(Lista.get(0).latitude, Lista.get(0).longitude));
    lineas.width(8);
    lineas.color(Color.RED);
    Mapa.addPolyline(lineas);
    Lista.clear();
}
    }
}