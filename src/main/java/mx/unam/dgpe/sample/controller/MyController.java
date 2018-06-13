package mx.unam.dgpe.sample.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;

public class MyController extends AbstractVerticle {
    private static final Logger logger = Logger.getLogger(MyController.class);
    private static String pba="";
    public void start(Future<Void> fut) {
	 
         
        logger.info("Inicializando Vertical");
        Router router = Router.router(vertx);
        //router.route("/*").handler(StaticHandler.create("assets")); // para invocar asi: http://localhost:8080/index.html
        // el directorio "upload-folder" será creado en la misma ubicación que el jar fue ejecutado
        router.route().handler(BodyHandler.create().setUploadsDirectory("upload-folder"));
        router.get("/api/primero").handler(this::primero);
        router.post("/api/segundo").handler(this::segundo);
	router.get("/api/tercero").handler(this::tercero);
	router.get("/api/calcu").handler(this::calcu);

	router.get("/api/suma").handler(this::suma);
	router.get("/api/resta").handler(this::resta);
	router.get("/api/multiplica").handler(this::multiplica);
	router.get("/api/divide").handler(this::divide);
        
        // Create the HTTP server and pass the "accept" method to the request handler.
        vertx.createHttpServer().requestHandler(router::accept).listen(
                config().getInteger("http.port", 8080), result -> {
                    if (result.succeeded()) {
                        fut.complete();
                    } else {
                        fut.fail(result.cause());
                    }
                });        
        pba=System.getenv("PBA");
        logger.info("Vertical iniciada !!!"+ pba);
    }  
    private void primero(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        HttpServerRequest request = routingContext.request();
        String mode = request.getParam("mode");
        String jsonResponse = procesa(mode);
        response.setStatusCode(200).
        putHeader("content-type", "application/json; charset=utf-8").
        end(jsonResponse);
    }
    
    private void segundo(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        String decoded = routingContext.getBodyAsString();
        String jsonResponse = procesa(decoded);
        response.setStatusCode(200).
        putHeader("content-type", "application/json; charset=utf-8").
        end(jsonResponse);
    }

 private void tercero(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        HttpServerRequest request = routingContext.request();
        String mode = request.getParam("mode");
        String resp = cadena(mode);
        response.setStatusCode(200).
        putHeader("content-type", "application/json; charset=utf-8").
        end(resp);
    }

private void calcu(RoutingContext routingContext) {
	HttpServerResponse response = routingContext.response();
        HttpServerRequest request = routingContext.request();
        String tipo = request.getParam("tipo");
	int num1 = Integer.parseInt(request.getParam("num1"));
	int num2 = Integer.parseInt(request.getParam("num2"));
	
        String resp = calcula(tipo,num1,num2);
        response.setStatusCode(200).
        putHeader("content-type", "application/json; charset=utf-8").
        end(resp);
}

private void suma(RoutingContext routingContext) {
	HttpServerResponse response = routingContext.response();
        HttpServerRequest request = routingContext.request();
	int num1 = Integer.parseInt(request.getParam("num1"));
	int num2 = Integer.parseInt(request.getParam("num2"));
	
        String resp = sumar(num1,num2);
        response.setStatusCode(200).
        putHeader("content-type", "application/json; charset=utf-8").
        end(resp);
}

private void resta(RoutingContext routingContext) {
	HttpServerResponse response = routingContext.response();
        HttpServerRequest request = routingContext.request();
	int num1 = Integer.parseInt(request.getParam("num1"));
	int num2 = Integer.parseInt(request.getParam("num2"));
	
        String resp = restar(num1,num2);
        response.setStatusCode(200).
        putHeader("content-type", "application/json; charset=utf-8").
        end(resp);
}

private void multiplica(RoutingContext routingContext) {
	HttpServerResponse response = routingContext.response();
        HttpServerRequest request = routingContext.request();
	int num1 = Integer.parseInt(request.getParam("num1"));
	int num2 = Integer.parseInt(request.getParam("num2"));
	
        String resp = multiplicar(num1,num2);
        response.setStatusCode(200).
        putHeader("content-type", "application/json; charset=utf-8").
        end(resp);
}

private void divide(RoutingContext routingContext) {
	HttpServerResponse response = routingContext.response();
        HttpServerRequest request = routingContext.request();
	int num1 = Integer.parseInt(request.getParam("num1"));
	int num2 = Integer.parseInt(request.getParam("num2"));
	
        String resp = dividir(num1,num2);
        response.setStatusCode(200).
        putHeader("content-type", "application/json; charset=utf-8").
        end(resp);
}


    private String procesa(String decoded) {
        Map<String, String> autos = new HashMap<>();
        autos.put("primero", "Ferrari");
        autos.put("segundo", "Lamborgini");
        autos.put("tercero", "Bugatti");
        
        Map<Object, Object> info = new HashMap<>();
        info.put("decoded", decoded);
        info.put("nombre", "gustavo");
        info.put("edad", "21");
        info.put("autos", autos);
        info.put("variable",pba);
        return Json.encodePrettily(info);
    }


   private String cadena(String fuente){
    	 String tmp =fuente +fuente;		
	boolean info =tmp.substring(1,tmp.length()-1).contains(fuente);
	return Json.encodePrettily(info);
	}


     private String calcula(String tipo, int num1, int num2){
        int resultado = 0;
        String operacion = tipo;
        switch(operacion){
            case "suma":
                resultado = num1 + num2;
                break;
            case "resta":
                resultado = num1 - num2;
                break;
            case "multiplica":
                resultado = num1 * num2;
                break;
            case "divide":
                resultado = num1 / num2;
                break;
            case "modulo":
                resultado = num1 % num2;
                break;
        }
        
	 Map<Object, Object> info = new HashMap<>();
         info.put("nombre", "carolina");
         info.put("resultado", resultado);
         info.put("variable",pba);
         return Json.encodePrettily(info);
     
     }	


     private String sumar(int num1, int num2){
        int resultado = 0;
        resultado = num1 + num2;
        
	 Map<Object, Object> info = new HashMap<>();
         info.put("nombre", "carolina");
         info.put("resultado", resultado);
         info.put("variable",pba);
         return Json.encodePrettily(info);
     
     }	

     private String restar(int num1, int num2){
        int resultado = 0;
        resultado = num1 - num2;
        
	 Map<Object, Object> info = new HashMap<>();
         info.put("nombre", "carolina");
         info.put("resultado", resultado);
         info.put("variable",pba);
         return Json.encodePrettily(info);
     
     }	

    private String multiplicar(int num1, int num2){
        int resultado = 0;
        resultado = num1 * num2;
        
	 Map<Object, Object> info = new HashMap<>();
         info.put("nombre", "carolina");
         info.put("resultado", resultado);
         info.put("variable",pba);
         return Json.encodePrettily(info);
     
     }	

    private String dividir(int num1, int num2){
        int resultado = 0;
        resultado = num1 / num2;
        
	 Map<Object, Object> info = new HashMap<>();
         info.put("nombre", "carolina");
         info.put("resultado", resultado);
         info.put("variable",pba);
         return Json.encodePrettily(info);
     
     }	

}
