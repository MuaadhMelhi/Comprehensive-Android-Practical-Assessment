package nyc.muaadh_melhi_develpoer.finaltestandriod.service;

import nyc.muaadh_melhi_develpoer.finaltestandriod.model.Breed;
import nyc.muaadh_melhi_develpoer.finaltestandriod.model.BreedImages;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by c4q on 2/25/18.
 */

public interface BreedService {
    @GET("/api/breed/{breedName}/images/random")
    Call<Breed> getBreed(@Path("breedName") String name);

    @GET("/api/breed/{breedName}/images")
    Call<BreedImages> getBreedImages(@Path("breedName") String name);


}
