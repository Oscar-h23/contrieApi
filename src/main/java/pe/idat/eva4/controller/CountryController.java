package pe.idat.eva4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.idat.eva4.entity.Country;
import pe.idat.eva4.service.CountryService;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    // Get all countries
    @GetMapping
    public ResponseEntity<List<Country>> getAllCountries() {
        List<Country> countries = countryService.getAllCountries();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    // Get a country by ID
    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable Long id) {
        Country country = countryService.getCountryById(id);
        if (country != null) {
            return new ResponseEntity<>(country, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Create a new country
    @PostMapping
    public ResponseEntity<String> createCountry(@RequestBody Country country) {
        Country createdCountry = countryService.createCountry(country);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("País creado correctamente");
    }

    // Update an existing country
    // Update a country
    @PutMapping("/{name}")
    public ResponseEntity<String> updateCountry(@PathVariable String name, @RequestBody Country country) {
        Country updatedCountry = countryService.updateCountryByName(name, country);
        if (updatedCountry != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("País actualizado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("País no encontrado");
        }
    }

    // Delete a country
    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteCountry(@PathVariable String name) {
        boolean isDeleted = countryService.deleteCountryByName(name);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("País eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("País no encontrado");
        }
    }
}
