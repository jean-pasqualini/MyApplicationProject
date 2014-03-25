package com.example.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Listener.MainListener;
import com.example.myapplication.Manager.MainManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity extends ActionBarActivity {

    TextView text = null;
    private Button button = null;
    private String hist = null;

    private MainManager manager;
    private MainListener listener;

    private View.OnTouchListener buttonListener = new View.OnTouchListener()
    {

        @Override
        public boolean onTouch(View view, MotionEvent motion) {
            Button button = (Button) view;

            int largeur = button.getWidth();

            int hauteur = button.getHeight();

            float x = motion.getX();

            float y = motion.getY();

         //   button.setTextSize(Math.abs((x - largeur / 2)) + Math.abs(y - hauteur/2));


       //     Toast.makeText(MainActivity.this, "je suis le maitre du monde", Toast.LENGTH_SHORT).show();
            // TODO Auto-generated method stub
            return true;
        }

    };

    public static String readJSON(String url)
    {
        StringBuilder stringBuilder = new StringBuilder();

        HttpClient httpClient = new DefaultHttpClient();

        HttpGet httpGet = new HttpGet(url);

        Log.d("JSON", "RRRRRRRRR");

        try {
            HttpResponse response = httpClient.execute(httpGet);

            Log.d("JSON", "AAAAAAAAA");

            StatusLine statusLine = response.getStatusLine();

            Log.d("JSON", "BBBBBBBBBB");

            int statusCode = statusLine.getStatusCode();

            Log.d("JSON", "CCCCCCCCCC");


            Log.d("JSON", "UN PAS");

            if(statusCode == 200)
            {
                Log.d("JSON", "UN 200");

                HttpEntity entity = response.getEntity();

                InputStream inputStream = entity.getContent();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream)
                );

                String line;

                while((line = reader.readLine()) != null)
                {
                    stringBuilder.append(line);
                }

                inputStream.close();
            }
            else
            {
                Log.d("JSON", "Failed to download file");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();

            Log.d("JSON", "ici " + e.toString());
        }

        return stringBuilder.toString();

    }

    public static JSONObject getJson(String url)
    {
        String result = readJSON(url);

        try {
            JSONObject jsonObject = new JSONObject(result);

            return jsonObject;
        }
        catch (Exception e)
        {
            Log.d("JSON", "pasla " + e.getLocalizedMessage());
        }

        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.blocnote);

        Resources res = getResources();

        try {
            text = (TextView) findViewById(R.id.vue);

/**
            JSONObject jsonObject = this.getJson("http://www.appartoo.com/api/v1/room/lastRooms?limit=4&noci=1");

            JSONObject array = jsonObject.getJSONObject("0");

            hist = res.getString(R.string.une_string, "john", 23, "persan");

            TextView vue = (TextView) findViewById(R.id.vue);

            vue.setText(hist);
*/
            new HttpAsyncTask().execute("http://www.appartoo.com/api/v1/room/lastRooms?limit=4&noci=1");

        }
        catch(Exception e)
        {
            Log.d("JSON", e.getLocalizedMessage());
        }
/**
        this.setContentView(an);

        this.setContentView(R.layout.fragment_main);
*/
 /**
        this.manager = new MainManager(this);
        this.listener = new MainListener(this.manager);
*/

    }

    private LinearLayout createAnnounce(JSONObject object) throws JSONException
    {
        LinearLayout linearLayout = new LinearLayout(this);

        TextView descript = new TextView(MainActivity.this);

        descript.setText(object.getString("title"));

        ImageView image = new ImageView(this);

        new DownloadImageTask(image).execute("http://www.appartoo.com/" + object.getString("picture"));

        linearLayout.addView(descript);

        linearLayout.addView(image);

        return linearLayout;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage)
        {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls)
        {
            String urldisplay = urls[0];

            Bitmap mIcon11 = null;

            try {
                InputStream in = new URL(urldisplay).openStream();

                mIcon11 = BitmapFactory.decodeStream(in);
            }
            catch(Exception e)
            {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            return mIcon11;
        }

        protected void onPostExecute(Bitmap result)
        {
            bmImage.setImageBitmap(result);
        }
    }


    private class HttpAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls)
        {
            return readJSON(urls[0]);
        }

        @Override
        protected void onPostExecute(String result)
        {
            Toast.makeText(getBaseContext(), "Received! ", Toast.LENGTH_LONG).show();

            //text.setText(result);

            try {

                JSONArray jsonObject = new JSONArray(result);

                TableLayout linerLayout = (TableLayout) findViewById(R.id.tablel);

                for(int i = 0, size = jsonObject.length(); i < size; i++)
                {
                    JSONObject array = jsonObject.getJSONObject(i);

                    linerLayout.addView(MainActivity.this.createAnnounce(array));
                }
            }
            catch(Exception e)
            {
                Log.d("JSON", ">>" + e.getLocalizedMessage());
            }
        }
    }

}
