package pl.inzynierka.monia.mapa.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.overlays.InfoWindow;
import org.osmdroid.bonuspack.overlays.MapEventsReceiver;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.bonuspack.overlays.Polyline;
import org.osmdroid.bonuspack.routing.GraphHopperRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import pl.inzynierka.monia.mapa.utils.CustomInfoWindow;
import pl.inzynierka.monia.mapa.R;
import pl.inzynierka.monia.mapa.models.Building;
import pl.inzynierka.monia.mapa.models.BuildingID;
import pl.inzynierka.monia.mapa.models.Identifier;

public class MapFragment extends Fragment implements MapEventsReceiver {
    private static final GeoPoint defaultGeoPoint = new GeoPoint(51.753540, 19.452974);
    private static final String campusA = "a";
    private static final String campusB = "b";
    private static final String campusC = "c";
    private static final String campusD = "d";
    private static final String other = "e";
    private static final int ZOOM_LEVEL = 17;
    private List<BuildingID> buildingIDs = new ArrayList<>();
    private List<Building> buildings = new ArrayList<>();
    private MenuItem menuItemCampusA;
    private MenuItem menuItemCampusB;
    private MenuItem menuItemCampusC;
    private MenuItem menuItemCampusD;
    private MenuItem menuItemOtherCampus;
    private View view;
    private MapView map;
    private int chosenBuildingId = -1;
    private Realm realm;
    private int radioButtonTypeOfTravelId = -1;
    private boolean navigateFromMyLocation;
    private Building pointA;
    private Building pointB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_map, container, false);

        initView();
        getBuildings();
        showMyLocation();

        if (radioButtonTypeOfTravelId != -1) {
            routing();
        } else {
            addAllMarkers();
        }

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
                addBuildingMarker(building, getResources().getDrawable(R.drawable.icon_marker),
                        false, true);
            }
        }
    }

    private void initView() {
        realm = Realm.getInstance(getActivity());

        setHasOptionsMenu(true);
        setMap();
        setViewOnPoint(map, new GeoPoint(51.745435, 19.451648), ZOOM_LEVEL);
    }

    @SuppressWarnings("deprecation")
    private void routing() {
        final ArrayList<GeoPoint> wayPoints = new ArrayList<>();
        GeoPoint startPoint;

        if (navigateFromMyLocation) {
            startPoint = checkPermissionsAndGetMyLocation();
        } else {
            startPoint = new GeoPoint(pointA.getLatitude(), pointA.getLongitude());
            addBuildingMarker(pointA, getResources().getDrawable(R.drawable.icon_marker), false, true);
        }

        final GeoPoint endPoint = new GeoPoint(pointB.getLatitude(), pointB.getLongitude());
        addBuildingMarker(pointB, getResources().getDrawable(R.drawable.icon_marker), false, true);

        wayPoints.add(startPoint);
        wayPoints.add(endPoint);

        new UpdateRoadTask().execute(wayPoints);
    }

    @Nullable
    private GeoPoint checkPermissionsAndGetMyLocation() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            final LocationManager locationManager =
                    (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

            Location myLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (myLocation == null) {
                myLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }

            if (myLocation != null) {
                return new GeoPoint(myLocation.getLatitude(), myLocation.getLongitude());
            }
        }

        return new GeoPoint(51.753663, 19.451716);
    }

    private class UpdateRoadTask extends AsyncTask<Object, Void, Road> {
        protected Road doInBackground(Object... params) {
            @SuppressWarnings("unchecked")
            final ArrayList<GeoPoint> wayPoints = (ArrayList<GeoPoint>) params[0];

            final RoadManager roadManager =
                    new GraphHopperRoadManager(getString(R.string.graphHopperApiKey));
            roadManager.addRequestOption("locale=" + Locale.getDefault().getLanguage());

            if (radioButtonTypeOfTravelId == R.id.radioButtonBike) {
                roadManager.addRequestOption("vehicle=bike");
            } else if (radioButtonTypeOfTravelId == R.id.radioButtonFoot) {
                roadManager.addRequestOption("vehicle=foot");
            }

            return roadManager.getRoad(wayPoints);
        }
        @Override
        protected void onPostExecute(Road road) {
            // // TODO: add info about road
            // showing distance and duration of the road
//            Toast.makeText(getActivity(), "distance = " + road.mLength, Toast.LENGTH_LONG).show();
//            Toast.makeText(getActivity(), "duration = " + road.mDuration, Toast.LENGTH_LONG).show();

            final Polyline roadOverlay = RoadManager.buildRoadOverlay(road, getActivity());

            map.getOverlays().add(roadOverlay);
            map.invalidate();
        }
    }


    @Override
    public boolean singleTapConfirmedHelper(GeoPoint p) {
        //TODO: does not work
        InfoWindow.closeAllInfoWindowsOn(map);
        map.invalidate();
        return true;
    }

    @Override
    public boolean longPressHelper(GeoPoint geoPoint) {
        return false;
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
        final MyLocationNewOverlay myLocationOverlay = new MyLocationNewOverlay(view.getContext(),
                new GpsMyLocationProvider(view.getContext()), map);
        myLocationOverlay.enableMyLocation();
        map.getOverlays().add(myLocationOverlay);
        map.invalidate();
    }

    @SuppressWarnings("deprecation")
    private void addAllMarkers() {
        final List<Building> chosenBuildings = new ArrayList<>();

        for (Building building : buildings) {
            if (chosenBuildingId != -1 && building.getId() == chosenBuildingId) {
                chosenBuildings.add(building);
                chosenBuildingId = -1;
            } else if (buildingIDs != null && !buildingIDs.isEmpty()) {
                for (BuildingID buildingID : buildingIDs) {
                    if (buildingID.getBuildingID() == building.getId()) {
                        chosenBuildings.add(building);
                    }
                }
            } else {
                addBuildingMarker(building, getResources().getDrawable(R.drawable.icon_marker),
                        false, true);
            }
        }

        for (Building chosenBuilding : chosenBuildings) {
            addBuildingMarker(chosenBuilding, getResources().getDrawable(R.drawable.icon_marker_dark),
                    true, false);
        }

        if (buildingIDs != null ) buildingIDs.clear();
    }

    private void addBuildingMarker(Building building, Drawable icon, boolean showInfoWindow,
                                   boolean defaultPosition) {
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
        final Marker marker = new Marker(map);

        setViewOnPoint(map, pointView, ZOOM_LEVEL);

        marker.setPosition(geoPoint);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setIcon(icon);
        marker.setTitle(title);
        marker.setSubDescription(description);
        marker.setInfoWindow(new CustomInfoWindow(map));

        if (showInfoWindow) {
            marker.showInfoWindow();
            map.getController().animateTo(marker.getPosition());
        }

        map.getOverlays().add(marker);
        map.invalidate();
    }

    public void passData(int buildingId) {
        this.chosenBuildingId = buildingId;
        this.buildingIDs = null;
    }

    public void passData(List<BuildingID> buildingIDs) {
        this.chosenBuildingId = -1;

        this.buildingIDs = new ArrayList<>();
        for (BuildingID buildingID : buildingIDs) {
            this.buildingIDs.add(buildingID);
        }
    }

    public void passData(int radioButtonTypeOfTravelId, boolean navigateFromMyLocation,
                         Building pointA, Building pointB) {
        this.radioButtonTypeOfTravelId = radioButtonTypeOfTravelId;
        this.navigateFromMyLocation = navigateFromMyLocation;
        this.pointA = pointA;
        this.pointB = pointB;
    }
}