package end.end.service.impl;

import end.end.dto.ItemDto;
import end.end.mapper.ItemMapper;
import end.end.repository.ItemRepository;
import end.end.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository repository;
    private final ItemMapper mapper;

    @Override
    public List<ItemDto> getAll() {
        return mapper.toDtoList(repository.findAll());
    }
    @Override
    public ItemDto getById(Long id) {
        return mapper.toDto(repository.findById(id).orElse(null));
    }
    @Override
    public boolean add(ItemDto itemDto) {
        if (Objects.isNull(itemDto)) {
            return false;
        }
        repository.save(mapper.toEntity(itemDto));
        return true;
    }
    @Override
    public boolean update(Long id, ItemDto itemDto) {
        ItemDto oldItem = getById(id);
        if (Objects.isNull(oldItem) || Objects.isNull(itemDto)) {
            return false;
        }
        oldItem.setNameDto(itemDto.getNameDto());
        oldItem.setPriceDto(itemDto.getPriceDto());
        oldItem.setCountryDto(itemDto.getCountryDto());
        oldItem.setCategoryDto(itemDto.getCategoryDto());
        repository.save(mapper.toEntity(oldItem));
        return true;
    }
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
