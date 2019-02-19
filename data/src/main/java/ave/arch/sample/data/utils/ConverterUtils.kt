package ave.arch.sample.data.utils

import com.ave.arch.sample.domain.exceptions.network.ConvertException

@Suppress("NOTHING_TO_INLINE")
inline fun <T> getOrDie(item: T?, binding: String): T = item
        ?: throw ConvertException("'$binding' must not be null")


