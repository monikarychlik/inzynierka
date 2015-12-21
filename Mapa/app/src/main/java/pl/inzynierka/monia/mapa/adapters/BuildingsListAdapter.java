package pl.inzynierka.monia.mapa.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pl.inzynierka.monia.mapa.R;
import pl.inzynierka.monia.mapa.callbacks.MainActivityCallbacks;
import pl.inzynierka.monia.mapa.models.Building;
import pl.inzynierka.monia.mapa.models.Identifier;

public class BuildingsListAdapter extends RecyclerView.Adapter<BuildingsListAdapter.CustomViewHolder> {
    private List<Building> buildings = new ArrayList<>();
    private MainActivityCallbacks mainActivityCallbacks;

    public BuildingsListAdapter(List<Building> buildings, MainActivityCallbacks mainActivityCallbacks) {
        this.buildings = buildings;
        this.mainActivityCallbacks = mainActivityCallbacks;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.fragment_building_row, parent, false);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int position) {
        final Building building = buildings.get(position);
        final Identifier buildingIdentifier = building.getIdentifier();
        final String buildingSign = buildingIdentifier.getMarkLetter().toUpperCase()
                + buildingIdentifier.getMarkNumber();

        customViewHolder.textViewBuildingSign.setText(buildingSign);
        customViewHolder.textViewBuildingName.setText(buildingIdentifier.getName());
        setListeners(customViewHolder, building, buildingSign);
    }

    private void setListeners(CustomViewHolder customViewHolder, final Building building, final String buildingSign) {
        customViewHolder.imageViewShowOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityCallbacks.passData(building.getId());
                mainActivityCallbacks.changeToMapFragment("");
            }
        });
        customViewHolder.buildingItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityCallbacks.passData(building.getId());
                mainActivityCallbacks.changeToBuildingInfoFragment(buildingSign);
            }
        });
    }

    @Override
    public int getItemCount() {
        return buildings.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView textViewBuildingSign;
        protected TextView textViewBuildingName;
        protected ImageView imageViewShowOnMap;
        protected CardView buildingItemLayout;

        public CustomViewHolder(View view) {
            super(view);
            this.textViewBuildingSign = (TextView) view.findViewById(R.id.textViewBuildingSign);
            this.textViewBuildingName = (TextView) view.findViewById(R.id.textViewBuildingName);
            this.imageViewShowOnMap = (ImageView) view.findViewById(R.id.imageViewShowOnMap);
            this.buildingItemLayout = (CardView) view.findViewById(R.id.buildingItemLayout);
        }
    }
}