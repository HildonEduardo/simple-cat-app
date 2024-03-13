package com.hdsw.asimpleapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.hdsw.asimpleapp.data.model.Cat
import com.hdsw.asimpleapp.data.repository.impl.CatRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class CatDetailViewModel @Inject constructor(private val repository: CatRepositoryImpl) : ViewModel() {
    fun getCat(catId: String): Flow<Cat> = repository.getCat(catId).flowOn(Dispatchers.IO)
}
