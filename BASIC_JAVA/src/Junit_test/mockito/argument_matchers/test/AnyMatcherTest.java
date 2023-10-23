package Junit_test.mockito.argument_matchers.test;

import Junit_test.mockito.mock.GameLevel;
import Junit_test.mockito.mock.GameNumGen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.List;

public class AnyMatcherTest {

    @Test
    @DisplayName("ArgumentMatchers.any()를 사용하면 인자로 어떠한 값이 넘어와도 willReturn()값이 반환된다.")
    void anyMatchTest() {
        GameNumGen genMock = Mockito.mock(GameNumGen.class);
        BDDMockito.given(genMock.generate(ArgumentMatchers.any())).willReturn("456");
//        BDDMockito.given(genMock.generate(Mockito.any())).willReturn("456");
//        BDDMockito.given(genMock.generate(BDDMockito.any())).willReturn("456");
        String num = genMock.generate(GameLevel.EASY);
        Assertions.assertEquals("456", num);

        String num2 = genMock.generate(GameLevel.NORMAL);
        Assertions.assertEquals("456", num2);
    }


    /**
     * anyInt()나 any() 등의 메서드는 내부적으로 인자의 일치 여부를 판단하기 위해
     * ArgumentMatcher를 등록한다. Mockito는 한 인자라도 ArgumentMatcher를 사용해서 설정한 경우
     * 모든 인자를 ArgumentMatcher를 이용하도록 하고 있다.
     */
    @Test
    void stub_two_arg() {
        List<String> mockList = Mockito.mock(List.class);
        BDDMockito.given(mockList.set(Mockito.anyInt(), "123")).willReturn("456");

        String old = mockList.set(5, "123");
        Assertions.assertEquals("456", old);
    }
    @Test
    void mixAnyAndEq() {
        List<String> mockList = Mockito.mock(List.class);
        BDDMockito.given(mockList.set(Mockito.anyInt(), Mockito.eq("123"))).willReturn("456");

        String old = mockList.set(5, "123");
        Assertions.assertEquals("456", old);
    }
}
