package com.myb.dist.controller

import com.myb.dist.DistTestConfig
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
internal class PackageReaderTest {

    @Autowired
    lateinit var config: DistTestConfig

    @Autowired
    lateinit var packageReader: PackageReader

    @Test
    fun testReadApkInfo() {
        val apk = File(config.apk)
        assert(apk.exists())
        val packageInfo = packageReader.readApkInfo(apk)
        // PackageInfo(name=柜面系统, versionName=1.8.4, versionCode=160, icon=res/mipmap-mdpi-v4/ic_launcher.png, pkgId=com.myb.shop, size=24096988, md5=876c150868d95293f9e3184fdc2dde7d)
        println(packageInfo)
        assert(packageInfo.name == "柜面系统")
        assert(packageInfo.pkgId == "com.myb.shop")
        assert(packageInfo.versionName.isNotEmpty())
        assert(packageInfo.valid())
    }
}