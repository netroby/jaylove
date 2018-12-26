package com.netroby.jaylove.repository


import com.netroby.jaylove.vo.Article
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository

interface ArticleRepository : PagingAndSortingRepository<Article, Long> {
    fun  findByContentContaining(pageable: Pageable, keyword: String, keyword2: String): Page<List<Article>>
}