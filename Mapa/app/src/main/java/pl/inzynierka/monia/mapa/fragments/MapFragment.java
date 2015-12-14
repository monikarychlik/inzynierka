package pl.inzynierka.monia.mapa.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import pl.inzynierka.monia.mapa.R;

public class MapFragment extends Fragment {
    private View view;
    private static final int zoomLevel = 11;
    private MapView map;

    public MapFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_map, container, false);

        initView();

        return view;
    }

    private void initView() {
        setMap();
        setViewOnPoint(map, new GeoPoint(51.745435, 19.451648), zoomLevel);

        //showMyLocation();
        //addMarker(map, new GeoPoint(51.745435, 19.451648));
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
    public void addMarker(GeoPoint geoPoint) {
        final Marker startMarker = new Marker(map);

        setViewOnPoint(map, geoPoint, 13);

        startMarker.setPosition(geoPoint);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        startMarker.setIcon(getResources().getDrawable(R.drawable.marker_default));
        startMarker.setTitle("I am here!");
        map.getOverlays().add(startMarker);

        map.invalidate();
    }
}
