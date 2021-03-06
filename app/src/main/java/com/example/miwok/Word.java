package com.example.miwok;

import java.util.ArrayList;

public class Word {

    private String mMiwokTranslation;
    private String mDefaultTranslation;
    private int mResourceId = -1;
    private String name;

    public Word(String defaultTranslation, String miwokTranslation) {
        mMiwokTranslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;

    }

    public Word(String defaultTranslation, String miwokTranslation, int resourceId) {
        mMiwokTranslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;
        mResourceId = resourceId;

    }

    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    public int getmResourceId() {
        return mResourceId;
    }

    public boolean hasImage(){
        if (mResourceId == -1) {
            return false;
        }
        return  true;
    }

}
