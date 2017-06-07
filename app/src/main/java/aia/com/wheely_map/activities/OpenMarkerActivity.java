package aia.com.wheely_map.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import aia.com.wheely_map.R;

public class OpenMarkerActivity extends AppCompatActivity {

    private double latitude;
    private double longitude;
    private String description;
    private Bitmap rampImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_marker);

        Bundle bundle = getIntent().getExtras();
        latitude = bundle.getDouble("lat");
        longitude = bundle.getDouble("long");
        description = bundle.getString("description");
        rampImage = null;

        ImageView imageView = (ImageView) findViewById(R.id.image_ramp);

        if (rampImage == null) {
            imageView.setImageResource(R.drawable.demo_image);
        } else {
            imageView.setImageBitmap(rampImage);
        }

        TextView textView = (TextView) findViewById(R.id.text_description);
        textView.setText(description);
    }
}
