package end.end.service;

import end.end.dto.CategoryDto;
import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAll();
    CategoryDto getById(Long id);
    boolean add(CategoryDto categoryDto);
    boolean update(Long id, CategoryDto categoryDto);
    void delete(Long id);
}