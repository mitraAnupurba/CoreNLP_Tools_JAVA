package com.mycompany.googlecloudexample;

import com.google.cloud.language.v1.AnalyzeEntitySentimentRequest;
import com.google.cloud.language.v1.AnalyzeEntitySentimentResponse;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.EncodingType;
import com.google.cloud.language.v1.Entity;
import com.google.cloud.language.v1.EntityMention;
import com.google.cloud.language.v1.LanguageServiceClient;
import java.io.IOException;

public class EntitySentimentAnalysis {
    public static void main(String[] args) throws IOException {
        String text = "I hate You";
        // Instantiate the Language client com.google.cloud.language.v1.LanguageServiceClient
try (LanguageServiceClient language = LanguageServiceClient.create()) {
  Document doc = Document.newBuilder()
      .setContent(text).setType(Type.PLAIN_TEXT).build();
  AnalyzeEntitySentimentRequest request = AnalyzeEntitySentimentRequest.newBuilder()
      .setDocument(doc)
      .setEncodingType(EncodingType.UTF16).build();
  // detect entity sentiments in the given string
  AnalyzeEntitySentimentResponse response = language.analyzeEntitySentiment(request);
  // Print the response
  for (Entity entity : response.getEntitiesList()) {
    System.out.printf("Entity: %s\n", entity.getName());
    System.out.printf("Salience: %.3f\n", entity.getSalience());
    System.out.printf("Sentiment : %s\n", entity.getSentiment());
    for (EntityMention mention : entity.getMentionsList()) {
      System.out.printf("Begin offset: %d\n", mention.getText().getBeginOffset());
      System.out.printf("Content: %s\n", mention.getText().getContent());
      System.out.printf("Magnitude: %.3f\n", mention.getSentiment().getMagnitude());
      System.out.printf("Sentiment score : %.3f\n", mention.getSentiment().getScore());
      System.out.printf("Type: %s\n\n", mention.getType());
    }
  }
}
    }
    
}
