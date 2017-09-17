package com.example.user.quarryapp.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.quarryapp.Model.GetProduct;
import com.example.user.quarryapp.R;
import com.example.user.quarryapp.UserMainActivity;

import java.util.List;

/**
 * Created by User on 7/6/2017.
 */

public class RecyclerProductAdapter extends RecyclerView.Adapter<RecyclerProductAdapter.ViewHolder> {
    Context context;
    List<GetProduct> getProduct;

    public RecyclerProductAdapter(List<GetProduct> getProduct, Context context){
        super();
        this.getProduct=getProduct;
        this.context=context;
    }


    @Override
    public RecyclerProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_products, parent, false);

        RecyclerProductAdapter.ViewHolder viewHolder = new RecyclerProductAdapter.ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerProductAdapter.ViewHolder holder, int position) {

        GetProduct getFareAdapter1 = getProduct.get(position);

        holder.name.setText(getFareAdapter1.getName());
        holder.price.setText(getFareAdapter1.getPrice());

        final String  id=getFareAdapter1.getId();
        final String  p_name=getFareAdapter1.getName();
        final String  p_price=getFareAdapter1.getPrice();

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Toast.makeText(context, "u clicked " +id, Toast.LENGTH_SHORT).show();
                ((UserMainActivity) context).goTOSell(id,p_price,p_name);
            }
        });

    }

    //get counts
    @Override
    public int getItemCount() {

        return getProduct.size();
    }


    //data holders

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name,price;
        public  CardView card;


        public ViewHolder(View itemView) {

            super(itemView);

            name = (TextView) itemView.findViewById(R.id.sellProductName);
            price = (TextView) itemView.findViewById(R.id.sellProductPrice);
            card = (CardView) itemView.findViewById(R.id.listOfSellProduct);

        }
    }



}
