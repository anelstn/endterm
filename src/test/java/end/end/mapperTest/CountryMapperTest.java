package end.end.mapperTest;

import end.end.dto.CountryDto;
import end.end.dto.ItemDto;
import end.end.mapper.CountryMapper;
import end.end.model.Country;
import end.end.model.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CountryMapperTest {
    @Autowired
    private CountryMapper countryMapper;

    @Test
    void convertEntityToDtoTest() {
        Country country = new Country(1L, "Kazakhstan", "KZ");
        CountryDto dto = countryMapper.toDto(country);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(country.getId(), dto.getId());
        Assertions.assertEquals(country.getName(), dto.getNameDto());
        Assertions.assertEquals(country.getCode(), dto.getCodeDto());

    }
    @Test
    void convertDtoToEntityTest() {
        CountryDto countryDto = new CountryDto();
        countryDto.setId(1L);
        countryDto.setNameDto("Kazakhstan");
        countryDto.setCodeDto("KZ");

        Country entity = countryMapper.toEntity(dto);
        Assertions.assertNotNull(entity);
        Assertions.assertEquals(dto.getId(), entity.getId());
        Assertions.assertEquals(dto.getNameDto(), entity.getName());
        Assertions.assertEquals(dto.getCodeDto(), entity.getCode());

    }
    @Test
    void convertEntityListToDtoListTest() {
        Country country = new Country(1L, "Kazakhstan", "KZ");
        List<Item> list = new ArrayList<>();

        List<ItemDto> dtoList = countryMapper.toDtoList(list);
        Assertions.assertNotNull(dtoList);
        Assertions.assertEquals(list.size(), dtoList.size());

        for (int i = 0; i < list.size(); i++) {
            Assertions.assertEquals(list.get(i).getId(), dtoList.get(i).getId());
            Assertions.assertEquals(list.get(i).getName(), dtoList.get(i).getNameDto());
            Assertions.assertEquals(list.get(i).getPrice(), dtoList.get(i).getPriceDto());
        }
    }
}
