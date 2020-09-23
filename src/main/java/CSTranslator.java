import com.google.cloud.translate.v3.LocationName;
import com.google.cloud.translate.v3.TranslateTextRequest;
import com.google.cloud.translate.v3.TranslateTextResponse;
import com.google.cloud.translate.v3.TranslationServiceClient;
import java.io.IOException;

public class CSTranslator {

        public static String translateText(String text) throws IOException {
            // TODO(developer): Replace these variables before running the sample.
            String projectId = "test-245921";
            // Supported Languages: https://cloud.google.com/translate/docs/languages
            String targetLanguage = "en";
            return translateText(projectId, targetLanguage, text);
        }

        public static String translateText(String projectId, String targetLanguage, String text)
                throws IOException {

            try (TranslationServiceClient client = TranslationServiceClient.create()) {
                LocationName parent = LocationName.of(projectId, "global");

                TranslateTextRequest request =
                        TranslateTextRequest.newBuilder()
                                .setParent(parent.toString())
                                .setMimeType("text/plain")
                                .setTargetLanguageCode(targetLanguage)
                                .addContents(text)
                                .build();

                TranslateTextResponse response = client.translateText(request);
                return response.getTranslationsList().get(0).getTranslatedText();
            }
        }
        public static void main (String args[]) throws IOException {
            CSTranslator t1 = new CSTranslator();
            System.out.println(t1.translateText("Hola como estas"));
        }
    }
