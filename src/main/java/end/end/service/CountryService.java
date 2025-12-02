package end.end.service;

import end.end.dto.CountryDto;
import java.util.List;

public interface CountryService {
    List<CountryDto> getAll();
    CountryDto getById(Long id);
    boolean add(CountryDto countryDto);
    boolean update(Long id, CountryDto countryDto);
    void delete(Long id);
}