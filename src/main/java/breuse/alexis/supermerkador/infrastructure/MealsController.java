package breuse.alexis.supermerkador.infrastructure;

import breuse.alexis.supermerkador.domain.MealGenerator;
import breuse.alexis.supermerkador.domain.Meal;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/v1/meals", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class MealsController {

    private MealGenerator mealGenerator;

    @PostMapping("/generate")
    public ResponseEntity<List<Meal>> generateMeals(@RequestBody GenerateMultipleMealRequest request) throws IOException {
        List<Meal> meals = mealGenerator.generate(request.getDays(), request.getMealCategory());

        return ResponseEntity.ok(meals);
    }

    @PostMapping("/reroll")
    public ResponseEntity<Meal> reroll(@RequestBody RerollRequest request) throws IOException {
        Meal meal = mealGenerator.reroll(request.getAlreadyGeneratedMeals());

        return ResponseEntity.ok(meal);
    }
}
