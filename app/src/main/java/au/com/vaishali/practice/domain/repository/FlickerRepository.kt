package au.com.vaishali.practice.domain.repository

import au.com.vaishali.practice.data.api.FlickerHelper
import au.com.vaishali.practice.domain.model.FlickerPhotos
import javax.inject.Inject

class FlickerRepository @Inject constructor(private val flickerHelper: FlickerHelper) {
    suspend fun getFlickerListOfPhotos(searchName: String): FlickerPhotos =
        flickerHelper.getFlickerPhotos(searchName)
}