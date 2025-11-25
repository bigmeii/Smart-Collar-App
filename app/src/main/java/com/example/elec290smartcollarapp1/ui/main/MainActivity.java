package com.example.elec290smartcollarapp1.ui.main;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.elec290smartcollarapp1.ui.map.MapFragment;
import com.example.elec290smartcollarapp1.ui.vitals.VitalsFragment;
import com.example.elec290smartcollarapp1.ui.ble_setup.BleConnectFragment;



import com.example.elec290smartcollarapp1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private MapFragment mapFragment;
    private VitalsFragment vitalsFragment;
    private BleConnectFragment bleConnectFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH}, 5);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_ADMIN}, 6);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_SCAN}, 1);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 2);
        }

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
        bleConnectFragment = new BleConnectFragment();


        // Default fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, bleConnectFragment)
                .commit();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_bluetooth) {
                switchFragment(bleConnectFragment);
                return true;
            } else if (id == R.id.nav_vitals) {
                switchFragment(vitalsFragment);
                return true;
            } else if (id == R.id.nav_map) {
                switchFragment(mapFragment);
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