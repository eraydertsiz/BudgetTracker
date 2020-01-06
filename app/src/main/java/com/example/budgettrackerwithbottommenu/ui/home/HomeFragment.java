package com.example.budgettrackerwithbottommenu.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import lecho.lib.hellocharts.view.PieChartView;

import com.example.budgettrackerwithbottommenu.BottomSheetActivity;
import com.example.budgettrackerwithbottommenu.MainActivity;
import com.example.budgettrackerwithbottommenu.R;

public class HomeFragment extends Fragment {

    private View root;
    private PieChartView pieChartView;
    private Button tradeBtn, socialBtn, transportBtn, healthBtn, giftBtn, billBtn, familyBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_home, container, false);

        getViews();
        registerEventHandlers();

        return root;

    }

    private void getViews(){
        pieChartView = root.findViewById(R.id.chart);
        tradeBtn = root.findViewById(R.id.btnAlisveris);
        socialBtn = root.findViewById(R.id.btnSosyal);
        transportBtn = root.findViewById(R.id.btnUlasim);
        healthBtn = root.findViewById(R.id.btnSaglik);
        giftBtn = root.findViewById(R.id.btnHediye);
        billBtn = root.findViewById(R.id.btnFatura);
        familyBtn = root.findViewById(R.id.btnAile);
    }

    private void registerEventHandlers(){
        tradeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).startBottomSheetActivity("Trade");
            }
        });
        socialBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).startBottomSheetActivity("Social");
            }
        });
        transportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).startBottomSheetActivity("Transportation");
            }
        });
        healthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).startBottomSheetActivity("Health");
            }
        });
        giftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).startBottomSheetActivity("Gift");
            }
        });
        billBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).startBottomSheetActivity("Bill");
            }
        });
        familyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).startBottomSheetActivity("Family");
            }
        });
    }


}