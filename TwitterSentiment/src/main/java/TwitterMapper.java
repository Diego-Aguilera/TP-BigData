// Imports the Google Cloud client library

import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import com.opencsv.CSVReader;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.StringReader;
import java.util.List;


public class TwitterMapper extends Mapper<Object, Text, Text, Text> {

    @Override
    public void map(Object key, Text value, Context context){
        // Instantiates a client
        try (LanguageServiceClient language = LanguageServiceClient.create()) {

            String tweet = "", query = "";
            String linea = value.toString();

            CSVReader reader = new CSVReader(new StringReader(linea));

            String [] tokens = reader.readNext();

            query = tokens[0];

            tweet = tokens[1];

            Document doc = Document.newBuilder()
                    .setContent(tweet)
                    .setType(Type.PLAIN_TEXT)
                    .setLanguage("en")
                    .build();

            // Detects the sentiment of the text
            Sentiment sentiment = language.analyzeSentiment(doc).getDocumentSentiment();

            System.out.printf("Text: %s%n", tweet);
            System.out.printf("Sentiment: %s, %s%n", sentiment.getScore(), sentiment.getMagnitude());

            context.write(new Text(linea), new Text(sentiment.getScore()+","+sentiment.getMagnitude()));
        }

        catch (java.io.IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
