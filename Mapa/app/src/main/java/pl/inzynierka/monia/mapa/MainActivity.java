package pl.inzynierka.monia.mapa;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import pl.inzynierka.monia.mapa.adapters.DrawerListAdapter;
import pl.inzynierka.monia.mapa.fragments.BuildingsListFragment;
import pl.inzynierka.monia.mapa.fragments.LessonPlanFragment;
import pl.inzynierka.monia.mapa.fragments.MapFragment;
import pl.inzynierka.monia.mapa.models.Building;
import pl.inzynierka.monia.mapa.utils.DrawerItem;

public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {
    private RealmResults<Building> buildings;
    private Realm realm;
    private Searcher searcher;
    private MapFragment mapFragment;
    private BuildingsListFragment buildingsListFragment;
    private LessonPlanFragment lessonPlanFragment;
    private String lastTitle = "";
    private ImageView imageViewAvatar;
    private TextView textViewDrawerTitle;
    private TextView textViewDrawerSubTitle;
    private RelativeLayout drawerHeader;

    ArrayList<DrawerItem> drawerItems = new ArrayList<>();
    private DrawerLayout drawerLayout;
    private RelativeLayout drawer;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;
    private AboutFragment aboutFragment;
    private ActionBar actionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

        setupActionBar();
        setupNavigationDrawer();
        changeToMapFragment(getString(R.string.map));
    }

    private void setupActionBar() {
        actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        if(actionBar != null){
            actionBar.setTitle(title);
        }
    }

    @SuppressWarnings("deprecation")
    private void initView() {
        setContentView(R.layout.activity_main);

        imageViewAvatar = (ImageView) findViewById(R.id.avatar);
        textViewDrawerTitle = (TextView) findViewById(R.id.drawerTitle);
        textViewDrawerSubTitle = (TextView) findViewById(R.id.drawerSubTitle);
        drawerHeader = (RelativeLayout) findViewById(R.id.drawerHeader);

        realm = Realm.getInstance(this);
        buildings = realm.where(Building.class).findAll();

        final DataCreator dataCreator = new DataCreator(this, realm);
        dataCreator.addData();

        initFragments();
    }

    private void initFragments() {
        mapFragment = new MapFragment();
        buildingsListFragment = new BuildingsListFragment();
        buildingsListFragment.passData(realm);
        lessonPlanFragment = new LessonPlanFragment();
        aboutFragment = new AboutFragment();
    }

//
//    public void onResultClick(String searchResult) {
//        final String[] buildingSplit = searchResult.split("\\n")[0].split(":");
//        final String buildingMarkLetter = buildingSplit[0].substring(0, 1);
//        final String buildingMarkNumber = buildingSplit[0].substring(1);
//        final String buildingName = buildingSplit[1];
//
//        for (Building building : buildings) {
//            final Identifier buildingIdentifier = building.getIdentifier();
//            if (buildingIdentifier.getMarkLetter().equals(buildingMarkLetter) &&
//                    buildingIdentifier.getMarkNumber() == Integer.parseInt(buildingMarkNumber) &&
//                    buildingIdentifier.getName().equals(buildingName)) {
//
//                if (toolbar.getTitle().equals(getString(R.string.map))) {
//                    mapFragment.addMarker(
//                            new GeoPoint(building.getLatitude(), building.getLongitude()));
//                }
//            }
//        }
//    }

    private void setupNavigationDrawer() {
        drawerItems.add(new DrawerItem("Mapa", "Widok mapy z budynkami", R.drawable.icon_arrow_left));
        drawerItems.add(new DrawerItem("Budynki", "Lista budynków", R.drawable.icon_arrow_left));
        drawerItems.add(new DrawerItem("Plan", "Twój plan zajęć", R.drawable.icon_arrow_left));

        drawerItems.add(new DrawerItem("O aplikacji", "Informacje o tej aplikacji", R.drawable.icon_arrow_left));

        imageViewAvatar.setImageResource(R.drawable.icon_close);
        textViewDrawerTitle.setText("SUPER APPKA!!1!!!one!11one");
        textViewDrawerSubTitle.setText("i nie straszne Ci kręte ścieżki!");

        drawerLayout = (DrawerLayout) findViewById(R.id.main);
        drawer = (RelativeLayout) findViewById(R.id.drawer);
        drawerList = (ListView) findViewById(R.id.drawerList);
        final DrawerListAdapter adapter = new DrawerListAdapter(this, drawerItems);
        drawerList.setAdapter(adapter);

        setDrawerListeners();
    }

    private void setDrawerListeners() {
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItemFromDrawer(position);
            }
        });

        drawerHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToMapFragment(getString(R.string.map));
                drawerLayout.closeDrawer(drawer);
            }
        });

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);
    }

    private void selectItemFromDrawer(int position) {
        drawerList.setItemChecked(position, true);

        switch (position){
            case 0:
                changeToMapFragment(getString(R.string.map));
                drawerLayout.closeDrawer(drawer);
                break;

            case 1:
                changeToBuildingsListFragment(getString(R.string.buildings));
                drawerLayout.closeDrawer(drawer);
                break;

            case 2:
                changeToLessonPlanFragment(getString(R.string.plan));
                drawerLayout.closeDrawer(drawer);
                break;

            case 3:
                changeToAboutFragment(getString(R.string.about));
                drawerLayout.closeDrawer(drawer);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    private void changeToMapFragment(String title){
        setTitle(title);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, mapFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void changeToBuildingsListFragment(String title){
        setTitle(title);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, buildingsListFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void changeToLessonPlanFragment(String title){
        setTitle(title);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, lessonPlanFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void changeToAboutFragment(String title){
        setTitle(title);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, aboutFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}
