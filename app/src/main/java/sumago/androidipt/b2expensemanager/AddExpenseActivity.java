package sumago.androidipt.b2expensemanager;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;

import sumago.androidipt.b2expensemanager.database.DbHelper;
import sumago.androidipt.b2expensemanager.models.Category;
import sumago.androidipt.b2expensemanager.models.Expense;


public class AddExpenseActivity extends AppCompatActivity {
    ChipGroup chipGroup;
    TextInputLayout txLayoutExpenseName;
    TextInputLayout txLayoutAmount;
    TextInputEditText etName;
    TextInputEditText etAmount;
    TextView etDate;
    TextInputEditText etNotes;
    Button btnAdd;
    DbHelper dbHelper;
    ArrayList<Category> categoryArrayList;
    int year;
    int month;
    int day;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        etName=findViewById(R.id.etName);
        etDate=findViewById(R.id.etDate);
        etAmount=findViewById(R.id.etAmount);
        etNotes=findViewById(R.id.etNote);
        btnAdd=findViewById(R.id.btnAdd);
        chipGroup=findViewById(R.id.chipGroup);
        txLayoutExpenseName=findViewById(R.id.txExpenseNameLayout);
        txLayoutAmount=findViewById(R.id.txAmountLayout);
        dbHelper=new DbHelper(this);
        categoryArrayList=dbHelper.getAllCategories();
        calendar= Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);
        etDate.setText(""+year+"-"+month+"-"+day);
        setUpChips();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validateFields())
                {
                    String name=etName.getText().toString();
                    double amount=Double.parseDouble(etAmount.getText().toString());
                    String note=etNotes.getText().toString();
                    String date=etDate.getText().toString();
                    Chip chip=findViewById(chipGroup.getCheckedChipId());
                    Category category= (Category) chip.getTag();
                    String catName=category.getName();
                    int catId=category.getId();
                    long id=dbHelper.insertExpense(new Expense(name,catName,date,amount,note,catId));

                    if(id>0)
                    {
                        finish();
                    }
                    Log.d("mytag",""+id);
                }
            }
        });
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDatePickerDialog();
            }
        });
    }

    private void showDatePickerDialog() {

        DatePickerDialog datePickerDialog=new DatePickerDialog(AddExpenseActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                etDate.setText(year+"-"+(month+1)+"-"+dayOfMonth);
            }
        },year,month,day);
        datePickerDialog.show();

    }

    private boolean validateFields() {
        ArrayList<Boolean> errors = new ArrayList<>();

        if (!etName.getText().toString().isEmpty() && etName.getText().toString().length() > 1) {
            errors.add(true);
            txLayoutExpenseName.setError(null);
        } else {
            txLayoutExpenseName.setError("Please enter a valid name");
            errors.add(false);
        }
        if (!etAmount.getText().toString().isEmpty() && etAmount.getText().toString().length() > 0) {
            errors.add(true);
            txLayoutAmount.setError(null);
        } else {
            txLayoutAmount.setError("Please enter a valid amount");
            errors.add(false);
        }
        return !errors.contains(false);
    }

    private void setUpChips() {

        chipGroup.setSingleSelection(true);
        chipGroup.setSelectionRequired(true);
        Chip firstChip = null;
        for(int i=0;i<categoryArrayList.size();i++)
        {

            Chip chip=new Chip(chipGroup.getContext());
            chip.setText(categoryArrayList.get(i).getName());
            chip.setTag(categoryArrayList.get(i));
            chip.setCheckable(true);
            chip.setId(View.generateViewId());
            chipGroup.addView(chip);
            if(i==0)
            {
                firstChip=chip;
            }
        }
        //firstChip.setSelected(true);

    }
}