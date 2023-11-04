package Junit_test.mockito.then_should.test;

import Junit_test.mockito.Game;
import Junit_test.mockito.GameLevel;
import Junit_test.mockito.GameNumGen;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

public class GameTest {

    @Test
    void init() {
        //given
        GameNumGen genMock = Mockito.mock(GameNumGen.class);
        Game game = new Game(genMock);
        //when
        game.init(GameLevel.EASY);
        //then
        BDDMockito.then(genMock).should().generate(GameLevel.EASY);
    }

    @Test
    void should_generate_any() {
        GameNumGen genMock = Mockito.mock(GameNumGen.class);
        Game game = new Game(genMock);
        game.init(GameLevel.NORMAL);

        BDDMockito.then(genMock).should().generate(Mockito.any());
    }

    @Test
    void should_only_called() {
        GameNumGen genMock = Mockito.mock(GameNumGen.class);
        Game game = new Game(genMock);
        game.init(GameLevel.NORMAL);

        BDDMockito.then(genMock).should(Mockito.only()).generate(Mockito.any());
    }
}
