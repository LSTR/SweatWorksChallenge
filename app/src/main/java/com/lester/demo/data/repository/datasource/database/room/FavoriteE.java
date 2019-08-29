package com.lester.demo.data.repository.datasource.database.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tb_favorite")
public class FavoriteE implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private long favorite_id;

    String gender;
    UserTitleE name;
    UserLocationE location;
    UserLoginE login;
    String email;
    DateE dob;
    DateE registered;
    String phone;
    String cell;
    IdentityE id;
    UserPictureE picture;
    String nat;
    boolean isFavorite;

    public long getFavorite_id() {
        return favorite_id;
    }

    public void setFavorite_id(long favorite_id) {
        this.favorite_id = favorite_id;
    }

    public UserTitleE getName() {
        return name;
    }

    public void setName(UserTitleE name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCell() {
        return cell;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public UserLocationE getLocation() {
        return location;
    }

    public void setLocation(UserLocationE location) {
        this.location = location;
    }

    public UserLoginE getLogin() {
        return login;
    }

    public void setLogin(UserLoginE login) {
        this.login = login;
    }

    public DateE getDob() {
        return dob;
    }

    public void setDob(DateE dob) {
        this.dob = dob;
    }

    public DateE getRegistered() {
        return registered;
    }

    public void setRegistered(DateE registered) {
        this.registered = registered;
    }

    public IdentityE getId() {
        return id;
    }

    public void setId(IdentityE id) {
        this.id = id;
    }

    public UserPictureE getPicture() {
        return picture;
    }

    public void setPicture(UserPictureE picture) {
        this.picture = picture;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @Entity(tableName = "tb_title")
    public class UserTitleE implements Serializable {
        @PrimaryKey(autoGenerate = true)
        @NonNull
        private long title_id;

        String title;
        String first;
        String last;

        public long getTitle_id() {
            return title_id;
        }

        public void setTitle_id(long title_id) {
            this.title_id = title_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getLast() {
            return last;
        }

        public void setLast(String last) {
            this.last = last;
        }
    }

    @Entity(tableName = "tb_location")
    public class UserLocationE implements Serializable {
        @PrimaryKey(autoGenerate = true)
        @NonNull
        private long location_id;

        String street;
        String city;
        String state;
        String postcode;
        CoordinateE coordinates;
        TimeZoneE timezone;

        public long getLocation_id() {
            return location_id;
        }

        public void setLocation_id(long location_id) {
            this.location_id = location_id;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public CoordinateE getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(CoordinateE coordinates) {
            this.coordinates = coordinates;
        }

        public TimeZoneE getTimezone() {
            return timezone;
        }

        public void setTimezone(TimeZoneE timezone) {
            this.timezone = timezone;
        }

        @Entity(tableName = "tb_coordinate")
        public class CoordinateE implements Serializable{
            @PrimaryKey(autoGenerate = true)
            @NonNull
            private long coordinate_id;

            String latitude;
            String longitude;

            public long getCoordinate_id() {
                return coordinate_id;
            }

            public void setCoordinate_id(long coordinate_id) {
                this.coordinate_id = coordinate_id;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }
        }

        @Entity(tableName = "tb_timezone")
        public class TimeZoneE implements Serializable{
            @PrimaryKey(autoGenerate = true)
            @NonNull
            private long timezone_id;

            String offset;
            String description;

            public long getTimezone_id() {
                return timezone_id;
            }

            public void setTimezone_id(long timezone_id) {
                this.timezone_id = timezone_id;
            }

            public String getOffset() {
                return offset;
            }

            public void setOffset(String offset) {
                this.offset = offset;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }
    }

    @Entity(tableName = "tb_login")
    public class UserLoginE implements Serializable {
        @PrimaryKey(autoGenerate = true)
        @NonNull
        private long login_id;

        String uuid;
        String username;

        public long getLogin_id() {
            return login_id;
        }

        public void setLogin_id(long login_id) {
            this.login_id = login_id;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    @Entity(tableName = "tb_date")
    public class DateE implements Serializable {
        @PrimaryKey(autoGenerate = true)
        @NonNull
        private long date_id;

        String date;
        int age;

        public long getDate_id() {
            return date_id;
        }

        public void setDate_id(long date_id) {
            this.date_id = date_id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    @Entity(tableName = "tb_identity")
    public class IdentityE implements Serializable {
        @PrimaryKey(autoGenerate = true)
        @NonNull
        private long identity_id;

        String name;
        String value;

        public long getIdentity_id() {
            return identity_id;
        }

        public void setIdentity_id(long identity_id) {
            this.identity_id = identity_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    @Entity(tableName = "tb_picture")
    public class UserPictureE implements Serializable {
        @PrimaryKey(autoGenerate = true)
        @NonNull
        private long picture_id;

        String large;
        String medium;
        String thumbnail;

        public long getPicture_id() {
            return picture_id;
        }

        public void setPicture_id(long picture_id) {
            this.picture_id = picture_id;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }
    }
}