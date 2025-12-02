package end.end.mapper;

import end.end.dto.CategoryDto;
import end.end.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "nameDto", source = "name")
    CategoryDto toDto(Category entity);

    @Mapping(target = "name", source = "nameDto")
    Category toEntity(CategoryDto dto);

    List<CategoryDto> toDtoList(List<Category> entities);
}