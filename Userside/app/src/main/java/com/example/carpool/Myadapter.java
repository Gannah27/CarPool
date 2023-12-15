package com.example.carpool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Myadapter extends RecyclerView.Adapter<Myadapter.MyViewHolder>{
    Context context;
    ArrayList<Item> itemArrayList;
    private ItemClicklistener clicklistener;

    public Myadapter(Context context, ArrayList<Item> itemArrayList,ItemClicklistener clicklistener) {
        this.context = context;
        this.itemArrayList = itemArrayList;
        this.clicklistener=clicklistener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.rout_item,parent,false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Item item = itemArrayList.get(position);
        holder.txt_dropT.setText(item.dropoffT);
        holder.txt_drop.setText(item.dropoffP);
        holder.txt_pickT.setText(item.pickupT);
        holder.txt_pick.setText(item.pickupP);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clicklistener.onItemClick(itemArrayList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txt_pickT,txt_pick,txt_dropT,txt_drop;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_pickT=itemView.findViewById(R.id.textView05);
            txt_pick=itemView.findViewById(R.id.textView04);
            txt_dropT =itemView.findViewById(R.id.textView07);
            txt_drop=itemView.findViewById(R.id.textView06);
        }
    }

        public interface ItemClicklistener{
          public void onItemClick(Item item);
        }

}