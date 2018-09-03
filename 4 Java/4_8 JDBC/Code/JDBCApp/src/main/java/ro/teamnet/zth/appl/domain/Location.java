package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

@Table(name="LOCATIONS")
public class Location {
    @Id(name="LOCATION_ID")
    private long id;

    @Column(name="STREET_ADDRESS")
    private String streetAddress;

    @Column(name="POSTAL_CODE")
    private String postalCode;

    @Column(name="CITY")
    private String city;

    @Column(name="STATE_PROVINCE")
    private String stateProvince;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(!(obj instanceof Location)){
            return false;
        }
        Location location = (Location) obj;
        if(id != location.id)
            return false;
        if(location.stateProvince == null || !stateProvince.equals(location.stateProvince))
            return false;
        if(location.streetAddress == null || !streetAddress.equals(location.streetAddress))
            return false;
        if(location.city == null || !city.equals(location.city))
            return false;
        if(location.postalCode == null || !postalCode.equals(location.postalCode))
            return false;
        return true;
    }

    @Override
    public String toString() {
        String loc = new String();
        loc = "ID: " + this.id + "\n" + "Street Address: " + this.streetAddress + "\n Postal Code: " + this.postalCode +
                "\n City: " + this.city + "\n State Province: " + this.stateProvince;
        return loc;
    }
}
