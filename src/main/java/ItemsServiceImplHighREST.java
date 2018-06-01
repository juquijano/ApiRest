/*
import com.google.gson.Gson;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ItemsServiceImplHighREST implements ItemsService {

    private HashMap<String, Items> userMap;
    private IndexRequest indexRequest;

    final RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(
                    new HttpHost("localhost", 9200, "http"),
                    new HttpHost("localhost", 9300, "http")));

    public ItemsServiceImplHighREST() {
        userMap = new HashMap<>();
//            System.out.println(request); // Arriba y abajo son dos formas de hacer lo mismo

        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("user", "kimchy");
        jsonMap.put("message", "trying out Elasticsearch");
        //IndexRequest indexRequest = new IndexRequest("posts", "doc", "1").source(jsonMap);

    }

        @Override
    public void addItem(Items item) {

System.out.println("00");
        indexRequest = new IndexRequest(
                "items",
                "doc",
                item.getId());
        String gson = new Gson().toJson(item, Items.class);
System.out.println("0");
System.out.println(gson);
            indexRequest.source(gson, XContentType.JSON);
System.out.println("-1");
            GetRequest getRequest = new GetRequest(
                "items",
                "doc",
                item.getId());
System.out.println("-2");
System.out.println(getRequest);
System.out.println("-21");

            GetResponse getResponse = null;
System.out.println("-22");
            try {
                getRequest.fetchSourceContext(FetchSourceContext.DO_NOT_FETCH_SOURCE);                System.out.println("-23");
                getResponse = client.get(getRequest);
System.out.println("-24");
            } catch (IOException e) {
                e.printStackTrace();
            }
System.out.println("-25");
            String index = getResponse.getIndex();
            String type = getResponse.getType();
            String id = getResponse.getId();
System.out.println("-4");
System.out.println(index);
System.out.println(type);
System.out.println(id);
            if (getResponse.isExists()) {
System.out.println("-5");
                long version = getResponse.getVersion();
                String sourceAsString = getResponse.getSourceAsString();
                Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
                byte[] sourceAsBytes = getResponse.getSourceAsBytes();
            }


        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Items> getItem() {
        return userMap.values();
    }

    @Override
    public Items getItem(String id) {
        return userMap.get(id);
    }

    @Override
    public Items editItem(Items forEdit) throws ItemsException {
        try {
            if (forEdit.getId() == null)
                throw new ItemsException("El ID no puede estar en blanco");

            Items toEdit = userMap.get(forEdit.getId());

            if (toEdit == null)
                throw new ItemsException("Usuario no encontrado");

            if (forEdit.getTitle() != null) {
                toEdit.setTitle(forEdit.getTitle());
            }
            if (forEdit.getCategory_id() != null) {
                toEdit.setCategory_id(forEdit.getCategory_id());
            }
            if (forEdit.getPrice() != 0) {
                toEdit.setPrice(forEdit.getPrice());
            }
            if (forEdit.getCurrency_id() != null) {
                toEdit.setCurrency_id(forEdit.getCurrency_id());
            }
            if (forEdit.getAvailable_quantity() != 0) {
                toEdit.setAvailable_quantity(forEdit.getAvailable_quantity());
            }
            if (forEdit.getBuying_mode() != null) {
                toEdit.setBuying_mode(forEdit.getBuying_mode());
            }
            if (forEdit.getListing_type_id() != null) {
                toEdit.setListing_type_id(forEdit.getListing_type_id());
            }
            if (forEdit.getCondition() != null) {
                toEdit.setCurrency_id(forEdit.getCondition());
            }
            if (forEdit.getDescription() != null) {
                toEdit.setDescription(forEdit.getDescription());
            }
            if (forEdit.getVideo_id() != null) {
                toEdit.setVideo_id(forEdit.getVideo_id());
            }
            if (forEdit.getWarranty() != null) {
                toEdit.setWarranty(forEdit.getWarranty());
            }
            if (forEdit.getPictures() != null) {
                toEdit.setPictures(forEdit.getPictures());
            }


            if (forEdit.getId() != null) {
                toEdit.setId(forEdit.getId());
            }
            return toEdit;
        } catch (Exception ex) {
            throw new ItemsException(ex.getMessage());
        }
    }

    @Override
    public void deleteItem(String id) {
        userMap.remove(id);
    }

    @Override
    public boolean itemExist(String id) {
        return userMap.containsKey(id);
    }
}
*/
