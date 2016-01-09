package pl.inzynierka.monia.mapa.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import pl.inzynierka.monia.mapa.CustomInfoWindow;
import pl.inzynierka.monia.mapa.R;
import pl.inzynierka.monia.mapa.models.Building;
import pl.inzynierka.monia.mapa.models.BuildingID;
import pl.inzynierka.monia.mapa.models.Identifier;

public class MapFragment extends Fragment {
    private static final GeoPoint defaultGeoPoint = new GeoPoint(51.753540, 19.452974);
    private static final String campusA = "a";
    private static final String campusB = "b";
    private static final String campusC = "c";
    private static final String campusD = "d";
    private static final String other = "e";
    private static final int ZOOM_LEVEL = 17;
    private View view;
    private MapView map;
    private int buildingId = -1;
    private Realm realm;
    private RealmList<BuildingID> buildingIDs;
    private List<Building> buildings;
    private MenuItem menuItemCampusA;
    private MenuItem menuItemCampusB;
    private MenuItem menuItemCampusC;
    private MenuItem menuItemCampusD;
    private MenuItem menuItemOtherCampus;

    public MapFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_map, container, false);

        initView();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.map_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
        setMenuItems(menu);
    }

    private void setMenuItems(Menu menu) {
        menuItemCampusA = menu.findItem(R.id.campusA);
        menuItemCampusB = menu.findItem(R.id.campusB);
        menuItemCampusC = menu.findItem(R.id.campusC);
        menuItemCampusD = menu.findItem(R.id.campusD);
        menuItemOtherCampus = menu.findItem(R.id.otherCampus);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setChecked(!item.isChecked());

        map.getOverlays().clear();

        if (menuItemCampusA.isChecked()) {
            addMarkersFromCampus(campusA);
        }

        if (menuItemCampusB.isChecked()) {
            addMarkersFromCampus(campusB);
        }

        if (menuItemCampusC.isChecked()) {
            addMarkersFromCampus(campusC);
        }

        if (menuItemCampusD.isChecked()) {
            addMarkersFromCampus(campusD);
        }

        if (menuItemOtherCampus.isChecked()) {
            addMarkersFromCampus(other);
        }

        return true;
    }

    @SuppressWarnings("deprecation")
    private void addMarkersFromCampus(String markLetter) {
        for (Building building : buildings) {
            if (building.getIdentifier().getMarkLetter().equals(markLetter)) {
                addBuildingMarker(building, getResources().getDrawable(R.drawable.icon_marker), false, true);
            }
        }
    }

    private void initView() {
        setHasOptionsMenu(true);
        setMap();
        setViewOnPoint(map, new GeoPoint(51.745435, 19.451648), ZOOM_LEVEL);
        getBuildings();

        showMyLocation();

        addAllMarkers(buildingId);

    }

    private void getBuildings() {
        buildings = realm.where(Building.class).findAll();
    }

    private void setMap() {
        map = (MapView) view.findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);
    }

    private void setViewOnPoint(MapView map, GeoPoint geoPoint, int zoomLevel) {
        final IMapController mapController = map.getController();
        mapController.setZoom(zoomLevel);
        mapController.setCenter(geoPoint);
    }

    private void showMyLocation() {
        final MyLocationNewOverlay mLocationOverlay =
                new MyLocationNewOverlay(view.getContext(), new GpsMyLocationProvider(view.getContext()), map);
        mLocationOverlay.enableMyLocation();
        map.getOverlays().add(mLocationOverlay);
        map.invalidate();
    }

    @SuppressWarnings("deprecation")
    private void addAllMarkers(int buildingId) {
        Building chosenBuilding = null;

        for (Building building : buildings) {
            if (buildingId != -1 && building.getId() == buildingId) {
                chosenBuilding = building;
            } else {
                addBuildingMarker(building, getResources().getDrawable(R.drawable.icon_marker), false, true);
            }
        }

        addBuildingMarker(chosenBuilding, getResources().getDrawable(R.drawable.icon_marker_dark), true, false);
    }

    private void addBuildingMarker(Building building, Drawable icon, boolean showInfoWindow, boolean defaultPosition) {
        if (building == null) return;

        final GeoPoint geoPoint = new GeoPoint(building.getLatitude(), building.getLongitude());
        final Identifier bIdentifier = building.getIdentifier();
        final String title = bIdentifier.getMarkLetter().toUpperCase() +
                bIdentifier.getMarkNumber() + " " + bIdentifier.getName();

        if (defaultPosition) {
            addMarker(geoPoint, defaultGeoPoint, icon, title, "", showInfoWindow);
        } else {
            addMarker(geoPoint, geoPoint, icon, title, "", showInfoWindow);
        }

    }

    @SuppressWarnings("deprecation")
    public void addMarker(GeoPoint geoPoint, GeoPoint pointView, Drawable icon,
                          String title, String description, boolean showInfoWindow) {
        final Marker startMarker = new Marker(map);

        setViewOnPoint(map, pointView, ZOOM_LEVEL);

        startMarker.setPosition(geoPoint);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        startMarker.setIcon(icon);
        startMarker.setTitle(title);
        startMarker.setSubDescription(description);
        startMarker.setInfoWindow(new CustomInfoWindow(view, map));

        if (showInfoWindow) {
            startMarker.showInfoWindow();
        }

        map.getOverlays().add(startMarker);

        map.invalidate();
    }

    public void passData(int buildingId, Realm realm) {
        this.buildingId = buildingId;
        this.realm = realm;
    }

    public void passData(RealmList<BuildingID> buildingIDs, Realm realm) {
        this.buildingIDs = buildingIDs;
        this.realm = realm;
    }
}