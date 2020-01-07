package com.example.budgettrackerwithbottommenu.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.budgettrackerwithbottommenu.R;
import com.example.budgettrackerwithbottommenu.TransactionsListAdapter;
import com.example.budgettrackerwithbottommenu.database.DatabaseHelper;

public class DashboardFragment extends Fragment {

    public static DashboardFragment instance;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        instance = this;
        root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        ListView listView = root.findViewById(R.id.transactions_listview);

        TransactionsListAdapter transactionsListAdapter = new TransactionsListAdapter(
                getActivity(),
                R.layout.transactions_list_row,
                DatabaseHelper.getDatabaseHelper(getActivity()).getAllTransactions()
        );
        listView.setAdapter(transactionsListAdapter);

        return root;
    }

    public void resetAdapter(){

        ListView listView = root.findViewById(R.id.transactions_listview);

        TransactionsListAdapter transactionsListAdapter = new TransactionsListAdapter(
                getActivity(),
                R.layout.transactions_list_row,
                DatabaseHelper.getDatabaseHelper(getActivity()).getAllTransactions()
        );
        listView.setAdapter(transactionsListAdapter);

    }

}