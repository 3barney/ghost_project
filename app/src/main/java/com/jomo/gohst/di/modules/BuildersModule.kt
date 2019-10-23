package com.jomo.gohst.di.modules

import com.jomo.gohst.AddDreamActivity
import com.jomo.gohst.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivities(): MainActivity

    @ContributesAndroidInjector
    abstract fun constributeAddDreamActivity(): AddDreamActivity
}