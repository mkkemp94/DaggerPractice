package com.mkemp.daggerpractice.ui.auth;

import android.util.Log;

import com.mkemp.daggerpractice.models.User;
import com.mkemp.daggerpractice.network.ExampleInterceptor;
import com.mkemp.daggerpractice.network.auth.AuthApi;
import com.mkemp.daggerpractice.util.Constants;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel
{
    private static final String TAG = "AuthViewModel";
    private final AuthApi authApi;
    @Inject ExampleInterceptor interceptor;
    
    @Inject
    public AuthViewModel(AuthApi authApi, ExampleInterceptor interceptor)
    {
        this.authApi = authApi;
        this.interceptor = interceptor;
        Log.d(TAG, "AuthViewModel: View model is working...");
        if (this.authApi == null)
        {
            Log.d(TAG, "AuthViewModel: auth API is NULL");
        }
        else
        {
            Log.d(TAG, "AuthViewModel: auth API created successfully!");
        }
        
        interceptor.setInterceptor(Constants.BASE_URL);
        authApi.getUser(1)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d)
                    {
        
                    }
    
                    @Override
                    public void onNext(User user)
                    {
                        Log.d(TAG, "onNext: " + user.getEmail());
                    }
    
                    @Override
                    public void onError(Throwable e)
                    {
                        Log.e(TAG, "onError: ", e);
                    }
    
                    @Override
                    public void onComplete()
                    {
        
                    }
                });
    }
}
