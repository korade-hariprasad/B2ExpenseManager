package sumago.androidipt.b2expensemanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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
    private static final String COLUMN_CATEGORY_NAME = "category_name";
    // Expense Table Columns
    private static final String COLUMN_EXPENSE_ID = "id";
    private static final String COLUMN_EXPENSE_NAME = "name";
    private static final String COLUMN_EXPENSE_DATE = "date";
    private static final String COLUMN_EXPENSE_AMOUNT = "amount";
    private static final String COLUMN_EXPENSE_CATEGORY_ID = "categoryId";
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
        values.put(COLUMN_EXPENSE_DATE,expense.getDate());
        long id=database.insert(TABLE_EXPENSE,null,values);
        return id;
    }
}
