package com.hsung.impactserver.global.security.exception

import com.hsung.impactserver.global.global.error.ErrorCode
import com.hsung.impactserver.global.global.error.data.GlobalError

class UserIdAlreadyExistsException : GlobalError(ErrorCode.USER_ID_ALREADY_EXISTS)