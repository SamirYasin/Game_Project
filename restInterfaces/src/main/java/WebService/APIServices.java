package WebService;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class APIServices {

    public APIServices() {
    }

    public static String fetchRandomWordAPI() {
        try {
            Gson gson = new Gson();
            WordObject wordObject;

            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://api.api-ninjas.com/v1/randomword"))
                    .header("X-Api-Key", "wE5jbdjGofOCf24orutH64Mow5sZOeDvdopJHDRm")
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
            wordObject = gson.fromJson(getResponse.body(), WordObject.class);
            return wordObject.getWord();
        } catch (Exception e) {
            return "cant parse or grab definition";
        }
    }

    public static String fetchDefinitionOfWord(String wordInQuestion) throws URISyntaxException, IOException, InterruptedException {

        String apiKey = "7f6e0207-8428-432f-8a0c-f6e9d37ae58f";
        String dynamicUrl = String.format("https://www.dictionaryapi.com/api/v3/references/collegiate/json/%s?key=%s", wordInQuestion, apiKey);

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(dynamicUrl))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        StringBuilder inline = new StringBuilder();
        Scanner scanner = new Scanner(getResponse.body());

        /*
            So Merriam's API has different or additional properties depending on the word that's being called
            which prevents us from having a static POJO class.
            So I decided to write the response JSON data into a string using a scanner so that we can parse the JSON into a POJO on the fly
            using 'parseString' method
         */
        while (scanner.hasNext()) {
            inline.append(scanner.nextLine());
        }

        scanner.close();

        JsonElement jsonTree = JsonParser.parseString(inline.toString());
        JsonArray jsonArray = jsonTree.getAsJsonArray();

        /*
            I'm pretty sure this is top 3 sloppiest ways to implement this lmao,
            but I'm tired and finding a better implementation would take another hour and a half :(
            This query below grabs the first definition of the word in question in the JSON body.
            It seems like Merriam's API puts the most relevant definition first so this should work
        */
        try {
            JsonElement definitionOfWord = jsonArray.get(0)
                    .getAsJsonObject().get("def")
                    .getAsJsonArray().get(0)
                    .getAsJsonObject().get("sseq")
                    .getAsJsonArray().get(0)
                    .getAsJsonArray().get(0)
                    .getAsJsonArray().get(1)
                    .getAsJsonObject().get("dt")
                    .getAsJsonArray().get(0)
                    .getAsJsonArray().get(1);

            inline.delete(0, inline.length());
            inline.append(trimDefinition(definitionOfWord.toString()));
        } catch (Exception e) {
            return "Sorry we couldn't parse this definition";
        }

        return inline.toString();
    }

    private static String trimDefinition(String definition) {
        String afterExtractingBcJargin = extractAllBcJargin(definition);
        String defAfterReplacingWord;

        if (afterExtractingBcJargin.contains("|")) {
            defAfterReplacingWord = placeSpecialWordInDefinition(afterExtractingBcJargin);
            if (defAfterReplacingWord.contains("|")) {
                defAfterReplacingWord = placeSpecialWordInDefinition(defAfterReplacingWord);
            }
            return defAfterReplacingWord;
        }

        return afterExtractingBcJargin.trim();
    }

    private static String placeSpecialWordInDefinition(String definition) {

        String wordWithinBrackets = definition.substring(definition.indexOf('{'), definition.indexOf('}') + 1);
        String wordToBeAdded = extractWordWithingBracket(wordWithinBrackets);

        return definition.replace(wordWithinBrackets, wordToBeAdded);
    }

    private static String extractAllBcJargin(String originalDefinition) {
        String strippedFirstInstanceOfBs = originalDefinition.replace("{bc}", "");

        if (strippedFirstInstanceOfBs.contains("{bc}")) {
            extractAllBcJargin(strippedFirstInstanceOfBs);
        }

        return strippedFirstInstanceOfBs;
    }

    private static String extractWordWithingBracket(String bracketedWord) {
        String[] wordArray = bracketedWord.split("\\|");
        String wordToFocusOn = wordArray[1];
        return wordToFocusOn.replace("}", "");
    }

    private static String extractQuotations(String definition) {
        String strippedFirstInstanceOfQuote = definition.replace("\"", "");

        if (strippedFirstInstanceOfQuote.contains("\"")) {
            extractQuotations(strippedFirstInstanceOfQuote);
        }

        return strippedFirstInstanceOfQuote;
    }
}