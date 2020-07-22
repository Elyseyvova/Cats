package eliseev.facts.data.network

import eliseev.facts.data.models.CatPhoto
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface CatPhotosAPI {

    @GET("/v1/images/search")
    fun randomCatPhotoAsync(): Deferred<Response<List<CatPhoto>>>
}
