package com.example.budgettrackerwithbottommenu.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import com.example.budgettrackerwithbottommenu.Transaction;
import com.example.budgettrackerwithbottommenu.utilities.DateHelper;

import java.util.Calendar;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper instance;

    private final static String DATABASE_NAME = "tracker_database";
    private final static int DATABASE_VERSION = 1;

    public static DatabaseHelper getDatabaseHelper(Context context){
        if(instance == null){
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    private DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {


        db.execSQL(
                "CREATE TABLE categories " +
                        "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name varchar(255))"
        );

        db.execSQL(
                "CREATE TABLE transactions " +
                        "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "amount real NOT NULL, " +
                        "category_id int NOT NULL, " +
                        "time int NOT NULL, " +
                        "FOREIGN KEY (category_id) REFERENCES categories (id))"
        );

        db.execSQL("INSERT INTO categories(name) " +
                "VALUES('Trade'),('Social'),('Transportation'),('Health'),('Gift'),('Bill'),('Family')");

        Log.d("DB_DEBUG","create database");

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS transactions");
        onCreate(db);
        Log.d("DB_DEBUG","upgrade database");
    }


    //region TRANSACTIONS

    public void insertTransaction(Transaction transaction){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("amount", transaction.amount);
        contentValues.put("category_id", transaction.category);
        contentValues.put("time", transaction.date);

        db.insert("transactions", null, contentValues);
    }

    public void insertTransaction(double amount, String categoryName, long dateTime){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("amount", amount);
        contentValues.put("category_id", getCategoryIdByName(categoryName));
        contentValues.put("time", dateTime);

        db.insert("transactions", null, contentValues);
    }

    public void insertTransaction(double amount, String category){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("amount", amount);
        contentValues.put("category_id", getCategoryIdByName(category));
        contentValues.put("time", System.currentTimeMillis() / 1000L );

        db.insert("transactions", null, contentValues);
    }

    public void removeAllTransactions(){

        SQLiteDatabase db = getWritableDatabase();
        db.delete("transactions", "", null);

    }

    public Transaction[] getAllTransactions(){
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM transactions", null);
        Transaction[] transactions = new Transaction[c.getCount()];

        int index = 0;
        while(c.moveToNext()){
            transactions[index++] = new Transaction(
                    c.getDouble(1),
                    c.getInt(2),
                    c.getInt(3),
                    c.getInt(0)
            );
        }
        c.close();
        return transactions;
    }

    public Transaction[] getTransactionsBetween(Calendar startDate, Calendar endDate){

        SQLiteDatabase db = getReadableDatabase();

        long startTime = DateHelper.convertCalendarToSeconds(startDate);
        long endTime = DateHelper.convertCalendarToSeconds(endDate);

        Log.d("DATE_DEBUG", "start: " + startTime + " end: " + endTime);

        Cursor c = db.rawQuery("SELECT * FROM transactions " +
                "WHERE time >= " + startTime + " AND " +
                "time <= " + endTime, null);
        Transaction[] transactions = new Transaction[c.getCount()];

        int index = 0;
        while(c.moveToNext()){
            transactions[index++] = new Transaction(
                    c.getDouble(1),
                    c.getInt(2),
                    c.getInt(3),
                    c.getInt(0)
            );
        }
        c.close();
        return transactions;

    }

    public Transaction[] getLastTransactions(int count){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM transactions ORDER BY time DESC LIMIT " + count, null);
        Transaction[] transactions = new Transaction[c.getCount()];

        int index = 0;
        while(c.moveToNext()){
            transactions[index++] = new Transaction(
                    c.getDouble(1),
                    c.getInt(2),
                    c.getInt(3),
                    c.getInt(0)
            );
        }
        c.close();
        return transactions;
    }

    //endregion


    //region CATEGORIES

    public void insertCategory(String name){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);

        db.insert("categories", null, contentValues);
    }

    public boolean checkCategoryNameExists(String categoryName){
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT id FROM categories WHERE name = '" + categoryName + "'", null);

        if(c.getCount() >= 1){
            c.close();
            return true;
        }
        c.close();
        return false;
    }

    public void removeAllCategories(){
        removeAllTransactions();
        SQLiteDatabase db = getWritableDatabase();
        db.delete("categories", "", null);
    }

    public String[] getCategories(){

        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM categories", null);
        String[] categories = new String[c.getCount()];

        int index = 0;
        while(c.moveToNext()){
            categories[index++] = c.getString(1);
        }
        c.close();
        return categories;

    }

    public String getCategoryNameById(int id){

        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT name FROM categories WHERE id = " + id, null);

        c.moveToFirst();
        String name = c.getString(0);

        c.close();
        return name;

    }

    public int getCategoryIdByName(String categoryName){
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT id FROM categories WHERE name = '" + categoryName + "'", null);

        c.moveToFirst();
        int id = c.getInt(0);

        c.close();
        return id;
    }

    public HashMap<String, Double> getAmountsByCategories(){
        HashMap<String, Double> groupedExpenses = new HashMap<String,Double>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT amount,category_id FROM transactions", null);

        int categoryColIndex = c.getColumnIndex("category_id");
        int amountColIndex = c.getColumnIndex("amount");

        while(c.moveToNext()){
            if(groupedExpenses.containsKey(getCategoryNameById(c.getInt(categoryColIndex)))){
                groupedExpenses.put(getCategoryNameById(c.getInt(categoryColIndex)), groupedExpenses.get(getCategoryNameById(c.getInt(categoryColIndex))) + c.getDouble(amountColIndex));
            }
            else{
                groupedExpenses.put(getCategoryNameById(c.getInt(categoryColIndex)), c.getDouble(amountColIndex));
            }
        }
        c.close();
        return groupedExpenses;
    }

    public void deleteCategory(String categoryName){

        SQLiteDatabase db = getWritableDatabase();

        Cursor c = db.rawQuery("SELECT id FROM categories WHERE name = '" + categoryName + "'", null);
        c.moveToFirst();
        int categoryId = c.getInt(0);
        c.close();

        db.delete("transactions","category_id=?", new String[]{"" + categoryId});
        db.delete("categories","name=?", new String[]{categoryName});

    }

    public void updateCategoryName(String oldName, String newName){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("name", newName);

        db.update("categories", cv, "name = ?", new String[]{newName});

    }

    public void moveCategoryToAnother(String fromCategory, String toCategory){

        SQLiteDatabase db = getWritableDatabase();

        Cursor c1 = db.rawQuery("SELECT id FROM categories WHERE name='" + fromCategory + "'", null);
        c1.moveToFirst();
        int fromId = c1.getInt(0);
        c1.close();

        Cursor c2 = db.rawQuery("SELECT id FROM categories WHERE name='" + toCategory + "'", null);
        c2.moveToFirst();
        int toId = c2.getInt(0);
        c2.close();

        ContentValues cv = new ContentValues();
        cv.put("category_id", toId);

        db.update("transactions", cv, "category_id=?", new String[]{"" + fromId});
        db.delete("categories", "id=?", new String[]{"" + toId});

    }

    //endregion

}
