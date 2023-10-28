package Junit_test.chap08.hard_coding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryPayInfo implements PayInfoDao {

    Map<String, PayInfo>  map = new HashMap<>();
    @Override
    public void insert(PayInfo payInfo) {
        map.put(payInfo.getId(), payInfo);
    }

    @Override
    public List<PayInfo> findAll() {
        return new ArrayList<PayInfo>(map.values());
    }
}
