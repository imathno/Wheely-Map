package aia.com.wheely_map.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import aia.com.wheely_map.R;
import aia.com.wheely_map.map.Ramp;

public class OpenMarkerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_marker);

        Bundle bundle = getIntent().getExtras();
        String description = (String)bundle.get("description");

        ImageView imageView = (ImageView) findViewById(R.id.image_ramp);

        if (description.contains("attack")) {
            imageView.setBackgroundResource(R.drawable.ramp1);
        } else if (description.contains("hole")) {
            imageView.setBackgroundResource(R.drawable.ramp2);
        } else {
            imageView.setBackgroundResource(R.drawable.ramp3);
        }

        TextView textView = (TextView) findViewById(R.id.text_description);
        textView.setText(description);
    }
}