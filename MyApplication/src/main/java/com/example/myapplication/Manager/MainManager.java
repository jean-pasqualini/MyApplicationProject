package com.example.myapplication.Manager;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.myapplication.Exception.HumanException;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

/**
 * Created by john on 24/12/13.
 */
public class MainManager {

    private MainActivity activity;

    private Button sendViewComponent;
    private Button razViewComponent;

    private EditText poidsViewComponent;
    private EditText tailleViewComponent;

    private RadioGroup groupViewComponent;

    private TextView resultViewComponent;

    private CheckBox megaViewComponent;

    public MainManager(MainActivity activity) {
        this.activity = activity;

        this.Configure();
    }

    public void Configure()
    {
        sendViewComponent = (Button) this.getActivity().findViewById(R.id.calcul);

        razViewComponent = (Button) this.getActivity().findViewById(R.id.raz);

        poidsViewComponent = (EditText) this.getActivity().findViewById(R.id.poids);

        tailleViewComponent = (EditText) this.getActivity().findViewById(R.id.taille);

        groupViewComponent = (RadioGroup) this.getActivity().findViewById(R.id.group);

        resultViewComponent = (TextView) this.getActivity().findViewById(R.id.result);

        megaViewComponent = (CheckBox) this.getActivity().findViewById(R.id.mega);
    }

    public void Calcul() throws HumanException
    {
        float taille = this.getTaille();

        float poids = this.getPoids();

        if(taille <= 0)
        {
            throw new HumanException("hého tu est un minipouce");
        }

        if(groupViewComponent.getCheckedRadioButtonId() == R.id.radio2)
        {
            taille = taille / 100;
        }

        taille = (float)Math.pow(taille, 2);

        float imc = poids / taille;

        this.setResult("Votre IMC est de " + String.valueOf(imc));


    }

    public float getTaille()
    {
        return Float.valueOf(this.getTailleViewComponent().getText().toString());
    }

    public void setResult(String result)
    {
        this.getResultViewComponent().setText(result);
    }

    public float getPoids()
    {
        return Float.valueOf(this.getPoidsViewComponent().getText().toString());
    }

    public MainActivity getActivity() {
        return activity;
    }

    public Button getSendViewComponent() {
        return sendViewComponent;
    }

    public Button getRazViewComponent() {
        return razViewComponent;
    }

    public EditText getPoidsViewComponent() {
        return poidsViewComponent;
    }

    public EditText getTailleViewComponent() {
        return tailleViewComponent;
    }

    public RadioGroup getGroupViewComponent() {
        return groupViewComponent;
    }

    public TextView getResultViewComponent() {
        return resultViewComponent;
    }

    public CheckBox getMegaViewComponent() {
        return megaViewComponent;
    }
}
