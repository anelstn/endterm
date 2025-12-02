package end.end.controller;

import end.end.dto.CategoryDto;
import end.end.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryApi {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        CategoryDto dto = categoryService.getById(id);
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }
        return ResponseEntity.ok(dto);
    }
    @PostMapping
    public ResponseEntity<?> add(@RequestBody CategoryDto categoryDto) {
        boolean created = categoryService.add(categoryDto);
        if (!created) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid category data");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Category created successfully");
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        boolean updated = categoryService.update(id, categoryDto);
        if (!updated) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update failed");
        }
        return ResponseEntity.ok("Category updated successfully");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
