package com.coderchowki.featureapps.Widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coderchowki.featureapps.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Vishant on 18-04-2016.
 */
public class DatePickerView extends LinearLayout implements View.OnTouchListener {
    private static final String TAG = "dpv";
    public static final int LEFT = 0;
    public static final int TOP = 1;
    public static final int RIGHT = 2;
    public static final int BOTTOM = 3;
    private static final int MESSAGE_WHAT = 2016;
    private Calendar mCalendar;
    private TextView mTextDate;
    private TextView mTextMonth;
    private TextView mTextYear;
    private SimpleDateFormat simpleDateFormat;
    private boolean mIncrement;
    private boolean mDecrement;
    private int mActiveTextViewId;
    public static final int DELAY = 250;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
           // Log.i(TAG,"message recieved by handler");
            if(mIncrement){
                increment(mActiveTextViewId);
            }
            if(mDecrement){
                decrement(mActiveTextViewId);
            }
            if(mIncrement || mDecrement){
                mHandler.sendEmptyMessageDelayed(MESSAGE_WHAT,DELAY);
            }

            return true;
        }
    });

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


    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.date_picker_view, this);
        mCalendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("MMM");
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTextDate = (TextView) this.findViewById(R.id.tv_date);
        mTextMonth = (TextView) this.findViewById(R.id.tv_month);
        mTextYear = (TextView) this.findViewById(R.id.tv_year);
        mTextDate.setOnTouchListener(this);
        mTextMonth.setOnTouchListener(this);
        mTextYear.setOnTouchListener(this);
        int date = mCalendar.get(Calendar.DATE);
        int month = mCalendar.get(Calendar.MONTH);
        int year = mCalendar.get(Calendar.YEAR);
        update(date, month, year, 0, 0, 0);
    }

    private void update(int date, int month, int year, int hour, int minute, int sec) {
        mCalendar.set(Calendar.DATE, date);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.HOUR, hour);
        mCalendar.set(Calendar.MINUTE, minute);
        mCalendar.set(Calendar.SECOND, sec);
        mTextDate.setText(date + "");
        mTextMonth.setText(simpleDateFormat.format(mCalendar.getTime()));
        mTextYear.setText(year + "");
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.tv_date:
                processEventFor(mTextDate, event);
                break;
            case R.id.tv_month:
                processEventFor(mTextMonth, event);
                break;
            case R.id.tv_year:
                processEventFor(mTextYear, event);
                break;

        }

        return true;
    }

    private void processEventFor(TextView textView, MotionEvent event) {
        Drawable[] drawables = textView.getCompoundDrawables();
        if (hasDrawableTop(drawables) && hasDrawableBottom(drawables)) {
            Rect topBound = drawables[TOP].getBounds();
            Rect bottomBound = drawables[BOTTOM].getBounds();
            float x = event.getX();
            float y = event.getY();

            mActiveTextViewId = textView.getId();
            if (topDrawableHit(textView, topBound.height(), x, y)) {
              //  Log.i(TAG, "in processEventFor topDrawableHit hit");
                if(isActionDown(event)) {
                    mIncrement = true;
                    increment(textView.getId());
                    mHandler.removeMessages(MESSAGE_WHAT);
                    mHandler.sendEmptyMessageDelayed(MESSAGE_WHAT,DELAY);
                    toggledrawable(textView,true);
                }
                if(isActionUPorCancel(event)){
                    mIncrement = false;
                    toggledrawable(textView,false);
                }

            } else if (bottomDrawableHit(textView, bottomBound.height(), x, y)) {
              //  Log.i(TAG, "in processEventFor bottomDrawableHit hit");
                if(isActionDown(event)) {
                    mDecrement = true;
                    decrement(textView.getId());
                    mHandler.removeMessages(MESSAGE_WHAT);
                    mHandler.sendEmptyMessageDelayed(MESSAGE_WHAT,DELAY);
                    toggledrawable(textView,true);
                }
                if(isActionUPorCancel(event)){
                    mDecrement = false;
                    toggledrawable(textView,false);
                }
            } else {
                mDecrement = false;
                mIncrement = false;
                toggledrawable(textView,false);
            }
        }

    }

    private void decrement(int id) {
        switch (id) {
            case R.id.tv_date:
                mCalendar.add(Calendar.DATE, -1);
                break;
            case R.id.tv_month:
                mCalendar.add(Calendar.MONTH, -1);
                break;
            case R.id.tv_year:
                mCalendar.add(Calendar.YEAR, -1);
                break;
        }
        set(mCalendar);
    }

    private void set(Calendar calendar) {
        int date = calendar.get(Calendar.DATE);
        // int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        mTextDate.setText(date + "");
        mTextMonth.setText(simpleDateFormat.format(calendar.getTime()));
        mTextYear.setText(year + "");
    }

    private void increment(int id) {
        switch (id) {
            case R.id.tv_date:
                mCalendar.add(Calendar.DATE, 1);
                break;
            case R.id.tv_month:
                mCalendar.add(Calendar.MONTH, 1);
                break;
            case R.id.tv_year:
                mCalendar.add(Calendar.YEAR, 1);
                break;
        }
        set(mCalendar);
    }

    private boolean isActionDown(MotionEvent event) {
        return event.getAction() == MotionEvent.ACTION_DOWN;
    }

    private boolean isActionUPorCancel(MotionEvent event) {
        return event.getAction() == MotionEvent.ACTION_UP ||
                event.getAction() == MotionEvent.ACTION_CANCEL;
    }

    private boolean bottomDrawableHit(TextView textView, int drawableHeight, float x, float y) {
        int xmin = textView.getPaddingLeft();
        int xmax = textView.getWidth() - textView.getPaddingRight();
        int ymax = textView.getHeight() - textView.getPaddingBottom();
        int ymin = ymax - drawableHeight;
        return x > xmin && x < xmax && y > ymin && y < ymax;

    }

    private boolean topDrawableHit(TextView textView, int drawableHeight, float x, float y) {
        int xmin = textView.getPaddingLeft();
        int xmax = textView.getWidth() - textView.getPaddingRight();
        int ymin = textView.getPaddingTop();
        int ymax = textView.getPaddingTop() + drawableHeight;
        return x > xmin && x < xmax && y > ymin && y < ymax;

    }

    private boolean hasDrawableTop(Drawable[] drawables) {
        return drawables[TOP] != null;

    }

    private boolean hasDrawableBottom(Drawable[] drawables) {
        return drawables[BOTTOM] != null;
    }


    private void toggledrawable(TextView textView ,boolean pressed){
        if(pressed){
            if(mIncrement){
                textView.setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.up_pressed,0,R.drawable.down_normal);
            }
            if(mDecrement){
                Log.i(TAG,"toggledrawable down pressed");
                textView.setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.up_normal,0,R.drawable.down_pressed);
            }

        }
        else{
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.up_normal,0,R.drawable.down_normal);
        }
    }
}

