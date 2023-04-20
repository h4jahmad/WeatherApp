package com.swensonhe.common.util

interface LocalDtoMapper<Receiver, Result> {
    fun toLocalDto(data: Receiver): Result
}