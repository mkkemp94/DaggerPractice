package com.mkemp.daggerpractice;

import com.mkemp.daggerpractice.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * This class exists for the lifetime of the application.
 */
public class BaseApplication extends DaggerApplication
{
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector()
    {
        return DaggerAppComponent.builder().myAwesomeApplication(this).buildTheThing();
    }
}
