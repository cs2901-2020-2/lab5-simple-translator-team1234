import com.google.cloud.translate.v3.LocationName;
import com.google.cloud.translate.v3.TranslateTextRequest;
import com.google.cloud.translate.v3.TranslateTextResponse;
import com.google.cloud.translate.v3.Translation;
import com.google.cloud.translate.v3.TranslationServiceClient;
import java.io.IOException;

    public class TranslateText {

        public static void translateText() throws Exception {
            // TODO(developer): Replace these variables before running the sample.
            String projectId = "test-245921";
            // Supported Languages: https://cloud.google.com/translate/docs/languages
            String targetLanguage = "en";
            String text = "Hola";
            translateText(projectId, targetLanguage, text);

            


        }

        public static void translateText(String projectId, String targetLanguage, String text)
                throws Exception {

            if(text.length() > 500){
                throw new LenghtSizeException("Too many characters");
            }        

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

                for (Translation translation : response.getTranslationsList()) {
                    System.out.printf("Translated text: %s\n", translation.getTranslatedText());
                }
            }
        }
    }
