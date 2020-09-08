package com.mkemp.daggerpractice.di;

import android.app.Application;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.mkemp.daggerpractice.R;
import com.mkemp.daggerpractice.network.ExampleInterceptor;

import javax.inject.Singleton;

import androidx.core.content.ContextCompat;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule
{
    // Put app dependencies in here like retrofit, glide, etc
    
    @Provides
    @Singleton
    ExampleInterceptor provideInterceptor() { // This is where the Interceptor object is constructed
        return new ExampleInterceptor();
    }
    
    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(ExampleInterceptor interceptor) { // The Interceptor is then added to the client
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }
    
    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance(OkHttpClient okHttpClient)
    {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl("http://localhost/")
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
