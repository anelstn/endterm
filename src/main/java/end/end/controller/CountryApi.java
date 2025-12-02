package end.end.controller;

import end.end.dto.CountryDto;
import end.end.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/country")
@RequiredArgsConstructor
public class CountryApi {
    private final CountryService countryService;
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(countryService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        CountryDto dto = countryService.getById(id);
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Country not found");
        }
        return ResponseEntity.ok(dto);
    }
    @PostMapping
    public ResponseEntity<?> add(@RequestBody CountryDto countryDto) {
        boolean created = countryService.add(countryDto);
        if (!created) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid country data");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Country created successfully");
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CountryDto countryDto) {
        boolean updated = countryService.update(id, countryDto);
        if (!updated) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update failed");
        }
        return ResponseEntity.ok("Country updated successfully");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        countryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
