package sumago.androidipt.b2expensemanager.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sumago.androidipt.b2expensemanager.ExpenseDetailsActivity;
import sumago.androidipt.b2expensemanager.R;
import sumago.androidipt.b2expensemanager.interfaces.OnListItemClickListener;
import sumago.androidipt.b2expensemanager.models.Expense;

public class ExpenseListAdapter extends RecyclerView.Adapter<ExpenseListAdapter.ViewHolder> {

   ArrayList<Expense> list;
   OnListItemClickListener onListItemClickListener;

    public ExpenseListAdapter(ArrayList<Expense> list,OnListItemClickListener onListItemClickListener) {

        this.list = list;
        this.onListItemClickListener=onListItemClickListener;
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


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onListItemClickListener.onListItemClick(list.get(position));
                /*Intent intent=new Intent(holder.itemView.getContext(), ExpenseDetailsActivity.class);
                intent.putExtra("id",list.get(position).getId());
                holder.itemView.getContext().startActivity(intent);*/
            }
        });





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
