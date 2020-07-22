package eliseev.facts.data.network

import eliseev.facts.data.models.Fact
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface FactsAPI {

    @GET("/facts/random")
    fun randomFactAsync(): Deferred<Response<Fact>>
}
