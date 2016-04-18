package com.coderchowki.featureapps.Widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coderchowki.featureapps.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Vishant on 18-04-2016.
 */
public class DatePickerView extends LinearLayout{
    private Calendar mCalendar;
    private TextView mTextDate;
    private TextView mTextMonth;
    private TextView mTextYear;
    private SimpleDateFormat simpleDateFormat;

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



    private void init(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.date_picker_view,this);
        mCalendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("MMM");
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTextDate = (TextView) this.findViewById(R.id.tv_date);
        mTextMonth = (TextView) this.findViewById(R.id.tv_month);
        mTextYear = (TextView) this.findViewById(R.id.tv_year);
        int date = mCalendar.get(Calendar.DATE);
        int month = mCalendar.get(Calendar.MONTH);
        int year = mCalendar.get(Calendar.YEAR);
        update(date,month,year,0,0,0);
    }

    private void update(int date, int month, int year, int hour, int minute, int sec) {
        mCalendar.set(Calendar.DATE,date);
        mCalendar.set(Calendar.MONTH,month);
        mCalendar.set(Calendar.YEAR,year);
        mCalendar.set(Calendar.HOUR,hour);
        mCalendar.set(Calendar.MINUTE,minute);
        mCalendar.set(Calendar.SECOND,sec);
        mTextDate.setText(date+"");
        mTextMonth.setText(simpleDateFormat.format(mCalendar.getTime()));
        mTextYear.setText(year +"");
    }
}
