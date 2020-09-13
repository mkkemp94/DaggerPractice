package com.mkemp.daggerpractice.di.main;

import com.mkemp.daggerpractice.ui.main.posts.PostsFragment;
import com.mkemp.daggerpractice.ui.main.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuildersModule
{
    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();
    
    @ContributesAndroidInjector
    abstract PostsFragment contributePostsFragment();
}
