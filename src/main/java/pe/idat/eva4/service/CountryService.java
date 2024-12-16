package pe.idat.eva4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.idat.eva4.entity.Country;
import pe.idat.eva4.repository.CountryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    // Retrieve all countries
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    // Retrieve a country by ID
    public Country getCountryById(Long id) {
        Optional<Country> country = countryRepository.findById(id);
        return country.orElse(null);
    }

    // Create a new country
    public Country createCountry(Country country) {
        return countryRepository.save(country);
    }

    // Actualizar un país usando su nombre
    public Country updateCountryByName(String name, Country country) {
        Optional<Country> existingCountry = countryRepository.findByName(name);
        if (existingCountry.isPresent()) {
            Country updatedCountry = existingCountry.get();
            updatedCountry.setName(country.getName());  // Aquí puedes agregar la lógica para actualizar otros campos si es necesario
            updatedCountry.setLanguage(country.getLanguage());
            updatedCountry.setContinent(country.getContinent());
            return countryRepository.save(updatedCountry);
        } else {
            return null;  // Si no se encuentra el país por nombre
        }
    }

    public boolean deleteCountryByName(String name) {
        Optional<Country> country = countryRepository.findByName(name);
        if (country.isPresent()) {
            countryRepository.delete(country.get());
            return true;
        } else {
            return false;  // Si no se encuentra el país por nombre
        }
    }
}
