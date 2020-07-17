package com.example.fndclient;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface FakeTextDetectionApi {

    @POST("detect")
    Call<FakeText> detectFakeText(@Body FakeText fakeText);
}
