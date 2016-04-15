package com.coderchowki.featureapps;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Vishant on 15-04-2016.
 */
//TODO:5> Create an adaptor for Recycler View
public class RV_Adaptor extends RecyclerView.Adapter<RV_Adaptor.RV_ViewHolder> {

    private LayoutInflater mLayoutInflater;
    ArrayList<String> arrayList = new ArrayList();

    public RV_Adaptor(Context context) {
        generate_arraylist();
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RV_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.rv_single_row,parent,false);
        RV_ViewHolder rv_viewHolder = new RV_ViewHolder(view);
        return rv_viewHolder;
    }

    public void generate_arraylist(){
        for(int i =0 ; i <100 ; i++){
            arrayList.add("hello" + i);
        }
    }

    @Override
    public void onBindViewHolder(RV_ViewHolder holder, int position) {
        holder.mtv_what.setText(arrayList.get(position));

    }

    @Override
    public int getItemCount() {
        return 99;
    }

    public static class RV_ViewHolder extends RecyclerView.ViewHolder{

        //TODO:6> initialize your view items here
        TextView mtv_what;

        public RV_ViewHolder(View itemView) {
            super(itemView);
            mtv_what = (TextView) itemView.findViewById(R.id.tv_what);
        }
    }
}
