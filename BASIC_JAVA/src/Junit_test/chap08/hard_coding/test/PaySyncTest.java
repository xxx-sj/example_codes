package Junit_test.chap08.hard_coding.test;

import Junit_test.chap08.hard_coding.MemoryPayInfo;
import Junit_test.chap08.hard_coding.PayInfoDao;
import Junit_test.chap08.hard_coding.PaySync;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PaySyncTest {
    PaySync paySync;

    @BeforeEach
    void setup() {
        PayInfoDao payInfoDao = new MemoryPayInfo();
        paySync = new PaySync(payInfoDao);
    }

    @Test
    void someTest() throws IOException {
        paySync.setFilePath("src/test/resources/c0111.csv");

        paySync.sync();


    }
}
