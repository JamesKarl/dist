package com.myb.dist

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

//@Component
//@ConfigurationProperties(prefix = "dist.distributions")
data class DistConfig(val root: String = "./distributions")

@ConfigurationProperties(prefix = "dist.test")
class DistTestConfig {
    lateinit var apk: String
    lateinit var ipa: String
}