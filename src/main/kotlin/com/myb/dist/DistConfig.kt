package com.myb.dist

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

//@Component
//@ConfigurationProperties(prefix = "dist.distributions")
data class DistConfig(val root: String = "./distributions")

@Component
@ConfigurationProperties(prefix = "dist.test")
class DistTestConfig {
    val apk: String = "/home/jameskarl/myb/code/demo/test/shop-v1.8.4.apk"
    val ipa: String = "/home/jameskarl/myb/code/demo/test/Runner.ipa"
}