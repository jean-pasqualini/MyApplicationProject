package com.example.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by adibox on 3/27/14.
 */
public class ColorButton extends Button {
    private TypedArray mCouleurs;

    private int position = 0;

    private Rect mRect;

    private Paint mPainter = new Paint(Paint.ANTI_ALIAS_FLAG);

    public ColorButton(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init();
    }

    public ColorButton(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public ColorButton(Context context)
    {
        super(context);
        init();
    }

    private void init()
    {
        Resources res = getResources();

        mCouleurs = res.obtainTypedArray(R.array.colors);

        setText("Changer de coulour");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom)
    {
        if(changed)
        {
            mRect = new Rect(
                    Math.round(0.5f * getWidth() - 50),
                    Math.round(0.75f * getHeight() - 50),
                    Math.round(0.5f * getWidth() + 50),
                    Math.round(0.75f * getHeight() + 50)
                        );
        }

        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            position ++;

            position %= mCouleurs.length();

            mPainter.setColor(mCouleurs.getColor(position, -1));

            invalidate();
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        canvas.drawRect(mRect, mPainter);

        super.onDraw(canvas);
    }


}
