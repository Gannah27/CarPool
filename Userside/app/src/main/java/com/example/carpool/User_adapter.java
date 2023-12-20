package com.example.carpool;

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

public class User_adapter extends RecyclerView.Adapter<User_adapter.MyViewHolder> {
    Context context;
    List<User> mitemArrayList=new ArrayList<>();
    private ItemClicklistener clicklistener;

    /*public Myadapter(Context context,List<schedule> mitemArrayList, ItemClicklistener clicklistener) {
        this.context = context;
        this.clicklistener=clicklistener;
        this.mitemArrayList=mitemArrayList;
        Log.d("SET6", "adapter null");
    }*/
    public User_adapter() {


        Log.d("SET6", "adapter null");
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rout_item,parent,false);
        Log.d("Viewholder", v.toString());
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        User item = mitemArrayList.get(position);
        holder.txt_dropT.setText(item.getDate_s());
        holder.txt_drop.setText(item.getDropP());
        holder.txt_pickT.setText(item.getCarnumber());
        holder.txt_pick.setText(item.getPickP());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clicklistener.onItemClick(mitemArrayList.get(position));
            }
        });

    }

    public void setMitemArrayList(List<User> mitemArrayList) {
        this.mitemArrayList = mitemArrayList;
        notifyDataSetChanged();


    }

    @Override
    public int getItemCount() {
        return mitemArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        TextView txt_pickT,txt_pick,txt_dropT,txt_drop;
        public MyViewHolder( View itemView) {
            super(itemView);
            txt_pickT=itemView.findViewById(R.id.textView05);
            txt_pick=itemView.findViewById(R.id.textView04);
            txt_dropT =itemView.findViewById(R.id.textView07);
            txt_drop=itemView.findViewById(R.id.textView06);
        }
    }

    public interface ItemClicklistener{
        public void onItemClick(User item);
    }

}