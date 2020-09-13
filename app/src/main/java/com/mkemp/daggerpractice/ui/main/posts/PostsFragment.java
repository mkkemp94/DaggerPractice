package com.mkemp.daggerpractice.ui.main.posts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mkemp.daggerpractice.R;
import com.mkemp.daggerpractice.models.Post;
import com.mkemp.daggerpractice.ui.main.Resource;
import com.mkemp.daggerpractice.viewmodels.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import dagger.android.support.DaggerFragment;

public class PostsFragment extends DaggerFragment
{
    private static final String TAG = "PostsFragment";
    
    private PostsViewModel viewModel;
    private RecyclerView recyclerView;
    
    @Inject
    ViewModelProviderFactory providerFactory;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        recyclerView = view.findViewById(R.id.recycler_view);
        
        viewModel = new ViewModelProvider(this, providerFactory).get(PostsViewModel.class);
   
        subscribeObservers();
    }
    
    private void subscribeObservers()
    {
        viewModel.observePosts().removeObservers(getViewLifecycleOwner());
        viewModel.observePosts().observe(getViewLifecycleOwner(), new Observer<Resource<List<Post>>>()
        {
            @Override
            public void onChanged(Resource<List<Post>> listResource)
            {
                if ( listResource != null )
                {
                    Log.d(TAG, "onChanged: " + listResource.data);
                }
            }
        });
    }
}
