package com.example.budgettrackerwithbottommenu;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.budgettrackerwithbottommenu.database.DatabaseHelper;
import com.example.budgettrackerwithbottommenu.ui.home.HomeFragment;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import android.widget.Button;
import android.widget.Toast;

public class BottomSheetActivity extends BottomSheetDialogFragment implements View.OnClickListener {

    double input1 = 0, input2 = 0;
    TextView edt1;
    boolean Addition, Subtract, Multiplication, Division,  decimal;
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonAdd, buttonSub,
            buttonMul, buttonDivision, buttonEqual, buttonDel, buttonDot;
    public static HomeFragment homeFragment;
    public static final String TAG = "BottomSheetActivity";


    public static  BottomSheetActivity newInstance() {
        return new BottomSheetActivity();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottomsheet, container, false);
    }

    @Override
    public void onViewCreated (@NonNull View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        super.onCreate(savedInstanceState);


        Button button0 = (Button)  getView().findViewById(R.id.button0);
        Button button1 = (Button)  getView().findViewById(R.id.button1);
        Button button2 = (Button)  getView().findViewById(R.id.button2);
        Button button3 = (Button)  getView().findViewById(R.id.button3);
        Button button4 = (Button)  getView().findViewById(R.id.button4);
        Button button5 = (Button)  getView().findViewById(R.id.button5);
        Button button6 = (Button)  getView().findViewById(R.id.button6);
        Button button7 = (Button)  getView().findViewById(R.id.button7);
        Button button8 = (Button)  getView().findViewById(R.id.button8);
        Button button9 = (Button)  getView().findViewById(R.id.button9);
        Button buttonDot = (Button)  getView().findViewById(R.id.buttonDot);
        Button buttonAdd = (Button)  getView().findViewById(R.id.buttonadd);
        Button buttonSub = (Button)  getView().findViewById(R.id.buttonsub);
        Button buttonMul = (Button)  getView().findViewById(R.id.buttonmul);
        Button buttonDivision = (Button)  getView().findViewById(R.id.buttondiv);
        Button buttonDel = (Button)  getView().findViewById(R.id.buttonDel);
        Button buttonEqual = (Button)  getView().findViewById(R.id.buttoneql);

        edt1 =  getView().findViewById(R.id.display);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText(edt1.getText() + "1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText(edt1.getText() + "2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText(edt1.getText() + "3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText(edt1.getText() + "4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText(edt1.getText() + "5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText(edt1.getText() + "6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText(edt1.getText() + "7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText(edt1.getText() + "8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText(edt1.getText() + "9");
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText(edt1.getText() + "0");
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt1.getText().length() != 0) {
                    input1 = Float.parseFloat(edt1.getText() + "");
                    Addition = true;
                    decimal = false;
                    edt1.setText(null);
                }
            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt1.getText().length() != 0) {
                    input1 = Float.parseFloat(edt1.getText() + "");
                    Subtract = true;
                    decimal = false;
                    edt1.setText(null);
                }
            }
        });

        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt1.getText().length() != 0) {
                    input1 = Float.parseFloat(edt1.getText() + "");
                    Multiplication = true;
                    decimal = false;
                    edt1.setText(null);
                }
            }
        });

        buttonDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt1.getText().length() != 0) {
                    input1 = Float.parseFloat(edt1.getText() + "");
                    Division = true;
                    decimal = false;
                    edt1.setText(null);
                }
            }
        });



        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Addition || Subtract || Multiplication || Division ) {
                    input2 = Float.parseFloat(edt1.getText() + "");
                }

                if (Addition) {

                    edt1.setText(input1 + input2 + "");
                    Addition = false;
                }

                if (Subtract) {

                    edt1.setText(input1 - input2 + "");
                    Subtract = false;
                }

                if (Multiplication) {
                    edt1.setText(input1 * input2 + "");
                    Multiplication = false;
                }

                if (Division) {
                    edt1.setText(input1 / input2 + "");
                    Division = false;
                }

                try{
                    String s = edt1.getText().toString();
                    s = s.replace("₺","");
                    double amount = Double.parseDouble(s);
                    DatabaseHelper.getDatabaseHelper(getActivity()).insertTransaction(amount, ((MainActivity)getActivity()).category);
                    if(homeFragment == null){
                        Log.d("FRAGMENT","FRAGMENT IS NULL");
                    }
                    else{
                        homeFragment.getDataFromDatabase();
                    }
                    dismiss();
                }
                catch(Exception e){
                    Toast.makeText(getContext(),"Please enter a valid amount", Toast.LENGTH_SHORT).show();
                }

            }
        });

        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText("");
                input1 = 0.0;
                input2 = 0.0;
            }
        });

        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (decimal) {
                    //do nothing or you can show the error
                } else {
                    edt1.setText(edt1.getText() + ".");
                    decimal = true;
                }

            }
        });

    }


    @Override
    public void onClick(View v) {

    }
}
