package Junit_test.chap07.card_valid.repository;

import Junit_test.chap07.card_valid.AutoDebitInfo;

public class StubAutoDebitInfoRepository implements AutoDebitInfoRepository{
    @Override
    public void save(AutoDebitInfo info) {

    }

    @Override
    public AutoDebitInfo findOne(String userId) {
        return null;
    }
}
