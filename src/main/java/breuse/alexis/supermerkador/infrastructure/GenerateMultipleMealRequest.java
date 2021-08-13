package breuse.alexis.supermerkador.infrastructure;

import breuse.alexis.supermerkador.domain.MealCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenerateMultipleMealRequest {
    private int days;
    private MealCategory mealCategory;
}
