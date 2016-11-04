package com.gssirohi.app.ui.viewmodel;


import com.gssirohi.app.domain.model.pojo.MCharacter;
import com.gssirohi.app.ui.viewmodel.contract.ICharactersListItemViewModel;

/**
 * Created by gssirohi on 7/7/16.
 */

public class CharactersListItemViewModel implements ICharactersListItemViewModel {


    private final MCharacter character;

    public CharactersListItemViewModel(MCharacter character) {
        this.character = character;
    }

    @Override
    public String getId() {
        return "" + character.getId();
    }

    @Override
    public String getName() {
        return character.getName();
    }

    @Override
    public int getComicsCount() {
        return character.getComicSummaryListContainer().getAvailable();
    }

    @Override
    public int getSeriesCount() {
        return character.getSeriesSummaryListContainer().getAvailable();
    }

    @Override
    public int getEventsCount() {
        return character.getEventSummaryListContainer().getAvailable();
    }

    @Override
    public int getStoriesCount() {
        return character.getStorySummaryListContainer().getAvailable();
    }

    @Override
    public String getImageUrl() {
        return character.getThumbnail().getPath() + "/standard_large." + character.getThumbnail().getExtension();
    }

    @Override
    public MCharacter getModel() {
        return character;
    }
}
