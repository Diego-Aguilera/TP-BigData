// Imports the Google Cloud client library

import com.opencsv.CSVReader;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.io.StringReader;

public class TwitterMapper extends Mapper<Object, Text, Text, FloatWritable> {

    @Override
    public void map(Object key, Text value, Context context){
        try {
            String query = "";
            float sent1;
            String linea = value.toString();

            CSVReader reader = new CSVReader(new StringReader(linea));

            String [] tokens = reader.readNext();

            query = tokens[0];

            if (query.contentEquals("query")) {
                return;
            } else {
                sent1 = Float.parseFloat(tokens[6]);
                context.write(new Text(query), new FloatWritable(sent1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
