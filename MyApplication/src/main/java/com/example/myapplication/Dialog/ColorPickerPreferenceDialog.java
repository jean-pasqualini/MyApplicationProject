package com.example.myapplication.Dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.preference.DialogPreference;
import android.util.AttributeSet;

import com.example.myapplication.View.ColorPickerView;

/**
 * Created by john on 12/04/2014.
 */
public class ColorPickerPreferenceDialog extends DialogPreference implements ColorPickerView.OnColorChangedListener {

    private int mColor;

    public ColorPickerPreferenceDialog(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    protected void onDialogClosed(boolean positiveResult)
    {
        if(positiveResult)
        {
            persistInt(mColor);
        }

        super.onDialogClosed(positiveResult);
    }


    protected void onPrepareDialogBuilder(AlertDialog.Builder builder) {
        int oldColor = getSharedPreferences().getInt(getKey(), Color.BLACK);

        builder.setView(new ColorPickerView(getContext(), this, oldColor));

        super.onPrepareDialogBuilder(builder);
    }

    public void colorChanged(int color)
    {
        mColor = color;
    }
}
