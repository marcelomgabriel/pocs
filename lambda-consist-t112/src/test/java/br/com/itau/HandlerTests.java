package br.com.itau;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.context.TestContext;

class HandlerTests {

    private S3Event s3Event;
    private Context context;

    @BeforeEach
    void init (){
        s3Event = Mockito.mock(S3Event.class);
        context = Mockito.mock(Context.class);
    }

    @Test
    void contextLoads() {
        new Handler().handleRequest(s3Event, context );
    }

}
