package com.kc.sampledemo

import android.app.Application
import com.kc.sampledemo.di.dbModule
import com.kc.sampledemo.di.networkModule
import com.kc.sampledemo.di.viewModelModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule


class App : Application() , KodeinAware {
    override val kodein: Kodein
        get() = Kodein.lazy {
            import(androidXModule(this@App))
            import(networkModule)
            import(dbModule)
            import(viewModelModule)
        }
}