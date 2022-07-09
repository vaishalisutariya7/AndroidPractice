package au.com.vaishali.practice.presentation.mapper

import au.com.vaishali.practice.domain.model.FlickerPhotos
import au.com.vaishali.practice.presentation.model.FlickerPhoto

fun mapToDomainToPresentationFlickerPhotoItem(flickerPhotos: FlickerPhotos): List<FlickerPhoto>? =
    flickerPhotos.photo?.map {
        FlickerPhoto(
            it.id,
            "https://farm${it.farm}.static.flickr.com/${it.server}/${it.id}_${it.secret}.jpg",
            if (it.title.isNullOrEmpty()) "N/A" else it.title
        )
    }