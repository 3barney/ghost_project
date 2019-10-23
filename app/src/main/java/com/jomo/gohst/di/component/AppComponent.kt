package com.jomo.gohst.di.component

import com.jomo.gohst.MainApplication
import com.jomo.gohst.di.modules.AppModule
import com.jomo.gohst.di.modules.BuildersModule
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(AndroidInjectionModule::class, AppModule::class, BuildersModule::class)
)
interface AppComponent {
    fun inject(app : MainApplication) // Inject your activity not android App
}