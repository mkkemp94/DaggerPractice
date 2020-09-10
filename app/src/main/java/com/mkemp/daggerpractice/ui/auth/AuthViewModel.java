package com.mkemp.daggerpractice.ui.auth;

import android.util.Log;

import com.mkemp.daggerpractice.models.User;
import com.mkemp.daggerpractice.network.auth.AuthApi;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel
{
    private static final String TAG = "AuthViewModel";
    
    private final AuthApi authApi;
    
    private MediatorLiveData<User> authUser = new MediatorLiveData<>();
    
    @Inject
    public AuthViewModel(AuthApi authApi)
    {
        this.authApi = authApi;
        Log.d(TAG, "AuthViewModel: View model is working...");
    }
    
    public void authenticateWithId(int userId)
    {
        // Convert rxJava call to live data
        final LiveData<User> source = LiveDataReactiveStreams.fromPublisher(
                authApi.getUser(userId)
                        .subscribeOn(Schedulers.io())
        );
        
        // Now we want to inform our authUser live data that something has changed...
        authUser.addSource(source, new Observer<User>()
        {
            @Override
            public void onChanged(User user)
            {
                authUser.setValue(user);
                authUser.removeSource(source);
            }
        });
    }
    
    public LiveData<User> observeUser()
    {
        // Return mediator
        return authUser;
    }
}
