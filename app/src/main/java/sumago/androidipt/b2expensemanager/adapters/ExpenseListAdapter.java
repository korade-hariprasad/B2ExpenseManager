package sumago.androidipt.b2expensemanager.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sumago.androidipt.b2expensemanager.R;
import sumago.androidipt.b2expensemanager.models.Expense;

public class ExpenseListAdapter extends RecyclerView.Adapter<ExpenseListAdapter.ViewHolder> {

   ArrayList<Expense> list;

    public ExpenseListAdapter(ArrayList<Expense> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ExpenseListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expense,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseListAdapter.ViewHolder holder, int position) {

        holder.tvAmount.setText(""+list.get(position).getAmount());
        holder.tvExpenseName.setText(list.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvExpenseName;
        TextView tvAmount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvExpenseName=itemView.findViewById(R.id.tvExpenseName);
            tvAmount=itemView.findViewById(R.id.tvAmount);
        }
    }
}
