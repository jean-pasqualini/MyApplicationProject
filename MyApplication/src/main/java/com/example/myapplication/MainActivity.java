package com.example.myapplication;

import android.content.res.Resources;
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
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Listener.MainListener;
import com.example.myapplication.Manager.MainManager;

import org.w3c.dom.Text;

public class MainActivity extends ActionBarActivity {

    private TextView text = null;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.blocnote);

        Resources res = getResources();


        Animation animation = AnimationUtils.loadAnimation(this, R.anim.my);

        LayoutAnimationController animationLayout = AnimationUtils.loadLayoutAnimation(this, R.anim.layoutmy);


    }


}
