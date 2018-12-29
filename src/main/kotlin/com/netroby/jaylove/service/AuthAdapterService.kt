package com.netroby.jaylove.service

import org.springframework.security.core.Authentication

interface AuthAdapterService {
    fun  getAuthentication() : Authentication?
    fun isAuthenticated(): Boolean?
    fun getUserName(): String?
}