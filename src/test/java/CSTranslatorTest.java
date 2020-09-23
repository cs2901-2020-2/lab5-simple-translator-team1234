import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSTranslatorTest{

    @Test(expectedExceptions = LengthSizeException.class)
    public void testCase1() throws LengthSizeException, Exception {
        generic(1);
    }

    @Test(invocationCount = 80, threadPoolSize = 80)
    public void testCase2() throws IOException, Exception {
        Long start = System.currentTimeMillis();
        Long end = System.currentTimeMillis();
        Long elapsedTime = end - start;
        Assert.assertTrue(elapsedTime <= 400);
    }




}