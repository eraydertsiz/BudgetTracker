package com.example.budgettrackerwithbottommenu;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.budgettrackerwithbottommenu.database.DatabaseHelper;
import com.example.budgettrackerwithbottommenu.ui.home.HomeFragment;
import com.example.budgettrackerwithbottommenu.utilities.DateHelper;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

public class BottomSheetActivity
        extends BottomSheetDialogFragment
        implements View.OnClickListener {

    double input1 = 0, input2 = 0;
    TextView edt1;
    boolean Addition, Subtract, Multiplication, Division,  decimal;
    Button button0, button1, button2, button3, button4, button5, button6,
            button7, button8, button9, buttonAdd, buttonSub,
            buttonMul, buttonDivision, buttonEqual, buttonDel, buttonDot, button_datePicker;
    public static HomeFragment homeFragment;
    public static final String TAG = "BottomSheetActivity";
    public static BottomSheetActivity instance;

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

        instance = this;

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        button_datePicker= getView().findViewById(R.id.button_date) ;

        datepicker.setDate(year, month, day);

        button0 = getView().findViewById(R.id.button0);
        button1 = getView().findViewById(R.id.button1);
        button2 = getView().findViewById(R.id.button2);
        button3 = getView().findViewById(R.id.button3);
        button4 = getView().findViewById(R.id.button4);
        button5 = getView().findViewById(R.id.button5);
        button6 = getView().findViewById(R.id.button6);
        button7 = getView().findViewById(R.id.button7);
        button8 = getView().findViewById(R.id.button8);
        button9 = getView().findViewById(R.id.button9);
        buttonDot = getView().findViewById(R.id.buttonDot);
        buttonAdd = getView().findViewById(R.id.buttonadd);
        buttonSub = getView().findViewById(R.id.buttonsub);
        buttonMul = getView().findViewById(R.id.buttonmul);
        buttonDivision = getView().findViewById(R.id.buttondiv);
        buttonDel = getView().findViewById(R.id.buttonDel);
        buttonEqual = getView().findViewById(R.id.buttoneql);

        edt1 =  getView().findViewById(R.id.display);

        button_datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker= new datepicker();
                datePicker.show(getFragmentManager(),"date picker");
            }
        });

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
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(datepicker.year, datepicker.month, datepicker.dayOfMonth);
                    DatabaseHelper.getDatabaseHelper(getActivity()).insertTransaction(amount, ((MainActivity)getActivity()).category, DateHelper.convertCalendarToSeconds(calendar));
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

    public void setDateButtonText(int year, int month, int day){

        String yearStr = "" + year;
        String monthStr = "" + (month + 1);
        if(monthStr.length() == 1){
            monthStr = "0" + monthStr;
        }
        String dayStr = "" + day;
        if(dayStr.length() == 1){
            dayStr = "0" + dayStr;
        }

        button_datePicker.setText(dayStr + "/" + monthStr + "/" + yearStr);

    }

    @Override
    public void onClick(View v) {


    }
}
