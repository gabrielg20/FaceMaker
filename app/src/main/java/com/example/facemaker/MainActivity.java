/*
    Author: Austin Gabriel-Garces
 */
package com.example.facemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    public static MainActivity thisActivity;

    SeekBar redVal, greenVal, blueVal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize the face surface view
        FaceView ourFace = (FaceView) findViewById(R.id.surfaceView);
        final Face finalFace = ourFace.getMyFace();

        FaceController guiFaceController = new FaceController(ourFace);

        //Initialize the spinner
        Spinner hairSpinner = (Spinner)findViewById(R.id.HairSelect);
        hairSpinner.setOnItemSelectedListener(guiFaceController);

        //Initialize the seek bars
        redVal = (SeekBar) findViewById(R.id.RedVal);
        greenVal = (SeekBar) findViewById(R.id.GreenVal);
        blueVal = (SeekBar) findViewById(R.id.BlueVal);

        redVal.setOnSeekBarChangeListener(guiFaceController);
        greenVal.setOnSeekBarChangeListener(guiFaceController);
        blueVal.setOnSeekBarChangeListener(guiFaceController);

        //Initializing the random button
        Button randButton = (Button) findViewById(R.id.RandomFace);
        randButton.setOnClickListener(guiFaceController);

        //Initialize the radio buttons
        RadioGroup modColorRG = (RadioGroup) findViewById(R.id.ModColorSchm);
        modColorRG.clearCheck();
        modColorRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch(checkedId){
                    case -1:
                        break;

                    case R.id.skinColorChange:
                        finalFace.skinChoice = true;
                        finalFace.eyeChoice = false;
                        finalFace.hairChoice = false;
                        break;

                    case R.id.eyeColorChange:
                        finalFace.skinChoice = false;
                        finalFace.eyeChoice = true;
                        finalFace.hairChoice = false;
                        break;

                    case R.id.hairColorChange:
                        finalFace.skinChoice = false;
                        finalFace.eyeChoice = false;
                        finalFace.hairChoice = true;
                        break;
                }
            }
        });
        thisActivity = this;
    }
}
