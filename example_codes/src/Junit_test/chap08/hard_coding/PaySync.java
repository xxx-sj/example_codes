package Junit_test.chap08.hard_coding;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class PaySync {
    private String filePath = "D://data/pay/cp0001.csv";
    //구현 클래스를 인터페이스로 변경하고 setter or 생성자로 받는다.
    private PayInfoDao payInfodao;

    public PaySync(PayInfoDao payInfodao) {
        this.payInfodao = payInfodao;
    }

    //setter 또는 인자를 통해 filepath를 받을 수 있다.

    public void sync() throws IOException {
        Path path = Paths.get(filePath);
        List<PayInfo> payInfos = Files.lines(path)
                .map(line -> {
                    String[] data = line.split(",");
                    PayInfo payInfo = new PayInfo(
                            data[0], data[1], Integer.parseInt(data[2])
                    );
                    return payInfo;
                })
                .collect(Collectors.toList());

        payInfos.forEach(pi -> payInfodao.insert(pi));
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
