package com.gssirohi.app.domain.repository.api;

import com.gssirohi.app.constant.Products;
import com.gssirohi.app.domain.model.ProductModel;
import com.gssirohi.app.domain.model.pojo.CharacterDataContainer;
import com.gssirohi.app.domain.model.pojo.CharacterResponseContainer;
import com.gssirohi.app.domain.model.pojo.Comic;
import com.gssirohi.app.domain.model.pojo.ComicsDataContainer;
import com.gssirohi.app.domain.model.pojo.ComicsResponseContainer;
import com.gssirohi.app.domain.model.pojo.Creator;
import com.gssirohi.app.domain.model.pojo.CreatorDataContainer;
import com.gssirohi.app.domain.model.pojo.CreatorResponseContainer;
import com.gssirohi.app.domain.model.pojo.Event;
import com.gssirohi.app.domain.model.pojo.EventDataContainer;
import com.gssirohi.app.domain.model.pojo.EventResponseContainer;
import com.gssirohi.app.domain.model.pojo.MCharacter;
import com.gssirohi.app.domain.model.pojo.Series;
import com.gssirohi.app.domain.model.pojo.SeriesDataContainer;
import com.gssirohi.app.domain.model.pojo.SeriesResponseContainer;
import com.gssirohi.app.domain.model.pojo.Story;
import com.gssirohi.app.domain.model.pojo.StoryDataContainer;
import com.gssirohi.app.domain.model.pojo.StoryResponseContainer;
import com.gssirohi.app.network.ResponseContainer;

import java.util.List;

/**
 * Created by gssirohi on 11/7/16.
 */
public class APIResponseMapper {


    public <T extends ProductModel> List<T> getProductListFromResponse(ResponseContainer container, Products type) {
        switch (type) {
            case COMIC:
                return (List<T>) getComicsListFromResponse(container);
            case CHARACTER:
                return (List<T>) getCharactersListFromResponse(container);
            case EVENT:
                return (List<T>) getEventListFromResponse(container);
            case SERIES:
                return (List<T>) getSeriesListFromResponse(container);
            case STORY:
                return (List<T>) getStoryListFromResponse(container);
            case CREATOR:
                return (List<T>) getCreatorListFromResponse(container);
        }
        return null;
    }

    public <T extends ProductModel> T getProductFromResponse(ResponseContainer container, Products type) {
        switch (type) {
            case COMIC:
                return (T) getComicFromResponse(container);
            case CHARACTER:
                return (T) getCharacterFromResponse(container);
            case EVENT:
                return (T) getEventFromResponse(container);
            case SERIES:
                return (T) getSeriesFromResponse(container);
            case STORY:
                return (T) getStoryFromResponse(container);
            case CREATOR:
                return (T) getCreatorFromResponse(container);
        }
        return null;
    }

    public List<MCharacter> getCharactersListFromResponse(ResponseContainer container) {
        if (container == null)
            return null;

        CharacterResponseContainer listContainer = (CharacterResponseContainer) container;
        CharacterDataContainer data = listContainer.getProductsDataContainer();
        if (data == null) {
            return null;
        }
        return data.getResults();
    }

    public List<Event> getEventListFromResponse(ResponseContainer container) {
        if (container == null)
            return null;

        EventResponseContainer listContainer = (EventResponseContainer) container;
        EventDataContainer data = listContainer.getProductsDataContainer();
        if (data == null) {
            return null;
        }
        return data.getResults();
    }

    public List<Series> getSeriesListFromResponse(ResponseContainer container) {
        if (container == null)
            return null;

        SeriesResponseContainer listContainer = (SeriesResponseContainer) container;
        SeriesDataContainer data = listContainer.getProductsDataContainer();
        if (data == null) {
            return null;
        }
        return data.getResults();
    }


    public List<Story> getStoryListFromResponse(ResponseContainer container) {
        if (container == null)
            return null;

        StoryResponseContainer listContainer = (StoryResponseContainer) container;
        StoryDataContainer data = listContainer.getProductsDataContainer();
        if (data == null) {
            return null;
        }
        return data.getResults();
    }


    public List<Creator> getCreatorListFromResponse(ResponseContainer container) {
        if (container == null)
            return null;

        CreatorResponseContainer listContainer = (CreatorResponseContainer) container;
        CreatorDataContainer data = listContainer.getProductsDataContainer();
        if (data == null) {
            return null;
        }
        return data.getResults();
    }

    public List<Comic> getComicsListFromResponse(ResponseContainer container) {
        if (container == null)
            return null;

        ComicsResponseContainer listContainer = (ComicsResponseContainer) container;
        ComicsDataContainer data = listContainer.getProductsDataContainer();
        if (data == null) {
            return null;
        }
        return data.getResults();
    }

    public MCharacter getCharacterFromResponse(ResponseContainer container) {
        if (container == null)
            return null;

        CharacterResponseContainer listContainer = (CharacterResponseContainer) container;
        CharacterDataContainer data = listContainer.getProductsDataContainer();
        if (data == null) {
            return null;
        }
        return data.getResults().get(0);
    }

    public Series getSeriesFromResponse(ResponseContainer container) {
        if (container == null)
            return null;

        SeriesResponseContainer listContainer = (SeriesResponseContainer) container;
        SeriesDataContainer data = listContainer.getProductsDataContainer();
        if (data == null) {
            return null;
        }
        return data.getResults().get(0);
    }

    public Story getStoryFromResponse(ResponseContainer container) {
        if (container == null)
            return null;

        StoryResponseContainer listContainer = (StoryResponseContainer) container;
        StoryDataContainer data = listContainer.getProductsDataContainer();
        if (data == null) {
            return null;
        }
        return data.getResults().get(0);
    }

    public Event getEventFromResponse(ResponseContainer container) {
        if (container == null)
            return null;

        EventResponseContainer listContainer = (EventResponseContainer) container;
        EventDataContainer data = listContainer.getProductsDataContainer();
        if (data == null) {
            return null;
        }
        return data.getResults().get(0);
    }


    public Creator getCreatorFromResponse(ResponseContainer container) {
        if (container == null)
            return null;

        CreatorResponseContainer listContainer = (CreatorResponseContainer) container;
        CreatorDataContainer data = listContainer.getProductsDataContainer();
        if (data == null) {
            return null;
        }
        return data.getResults().get(0);
    }

    private Comic getComicFromResponse(ResponseContainer container) {
        if (container == null)
            return null;

        ComicsResponseContainer listContainer = (ComicsResponseContainer) container;
        ComicsDataContainer data = listContainer.getProductsDataContainer();
        if (data == null) {
            return null;
        }
        return data.getResults().get(0);
    }
}
