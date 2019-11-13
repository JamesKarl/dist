package com.myb.dist.db

import org.springframework.data.repository.CrudRepository

interface AppInfoRepository : CrudRepository<AppInfo, Int> {
}

interface AppPublishHistoryRepository : CrudRepository<AppPublishHistory, Int> {

}