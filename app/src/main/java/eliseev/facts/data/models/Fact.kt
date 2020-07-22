package eliseev.facts.data.models

import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

data class Fact(
    var text: String,
    var createdAt: String?,
    var photo: String
) {
    companion object {

        fun fromResponses(
            factResponse: Response<Fact>,
            catPhotosResponse: Response<List<CatPhoto>>
        ) = Fact(
            text = factResponse.body()!!.text,
            createdAt = factResponse.body()!!.createdAt,
            photo = catPhotosResponse.body()!!.first().url
        )
    }

    val formattedCreatedAt: String
        get() {
            val inputDate =
                SimpleDateFormat("yyyy-mm-dd'T'hh:MM:ss", Locale.ENGLISH).parse(createdAt)
            return SimpleDateFormat("hh:MM, dd MMMM yyyy", Locale.ENGLISH).format(inputDate)
        }
}
