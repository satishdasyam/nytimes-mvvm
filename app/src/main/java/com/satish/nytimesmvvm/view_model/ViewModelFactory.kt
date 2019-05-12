package com.satish.nytimesmvvm.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider


@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject
constructor(private val mProviderMap: Map<Class<out ViewModel>, Provider<ViewModel>>) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return mProviderMap[modelClass]?.get() as T
    }
}