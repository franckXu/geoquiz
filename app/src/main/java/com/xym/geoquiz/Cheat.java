package com.xym.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Cheat extends AppCompatActivity {

    private static final String TAG = "CheatActivity";
    static final String EXTAR_ANSWER_IS_SHOW = "com.xym.geoquiz.EXTAR_ANSWER_IS_SHOW";
    static final String EXTAR_QUESTION = "com.xym.geoquiz.EXTAR_QUESTION";
    static final String EXTAR_ANSWER_IS_TRUE = "com.xym.geoquiz.EXTAR_ANSWER_IS_TRUE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_cheat);

        final TextView answerText = findViewById(R.id.answer_txt);
        final Bundle bundle = getIntent().getExtras();
        TextView textView = findViewById(R.id.question_text);
        textView.setText(bundle.getInt(EXTAR_QUESTION));

        setAnswerIsShow(false);
        Button button = findViewById(R.id.show_answer_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerText.setText(bundle.getBoolean(EXTAR_ANSWER_IS_TRUE) ? "is True" : "is False");
                setAnswerIsShow(true);
            }
        });
    }

    void setAnswerIsShow(Boolean isShow) {
        Intent data = new Intent();
        data.putExtra(EXTAR_ANSWER_IS_SHOW, isShow);
        setResult(RESULT_OK, data);
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
}