package com.myb.dist

import com.myb.dist.db.AppFileRepository
import com.myb.dist.db.AppInfoRepository
import com.myb.dist.db.AppPublishHistoryRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest

@DataJpaTest
class RepositoryTests @Autowired constructor(
        val fileRepository: AppFileRepository,
        val historyRepository: AppPublishHistoryRepository,
        val appRepository: AppInfoRepository,
        val config: DistTestConfig
) {

    @Test
    fun testConfig() {
        assertThat(config.apk).contains(".apk")
        assertThat(config.ipa).contains(".ipa")
    }

}