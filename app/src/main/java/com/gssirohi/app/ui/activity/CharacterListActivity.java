package com.gssirohi.app.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.Menu;
import android.view.MenuItem;

import com.gssirohi.app.R;
import com.gssirohi.app.base.BaseActivity;
import com.gssirohi.app.domain.model.pojo.ComicSummary;
import com.gssirohi.app.domain.model.pojo.ComicSummaryListContainer;
import com.gssirohi.app.domain.model.pojo.MCharacter;
import com.gssirohi.app.ui.customview.ProductCardView;
import com.gssirohi.app.ui.fragment.CharacterListTabFragment;
import com.gssirohi.app.ui.fragment.ProductDetailsFragment;
import com.gssirohi.app.ui.viewmodel.contract.IProductViewModel;

import java.util.ArrayList;
import java.util.List;

public class CharacterListActivity extends BaseActivity implements ProductCardView.ProductCardViewContract {

    private List<MCharacter> characters = new ArrayList<>();

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters_list);
        CharacterListTabFragment frag = CharacterListTabFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, frag, "tabs").commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_trips_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public static Intent getCallingIntent(Context context) {
        Intent i = new Intent(context, CharacterListActivity.class);
        return i;
    }


    @Override
    public void onBackPressed() {
        int fragments = getSupportFragmentManager().getBackStackEntryCount();
        if (fragments == 1) {
            finish();
            return;
        }

        super.onBackPressed();
    }


    public MCharacter getCharacterById(String characterId) {
        for (MCharacter item : characters) {
            if (("" + item.getId()).equals(characterId)) {
                return item;
            }
        }
        return null;
    }

    private boolean isAvenger(MCharacter character) {
        ComicSummaryListContainer comicSummaryListContainer = character.getComicSummaryListContainer();
        List<ComicSummary> items = comicSummaryListContainer.getItems();
        for (ComicSummary item : items) {
            if (item.getName().contains("Avenger")) {
                return true;
            }
        }
        return false;
    }

    public List<MCharacter> getCharacters() {
        return this.characters;
    }

    public List<MCharacter> getCharactersList(int tabPosition) {
        ArrayList<MCharacter> characters = new ArrayList<>();
        switch (tabPosition) {
            case 0:
                for (MCharacter character : getCharacters()) {
                    if (isAvenger(character)) {
                        characters.add(character);
                    }
                }
                break;
            case 1:
                for (MCharacter character : getCharacters()) {
                    if (!isAvenger(character)) {
                        characters.add(character);
                    }
                }
                break;
            default:
        }
        return characters;
    }

    @Override
    public void onCardViewItemClicked(IProductViewModel viewModel) {
        ProductDetailsFragment newFragment = ProductDetailsFragment.newInstance(viewModel.getResourceUri());
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //TODO: put tablet and mobile logic here
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
