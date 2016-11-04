
package com.gssirohi.app.domain.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.gssirohi.app.domain.model.SummaryListContainer;

import java.util.ArrayList;
import java.util.List;


public class SeriesSummaryListContainer extends SummaryListContainer {

    @SerializedName("available")
    @Expose
    private Integer available;
    @SerializedName("returned")
    @Expose
    private Integer returned;
    @SerializedName("collectionURI")
    @Expose
    private String collectionURI;
    @SerializedName("items")
    @Expose
    private List<SeriesSummary> items = new ArrayList<SeriesSummary>();

    /**
     * @return The available
     */
    public Integer getAvailable() {
        return available;
    }

    /**
     * @param available The available
     */
    public void setAvailable(Integer available) {
        this.available = available;
    }

    /**
     * @return The returned
     */
    public Integer getReturned() {
        return returned;
    }

    /**
     * @param returned The returned
     */
    public void setReturned(Integer returned) {
        this.returned = returned;
    }

    /**
     * @return The collectionURI
     */
    public String getCollectionURI() {
        return collectionURI;
    }

    /**
     * @param collectionURI The collectionURI
     */
    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    /**
     * @return The items
     */
    public List<SeriesSummary> getItems() {
        return items;
    }

    /**
     * @param items The items
     */
    public void setItems(List<SeriesSummary> items) {
        this.items = items;
    }

}
