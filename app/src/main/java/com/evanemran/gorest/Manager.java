package com.evanemran.gorest;

import android.content.Context;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class Manager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://gorest.co.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public Manager(Context context) {
        this.context = context;
    }

    public void postUser(UserResponseListener listener, UserResponse model){
        CallUsers callUsers = retrofit.create(CallUsers.class);
        Call<UserResponse> call = callUsers.calUsers(model);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    private interface CallUsers{
        @Headers({
                "Accept: application/json",
                "Authorization: Bearer ed0660b211613f147a454d9e9261ae80c56d102d6d41b3e3cbd52bbdcd519f47"
        })
        @POST("public/v2/users")
        Call<UserResponse> calUsers(
                @Body() UserResponse response
        );
    }
}
