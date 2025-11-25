package com.example.elec290smartcollarapp1.data.ble;

public class BleTestDevice {
    public String name;
    public String address;
    public int rssi;

    public BleTestDevice(String name, String address, int rssi) {
        this.name = name;
        this.address = address;
        this.rssi = rssi;
    }
}
