package com.netroby.jaylove.vo

import javax.persistence.*

@Entity
@Table(name="article")
data class ArticleEdit  (
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var aid: Long = 0,
    var content: String = "",
    var images: String = "",
    var publishStatus: Long = 1
)