import com.google.gson.Gson;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.update.UpdateResponse;

import java.util.Collection;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        port(8080);
        final ItemsService itemsService = new ItemsServiceImpl();

        post("/items/:id", (request, response) -> {
            response.type("application/json");
            Items item = new Gson().fromJson(request.body(), Items.class);
            item.setId(request.params(":id"));
            if (!item.validItem()) {
                return new Gson().toJson(
                        new StandardResponse(StatusResponse.ERROR, new Gson()
                                .toJson("Faltan campos en el item."))
                );
            }
            try {
                itemsService.addItem(item);
            } catch (Exception e) {
                return new Gson().toJson(
                        new StandardResponse(StatusResponse.ERROR, new Gson()
                                .toJson("Error: " + e))
                );
            }
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        });

        post("/items", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(
                    new StandardResponse(StatusResponse.ERROR, new Gson()
                            .toJson("Falta el parámetro \"id\" del item."))
            );
        });

        get("/items/:id", (request, response) -> {
            response.type("application/json");
            Items item = itemsService.getItem(request.params(":id"));
            if (item != null) {
                return new Gson().toJson(
                        new StandardResponse(StatusResponse.SUCCESS, new Gson()
                                .toJsonTree(item))
                );
            } else {
                return new Gson().toJson(
                        new StandardResponse(StatusResponse.ERROR, new Gson()
                                .toJson("No existe el item."))
                );
            }
        });

        get("/items", (request, response) -> {
            response.type("application/json");
            Collection<Items> items = itemsService.getItem();
            if (items != null) {
                return new Gson().toJson(
                        new StandardResponse(StatusResponse.SUCCESS, new Gson()
                                .toJsonTree(itemsService.getItem()))
                );
            } else {
                return new Gson().toJson(
                        new StandardResponse(StatusResponse.ERROR, new Gson()
                                .toJson("No existe ningún item."))
                );
            }
        });

        put("/items/:id", (request, response) -> {
            response.type("application/json");
            UpdateResponse updateResponse = itemsService.editItem(request.params(":id"), request.body());
        if (updateResponse.getResult() == DocWriteResponse.Result.UPDATED) {
                return new Gson().toJson(
                        new StandardResponse(StatusResponse.SUCCESS, new Gson()
                                .toJsonTree(updateResponse.getResult()))
                );
            } else {
                return new Gson().toJson(
                        new StandardResponse(StatusResponse.ERROR, new Gson()
                                .toJson("No se actualizó el item."))
                );
            }
        });

        delete("/items/:id", (request, response) -> {
            response.type("application/json");
            if (request.params(":id") == null) {
                return new Gson().toJson(
                        new StandardResponse(StatusResponse.ERROR, "Debe ingresar un id.")
                );
            }
            DeleteResponse deleteResponse = itemsService.deleteItem(request.params(":id"));
            if (deleteResponse.getResult() == DocWriteResponse.Result.NOT_FOUND) {
                return new Gson().toJson(
                        new StandardResponse(StatusResponse.ERROR, "Item no encontrado.")
                );
            }
            return new Gson().toJson(
                    new StandardResponse(StatusResponse.SUCCESS, "Item borrado.")
            );
        });

        delete("/items", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(
                    new StandardResponse(StatusResponse.ERROR, new Gson()
                            .toJson("Falta el parámetro \"id\" del item."))
            );
        });
    }
}




