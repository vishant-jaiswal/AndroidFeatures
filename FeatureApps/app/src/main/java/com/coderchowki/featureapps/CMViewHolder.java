package com.coderchowki.featureapps;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Vishant on 11-04-2016.
 */
public static class CMViewHolder extends RecyclerView.ViewHolder {
    TextView messageText;
    TextView nameText;

    public CMViewHolder(View itemView) {
        super(itemView);
        nameText = (TextView)itemView.findViewById(android.R.id.text1);
        messageText = (TextView) itemView.findViewById(android.R.id.text2);
    }
}
