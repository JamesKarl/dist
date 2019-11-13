package com.myb.dist.controller

import com.myb.dist.db.AppInfo
import com.myb.dist.db.AppInfoRepository
import com.myb.dist.db.AppPublishHistory
import com.myb.dist.db.AppPublishHistoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.*


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

    @PostMapping("history")
    fun queryPublishHistory(@RequestParam("appId") appId: Int,
                            @RequestParam("page") page: Int,
                            @RequestParam("pageSize") pageSize: Int
    ): Page<AppPublishHistory> {
        return historyRepository.findAll(PageRequest.of(page, pageSize))
    }
}