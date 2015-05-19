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
    
    public List<City> getCities() {
        return cityRepo.findAll();
    }
}
