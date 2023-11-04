package Junit_test.mockito.annotation.test;

import Junit_test.mockito.GameNumGen;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class JUnit5ExtensionTest {

    @Mock
    private GameNumGen genMock;
}
