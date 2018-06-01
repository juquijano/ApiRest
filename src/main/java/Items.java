import java.util.ArrayList;

public class Items {

    private String id;
    private String title;
    private String category_id;
    private int price;
    private String currency_id;
    private int available_quantity;
    private String buying_mode;
    private String listing_type_id;
    private String condition;
    private String description;
    private String video_id;
    private String warranty;
    private ArrayList pictures;

    public Items() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(String currency_id) {
        this.currency_id = currency_id;
    }

    public int getAvailable_quantity() {
        return available_quantity;
    }

    public void setAvailable_quantity(int available_quantity) {
        this.available_quantity = available_quantity;
    }

    public String getBuying_mode() {
        return buying_mode;
    }

    public void setBuying_mode(String buying_mode) {
        this.buying_mode = buying_mode;
    }

    public String getListing_type_id() {
        return listing_type_id;
    }

    public void setListing_type_id(String listing_type_id) {
        this.listing_type_id = listing_type_id;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public ArrayList getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList pictures) {
        this.pictures = pictures;
    }

    // Tech debt to improve method
    public boolean validItem() {
        if (this.getId() == null
                || this.getTitle() == null
                || this.getCategory_id() == null
                || this.getCurrency_id() == null
                || this.getBuying_mode() == null
                || this.getListing_type_id() == null
                || this.getCondition() == null
                || this.getDescription() == null
                || this.getVideo_id() == null
                || this.getWarranty() == null
                || this.getPictures() == null) {
            return false;
        }
        return true;
    }
}
