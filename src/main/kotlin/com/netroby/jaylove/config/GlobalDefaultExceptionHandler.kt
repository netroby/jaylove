package com.netroby.jaylove.config

import org.slf4j.LoggerFactory
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest


@ControllerAdvice
@RestControllerAdvice
class GlobalDefaultExceptionHandler  {

    private val log = LoggerFactory.getLogger("GlobalDefaultExceptionHandler")

    @ExceptionHandler(value = [ResponseStatusException::class])
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun responseStatusExceptionHandler(req: HttpServletRequest, e: ResponseStatusException) : Map<String, Any> {
        return mapOf("msg" to e.reason.toString(), "data" to arrayOf<String>(), "error" to "403")
    }

    @ExceptionHandler(value = [Exception::class])
    @Throws(Exception::class)
    fun defaultErrorHandler(req: HttpServletRequest, e: Exception): ModelAndView {
        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it - like the OrderNotFoundException example
        // at the start of this post.
        // AnnotationUtils is a Spring Framework utility class.
        if (AnnotationUtils.findAnnotation(e::class.java, ResponseStatus::class.java) != null) {
            log.info("re throw it , let framework process it.")
            throw e
        }

        // Otherwise setup and send the user to a default error-view.
        log.info("Enter the default errorHandler")
        val mav = ModelAndView()
        mav.addObject("exception", e)
        mav.addObject("url", req.requestURL)
        mav.viewName = DEFAULT_ERROR_VIEW
        return mav
    }


    companion object {
        const val DEFAULT_ERROR_VIEW = "error"
    }
}