package com.example.myapplication.Listener;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.Exception.HumanException;
import com.example.myapplication.Manager.MainManager;

/**
 * Created by john on 24/12/13.
 */
public class MainListener {

    private MainManager manager;

    public MainListener(MainManager manager)
    {
        this.manager = manager;

        this.Configure();
    }

    public void Configure()
    {
        this.getManager().getSendViewComponent().setOnClickListener(this.OnClickSendButton);

        this.getManager().getRazViewComponent().setOnClickListener(this.OnClickSendButton);

        this.getManager().getTailleViewComponent().addTextChangedListener(this.textWatcher);

        this.getManager().getPoidsViewComponent().addTextChangedListener(this.textWatcher);

        this.getManager().getMegaViewComponent().setOnClickListener(this.OnClickSendButton);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private View.OnClickListener OnClickSendButton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button = (Button) view;

            try {

                MainListener.this.getManager().Calcul();

            }
            catch (HumanException e)
            {
               Toast.makeText(MainListener.this.getManager().getActivity(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }
    };

    public MainManager getManager() {
        return manager;
    }
}
