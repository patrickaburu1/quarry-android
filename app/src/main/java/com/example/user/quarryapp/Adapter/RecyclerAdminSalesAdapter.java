package com.example.user.quarryapp.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.quarryapp.Model.GetSales;
import com.example.user.quarryapp.R;

import java.util.List;

/**
 * Created by User on 7/6/2017.
 */

public class RecyclerAdminSalesAdapter extends RecyclerView.Adapter<RecyclerAdminSalesAdapter.ViewHolder> {
    Context context;
    List<GetSales> getSales;

    public RecyclerAdminSalesAdapter(List<GetSales> getSales, Context context){
        super();
        this.getSales=getSales;
        this.context=context;
    }


    @Override
    public RecyclerAdminSalesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_admin_sales, parent, false);

        RecyclerAdminSalesAdapter.ViewHolder viewHolder = new RecyclerAdminSalesAdapter.ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdminSalesAdapter.ViewHolder holder, int position) {

        GetSales getFareAdapter1 = getSales.get(position);

        holder.product.setText(getFareAdapter1.getProduct());
        holder.total.setText(getFareAdapter1.getTotal());
        holder.quantity.setText(getFareAdapter1.getQuantity());


        holder.adminSalesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(context, "u clicked " +id, Toast.LENGTH_SHORT).show();
//                ((UserMainActivity) context).goTOSell(id);
            }
        });

    }

    //get counts
    @Override
    public int getItemCount() {

        return getSales.size();
    }


    //data holders

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView product,total,quantity;
        public CardView adminSalesCard;


        public ViewHolder(View itemView) {

            super(itemView);

            total = (TextView) itemView.findViewById(R.id.adminSalesTotal);
            product = (TextView) itemView.findViewById(R.id.adminSalesProduct);
            quantity = (TextView) itemView.findViewById(R.id.adminSalesQuanity);
            adminSalesCard = (CardView) itemView.findViewById(R.id.adminSalesCard);

        }
    }



}
