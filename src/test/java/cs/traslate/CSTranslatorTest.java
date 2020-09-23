package  cs.traslate;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Test
class CSTranslatorTest{


    @Test
    void testTranslation() throws  Exception{
        List<String> input = readInput(0);
        List<String> output = readOutput(0);
        CSTranslator csTranslator = new CSTranslator();
        for ( int i = 0; i<input.size(); i++) {
            String translatedText = csTranslator.translateText(input.get(i));
            Assert.assertEquals(translatedText, output.get(i));
        }
    }

    @Test(invocationCount = 80, threadPoolSize = 80)
    public void testCase3() throws Exception{
        testTranslation();
    }

    @Test(expectedExceptions = Exception.class)
    public void testCase2Error() throws Exception{
        String line = generateRandomString(600);

        CSTranslator sequencer = new CSTranslator();
        sequencer.translateText(line);
    }

    @Test(timeOut = 2800)
    public void testcase4() throws Exception{
        testTranslation();
    }

    private List<String> readInput(int testNumber){
        List<String> lines = readFile(testNumber, "input");
        return lines;
    }

    private List<String> readOutput(int testNumber){
        List<String> lines = readFile(testNumber, "output");
        return lines;
    }

    public List<String> readFile(int testNumber, String type){
        String fileName = "test_case<testNumber>_<type>";
        fileName = fileName.replace("<testNumber>", Integer.toString(testNumber));
        fileName = fileName.replace("<type>", type);
        InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
        Scanner scan = new Scanner(is);
        List<String> lines = new ArrayList<String>();
        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            lines.add(line);
        }
        return lines;
    }

    private static final String CHAR_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final String DATA_FOR_RANDOM_STRING =  CHAR_UPPER;
    private static final SecureRandom random = new SecureRandom();

    public static String generateRandomString(int length) {
        if (length < 1) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);
            sb.append(rndChar);
        }
        return sb.toString();

    }
}