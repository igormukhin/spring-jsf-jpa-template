package de.wirthedv.appname.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import de.wirthedv.appname.domain.City;
import de.wirthedv.appname.repo.CityRepo;
import de.wirthedv.bone.spring.RequestScopedComponent;

@RequestScopedComponent("citiesBean")
public class CitiesBean {
    
    @Autowired
    private CityRepo cityRepo;
    
    private List<City> cities;
    
    public List<City> getCities() {
        if (cities == null) {
            cities = cityRepo.findAll();
        }
        
        return cities;
    }
}
