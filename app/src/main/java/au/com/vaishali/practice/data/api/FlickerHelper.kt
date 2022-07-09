package au.com.vaishali.practice.data.api

import au.com.vaishali.practice.data.mapper.mapDataToDomainFlickerPhotos
import javax.inject.Inject

private const val apiKey = "96358825614a5d3b1a1c3fd87fca2b47"
private const val method = "flickr.photos.search"
private const val format = "json"
private const val noJsonCallBack = 1

class FlickerHelper @Inject constructor(private val flickerService: FlickerService) {

    suspend fun getFlickerPhotos(searchName: String) =
        mapDataToDomainFlickerPhotos(
            flickerService.getFlickerPhotos(
                method, apiKey, searchName,
                format, noJsonCallBack
            )
        )
}