package com.hsung.impactserver.global.security.exception

import com.hsung.impactserver.global.global.error.ErrorCode
import com.hsung.impactserver.global.global.error.data.GlobalError

class InvalidParameterException : GlobalError(ErrorCode.INVALID_PARAMETER)