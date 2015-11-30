package pl.inzynierka.monia.mapa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    MyLocationNewOverlay mLocationOverlay;
    MapView map;
    Realm realm;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realm = Realm.getInstance(this);

        final DataCreator dataCreator = new DataCreator(this, realm);
        dataCreator.addData();

        setMap();
        showMyLocation();
        addMarker(map);
    }

    private void showMyLocation() {
        mLocationOverlay = new MyLocationNewOverlay(this, new GpsMyLocationProvider(this), map);
        mLocationOverlay.enableMyLocation();
        map.getOverlays().add(mLocationOverlay);
        map.invalidate();
    }

    private void setMap() {
        map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);
    }

    private void addMarker(MapView map) {
        final Marker startMarker = new Marker(map);
        final GeoPoint geoPoint = new GeoPoint(51.745435, 19.451648);

        final IMapController mapController = map.getController();
        mapController.setZoom(9);
        mapController.setCenter(geoPoint);

        startMarker.setPosition(geoPoint);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        startMarker.setIcon(getResources().getDrawable(R.drawable.marker_default));
        startMarker.setTitle("I am here!");
        map.getOverlays().add(startMarker);

        map.invalidate();
    }
}
