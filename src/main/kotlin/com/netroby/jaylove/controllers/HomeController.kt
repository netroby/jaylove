package com.netroby.jaylove.controllers

import com.netroby.jaylove.config.JayloveConfig
import com.netroby.jaylove.service.AuthAdapterService
import com.netroby.jaylove.service.PrepareModelService
import com.netroby.jaylove.repository.ArticleRepository
import com.netroby.jaylove.vo.Article
import com.netroby.jaylove.vo.ArticleAdd
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
class HomeController(
        @Autowired private val articleRepository: ArticleRepository,
        @Autowired private val prepareModelService: PrepareModelService,
        @Autowired private val authAdapterService: AuthAdapterService,
        @Autowired private val jayloveConfig: JayloveConfig
        ) {


    private val logger = LoggerFactory.getLogger("index")

    @GetMapping("/")
    fun home(model: Model, @RequestParam(value = "page", defaultValue = "0") page: Int): ModelAndView {
        model.addAllAttributes(prepareModelService.getModel())
        val sort = Sort(Sort.Direction.DESC, "aid")
        val pageable = PageRequest.of(page, 15, sort)
        val result = articleRepository.findAll(pageable)
        model.addAttribute("result", result.content)
        logger.info("Got result: {}", result.content)
        val role = SimpleGrantedAuthority("ROLE_ADMIN")
        model.addAttribute("username", authAdapterService.getUserName())
        model.addAttribute("isAuthenticated", authAdapterService.isAuthenticated())
        model.addAttribute("nextPage", page+1)
        var prevPage = page - 1;
        if (prevPage < 0) {
            prevPage = 0;
        }
        model.addAttribute("prevPage", prevPage)
        return ModelAndView("index")
    }

    @RequestMapping("/login")
    fun login(): String {
        return "login"
    }
    @RequestMapping("/logout")
    fun logout(request: HttpServletRequest, response: HttpServletResponse): String {
        val auth = authAdapterService.getAuthentication()
        if (auth != null) {
            SecurityContextLogoutHandler().logout(request, response, auth)
        }
        return "redirect:/"
    }

    @PostMapping("/saveAdd")
    fun saveAdd(model: Model, articleAdd: ArticleAdd): ModelAndView {
        val article = Article(content = articleAdd.content,
                publishStatus = 1 )
        var articleString = article.toString()
        if (articleString.length > 220) {
            articleString = articleString.substring(0..220)
        }
        logger.info("article {}", articleString)
        this.articleRepository.save(article)
        model.addAttribute("message", "Success")
        return ModelAndView("message")
    }
}