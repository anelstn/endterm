package end.end.service.impl;

import end.end.dto.CategoryDto;
import end.end.mapper.CategoryMapper;
import end.end.repository.CategoryRepository;
import end.end.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Override
    public List<CategoryDto> getAll() {
        return mapper.toDtoList(repository.findAll());
    }
    @Override
    public CategoryDto getById(Long id) {
        return mapper.toDto(repository.findById(id).orElse(null));
    }
    @Override
    public boolean add(CategoryDto categoryDto) {
        if (Objects.isNull(categoryDto)) {
            return false;
        }
        repository.save(mapper.toEntity(categoryDto));
        return true;
    }
    @Override
    public boolean update(Long id, CategoryDto categoryDto) {
        CategoryDto oldCategory = getById(id);
        if (Objects.isNull(oldCategory) || Objects.isNull(categoryDto)) {
            return false;
        }
        oldCategory.setNameDto(categoryDto.getNameDto());
        repository.save(mapper.toEntity(oldCategory));
        return true;
    }
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
