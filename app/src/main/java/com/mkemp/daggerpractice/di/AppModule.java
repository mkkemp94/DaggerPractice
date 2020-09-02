package com.mkemp.daggerpractice.di;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule
{
    // Put app dependencies in here like retrofit, glide, etc
    
    @Provides
    static String myString()
    {
        return "Testing 1 2 3 ?";
    }
    
    @Provides
    static boolean getApp(Application application)
    {
        return application == null ;
    }
}
