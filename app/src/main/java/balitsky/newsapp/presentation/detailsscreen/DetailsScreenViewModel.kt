package balitsky.newsapp.presentation.detailsscreen

import androidx.lifecycle.ViewModel
import balitsky.newsapp.domain.usecase.GetNewsDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val getNewsDetailUseCase: GetNewsDetailUseCase
): ViewModel() {

    private val _urlImage = MutableStateFlow("")
    val urlImage = _urlImage.asStateFlow()

    private val _title = MutableStateFlow("")
    val title = _title.asStateFlow()

    private val _content = MutableStateFlow("")
    val content = _content.asStateFlow()

    fun initWithId(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val details = getNewsDetailUseCase.invoke(id)

            _urlImage.update { details?.imageUrl ?: "" }
            _content.update { details?.content ?: "" }
            _title.update { details?.title ?: "" }
        }
    }
}
