/*
    Author: Austin Gabriel-Garces
    Description: Class that creates the surface view of the face. Draws all face components and its aspects.
 */
package com.example.facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceView;

import java.util.Random;

public class FaceView extends SurfaceView {

    Paint skinPaint = new Paint();
    Paint eyeSockets = new Paint();
    Paint eyeIris = new Paint();
    Paint eyePupil = new Paint();
    Paint lipPaint = new Paint();
    Paint hairPaint = new Paint();

    public static final float faceTop = 150.0f;
    public static final float faceLeft = 350.0f;
    public static final float faceRight = 350.0f;
    public static final float faceBottom = 400.0f;

    private Face myFace = new Face();

    protected Random randNum = new Random();

    public FaceView(Context context, AttributeSet attrs){
        super(context, attrs);
        setWillNotDraw(false);

        //myFace.skinColor = Color.rgb(myFace.rVal, myFace.gVal, myFace.bVal);

        skinPaint.setColor(Color.GREEN);
        skinPaint.setStyle(Paint.Style.FILL);
        eyeSockets.setColor(Color.GREEN);
        eyeSockets.setStyle(Paint.Style.FILL);
        eyeIris.setColor(Color.RED);
        eyeIris.setStyle(Paint.Style.FILL);
        eyePupil.setColor(Color.BLACK);
        eyePupil.setStyle(Paint.Style.FILL);
        lipPaint.setColor(Color.BLACK);
        lipPaint.setStyle(Paint.Style.FILL);
        hairPaint.setColor(Color.BLACK);
        hairPaint.setStyle(Paint.Style.FILL);

        setBackgroundColor(Color.WHITE);
    }

    public Face getMyFace(){return myFace;}

    @Override
    public void onDraw(Canvas canvas){

        if (!myFace.randMode) {
            //Radio Button selection
            if (myFace.skinChoice) {//Setting any new color changes to the skin
                myFace.skinColor = Color.rgb(myFace.rVal, myFace.gVal, myFace.bVal);
                skinPaint.setColor(myFace.skinColor);
                eyeSockets.setColor(myFace.skinColor);
            } else if (myFace.eyeChoice) {//Setting any new color changes to the eyes
                myFace.eyeColor = Color.rgb(myFace.rVal, myFace.gVal, myFace.bVal);
                eyeIris.setColor(myFace.eyeColor);
            } else if (myFace.hairChoice) {//Setting any new color changes to the hair
                myFace.hairColor = Color.rgb(myFace.rVal, myFace.gVal, myFace.bVal);
                hairPaint.setColor(myFace.hairColor);
            }
        }
        else {//Face color components set to a random color
            skinPaint.setARGB(255, randNum.nextInt(255), randNum.nextInt(255), randNum.nextInt(255));
            eyeSockets = skinPaint;
            eyeIris.setARGB(255, randNum.nextInt(255), randNum.nextInt(255), randNum.nextInt(255));
            hairPaint.setARGB(255, randNum.nextInt(255), randNum.nextInt(255), randNum.nextInt(255));
            myFace.randMode = false;
        }

        //Finding the starting locations of the face and setting up the bounds
        int yCenter = canvas.getHeight()/2;
        int xCenter = canvas.getWidth()/2;

        float leftFaceBound = xCenter - faceLeft;
        float topFaceBound = yCenter - faceTop;
        float rightFaceBound = xCenter + faceRight;
        float bottomFaceBound = yCenter + faceBottom;

        float xCenterLeftEye = ((xCenter-leftFaceBound)/2) + leftFaceBound-50;
        float xCenterRightEye = ((rightFaceBound-xCenter)/2) + xCenter+50;
        float yCenterEye = topFaceBound;

        //Draw the face borders
        canvas.drawOval(leftFaceBound-5, topFaceBound-5, rightFaceBound+5, bottomFaceBound+5, eyePupil);
        canvas.drawCircle(xCenterLeftEye, yCenterEye, 130, eyePupil);
        canvas.drawCircle(xCenterRightEye, yCenterEye, 130, eyePupil);

        //Draw the starting face
        canvas.drawOval(leftFaceBound, topFaceBound, rightFaceBound, bottomFaceBound, skinPaint);

        //Draw the eyeSockets
        canvas.drawCircle(xCenterLeftEye, yCenterEye, 125, eyeSockets);
        canvas.drawCircle(xCenterRightEye, yCenterEye, 125, eyeSockets);

        //Draw eyeIris
        canvas.drawCircle(xCenterLeftEye, yCenterEye, 102, eyePupil);
        canvas.drawCircle(xCenterRightEye, yCenterEye, 102, eyePupil);
        canvas.drawCircle(xCenterLeftEye, yCenterEye, 100, eyeIris);
        canvas.drawCircle(xCenterRightEye, yCenterEye, 100, eyeIris);

        //Draw pupils
        canvas.drawCircle(xCenterLeftEye, yCenterEye, 75, eyePupil);
        canvas.drawCircle(xCenterRightEye, yCenterEye, 75, eyePupil);

        //Spinner Selection
        switch(myFace.hairStyle){
            case(1):
                //Draw eyelashes
                float radius = 102;
                float cxL = xCenterLeftEye;
                float cxR = xCenterRightEye;
                float cy = yCenterEye;
                float startXL;
                float startXR;
                float startY;
                float endXL;
                float endXR;
                float endY;

                for (double i=0; i < Math.PI; i=i+0.2){
                    startXL = (float)(cxL + (radius*Math.cos(i+Math.PI)));
                    startXR = (float)(cxR + (radius*Math.cos(i+Math.PI)));
                    startY = (float)(cy + (radius*Math.sin(i+Math.PI)));
                    endXL = (float)(startXL + ((radius/3)*Math.cos(i+Math.PI)));
                    endXR = (float)(startXR + ((radius/3)*Math.cos(i+Math.PI)));
                    endY = (float)(startY + ((radius/3)*Math.sin(i+Math.PI)));
                    canvas.drawLine(startXL, startY, endXL, endY, hairPaint);
                    canvas.drawLine(startXR, startY, endXR, endY, hairPaint);
                }
                break;

            case(2):
                //Draw mustache}
                canvas.drawArc(leftFaceBound+220, topFaceBound+115, xCenter-5, bottomFaceBound-380, 0, 135, false, hairPaint);
                canvas.drawArc(xCenter+5, topFaceBound+115, rightFaceBound-220, bottomFaceBound-380, 45, 135, false, hairPaint);
                break;

            case(3):
                //Draw goatee
                drawTriangle(canvas, hairPaint, xCenter, yCenter+250, 50);
                canvas.drawArc(leftFaceBound+200, topFaceBound+60, rightFaceBound-200, bottomFaceBound-240, 0, 180, false, skinPaint);
                break;
        }
        //Draw mouth
        canvas.drawArc(leftFaceBound+200, topFaceBound+50, rightFaceBound-200, bottomFaceBound-250, 0, 180, false, eyePupil);
    }

    //Method that draws a triangle
    public void drawTriangle(Canvas canvas, Paint paint, float x, float y, float width) {
        float halfWidth = width / 2;

        Path path = new Path();
        path.moveTo(x, y - halfWidth); // Top
        path.lineTo(x - width, y - width*2); // Bottom left
        path.lineTo(x + width, y - width*2); // Bottom right
        path.lineTo(x, y - halfWidth); // Back to Top
        path.close();

        canvas.drawPath(path, paint);
    }
}
