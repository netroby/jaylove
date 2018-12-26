package com.netroby.jaylove.vo

import javax.persistence.*

@Entity
@Table(name="article")
data class ArticleAdd  (
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var content: String = "",
    var publishStatus: Long = 1,
    var images: String = ""
)