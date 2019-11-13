package com.myb.dist.controller

import com.myb.dist.db.AppInfo
import com.myb.dist.db.AppInfoRepository
import com.myb.dist.db.AppPublishHistoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("dist")
class PublishController {

    @Autowired
    lateinit var appRepository: AppInfoRepository
    @Autowired
    lateinit var historyRepository: AppPublishHistoryRepository

    @GetMapping("hello")
    fun hello(): String {
        return "Hello, world"
    }

    @PostMapping("apps")
    fun queryAllApps(): List<AppInfo> {
        return appRepository.findAll().toList()
    }
}