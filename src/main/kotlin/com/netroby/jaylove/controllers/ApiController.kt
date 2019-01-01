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
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api")
class ApiController(
        @Autowired private val articleRepository: ArticleRepository,
        @Autowired private val prepareModelService: PrepareModelService,
        @Autowired private val authAdapterService: AuthAdapterService,
        @Autowired private val jayloveConfig: JayloveConfig
        ) {


    private val logger = LoggerFactory.getLogger("index")

    @GetMapping("/")
    fun home(@RequestParam("token") token: String, @RequestParam(value = "page", defaultValue = "0") page: Int): Map<String, Any> {
        val sort = Sort(Sort.Direction.DESC, "aid")
        val pageable = PageRequest.of(page, 15, sort)
        val result = articleRepository.findAll(pageable)
        logger.info("Got result: {}", result.content)
        var prevPage = page - 1;
        if (prevPage < 0) {
            prevPage = 0;
        }
        val blogList: List<String> = listOf("a", "b")
        return mapOf("data" to blogList)
    }

    @PostMapping("/login")
    fun login(@RequestParam("username") username: String, @RequestParam("password") password: String): Map<String, String> {
        return mapOf("msg" to "Success", "token" to "1234")
    }
    @PostMapping("/logout")
    fun logout(@RequestParam("token") token: String): Map<String, String> {
        return mapOf("msg" to "success")
    }

    @PostMapping("/save-blog-add")
    fun saveAdd(articleAdd: ArticleAdd): Map<String, String> {
        val article = Article(content = articleAdd.content,
                publishStatus = 1 )
        var articleString = article.toString()
        if (articleString.length > 220) {
            articleString = articleString.substring(0..220)
        }
        logger.info("article {}", articleString)
        this.articleRepository.save(article)
        return mapOf("msg" to "success")
    }
    @PostMapping("/file-upload")
    fun fileUpload(@RequestParam("token") token : String, @RequestParam("uploadfile") file: MultipartFile): Map<String, String> {
        return mapOf("url" to "http://www.baidu.com")
    }
}