package com.zorgundostu.location.repository;

import static com.zorgundostu.location.constant.LocationConstants.LOCATION_JDBC_TEMPLATE;
import static com.zorgundostu.location.repository.query.LocationSqlQueries.SQL_FETCH_CITIES;
import static com.zorgundostu.location.repository.query.LocationSqlQueries.SQL_FETCH_DISTRICTS;
import static com.zorgundostu.location.repository.query.LocationSqlQueries.SQL_FETCH_NEIGHBORHOODS_WITH_TOWN;
import static com.zorgundostu.location.repository.query.LocationSqlQueries.SQL_FETCH_TOWNS;

import java.util.List;

import com.zorgundostu.location.constant.LocationTypes;
import com.zorgundostu.location.model.Location;
import lombok.extern.slf4j.Slf4j;
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

    public List<Location> fetchClickableDistricts(String city) throws Exception {
        List<Location> districts;
        try {
            districts = locationJdbcTemplate.query(SQL_FETCH_DISTRICTS, (rs, rowNum) -> new Location(
                    LocationTypes.DISTRICT.label(),
                    rs.getString("district")

            ), new Object[]{"%" + city.toUpperCase() + "%"});
        } catch (Exception exception) {
            log.error("An exception occurred while fetching districts");
            throw new RuntimeException("An exception occurred while fetching districts", exception);
        }
        //TODO Check list
        return districts;
    }

    public List<Location> fetchClickableTowns(String city, String district) {
        List<Location> towns;
        try {
            towns = locationJdbcTemplate.query(SQL_FETCH_TOWNS, (rs, rowNum) -> new Location(
                    LocationTypes.TOWN.label(),
                    rs.getString("town")

            ), new Object[]{"%" + city.toUpperCase() + "%", "%" + district.toUpperCase() + "%"});

        } catch (Exception exception) {
            log.error("An exception occurred while fetching towns");
            throw new RuntimeException("An exception occurred while fetching towns", exception);
        }
        return towns;
    }

    public List<Location> fetchClickableNeighborhoods(String city, String district, String town) {
        List<Location> neighborhoods;
        try {
            neighborhoods = locationJdbcTemplate.query(SQL_FETCH_NEIGHBORHOODS_WITH_TOWN, (rs, rowNum) -> new Location(
                            LocationTypes.NEIGHBORHOOD.label(),
                            rs.getString("neighborhood")

                    ), new Object[]{"%" + city.toUpperCase() + "%", "%" + district.toUpperCase() + "%", "%" + town.toUpperCase() + "%"}
            );

        } catch (Exception exception) {
            log.error("An exception occurred while fetching neighborhoods");
            throw new RuntimeException("An exception occurred while fetching neighborhoods", exception);
        }
        return neighborhoods;
    }

}
