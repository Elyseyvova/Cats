package eliseev.facts.di

import com.google.gson.GsonBuilder
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import eliseev.facts.BuildConfig
import eliseev.facts.data.network.CatPhotosAPI
import eliseev.facts.data.network.FactsAPI
import eliseev.facts.repositories.facts.FactsRepositoryImpl
import eliseev.facts.ui.screens.main.MainPresenter
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {

    val gson =
        GsonBuilder()
            .setPrettyPrinting()
            .create()

    val okHttpInterceptorLogging = LoggingInterceptor.Builder()
        .loggable(BuildConfig.DEBUG)
        .setLevel(Level.BODY)
        .log(Platform.INFO)
        .tag("REST")
        .build()

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(okHttpInterceptorLogging)
        .readTimeout(15, TimeUnit.MINUTES)
        .writeTimeout(15, TimeUnit.MINUTES)
        .callTimeout(15, TimeUnit.MINUTES)
        .build()

    val factsApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BuildConfig.FACTS_HOST)
        .client(okHttpClient)
        .build()
        .create(FactsAPI::class.java)

    val catPhotosApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BuildConfig.CAT_PHOTOS_HOST)
        .client(okHttpClient)
        .build()
        .create(CatPhotosAPI::class.java)

    val repository = FactsRepositoryImpl(factsApi, catPhotosApi)

    factory { MainPresenter(repository) }
}

