package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.List;

/**
 * Created by adibox on 3/27/14.
 */
public class RepertoireAdapter extends ArrayAdapter {

    private Context context;
    private int resource;
    private LayoutInflater inflater;

    public RepertoireAdapter (Context context, List<String> values)
    {
        super(context, R.layout.ligne, values);
        this.context = context;
        this.resource = R.layout.ligne;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        TextView vue = (TextView) inflater.inflate(R.layout.ligne, null);

        String item = (String) getItem(position);

        vue.setText(item);

        return vue;
    }

}
