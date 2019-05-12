package com.satish.nytimesmvvm.view_model

import androidx.lifecycle.ViewModel
import com.satish.nytimesmvvm.repository.NyRepository
import javax.inject.Inject

class ArticleViewModel @Inject
constructor(private val nyRepository: NyRepository) : ViewModel() {
}