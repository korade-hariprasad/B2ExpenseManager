package sumago.androidipt.b2expensemanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import sumago.androidipt.b2expensemanager.models.Expense;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mydb";
    // Table Names
    private static final String TABLE_EXPENSE = "expense";
    private static final String TABLE_CATEGORY = "category";
    // Common Column
    private static final String COLUMN_IS_DELETED = "is_deleted";
    // Category Table Columns
    private static final String COLUMN_CATEGORY_ID = "id";
    private static final String COLUMN_CATEGORY_NAME = "categoryName";
    // Expense Table Columns
    private static final String COLUMN_EXPENSE_ID = "id";
    private static final String COLUMN_EXPENSE_NAME = "name";
    private static final String COLUMN_EXPENSE_DATE = "date";
    private static final String COLUMN_EXPENSE_AMOUNT = "amount";
    private static final String COLUMN_EXPENSE_CATEGORY_ID = "categoryId";
    private static final String COLUMN_EXPENSE_NOTE = "note";
    private static final String COLUMN_EXPENSE_CATEGORY_NAME = "categoryName";



    private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE " +
            TABLE_CATEGORY + "("
            + COLUMN_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CATEGORY_NAME + " TEXT NOT NULL, "
            + COLUMN_IS_DELETED + " INTEGER DEFAULT 0);";
    // Create Expense Table
    private static final String CREATE_TABLE_EXPENSE = "CREATE TABLE " +
            TABLE_EXPENSE + "("
            + COLUMN_EXPENSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_EXPENSE_NAME + " TEXT NOT NULL, "
            + COLUMN_EXPENSE_DATE + " TEXT NOT NULL, "
            + COLUMN_EXPENSE_AMOUNT + " REAL NOT NULL, "
            + COLUMN_EXPENSE_CATEGORY_ID + " INTEGER, "
            + COLUMN_EXPENSE_CATEGORY_NAME + " TEXT NOT NULL, "
            + COLUMN_EXPENSE_NOTE + " TEXT NOT NULL, "
            + COLUMN_IS_DELETED + " INTEGER DEFAULT 0"
            + ");";
    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE_EXPENSE);
            db.execSQL("CREATE TABLE category(id INTEGER PRIMARY KEY AUTOINCREMENT, category_name" +
                    "TEXT NOT NULL, is_deleted INTEGER DEFAULT 0);");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertExpense(Expense expense)
    {
        SQLiteDatabase database=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_EXPENSE_CATEGORY_NAME,expense.getCategoryName());
        values.put(COLUMN_EXPENSE_NAME,expense.getName());
        values.put(COLUMN_EXPENSE_CATEGORY_ID,expense.getCategoryId());
        values.put(COLUMN_EXPENSE_AMOUNT,expense.getAmount());
        values.put(COLUMN_EXPENSE_NOTE,expense.getNote());
        values.put(COLUMN_EXPENSE_DATE,expense.getDate());
        long id=database.insert(TABLE_EXPENSE,null,values);
        return id;
    }

    public ArrayList<Expense> getAllExpenses(){
        ArrayList<Expense> list=new ArrayList<>();
        SQLiteDatabase database=getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT * FROM "+TABLE_EXPENSE,null);
        if(cursor.moveToFirst())
        {
            do{
                Expense expense=new Expense();
                expense.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXPENSE_NAME)));
                expense.setId(cursor.getInt(0));
                expense.setAmount(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_EXPENSE_AMOUNT)));
                expense.setCategoryName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY_NAME)));
                expense.setCategoryId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_EXPENSE_CATEGORY_ID)));
                expense.setNote(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXPENSE_NOTE)));
                list.add(expense);

            }while (cursor.moveToNext());

        }
        return list;
    }
    public double getSum(){
        SQLiteDatabase database=getReadableDatabase();
        double sum=0d;
        Cursor cursor=database.rawQuery("SELECT SUM(amount) FROM expense",null);
        if(cursor.moveToFirst())
        {
            sum=cursor.getDouble(0);
        }
        return sum;
    }

    public Expense getExpenseById(int id)
    {
        Expense expense=new Expense();
        SQLiteDatabase database=getReadableDatabase();
        String[] selectionArgs=new String[]{String.valueOf(id)};
        Cursor cursor=database.rawQuery("SELECT * FROM "+TABLE_EXPENSE+" WHERE "+COLUMN_EXPENSE_ID+"=?",selectionArgs);
        if(cursor!=null)
        {
            if(cursor.moveToFirst())
            {

                expense.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXPENSE_NAME)));
                expense.setId(cursor.getInt(0));
                expense.setAmount(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_EXPENSE_AMOUNT)));
                expense.setCategoryName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY_NAME)));
                expense.setCategoryId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_EXPENSE_CATEGORY_ID)));
                expense.setNote(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXPENSE_NOTE)));
                expense.setDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXPENSE_DATE)));
            }
        }

        return expense;
    }

    public int deleteExpenseById(int id)
    {
        SQLiteDatabase database=getWritableDatabase();
        String[] selectionArgs=new String[]{String.valueOf(id)};
        int count=database.delete(TABLE_EXPENSE,COLUMN_EXPENSE_ID+"=?",selectionArgs);
        return count;
    }

    public int updateExpense(Expense expense){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues values=new ContentValues();
        String[] selectionArgs=new String[]{String.valueOf(expense.getId())};
        values.put(COLUMN_EXPENSE_CATEGORY_NAME,expense.getCategoryName());
        values.put(COLUMN_EXPENSE_NAME,expense.getName());
        values.put(COLUMN_EXPENSE_CATEGORY_ID,expense.getCategoryId());
        values.put(COLUMN_EXPENSE_AMOUNT,expense.getAmount());
        values.put(COLUMN_EXPENSE_NOTE,expense.getNote());
        values.put(COLUMN_EXPENSE_DATE,expense.getDate());
        int count=database.update(TABLE_EXPENSE,values,COLUMN_EXPENSE_ID+"=?",selectionArgs);
        return count;
    }
}
