package com.hsung.impactserver.global.global.error.data

import com.hsung.impactserver.global.global.error.ErrorCode

open class GlobalError(
    val errorCode: ErrorCode,
    vararg args: Any
) : RuntimeException(errorCode.parse(args))