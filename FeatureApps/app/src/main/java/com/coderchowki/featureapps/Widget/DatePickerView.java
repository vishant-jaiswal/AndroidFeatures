package com.coderchowki.featureapps.Widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.coderchowki.featureapps.R;

/**
 * Created by Vishant on 18-04-2016.
 */
public class DatePickerView extends LinearLayout{
    public DatePickerView(Context context) {
        super(context);
    }

    public DatePickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DatePickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public DatePickerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.date_picker_view,this);
    }
}
