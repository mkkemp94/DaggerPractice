package com.mkemp.daggerpractice.ui.main.posts;

import android.util.Log;

import com.mkemp.daggerpractice.SessionManager;
import com.mkemp.daggerpractice.network.main.MainApi;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;

public class PostsViewModel extends ViewModel
{
    private static final String TAG = "PostsViewModel";
    
    private final SessionManager sessionManager;
    private final MainApi mainApi;
    
    @Inject
    public PostsViewModel(SessionManager sessionManager, MainApi mainApi)
    {
        this.sessionManager = sessionManager;
        this.mainApi = mainApi;
        Log.d(TAG, "PostsViewModel: post view model is working...");
    }
}
