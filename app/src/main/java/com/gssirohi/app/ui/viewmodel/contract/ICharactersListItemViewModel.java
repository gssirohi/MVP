package com.gssirohi.app.ui.viewmodel.contract;

/**
 * Created by gssirohi on 7/7/16.
 */
public interface ICharactersListItemViewModel extends IViewModel {

    public String getId();

    public String getName();

    int getComicsCount();

    int getSeriesCount();

    int getEventsCount();

    int getStoriesCount();

    String getImageUrl();
}
