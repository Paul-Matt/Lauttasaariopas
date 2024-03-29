package mattila.pauliina.laruopas.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

public class Location implements Parcelable{
    private final LatLng coordinates;
    private final String description;
    private final String name;
    private final String url;

    public Location(LatLng coordinates, String name, String description, String url){
        this.coordinates = coordinates;
        this.description = description;
        this.name = name;
        this.url = url;
    }

    public Location(LatLng coordinates, String name, String description){
        this.coordinates = coordinates;
        this.description = description;
        this.name = name;
        url = null;
    }


    public LatLng getCoordinates() {
        return coordinates;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.coordinates, flags);
        dest.writeString(this.description);
        dest.writeString(this.name);
        dest.writeString(this.url);
    }

    protected Location(Parcel in) {
        this.coordinates = in.readParcelable(LatLng.class.getClassLoader());
        this.description = in.readString();
        this.name = in.readString();
        this.url = in.readString();
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel source) {
            return new Location(source);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
}
