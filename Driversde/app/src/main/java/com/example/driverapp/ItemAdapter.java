package com.example.driverapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {
    Context context;
    List<Drivers> mitemArrayList=new ArrayList<>();
    private ItemClicklistener clicklistener;

    /*public Myadapter(Context context,List<schedule> mitemArrayList, ItemClicklistener clicklistener) {
        this.context = context;
        this.clicklistener=clicklistener;
        this.mitemArrayList=mitemArrayList;
        Log.d("SET6", "adapter null");
    }*/
    public ItemAdapter(ItemClicklistener clicklistener) {
        this.clicklistener=clicklistener;

        Log.d("SET6", "adapter null");
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        Log.d("Viewholder", v.toString());
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Drivers item = mitemArrayList.get(position);
        holder.txt_Date.setText(item.getDate_s());
        holder.txt_drop.setText(item.getDropP());
        holder.txt_Carname.setText(item.getCarnumber());
        holder.txt_pick.setText(item.getPickP());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clicklistener.onItemClick(mitemArrayList.get(position));
            }
        });

    }

    public void setMitemArrayList(List<Drivers> mitemArrayList) {
        this.mitemArrayList = mitemArrayList;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return mitemArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txt_Carname,txt_pick, txt_Date,txt_drop;
        public MyViewHolder( View itemView) {
            super(itemView);
            txt_Carname=itemView.findViewById(R.id.textView22);
            txt_pick=itemView.findViewById(R.id.textView21);
            txt_Date =itemView.findViewById(R.id.textView24);
            txt_drop=itemView.findViewById(R.id.textView23);
        }
    }

    public interface ItemClicklistener{
        public void onItemClick(Drivers item);
    }

}