package Junit_test.mockito.mock.test;

import Junit_test.mockito.mock.GameLevel;
import Junit_test.mockito.mock.GameNumGen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.List;

public class GameGenMockTest {
    @Test
    void mockTest() {
        GameNumGen genMock = Mockito.mock(GameNumGen.class);
        BDDMockito.given(genMock.generate(GameLevel.EASY)).willReturn("123");

        String num = genMock.generate(GameLevel.EASY);
        Assertions.assertEquals("123", num);
    }

    @Test
    void mockThrowTest() {
        GameNumGen genMock = Mockito.mock(GameNumGen.class);
        BDDMockito.given(genMock.generate(null)).willThrow(IllegalArgumentException.class);
//        BDDMockito.given(genMock.generate(null)).willThrow(new IllegalArgumentException());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            genMock.generate(null);
        });

    }

    /**
     * given 모의객체 메서드 실행이 아닌 모의 객체를 넘기는 것에 유의하자.
     * 위에서 보면 given에 모의객체 메서드 실행을 넘긴다.
     * 실제로 clear()를 호출하지 않는다. 단지 익셉셩르 발생시키는 모의 객체를 설정한 것 뿐
     */
    @Test
    @DisplayName("리턴 타입이 void인 메서드에 대해 익셉션을 발생시키기 위해서는 willThrow()를 먼저 호출한다.")
    void voidMethodWillThrowTest() {
        List<String> mockList = Mockito.mock(List.class);
        BDDMockito.willThrow(UnsupportedOperationException.class)
                .given(mockList)
                .clear();

        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            mockList.clear();
        });
    }

    /**
     * 여기서 기본값은 int 는 0을, boolean 은 false를 리턴하고, 참조 타입은 null을 리턴한다.
     */
    @Test
    @DisplayName("스텁을 설정할 때 전달한 인자와 테스트 시 전달하는 인자가 다르면 기본값을 리턴한다.")
    void arg_not_matched() {
        GameNumGen genMock = Mockito.mock(GameNumGen.class);
        BDDMockito.given(genMock.generate(GameLevel.EASY)).willReturn("123");

        String result = genMock.generate(GameLevel.NORMAL);
        Assertions.assertNotEquals("123", result);
        Assertions.assertNull(result, "스텁과 인자가 다르면 기본 값을 리턴한다.");
    }
}
