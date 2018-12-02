package com.retromakers.retrofitpractice.helpers;

import com.retromakers.retrofitpractice.common.constants.NetworkKeys;
import com.retromakers.retrofitpractice.models.request.NameJobRequest;
import com.retromakers.retrofitpractice.models.response.NameJobResponse;
import com.retromakers.retrofitpractice.models.response.SingleUserResponse;
import com.retromakers.retrofitpractice.network.ApiServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiIntegrationHelper {
    private static ApiIntegrationHelper sInstance;
    private ApiServices mApiServices;

    public ApiIntegrationHelper() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetworkKeys.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApiServices = retrofit.create(ApiServices.class);
    }

    public static ApiIntegrationHelper getInstance() {
        if (sInstance == null) {
            sInstance = new ApiIntegrationHelper();
        }
        return sInstance;
    }

    public void getUserDetails(Callback<SingleUserResponse> singleUserResponseCallback) {
        Call<SingleUserResponse> getUserDetails = mApiServices.getUserDetails();
        getUserDetails.enqueue(singleUserResponseCallback);
    }

    public void doLogin(NameJobRequest nameJobRequest, Callback<NameJobResponse> nameJobResponseCallback) {
        Call<NameJobResponse> getToken = mApiServices.doLogin(nameJobRequest);
        getToken.enqueue(nameJobResponseCallback);
    }
}
