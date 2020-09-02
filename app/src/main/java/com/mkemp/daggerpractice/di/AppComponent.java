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
        modules = {
                AndroidSupportInjectionModule.class,
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
        // Pass my app
        @BindsInstance
        Builder myAwesomeApplication(Application application);
        
        // Override the regular builder
        AppComponent buildTheThing();
    }
}
