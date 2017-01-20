package com.coderchowki.featureapps;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by visha on 20-Jan-17.
 */

public class CustomTextView extends TextView {

    protected static int[] STATE_GO = { R.attr.state_go };
    protected static int[] STATE_SLOW_DOWN = { R.attr.state_slow_down };
    protected static int[] STATE_STOP = { R.attr.state_stop };

    private CustomState customState;

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void update(CustomState state ) {

        if( state != null )
            customState = state;

        if( CustomState.GO.equals( customState ) ) {
            setText( "GO" );
        } else if( CustomState.SLOW_DOWN.equals( customState ) ) {
            setText( "SLOW DOWN" );
        } else if( CustomState.STOP.equals( customState) ) {
            setText( "STOP" );
        }

        refreshDrawableState();
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        if( customState == null )
            return super.onCreateDrawableState(extraSpace);

        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if( CustomState.GO.equals( customState ) ) {
            mergeDrawableStates( drawableState, STATE_GO );
            return drawableState;
        } else if( CustomState.SLOW_DOWN.equals( customState ) ) {
            mergeDrawableStates( drawableState, STATE_SLOW_DOWN );
            return drawableState;
        } else if( CustomState.STOP.equals( customState) ) {
            mergeDrawableStates( drawableState, STATE_STOP );
            return drawableState;
        } else {
            return super.onCreateDrawableState(extraSpace);
        }
    }
}

