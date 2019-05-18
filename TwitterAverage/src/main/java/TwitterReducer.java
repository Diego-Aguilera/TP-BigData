import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.math.BigDecimal;

public class TwitterReducer extends Reducer<Text, FloatWritable, Text, FloatWritable> {
    public void reduce(Text key, Iterable<FloatWritable> values, Context context) {
        try {

            float sent, sum = 0, prom = 0;
            int i = 0;

            for (FloatWritable t : values)
            {
                sent = t.get();
                sum = sum + sent;
                i++;
            }

            prom = sum/i;

            context.write(new Text(key), new FloatWritable(BigDecimal.valueOf(prom).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue()));

        }  catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}