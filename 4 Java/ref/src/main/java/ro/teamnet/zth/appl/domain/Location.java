package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

@Table (name="LOCATIONS")

public class Location {
    @Id(name="location_id")
        private long id;
    @Id(name="street_address")
        private String streetAddress;
    @Id(name="postal_code")
        private String postalCode;
    @Id(name="city")
        private String city;
    @Id(name="state_province")
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;

        Location location = (Location) o;

        if (getId() != location.getId()) return false;
        if (getStreetAddress() != null ? !getStreetAddress().equals(location.getStreetAddress()) : location.getStreetAddress() != null)
            return false;
        if (getPostalCode() != null ? !getPostalCode().equals(location.getPostalCode()) : location.getPostalCode() != null)
            return false;
        if (getCity() != null ? !getCity().equals(location.getCity()) : location.getCity() != null) return false;
        return getStateProvince() != null ? getStateProvince().equals(location.getStateProvince()) : location.getStateProvince() == null;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", streetAddress='" + streetAddress + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", stateProvince='" + stateProvince + '\'' +
                '}';
    }
}
