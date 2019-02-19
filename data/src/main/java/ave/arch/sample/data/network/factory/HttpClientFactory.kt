package ave.arch.sample.data.network.factory

import com.ave.arch.sample.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object HttpClientFactory {

    private const val CONNECT_TIMEOUT_MILLIS = 120000L
    private const val READ_TIMEOUT_MILLIS = 120000L

    fun okHttpClient(builder: OkHttpClient.Builder.() -> Unit): OkHttpClient {
        return getPreConfiguredClientBuilder()
                .apply {
                    builder(this)
                    addInterceptor(getLoggingInterceptor())
                }
                .build()
    }

    private fun getPreConfiguredClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder().apply {
            connectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
            readTimeout(READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
        }
    }

    private fun getLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }
}
