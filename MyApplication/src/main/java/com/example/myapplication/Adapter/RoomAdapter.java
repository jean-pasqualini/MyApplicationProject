package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by adibox on 5/7/14.
 */
public class RoomAdapter extends BaseAdapter {

    private Context context;
    private int resource;
    private LayoutInflater inflater;
    private JSONArray rooms;

    public RoomAdapter (Context context, JSONArray rooms)
    {
        this.rooms = rooms;
        this.context = context;
        this.resource = R.layout.ligne;
        this.inflater = LayoutInflater.from(context);
    }

    public int getCount()
    {
        return rooms.length();
    }

    public long getItemId(int position)
    {
        return position;
    }

    public Object getItem(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View vue = inflater.inflate(R.layout.ligne, null);

        TextView title = (TextView)vue.findViewById(R.id.title);
        TextView subtitle = (TextView)vue.findViewById(R.id.artist);
        TextView date = (TextView)vue.findViewById(R.id.duration);

        try {

            JSONObject room = this.rooms.getJSONObject(position);

            title.setText(room.getString("title"));
            subtitle.setText(room.getString("publicaddress"));
            date.setText(room.getString("pricemonth") + " €");

        }
        catch (JSONException e)
        {
            title.setText("except");
        }

        return vue;
    }
}
