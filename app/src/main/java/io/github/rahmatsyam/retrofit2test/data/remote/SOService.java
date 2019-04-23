package io.github.rahmatsyam.retrofit2test.data.remote;

import io.github.rahmatsyam.retrofit2test.data.model.SOAnswersResponse;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;


public interface SOService {
    @GET("answers?order=desc&sort=activity&site=stackoverflow")
    @Headers({"Content-Type: application/json"})
    Call<SOAnswersResponse> getAnswers();

    @GET("answers?order=desc&sort=activity&site=stackoverflow")
    Call<SOAnswersResponse> getAnswers(@Query("tagged") String tags);
}
