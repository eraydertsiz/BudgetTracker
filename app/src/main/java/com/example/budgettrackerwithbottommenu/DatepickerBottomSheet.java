package com.example.budgettrackerwithbottommenu;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DatepickerBottomSheet extends DialogFragment {

    public static int year;
    public static int month;
    public static int dayOfMonth;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new DatePickerDialog(getActivity(), new MyDateSetListener(), year, month, dayOfMonth) ;
    }

    public static void setDate(int year, int month, int dayOfMonth){
        DatepickerBottomSheet.year = year;
        DatepickerBottomSheet.month = month;
        DatepickerBottomSheet.dayOfMonth = dayOfMonth;
        if(BottomSheetFragment.instance != null){
            BottomSheetFragment.instance.setDateButtonText(year, month, dayOfMonth);
        }
    }

    public class MyDateSetListener implements  DatePickerDialog.OnDateSetListener{

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            setDate(year, month, dayOfMonth);
            //Toast.makeText(getActivity(),"Date Selected. Year: " + year + " Month: " + month + " day: " + dayOfMonth, Toast.LENGTH_SHORT).show();
        }
    }
}
