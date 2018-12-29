package com.netroby.jaylove.vo

import java.time.Instant
import javax.persistence.*

@Entity
@Table(name="article")
data class Article  (
        @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    var aid: Long = 0,
        var content: String = "",
        var publishStatus: Int = 0,
        var publishTime: Long =     Instant.now().epochSecond,
        var images: String = ""
)