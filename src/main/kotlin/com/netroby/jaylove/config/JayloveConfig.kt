package com.netroby.jaylove.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@Configuration
@ConfigurationProperties(prefix = "jaylove")
@Component
class JayloveConfig {
    var site: Map<String, String> = HashMap()
}