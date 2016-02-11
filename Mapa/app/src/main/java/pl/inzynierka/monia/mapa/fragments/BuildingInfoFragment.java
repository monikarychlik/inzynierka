package pl.inzynierka.monia.mapa.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


import io.realm.Realm;
import pl.inzynierka.monia.mapa.R;
import pl.inzynierka.monia.mapa.adapters.BuildingInfoAdapter;
import pl.inzynierka.monia.mapa.models.Building;
import pl.inzynierka.monia.mapa.models.Identifier;
import pl.inzynierka.monia.mapa.utils.Keyboard;

public class BuildingInfoFragment extends Fragment {
    private View view;
    private Realm realm;
    private int buildingId;
    private Building building;
    private ListView listViewUnits;
    private TextView textViewTitleBuildingSign;
    private TextView textViewTitleBuildingName;

    public BuildingInfoFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_building_info, container, false);

        initView();

        return view;
    }

    private void initView() {
        realm = Realm.getInstance(getActivity());
        listViewUnits = (ListView) view.findViewById(R.id.listViewUnits);
        textViewTitleBuildingSign = (TextView) view.findViewById(R.id.textViewTitleBuildingSign);
        textViewTitleBuildingName = (TextView) view.findViewById(R.id.textViewTitleBuildingName);
        building = realm.where(Building.class).equalTo("id", buildingId).findFirst();

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

    private void setupAdapter() {
        final BuildingInfoAdapter adapter = new BuildingInfoAdapter(getContext(), building);
        listViewUnits.setAdapter(adapter);
    }

    public void passData(int buildingId) {
        this.buildingId = buildingId;
    }
}
