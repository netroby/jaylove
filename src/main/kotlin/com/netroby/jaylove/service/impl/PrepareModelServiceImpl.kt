package com.netroby.jaylove.service.impl

import com.netroby.jaylove.config.JayloveConfig
import com.netroby.jaylove.service.AuthAdapterService
import com.netroby.jaylove.service.PrepareModelService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PrepareModelServiceImpl(
        @Autowired private val authAdapterService: AuthAdapterService,
        @Autowired private val jayloveConfig: JayloveConfig
): PrepareModelService {
    override fun getModel(): HashMap<String, Any> {
            val map: java.util.HashMap<String, Any> = java.util.HashMap()
            map["username"] = authAdapterService.getUserName()?:""
            map["isAuthenticated"] = authAdapterService.isAuthenticated()?:false
            map["siteName"] = jayloveConfig.site["name"] ?:""
            map["siteDescription"] = jayloveConfig.site["description"] ?: ""
            return map
    }
}