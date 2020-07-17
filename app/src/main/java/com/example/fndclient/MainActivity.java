package com.example.fndclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FakeTextSaveActivity.class);
                startActivity(intent);
            }
        });


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://466c55c62147.ngrok.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        fakeTextDetectionApi = retrofit.create(FakeTextDetectionApi.class);

        textViewResult = (TextView)findViewById(R.id.textViewResult);

        feedbackOne = (TextView)findViewById(R.id.feedback_one);
        feedbackTwo = (TextView)findViewById(R.id.feedback_two);
        text = (EditText) findViewById(R.id.editFakeText);

    }


    public void detectFakeText(View view)
    {
        String detectText = text.getText().toString();
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

//                Log.d("Feedback 1",FeedbackOne);
//                Log.d("Feedback 2",FeedbackTwo);
            }

            @Override
            public void onFailure(Call<FakeText> call, Throwable t) {

                Log.d("Result: ","Failed");
                Log.d("Code: ",t.getMessage());

            }
        });
    }

}