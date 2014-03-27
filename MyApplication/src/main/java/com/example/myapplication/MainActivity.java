package com.example.myapplication;

import android.app.Dialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.RepertoireAdapter;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    TextView text = null;
    private Button button = null;
    private String hist = null;

    private MainManager manager;
    private MainListener listener;

    private ListView mListSexe = null;

    private GridView mListProg = null;

    private Button mSend = null;

    private String[] mSexes = {"Homme", "Femme"};

    private String[] mLanguages = null;

    private ListView liste = null;

    private final static int ID_NORMAL_DIALOG = 0;
    private final static int ID_ENEVEREE_DIALOG = 1;

    ListView vue;

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
    public Dialog onCreateDialog(int id)
    {
        Dialog box = null;

        if(id == ID_NORMAL_DIALOG)
        {
            box = new Dialog(this);

            box.setTitle("hahahehazze");
        }

        return box;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_main);

        Resources res = getResources();

        try {


            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.tablel);

/**
            JSONObject jsonObject = this.getJson("http://www.appartoo.com/api/v1/room/lastRooms?limit=4&noci=1");

            JSONObject array = jsonObject.getJSONObject("0");

            hist = res.getString(R.string.une_string, "john", 23, "persan");

            TextView vue = (TextView) findViewById(R.id.vue);

            vue.setText(hist);

           // new HttpAsyncTask().execute("http://www.appartoo.com/api/v1/room/lastRooms?limit=4&noci=1");


            TableLayout linerLayout = (TableLayout) findViewById(R.id.tablel);

            vue = new ListView(this);

            String[][] repertoire = new String[][]{
                    {"Bill gates", "dqsdsqdsqd"},
                    {"jean paul", "E23ED2"}
            };

            List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

            HashMap<String, String> element;

            for(int i = 0; i < repertoire.length ; i++)
            {
                element = new HashMap<String, String>();

                element.put("nom", repertoire[i][0]);

                element.put("telephone", repertoire[i][1]);

                list.add(element);
            }

            final ListAdapter adapter = new SimpleAdapter(
                    this,
                    list,
                    android.R.layout.simple_list_item_single_choice,
                    new String[] {"nom", "telephone"},
                    new int[] { android.R.id.text1, android.R.id.text2 }
            );

            vue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(MainActivity.this, "vous avez cliquer sur le ", Toast.LENGTH_LONG).show();
                }
            });

            vue.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

            vue.setAdapter(adapter);

            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

            adapter1.add("Pommes");

            int position = adapter1.getPosition("Pommes");

            Toast.makeText(this, "Les " + adapter1.getItem(position) + " se trouve à la position" + position, Toast.LENGTH_LONG).show();

            adapter1.remove("Pommes");


            mListSexe = (ListView) findViewById(R.id.listSexe);

            mListProg = (GridView) findViewById(R.id.listProg);

            mSend = (Button) findViewById(R.id.send);

            mLanguages = new String[]{"C", "Java", "COBOL", "Perl", "PHP"};

            mListSexe.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, mSexes));

            mListProg.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, mLanguages));

            mListProg.setItemChecked(1, true);

            mSend.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v)
                {
                    Toast.makeText(MainActivity.this, "Merci", Toast.LENGTH_SHORT).show();

                    mListSexe.setChoiceMode(ListView.CHOICE_MODE_NONE);

                    mListProg.setChoiceMode(ListView.CHOICE_MODE_NONE);

                    mListSexe.setAdapter(mListSexe.getAdapter());
                }
            });


        liste = (ListView) new ListView(this);

        List<String> example = new ArrayList<String>();

        for(int i =  1; i <= 10; i++)
        {
                example.add("Element " + i);
         }

         ArrayAdapter<String> adapter = new RepertoireAdapter(this, example);

       // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

         liste.setAdapter(adapter);

         linearLayout.addView(liste);
 */
          //  linearLayout.addView(vue);

         Button un = new Button(this);

         un.setText("un bouton");

         un.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 showDialog(ID_NORMAL_DIALOG);
             }
         });

         linearLayout.addView(un);

        }
        catch(Exception e)
        {
            e.printStackTrace();
           // Log.d("JSON", e.getLocalizedMessage());
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

                LinearLayout linerLayout = (LinearLayout) findViewById(R.id.tablel);

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
