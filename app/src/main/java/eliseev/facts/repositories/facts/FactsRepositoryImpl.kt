package eliseev.facts.repositories.facts

import eliseev.facts.data.models.Fact
import eliseev.facts.data.network.CatPhotosAPI
import eliseev.facts.data.network.FactsAPI
import eliseev.facts.data.result.FactResult

class FactsRepositoryImpl(
    private val factsApi: FactsAPI,
    private val catPhotosApi: CatPhotosAPI
) : FactsRepository {

    override suspend fun randomFact() = try {
        val factResponse = factsApi.randomFactAsync().await()
        val catPhotoResponse = catPhotosApi.randomCatPhotoAsync().await()

        if (!factResponse.isSuccessful) FactResult.Failure(Exception("Error load fact..."))
        if (factResponse.body() == null) FactResult.Failure(Exception("Error load fact..."))

        if (!catPhotoResponse.isSuccessful) FactResult.Failure(Exception("Error load photo..."))
        if (catPhotoResponse.body() == null) FactResult.Failure(Exception("Error load photo..."))

        FactResult.Success(Fact.fromResponses(factResponse, catPhotoResponse))
    } catch (e: Exception) {
        FactResult.Failure(e)
    }
}
