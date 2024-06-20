package sumago.androidipt.b2expensemanager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import sumago.androidipt.b2expensemanager.database.DbHelper;
import sumago.androidipt.b2expensemanager.models.Expense;


public class AddExpenseActivity extends AppCompatActivity {
    ChipGroup chipGroup;

    TextInputLayout txLayoutExpenseName;
    TextInputLayout txLayoutAmount;
    TextInputEditText etName;
    TextInputEditText etAmount;
    TextInputEditText etDate;
    TextInputEditText etNotes;
    Button btnAdd;

    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        etName=findViewById(R.id.etName);
        etDate=findViewById(R.id.etDate);
        etAmount=findViewById(R.id.etAmount);
        etNotes=findViewById(R.id.etNote);
        btnAdd=findViewById(R.id.btnAdd);
        txLayoutExpenseName=findViewById(R.id.txExpenseNameLayout);
        txLayoutAmount=findViewById(R.id.txAmountLayout);
        dbHelper=new DbHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validateFields())
                {
                    String name=etName.getText().toString();
                    double amount=Double.parseDouble(etAmount.getText().toString());
                    String note=etNotes.getText().toString();
                    String date=etDate.getText().toString();
                    String catName="Default";
                    int catId=1;
                    long id=dbHelper.insertExpense(new Expense(name,catName,date,amount,note,catId));
                    Log.d("mytag",""+id);
                }
            }
        });
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