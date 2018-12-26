package com.netroby.jaylove.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class HomeController {
    @GetMapping("/")
    fun home(model: Model): ModelAndView {
        return ModelAndView("index")
    }
}