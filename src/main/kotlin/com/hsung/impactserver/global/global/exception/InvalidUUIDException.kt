package com.hsung.impactserver.global.global.exception

import com.hsung.impactserver.global.global.error.ErrorCode
import com.hsung.impactserver.global.global.error.data.GlobalError

class InvalidUUIDException(target: String) : GlobalError(ErrorCode.INVALID_UUID, target)