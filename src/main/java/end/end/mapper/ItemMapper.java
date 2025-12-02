package end.end.mapper;

import end.end.dto.ItemDto;
import end.end.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, CountryMapper.class})
public interface ItemMapper {

    @Mapping(target = "nameDto", source = "name")
    @Mapping(target = "priceDto", source = "price")
    @Mapping(target = "countryDto", source = "country")
    @Mapping(target = "categoryDto", source = "category")
    ItemDto toDto(Item entity);

    @Mapping(target = "name", source = "nameDto")
    @Mapping(target = "price", source = "priceDto")
    @Mapping(target = "country", source = "countryDto")
    @Mapping(target = "category", source = "categoryDto")
    Item toEntity(ItemDto dto);

    List<ItemDto> toDtoList(List<Item> entities);
}
