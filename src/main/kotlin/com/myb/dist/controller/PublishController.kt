package com.myb.dist.controller

import com.myb.dist.DistConfig
import com.myb.dist.db.*
import com.myb.dist.utils.DATE_PATTERN
import com.myb.dist.utils.toString
import javafx.scene.input.DataFormat
import org.apache.tomcat.jni.Directory
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.logging.LogLevel
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.Logger
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping("dist")
@CrossOrigin
class PublishController {

    private val logger = LoggerFactory.getLogger(PublishController::class.simpleName)

    @Autowired
    lateinit var appRepository: AppInfoRepository
    @Autowired
    lateinit var historyRepository: AppPublishHistoryRepository

    @Autowired
    lateinit var appFileRepository: AppFileRepository

    @Autowired
    lateinit var packageManager: PackageManager

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
    fun uploadPackage(@RequestParam("file") file: MultipartFile, redirectAttributes: RedirectAttributes): Int? {
        val appInfo = packageManager.save(file) ?: return null
        val destFile = appInfo.getFile(packageManager.getRootDir()) ?: return null
        val info = PackageReader.readApkInfo(destFile)
        appRepository.save(info.toAppInfo()).apply {
            historyRepository.save(info.toAppPublishHistory(id!!))
        }
        return appInfo.id
    }

    @GetMapping("downloadPackage")
    fun downloadPackage(@RequestParam("fileId") fileId: Int, response: HttpServletResponse) {
        val dest = appFileRepository.findById(fileId)
        if (!dest.isPresent) {
            return
        }
        val appInfo = dest.get()
        val appFile = appInfo.getFile(packageManager.getRootDir()) ?: return
        with(response) {
            setHeader("content-type", "application/octet-stream")
            contentType = "application/octet-stream"
            setHeader("Content-Disposition", "attachment;filename=${appInfo.name}")
            outputStream.buffered().write(appFile.inputStream().buffered().readBytes())
        }
    }
}