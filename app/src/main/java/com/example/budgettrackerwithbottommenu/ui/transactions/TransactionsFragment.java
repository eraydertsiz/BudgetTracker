package com.example.budgettrackerwithbottommenu.ui.transactions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.budgettrackerwithbottommenu.DatepickerTransactionsList;
import com.example.budgettrackerwithbottommenu.R;
import com.example.budgettrackerwithbottommenu.TransactionsListAdapter;
import com.example.budgettrackerwithbottommenu.database.DatabaseHelper;

import java.util.Calendar;

public class TransactionsFragment extends Fragment {

    public static TransactionsFragment instance;

    View root;
    Button dateButton;
    ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        instance = this;
        root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        dateButton = root.findViewById(R.id.button_date);
        listView = root.findViewById(R.id.transactions_listview);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker= new DatepickerTransactionsList();
                datePicker.show(getFragmentManager(),"date picker");
            }
        });

        TransactionsListAdapter transactionsListAdapter = new TransactionsListAdapter(
                getActivity(),
                R.layout.transactions_list_row,
                DatabaseHelper.getDatabaseHelper(getActivity()).getLastTransactions(20)
        );
        listView.setAdapter(transactionsListAdapter);

        return root;
    }

    public void resetAdapter(){

        TransactionsListAdapter transactionsListAdapter = new TransactionsListAdapter(
                getActivity(),
                R.layout.transactions_list_row,
                DatabaseHelper.getDatabaseHelper(getActivity()).getAllTransactions()
        );
        listView.setAdapter(transactionsListAdapter);

    }

    public void resetAdapter(int year, int month, int dayOfMonth){

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        TransactionsListAdapter transactionsListAdapter = new TransactionsListAdapter(
                getActivity(),
                R.layout.transactions_list_row,
                DatabaseHelper.getDatabaseHelper(getActivity()).getTransactionsBetween(
                        cal.getTimeInMillis() / 1000L,
                        cal.getTimeInMillis() / 1000L + 86399
                )
        );
        listView.setAdapter(transactionsListAdapter);

    }

    public void dateSelected(int year, int month, int dayOfMonth) {
        resetAdapter(year, month, dayOfMonth);
        String yearStr = "" + year;
        String monthStr = "" + (month + 1);
        if(monthStr.length() == 1){
            monthStr = "0" + monthStr;
        }
        String dayStr = "" + dayOfMonth;
        if(dayStr.length() == 1){
            dayStr = "0" + dayStr;
        }
        dateButton.setText(dayStr + "/" + monthStr + "/" + yearStr);
    }
}