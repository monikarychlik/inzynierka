package pl.inzynierka.monia.mapa.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.realm.Realm;
import pl.inzynierka.monia.mapa.R;
import pl.inzynierka.monia.mapa.adapters.UnitsListAdapter;
import pl.inzynierka.monia.mapa.callbacks.MainActivityCallbacks;
import pl.inzynierka.monia.mapa.models.Building;
import pl.inzynierka.monia.mapa.models.Identifier;
import pl.inzynierka.monia.mapa.models.Unit;
import pl.inzynierka.monia.mapa.utils.Keyboard;

public class BuildingInfoFragment extends Fragment {
    private View view;
    private int buildingId;
    private Building building;
    private RecyclerView recyclerView;
    private TextView textViewTitleBuildingSign;
    private TextView textViewTitleBuildingName;
    private MainActivityCallbacks mainActivityCallbacks;
    private LinearLayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_building_info, container, false);

        initView();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        refreshAdapter();
    }

    private void refreshAdapter() {
        layoutManager.scrollToPosition(0);
    }

    private void initView() {
        final Realm realm = Realm.getInstance(getActivity());
        building = realm.where(Building.class).equalTo("id", buildingId).findFirst();

        mainActivityCallbacks = (MainActivityCallbacks) getActivity();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewUnits);
        textViewTitleBuildingSign = (TextView) view.findViewById(R.id.textViewTitleBuildingSign);
        textViewTitleBuildingName = (TextView) view.findViewById(R.id.textViewTitleBuildingName);

        final Keyboard keyboard = new Keyboard(getActivity());
        keyboard.hideSoftKeyboard();

        setInfoTitle();
        setupAdapter();
    }

    private void setInfoTitle() {
        final Identifier buildingIdentifier = building.getIdentifier();
        final String buildingSign = buildingIdentifier.getMarkLetter().toUpperCase()
                + buildingIdentifier.getMarkNumber();

        textViewTitleBuildingSign.setText(buildingSign);
        textViewTitleBuildingName.setText(buildingIdentifier.getName());
    }

    @SuppressWarnings("deprecation")
    private void setupAdapter() {
        final List<Unit> units = building.getUnits();
        final TextView textViewNoUnitsInfo = (TextView) view.findViewById(R.id.noUnitsInfo);

        if (units.size() == 0) {
            textViewNoUnitsInfo.setVisibility(View.VISIBLE);
            textViewNoUnitsInfo.bringToFront();
        } else {
            textViewNoUnitsInfo.setVisibility(View.GONE);
            recyclerView.bringToFront();
        }

        final UnitsListAdapter unitsListAdapter = new UnitsListAdapter(units, mainActivityCallbacks);
        layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setBackgroundColor(getResources().getColor(R.color.cardview_light_background));
        recyclerView.setAdapter(unitsListAdapter);
    }

    public void passData(int buildingId) {
        this.buildingId = buildingId;
    }
}
