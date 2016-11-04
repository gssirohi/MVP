package com.gssirohi.app.ui.viewcontract;

import com.gssirohi.app.domain.model.pojo.MCharacter;

import java.util.List;

/**
 * Created by gssirohi on 5/7/16.
 */
public interface CharactersScreenViewContract extends ViewContract {
    void onAllCharacterFetched(List<MCharacter> characters);
}
