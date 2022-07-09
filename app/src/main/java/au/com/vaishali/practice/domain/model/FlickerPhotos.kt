package au.com.vaishali.practice.domain.model


data class FlickerPhotos(
    var photo: List<Photo>?
) {
    data class Photo(
        var id: String? = null,
        var owner: String? = null,
        var secret: String? = null,
        var server: String? = null,
        var farm: Int? = null,
        var title: String? = null
    )
}