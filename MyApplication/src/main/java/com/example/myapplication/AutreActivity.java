package com.example.myapplication;

import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class AutreActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autre);

        final File mFile = new File(Environment.getExternalStorageDirectory().getPath() + "/Android/data/" + getPackageName() + "/files/" + "prenom.txt");

        LinearLayout rel = (LinearLayout) findViewById(R.id.autre);

        Button mWrite = new Button(this);

        mWrite.setText("write");
        mWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream output = openFileOutput("prenom.txt", MODE_PRIVATE);

                    output.write(new String("Jean").getBytes());

                    if(output != null) output.close();

                    if(
                            Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                            &&
                            !Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment.getExternalStorageState())
                    )
                    {
                        mFile.createNewFile();

                        output = new FileOutputStream(mFile);

                        output.write(new String("Jean").getBytes());

                        if(output != null) output.close();
                    }
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }


            }
        });

        Button mRead = new Button(this);

        mRead.setText("read");

        mRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream input = openFileInput("prenom.txt");

                    int values;

                    StringBuffer lu = new StringBuffer();

                    while((values = input.read()) != -1)
                    {
                        lu.append((char)values);
                    }

                    Toast.makeText(AutreActivity.this, "interne" + lu.toString(), Toast.LENGTH_SHORT).show();

                    if(input != null) input.close();

                    if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))
                    {
                        lu = new StringBuffer();

                        input = new FileInputStream(mFile);

                        while((values = input.read()) != -1)
                        {
                            lu.append((char) values);
                        }

                        Toast.makeText(AutreActivity.this, "externe" + lu.toString(), Toast.LENGTH_SHORT).show();

                        if(input != null) input.close();
                    }
                }
                catch(FileNotFoundException e)
                {
                    e.printStackTrace();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }


            }
        });

        rel.addView(mRead);

        rel.addView(mWrite);

    }


}
