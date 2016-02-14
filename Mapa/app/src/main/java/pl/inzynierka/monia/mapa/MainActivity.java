package pl.inzynierka.monia.mapa;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmList;
import pl.inzynierka.monia.mapa.adapters.DrawerListAdapter;
import pl.inzynierka.monia.mapa.callbacks.MainActivityCallbacks;
import pl.inzynierka.monia.mapa.fragments.AboutFragment;
import pl.inzynierka.monia.mapa.fragments.BuildingInfoFragment;
import pl.inzynierka.monia.mapa.fragments.BuildingsListFragment;
import pl.inzynierka.monia.mapa.fragments.LessonPlanFragment;
import pl.inzynierka.monia.mapa.fragments.MapFragment;
import pl.inzynierka.monia.mapa.fragments.NavigationFragment;
import pl.inzynierka.monia.mapa.fragments.UnitInfoFragment;
import pl.inzynierka.monia.mapa.fragments.UnitsListFragment;
import pl.inzynierka.monia.mapa.models.Building;
import pl.inzynierka.monia.mapa.models.BuildingID;
import pl.inzynierka.monia.mapa.utils.DataCreator;
import pl.inzynierka.monia.mapa.utils.DrawerItem;
import pl.inzynierka.monia.mapa.utils.Keyboard;

public class MainActivity extends AppCompatActivity implements MainActivityCallbacks {

    private Realm realm;
    private MapFragment mapFragment;
    private BuildingsListFragment buildingsListFragment;
    private UnitsListFragment unitsListFragment;
    private LessonPlanFragment lessonPlanFragment;
    private AboutFragment aboutFragment;
    private BuildingInfoFragment buildingInfoFragment;
    private UnitInfoFragment unitInfoFragment;
    private NavigationFragment navigationFragment;
    private ImageView imageViewAvatar;
    private TextView textViewDrawerTitle;
    private RelativeLayout drawerHeader;
    private DrawerLayout drawerLayout;
    private RelativeLayout drawer;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;
    private ActionBar actionBar;
    private ArrayList<DrawerItem> drawerItems = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private boolean isDataCreatedDefault = false;
    private Keyboard keyboard;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

        setupActionBar();
        setupNavigationDrawer();

        checkData(false);

        mapFragment.passData(-1);
        changeToMapFragment(getString(R.string.map));
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        keyboard = new Keyboard(this);

        imageViewAvatar = (ImageView) findViewById(R.id.avatar);
        textViewDrawerTitle = (TextView) findViewById(R.id.drawerTitle);
        drawerHeader = (RelativeLayout) findViewById(R.id.drawerHeader);

        realm = Realm.getInstance(this);
        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_key),
                Context.MODE_PRIVATE);

        addData(false);

        initFragments();
    }

    private void initFragments() {
        mapFragment = new MapFragment();
        buildingsListFragment = new BuildingsListFragment();
        unitsListFragment = new UnitsListFragment();
        buildingInfoFragment = new BuildingInfoFragment();
        lessonPlanFragment = new LessonPlanFragment();
        aboutFragment = new AboutFragment();
        unitInfoFragment = new UnitInfoFragment();
        navigationFragment = new NavigationFragment();
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
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setupNavigationDrawer() {
        drawerItems.add(new DrawerItem(getString(R.string.map),
                getString(R.string.view_map_with_buildings), R.drawable.icon_map_green));
        drawerItems.add(new DrawerItem(getString(R.string.navigation),
                getString(R.string.navigation_point_to_point), R.drawable.icon_navigation2));
        drawerItems.add(new DrawerItem(getString(R.string.buildings),
                getString(R.string.building_list), R.drawable.icon_building));
        drawerItems.add(new DrawerItem(getString(R.string.units),
                getString(R.string.unit_list), R.drawable.icon_unit));
        drawerItems.add(new DrawerItem(getString(R.string.plan),
                getString(R.string.your_lesson_plan), R.drawable.icon_calendar));
        drawerItems.add(new DrawerItem(getString(R.string.about),
                getString(R.string.app_info), R.drawable.icon_about));

        imageViewAvatar.setBackground(getResources().getDrawable(R.drawable.icon_app_no_background));
        textViewDrawerTitle.setText(getString(R.string.app_name));

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

        setDrawerToggle();
        drawerLayout.setDrawerListener(drawerToggle);
    }

    private void setDrawerToggle() {
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
                keyboard.hideSoftKeyboard();
                mapFragment.passData(true);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
                mapFragment.passData(false);
            }
        };
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        realm = null;
        checkData(true);
    }

    private void checkData(boolean restartActivity) {
        if (realm == null) {
            isDataCreatedDefault = true;
            writeIsDataCreatedToSharedPref();
            addData(restartActivity);
        }
    }

    private void addData(boolean restartActivity) {
        isDataCreatedDefault =
                sharedPreferences.getBoolean(getString(R.string.is_data_created_key), false);
        checkLanguage();

        if (!isDataCreatedDefault) {
            new AddDataTask(this, restartActivity).execute();
            writeIsDataCreatedToSharedPref();
        }
    }

    private void checkLanguage() {
        final String currentLanguage = Locale.getDefault().getLanguage();
        final String savedLanguage =
                sharedPreferences.getString(getString(R.string.current_language), "");

        if (!savedLanguage.equals(currentLanguage)) {
            writeLanguageToSharedPref(currentLanguage);
            isDataCreatedDefault = false;
        }
    }

    private void writeIsDataCreatedToSharedPref() {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(getString(R.string.is_data_created_key), !isDataCreatedDefault);
        editor.apply();
    }

    private void writeLanguageToSharedPref(String currentLanguage) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.current_language), currentLanguage);
        editor.apply();
    }

    private class AddDataTask extends AsyncTask<Object, Void, Void> {
        private ProgressDialog progressDialog;
        private Context context;
        private boolean restartActivity;

        public AddDataTask(Context context, boolean restartActivity) {
            this.progressDialog = new ProgressDialog(context);
            this.context = context;
            this.restartActivity = restartActivity;
        }

        @Override
        protected void onPreExecute() {
            this.progressDialog.setTitle(getString(R.string.loading_database));
            this.progressDialog.setMessage(getString(R.string.loading_database_message));
            this.progressDialog.setCancelable(false);
            this.progressDialog.setCanceledOnTouchOutside(false);
            this.progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            this.progressDialog.show();
        }

        @Override
        protected Void doInBackground(Object... params) {
            final DataCreator dataCreator = new DataCreator(context);
            dataCreator.addData();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (this.progressDialog != null && this.progressDialog.isShowing()) {
                this.progressDialog.dismiss();
            }

            if (this.restartActivity) {
                restartActivity();
            } else {
                changeToAboutFragment("");
                changeToMapFragment("");
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void restartActivity() {
        final Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        this.finishAffinity();
        startActivity(intent);
    }

    private void selectItemFromDrawer(int position) {
        drawerList.setItemChecked(position, true);

        switch (position){
            case 0:
                changeToMapFragment(getString(R.string.map));
                break;

            case 1:
                changeToNavigationFragment(getString(R.string.navigation));
                break;

            case 2:
                changeToBuildingsListFragment(getString(R.string.buildings));
                break;

            case 3:
                changeToUnitsListFragment(getString(R.string.units));
                break;

            case 4:
                changeToLessonPlanFragment(getString(R.string.plan));
                break;

            case 5:
                changeToAboutFragment(getString(R.string.about));
                break;
        }

        drawerLayout.closeDrawer(drawer);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(drawer)) {
            drawerLayout.closeDrawer(drawer);
            return;
        }

        super.onBackPressed();
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

    public void changeToNavigationFragment(String title) {
        stopNavigation();

        if (title.isEmpty()) {
            setTitle(getString(R.string.map));
        } else {
            setTitle(title);
        }

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, navigationFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void changeToUnitsListFragment(String title) {
        stopNavigation();

        if (title.isEmpty()) {
            setTitle(getString(R.string.map));
        } else {
            setTitle(title);
        }

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, unitsListFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void changeToMapFragment(String title){
        stopNavigation();

        if (title.isEmpty()) {
            setTitle(getString(R.string.map));
            keyboard.hideSoftKeyboard();
        } else {
            setTitle(title);
        }

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, mapFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void changeToBuildingsListFragment(String title){
        stopNavigation();

        if (title.isEmpty()) {
            setTitle(getString(R.string.building_list));
        } else {
            setTitle(title);
        }

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, buildingsListFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void changeToBuildingInfoFragment(String title){
        stopNavigation();

        if (title.isEmpty()) {
            setTitle(getString(R.string.building_info));
        } else {
            setTitle(title);
        }

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, buildingInfoFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void changeToLessonPlanFragment(String title){
        stopNavigation();

        if (title.isEmpty()) {
            setTitle(getString(R.string.plan));
        } else {
            setTitle(title);
        }

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, lessonPlanFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void changeToUnitInfoFragment(String title) {
        stopNavigation();

        if (title.isEmpty()) {
            setTitle(getString(R.string.units));
        } else {
            setTitle(title);
        }

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, unitInfoFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void changeToAboutFragment(String title){
        stopNavigation();

        if (title.isEmpty()) {
            setTitle(getString(R.string.about));
        } else {
            setTitle(title);
        }

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, aboutFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void passBuildingsIdToMap(int id) {
        mapFragment.passData(id);
    }

    @Override
    public void passBuildingsIdToInfo(int id) {
        buildingInfoFragment.passData(id);
    }

    @Override
    public void passBuildingsIDs(RealmList<BuildingID> buildingIDs) {
        mapFragment.passData(buildingIDs);
    }

    @Override
    public void passDataToMap(int radioButtonTypeOfTravelId, boolean navigateFromMyLocation,
                              Building pointA, Building pointB) {
        mapFragment.passData(radioButtonTypeOfTravelId, navigateFromMyLocation, pointA, pointB);
    }

    @Override
    public void passUnitId(int id) {
        unitInfoFragment.passData(id);
    }

    @Override
    public void stopNavigation() {
        mapFragment.stopNavigation();
    }
}
