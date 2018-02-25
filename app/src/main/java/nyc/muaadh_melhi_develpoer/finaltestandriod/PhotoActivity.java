package nyc.muaadh_melhi_develpoer.finaltestandriod;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PhotoActivity extends AppCompatActivity {

    private SharedPreferences loginSharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        setUp();
    }

    private void setUp() {
        ImageView imageView = findViewById(R.id.photo_url);
        Intent intent = getIntent();
        String url = intent.getStringExtra(DogViewHolder.URL);
        Picasso.with(this).load(url).into(imageView);
        loginSharedPref = getApplicationContext().getSharedPreferences(intent.getStringExtra("shared"), MODE_PRIVATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                loginSharedPref.edit().clear().apply();
                startActivity(new Intent(PhotoActivity.this, LoginActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
