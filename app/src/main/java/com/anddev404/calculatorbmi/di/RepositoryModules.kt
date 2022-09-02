package com.anddev404.calculatorbmi.di

import com.anddev404.calculatorbmi.data.Repository
import org.koin.dsl.module

val repositoryModules = module {
    single { Repository(get()) }
}
