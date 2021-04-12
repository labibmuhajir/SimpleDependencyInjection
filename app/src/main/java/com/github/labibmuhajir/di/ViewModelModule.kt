package com.github.labibmuhajir.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.github.labibmuhajir.di.InjectEngine.ACTIVITY
import com.github.labibmuhajir.di.InjectEngine.FRAGMENT
import com.github.labibmuhajir.di.InjectEngine.getInjection
import com.github.labibmuhajir.domain.factory.MainFactory
import com.github.labibmuhajir.presentation.MainViewModel

val viewModelModule = listOf(
    ProvidedObject(
        lazy {
            ViewModelProviders.of(
                getInjection<FragmentActivity>(),
                MainFactory(getInjection())
            ).get(
                MainViewModel::class.java
            )
        }, ACTIVITY, MainViewModel::class.java
    ),
    ProvidedObject(
        lazy {
            ViewModelProviders.of(
                getInjection<Fragment>(),
                MainFactory(getInjection())
            ).get(
                MainViewModel::class.java
            )
        }, FRAGMENT, MainViewModel::class.java
    )
)