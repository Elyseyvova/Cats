package eliseev.facts.repositories.facts

import eliseev.facts.data.result.FactResult

interface FactsRepository {

    suspend fun randomFact(): FactResult
}
