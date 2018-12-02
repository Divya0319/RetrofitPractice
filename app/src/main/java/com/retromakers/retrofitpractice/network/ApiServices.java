package com.retromakers.retrofitpractice.network;

import com.retromakers.retrofitpractice.common.constants.NetworkKeys;
import com.retromakers.retrofitpractice.models.request.NameJobRequest;
import com.retromakers.retrofitpractice.models.response.NameJobResponse;
import com.retromakers.retrofitpractice.models.response.SingleUserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServices {
    @POST(NetworkKeys.POST_ENDPOINT)
    Call<NameJobResponse> doLogin(@Body NameJobRequest loginRequest);

    @GET(NetworkKeys.GET_ENDPOINT)
    Call<SingleUserResponse> getUserDetails();
}
