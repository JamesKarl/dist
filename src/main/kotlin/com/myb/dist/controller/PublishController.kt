package com.myb.dist.controller

import com.myb.dist.DistConfig
import com.myb.dist.db.AppInfo
import com.myb.dist.db.AppInfoRepository
import com.myb.dist.db.AppPublishHistory
import com.myb.dist.db.AppPublishHistoryRepository
import org.apache.tomcat.jni.Directory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.io.File
import java.util.*


@RestController
@RequestMapping("dist")
@CrossOrigin
class PublishController {

    @Autowired
    lateinit var appRepository: AppInfoRepository
    @Autowired
    lateinit var historyRepository: AppPublishHistoryRepository
    //@Autowired
    lateinit var config: DistConfig

    @GetMapping("hello")
    fun hello(): String {
        return "Hello, world"
    }

    @PostMapping("apps")
    fun queryAllApps(): List<AppInfo> {
        return appRepository.findAll().toList()
    }

    @PostMapping("history")
    fun queryPublishHistory(@RequestParam("appId") appId: Int,
                            @RequestParam("page") page: Int,
                            @RequestParam("pageSize") pageSize: Int
    ): Page<AppPublishHistory> {
        return historyRepository.findAll(PageRequest.of(page, pageSize))
    }


    @PostMapping("detail")
    fun queryDetail(@Param("appId") appId: Int): Optional<AppInfo> {
        return appRepository.findById(appId)
    }

    @PostMapping("uploadPackage")
    fun uploadPackage(@RequestParam("file") file: MultipartFile, redirectAttributes: RedirectAttributes) {
        val root = getRootDir()
    }

    private fun getRootDir(): File {
        val root = File(config.root.toString())
        if (root.isDirectory) {
            return root
        }

        if (root.isFile) {
            root.delete()
        }

        root.mkdir()
        return root
    }
}