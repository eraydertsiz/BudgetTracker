package com.example.budgettrackerwithbottommenu;

import com.example.budgettrackerwithbottommenu.utilities.DateHelper;

import java.util.Calendar;
import java.util.Date;

public class Transaction {

    public int id;
    public double amount;
    public int category;
    public long date;

    public Transaction(double amount, int category, long date, int id){
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.id = id;
    }

    public Transaction(double amount, int category, long date){
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.id = -1;
    }

    public String toString(){
        if(id == -1)
            return "ID: Unknown, Amount: " + amount + ", Category: " + category + ", Date: " + getStringRepresentationOfDate();
        return "ID: " + id + ", Amount: " + amount + ", Category: " + category + ", Date: " + getStringRepresentationOfDate();
    }

    public String toStringWellPrinted(){
        if(id == -1)
            return "ID: Unknown\nAmount: " + amount + "\nCategory: " + category + "\nDate: " + getStringRepresentationOfDate();
        return "ID: " + id + "\nAmount: " + amount + "\nCategory: " + category + "\nDate: " + getStringRepresentationOfDate();
    }

    public String getStringRepresentationOfDate(){
        return new Date(date * 1000).toString();
    }

    public String getStringOnlyDatePart(){

        Calendar cal = DateHelper.convertSecondsToCalendar(date);
        String year = "" + cal.get(Calendar.YEAR);
        String month = "" + (cal.get(Calendar.MONTH) + 1);
        if(month.length() == 1){
            month = "0" + month;
        }
        String day = "" + cal.get(Calendar.DAY_OF_MONTH);
        if(day.length() == 1){
            day = "0" + day;
        }
        return day + "/" + month + "/" + year;

    }




    //STATIC METHODS

    public static String transactionsToString(Transaction[] transactions){
        String s = "";
        for(int i = 0; i < transactions.length; i++){
            s += transactions[i].toString() + "\n";
        }
        return s;
    }


}
