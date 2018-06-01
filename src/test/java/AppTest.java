import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Headers;
import com.jayway.restassured.specification.*;
import com.jayway.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class AppTest {

    @Test
    public void validar_post_status_code_200() {
        RestAssured.baseURI = "http://localhost:8080";
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.body("{\n" +
                "  \"title\":\"Item de test - No Ofertar\",\n" +
                "  \"category_id\":\"MLA5529\",\n" +
                "  \"price\":10,\n" +
                "  \"currency_id\":\"ARS\",\n" +
                "  \"available_quantity\":1,\n" +
                "  \"buying_mode\":\"buy_it_now\",\n" +
                "  \"listing_type_id\":\"bronze\",\n" +
                "  \"condition\":\"new\",\n" +
                "  \"description\": \"Item:,  Ray-Ban WAYFARER\",\n" +
                "  \"video_id\": \"YOUTUBE_ID_HERE\",\n" +
                "  \"warranty\": \"12 months by Ray Ban\",\n" +
                "  \"pictures\":[\n" +
                "    {\"source\":\"http://upload.wikimedia.org/wikipedia/commons/f/fd/Ray_Ban_Original_Wayfarer.jpg\"},\n" +
                "    {\"source\":\"http://en.wikipedia.org/wiki/File:Teashades.gif\"}\n" +
                "  ]\n" +
                "}");
        Response response = httpRequest.post("/items/1");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(String.valueOf(statusCode), "200");
    }

    @Test
    public void validar_get_status_code_200() {
        RestAssured.baseURI = "http://localhost:8080";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/items/1");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(String.valueOf(statusCode), "200");
    }

    @Test
    public void validar_get_all_status_code_200() {
        RestAssured.baseURI = "http://localhost:8080";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/items");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(String.valueOf(statusCode), "200");
    }

    @Test
    public void validar_put_status_code_200() {
        RestAssured.baseURI = "http://localhost:8080";
        RequestSpecification httpRequestPost = RestAssured.given();
        httpRequestPost.body("{\n" +
                "  \"title\":\"Item de test - NOOOOOOO Ofertar\",\n" +
                "  \"category_id\":\"MLA5529\",\n" +
                "  \"price\":10,\n" +
                "  \"currency_id\":\"ARS\",\n" +
                "  \"available_quantity\":1,\n" +
                "  \"buying_mode\":\"buy_it_now\",\n" +
                "  \"listing_type_id\":\"bronze\",\n" +
                "  \"condition\":\"new\",\n" +
                "  \"description\": \"Item:,  Ray-Ban WAYFARER\",\n" +
                "  \"video_id\": \"YOUTUBE_ID_HERE\",\n" +
                "  \"warranty\": \"12 months by Ray Ban\",\n" +
                "  \"pictures\":[\n" +
                "    {\"source\":\"http://upload.wikimedia.org/wikipedia/commons/f/fd/Ray_Ban_Original_Wayfarer.jpg\"},\n" +
                "    {\"source\":\"http://en.wikipedia.org/wiki/File:Teashades.gif\"}\n" +
                "  ]\n" +
                "}");
        Response response = httpRequestPost.post("/items/1");
        RequestSpecification httpRequestPut = RestAssured.given();
        httpRequestPut.body("{\"title\":\"Item de test - SIIIIIII Ofertar\"}");
        Response responsePut = httpRequestPut.put("/items/1");
        int statusCode = responsePut.getStatusCode();
        Assert.assertEquals(String.valueOf(statusCode), "200");
    }

    @Test
    public void validar_delete_status_code_200() {
        RestAssured.baseURI = "http://localhost:8080";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.delete("/items/1");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(String.valueOf(statusCode), "200");
    }

    @Test
    public void validar_get_headers() {
        RestAssured.baseURI = "http://localhost:8080";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/items/1");
        Headers allHeaders = response.headers();
        for(Header header : allHeaders) {
            System.out.println("Key: " + header.getName()
                    + " Value:" + header.getValue());
        }
        String contentType = response.header("Content-Type");
        Assert.assertEquals(contentType, "application/json");
    }

    @Test
    public void validar_get_all_headers() {
        RestAssured.baseURI = "http://localhost:8080";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/items");
        Headers allHeaders = response.headers();
        for(Header header : allHeaders) {
            System.out.println("Key: " + header.getName()
                    + " Value:" + header.getValue());
        }
        String contentType = response.header("Content-Type");
        Assert.assertEquals(contentType, "application/json");
    }

    @Test
    public void validar_post_missing_filds() {
        RestAssured.baseURI = "http://localhost:8080";
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.body("{\n" +
                "  \"title\":\"Item de test - No Ofertar\",\n}");
        Response response = httpRequest.post("/items/1");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(String.valueOf(statusCode), "500");
    }
}
