package end.end.serviceTest;

import end.end.dto.CountryDto;
import end.end.service.CountryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class CountryServiceTest {
    @Autowired
    private CountryService countryService;

    @Test
    void getAllTest() {
        List<CountryDto> list = countryService.getAll();
        Assertions.assertNotNull(list);
        Assertions.assertNotEquals(0, list.size());
    }
    @Test
    void getByIdTest() {
        List<CountryDto> list = CountryService.getAll();
        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        Long id = list.get(randomIndex).getId();

        CountryDto countryDto = countryService.getById(id);
        Assertions.assertNotNull(countryDto);
        Assertions.assertEquals(id, countryDto.getId());
    }
    @Test
    void addTest() {
        CountryDto country = new CountryDto();
        country.setId(1L);
        country.setNameDto("TestCountry");
        country.setCodeDto("TC");

        boolean created = countryService.add(country);
        Assertions.assertTrue(created);
    }
    @Test
    void updateTest() {
        List<CountryDto> list = CountryService.getAll();
        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        Long id = list.get(randomIndex).getId();

        CountryDto updatedCountry = new CountryDto();
        updatedCountry.setId(id);
        updatedCountry.setNameDto("UpdatedCountry");
        updatedCountry.setCodeDto("UC");

        boolean updated = countryService.update(id, updatedCountry);
        Assertions.assertTrue(updated);

        CountryDto check = countryService.getById(id);
        Assertions.assertEquals("UpdatedCountry", check.getNameDto());
        Assertions.assertEquals("UC", check.getCodeDto());
    }
    @Test
    void deleteTest() {
        List<CountryDto> list = countryService.getAll();
        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        Long id = list.get(randomIndex).getId();

        countryService.delete(id);

        CountryDto deleted = countryService.getById(id);
        Assertions.assertNull(deleted);
    }
}
