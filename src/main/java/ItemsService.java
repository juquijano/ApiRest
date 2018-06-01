import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import java.util.Collection;

public interface ItemsService {

    public void addItem (Items item) throws Exception;
    public Collection<Items> getItem();
    public Items getItem(String id);
    public UpdateResponse editItem(String id, String jsonBody) throws ItemsException;
    public DeleteResponse deleteItem (String id);
}
