package com.example.fndclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private FakeTextDetectionApi fakeTextDetectionApi;
    private TextView textViewResult;
    private TextView feedbackOne;
    private TextView feedbackTwo;
    private EditText text;
    public static final int UPDATE_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, FakeTextSaveActivity.class);
            startActivityForResult(intent,UPDATE_REQUEST_CODE);
        });


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://3ad151078828.ngrok.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        fakeTextDetectionApi = retrofit.create(FakeTextDetectionApi.class);

        textViewResult = (TextView)findViewById(R.id.textViewResult);

        feedbackOne = (TextView)findViewById(R.id.feedback_one);
        feedbackTwo = (TextView)findViewById(R.id.feedback_two);
        text = (EditText) findViewById(R.id.editFakeText);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        String fakeTextData = data.getStringExtra(FakeTextSaveActivity.EXTRA_REPLY_FAKE_TEXT);
        String feedBackOne = data.getStringExtra(FakeTextSaveActivity.EXTRA_REPLY_FEEDBACK_ONE);
        if (requestCode == UPDATE_REQUEST_CODE && resultCode == RESULT_OK) {

            FakeText fakeText;
            if((data.hasExtra(FakeTextSaveActivity.EXTRA_REPLY_FEEDBACK_TWO)))
            {
                String feedBackTwo = data.getStringExtra(FakeTextSaveActivity.EXTRA_REPLY_FEEDBACK_TWO);
                fakeText = new FakeText(fakeTextData,feedBackOne,feedBackTwo);
            }
            else{
                fakeText = new FakeText(fakeTextData,feedBackOne);
            }
            Call<FakeText> call = fakeTextDetectionApi.saveFakeText(fakeText);

            call.enqueue(new Callback<FakeText>() {
                @Override
                public void onResponse(Call<FakeText> call, Response<FakeText> response) {

                    Log.d("Result: ","Successfull");

                    if (!response.isSuccessful()) {
                        Log.d("Error: ", String.valueOf(response.code()));
                        return;
                    }

                    Toast.makeText(
                            getApplicationContext(),
                            "Fake Text saved successfully.",
                            Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<FakeText> call, Throwable t) {

                    Log.d("Result: ","Failed");
                    Log.d("Code: ","" + t.getMessage());

                    Toast.makeText(
                            getApplicationContext(),
                            "Unable to save text.",
                            Toast.LENGTH_LONG).show();

                }
            });

        }
        else {
            Toast.makeText(
                    getApplicationContext(),
                    "Fake Text could not be saved.",
                    Toast.LENGTH_LONG).show();
        }

    }




    public void detectFakeText(View view)
    {
        String detectText = text.getText().toString();

        if(TextUtils.isEmpty(detectText))
        {
            Toast.makeText(
                    getApplicationContext(),
                    "Fake Text cannot be empty.",
                    Toast.LENGTH_LONG).show();
            return;
        }
        FakeText fakeText = new FakeText(detectText);

        Call<FakeText> call = fakeTextDetectionApi.detectFakeText(fakeText);

        call.enqueue(new Callback<FakeText>() {
            @Override
            public void onResponse(Call<FakeText> call, Response<FakeText> response) {
                Log.d("Result: ","Successfull");

                if (!response.isSuccessful()) {
                    Log.d("Error: ", String.valueOf(response.code()));
                    return;
                }

                FakeText fakeText = response.body();

                String content = fakeText.getDescription();
                String FeedbackOne = fakeText.getFeedback_1();
                String FeedbackTwo = fakeText.getFeedback_2();

                textViewResult.setText(content);
                feedbackOne.setText("" + FeedbackOne);
                feedbackTwo.setText("" + FeedbackTwo);
                Log.d("Content: ",content);


            }

            @Override
            public void onFailure(Call<FakeText> call, Throwable t) {

                Log.d("Result: ","Failed");
                Log.d("Code: ",t.getMessage());

            }
        });
    }

}