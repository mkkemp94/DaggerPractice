package com.mkemp.daggerpractice.ui.main.profile;

import android.util.Log;

import com.mkemp.daggerpractice.SessionManager;
import com.mkemp.daggerpractice.models.User;
import com.mkemp.daggerpractice.ui.auth.AuthResource;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel
{
    private static final String TAG = "ProfileViewModel";
    
    private final SessionManager sessionManager;
    
    @Inject
    public ProfileViewModel(SessionManager sessionManager)
    {
        this.sessionManager = sessionManager;
        Log.d(TAG, "ProfileViewModel: profile view model is ready...");
    }
    
    public LiveData<AuthResource<User>> getAuthenticatedUser()
    {
        return sessionManager.getAuthUser();
    }
}
