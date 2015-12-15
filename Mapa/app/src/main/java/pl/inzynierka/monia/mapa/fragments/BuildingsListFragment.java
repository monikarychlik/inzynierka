package pl.inzynierka.monia.mapa.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import pl.inzynierka.monia.mapa.R;
import pl.inzynierka.monia.mapa.Searcher;
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

    public BuildingsListFragment() {}

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainActivityCallbacks = (MainActivityCallbacks) activity;
    }

    @SuppressWarnings("deprecation")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        initView(inflater, container);
        setupAdapter();

        return view;
    }

    private void initView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_buildings_list, container, false);
        buildings = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        editTextSearch = (EditText) view.findViewById(R.id.editTextSearch);
        textViewSearchInfo = (TextView) view.findViewById(R.id.searchInfo);
        imageViewSearch = (ImageView) view.findViewById(R.id.imageViewSearch);
        buildingListLayout = (LinearLayout) view.findViewById(R.id.buildingListLayout);
        searcher = new Searcher(realm);

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
            searcher.search(s.toString(), buildings);
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
                showSoftKeyboard(getActivity());
                break;

            case R.id.buildingListLayout:
                editTextSearch.clearFocus();
                hideSoftKeyboard(getActivity());
                break;
        }
    }

    @SuppressWarnings("ConstantConditions")
    public static void hideSoftKeyboard(Activity activity) {
        final InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static void showSoftKeyboard(Activity activity) {
        final InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public void passData(Realm realm){
        this.realm = realm;
    }
}
