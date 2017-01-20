package com.garfieldchou.memorableplaces;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> places = new ArrayList<>();
    static ArrayList<LatLng> locations = new ArrayList<>();
    static ArrayAdapter arrayAdapter;
    static SharedPreferences placesSharedPreferences;
    static SharedPreferences locationsSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);

        placesSharedPreferences = this.getSharedPreferences("com.garfieldchou.memorableplaces", Context.MODE_PRIVATE);

        locationsSharedPreferences = this.getSharedPreferences("com.garfieldchou.memorableplaces", Context.MODE_PRIVATE);

        try {

            places = (ArrayList<String>) ObjectSerializer.deserialize(placesSharedPreferences.getString("places", ObjectSerializer.serialize(new ArrayList<String>())));

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            locations = (ArrayList<LatLng>) ObjectSerializer.deserialize(placesSharedPreferences.getString("locations", ObjectSerializer.serialize(new ArrayList<LatLng>())));
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (places.size() == 0) {

            places.add("Add a new places...");

        }

        if (locations.size() == 0) {

            locations.add(new LatLng(0, 0));

        }

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, places);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);

                intent.putExtra("placeNumber", i);

                startActivity(intent);

            }
        });
    }
}
