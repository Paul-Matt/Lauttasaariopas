package mattila.pauliina.laruopas;

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

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowLongClickListener {

    private static final LatLng lauttasaari = new LatLng(60.158611, 24.875);
    private static final LatLng puun_rakkaus = new LatLng(60.145772, 24.873872);
    private static final LatLng hiidenkirnu = new LatLng(60.152670, 24.855661);
    private static final LatLng siirtolohkare_lun = new LatLng(60.155222, 24.854890);
    private static final LatLng siirtolohkare_laki = new LatLng(60.158852, 24.869199);
    private static final LatLng muinaisrantakivikko = new LatLng(60.163837, 24.883227);
    private static final LatLng sisahattu = new LatLng(60.143370, 24.88995);

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

    private void addMarkersToMap() {
        mMap.addMarker(new MarkerOptions()
                .position(puun_rakkaus)
                .title("Puun rakkaus-veistos"));

        mMap.addMarker(new MarkerOptions()
                .position(hiidenkirnu)
                .title("Hiidenkirnu, Länsiulapanniemi"));

        mMap.addMarker(new MarkerOptions()
                .position(siirtolohkare_lun)
                .title("Siirtolohkare, Länsiulapanniemi"));

        mMap.addMarker(new MarkerOptions()
                .position(siirtolohkare_laki)
                .title("Siirtolohkare, Lauttasaaren kirkko"));

        mMap.addMarker(new MarkerOptions()
                .position(muinaisrantakivikko)
                .title("Muinaisrantakivikko, Kotkavuori"));

        mMap.addMarker(new MarkerOptions()
                .position(sisahattu)
                .title("Sisä-Hattu -saari"));

    }

    @Override
    public void onInfoWindowLongClick(Marker marker) {
        Toast.makeText(this, "Infowindow long click", Toast.LENGTH_SHORT).show();
    }
}
