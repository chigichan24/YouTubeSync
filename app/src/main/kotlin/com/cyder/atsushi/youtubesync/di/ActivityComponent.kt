package com.cyder.atsushi.youtubesync.di

import com.cyder.atsushi.youtubesync.di.scope.ActivityScope
import dagger.Subcomponent

/**
 * Created by chigichan24 on 2018/01/12.
 */

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    //TODO when you create Activity, you have to create inject method

    fun plus(module: FragmentModule): FragmentComponent
}