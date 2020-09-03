package com.mkemp.daggerpractice.ui.auth;

import android.util.Log;

import com.mkemp.daggerpractice.network.auth.AuthApi;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;

public class AuthViewModel extends ViewModel
{
    private static final String TAG = "AuthViewModel";
    private final AuthApi authApi;
    
    @Inject
    public AuthViewModel(AuthApi authApi)
    {
        this.authApi = authApi;
        Log.d(TAG, "AuthViewModel: View model is working...");
        if (this.authApi == null)
        {
            Log.d(TAG, "AuthViewModel: auth API is NULL");
        }
        else
        {
            Log.d(TAG, "AuthViewModel: auth API created successfully!");
        }
    }
}
