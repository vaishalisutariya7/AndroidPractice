package au.com.vaishali.practice.data

import au.com.vaishali.practice.data.model.FlickerPhotoResponse

fun flickerPhotosResponseBuilder(
    photos: FlickerPhotoResponse.Photos? = buildPhotosData(),
    stat: String? = "ok"
): FlickerPhotoResponse =
    FlickerPhotoResponse(photos, stat)

private fun buildPhotosData(
    page: Int? = 1,
    pages: Int? = 1215,
    perpage: Int? = 100,
    total: Int? = 121414,
    photo: List<FlickerPhotoResponse.Photo>? = buildPhotoData()
): FlickerPhotoResponse.Photos? = FlickerPhotoResponse.Photos(
    page,
    pages,
    perpage,
    total,
    photo
)

private fun buildPhotoData(
    id: String? = "52208012689",
    owner: String? = "38188346@N03",
    secret: String? = "683bc0834a",
    server: String? = "65535",
    farm: Int? = 66,
    title: String? = "2022-07-08 129 kitten",
    ispublic: Int? = 1,
    isfriend: Int? = 0,
    isfamily: Int? = 0
): List<FlickerPhotoResponse.Photo>? = listOf(
    FlickerPhotoResponse.Photo(
        id,
        owner,
        secret,
        server,
        farm,
        title,
        ispublic,
        isfriend,
        isfamily
    )
)
