package com.mkemp.daggerpractice.di;

import com.mkemp.daggerpractice.viewmodels.ViewModelProviderFactory;

import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;

/**
 * Provides the dependency for the ViewModelFactory
 *
 * Can probably be reused across projects!
 */
@Module
public abstract class ViewModelFactoryModule
{
    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelProviderFactory);
}
