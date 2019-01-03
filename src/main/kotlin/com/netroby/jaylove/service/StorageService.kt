package com.netroby.jaylove.service

import org.springframework.web.multipart.MultipartFile

interface StorageService {
    fun upload(upFile: MultipartFile) : String
}