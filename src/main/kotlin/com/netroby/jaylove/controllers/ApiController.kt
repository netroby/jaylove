package com.netroby.jaylove.controllers

import com.netroby.jaylove.config.AccountConfig
import com.netroby.jaylove.config.JayloveConfig
import com.netroby.jaylove.repository.AccessTokenRepository
import com.netroby.jaylove.repository.ArticleRepository
import com.netroby.jaylove.service.StorageService
import com.netroby.jaylove.utils.HashUtils
import com.netroby.jaylove.vo.AccessToken
import com.netroby.jaylove.vo.ApiArticleAdd
import com.netroby.jaylove.vo.Article
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.server.ResponseStatusException
import java.time.Instant
import javax.servlet.http.HttpServletRequest

@RestController
class ApiController(
        @Autowired private val articleRepository: ArticleRepository,
        @Autowired private val tokenRepository: AccessTokenRepository,
        @Autowired private val jayloveConfig: JayloveConfig,
        @Autowired private val accountConfig: AccountConfig,
        @Autowired private val storageService: StorageService
        ) {


    private val logger = LoggerFactory.getLogger("index")

    @PostMapping("/api", "/api/list")
    fun home(@RequestBody req: Map<String, Any>): Map<String, Any> {
        tokenValidCheck(req["token"].toString())
        val sort = Sort(Sort.Direction.DESC, "aid")
        val pageable = PageRequest.of(req["page"].toString().toInt(), 15, sort)
        val result = articleRepository.findAll(pageable)
        if (!result.isEmpty) {
            logger.info("Got result: {}", result.content)
            return mapOf("data" to result.content)
        }
        return mapOf("data" to arrayOf<Any>())
    }

    @PostMapping("/api/login")
    fun login(@RequestBody req: Map<String, Any>): Map<String, String> {
        return if (req["username"].toString() == accountConfig.username && req["password"].toString() == accountConfig.password) {
            val data = AccessToken()
            data.token = HashUtils.sha256WithPrefix(Instant.EPOCH.epochSecond.toString(), "xkjdfksdljfkasljdfklajf")
            tokenRepository.save(data)
            mapOf("msg" to "Success", "token" to data.token)
        } else {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "failed");
        }
    }
    @PostMapping("/api/logout")
    fun logout(@RequestParam("token") token: String): Map<String, String> {
        val result = tokenRepository.findById(token)
        if (result.isPresent) {
            tokenRepository.delete(result.get())
        }
        return mapOf("msg" to "success")
    }

    @PostMapping("/api/save-blog-add")
    fun saveAdd(@RequestBody articleAdd: ApiArticleAdd): Map<String, String> {
        tokenValidCheck(articleAdd.token)
        val article = Article(content = articleAdd.content,
                publishStatus = 1, images = articleAdd.images)
        var articleString = article.toString()
        if (articleString.length > 220) {
            articleString = articleString.substring(0..220)
        }
        logger.info("article {}", articleString)
        this.articleRepository.save(article)
        return mapOf("msg" to "success")
    }
    @PostMapping("/api/file-upload")
    fun fileUpload(@RequestParam("token") token : String, @RequestParam("uploadfile") file: MultipartFile): Map<String, String> {
        tokenValidCheck(token)
        val url = storageService.upload(file)
        return mapOf("url" to url)
    }

    fun tokenValidCheck(token : String) {
        logger.info("try to get token: {}", token)
        val result = tokenRepository.findById(token)
        logger.info("Got token: {}", result.get())
        if (!result.isPresent) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "token not valid")
        }
    }
}