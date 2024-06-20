package sumago.androidipt.b2expensemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import sumago.androidipt.b2expensemanager.database.DbHelper;
import sumago.androidipt.b2expensemanager.models.Expense;


public class ExpenseDetailsActivity extends AppCompatActivity {

    TextView tvName;
    TextView tvDate;
    TextView tvCategory;
    TextView tvAmount;
    CardView cardDelete;
    CardView cardEdit;
    DbHelper dbHelper;
    int expenseId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_details);
        tvAmount=findViewById(R.id.tvAmount);
        tvName=findViewById(R.id.tvName);
        tvCategory=findViewById(R.id.tvCategory);
        tvDate=findViewById(R.id.tvDate);
        cardDelete=findViewById(R.id.cardDelete);
        cardEdit=findViewById(R.id.cardEdit);
        dbHelper=new DbHelper(this);
        expenseId=getIntent().getIntExtra("id",0);
        // setDetails(new Expense());
        cardDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        cardEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ExpenseDetailsActivity.this, UpdateExpenseActivity.class);
                intent.putExtra("id",expenseId);
                startActivity(intent);
            }
        });
    }

    private void setDetails(Expense expense) {
        tvDate.setText(expense.getDate());
        tvName.setText(expense.getName());
        tvCategory.setText(expense.getCategoryName());
        tvAmount.setText(""+expense.getAmount());
    }
}