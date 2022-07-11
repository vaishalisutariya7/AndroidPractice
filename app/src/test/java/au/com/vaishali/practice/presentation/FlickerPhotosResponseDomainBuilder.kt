package au.com.vaishali.practice.presentation

import au.com.vaishali.practice.domain.model.FlickerPhotos
import au.com.vaishali.practice.presentation.model.FlickerPhoto

fun flickerPhotosResponseDomainBuilder(
    photo: List<FlickerPhotos.Photo> =
        buildFlickerDomainPhotos()
): FlickerPhotos = FlickerPhotos(photo)

private fun buildFlickerDomainPhotos(
    id: String? = "52208012689",
    owner: String? = "38188346@N03",
    secret: String? = "683bc0834a",
    server: String? = "65535",
    farm: Int? = 66,
    title: String? = "2022-07-08 129 kitten"
): List<FlickerPhotos.Photo> = listOf(
    FlickerPhotos.Photo(id, owner, secret, server, farm, title)
)

fun mapDomainToPresentationFlickerResponseBuilder(
    id: String? = "52208012689",
    imageUrl: String? = "https://farm66.static.flickr.com/65535/52208012689_683bc0834a.jpg",
    title: String? = "2022-07-08 129 kitten"
): List<FlickerPhoto> = listOf(FlickerPhoto(id, imageUrl, title))