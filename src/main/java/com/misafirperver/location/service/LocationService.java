package com.misafirperver.location.service;

import java.util.List;

import com.misafirperver.location.model.Location;
import com.misafirperver.location.repository.LocationRepository;
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

    public List<Location> getClickableLocations(String city, String district, String town) {
        List<Location> locations = null;
        if (ObjectUtils.isEmpty(city) && ObjectUtils.isEmpty(district) && ObjectUtils.isEmpty(town)) {
            locations = getClickableCities();
        } else if (ObjectUtils.isNotEmpty(city) && ObjectUtils.isEmpty(district)) {
            locations = getClickableDistricts(city);
        } else if (ObjectUtils.isNotEmpty(city) && ObjectUtils.isNotEmpty(district)&&ObjectUtils.isEmpty(town)) {
            locations = getClickableTowns(city, district);
        } else if (ObjectUtils.isNotEmpty(city) && ObjectUtils.isNotEmpty(district)&&ObjectUtils.isNotEmpty(town)) {
            locations = getClickableNeighborhoods(city, district, town);
        } else {
            log.error("Exception");
        }
        return locations;
    }

    private List<Location> getClickableCities() {
        return locationRepository.fetchCities();
    }

    private List<Location> getClickableDistricts(String city) {
        return locationRepository.fetchClickableDistricts(city);
    }

    private List<Location> getClickableTowns(String city, String district) {
        return locationRepository.fetchClickableTowns( city, district);
    }

    private List<Location> getClickableNeighborhoods(String city, String district, String town) {
        return locationRepository.fetchClickableNeighborhoods(city, district, town);
    }

}
