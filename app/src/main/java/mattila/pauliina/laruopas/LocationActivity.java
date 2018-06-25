package mattila.pauliina.laruopas;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import mattila.pauliina.laruopas.pojo.Location;

public class LocationActivity extends Activity {

    //this helps to make sure no spelling error are accidentally made
    public static final String LOCATION = "location";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Location location = (Location) getIntent().getParcelableExtra(LOCATION);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_location);
        TextView title = findViewById(R.id.tvTitle);
        title.setText(location.getName());
        TextView description = findViewById(R.id.tvDescription);
        description.setText(location.getDescription());
    }
}
