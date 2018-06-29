package mattila.pauliina.laruopas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        TextView infoTitle = findViewById(R.id.tvInfoTitle);
        infoTitle.setText(R.string.tlTiedot);
        TextView infoFeedback = findViewById(R.id.tvInfoFeedback);
        infoFeedback.setText(R.string.tvFeedback);
        TextView copyRight = findViewById(R.id.tvInfoCopyright);
        copyRight.setText(R.string.tvCopyright);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        myToolbar.setTitle(getString(R.string.app_name));
        myToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);

        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
