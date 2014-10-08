package com.example.guoqi.geoquiz;

/**
 * Created by guoqi on 14-9-29.
 */
public class TrueFalse {
    public int getQuestion () {
        return mQuestion;
    }

    public void setQuestion (int question) {
        mQuestion = question;
    }

    private int mQuestion;

    public boolean isTrueQuestion () {
        return mTrueQuestion;
    }

    public void setTrueQuestion (boolean trueQuestion) {
        mTrueQuestion = trueQuestion;
    }

    private boolean mTrueQuestion;

    public TrueFalse(int no, boolean trueQuestion)
    {
        mQuestion = no;
        mTrueQuestion = trueQuestion;
    }

}
