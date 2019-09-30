/*
    Author: Austin Gabriel-Garces
    Description: Class used to holds important variables of the drawn face
 */
package com.example.facemaker;

public class Face {

    //RGB components for colors used
    public int rVal = 0;
    public int gVal = 0;
    public int bVal = 0;

    public int skinColor = 0;
    public int eyeColor = 0;
    public int hairColor = 0;
    public int hairStyle = 0;

    //Boolean variables based off of Radio Buttons
    public boolean skinChoice = false;
    public boolean eyeChoice = false;
    public boolean hairChoice = false;

    //Face drawn randomly when random button is pressed
    public boolean randMode = true;
}
