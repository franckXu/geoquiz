package com.xym.geoquiz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private final static String INDEX_KEY = "index";
    int currentQuestionIndex = 0;
    Context mContext;

    TextView mTextView;

    final TrueFalse[] trueFalses = new TrueFalse[]{
            new TrueFalse(R.string.question_1, false)
            , new TrueFalse(R.string.question_2, false)
            , new TrueFalse(R.string.question_3, true)
    };

    Button prevBtn;
    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            currentQuestionIndex = savedInstanceState.getInt(INDEX_KEY, 0);
        }

        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_main);

        prevBtn = findViewById(R.id.prev_button);
        nextBtn = findViewById(R.id.next_button);

        mTextView = findViewById(R.id.question_text);
        mTextView.setText(trueFalses[currentQuestionIndex].getmQuestion());
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuestionIndex == trueFalses.length - 1) return;
                currentQuestionIndex++;
                updatePrevNextBtnVisible();
            }
        });

        Button trueBtn = findViewById(R.id.true_button);
        Button falseBtn = findViewById(R.id.false_button);
        mContext = getApplicationContext();
        trueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTrueFalseBtnClick(true);
                updatePrevNextBtnVisible();
            }
        });
        falseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTrueFalseBtnClick(false);
                updatePrevNextBtnVisible();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuestionIndex == trueFalses.length - 1) return;
                currentQuestionIndex++;
                updatePrevNextBtnVisible();
            }
        });

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuestionIndex == 0) return;
                currentQuestionIndex--;
                updatePrevNextBtnVisible();
            }
        });

        Button cheatButton = findViewById(R.id.cheat_button);
        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cheat.class);
                intent.putExtra(Cheat.EXTAR_QUESTION, trueFalses[currentQuestionIndex].getmQuestion());
                intent.putExtra(Cheat.EXTAR_ANSWER_IS_TRUE, trueFalses[currentQuestionIndex].ismTrueQuestion());
                startActivityForResult(intent, 0);
            }
        });
        updatePrevNextBtnVisible();
    }

    void updatePrevNextBtnVisible() {
        mTextView.setText(trueFalses[currentQuestionIndex].getmQuestion());
        // handle prevBtn visible
        if (currentQuestionIndex == 0) {
            prevBtn.setVisibility(View.INVISIBLE);
            nextBtn.setVisibility(View.VISIBLE);
        } else if (currentQuestionIndex == trueFalses.length - 1) {
            prevBtn.setVisibility(View.VISIBLE);
            nextBtn.setVisibility(View.INVISIBLE);
        } else {
            prevBtn.setVisibility(View.VISIBLE);
            nextBtn.setVisibility(View.VISIBLE);
        }

    }

    void onTrueFalseBtnClick(Boolean answer) {
        if (trueFalses[currentQuestionIndex].ismTrueQuestion() == answer) {
            if (currentQuestionIndex == trueFalses.length - 1) {
                Toast.makeText(mContext, "恭喜，你已经完成所有题目。", Toast.LENGTH_SHORT).show();
            } else {
                if (answerIsShow) {
                    Toast.makeText(mContext, "cheating is wrong", Toast.LENGTH_SHORT).show();
                    answerIsShow = false;
                } else {
                    Toast.makeText(mContext, "is correct", Toast.LENGTH_SHORT).show();
                }
                currentQuestionIndex++;
                mTextView.setText(trueFalses[currentQuestionIndex].getmQuestion());
            }
        } else {
            if (answerIsShow) {
                Toast.makeText(mContext, "cheating is wrong", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mContext, "is not correct", Toast.LENGTH_SHORT).show();
            }
        }

    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(INDEX_KEY, currentQuestionIndex);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    Boolean answerIsShow = false;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 && resultCode == RESULT_OK && data != null) {
            answerIsShow = data.getBooleanExtra(Cheat.EXTAR_ANSWER_IS_SHOW, false);
        }
    }
}
