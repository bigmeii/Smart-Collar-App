package com.example.elec290smartcollarapp1.ui.ble_setup;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.elec290smartcollarapp1.data.ble.BleTestDevice;
import com.example.elec290smartcollarapp1.R;

import java.util.ArrayList;
import java.util.List;
import android.os.Handler;

public class BleConnectFragment extends Fragment {
    private Spinner deviceSpinner;
    private Button refreshButton;
    private Button disconnectButton;
    private TextView connectionStatusText;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothLeScanner bleScanner;
    private ArrayAdapter<String> spinnerAdapter;
    private final List<String> deviceNames = new ArrayList<String>() {{
        add(new BleTestDevice("Select Device", "00:11:22:33:44:01", -45).name);
        add(new BleTestDevice("EthansPC", "00:11:22:33:44:01", -45).name);
        add(new BleTestDevice("Arduino UNO WiFi", "00:11:22:33:44:02", -70).name);
        add(new BleTestDevice("Ethan's iPhone (2)", "00:11:22:33:44:03", -60).name);
    }};

    final Handler handler = new Handler();
    private boolean isSpinnerInitialized = false;

    public static boolean isConnected = false;

    public BleConnectFragment() {
        super(R.layout.fragment_ble_connect);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        deviceNames.add(new BleTestDevice("Select Device", "00:11:22:33:44:01", -45).name);
//        deviceNames.add(new BleTestDevice("EthansPC", "00:11:22:33:44:01", -45).name);
//        deviceNames.add(new BleTestDevice("Arduino UNO WiFi", "00:11:22:33:44:02", -70).name);
//        deviceNames.add(new BleTestDevice("Ethan's iPhone (2)", "00:11:22:33:44:03", -60).name);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ble_connect, container, false);

        deviceSpinner = view.findViewById(R.id.spinner_devices);
        refreshButton = view.findViewById(R.id.button_refresh);
        disconnectButton = view.findViewById(R.id.button_disconnect);
        connectionStatusText = view.findViewById(R.id.label_connection_status);

        // Set up Bluetooth
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bluetoothAdapter == null) {
            Toast.makeText(getContext(), "BLE not supported on this device.", Toast.LENGTH_LONG).show();
        } else {
            bleScanner = bluetoothAdapter.getBluetoothLeScanner();
        }

        // Set up Spinner
        spinnerAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                deviceNames);

        deviceSpinner.setAdapter(spinnerAdapter);

        // Refresh button → Start scanning
        refreshButton.setOnClickListener(v -> startBleScan());

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        disconnectButton.setOnClickListener(v -> {
            deviceSpinner.setSelection(0);
            Toast.makeText(getContext(), "Device Disconnected", Toast.LENGTH_SHORT).show();
        });

        deviceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!isSpinnerInitialized) {
                    // First call, just set flag and ignore
                    isSpinnerInitialized = true;
                    return;
                }
                String selectedItem = (String) parent.getItemAtPosition(position);

                // Run functions based on item
                switch (selectedItem) {
                    case "Arduino UNO WiFi":
                        connectionStatusSuccess();
                        break;
                    case "Ethan's iPhone (2)":
                    case "EthansPC":
                        connectionStatusFailed();
                        break;
                    default:
                        connectionStatusText.setText("Disconnected");
                        handleLostConnection();
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Optional: handle no selection
            }
        });

    }

    private void connectionStatusSuccess() {
        if (!isConnected) {
            connectionStatusText.setText("Connecting...");
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                    connectionStatusText.setText("Connected!");
                    isConnected = true;
                    Toast.makeText(getContext(), "Connected!", Toast.LENGTH_SHORT).show();
                }
            }, 5000);
        } else {
            connectionStatusText.setText("Connected!");
        }
    }

    private void connectionStatusFailed() {
        connectionStatusText.setText("Connecting...");
        if (isConnected) {
            handleLostConnection();
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                connectionStatusText.setText("Connection Failed");
            }
        }, 5000);
    }

    private void startBleScan() {

        spinnerAdapter.notifyDataSetChanged();
    }

    private void handleLostConnection() {
        isConnected = false;
        Toast.makeText(getContext(), "Lost connection to collar. Location shown is from last connection.", Toast.LENGTH_SHORT).show();
    }

    public static boolean getConnectionStatus() {
        return isConnected;
    }

}
