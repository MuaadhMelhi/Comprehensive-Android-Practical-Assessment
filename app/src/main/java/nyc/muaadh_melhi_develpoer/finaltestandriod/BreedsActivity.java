package nyc.muaadh_melhi_develpoer.finaltestandriod;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import nyc.muaadh_melhi_develpoer.finaltestandriod.backend.Common;
import nyc.muaadh_melhi_develpoer.finaltestandriod.model.Breed;
import nyc.muaadh_melhi_develpoer.finaltestandriod.service.BreedService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BreedsActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView greetingText;
    private BreedService breedService;
    private String[] breedName = {"terrier", "spaniel", "retriever", "poodle"};
    private ImageView terrierIv, spanielIv, retrieverIv, poodleIv;
    private CardView terrierCardV, spanielCardV, retrieverCardV, poodleCardV;

    private SharedPreferences loginSharedPref;
    public static final String BREED_NAME = "breedName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeds);
        setUpView();
        greetingUser();
        for (int i = 0; i < breedName.length; i++) {
            requestBreedImage(breedName[i]);
        }
    }

    private void greetingUser() {
        Intent intent = getIntent();
        loginSharedPref = getApplicationContext().getSharedPreferences(intent.getStringExtra("shared"), MODE_PRIVATE);
        String userName = loginSharedPref.getString("username", "");
        if (userName.length() > 0) {
            StringBuilder s = new StringBuilder(getString(R.string.greetingQuestion));
            s.append(userName).append(" ?");
            greetingText.setText(s);
        } else {
            startActivity(new Intent(BreedsActivity.this, LoginActivity.class));
        }
    }

    private void setUpView() {
        greetingText = findViewById(R.id.welcome_user);
        terrierIv = findViewById(R.id.image_terrier);
        spanielIv = findViewById(R.id.image_spaniel);
        retrieverIv = findViewById(R.id.image_retriever);
        poodleIv = findViewById(R.id.image_poodle);
        terrierCardV = findViewById(R.id.card_terrier);
        spanielCardV = findViewById(R.id.card_spaniel);
        retrieverCardV = findViewById(R.id.card_retriever);
        poodleCardV = findViewById(R.id.card_poodle);
        breedService = Common.getBreed();
        terrierCardV.setOnClickListener(this);
        spanielCardV.setOnClickListener(this);
        retrieverCardV.setOnClickListener(this);
        poodleCardV.setOnClickListener(this);

    }

    private void requestBreedImage(final String breedName) {
        breedService.getBreed(breedName).enqueue(new Callback<Breed>() {
            @Override
            public void onResponse(Call<Breed> call, Response<Breed> response) {
                String url = response.body().getMessage();
                ImageView imageView = terrierIv;
                switch (breedName) {
                    case "terrier":
                        imageView = terrierIv;
                        break;
                    case "spaniel":
                        imageView = spanielIv;
                        break;
                    case "retriever":
                        imageView = retrieverIv;
                        break;
                    case "poodle":
                        imageView = poodleIv;
                        break;
                }
                Picasso.with(getApplicationContext()).load(url).into(imageView);
            }

            @Override
            public void onFailure(Call<Breed> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onClick(View v) {
        String breedName = "";
        switch (v.getId()) {
            case R.id.card_terrier:
                breedName = "terrier";
                break;
            case R.id.card_spaniel:
                breedName = "spaniel";
                break;
            case R.id.card_retriever:
                breedName = "retriever";
                break;
            case R.id.card_poodle:
                breedName = "poodle";
                break;
        }
        Intent intent = new Intent(this, DogsActivity.class);
        intent.putExtra(BREED_NAME, breedName);
        startActivity(intent);

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
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
