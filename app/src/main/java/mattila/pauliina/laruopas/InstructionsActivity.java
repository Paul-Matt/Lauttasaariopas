package mattila.pauliina.laruopas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class InstructionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        TextView instructionsTitle = findViewById(R.id.tvInstructionsTitle);
        instructionsTitle.setText(R.string.instructionsTitle);
        TextView instructionsText = findViewById(R.id.tvInstructions);
        instructionsText.setText(R.string.instructions);

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
