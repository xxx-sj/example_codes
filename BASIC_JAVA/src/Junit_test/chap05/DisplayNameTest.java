package Junit_test.chap05;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("@DisplayName test")
public class DisplayNameTest {
    
    @DisplayName("값 같은지 비교")
    @Test
    void assertEqualsMethod() {
        
    }

    @Disabled
    @DisplayName("@Disabled 테스트")
    @Test
    void disabledMethod() {

    }
}
