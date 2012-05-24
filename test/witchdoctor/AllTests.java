package witchdoctor;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import witchdoctor.detect.diff.code.CodeChunkTest;
import witchdoctor.detect.diff.du.DuDifferencerTest;
import witchdoctor.detect.diff.du.DuUtilsTest;

@RunWith(Suite.class)
@SuiteClasses({ CodeChunkTest.class, DuDifferencerTest.class, DuUtilsTest.class })
public class AllTests {

}
