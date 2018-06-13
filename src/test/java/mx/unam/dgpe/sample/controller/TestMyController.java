package mx.unam.dgpe.sample.controller;

import org.apache.log4j.Logger;
import org.junit.Test;


import io.vertx.core.AbstractVerticle;
import static mx.unam.dgpe.sample.controller.RestUtil.*;

public class TestMyController extends AbstractVerticle {
    private static final Logger logger = Logger.getLogger(TestMyController.class);
    
    @Test
    public void ok() throws Exception {
	//String result= sendGet("http://192.168.62.128:8080/api/tercero?mode=sedsed");
	String result= sendGet("http://localhost:8080/api/calcu?tipo=suma&num1=15&num2=5");
        logger.info(result);

	result= sendGet("http://localhost:8080/api/calcu?tipo=resta&num1=15&num2=5");
        logger.info(result);
	
	result= sendGet("http://localhost:8080/api/calcu?tipo=multiplica&num1=15&num2=5");
        logger.info(result);

	result= sendGet("http://localhost:8080/api/calcu?tipo=divide&num1=15&num2=5");
        logger.info(result);
	
	
        logger.info(result);
	
	}
}
