package nyc.muaadh_melhi_develpoer.finaltestandriod;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nyc.muaadh_melhi_develpoer.finaltestandriod.backend.Common;
import nyc.muaadh_melhi_develpoer.finaltestandriod.model.BreedImages;
import nyc.muaadh_melhi_develpoer.finaltestandriod.service.BreedService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DogsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BreedService breedService;
    private DogAdapter dogAdapter;
    private List<String> dogImageList = new ArrayList<>();
    private SharedPreferences loginSharedPref;
    private Intent intent;
    private TextView breedText;
    private String breedName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs);
        setUp();
        checkOrientation();
        breedNetworkingCall();
    }

    private void breedNetworkingCall() {
        breedService.getBreedImages(breedName).enqueue(new Callback<BreedImages>() {
            @Override
            public void onResponse(Call<BreedImages> call, Response<BreedImages> response) {
                dogImageList = response.body().getMessage();
                dogAdapter = new DogAdapter(dogImageList);
                recyclerView.setAdapter(dogAdapter);
            }

            @Override
            public void onFailure(Call<BreedImages> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void setUp() {
        breedText = findViewById(R.id.dog_breed_text);
        recyclerView = findViewById(R.id.dog_ac_re);
        intent = getIntent();
        breedService = Common.getBreed();
        breedName = intent.getStringExtra(BreedsActivity.BREED_NAME);
        loginSharedPref = getApplicationContext().getSharedPreferences(intent.getStringExtra("shared"), MODE_PRIVATE);
        breedText.setText(breedName);
    }

    private void checkOrientation() {
        if (DogsActivity.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        }
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
                startActivity(new Intent(DogsActivity.this, LoginActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
