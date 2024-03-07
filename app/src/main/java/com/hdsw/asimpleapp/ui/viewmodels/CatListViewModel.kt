package com.hdsw.asimpleapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.hdsw.asimpleapp.data.model.Cat
import com.hdsw.asimpleapp.data.repository.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class CatListViewModel @Inject constructor(repository: CatRepository) : ViewModel() {
    val cats: Flow<List<Cat>> = repository.getCats().flowOn(Dispatchers.IO)
}
