package breuse.alexis.supermerkador.domain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MealGenerator {

    private final ObjectMapper objectMapper;

    public List<Meal> generate(int days, MealCategory mealCategory) throws IOException {
        List<Meal> totalAvailableMeals = objectMapper.readValue(readAllBytes("meals.json"), new TypeReference<>() {
        });

        List<Meal> vegetarianMeals = totalAvailableMeals.stream().filter(it -> it.getMealCategory() == MealCategory.VEGETARIAN).collect(Collectors.toList());
        List<Meal> meatMeals = totalAvailableMeals.stream().filter(it -> it.getMealCategory() == MealCategory.MEAT).collect(Collectors.toList());

        List<Meal> meals = new ArrayList<>();

        if(mealCategory == MealCategory.HALF) {
            meals.addAll(generate(vegetarianMeals, days));
            meals.addAll(generate(meatMeals, days));
        }
        else if(mealCategory == MealCategory.VEGETARIAN) {
            meals.addAll(generate(vegetarianMeals, days * 2));
        }
        else if(mealCategory == MealCategory.MEAT) {
            meals.addAll(generate(meatMeals, days * 2));
        }

        return meals;
    }

    private List<Meal> generate(List<Meal> availableMeals, int numberToGenerate) {
        Random random = new Random();
        List<Meal> meals = new ArrayList<>();

        for (int i = 0; i < numberToGenerate; i++) {
            int randomMealIndex = random.nextInt(availableMeals.size());
            meals.add(availableMeals.get(randomMealIndex));
            availableMeals.remove(randomMealIndex);
        }

        return meals;
    }

    private byte[] readAllBytes(String fileName) throws IOException {
        return Files.readAllBytes(new ClassPathResource(fileName).getFile().toPath());
    }

    public Meal reroll(List<String> alreadyGeneratedMeals) throws IOException {
        List<Meal> totalAvailableMeals = objectMapper.readValue(readAllBytes("meals.json"), new TypeReference<>() {
        });

        alreadyGeneratedMeals.forEach(alreadyGeneratedMeal ->
                totalAvailableMeals.removeIf(meal -> alreadyGeneratedMeal.toLowerCase(Locale.ROOT).equals(meal.getTitle().toLowerCase(Locale.ROOT))));

        Random random = new Random();
        int randomMealIndex = random.nextInt(totalAvailableMeals.size());

        return totalAvailableMeals.get(randomMealIndex);
    }
}
