package backend.academy.labyrinths.factories;

import backend.academy.labyrinths.enums.GeneratorType;
import backend.academy.labyrinths.impl.generators.DeepFirstSearchGenerator;
import backend.academy.labyrinths.impl.generators.EllerGenerator;
import backend.academy.labyrinths.interfaces.generators.LabyrinthGenerator;
import java.util.HashMap;
import java.util.Map;

/**
 * Фабрика алгоритмов генерации лабиринта
 */
public class LabyrinthGeneratorFactory {

    private final Map<GeneratorType, LabyrinthGenerator> generators;

    public LabyrinthGeneratorFactory() {
        this.generators = new HashMap<>();
        for (GeneratorType type : GeneratorType.values()) {
            if (type.equals(GeneratorType.ELLER_GENERATOR)) {
                generators.put(type, new EllerGenerator());
            } else if (type.equals(GeneratorType.DFS)) {
                generators.put(type, new DeepFirstSearchGenerator());
            }
        }
    }

    /**
     * Метод, возвращающий конкретную реализацию алгоритма генерации в зависимости от переданного типа
     * @param type - тип алгоритма генерации лабиринта
     * @return - конкретная реализация алгоритма генерации
     */
    public LabyrinthGenerator get(GeneratorType type) {
        return this.generators.get(type);
    }
}
