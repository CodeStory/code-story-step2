package gildedrose;

import org.junit.Test;

public class ZombieJsTest extends AbstractZombieJsTest {
    @Test
    public void canTest() {
        assertWithZombieJs("test.js");
    }
}
