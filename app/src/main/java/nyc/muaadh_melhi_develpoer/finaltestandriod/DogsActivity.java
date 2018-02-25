package nyc.muaadh_melhi_develpoer.finaltestandriod;

import android.content.Intent;
import android.content.SharedPreferences;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs);
        TextView breedText = findViewById(R.id.dog_breed_text);
        recyclerView = findViewById(R.id.dog_ac_re);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        Intent intent = getIntent();
        String breedName = intent.getStringExtra(BreedsActivity.BREED_NAME);
        loginSharedPref = getApplicationContext().getSharedPreferences(intent.getStringExtra("shared"), MODE_PRIVATE);
        breedText.setText(breedName);
        breedService = Common.getBreed();
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
                SharedPreferences.Editor editor = loginSharedPref.edit();
                String user = loginSharedPref.getString(LoginActivity.USER_NAME_K, "");
                editor.remove(user).commit();
                //loginSharedPref.edit().remove(LoginActivity.USER_NAME_K).commit();
                startActivity(new Intent(DogsActivity.this, LoginActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
