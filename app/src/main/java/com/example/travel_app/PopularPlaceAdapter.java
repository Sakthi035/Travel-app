package com.example.travel_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PopularPlaceAdapter extends RecyclerView.Adapter<PopularPlaceAdapter.PlaceViewHolder> {

    private List<DataModelPopularPlaces> placeList;

    public PopularPlaceAdapter(List<DataModelPopularPlaces> placeList) {
        this.placeList = placeList;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View palceView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popularplaces,parent,false);
        return new PlaceViewHolder(palceView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {

        DataModelPopularPlaces dataitem = placeList.get(position);
        holder.PlaceImage.setImageResource(dataitem.getPlaceImage());
        holder.PlaceName.setText(dataitem.getPlaceName());
        holder.PlaceCity.setText(dataitem.getPlaceCity());



    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }

    public static class PlaceViewHolder extends RecyclerView.ViewHolder{
        ImageView PlaceImage;
        TextView PlaceName;
        TextView PlaceCity;


        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            PlaceImage = itemView.findViewById(R.id.popularPlaceImage);
            PlaceName = itemView.findViewById(R.id.PopularPlaceName);
            PlaceCity = itemView.findViewById(R.id.PopularPlaceCity);

        }
    }
}
