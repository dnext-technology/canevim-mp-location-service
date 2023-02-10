package com.zorgundostu.location.service;

import java.util.List;

import com.zorgundostu.location.model.Location;
import com.zorgundostu.location.repository.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class LocationService {

    private LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> getClickableLocations(String city, String district, String town) throws Exception {
        List<Location> locations;
        if (ObjectUtils.isEmpty(city) && ObjectUtils.isEmpty(district) && ObjectUtils.isEmpty(town)) {
            locations = getClickableCities();
        } else if (ObjectUtils.isNotEmpty(city) && ObjectUtils.isEmpty(district)) {
            locations = getClickableDistricts(city);
        } else if (ObjectUtils.isNotEmpty(city) && ObjectUtils.isNotEmpty(district)&&ObjectUtils.isEmpty(town)) {
            locations = getClickableTowns(city, district);
        } else if (ObjectUtils.isNotEmpty(city) && ObjectUtils.isNotEmpty(district)&&ObjectUtils.isNotEmpty(town)) {
            locations = getClickableNeighborhoods(city, district, town);
        } else {
            log.error("An exception occurred while fetching locations, city: {}, district: {}, town: {}", city, district, town);
            throw new RuntimeException("An exception occurred while fetching locations, city: " + city + ", district: " + district + ", town: " + town);
        }
        return locations;
    }

    private List<Location> getClickableCities() {
        return locationRepository.fetchCities();
    }

    private List<Location> getClickableDistricts(String city) throws Exception {
        return locationRepository.fetchClickableDistricts(city);
    }

    private List<Location> getClickableTowns(String city, String district) {
        return locationRepository.fetchClickableTowns( city, district);
    }

    private List<Location> getClickableNeighborhoods(String city, String district, String town) {
        return locationRepository.fetchClickableNeighborhoods(city, district, town);
    }

}
