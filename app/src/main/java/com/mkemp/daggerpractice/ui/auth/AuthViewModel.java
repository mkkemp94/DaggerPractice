package com.mkemp.daggerpractice.ui.auth;

import android.util.Log;

import com.mkemp.daggerpractice.SessionManager;
import com.mkemp.daggerpractice.models.User;
import com.mkemp.daggerpractice.network.auth.AuthApi;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModel;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel
{
    private static final String TAG = "AuthViewModel";
    
    private final AuthApi authApi;
    private SessionManager sessionManager;
    
    @Inject
    public AuthViewModel(AuthApi authApi, SessionManager sessionManager)
    {
        this.authApi = authApi;
        this.sessionManager = sessionManager;
        Log.d(TAG, "AuthViewModel: View model is working...");
    }
    
    public void authenticateWithId(int userId)
    {
        Log.d(TAG, "authenticateWithId: attempting to login...");
        sessionManager.authenticateWithId(queryUserId(userId));
    }
    
    private LiveData<AuthResource<User>> queryUserId(int userId)
    {
        return LiveDataReactiveStreams.fromPublisher(
            
                // Flowable user object to return
                authApi.getUser(userId)
                    
                        // Instead of calling onError()
                        .onErrorReturn(new Function<Throwable, User>() {
                            @Override
                            public User apply(Throwable throwable) throws Exception
                            {
                                User errorUser = new User();
                                errorUser.setId(-1);
                                return errorUser;
                            }
                        })
                    
                        // Receives error user or success user
                        .map(new Function<User, AuthResource<User>>() {
                            @Override
                            public AuthResource<User> apply(User user) throws Exception
                            {
                                if (user.getId() == -1)
                                {
                                    return AuthResource.error("Could not authenticate", (User) null);
                                }
                            
                                return AuthResource.authenticated(user);
                            }
                        })
                    
                        .subscribeOn(Schedulers.io())
        );
    }
    
    public LiveData<AuthResource<User>> observeAuthState()
    {
        // Return mediator
        return sessionManager.getAuthUser();
    }
}
