package au.com.vaishali.practice.presentation.viewmodel

import au.com.vaishali.practice.application.base.BaseViewModel
import au.com.vaishali.practice.application.base.SingleLiveEvent
import au.com.vaishali.practice.application.base.toSingleEvent
import au.com.vaishali.practice.domain.usecase.GetListOfFlickerPhotoUseCase
import au.com.vaishali.practice.presentation.mapper.mapToDomainToPresentationFlickerPhotoItem
import au.com.vaishali.practice.presentation.model.FlickerPhoto
import au.com.vaishali.practice.presentation.viewmodel.HomeViewModel.DisplayEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getListOfFlickerPhotoUseCase: GetListOfFlickerPhotoUseCase) :
    BaseViewModel() {

    sealed class DisplayEvent {
        data class DisplayPhotos(val data: List<FlickerPhoto>) : DisplayEvent()
        object DisplayLoading : DisplayEvent()
        object FinishLoading : DisplayEvent()
        object DisplayEmptyList : DisplayEvent()
        data class DisplayError(val error: String) : DisplayEvent()
    }

    private val _getListOfFlickerPhoto = SingleLiveEvent<DisplayEvent>()
    val getListOfFlickerPhoto = _getListOfFlickerPhoto.toSingleEvent()

    var job: Job? = null

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _getListOfFlickerPhoto.postValue(DisplayError("${throwable.localizedMessage}"))
    }

    fun textGetsChanged() {
        _getListOfFlickerPhoto.value = DisplayEmptyList
    }

    fun searchPhoto(searchText: String) =
        getListOfFlickerPhotos(searchText)

    private fun getListOfFlickerPhotos(searchText: String) {
        _getListOfFlickerPhoto.postValue(DisplayLoading)
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = mapToDomainToPresentationFlickerPhotoItem(
                getListOfFlickerPhotoUseCase.getListOfFlickerPhotos(searchName = searchText)
            )
            if (response.isNullOrEmpty()) {
                _getListOfFlickerPhoto.postValue(DisplayEmptyList)
            } else {
                _getListOfFlickerPhoto.postValue(DisplayPhotos(response))
            }
        }
        _getListOfFlickerPhoto.postValue(FinishLoading)
    }
}