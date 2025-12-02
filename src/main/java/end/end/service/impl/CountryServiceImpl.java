package end.end.service.impl;

import end.end.dto.CountryDto;
import end.end.mapper.CountryMapper;
import end.end.repository.CountryRepository;
import end.end.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository repository;
    private final CountryMapper mapper;

    @Override
    public List<CountryDto> getAll() {
        return mapper.toDtoList(repository.findAll());
    }
    @Override
    public CountryDto getById(Long id) {
        return mapper.toDto(repository.findById(id).orElse(null));
    }
    @Override
    public boolean add(CountryDto countryDto) {
        if (Objects.isNull(countryDto)) {
            return false;
        }
        repository.save(mapper.toEntity(countryDto));
        return true;
    }
    @Override
    public boolean update(Long id, CountryDto countryDto) {
        CountryDto oldCountry = getById(id);
        if (Objects.isNull(oldCountry) || Objects.isNull(countryDto)) {
            return false;
        }
        oldCountry.setNameDto(countryDto.getNameDto());
        oldCountry.setCodeDto(countryDto.getCodeDto());
        repository.save(mapper.toEntity(oldCountry));
        return true;
    }
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
