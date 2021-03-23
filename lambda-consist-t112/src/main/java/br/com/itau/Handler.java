package br.com.itau;

import br.com.itau.services.ServiceImpl;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;

public class Handler implements RequestHandler<S3Event, String> {

    ServiceImpl service = new ServiceImpl();

    String header;

    @Override
    public String handleRequest(S3Event event, Context context) {

        String message = "1644                697";
        if (service.isHeader(message)) {
            header = message;
        }

        message = "1644                695";
        if (service.isTrailler(message)) {
            if (service.isHeaderCompatibleTrailler(header, message)) {
                System.out.println("Move obect to bucket-sucess");
            } else {
                System.out.println("Move obect to bucket-insucess");
            }
        }

        return "end";
    }

}