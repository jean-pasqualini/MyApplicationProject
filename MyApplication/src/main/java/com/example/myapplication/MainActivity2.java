package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class MainActivity2 extends ActionBarActivity {

    public final static String AGE = "MyApplication.intent.example.AGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);

        Intent i = getIntent();

        int age = i.getIntExtra(MainActivity2.AGE, 0);

        Toast.makeText(this, "age : " + age, Toast.LENGTH_SHORT).show();

        RelativeLayout rel = (RelativeLayout) findViewById(R.id.act2);

        Button btn = new Button(this);

        btn.setText("Quitter");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent test = new Intent(MainActivity2.this, MainActivity3.class);

                startActivity(test);

                /**
                Uri tel = Uri.parse("tel:0606060606");

                Intent test = new Intent(Intent.ACTION_DIAL, tel);

                startActivity(test);


                Intent result = new Intent();

                result.putExtra(MainActivity2.AGE, 10);

                setResult(RESULT_OK, result);

                finish();
                 */
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        boolean mare = preferences.getBoolean("mare", false);

        if(mare)
        {
            Toast.makeText(this, "OUI", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "NON", Toast.LENGTH_SHORT).show();
        }

        rel.addView(btn);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
