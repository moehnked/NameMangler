package com.example.namemangler;

import java.util.Random;

public class Name {
    private String mName;

    public int mangle(){
        //returns random last name to be appended
        int[] r = new int[]{R.string.rn1,R.string.rn2, R.string.rn3, R.string.rn4, R.string.rn5};
        Random randomGen = new Random();
        return r[randomGen.nextInt() % 5];
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
