package com.arirus.viewlayout;

/**
 * Created by whd on 2016/11/30.
 */

public class ChatContenBean {
    private String mName;
    private int mType;
    private int mDrawble;
    private String mText;

    @Override
    public String toString() {
        return mText;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }

    public int getDrawble() {
        return mDrawble;
    }

    public void setDrawble(int drawble) {
        mDrawble = drawble;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }
}
