package com.alexmls.core.domain.util

class DataErrorException(
    val error: DataError
): Exception()