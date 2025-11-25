package com.example.elec290smartcollarapp1.ui.vitals;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.elec290smartcollarapp1.R;
import com.example.elec290smartcollarapp1.ui.vitals.VitalsViewModel;

public class VitalsFragment extends Fragment {

    private TextView heartRateText;
    private VitalsViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vitals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // UI reference
        heartRateText = view.findViewById(R.id.heart_rate_value);

        // Shared ViewModel used by BLE fragment
        viewModel = new ViewModelProvider(requireActivity()).get(VitalsViewModel.class);

        viewModel.startSimulation();

        // Update UI when HR data arrives
        viewModel.getHeartRate().observe(getViewLifecycleOwner(), hr -> {
            if (hr != null) {
                heartRateText.setText(hr + " bpm");
            }
        });
    }
}
