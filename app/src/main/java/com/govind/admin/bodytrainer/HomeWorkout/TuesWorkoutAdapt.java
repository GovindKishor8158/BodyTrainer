package com.govind.admin.bodytrainer.HomeWorkout;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.govind.admin.bodytrainer.R;


import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by Admin on 23-Mar-19.
 */

public class TuesWorkoutAdapt extends RecyclerView.Adapter<TuesWorkoutAdapt.Viewholder> {
    ArrayList<TueWorkoutbean> hq_list;

    Context listner;

    public TuesWorkoutAdapt(ArrayList<TueWorkoutbean> hq_list, Context listener) {

        this.hq_list = hq_list;
        this.listner = listener;
    }


    @Override
    public TuesWorkoutAdapt.Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tuewrkada_lay, parent, false);
        TuesWorkoutAdapt.Viewholder viewholder = new TuesWorkoutAdapt.Viewholder(view);
        return viewholder;

    }

    @Override
    public void onBindViewHolder(final TuesWorkoutAdapt.Viewholder holder, final int position) {
        holder.t1.setText(hq_list.get(position).getEs_workname());
        holder.t2.setText(hq_list.get(position).getEs_sets());
        Log.e("images",hq_list.get(position).getEs_gifimage());
        if (hq_list.get(position).getEs_gifimage() != null) {

            Glide.with(listner).load(hq_list.get(position).getEs_gifimage()).placeholder(listner.getResources().getDrawable(R.drawable.closepushup)).into(holder.iv);
        } else {
            Glide.with(listner).load(String.valueOf(listner.getResources().getDrawable(R.drawable.closepushup))).into(holder.iv);
        }


    }



    @Override
    public int getItemCount() {
        return hq_list.size();
    }



    public void setProfileArrayList(ArrayList<TueWorkoutbean> hq_list) {
        this.hq_list = hq_list;
    }


    public class Viewholder extends RecyclerView.ViewHolder {
        public TextView t1;
        public TextView t2;
        public GifImageView iv;



        public Viewholder(View itemView) {
            super(itemView);

            t1 = (TextView) itemView.findViewById(R.id.textView7);
            t2 = (TextView) itemView.findViewById(R.id.textset);
            iv = (GifImageView) itemView.findViewById(R.id.closepushgif);

        }
    }
}
