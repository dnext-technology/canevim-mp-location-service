package com.misafirperver.location.repository;

import static com.misafirperver.location.constant.LocationConstants.LOCATION_JDBC_TEMPLATE;
import static com.misafirperver.location.repository.query.LocationSqlQueries.SQL_FETCH_CITIES;
import static com.misafirperver.location.repository.query.LocationSqlQueries.SQL_FETCH_DISTRICTS;
import static com.misafirperver.location.repository.query.LocationSqlQueries.SQL_FETCH_NEIGHBORHOODS_WITH_TOWN;
import static com.misafirperver.location.repository.query.LocationSqlQueries.SQL_FETCH_TOWNS;

import java.util.List;

import com.misafirperver.location.constant.LocationTypes;
import com.misafirperver.location.model.Location;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Slf4j
@Repository
public class LocationRepository {

    private final JdbcTemplate locationJdbcTemplate;

    public LocationRepository(@Qualifier(LOCATION_JDBC_TEMPLATE) JdbcTemplate jdbcTemplate) {
        this.locationJdbcTemplate = jdbcTemplate;
    }

    public List<Location> fetchCities() {
        List<Location> cities = null;
        try {
            cities = locationJdbcTemplate.query(SQL_FETCH_CITIES, (rs, rowNum) -> new Location(
                    LocationTypes.CITY.label(),
                    rs.getString("city")
            ));
        } catch (Exception exception) {
            log.error("An exception occurred while fetching cities");
        }

        //TODO Check list
        return cities;
    }

    public List<Location> fetchClickableDistricts(String city) {
        List<Location> districts = null;
        try {

            districts = locationJdbcTemplate.query(SQL_FETCH_DISTRICTS, (rs, rowNum) -> new Location(
                    LocationTypes.DISTRICT.label(),
                    rs.getString("district")

            ), new Object[] {city});
        } catch (Exception exception) {
            log.error("An exception occurred while fetching districts");
        }

        //TODO Check list
        return districts;
    }

    public List<Location> fetchClickableTowns(String city, String district) {
        List<Location> towns = null;
        try {
            towns = locationJdbcTemplate.query(SQL_FETCH_TOWNS, (rs, rowNum) -> new Location(
                    LocationTypes.TOWN.label(),
                    rs.getString("town")

            ), new Object[] {city, district});

        } catch (Exception exception) {
            log.error("An exception occurred while fetching towns");
        }

        return towns;
    }

    public List<Location> fetchClickableNeighborhoods( String city, String district, String town) {
        List<Location> neighborhoods = null;
        try {
            neighborhoods = locationJdbcTemplate.query(SQL_FETCH_NEIGHBORHOODS_WITH_TOWN, (rs, rowNum) -> new Location(
                            LocationTypes.NEIGHBORHOOD.label(),
                            rs.getString("neighborhood")

                    ), new Object[] {city, district, town}
            );

        } catch (Exception exception) {
            log.error("An exception occurred while fetching neighborhoods");
        }


        return neighborhoods;
    }

}
