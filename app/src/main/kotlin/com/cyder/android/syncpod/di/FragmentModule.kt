package com.cyder.android.syncpod.di

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.cyder.android.syncpod.di.scope.FragmentScope
import dagger.Module
import dagger.Provides

/**
 * Created by chigichan24 on 2018/01/12.
 */
@Module
class FragmentModule(private val fragment: Fragment) {
    @Provides
    @FragmentScope
    fun provideFragmentManager(): FragmentManager? = fragment.fragmentManager
}