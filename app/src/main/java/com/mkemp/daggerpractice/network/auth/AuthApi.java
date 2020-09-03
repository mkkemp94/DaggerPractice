package com.mkemp.daggerpractice.network.auth;

import com.mkemp.daggerpractice.models.User;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AuthApi
{
    @GET("users/{id}")
    Flowable<User> getUser(@Path("id") int id);
}
