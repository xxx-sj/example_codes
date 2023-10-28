package Junit_test.chap08.hard_coding;

import java.util.List;

public interface PayInfoDao {
    void insert(PayInfo payInfo);

    List<PayInfo> findAll();
}
