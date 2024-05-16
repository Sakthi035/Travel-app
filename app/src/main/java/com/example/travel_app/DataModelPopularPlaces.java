package com.example.travel_app;

public class DataModelPopularPlaces {
    private int placeImage;
    private int placeName;
    private int placeCity;

    public int getPlaceImage() {
        return placeImage;
    }

    public int getPlaceName() {
        return placeName;
    }

    public int getPlaceCity() {
        return placeCity;
    }

    public DataModelPopularPlaces(int placeImage, int placeName, int placeCity) {
        this.placeImage = placeImage;
        this.placeName = placeName;
        this.placeCity = placeCity;
    }


}
