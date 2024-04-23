package com.example.travel_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class PopularPlacesFragment extends Fragment {

    private List<DataModelPopularPlaces> placeList;
    private RecyclerView PopularPlaceRecycleView;
    private PopularPlaceAdapter popularPlaceAdapter;

    public PopularPlacesFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_popular_places, container, false);
        placeList = generatePlace();

        PopularPlaceRecycleView = rootView.findViewById(R.id.popularPlacesRecycleView);
        PopularPlaceRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

        popularPlaceAdapter = new PopularPlaceAdapter(placeList);
        PopularPlaceRecycleView.setAdapter(popularPlaceAdapter);

        return rootView;
    }

    private List<DataModelPopularPlaces> generatePlace(){
        List<DataModelPopularPlaces> placeModel = new ArrayList<>();
        placeModel.add(new DataModelPopularPlaces(R.drawable.red_fort_delhi,R.string.red_fort,R.string.delhi));
        placeModel.add(new DataModelPopularPlaces(R.drawable.golden_temple_amrister,R.string.golden_temple,R.string.amrister));
        placeModel.add(new DataModelPopularPlaces(R.drawable.mysore_palace_mysore,R.string.mysore_palace,R.string.mysore));
        placeModel.add(new DataModelPopularPlaces(R.drawable.ooty_tamil_nadu,R.string.ooty,R.string.tamil_nadu));
        placeModel.add(new DataModelPopularPlaces(R.drawable.india_gate_delhi,R.string.india_gate,R.string.delhi));
        placeModel.add(new DataModelPopularPlaces(R.drawable.aguada_fort_goa,R.string.aguada_fort,R.string.goa));
        placeModel.add(new DataModelPopularPlaces(R.drawable.gate_way_of_india_mumbai,R.string.gate_way,R.string.mumbai));
        placeModel.add(new DataModelPopularPlaces(R.drawable.meenakshi_temple_madurai,R.string.meenakshi_temple,R.string.madurai));
        placeModel.add(new DataModelPopularPlaces(R.drawable.statue_of_unity_vadodara,R.string.unity,R.string.vadodara));
        placeModel.add(new DataModelPopularPlaces(R.drawable.baga_beach_goa,R.string.baga_beach,R.string.goa));
        placeModel.add(new DataModelPopularPlaces(R.drawable.promenada_beach_puducherry,R.string.promenada_beach,R.string.puducherry));
        placeModel.add(new DataModelPopularPlaces(R.drawable.mudumalai_national_park_ooty,R.string.mudumalai,R.string.ooty));
        placeModel.add(new DataModelPopularPlaces(R.drawable.ellora_caves_aurangabad,R.string.ellora,R.string.aurangabad));
        placeModel.add(new DataModelPopularPlaces(R.drawable.kodaikanal_tamil_nadu,R.string.kodaikanal,R.string.tamil_nadu));
        placeModel.add(new DataModelPopularPlaces(R.drawable.charminar_hydrabad,R.string.charminar,R.string.hyderabad));
        placeModel.add(new DataModelPopularPlaces(R.drawable.nageshwara_jyotirlinga_dwaraka,R.string.nageshwar,R.string.dwarka));
        placeModel.add(new DataModelPopularPlaces(R.drawable.dudhsagar_waterfalls_goa,R.string.dudhsagar,R.string.goa));
        placeModel.add(new DataModelPopularPlaces(R.drawable.nubra_valley_leh,R.string.nubra,R.string.leh));
        placeModel.add(new DataModelPopularPlaces(R.drawable.lotus_temple_delhi,R.string.lotus_temple,R.string.delhi));
        placeModel.add(new DataModelPopularPlaces(R.drawable.victoria_memorial_kolkata,R.string.victoria,R.string.kolkata));
        placeModel.add(new DataModelPopularPlaces(R.drawable.rann_of_kutch_rannofkutch,R.string.rann_of_kutch,R.string.rann_of_kutch_city));
        placeModel.add(new DataModelPopularPlaces(R.drawable.jallianwala_bagh_amrister,R.string.jallianwala_bagh,R.string.amrister));
        placeModel.add(new DataModelPopularPlaces(R.drawable.varkala_beach_kerala,R.string.varkala,R.string.kerala));
        placeModel.add(new DataModelPopularPlaces(R.drawable.rameshwaram_tamil_nadu,R.string.rameshwaram,R.string.tamil_nadu));
        placeModel.add(new DataModelPopularPlaces(R.drawable.chikmagalur_karnataka,R.string.chikmagalur,R.string.karnataka));
        placeModel.add(new DataModelPopularPlaces(R.drawable.munnar_kerala,R.string.munnar,R.string.kerala));
        placeModel.add(new DataModelPopularPlaces(R.drawable.darjeeling_west_bengal,R.string.darjeeling,R.string.west_bengal));
        placeModel.add(new DataModelPopularPlaces(R.drawable.varanasi_uttar_pradesh,R.string.varanasi,R.string.uttar_pradesh));
        placeModel.add(new DataModelPopularPlaces(R.drawable.coorg_karnataga,R.string.coorg,R.string.karnataka));
        placeModel.add(new DataModelPopularPlaces(R.drawable.qutab_minar_delhi,R.string.qutab_minar,R.string.delhi));

        return placeModel;
    }
}