package functions;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//Handle Lambda: functions.Lambda1::handleRequest
public class Lambda1 implements RequestHandler<S3Event, String> {

    private AmazonS3 s3 = AmazonS3ClientBuilder.standard().build();

    private static String bkt = "indaia";
    private static String key = "largefile.txt";
    //key = URLDecoder.decode(key, "UTF-8");

    @Override
    public String handleRequest(S3Event event, Context context) {
        context.getLogger().log("Received event: " + event);

        try {
            S3Object response = s3.getObject(new GetObjectRequest(bkt, key));
            BufferedReader br = new BufferedReader(new InputStreamReader(response.getObjectContent()));
            String line;
            int qtdLinePar = 0;
            int qtdLineImpar = 0;
            while ((line = br.readLine()) != null) {
//                System.out.println("line:" + line);
                String numStr = line.substring(0,7);
                int numInt = Integer.parseInt(numStr);
                if(numInt % 2 == 0){
                    qtdLinePar++;
                }else{
                    qtdLineImpar++;
                }
            }
            System.out.println("Quantidade de linha par..:"+qtdLinePar);
            System.out.println("Quantidade de linha impar:"+qtdLineImpar);
            return "Processamento efetuado com sucesso!";
        } catch (Exception e) {
            System.err.println("Exception: " + e);
            return "Processamento com erro!";
        }
    }
}
