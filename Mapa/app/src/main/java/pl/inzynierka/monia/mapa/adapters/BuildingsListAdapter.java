package pl.inzynierka.monia.mapa.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pl.inzynierka.monia.mapa.R;
import pl.inzynierka.monia.mapa.models.Building;
import pl.inzynierka.monia.mapa.models.Identifier;

public class BuildingsListAdapter extends RecyclerView.Adapter<BuildingsListAdapter.CustomViewHolder> {
    private List<Building> buildings = new ArrayList<>();

    public BuildingsListAdapter(List<Building> buildings) {
        this.buildings = buildings;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.fragment_building_row, parent, false);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int position) {
        final Identifier buildingIdentifier = buildings.get(position).getIdentifier();

        final String buildingSign = buildingIdentifier.getMarkLetter().toUpperCase()
                + buildingIdentifier.getMarkNumber();
        customViewHolder.textViewBuildingSign.setText(buildingSign);
        customViewHolder.textViewBuildingName.setText(buildingIdentifier.getName());
    }

    @Override
    public int getItemCount() {
        return buildings.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView textViewBuildingSign;
        protected TextView textViewBuildingName;

        public CustomViewHolder(View view) {
            super(view);
            this.textViewBuildingSign = (TextView) view.findViewById(R.id.textViewBuildingSign);
            this.textViewBuildingName = (TextView) view.findViewById(R.id.textViewBuildingName);
        }
    }
}