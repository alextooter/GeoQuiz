package com.example.guoqi.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends ActionBarActivity {
    final private String TAG = "QuizActivity";
    final private String KEY_INDEX = "index";
    final private String IsCheater = "IsCheater";

    private Button mYesButton;
    private Button mNoButton;

    private Button mNextButton;
    private Button mPrevButton;
    private TextView mQuestionView;

    private Button mCheatButton;

    private TrueFalse mQuestionBank[] = new TrueFalse[] {
            new TrueFalse(R.string.question_africa,false),
            new TrueFalse(R.string.question_americas, true),
            new TrueFalse(R.string.question_asia,true),
            new TrueFalse(R.string.question_mideast, false),
            new TrueFalse(R.string.question_oceans,true),
    };
    private int mCurrentIndex = 0;

    private boolean[] mIsCheater = new boolean[mQuestionBank.length];

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null) {
            return;
        }
        else {
            mIsCheater[mCurrentIndex] = data.getBooleanExtra(CheatActivity.ANSWER_SHOWN, false);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0);
            mIsCheater[mCurrentIndex] = savedInstanceState.getBoolean(IsCheater, false);
        }
        setContentView(R.layout.activity_quiz);

        Log.d(TAG, "onCreate");

        mYesButton = (Button)findViewById(R.id.yes_button);
        mYesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

        mNoButton = (Button) findViewById(R.id.no_button);
        mNoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                if (mIsCheater[mCurrentIndex] == true) {
                    ;//nothing to do
                } else {
                    mIsCheater[mCurrentIndex] = false;
                }
                updateQuestion();
            }
        });

        mCheatButton = (Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                //tell activity manager to create a new cheat_activity
                Intent intent = new Intent(QuizActivity.this, CheatActivity.class);
                //with a true extra value.
                intent.putExtra(CheatActivity.ANSWER_TAG, mQuestionBank[mCurrentIndex].isTrueQuestion());
//                startActivity(intent);
                startActivityForResult(intent, 0);
            }
        });

//        mPrevButton = (Button)findViewById(R.id.prev_button);
//        mPrevButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick (View view) {
//                if (mCurrentIndex == 0)
//                    return;
//                else {
//                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
//                    updateQuestion();
//                }
//            }
//        });

        mQuestionView = (TextView)findViewById(R.id.question_text);
        updateQuestion();

        mQuestionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
    }

    @Override
    protected void onDestroy () {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    @Override
    protected void onStop () {
        super.onStop();
        Log.d(TAG,"onStop");
    }

    @Override
    protected void onPause () {
        super.onPause();
        Log.d(TAG,"onPause");
    }

    @Override
    protected void onResume () {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "on save instance state");
        outState.putInt(KEY_INDEX, mCurrentIndex);
        outState.putBoolean(IsCheater,mIsCheater[mCurrentIndex]);
    }

    @Override
    protected void onStart () {
        super.onStart();
        Log.d(TAG,"onStart");
    }

    private void updateQuestion () {
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        if (mQuestionView != null)
            mQuestionView.setText(question);
        else {
            Log.d(TAG, "Wrong QuestionView");
        }
    }

    private void checkAnswer (boolean userAnswer) {
        boolean isTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
        int id = 0;
        if (mIsCheater[mCurrentIndex] == true) {
            id = R.string.judgment_toast;
        }
        else {
            if (isTrue == userAnswer) {
                id = R.string.correct_toast;
            } else {
                id = R.string.wrong_toast;
            }
        }
        Toast.makeText(QuizActivity.this, id, Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quiz, menu);
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
