package com.zdesk.services.exception.model

import org.springframework.http.HttpStatus

data class GenericErrorResponse(val status: HttpStatus, val message: String)
