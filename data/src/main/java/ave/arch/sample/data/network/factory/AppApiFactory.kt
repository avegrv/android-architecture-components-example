package ave.arch.sample.data.network.factory

import com.ave.arch.sample.domain.exceptions.network.NetworkException
import com.ave.arch.sample.domain.exceptions.network.ServerException
import ave.arch.sample.data.network.ServerUrls
import java.io.IOException
import javax.inject.Inject

class AppApiFactory @Inject constructor() : ApiFactory(
        ServerUrls.Github(),
        HttpClientFactory.okHttpClient {

            // Error interceptor
            addInterceptor { chain ->
                val response = try {
                    chain.proceed(chain.request())
                } catch (th: Throwable) {
                    when(th) {
                        is IOException -> throw NetworkException()
                        else -> throw th
                    }
                }
                if (!response.isSuccessful) {
                    throw ServerException(response.code())
                }
                response
            }
        }
)