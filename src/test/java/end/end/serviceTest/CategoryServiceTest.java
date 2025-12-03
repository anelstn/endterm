package end.end.serviceTest;

import end.end.dto.CategoryDto;
import end.end.service.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class CategoryServiceTest {
    @Autowired
    private CategoryService categoryService;

    @Test
    void getAllTest() {
        List<CategoryDto> list = categoryService.getAll();
        Assertions.assertNotNull(list);
        Assertions.assertNotEquals(0, list.size());
    }
    @Test
    void getByIdTest() {
        List<CategoryDto> list = CategoryService.getAll();
        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        Long id = list.get(randomIndex).getId();

        CategoryDto categoryDto = categoryService.getById(id);
        Assertions.assertNotNull(categoryDto);
        Assertions.assertEquals(id, categoryDto.getId());
    }
    @Test
    void addTest() {
        CategoryDto category = new CategoryDto();
        category.setId(1L);
        category.setNameDto("TestCategory");

        boolean created = categoryService.add(category);
        Assertions.assertTrue(created);
    }
    @Test
    void updateTest() {
        List<CategoryDto> list = CategoryService.getAll();
        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        Long id = list.get(randomIndex).getId();

        CategoryDto updatedCategory = new CategoryDto();
        updatedCategory.setId(id);
        updatedCategory.setNameDto("UpdatedCategory");

        boolean updated = categoryService.update(id, updatedCategory);
        Assertions.assertTrue(updated);

        CategoryDto check = categoryService.getById(id);
        Assertions.assertEquals("UpdatedCountry", check.getNameDto());
    }
    @Test
    void deleteTest() {
        List<CategoryDto> list = categoryService.getAll();
        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        Long id = list.get(randomIndex).getId();

        categoryService.delete(id);

        CategoryDto deleted = categoryService.getById(id);
        Assertions.assertNull(deleted);
    }
}

