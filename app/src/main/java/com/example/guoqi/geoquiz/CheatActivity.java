package com.example.guoqi.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CheatActivity extends ActionBarActivity {

    public static final String ANSWER_TAG = "com.example.guoqi.geoquiz.answer_is_true";
    public static final String ANSWER_SHOWN = "com.example.guoqi.geoquiz.is_shown";

    private boolean mAnswerIsTrue;
    private boolean mIsCheater;

    TextView mAnswerView;
    Button   mGetAnswerButton;

    private  void setAnswerIsShown (boolean isShown) {
        Intent intent = new Intent();
        intent.putExtra(ANSWER_SHOWN, isShown);
        setResult(RESULT_OK,intent);

        mIsCheater = isShown;
   }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        boolean isPrevCheater = false;
        if (savedInstanceState != null) {
            isPrevCheater = savedInstanceState.getBoolean(ANSWER_SHOWN, false);
        }
        setAnswerIsShown(isPrevCheater);

        mAnswerIsTrue = getIntent().getBooleanExtra(ANSWER_TAG,false);

        mAnswerView = (TextView)findViewById(R.id.answerTextView);

        mGetAnswerButton = (Button)findViewById(R.id.showAnswerButton);
        mGetAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                if (mAnswerIsTrue == true) {
                    mAnswerView.setText(R.string.yes_text);
                }
                else {
                    mAnswerView.setText(R.string.no_text);
                }
                setAnswerIsShown(true);
            }
        });
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(ANSWER_SHOWN, mIsCheater);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cheat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
