package com.myb.dist

import com.myb.dist.controller.PackageInfo
import com.myb.dist.controller.toAppInfo
import com.myb.dist.db.AppFile
import com.myb.dist.db.AppFileRepository
import com.myb.dist.db.AppInfo
import com.myb.dist.db.AppInfoRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DistApplicationTests {

    @Autowired
    lateinit var appRepository: AppInfoRepository

    @Autowired
    lateinit var appFileRepository: AppFileRepository

    @Test
    fun contextLoads() {
        var apps = appRepository.findAllByAppIdContains("test")
        if (apps.isNotEmpty()) {
            appRepository.deleteAll(apps)
            apps = appRepository.findAllByAppIdContains("test")
            assert(apps.isEmpty())
        }

        val appInfo = AppInfo(appId = "com.test.app", name = "Test", type = "android")
        appRepository.save(appInfo)
        apps = appRepository.findAllByAppIdContains("test")
        assert(apps.size == 1)
    }

    @Test
    fun testPublish() {
        val packageInfo = PackageInfo("Test", "1.0.0", 1, "icon url", "com.test.app", 20943920, "md5md5md5")
        val appInfo = packageInfo.toAppInfo()


    }


    @Test
    fun testAddAppFile() {
        val appFile = AppFile(name = "test.apk", md5 = "md5xxxxxx", size = 93049034L, path = "/fdipk/fdk")
        val tmp = appFileRepository.save(appFile)
        assert(tmp.id != null)
    }
}
