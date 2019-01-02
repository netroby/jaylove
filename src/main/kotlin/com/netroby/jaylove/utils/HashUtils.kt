package com.netroby.jaylove.utils

import java.security.MessageDigest

object HashUtils {
    fun sha256(v : String) : String {
        val bytes = v.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }
    fun sha256WithPrefix(v : String, p : String) : String{
        return sha256(v + p)
    }
}
