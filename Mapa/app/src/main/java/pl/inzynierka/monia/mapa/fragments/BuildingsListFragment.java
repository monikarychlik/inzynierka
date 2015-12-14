package pl.inzynierka.monia.mapa.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.realm.Realm;
import io.realm.RealmResults;
import pl.inzynierka.monia.mapa.R;
import pl.inzynierka.monia.mapa.adapters.BuildingsListAdapter;
import pl.inzynierka.monia.mapa.models.Building;

public class BuildingsListFragment extends Fragment {
    private RealmResults<Building> buildings;
    private View view;
    private BuildingsListAdapter adapter;
    private Realm realm;
    private RecyclerView recyclerView;

    public BuildingsListFragment() {}

    @SuppressWarnings("deprecation")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_buildings_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.list);

        realm.beginTransaction();
        buildings = realm.where(Building.class).findAll();
        realm.commitTransaction();

        adapter = new BuildingsListAdapter(buildings);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setBackgroundColor(getResources().getColor(R.color.cardview_light_background));
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void passData(Realm realm){
        this.realm = realm;
    }
}
