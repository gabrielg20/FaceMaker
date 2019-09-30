/*
    Author: Austin Gabriel-Garces
    Description: Class that handles all touch events from the user. Changes face aspects as necessary
 */
package com.example.facemaker;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SeekBar;

import java.util.Random;

public class FaceController extends Activity implements /*RadioGroup.OnCheckedChangeListener,*/ AdapterView.OnItemSelectedListener, android.widget.SeekBar.OnSeekBarChangeListener, View.OnClickListener{

    private FaceView myFaceView;
    private Face ourFace;

    private int red, green, blue;

    //Constructor
    public FaceController(FaceView face){
        myFaceView = face;
        ourFace = myFaceView.getMyFace();
    }

    //Handling the random button click evenet
    public void onClick(View v){
        ourFace.randMode = true;
        myFaceView.invalidate();
    }

    //Handling the Spinner component
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
        String hairChoice = (String)parent.getItemAtPosition(pos);
        Random randNum = new Random();
        if (!ourFace.randMode) {
            if (hairChoice.equals("Eye Lashes")) {
                ourFace.hairStyle = 1;
            } else if (hairChoice.equals("Mustache")) {
                ourFace.hairStyle = 2;
            } else if (hairChoice.equals("Goatee")) {
                ourFace.hairStyle = 3;
            }
        }
        else {
            ourFace.hairStyle = randNum.nextInt(3);
        }
        myFaceView.invalidate();
    }

    public void onNothingSelected(AdapterView<?> parent){

    }

    //Handling the SeekBar component
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
    {
        if (fromUser) {
            switch (seekBar.getId()) {
                case R.id.RedVal:
                    ourFace.rVal = progress;
                    myFaceView.invalidate();
                    break;

                case R.id.GreenVal:
                    ourFace.gVal = progress;
                    myFaceView.invalidate();
                    break;

                case R.id.BlueVal:
                    ourFace.bVal = progress;
                    myFaceView.invalidate();
                    break;
            }
        }
}

    public void onStartTrackingTouch(SeekBar seekBar){}

    public void onStopTrackingTouch(SeekBar seekBar){}
}
