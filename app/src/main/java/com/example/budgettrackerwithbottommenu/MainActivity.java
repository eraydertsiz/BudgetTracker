package com.example.budgettrackerwithbottommenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import lecho.lib.hellocharts.view.PieChartView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        /*
        PieChartView pieChartView = findViewById(R.id.chart);
        Button tradeBtn = (Button) findViewById(R.id.btnAlisveris);
        Button socialBtn = (Button) findViewById(R.id.btnSosyal);
        Button transportBtn = (Button) findViewById(R.id.btnUlasim);
        Button healthBtn = (Button) findViewById(R.id.btnSaglik);
        Button giftBtn = (Button) findViewById(R.id.btnHediye);
        Button billBtn = (Button) findViewById(R.id.btnFatura);

        final Intent bottomSheet = new Intent(this, BottomSheetActivity.class);

        tradeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheet.putExtra("category", "trade");
                startActivity(bottomSheet);
            }
        });
        socialBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheet.putExtra("category", "social");
                startActivity(bottomSheet);
            }
        });
        transportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheet.putExtra("category", "transport");
                startActivity(bottomSheet);
            }
        });
        healthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheet.putExtra("category", "health");
                startActivity(bottomSheet);
            }
        });
        giftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheet.putExtra("category", "gift");
                startActivity(bottomSheet);
            }
        });
        billBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheet.putExtra("category", "bill");
                startActivity(bottomSheet);
            }
        });

         */



    }
}
