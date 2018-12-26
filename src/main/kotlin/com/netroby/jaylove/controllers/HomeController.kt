package com.netroby.jaylove.controllers

import com.netroby.jaylove.config.JayloveConfig
import com.netroby.jaylove.service.AuthAdapterService
import com.netroby.jaylove.service.PrepareModelService
import com.netroby.josense.repository.ArticleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

@Controller
class HomeController(
        @Autowired private val articleRepository: ArticleRepository,
        @Autowired private val prepareModelService: PrepareModelService,
        @Autowired private val authAdapterService: AuthAdapterService,
        @Autowired private val jayloveConfig: JayloveConfig
        ) {
    fun home(model: Model, @RequestParam(value = "page", defaultValue = "0") page: Int): ModelAndView {
        model.addAllAttributes(prepareModelService.getModel())
        val sort = Sort(Sort.Direction.DESC, "aid")
        val pageable = PageRequest.of(page, 15, sort)
        val result = articleRepository.findAll(pageable)
        model.addAttribute("result", result.content)
        val role = SimpleGrantedAuthority("ROLE_ADMIN")
        model.addAttribute("username", authAdapterService.getUserName())
        model.addAttribute("isAuthenticated", authAdapterService.isAuthenticated())
        model.addAttribute("nextPage", page+1)
        var prevPage = page - 1;
        if (prevPage < 0) {
            prevPage = 0;
        }
        model.addAttribute("prevPage", prevPage)
        return ModelAndView("home")
    }

    @RequestMapping("/login")
    fun login(): String {
        return "login"
    }
}