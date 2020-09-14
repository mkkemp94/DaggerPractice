package com.mkemp.daggerpractice.di.main;

import com.mkemp.daggerpractice.network.main.MainApi;
import com.mkemp.daggerpractice.ui.main.posts.PostsRecyclerAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule
{
    @MainScope
    @Provides
    static PostsRecyclerAdapter provideAdapter()
    {
        return new PostsRecyclerAdapter();
    }
    
    @MainScope
    @Provides
    static MainApi provideMainApi(Retrofit retrofit)
    {
        return retrofit.create(MainApi.class);
    }
}
