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
import pl.inzynierka.monia.mapa.models.Unit;
import pl.inzynierka.monia.mapa.utils.Keyboard;
import pl.inzynierka.monia.mapa.utils.Searcher;
import pl.inzynierka.monia.mapa.adapters.UnitsListAdapter;
import pl.inzynierka.monia.mapa.callbacks.MainActivityCallbacks;

public class UnitsListFragment extends Fragment implements TextWatcher, View.OnClickListener {
    private View view;
    private Realm realm;
    private List<Unit> units;
    private UnitsListAdapter adapter;
    private RecyclerView recyclerView;
    private EditText editTextSearch;
    private TextView textViewSearchInfo;
    private ImageView imageViewSearch;
    private LinearLayout buildingListLayout;
    private Searcher searcher;
    private MainActivityCallbacks mainActivityCallbacks;
    private Keyboard keyboard;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        initView(inflater, container);
        setupAdapter();

        return view;
    }

    private void initView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_units_list, container, false);
        mainActivityCallbacks = (MainActivityCallbacks) getActivity();
        units = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.listUnits);
        editTextSearch = (EditText) view.findViewById(R.id.editTextSearchUnits);
        textViewSearchInfo = (TextView) view.findViewById(R.id.searchInfoUnits);
        imageViewSearch = (ImageView) view.findViewById(R.id.imageViewSearchUnits);
        buildingListLayout = (LinearLayout) view.findViewById(R.id.unitsListLayout);
        searcher = new Searcher(realm);
        keyboard = new Keyboard(getActivity());

        setListeners();
        findUnits();
    }

    private void setListeners() {
        editTextSearch.addTextChangedListener(this);
        imageViewSearch.setOnClickListener(this);
        buildingListLayout.setOnClickListener(this);
    }

    private void findUnits() {
        final List<Unit> tempUnits = realm.where(Unit.class).findAll();
        for (Unit unit : tempUnits) {
            units.add(unit);
        }
    }

    private void setupAdapter() {
        adapter = new UnitsListAdapter(units, mainActivityCallbacks);

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
            findUnits();
        } else {
            searcher.searchUnits(s.toString(), units);
        }
        adapter.notifyDataSetChanged();

        if (units.isEmpty()) {
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

    public void passData(Realm realm){
        this.realm = realm;
    }
}

