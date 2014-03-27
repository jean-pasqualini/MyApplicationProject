package com.example.myapplication;

import android.content.Context;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by adibox on 3/27/14.
 */
public class ToHtmlView extends LinearLayout {
    private EditText mEdit;

    private TextView mText;

    public ToHtmlView(Context context)
    {
        super(context);
        init();
    }

    public ToHtmlView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public void init()
    {
        int wrap = LayoutParams.WRAP_CONTENT;

        int fill = LayoutParams.FILL_PARENT;

        setOrientation(LinearLayout.VERTICAL);

        setLayoutParams(new LayoutParams(fill, fill));

        mEdit = new EditText(getContext());

        mEdit.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EDIT_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);

        mEdit.setMaxLines(10);

        mEdit.setHorizontallyScrolling(false);

        mEdit.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                mText.setText(Html.fromHtml(charSequence.toString()));
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mText = new TextView(getContext());

        mText.setText("");

        addView(mEdit, new LayoutParams(fill, wrap));
        addView(mText, new LayoutParams(fill, wrap));
    }
}
