package com.example.myapplication;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by john on 12/04/2014.
 */
public class ExploreurPreference extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.exp_prefenrence);
    }
}
