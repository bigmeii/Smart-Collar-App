package com.example.elec290smartcollarapp1.ui.vitals;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Timer;
import java.util.TimerTask;

public class VitalsViewModel extends ViewModel {
    private final MutableLiveData<Integer> heartRate = new MutableLiveData<>();

    public LiveData<Integer> getHeartRate() {
        return heartRate;
    }

    public void updateHeartRate(int hr) {
        heartRate.postValue(hr);
    }

    private Timer simulationTimer = new Timer();

    public void startSimulation() {
        simulationTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                int fakeHR = 60 + (int)(Math.random() * 40); // 60–100 bpm
                updateHeartRate(fakeHR);
            }
        }, 0, 1000); // every 1 second
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        simulationTimer.cancel();
    }
}
