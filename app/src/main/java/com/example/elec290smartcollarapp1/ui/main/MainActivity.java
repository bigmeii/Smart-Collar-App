package com.example.elec290smartcollarapp1.ui.main;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.elec290smartcollarapp1.ui.map.MapFragment;
import com.example.elec290smartcollarapp1.ui.vitals.VitalsFragment;


import com.example.elec290smartcollarapp1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private MapFragment mapFragment;
    private VitalsFragment vitalsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize fragments
        mapFragment = new MapFragment();
        vitalsFragment = new VitalsFragment();

        // Default fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, mapFragment)
                .commit();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_map:
                    switchFragment(mapFragment);
                    return true;
                case R.id.nav_vitals:
                    switchFragment(vitalsFragment);
                    return true;
            }
            return false;
        });
    }

    private void switchFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}