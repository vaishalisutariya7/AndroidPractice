package au.com.vaishali.practice.data.api

import au.com.vaishali.practice.data.model.FlickerPhotoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickerService {
    @GET("rest")
    suspend fun getFlickerPhotos(
        @Query("method") method: String,
        @Query("api_key") api_key: String,
        @Query("text") searchName: String,
        @Query("format") format: String,
        @Query("nojsoncallback") nojsoncallback: Int
    ): FlickerPhotoResponse
}