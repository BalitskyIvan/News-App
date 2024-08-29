package balitsky.newsapp.presentation.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import balitsky.newsapp.domain.model.NewsModel
import balitsky.newsapp.domain.usecase.GetNewsUseCase
import balitsky.newsapp.presentation.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
): ViewModel() {

    private val _newsList = MutableStateFlow<List<NewsModel>>(listOf())
    val newsList = _newsList.asStateFlow()

    private val _error = MutableStateFlow("")
    val error = _error.asStateFlow()

    private val _isLoadingVisible = MutableStateFlow(true)
    val isLoadingVisible = _isLoadingVisible.asStateFlow()

    init {
        fetchState(isForced = false)
    }

    fun fetchNewList() {
        fetchState(isForced = true)
    }

    private fun fetchState(isForced: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            getNewsUseCase.invoke(isForced).collect { newState ->
                when (newState) {
                    is UiState.Loading -> {
                        _newsList.update { listOf() }
                        _error.update { "" }
                        _isLoadingVisible.update { true }
                    }
                    is UiState.Error -> {
                        _newsList.update { listOf() }
                        _error.update { newState.message }
                        _isLoadingVisible.update { false }
                    }
                    is UiState.Success -> {
                        _newsList.update { newState.data }
                        _error.update { "" }
                        _isLoadingVisible.update { false }
                    }
                }
            }
        }
    }
}
