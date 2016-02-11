package pl.inzynierka.monia.mapa.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import pl.inzynierka.monia.mapa.R;
import pl.inzynierka.monia.mapa.utils.Keyboard;
import pl.inzynierka.monia.mapa.utils.Searcher;
import pl.inzynierka.monia.mapa.adapters.BuildingsListAdapter;
import pl.inzynierka.monia.mapa.callbacks.MainActivityCallbacks;
import pl.inzynierka.monia.mapa.models.Building;

public class BuildingsListFragment extends Fragment implements TextWatcher, View.OnClickListener {
    private View view;
    private Realm realm;
    private List<Building> buildings;
    private BuildingsListAdapter adapter;
    private RecyclerView recyclerView;
    private EditText editTextSearch;
    private TextView textViewSearchInfo;
    private ImageView imageViewSearch;
    private LinearLayout buildingListLayout;
    private Searcher searcher;
    private MainActivityCallbacks mainActivityCallbacks;
    private Keyboard keyboard;

    @SuppressWarnings("deprecation")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_buildings_list, container, false);

        initView();
        setupAdapter();

        return view;
    }

    private void initView() {
        realm = Realm.getInstance(getActivity());
        mainActivityCallbacks = (MainActivityCallbacks) getActivity();
        buildings = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        editTextSearch = (EditText) view.findViewById(R.id.editTextSearch);
        textViewSearchInfo = (TextView) view.findViewById(R.id.searchInfo);
        imageViewSearch = (ImageView) view.findViewById(R.id.imageViewSearch);
        buildingListLayout = (LinearLayout) view.findViewById(R.id.buildingListLayout);

        searcher = new Searcher(getActivity());
        keyboard = new Keyboard(getActivity());

        setListeners();
        findBuildings();
    }

    private void setListeners() {
        editTextSearch.addTextChangedListener(this);
        imageViewSearch.setOnClickListener(this);
        buildingListLayout.setOnClickListener(this);
    }

    private void findBuildings() {
        final List<Building> tempBuildings = realm.where(Building.class).findAll();
        for (Building building : tempBuildings) {
            buildings.add(building);
        }
    }

    @SuppressWarnings("deprecation")
    private void setupAdapter() {
        adapter = new BuildingsListAdapter(buildings, mainActivityCallbacks);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setBackgroundColor(getResources().getColor(R.color.cardview_light_background));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        textViewSearchInfo.setVisibility(View.INVISIBLE);

        if (s.toString().isEmpty()) {
            findBuildings();
        } else {
            searcher.searchAll(s.toString(), buildings);
        }
        adapter.notifyDataSetChanged();

        if (buildings.isEmpty()) {
            textViewSearchInfo.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {}

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.imageViewSearch:
                editTextSearch.requestFocus();
                keyboard.showSoftKeyboard();
                break;

            case R.id.buildingListLayout:
                editTextSearch.clearFocus();
                keyboard.hideSoftKeyboard();
                break;
        }
    }
}
