package nyc.muaadh_melhi_develpoer.finaltestandriod.backend;

import nyc.muaadh_melhi_develpoer.finaltestandriod.service.BreedService;

/**
 * Created by c4q on 2/25/18.
 */

public class Common {
    //https://dog.ceo/api/breed/terrier/images/random
    private static final String BASE_URL = "https://dog.ceo";

    public static BreedService getBreed() {
        return RetrofitClient.getRetrofitClient(BASE_URL).create(BreedService.class);
    }

}
