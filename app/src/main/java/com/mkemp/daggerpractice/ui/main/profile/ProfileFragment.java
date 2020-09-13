package com.mkemp.daggerpractice.ui.main.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mkemp.daggerpractice.R;
import com.mkemp.daggerpractice.models.User;
import com.mkemp.daggerpractice.ui.auth.AuthResource;
import com.mkemp.daggerpractice.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import dagger.android.support.DaggerFragment;

public class ProfileFragment extends DaggerFragment
{
    private static final String TAG = "ProfileFragment";
    
    private ProfileViewModel viewModel;
    private TextView email, username, website;
    
    @Inject
    ViewModelProviderFactory providerFactory;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        Toast.makeText(getActivity(), "Profile fragment created", Toast.LENGTH_SHORT).show();
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        Log.d(TAG, "onViewCreated: ProfileFragment was created...");
        email = view.findViewById(R.id.email);
        username = view.findViewById(R.id.username);
        website = view.findViewById(R.id.website);
        
        viewModel = new ViewModelProvider(this, providerFactory).get(ProfileViewModel.class);
    
        subscribeObservers();
    }
    
    private void subscribeObservers()
    {
        // Fragments have their own lifecycle so make sure you remove observers
        viewModel.getAuthenticatedUser().removeObservers(getViewLifecycleOwner());
        viewModel.getAuthenticatedUser().observe(getViewLifecycleOwner(), new Observer<AuthResource<User>>()
        {
            @Override
            public void onChanged(AuthResource<User> userAuthResource)
            {
                if ( userAuthResource != null )
                {
                    switch ( userAuthResource.status )
                    {
                        case AUTHENTICATED:
                        {
                            setUserDetails(userAuthResource.data);
                            break;
                        }
                        
                        case ERROR:
                        {
                            setErrorDetails(userAuthResource.message);
                            break;
                        }
                    }
                }
            }
        });
    }
    
    private void setUserDetails(User user)
    {
        email.setText(user.getEmail());
        username.setText(user.getUsername());
        website.setText(user.getWebsite());
    }
    
    private void setErrorDetails(String message)
    {
        email.setText(message);
        username.setText("Error");
        website.setText("Error");
    }
}
