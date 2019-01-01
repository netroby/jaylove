package com.netroby.jaylove.repository


import com.netroby.jaylove.vo.AccessToken
import org.springframework.data.repository.CrudRepository

interface AccessTokenRepository : CrudRepository<AccessToken, String> {
}