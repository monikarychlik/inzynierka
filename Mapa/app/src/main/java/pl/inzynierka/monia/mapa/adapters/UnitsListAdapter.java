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
import pl.inzynierka.monia.mapa.models.Identifier;
import pl.inzynierka.monia.mapa.models.Unit;

public class UnitsListAdapter extends RecyclerView.Adapter<UnitsListAdapter.CustomViewHolder> {
    private List<Unit> units = new ArrayList<>();
    private MainActivityCallbacks mainActivityCallbacks;

    public UnitsListAdapter(List<Unit> units, MainActivityCallbacks mainActivityCallbacks) {
        this.units = units;
        this.mainActivityCallbacks = mainActivityCallbacks;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.fragment_list_row_units, parent, false);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int position) {
        final Unit unit = units.get(position);
        final Identifier unitIdentifier = unit.getIdentifier();
        final String unitSign = unitIdentifier.getMarkLetter().toUpperCase()
                + unitIdentifier.getMarkNumber();

        customViewHolder.textViewUnitSign.setText(unitSign);
        customViewHolder.textViewUnitName.setText(unitIdentifier.getName());
        setListeners(customViewHolder, unit, unitSign);
    }

    private void setListeners(CustomViewHolder customViewHolder, final Unit unit, final String unitSign) {

        customViewHolder.imageViewShowOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityCallbacks.passBuildingsIDs(unit.getBuildingsIDs());
                mainActivityCallbacks.changeToMapFragment("");
            }
        });

        customViewHolder.unitItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityCallbacks.passUnitId(unit.getId());
                mainActivityCallbacks.changeToUnitInfoFragment(unitSign);
            }
        });
    }

    @Override
    public int getItemCount() {
        return units.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView textViewUnitSign;
        protected TextView textViewUnitName;
        protected ImageView imageViewShowOnMap;
        protected CardView unitItemLayout;

        public CustomViewHolder(View view) {
            super(view);
            this.textViewUnitSign = (TextView) view.findViewById(R.id.textViewUnitSign);
            this.textViewUnitName = (TextView) view.findViewById(R.id.textViewUnitName);
            this.imageViewShowOnMap = (ImageView) view.findViewById(R.id.imageViewUnitShowOnMap);
            this.unitItemLayout = (CardView) view.findViewById(R.id.itemLayoutUnit);
        }
    }
}