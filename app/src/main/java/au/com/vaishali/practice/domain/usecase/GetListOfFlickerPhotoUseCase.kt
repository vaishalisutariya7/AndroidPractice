package au.com.vaishali.practice.domain.usecase

import au.com.vaishali.practice.domain.model.FlickerPhotos
import au.com.vaishali.practice.domain.repository.FlickerRepository
import javax.inject.Inject

class GetListOfFlickerPhotoUseCase @Inject constructor(private val flickerRepository: FlickerRepository) {
    suspend fun getListOfFlickerPhotos(searchName: String): FlickerPhotos =
        flickerRepository.getFlickerListOfPhotos(searchName)
}