package sumago.androidipt.b2expensemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import sumago.androidipt.b2expensemanager.database.DbHelper;
import sumago.androidipt.b2expensemanager.models.Expense;


public class UpdateExpenseActivity extends AppCompatActivity {
    TextInputLayout txLayoutExpenseName;
    TextInputLayout txLayoutAmount;
    TextInputEditText etName;
    TextInputEditText etAmount;
    TextInputEditText etDate;
    TextInputEditText etNotes;
    Button btnUpdate;
    DbHelper dbHelper;
    int expenseId=0;
    Expense expense;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_expense);
        etName=findViewById(R.id.etName);
        etDate=findViewById(R.id.etDate);
        etAmount=findViewById(R.id.etAmount);
        etNotes=findViewById(R.id.etNote);
        btnUpdate=findViewById(R.id.btnUpdate);
        txLayoutExpenseName=findViewById(R.id.txExpenseNameLayout);
        txLayoutAmount=findViewById(R.id.txAmountLayout);
        dbHelper=new DbHelper(this);
        expenseId=getIntent().getIntExtra("id",0);
         expense=dbHelper.getExpenseById(expenseId);
        setDetails(expense);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateFields())
                {
                    String name=etName.getText().toString();
                    double amount=Double.parseDouble(etAmount.getText().toString());
                    String note=etNotes.getText().toString();
                    String date=etDate.getText().toString();
                    String catName=expense.getCategoryName();
                    int catId=expense.getCategoryId();
                    int count=dbHelper.updateExpense(new Expense(expenseId,name,catName,date,amount,note,catId));
                    if(count>0)
                    {
                        Intent intent=new Intent(UpdateExpenseActivity.this,MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    private void setDetails(Expense expense) {
        etName.setText(expense.getName());
        etAmount.setText(""+expense.getAmount());
        etNotes.setText(expense.getNote());
        etDate.setText(expense.getDate());
    }

    private boolean validateFields() {
        ArrayList<Boolean> errors=new ArrayList<>();

        if(!etName.getText().toString().isEmpty() && etName.getText().toString().length()>1)
        {
            errors.add(true);
            etName.setError(null);
        }else
        {
            etName.setError("Please enter a valid name");
            errors.add(false);
        }
        if(!etAmount.getText().toString().isEmpty() && etAmount.getText().toString().length()>0)
        {
            errors.add(true);
            etAmount.setError(null);
        }else{
            etAmount.setError("Please enter a valid amount");
            errors.add(false);
        }
        return !errors.contains(false);
    }
}