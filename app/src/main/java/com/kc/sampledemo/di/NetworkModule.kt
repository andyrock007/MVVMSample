package com.kc.sampledemo.di


import com.kc.sampledemo.data.local.AppDatabase
import com.kc.sampledemo.data.network.HeaderInterceptor
import com.kc.sampledemo.data.network.WebApi
import com.kc.sampledemo.data.repo.DataRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val networkModule = Kodein.Module("networkModule"){
    bind() from  singleton {
        HeaderInterceptor()
    }

    bind() from singleton {
        WebApi(headerInterceptor = instance())
    }

    bind() from  singleton {
        DataRepository(api = instance())
    }
}

val dbModule = Kodein.Module("dbModule"){
    bind() from singleton {
        AppDatabase.getDatabase(context = instance())
    }
}

