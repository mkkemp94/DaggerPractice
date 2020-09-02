package com.mkemp.daggerpractice.di;

import android.app.Application;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.mkemp.daggerpractice.R;

import androidx.core.content.ContextCompat;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule
{
    // Put app dependencies in here like retrofit, glide, etc
    
    @Provides
    static RequestOptions provideRequestOptions()
    {
        // For Glide
        return RequestOptions
                .placeholderOf(R.drawable.white_background)
                .error(R.drawable.white_background);
    }
    
    @Provides
    static RequestManager provideGlideInstance(Application application,
                                               RequestOptions requestOptions)
    {
        return Glide.with(application).setDefaultRequestOptions(requestOptions);
    }
    
    @Provides
    static Drawable provideAppDrawable(Application application)
    {
        return ContextCompat.getDrawable(application, R.drawable.logo);
    }
}
