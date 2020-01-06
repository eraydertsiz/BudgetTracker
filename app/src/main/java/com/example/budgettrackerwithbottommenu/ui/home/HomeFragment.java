package com.example.budgettrackerwithbottommenu.ui.home;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
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
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;

import com.example.budgettrackerwithbottommenu.BottomSheetActivity;
import com.example.budgettrackerwithbottommenu.MainActivity;
import com.example.budgettrackerwithbottommenu.R;
import com.example.budgettrackerwithbottommenu.Transaction;
import com.example.budgettrackerwithbottommenu.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class HomeFragment extends Fragment {

    private View root;
    private PieChartView chart;
    private PieChartData data;
    private Button tradeBtn, socialBtn, transportBtn, healthBtn, giftBtn, billBtn, familyBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_home, container, false);

        getViews();
        registerEventHandlers();

        return root;

    }

    @Override
    public void onResume() {
        super.onResume();
        getDataFromDatabase();
    }

    private void getViews(){
        chart = root.findViewById(R.id.chart);
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

    private void setChartColors(){

    }

    private void getDataFromDatabase(){
        Transaction[] t = DatabaseHelper.getDatabaseHelper(getActivity()).getAllTransactions();
        Log.d("DB_DEBUG", Transaction.transactionsToString(t));

        HashMap<String, Double> amountByCategories = DatabaseHelper.getDatabaseHelper(getActivity()).getAmountsByCategories();
        Set<String> keySet =  amountByCategories.keySet();
        String[] keysArray = new String[keySet.size()];
        keySet.toArray(keysArray);

        for(int i = 0; i < keysArray.length; i++){
            Log.d("DB_DEBUG", keysArray[i] + ": " + amountByCategories.get(keysArray[i]));
        }

        List<SliceValue> values = new ArrayList<SliceValue>();
        for (int i = 0; i < keysArray.length; ++i) {
            SliceValue sliceValue = new SliceValue(new Double(amountByCategories.get(keysArray[i])).floatValue(), ChartUtils.pickColor());
            sliceValue.setLabel(keysArray[i] + ": " + sliceValue.getValue() + "₺");
            values.add(sliceValue);
        }

        data = new PieChartData(values);
        data.setHasLabels(true);
        data.setHasLabelsOnlyForSelected(false);
        data.setHasLabelsOutside(false);
        data.setHasCenterCircle(true);

        chart.setPieChartData(data);

    }

    private void generateData() {

        int numValues = 6;

        List<SliceValue> values = new ArrayList<SliceValue>();
        for (int i = 0; i < numValues; ++i) {
            SliceValue sliceValue = new SliceValue((float) Math.random() * 30 + 15, ChartUtils.pickColor());
            sliceValue.setLabel("Data: " + sliceValue.getValue() + "₺");
            values.add(sliceValue);
        }

        data = new PieChartData(values);
        data.setHasLabels(true);
        data.setHasLabelsOnlyForSelected(false);
        data.setHasLabelsOutside(false);
        data.setHasCenterCircle(true);


        /*
().getDisplayMetrics().scaledDensity,
                    (int) getResources().getDimension(R.dimen            Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "Roboto-Italic.ttf");
.pie_chart_text2_size)));
        }
        */
        chart.setPieChartData(data);
    }


}