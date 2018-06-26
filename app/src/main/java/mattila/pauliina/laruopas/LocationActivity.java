package mattila.pauliina.laruopas;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

import mattila.pauliina.laruopas.pojo.Location;

public class LocationActivity extends Activity {

    //this helps to make sure no spelling error are accidentally made
    public static final String LOCATION = "location";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Location location = (Location) getIntent().getParcelableExtra(LOCATION);
        //remove the LaruOpas title from top
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_location);
        TextView title = findViewById(R.id.tvTitle);
        title.setText(location.getName());
        TextView description = findViewById(R.id.tvDescription);
        description.setText(location.getDescription());
        ImageView image = findViewById(R.id.ivImage);
        new DownLoadImageTask(image).execute(location.getUrl());
    }

    /*
        AsyncTask enables proper and easy use of the UI thread. This class
        allows to perform background operations and publish results on the UI
        thread without having to manipulate threads and/or handlers.
     */

    /*
        final AsyncTask<Params, Progress, Result>
            execute(Params... params)
                Executes the task with the specified parameters.
     */
    private class DownLoadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView) {
            this.imageView = imageView;
        }

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        protected Bitmap doInBackground(String... urls) {
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try {
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            } catch (Exception e) { // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }
}



