package com.mkemp.daggerpractice.di;

import android.app.Application;

import com.mkemp.daggerpractice.BaseApplication;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Persists across the entire lifetime of the application.
 *
 * Inject BaseApplication (the client) into this AppComponent (the service)
 */
@Component(
        // Hey component, here are all the modules you'll need to do what I want you to do
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityBuildersModule.class,
                AppModule.class
        }
)
public interface AppComponent extends AndroidInjector<BaseApplication>
{
    /**
     * Build this object using a custom builder.
     */
    @Component.Builder
    interface Builder
    {
        // Pass my app ... it will be available to the modules
        @BindsInstance
        Builder myAwesomeApplication(Application application);
        
        // Override the regular builder
        AppComponent buildTheThing();
    }
}
