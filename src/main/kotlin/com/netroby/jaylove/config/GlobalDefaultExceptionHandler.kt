package com.netroby.jaylove.config

import org.springframework.web.servlet.ModelAndView
import org.springframework.core.annotation.AnnotationUtils
import javax.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus


@ControllerAdvice
internal class GlobalDefaultExceptionHandler {

    @ExceptionHandler(value = [Exception::class])
    @Throws(Exception::class)
    fun defaultErrorHandler(req: HttpServletRequest, e: Exception): ModelAndView {
        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it - like the OrderNotFoundException example
        // at the start of this post.
        // AnnotationUtils is a Spring Framework utility class.
        if (AnnotationUtils.findAnnotation(e.javaClass, ResponseStatus::class.java) != null)
            throw e

        // Otherwise setup and send the user to a default error-view.
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