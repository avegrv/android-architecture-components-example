package ave.arch.sample.data.network.factory

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ave.arch.sample.data.network.ServerUrls

abstract class ApiFactory(
        private val serverUrl: ServerUrls.ServerUrl,
        private val httpClient: OkHttpClient
) {

    fun <T> create(clazz: Class<T>): T {
        return Retrofit.Builder()
                .baseUrl(serverUrl.url)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(clazz)
    }
}