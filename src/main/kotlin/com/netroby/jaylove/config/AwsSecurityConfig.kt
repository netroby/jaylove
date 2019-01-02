package com.netroby.jaylove.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@Configuration
@ConfigurationProperties(prefix = "aws")
@Component
class AwsSecurityConfig {
    var accessKeyId = ""
    var secretAccessKey = ""
    var region = ""
    var bucket = ""
}