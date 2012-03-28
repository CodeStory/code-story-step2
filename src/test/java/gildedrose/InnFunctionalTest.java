package gildedrose;

import org.junit.*;
import test.utils.*;

import static test.utils.ZombieJsAssertions.*;

public class InnFunctionalTest {
	@ClassRule
	public static HttpServerRule httpServer = new HttpServerRule();

	@Test
	public void canTestInn() {
		assertThat(httpServer).canRun("testInn.js");
	}
}
