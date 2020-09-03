package com.mkemp.daggerpractice.di;

import android.app.Application;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.mkemp.daggerpractice.R;
import com.mkemp.daggerpractice.util.Constants;

import javax.inject.Singleton;

import androidx.core.content.ContextCompat;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule
{
    // Put app dependencies in here like retrofit, glide, etc
    
    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance()
    {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    
    @Singleton // Scope them to the AppComponent scope
    @Provides
    static RequestOptions provideRequestOptions()
    {
        // For Glide
        return RequestOptions
                .placeholderOf(R.drawable.white_background)
                .error(R.drawable.white_background);
    }
    
    @Singleton
    @Provides
    static RequestManager provideGlideInstance(Application application,
                                               RequestOptions requestOptions)
    {
        return Glide.with(application).setDefaultRequestOptions(requestOptions);
    }
    
    @Singleton
    @Provides
    static Drawable provideAppDrawable(Application application)
    {
        return ContextCompat.getDrawable(application, R.drawable.logo);
    }
}
