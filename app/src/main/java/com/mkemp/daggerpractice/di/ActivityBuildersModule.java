package com.mkemp.daggerpractice.di;

import com.mkemp.daggerpractice.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Dependencies live in the modules.
 *
 * Here you can specify potential clients to inject into.
 */
@Module
public abstract class ActivityBuildersModule
{
    @ContributesAndroidInjector
    abstract AuthActivity contributeAuthActivity();
}
