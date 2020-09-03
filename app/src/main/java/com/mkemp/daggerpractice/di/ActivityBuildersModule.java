package com.mkemp.daggerpractice.di;

import com.mkemp.daggerpractice.di.auth.AuthViewModelsModule;
import com.mkemp.daggerpractice.ui.auth.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Dependencies live in the modules.
 *
 * Here you can specify potential clients to inject into.
 *
 * Can kind of be reused across projects!
 */
@Module
public abstract class ActivityBuildersModule
{
    @ContributesAndroidInjector(
            modules = {
                    // This is only included in this sub component
                    // Only auth activity will be able to use this view model
                    AuthViewModelsModule.class
            }
    )
    abstract AuthActivity contributeAuthActivity();
}
