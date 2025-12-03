package end.end.serviceTest;

import end.end.dto.CategoryDto;
import end.end.dto.CountryDto;
import end.end.dto.ItemDto;
import end.end.service.ItemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Random;

@SpringBootTest
public class ItemServiceTest {
    @Autowired
    private ItemService itemService;

    @Test
    void getAllTest() {
        List<ItemDto> itemDtos = itemService.getAll();
        Assertions.assertNotNull(itemDtos);
        Assertions.assertNotEquals(0, itemDtos.size());

        for (ItemDto dto : itemDtos) {
            Assertions.assertNotNull(dto);
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getNameDto());
            Assertions.assertTrue(dto.getPriceDto() >= 0);
        }
    }
    @Test
    void getByIdTest() {
        List<ItemDto> allItems = itemService.getAll();
        Random random = new Random();
        int randomIndex = random.nextInt(allItems.size());
        Long someId = allItems.get(randomIndex).getId();

        ItemDto itemDto = itemService.getById(someId);
        Assertions.assertNotNull(itemDto);
        Assertions.assertEquals(someId, itemDto.getId());

        ItemDto nonExisting = itemService.getById(-1L);
        Assertions.assertNull(nonExisting);
    }
    @Test
    void addTest() {
        CountryDto country = new CountryDto();
        country.setId(1L);
        country.setNameDto("TestCountry");
        country.setCodeDto("TC");

        CategoryDto category = new CategoryDto();
        category.setId(1L);
        category.setNameDto("TestCategory");

        ItemDto itemDto = new ItemDto();
        itemDto.setNameDto("TestItem");
        itemDto.setPriceDto(500);
        itemDto.setCountryDto(country);
        itemDto.setCategoryDto(List.of(category));

        boolean created = itemService.add(itemDto);
        Assertions.assertTrue(created);
    }
    @Test
    void updateTest() {
        List<ItemDto> allItems = itemService.getAll();
        Random random = new Random();
        int randomIndex = random.nextInt(allItems.size());
        Long someId = allItems.get(randomIndex).getId();

        ItemDto updatedItem = new ItemDto();
        updatedItem.setId(someId);
        updatedItem.setNameDto("UpdatedItem");
        updatedItem.setPriceDto(999);

        boolean updated = itemService.update(someId, updatedItem);
        Assertions.assertTrue(updated);

        ItemDto check = itemService.getById(someId);
        Assertions.assertEquals("UpdatedItem", check.getNameDto());
        Assertions.assertEquals(999, check.getPriceDto());
    }
    @Test
    void deleteTest() {
        List<ItemDto> allItems = itemService.getAll();
        Random random = new Random();
        int randomIndex = random.nextInt(allItems.size());
        Long someId = allItems.get(randomIndex).getId();

        itemService.delete(someId);

        ItemDto deleted = itemService.getById(someId);
        Assertions.assertNull(deleted);
    }
}
