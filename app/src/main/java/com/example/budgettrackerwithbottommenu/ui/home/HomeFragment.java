package com.example.budgettrackerwithbottommenu.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;

import com.example.budgettrackerwithbottommenu.BottomSheetActivity;
import com.example.budgettrackerwithbottommenu.MainActivity;
import com.example.budgettrackerwithbottommenu.R;
import com.example.budgettrackerwithbottommenu.Transaction;
import com.example.budgettrackerwithbottommenu.database.DatabaseHelper;
import com.example.budgettrackerwithbottommenu.utilities.DateHelper;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HomeFragment extends Fragment {

    private View root;
    private PieChartView chart;
    private PieChartData data;
    private Button tradeBtn, socialBtn, transportBtn, healthBtn, giftBtn, billBtn, familyBtn;

    private  Map<String, Integer> categoryColors;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_home, container, false);

        getViews();
        registerEventHandlers();

        BottomSheetActivity.homeFragment = this;

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

    private void getCategoryColors(){
        Field[] fields = R.color.class.getDeclaredFields();

        //Create arrays for color names and values
        String [] names = new String[fields.length];
        int [] colors = new int [fields.length];

        //iterate on the fields array, and get the needed values:
        try {
            for(int i=0; i<fields.length; i++) {
                names [i] = fields[i].getName();
                colors [i] = fields[i].getInt(null);
            }
        } catch (Exception ex) {
            /* handle exception if you want to */
        }

        categoryColors = new HashMap<String, Integer>();

        for(int i=0; i<colors.length; i++) {
            categoryColors.put(names[i], colors[i]);
        }
    }

    public void getDataFromDatabase(){

        getCategoryColors();
        Transaction[] t = DatabaseHelper.getDatabaseHelper(getActivity()).getAllTransactions();
        //Log.d("DB_DEBUG", Transaction.transactionsToString(t));



        HashMap<String, Double> amountByCategories = DatabaseHelper.getDatabaseHelper(getActivity()).getAmountsByCategories(
                DateHelper.getTodayAsSeconds(),
                DateHelper.getEndOfTodayAsSeconds()
        );
        Set<String> keySet =  amountByCategories.keySet();
        String[] keysArray = new String[keySet.size()];
        keySet.toArray(keysArray);

        NumberFormat formatter = new DecimalFormat("#0.00");

        List<SliceValue> values = new ArrayList<SliceValue>();
        for (int i = 0; i < keysArray.length; i++) {
            SliceValue sliceValue = new SliceValue(new Double(amountByCategories.get(keysArray[i])).floatValue(), ChartUtils.pickColor());
            sliceValue.setLabel(formatter.format(sliceValue.getValue()) + "₺");
            sliceValue.setColor(getResources().getColor(categoryColors.get(keysArray[i])));
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

        chart.setPieChartData(data);
    }


}