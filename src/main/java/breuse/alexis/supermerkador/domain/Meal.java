package breuse.alexis.supermerkador.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Meal {
    private String title;
    private List<Ingredient> requiredIngredients;
    private MealCategory mealCategory;
}
