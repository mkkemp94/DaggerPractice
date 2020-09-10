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
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel
{
    private static final String TAG = "AuthViewModel";
    
    private final AuthApi authApi;
    
    private MediatorLiveData<AuthResource<User>> authUser = new MediatorLiveData<>();
    
    @Inject
    public AuthViewModel(AuthApi authApi)
    {
        this.authApi = authApi;
        Log.d(TAG, "AuthViewModel: View model is working...");
    }
    
    public void authenticateWithId(int userId)
    {
        // Tell UI request is being made ... show progress bar!!
        authUser.setValue(AuthResource.loading((User) null));
        
        // Convert rxJava call to live data
        final LiveData<AuthResource<User>> source = LiveDataReactiveStreams.fromPublisher(
                
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
                                try
                                {
                                    Thread.sleep(2000);
                                }
                                catch (InterruptedException e)
                                {
                                    e.printStackTrace();
                                }
                                
                                if (user.getId() == -1)
                                {
                                    return AuthResource.error("Could not authenticate", (User) null);
                                }
                                
                                return AuthResource.authenticated(user);
                            }
                        })
                        
                        .subscribeOn(Schedulers.io())
        );
        
        // Now we want to inform our authUser live data that something has changed...
        authUser.addSource(source, new Observer<AuthResource<User>>()
        {
            @Override
            public void onChanged(AuthResource<User> user)
            {
                authUser.setValue(user);
                authUser.removeSource(source);
            }
        });
    }
    
    public LiveData<AuthResource<User>> observeUser()
    {
        // Return mediator
        return authUser;
    }
}
