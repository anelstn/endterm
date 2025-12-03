package end.end.mapperTest;

import end.end.dto.CategoryDto;
import end.end.dto.CountryDto;
import end.end.dto.ItemDto;
import end.end.model.Category;
import end.end.model.Country;
import end.end.model.Item;
import end.end.mapper.ItemMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ItemMapperTest {
    @Autowired
    private ItemMapper itemMapper;

    @Test
    void convertEntityToDtoTest() {
        Country country = new Country(1L, "Kazakhstan", "KZ");
        List<Category> categories = List.of(new Category(1L, "Electronics"));
        Item item = new Item(1L, "Phone", 1000, country, categories);
        ItemDto dto = itemMapper.toDto(item);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(item.getId(), dto.getId());
        Assertions.assertEquals(item.getName(), dto.getNameDto());
        Assertions.assertEquals(item.getPrice(), dto.getPriceDto());

        Assertions.assertNotNull(dto.getCountryDto());
        Assertions.assertEquals(item.getCountry().getId(), dto.getCountryDto().getId());
        Assertions.assertEquals(item.getCountry().getName(), dto.getCountryDto().getNameDto());
        Assertions.assertEquals(item.getCountry().getCode(), dto.getCountryDto().getCodeDto());

        Assertions.assertNotNull(dto.getCategoryDto());
        Assertions.assertEquals(item.getCategory().size(), dto.getCategoryDto().size());
        Assertions.assertEquals(item.getCategory().get(0).getName(), dto.getCategoryDto().get(0).getNameDto());
    }
    @Test
    void convertDtoToEntityTest() {
        CountryDto countryDto = new CountryDto();
        countryDto.setId(1L);
        countryDto.setNameDto("Kazakhstan");
        countryDto.setCodeDto("KZ");

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(1L);
        categoryDto.setNameDto("Electronics");

        ItemDto dto = new ItemDto();
        dto.setId(1L);
        dto.setNameDto("Phone");
        dto.setPriceDto(1000);
        dto.setCountryDto(countryDto);
        dto.setCategoryDto(List.of(categoryDto));

        Item entity = itemMapper.toEntity(dto);
        Assertions.assertNotNull(entity);
        Assertions.assertEquals(dto.getId(), entity.getId());
        Assertions.assertEquals(dto.getNameDto(), entity.getName());
        Assertions.assertEquals(dto.getPriceDto(), entity.getPrice());

        Assertions.assertNotNull(entity.getCountry());
        Assertions.assertEquals(dto.getCountryDto().getId(), entity.getCountry().getId());
        Assertions.assertEquals(dto.getCountryDto().getNameDto(), entity.getCountry().getName());
        Assertions.assertEquals(dto.getCountryDto().getCodeDto(), entity.getCountry().getCode());

        Assertions.assertNotNull(entity.getCategory());
        Assertions.assertEquals(dto.getCategoryDto().size(), entity.getCategory().size());
        Assertions.assertEquals(dto.getCategoryDto().get(0).getNameDto(), entity.getCategory().get(0).getName());
    }
    @Test
    void convertEntityListToDtoListTest() {
        Country country = new Country(1L, "Kazakhstan", "KZ");
        List<Category> categories = List.of(new Category(1L, "Electronics"));
        List<Item> list = new ArrayList<>();
        list.add(new Item(1L, "Phone", 1000, country, categories));
        list.add(new Item(2L, "Laptop", 2000, country, categories));

        List<ItemDto> dtoList = itemMapper.toDtoList(list);
        Assertions.assertNotNull(dtoList);
        Assertions.assertEquals(list.size(), dtoList.size());

        for (int i = 0; i < list.size(); i++) {
            Assertions.assertEquals(list.get(i).getId(), dtoList.get(i).getId());
            Assertions.assertEquals(list.get(i).getName(), dtoList.get(i).getNameDto());
            Assertions.assertEquals(list.get(i).getPrice(), dtoList.get(i).getPriceDto());
        }
    }
}
