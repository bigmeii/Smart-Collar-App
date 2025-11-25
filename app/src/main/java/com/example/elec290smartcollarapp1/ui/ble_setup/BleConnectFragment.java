package com.example.elec290smartcollarapp1.ui.ble_setup;

import android.Manifest;
import android.os.Bundle;

import androidx.annotation.RequiresPermission;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.elec290smartcollarapp1.data.ble.BleTestDevice;
import com.example.elec290smartcollarapp1.R;

import java.util.ArrayList;
import java.util.List;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BleConnectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BleConnectFragment extends Fragment {

    private Spinner deviceSpinner;
    private Button refreshButton;
    private Button disconnectButton;

    private BluetoothAdapter bluetoothAdapter;
    private BluetoothLeScanner bleScanner;

    private ArrayAdapter<String> spinnerAdapter;
    private final List<String> deviceNames = new ArrayList<>();
    private final List<BluetoothDevice> devicesFound = new ArrayList<>();

    private List<String> generateFakeDevices() {
        deviceNames.add(new BleTestDevice("Helmet A", "00:11:22:33:44:01", -45).name);
        deviceNames.add(new BleTestDevice("Helmet B", "00:11:22:33:44:02", -70).name);
        deviceNames.add(new BleTestDevice("Helmet C", "00:11:22:33:44:03", -60).name);
        return deviceNames;
    }



    public BleConnectFragment() {
        super(R.layout.fragment_ble_connect);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ble_connect, container, false);

        deviceSpinner = view.findViewById(R.id.sort_by_spinner);
        refreshButton = view.findViewById(R.id.refresh_button);
        disconnectButton = view.findViewById(R.id.disconnect_button);

        deviceNames.add(new BleTestDevice("Select Device", "00:11:22:33:44:01", -45).name);
        deviceNames.add(new BleTestDevice("EthansPC", "00:11:22:33:44:01", -45).name);
        deviceNames.add(new BleTestDevice("Arduino UNO WiFi", "00:11:22:33:44:02", -70).name);
        deviceNames.add(new BleTestDevice("Ethan's iPhone (2)", "00:11:22:33:44:03", -60).name);

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

        // Disconnect button
        disconnectButton.setOnClickListener(v ->
                Toast.makeText(getContext(), "Disconnect pressed (implement later)", Toast.LENGTH_SHORT).show());

        return view;
    }

    private void startBleScan() {
//        if (bluetoothAdapter == null || bleScanner == null) {
//            Toast.makeText(getContext(), "Bluetooth not available.", Toast.LENGTH_SHORT).show();
//            return;
//        }

        // Source - https://stackoverflow.com/a
// Posted by pankajagarwal, modified by community. See post 'Timeline' for change history
// Retrieved 2025-11-24, License - CC BY-SA 4.0



        // Clear old list
//        devicesFound.clear();
//        deviceNames.clear();
        spinnerAdapter.notifyDataSetChanged();

//        // Start scan
//        bleScanner.startScan(scanCallback);
//        Toast.makeText(getContext(), "Scanning for devices...", Toast.LENGTH_SHORT).show();
//
//        // Auto-stop scanning after 4 seconds
//        deviceSpinner.postDelayed(() -> {
//            bleScanner.stopScan(scanCallback);
//            Toast.makeText(getContext(), "Scan complete.", Toast.LENGTH_SHORT).show();
//        }, 4000);
    }
//    private final ScanCallback scanCallback = new ScanCallback() {
//        @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
//        @Override
//        public void onScanResult(int callbackType, ScanResult result) {
//
//            BluetoothDevice device = result.getDevice();
//            String name = device.getName();
//
//            if (name == null) name = "Unnamed Device";
//
//            // Avoid duplicates
//            if (!deviceNames.contains(name)) {
//                deviceNames.add(name);
//                devicesFound.add(device);
//                spinnerAdapter.notifyDataSetChanged();
//            }
//        }
//    };
}
