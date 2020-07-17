package com.example.fndclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class FakeTextSaveActivity extends AppCompatActivity {
    private EditText fakeTextSave, feedBackOne,feedBackTwo;

    public static final String EXTRA_REPLY_FAKE_TEXT = "extra_reply_fake_text";
    public static final String EXTRA_REPLY_FEEDBACK_ONE = "extra_reply_feedback_one";
    public static final String EXTRA_REPLY_FEEDBACK_TWO = "extra_reply_feedback_two";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake_text_save);
        fakeTextSave = (EditText) findViewById(R.id.editFakeTextSave);
        feedBackOne = (EditText) findViewById(R.id.editTextFeedback1);
        feedBackTwo = (EditText) findViewById(R.id.editTextFeedback2);



    }

    public void saveToDatabase(View view) {
        Intent replyIntent = new Intent();
        Log.d("message","Begin");
        if (TextUtils.isEmpty(feedBackOne.getText()) ||
                TextUtils.isEmpty(fakeTextSave.getText()) ) {
            setResult(RESULT_CANCELED, replyIntent);
            Log.d("message","Save To DB if");
        }
        else{
            Log.d("message","Save To DB else");

            String fakeText = fakeTextSave.getText().toString();
            String feedBack1 = feedBackOne.getText().toString();
            String feedBack2 = feedBackTwo.getText().toString();

            replyIntent.putExtra(EXTRA_REPLY_FAKE_TEXT,fakeText);
            replyIntent.putExtra(EXTRA_REPLY_FEEDBACK_ONE,feedBack1);

            if(! TextUtils.isEmpty(feedBack2)){
                replyIntent.putExtra(EXTRA_REPLY_FEEDBACK_TWO, feedBack2);

            }

            setResult(RESULT_OK,replyIntent);


        }

        finish();
    }
}