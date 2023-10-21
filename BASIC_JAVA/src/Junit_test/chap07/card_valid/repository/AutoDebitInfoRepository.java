package Junit_test.chap07.card_valid.repository;

import Junit_test.chap07.card_valid.AutoDebitInfo;

public interface AutoDebitInfoRepository {
    void save(AutoDebitInfo info);
    AutoDebitInfo findOne(String userId);
}
