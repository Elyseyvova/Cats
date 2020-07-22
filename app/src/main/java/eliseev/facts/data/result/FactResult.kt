package eliseev.facts.data.result

import eliseev.facts.data.models.Fact

sealed class FactResult {
    data class Success(val fact: Fact) : FactResult()
    data class Failure(val error: Exception) : FactResult()
}
