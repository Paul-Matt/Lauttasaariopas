package mattila.pauliina.laruopas;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import mattila.pauliina.laruopas.pojo.Location;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private static final Location[] locations = new Location[]{
            new Location(new LatLng(60.1453035, 24.8722835), "Puun rakkaus -veistos", "Sanna Karlsson-Sutisnan Puun rakkaus-veistos puunrungossa oli osa LARU15 Human Era – Nordic Environmental -taidenäyttelyä Lauttasaaren rannoilla syksyllä 2015. ", "http://zebnet.ddns.net/laruopas/21092015956.jpg"),
            new Location(new LatLng(60.152670, 24.855661), "Hiidenkirnu, Länsiulapanniemi", "Sijainti: Lauttasaari, Länsiulapanniemi, lounainen rantakallio, kuusen alla. Koko: halkaisija 0,6 m, syvyys 0,8 m. ", "http://kartta.hel.fi/Applications/ltj/html/linkitetyt_ltj/Kuvat/Geologiset_kohteet/12_003_Antti_Salla_2015.JPG"),
            new Location(new LatLng(60.155222, 24.854890), "Siirtolohkare, Länsiulapanniemi", "Rapakivigraniittinen siirtolohkare. Sijainti: Lauttasaari, Länsiulapanniemi. Koko: 2,5 m x 4 m x 5 m.", "http://kartta.hel.fi/Applications/ltj/html/linkitetyt_ltj/Kuvat/Geologiset_kohteet/22_003_Antti_Salla_2015.JPG"),
            new Location(new LatLng(60.158852, 24.869199), "Siirtolohkare, Lauttasaaren kirkko", "Rapakivigraniittinen siirtolohkare. Sijainti: Lauttasaaren kirkon piha. Koko: 2,5 m x 4,5 m x 5 m. Lohkaretta siirretty kirkon rakentamisen tieltä. Ei luonnontilaisella paikalla vaan vesialtaassa.", "http://kartta.hel.fi/Applications/ltj/html/linkitetyt_ltj/Kuvat/Geologiset_kohteet/22_030_Antti_Salla_2015.JPG"),
            new Location(new LatLng(60.163837, 24.883227), "Muinaisrantakivikko, Kotkavuori", "Muinainen Litorina-meren rantakivikko tasolla noin 20 mpy. Kivet pyöristyneitä, kooltaan 10-60 cm. Sijainti: Lauttasaari, Kotkavuoren pohjoisrinne. Koko: 10-40 m x 280 m. Kiviä on jonkin verran käytetty huviloiden kivijalkoihin ja terasseihin.", "http://kartta.hel.fi/Applications/ltj/html/linkitetyt_ltj/Kuvat/Geologiset_kohteet/21_009_Antti_Salla_2015.JPG"),
            new Location(new LatLng(60.143370, 24.88995), "Sisä-Hattu -saari", "Pieneen saareen Lauttasaaren eteläpuolella voi kävellä kuivin jaloin matalan veden aikaan, tai kahlata vedenalaista kannasta pitkin.", "http://zebnet.ddns.net/laruopas/10072013225.jpg"),
    };

    //centers the camera
    private static final LatLng lauttasaari = new LatLng(60.158611, 24.875);
    /**
     * URL to get JSON location data
     */
    private static final String BASE_URL = "http://www.hel.fi/palvelukarttaws/rest/v4/unit/";

    /**
     * Tag for the log messages
     */
    private static final String TAG = MapsActivity.class.getSimpleName();


    private GoogleMap mMap;

    /**
     * Keeps track of the selected marker.
     */
    private Marker mSelectedMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        UserAsyncTask task = new UserAsyncTask();
        task.execute("40701", "40098", "40677");
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker in Lauttasaari, Helsinki.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add lots of markers to the map.
        addMarkersToMap();

        // Zoom in on Lauttasaari
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lauttasaari, 13));

        // Set listener for info window click event.
        mMap.setOnInfoWindowClickListener(this);
    }

    // Adds marker based on location
    private void addMarkersToMap() {
        for (Location location : locations) {
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(location.getCoordinates())
                    .title(location.getName()));
            marker.setTag(location);

        }
    }

    // Opens a dialog activity with extra info on infoWindowLongClick
    @Override
    public void onInfoWindowClick(Marker marker) {
        Location location = (Location) marker.getTag();
        //Toast.makeText(this, "Infowindow long click", Toast.LENGTH_SHORT).show();
        Intent goToLocation = new Intent(this, LocationActivity.class);
        goToLocation.putExtra(LocationActivity.LOCATION, location);
        startActivity(goToLocation);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.mnOhjeet:
                //Toast.makeText(this, "Ohjeet", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(this, InstructionsActivity.class);
                startActivityForResult(myIntent, 0);
                return true;
            case R.id.mnTiedot:
                //Toast.makeText(this, "Tiedot", Toast.LENGTH_SHORT).show();
                myIntent = new Intent(this, InfoActivity.class);
                startActivityForResult(myIntent, 1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class UserAsyncTask extends AsyncTask<String, Location, Location> {
        HttpHandler handler = new HttpHandler();


        @Override
        protected Location doInBackground(String... ids) {
            for(String id : ids){
                URL url = handler.createUrl(BASE_URL + id);

                // Perform HTTP request to the URL and receive a JSON response back
                String jsonResponse = "";
                try {
                    jsonResponse = handler.makeHttpRequest(url);
                } catch (IOException e) {
                    Log.e(TAG, "Problem making the HTTP request.", e);
                }

                // Extract relevant fields from the JSON response and return the Location object as the result fo the {@link UserAsyncTask}
                Location location =  handler.extractFeatureFromJson(jsonResponse);
                publishProgress(location);
            }
            return null;
        }

        //called at any point from doInBackground, every time doInBackground retrieves a location onProgressUpdate is called
        @Override
        protected void onProgressUpdate(Location... location) {
            if (location[0] == null) {
                Toast.makeText(MapsActivity.this, "Couldn't get JSON from server. Check LogCat for possible errors!", Toast.LENGTH_LONG).show();
                return;
            }
            if(mMap != null){
                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(location[0].getCoordinates())
                        .title(location[0].getName()));
                marker.setTag(location[0]);
            }
        }

        /**
         * Update the screen with the given location (which was the result of the
         * UserAsyncTask.
         */
        //called whenever what is done on the background is completed
        @Override
        protected void onPostExecute(Location location) {

        }

        /**
         * Update the screen to display information from the given Location.

        private void updateUi(Location location) {

            TextView name = (TextView) findViewById(R.id.tv_rest_name);
            name.setText(location.getName());

            TextView description = (TextView) findViewById(R.id.tv_rest_description);
            description.setText(location.getDescription());

        } */
    }

}
