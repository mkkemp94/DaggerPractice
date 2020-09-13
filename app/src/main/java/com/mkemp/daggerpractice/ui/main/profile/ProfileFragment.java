package com.mkemp.daggerpractice.ui.main.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mkemp.daggerpractice.R;
import com.mkemp.daggerpractice.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import dagger.android.support.DaggerFragment;

public class ProfileFragment extends DaggerFragment
{
    private static final String TAG = "ProfileFragment";
    
    private ProfileViewModel viewModel;
    
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
        viewModel = new ViewModelProvider(this, providerFactory).get(ProfileViewModel.class);
    }
}
