package com.ave.arch.sample.domain.exceptions.network

open class ServerException(val code: Int) : RuntimeException("error code=$code")
