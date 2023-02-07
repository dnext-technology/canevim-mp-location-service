package com.misafirperver.location.repository.query;

public class LocationSqlQueries {
    private LocationSqlQueries(){}

    public static final String SQL_FETCH_CITIES = "SELECT DISTINCT city FROM public.location ORDER BY city ASC";
    public static final String SQL_FETCH_DISTRICTS = "SELECT DISTINCT district FROM public.location WHERE city ILIKE ? ORDER BY district ASC";
    public static final String SQL_FETCH_TOWNS = "SELECT DISTINCT town FROM public.location WHERE city ILIKE ? AND district ILIKE ? ORDER BY town ASC";
    public static final String SQL_FETCH_NEIGHBORHOODS_WITH_TOWN = "SELECT DISTINCT neighborhood FROM public.location WHERE city ILIKE ? AND district ILIKE ? AND town ILIKE ? ORDER BY neighborhood ASC";




}
