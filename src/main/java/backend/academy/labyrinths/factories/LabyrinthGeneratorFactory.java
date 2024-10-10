package backend.academy.labyrinths.factories;

import backend.academy.labyrinths.enums.GeneratorType;
import backend.academy.labyrinths.impl.generators.EllerGenerator;
import backend.academy.labyrinths.interfaces.generators.LabyrinthGenerator;
import java.util.HashMap;
import java.util.Map;

public class LabyrinthGeneratorFactory {

    private final Map<GeneratorType, LabyrinthGenerator> generators;

    public LabyrinthGeneratorFactory() {
        this.generators = new HashMap<>();
        for (GeneratorType type : GeneratorType.values()) {
            if (type.equals(GeneratorType.ELLER_GENERATOR))
                generators.put(type, new EllerGenerator());
        }
    }

    public LabyrinthGenerator get(GeneratorType type) {
        return this.generators.get(type);
    }
}
