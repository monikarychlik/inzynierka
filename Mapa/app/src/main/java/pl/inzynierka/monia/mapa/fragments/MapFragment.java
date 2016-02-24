package pl.inzynierka.monia.mapa.fragments;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
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
import android.widget.ImageButton;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.overlays.InfoWindow;
import org.osmdroid.bonuspack.overlays.MapEventsOverlay;
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
import pl.inzynierka.monia.mapa.callbacks.MainActivityCallbacks;
import pl.inzynierka.monia.mapa.utils.CustomInfoWindow;
import pl.inzynierka.monia.mapa.R;
import pl.inzynierka.monia.mapa.models.Building;
import pl.inzynierka.monia.mapa.models.BuildingID;
import pl.inzynierka.monia.mapa.models.Identifier;

public class MapFragment extends Fragment
        implements MapEventsReceiver, LocationListener, View.OnClickListener {
    private static final GeoPoint defaultGeoPoint = new GeoPoint(51.753540, 19.452974);
    private static final String campusA = "a";
    private static final String campusB = "b";
    private static final String campusC = "c";
    private static final String campusD = "d";
    private static final String other = "e";
    private static final int ZOOM_LEVEL = 17;
    private static final int NUMBER_OF_TRIALS_GET_MY_LOCATION = 3;
    private static final long MIN_TIME_TO_UPDATE = 2000;
    private static final float MIN_DISTANCE_TO_UPDATE = 10;
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
    private int radioButtonTypeOfTravelId = -1;
    private boolean navigateFromMyLocation;
    private Building pointA;
    private Building pointB;
    private boolean isDrawerOpen = false;
    private Polyline oldRoadOverlay;
    private boolean updateLocation = true;
    private GeoPoint defaultMyLocation;
    private Location myLocation;
    private IMapController mapController;
    private MainActivityCallbacks mainActivityCallbacks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_map, container, false);

        initView();
        showMyLocation();

        if (radioButtonTypeOfTravelId != -1) {
            routing();
        } else {
            addAllMarkers();
        }

        setViewOnPoint(map, defaultGeoPoint, ZOOM_LEVEL);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (updateLocation) {
            setViewOnPoint(map, getLastViewPoint(), map.getZoomLevel());
        } else {
            setViewOnPoint(map, defaultGeoPoint, map.getZoomLevel());
        }
    }

    private void initView() {
        mainActivityCallbacks = (MainActivityCallbacks) getActivity();
        final Realm realm = Realm.getInstance(getActivity());
        buildings = realm.where(Building.class).findAll();

        defaultMyLocation = new GeoPoint(51.753663, 19.451716);
        setImageButton();

        setMapButtons();

        setHasOptionsMenu(true);
        setMap();
        setViewOnPoint(map, defaultGeoPoint, ZOOM_LEVEL);
    }

    private void setMapButtons() {
        final ImageButton imageButtonAnimateToMyLocation =
                (ImageButton) view.findViewById(R.id.imageButtonAnimateToMyLocation);
        final ImageButton imageButtonZoomIn =
                (ImageButton) view.findViewById(R.id.imageButtonZoomIn);
        final ImageButton imageButtonZoomOut =
                (ImageButton) view.findViewById(R.id.imageButtonZoomOut);

        imageButtonAnimateToMyLocation.setOnClickListener(this);
        imageButtonZoomIn.setOnClickListener(this);
        imageButtonZoomOut.setOnClickListener(this);
    }

    private void setImageButton() {
    }

    private void setMap() {
        map = (MapView) view.findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);

        final MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(getActivity(), this);
        map.getOverlays().add(0, mapEventsOverlay);
    }

    private void setViewOnPoint(MapView map, GeoPoint geoPoint, int zoomLevel) {
        mapController = map.getController();
        mapController.setZoom(zoomLevel);
        mapController.setCenter(geoPoint);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        hideMenuItems(menu, !isDrawerOpen);
        super.onPrepareOptionsMenu(menu);
    }

    private void hideMenuItems(Menu menu, boolean visible) {
        for (int i = 0; i < menu.size(); i++) {
            menu.getItem(i).setVisible(visible);
        }
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
        stopNavigation();

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

        map.invalidate();

        return true;
    }

    @SuppressWarnings("deprecation")
    private void addMarkersFromCampus(String markLetter) {
        for (Building building : buildings) {
            if (building.getIdentifier().getMarkLetter().equals(markLetter)) {
                addMarker(building, getResources().getDrawable(R.drawable.icon_marker_dot_big),
                        false, true);
            }
        }
    }

    @SuppressWarnings("deprecation")
    private void routing() {
        if (navigateFromMyLocation) {
            updateLocation = true;
            animateToMyLocation();
            navigateFromMyLocation(null);
        } else {
            final GeoPoint startPoint = new GeoPoint(pointA.getLatitude(), pointA.getLongitude());
            addMarker(pointA, getResources().getDrawable(R.drawable.icon_marker_dot_big), false, true);

            navigate(startPoint, true);
            radioButtonTypeOfTravelId = -1;
        }
    }

    private void navigateFromMyLocation(@Nullable GeoPoint startPoint) {
        if (startPoint == null) {
            startPoint = checkPermissionsAndGetMyLocation();
        }

        navigate(startPoint, false);
    }

    @SuppressWarnings("deprecation")
    private void navigate(GeoPoint startPoint, boolean showProgressDialog) {
        final ArrayList<GeoPoint> wayPoints = new ArrayList<>();

        final GeoPoint endPoint = new GeoPoint(pointB.getLatitude(), pointB.getLongitude());
        addMarker(pointB, getResources().getDrawable(R.drawable.icon_marker_dot_big), false, true);

        wayPoints.add(startPoint);
        wayPoints.add(endPoint);

        new UpdateRoadTask(getActivity(), showProgressDialog).execute(wayPoints);
    }

    @Nullable
    private GeoPoint checkPermissionsAndGetMyLocation() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            final LocationManager locationManager =
                    (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, MIN_TIME_TO_UPDATE, MIN_DISTANCE_TO_UPDATE, this);

            int trials = 0;

            while (trials != NUMBER_OF_TRIALS_GET_MY_LOCATION) {
                myLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                if (myLocation == null) {
                    myLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                }

                if (myLocation != null) {
                    return new GeoPoint(myLocation.getLatitude(), myLocation.getLongitude());
                }

                trials++;
            }
        }

        return defaultMyLocation;
    }

    @Override
    public void onLocationChanged(Location location) {
        if (updateLocation) {
            navigateFromMyLocation(new GeoPoint(location.getLatitude(), location.getLongitude()));
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onProviderDisabled(String provider) {}

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.imageButtonAnimateToMyLocation:
                animateToMyLocation();
                break;

            case R.id.imageButtonZoomIn:
                mapController.zoomIn();
                break;

            case R.id.imageButtonZoomOut:
                mapController.zoomOut();
                break;
        }
    }

    private class UpdateRoadTask extends AsyncTask<Object, Void, Road> {
        private ProgressDialog progressDialog;
        private Context context;
        private boolean showProgressDialog;

        public UpdateRoadTask(Context context, boolean showProgressDialog) {
            this.progressDialog = new ProgressDialog(context);
            this.context = context;
            this.showProgressDialog = showProgressDialog;
        }

        @Override
        protected void onPreExecute() {
            if (this.showProgressDialog) {
                this.progressDialog.setTitle(getString(R.string.routing));
                this.progressDialog.setMessage(getString(R.string.routing_message));
                this.progressDialog.setCancelable(false);
                this.progressDialog.setCanceledOnTouchOutside(false);
                this.progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                this.progressDialog.show();
            }
        }

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
//          TODO: dodaj info o drodze
//          distance = road.mLength
//          duration = road.mDuration

            final Polyline roadOverlay = RoadManager.buildRoadOverlay(road, this.context);

            if (oldRoadOverlay != null) {
                map.getOverlays().remove(oldRoadOverlay);
            }

            map.getOverlays().add(roadOverlay);
            map.invalidate();

            oldRoadOverlay = roadOverlay;

            if (this.showProgressDialog && this.progressDialog != null &&
                    this.progressDialog.isShowing()) {
                this.progressDialog.dismiss();
            }
        }
    }

    @Override
    public boolean singleTapConfirmedHelper(GeoPoint p) {
        InfoWindow.closeAllInfoWindowsOn(map);
        map.invalidate();
        return true;
    }

    private void animateToMyLocation(){
        checkPermissionsAndGetMyLocation();
        if (myLocation != null) {
            final GeoPoint myLocationGeoPoint =
                    new GeoPoint(myLocation.getLatitude(), myLocation.getLongitude());
            if (myLocationGeoPoint != defaultGeoPoint) {
                map.getController().animateTo(myLocationGeoPoint);
            }
        }
    }

    @Override
    public boolean longPressHelper(GeoPoint geoPoint) {
        return false;
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
                addMarker(building, getResources().getDrawable(R.drawable.icon_marker_dot_big),
                        false, true);
            }
        }

        for (Building chosenBuilding : chosenBuildings) {
            addMarker(chosenBuilding, getResources().getDrawable(R.drawable.icon_marker_dark),
                    false, false);
        }

        if (buildingIDs != null ) buildingIDs.clear();
    }



    private GeoPoint getLastViewPoint() {
        GeoPoint lastGeoPoint = (GeoPoint) map.getMapCenter();

        if (lastGeoPoint == null) {
            lastGeoPoint = defaultGeoPoint;
        }

        return lastGeoPoint;
    }

    public void addMarker(Building building, Drawable icon, boolean showInfoWindow, boolean defaultPosition) {
        final Marker marker = new Marker(map);
        final GeoPoint geoPoint = new GeoPoint(building.getLatitude(), building.getLongitude());
        final Identifier bIdentifier = building.getIdentifier();
        final String title = bIdentifier.getMarkLetter().toUpperCase() +
                bIdentifier.getMarkNumber() + " " + bIdentifier.getName();

        setMarkerViewOnPoint(defaultPosition, geoPoint);
        marker.setPosition(geoPoint);
        setMarkerAnchor(icon, marker);

        marker.setIcon(icon);
        marker.setTitle(title);
        marker.setSubDescription(building.getId() + "");
        marker.setInfoWindow(new CustomInfoWindow(map));
        setMarkerOnClickListener(marker);

        if (showInfoWindow) {
            marker.showInfoWindow();
            map.getController().animateTo(marker.getPosition());
        }

        map.getOverlays().add(marker);
        map.invalidate();
    }

    @SuppressWarnings("deprecation")
    private void setMarkerAnchor(Drawable icon, Marker marker) {
        if (icon == getResources().getDrawable(R.drawable.icon_marker_dark)) {
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        } else {
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER);
        }
    }

    private void setMarkerViewOnPoint(boolean defaultPosition, GeoPoint geoPoint) {
        if (defaultPosition) {
            setViewOnPoint(map, getLastViewPoint(), map.getZoomLevel());
        } else {
            setViewOnPoint(map, geoPoint, map.getZoomLevel());
        }
    }

    private void setMarkerOnClickListener(Marker marker) {
        marker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker, final MapView mapView) {
                InfoWindow.closeAllInfoWindowsOn(map);
                marker.showInfoWindow();
                setupInfoWindow(marker);
                setBubbleListener(marker);

                return true;
            }
        });
    }

    private void setBubbleListener(final Marker marker) {
        view.findViewById(R.id.bubbleInfoWindow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityCallbacks.passBuildingsIdToInfo(
                        Integer.parseInt(marker.getSubDescription()));
                mainActivityCallbacks.changeToBuildingInfoFragment("");
            }
        });
    }

    private void setupInfoWindow(final Marker marker) {
        view.findViewById(R.id.bubble_description).setVisibility(View.GONE);
        view.findViewById(R.id.bubble_subdescription).setVisibility(View.GONE);
        view.findViewById(R.id.button_goto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityCallbacks.passDataToNavigation(
                        buildings.get(Integer.parseInt(marker.getSubDescription())-1));
                mainActivityCallbacks.changeToNavigationFragment("");
            }
        });
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

    public void passData(boolean isDrawerOpen) {
        this.isDrawerOpen = isDrawerOpen;
    }

    public void stopNavigation() {
        this.updateLocation = false;
    }
}