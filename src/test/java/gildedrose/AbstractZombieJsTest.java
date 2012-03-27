package gildedrose;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.exec.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Map;

import static org.junit.Assert.fail;

public abstract class AbstractZombieJsTest extends AbstractHttpServerTest {
    protected void assertWithZombieJs(String jsPath) {
        Executor executor = new DefaultExecutor();
        executor.setWatchdog(new ExecuteWatchdog(60000));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        executor.setStreamHandler(new PumpStreamHandler(output));

        Map map = ImmutableMap.of("file", new File(".", jsPath), "port", port());
        CommandLine cmdLine = CommandLine.parse("/usr/local/bin/node ${file} ${port}", map);

        Exception failure = null;
        try {
            executor.execute(cmdLine);
        } catch (Exception e) {
            failure = e;
        }

        for (String line : Splitter.on('\n').split(output.toString())) {
            if (line.contains("Error: ")) {
                fail(line);
            }
        }

        if (null != failure) {
            fail(failure.getMessage());
        }
    }
}
