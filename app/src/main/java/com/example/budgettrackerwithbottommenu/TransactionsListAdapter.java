package com.example.budgettrackerwithbottommenu;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.budgettrackerwithbottommenu.database.DatabaseHelper;
import com.example.budgettrackerwithbottommenu.utilities.DateHelper;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TransactionsListAdapter extends ArrayAdapter<Transaction> {

    private LayoutInflater inflater;
    private Transaction[] transactions;
    private Activity activity;

    private NumberFormat formatter;

    public TransactionsListAdapter(@NonNull Activity activity, int resource, @NonNull Transaction[] transactions) {
        super(activity, resource, transactions);
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.transactions = transactions;
        this.activity = activity;
        formatter = new DecimalFormat("#0.00");
    }

    public View getView(final int position, View convertView, ViewGroup parent) {


        convertView = inflater.inflate(R.layout.transactions_list_row, null);
        TextView amountText = (TextView) convertView.findViewById(R.id.transactions_list_amount);
        TextView categoryText = (TextView) convertView.findViewById(R.id.transactions_list_category);
        TextView dateText = (TextView) convertView.findViewById(R.id.transactions_list_date);
        ImageButton deleteButton = (ImageButton) convertView.findViewById(R.id.transactions_list_delete_button);

        amountText.setText(formatter.format(transactions[position].amount) + "₺");
        categoryText.setText(DatabaseHelper.getDatabaseHelper(activity).getCategoryNameById(transactions[position].category));
        dateText.setText(transactions[position].getStringRepresentationOfDate());

        return convertView;
    }

}
