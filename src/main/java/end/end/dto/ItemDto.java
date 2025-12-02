package end.end.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private Long id;
    private String nameDto;
    private int priceDto;
    private CountryDto countryDto;
    private List<CategoryDto> categoryDto;
}