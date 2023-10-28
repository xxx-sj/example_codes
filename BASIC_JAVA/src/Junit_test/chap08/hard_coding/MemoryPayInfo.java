package Junit_test.chap08.hard_coding;

import java.util.HashMap;
import java.util.Map;

public class MemoryPayInfo implements PayInfoDao {

    Map<String, PayInfo>  map = new HashMap<>();
    @Override
    public void insert(PayInfo payInfo) {
        map.put(payInfo.getId(), payInfo);
    }
}
