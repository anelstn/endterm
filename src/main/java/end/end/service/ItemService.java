package end.end.service;

import end.end.dto.ItemDto;
import java.util.List;

public interface ItemService {

    List<ItemDto> getAll();
    ItemDto getById(Long id);
    boolean add(ItemDto itemDto);
    boolean update(Long id, ItemDto itemDto);
    void delete(Long id);

}