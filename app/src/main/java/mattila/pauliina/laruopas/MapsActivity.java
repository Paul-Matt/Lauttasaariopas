package mattila.pauliina.laruopas;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import mattila.pauliina.laruopas.pojo.Location;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowLongClickListener {

    private static final Location[] locations = new Location[]{
            new Location(new LatLng(60.1453035, 24.8722835), "Puun rakkaus -veistos", "Sanna Karlsson-Sutisnan Puun rakkaus-veistos puunrungossa oli osa LARU15 Human Era – Nordic Environmental -taidenäyttelyä Lauttasaaren rannoilla syksyllä 2015. "),
            new Location(new LatLng(60.152670, 24.855661), "Hiidenkirnu, Länsiulapanniemi", "Sijainti: Lauttasaari, Länsiulapanniemi, lounainen rantakallio, kuusen alla. Koko: halkaisija 0,6 m, syvyys 0,8 m. "),
            new Location(new LatLng(60.155222, 24.854890), "Siirtolohkare, Länsiulapanniemi", "Rapakivigraniittinen siirtolohkare. Sijainti: Lauttasaari, Länsiulapanniemi. Koko: 2,5 m x 4 m x 5 m."),
            new Location(new LatLng(60.158852, 24.869199), "Siirtolohkare, Lauttasaaren kirkko", "Rapakivigraniittinen siirtolohkare. Sijainti: Lauttasaaren kirkon piha. Koko: 2,5 m x 4,5 m x 5 m. Lohkaretta siirretty kirkon rakentamisen tieltä. Ei luonnontilaisella paikalla vaan vesialtaassa."),
            new Location(new LatLng(60.163837, 24.883227), "Muinaisrantakivikko, Kotkavuori", "Muinainen Litorina-meren rantakivikko tasolla noin 20 mpy. Kivet pyöristyneitä, kooltaan 10-60 cm. Sijainti: Lauttasaari, Kotkavuoren pohjoisrinne. Koko: 10-40 m x 280 m. Kiviä on jonkin verran käytetty huviloiden kivijalkoihin ja terasseihin."),
            new Location(new LatLng(60.143370, 24.88995), "Sisä-Hattu -saari", "Pieneen saareen Lauttasaaren eteläpuolella voi kävellä kuivin jaloin matalan veden aikaan, tai kahlata vedenalaista kannasta pitkin."),
    } ;

    //centers the camera
    private static final LatLng lauttasaari = new LatLng(60.158611, 24.875);


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

        // Set listener for info window long click event.
        mMap.setOnInfoWindowLongClickListener(this);
    }

    // Adds marker based on location
    private void addMarkersToMap() {
        for (Location location : locations){
            Marker marker =  mMap.addMarker(new MarkerOptions()
                    .position(location.getCoordinates())
                    .title(location.getName()));
            marker.setTag(location);

        }
    }

    // Opens a dialog activity with extra info on infoWindowLongClick
    @Override
    public void onInfoWindowLongClick(Marker marker) {
        Location location = (Location) marker.getTag();
        //Toast.makeText(this, "Infowindow long click", Toast.LENGTH_SHORT).show();
        Intent goToLocation = new Intent(this, LocationActivity.class);
        goToLocation.putExtra(LocationActivity.LOCATION, location);
        startActivity(goToLocation);
    }
}
