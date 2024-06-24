package sumago.androidipt.b2expensemanager.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sumago.androidipt.b2expensemanager.R;
import sumago.androidipt.b2expensemanager.models.ExpenseReport;

public class AnalyticsAdapter  extends RecyclerView.Adapter<AnalyticsAdapter.ViewHolder> {

    ArrayList<ExpenseReport> list;

    public AnalyticsAdapter(ArrayList<ExpenseReport> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stats, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvCatName.setText(list.get(position).getCategoryName());
        holder.tvMaxAmount.setText("" + list.get(position).getMaxAmount());
        holder.tvMinAmount.setText("" + list.get(position).getMinAmount());
        holder.tvTotal.setText("" + list.get(position).getTotalAmount());
        holder.tvAvg.setText("" + list.get(position).getAverageAmount());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvCatName;
        TextView tvMinAmount;
        TextView tvMaxAmount;
        TextView tvAvg;
        TextView tvTotal;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCatName = itemView.findViewById(R.id.tvCatName);
            tvMinAmount = itemView.findViewById(R.id.tvMinAmount);
            tvMaxAmount = itemView.findViewById(R.id.tvMaxAmount);
            tvAvg = itemView.findViewById(R.id.tvAvg);
            tvTotal = itemView.findViewById(R.id.tvTotal);
        }
    }
}
