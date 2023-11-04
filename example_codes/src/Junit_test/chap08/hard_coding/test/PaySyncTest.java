package Junit_test.chap08.hard_coding.test;

import Junit_test.chap08.hard_coding.MemoryPayInfo;
import Junit_test.chap08.hard_coding.PayInfo;
import Junit_test.chap08.hard_coding.PayInfoDao;
import Junit_test.chap08.hard_coding.PaySync;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class PaySyncTest {
    PaySync paySync;
    PayInfoDao payInfoDao;

    @BeforeEach
    void setup() {
        payInfoDao = new MemoryPayInfo();
        paySync = new PaySync(payInfoDao);
    }

    @Test
    void all_data_saved() throws IOException {
        paySync.setFilePath("src/test/resources/c0111.csv");
        paySync.sync();

        List<PayInfo> savedInfos = payInfoDao.findAll();
    }
}
