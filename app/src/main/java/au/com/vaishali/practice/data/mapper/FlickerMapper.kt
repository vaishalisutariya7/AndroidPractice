package au.com.vaishali.practice.data.mapper

import au.com.vaishali.practice.data.model.FlickerPhotoResponse
import au.com.vaishali.practice.data.model.FlickerPhotoResponse.*
import au.com.vaishali.practice.domain.model.FlickerPhotos as DomainFlickerPhotos
import au.com.vaishali.practice.domain.model.FlickerPhotos.Photo as DomainPhoto

fun mapDataToDomainFlickerPhotos(flickerPhotoResponse: FlickerPhotoResponse): DomainFlickerPhotos =
    DomainFlickerPhotos(
        mapDataToDomainPhoto(flickerPhotoResponse.photos?.photo)
    )

fun mapDataToDomainPhoto(photo: List<Photo>?): List<DomainPhoto>? =
    photo?.map {
        DomainPhoto(
            it.id,
            it.owner,
            it.secret,
            it.server,
            it.farm,
            it.title
        )
    }

