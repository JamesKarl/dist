package com.myb.dist.db

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AppInfoRepository : CrudRepository<AppInfo, Int> {
    fun findAll(pageable: Pageable): Page<AppInfo>
    fun findAllByAppId(appId: String): List<AppInfo>
    fun findAllByAppIdContains(appId: String): List<AppInfo>
    fun deleteAllByAppId(appId: String)
}

@Repository
interface AppPublishHistoryRepository : CrudRepository<AppPublishHistory, Int> {
    fun findAll(pageable: Pageable): Page<AppPublishHistory>
}