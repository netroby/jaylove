package com.netroby.jaylove.vo

import javax.persistence.*

@Entity
@Table(name="access_token")
data class AccessToken  (
        @Id
    var token: String = ""
)