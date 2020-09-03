package com.mkemp.daggerpractice.viewmodels;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Issue with injecting View Models: they must have empty constructors by default.
 * We can use constructor injection... but how to make that work with Dagger?
 *
 * We'll need a custom view model factory.
 *
 * This class can be reused in all projects!
 */
public class ViewModelProviderFactory implements ViewModelProvider.Factory
{
    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> creators;
    
    @Inject
    public ViewModelProviderFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> creators)
    {
        this.creators = creators;
    }
    
    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass)
    {
        Provider<? extends ViewModel> creator = creators.get(modelClass);
    
        // If the view model has not been created
        if ( creator == null )
        {
            
            // Loop through the allowable keys (aka allowed classes with the @ViewModelKey)
            for ( Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : creators.entrySet() )
            {
                // If it's allowed, set the Provider<ViewModel>
                if ( modelClass.isAssignableFrom(entry.getKey()) )
                {
                    creator = entry.getValue();
                    break;
                }
            }
        }
        
        // If this is not one of the allowed keys, throw exception
        if ( creator == null )
        {
            throw new IllegalArgumentException("Unknown model class " + modelClass);
        }
        
        // Return the Provider
        try
        {
            return (T) creator.get();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}