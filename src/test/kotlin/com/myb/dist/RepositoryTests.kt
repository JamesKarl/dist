package com.myb.dist

import com.myb.dist.db.AppFileRepository
import com.myb.dist.db.AppInfoRepository
import com.myb.dist.db.AppPublishHistoryRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RepositoryTests {

    @Autowired
    lateinit var fileRepository: AppFileRepository
    @Autowired
    lateinit var historyRepository: AppPublishHistoryRepository
    @Autowired
    lateinit var appRepository: AppInfoRepository
    @Autowired
    lateinit var config: DistTestConfig

    @Test
    fun testConfig() {
        assertThat(config.apk).contains(".apk")
        assertThat(config.ipa).contains(".ipa")
    }

}