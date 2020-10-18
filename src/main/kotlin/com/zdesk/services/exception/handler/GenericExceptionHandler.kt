package com.zdesk.services.exception.handler

import com.zdesk.services.exception.GenericException
import com.zdesk.services.exception.model.GenericErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
public class GenericExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(GenericException::class)
    @ResponseBody
    internal fun onException(ex: GenericException): GenericErrorResponse {
        val errorResponse = GenericErrorResponse(HttpStatus.BAD_REQUEST, "Something went wrong! Please try again.".plus(ex.message))
        return errorResponse
    }
}
