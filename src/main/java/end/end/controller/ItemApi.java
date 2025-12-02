package end.end.controller;

import end.end.dto.ItemDto;
import end.end.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemApi {
    private final ItemService itemService;
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(itemService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        ItemDto dto = itemService.getById(id);
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
        }
        return ResponseEntity.ok(dto);
    }
    @PostMapping
    public ResponseEntity<?> add(@RequestBody ItemDto itemDto) {
        boolean created = itemService.add(itemDto);
        if (!created) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid item data");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Item created successfully");
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ItemDto itemDto) {
        boolean updated = itemService.update(id, itemDto);
        if (!updated) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update failed");
        }
        return ResponseEntity.ok("Item updated successfully");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

