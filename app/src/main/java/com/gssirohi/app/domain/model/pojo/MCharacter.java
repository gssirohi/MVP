
package com.gssirohi.app.domain.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.gssirohi.app.domain.model.ProductModel;

import java.util.ArrayList;
import java.util.List;


public class MCharacter extends ProductModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("resourceURI")
    @Expose
    private String resourceURI;
    @SerializedName("urls")
    @Expose
    private List<Url> urls = new ArrayList<Url>();
    @SerializedName("thumbnail")
    @Expose
    private Thumbnail thumbnail;
    @SerializedName("comics")
    @Expose
    private ComicSummaryListContainer comicSummaryListContainer;
    @SerializedName("stories")
    @Expose
    private StorySummaryListContainer storySummaryListContainer;
    @SerializedName("events")
    @Expose
    private EventSummaryListContainer eventSummaryListContainer;
    @SerializedName("series")
    @Expose
    private SeriesSummaryListContainer seriesSummaryListContainer;

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    @Override
    public CharacterSummaryListContainer getCharacterSummaryListContainer() {
        return null;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The modified
     */
    public String getModified() {
        return modified;
    }

    /**
     * @param modified The modified
     */
    public void setModified(String modified) {
        this.modified = modified;
    }

    /**
     * @return The resourceURI
     */
    public String getResourceURI() {
        return resourceURI;
    }

    /**
     * @param resourceURI The resourceURI
     */
    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    /**
     * @return The urls
     */
    public List<Url> getUrls() {
        return urls;
    }

    /**
     * @param urls The urls
     */
    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }

    /**
     * @return The thumbnail
     */
    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    /**
     * @param thumbnail The thumbnail
     */
    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * @return The comicSummaryListContainer
     */
    public ComicSummaryListContainer getComicSummaryListContainer() {
        return comicSummaryListContainer;
    }

    /**
     * @param comicSummaryListContainer The comicSummaryListContainer
     */
    public void setComicSummaryListContainer(ComicSummaryListContainer comicSummaryListContainer) {
        this.comicSummaryListContainer = comicSummaryListContainer;
    }

    /**
     * @return The storySummaryListContainer
     */
    public StorySummaryListContainer getStorySummaryListContainer() {
        return storySummaryListContainer;
    }

    @Override
    public CreatorSummaryListContainer getCreatorSummaryListContainer() {
        return null;
    }

    /**
     * @param storySummaryListContainer The storySummaryListContainer
     */
    public void setStorySummaryListContainer(StorySummaryListContainer storySummaryListContainer) {
        this.storySummaryListContainer = storySummaryListContainer;
    }

    /**
     * @return The eventSummaryListContainer
     */
    public EventSummaryListContainer getEventSummaryListContainer() {
        return eventSummaryListContainer;
    }

    /**
     * @param eventSummaryListContainer The eventSummaryListContainer
     */
    public void setEventSummaryListContainer(EventSummaryListContainer eventSummaryListContainer) {
        this.eventSummaryListContainer = eventSummaryListContainer;
    }

    /**
     * @return The seriesSummaryListContainer
     */
    public SeriesSummaryListContainer getSeriesSummaryListContainer() {
        return seriesSummaryListContainer;
    }

    /**
     * @param seriesSummaryListContainer The seriesSummaryListContainer
     */
    public void setSeriesSummaryListContainer(SeriesSummaryListContainer seriesSummaryListContainer) {
        this.seriesSummaryListContainer = seriesSummaryListContainer;
    }

}
