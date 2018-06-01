import com.google.gson.Gson;
import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class ItemsServiceImpl implements ItemsService {

    private IndexRequest indexRequest;
    final RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(
                    new HttpHost("localhost", 9200, "http"),
                    new HttpHost("localhost", 9300, "http")));


    public ItemsServiceImpl() {
    }

        @Override
    public void addItem(Items item) throws Exception{
        String gson = new Gson().toJson(item, Items.class);
        indexRequest = new IndexRequest(
                "items",
                "application/json",
                item.getId());
        try {
            indexRequest.source(gson, XContentType.JSON);
            client.index(indexRequest);
        } catch(ElasticsearchException e) {
            throw new Exception("Posible conflicto con la base de datos: " + e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("Input/Output exception: " + e);
        } catch (NullPointerException e) {
            throw new Exception("Datos nulos: " + e);
        }
    }

    @Override
    public Collection<Items> getItem() {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder()
                .query(QueryBuilders.matchAllQuery());
        SearchRequest searchRequest = new SearchRequest("items")
                .types("application/json")
                .source(searchSourceBuilder);
        SearchResponse searchResponse;
        Collection<Items> collection = new ArrayList<>();
        try {
            searchResponse = client.search(searchRequest);
            SearchHit[] searchHits = searchResponse.getHits().getHits();
                for (SearchHit hit : searchHits) {
                    Items item = new Gson().fromJson(hit.getSourceAsString(), Items.class);
                    collection.add(item);
            }
        } catch(ElasticsearchException e) {
            System.err.println("Elasticsearch exception: " + e);
            return null;
        } catch (IOException e) {
            System.err.println("Input/Output exception: " + e);
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.err.println("Error en la base de datos: " + e);
            return null;
        }
        return collection;
    }

    @Override
    public Items getItem(String id) {
        GetRequest getRequest = new GetRequest(
                "items",
                "application/json",
                id);
        GetResponse getResponse = null;
        try {
            getResponse = client.get(getRequest);
        } catch(ElasticsearchException e) {
            System.err.println("Elasticsearch exception: " + e);
            return null;
        } catch (IOException e) {
            System.err.println("Input/Output exception: " + e);
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.err.println("Error en la base de datos: " + e);
            return null;
        }
        return new Gson().fromJson(getResponse.getSourceAsString(), Items.class);
    }

    @Override
    public UpdateResponse editItem(String id, String jsonBody) throws ItemsException {
        try {
            if (id == null)
                throw new ItemsException("El ID no puede estar en blanco");
            UpdateRequest request = new UpdateRequest(
                    "items",
                    "application/json",
                    id);
            System.out.println("1");
            request.doc(jsonBody, XContentType.JSON);
            System.out.println("2");
            return client.update(request);
        } catch (Exception e) {
            throw new ItemsException(e.getMessage());
        }
    }

    @Override
    public DeleteResponse deleteItem(String id) {
        DeleteRequest request = new DeleteRequest(
                "items",
                "application/json",
                id);
        DeleteResponse deleteResponse = null;
        try {
            deleteResponse = client.delete(request);
        } catch (ElasticsearchException e) {
            System.err.println("Elasticsearch exception: " + e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deleteResponse;
    }
}