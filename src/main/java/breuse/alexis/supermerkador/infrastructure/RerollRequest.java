package breuse.alexis.supermerkador.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RerollRequest {
    private List<String> alreadyGeneratedMeals;
}
