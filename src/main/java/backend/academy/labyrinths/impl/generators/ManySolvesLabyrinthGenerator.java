package backend.academy.labyrinths.impl.generators;

import backend.academy.labyrinths.entites.Cell;
import backend.academy.labyrinths.entites.Labyrinth;
import backend.academy.labyrinths.enums.CellType;
import backend.academy.labyrinths.interfaces.generators.LabyrinthGenerator;
import java.util.Random;

public class ManySolvesLabyrinthGenerator implements LabyrinthGenerator {

    private final static Random RANDOM = new Random();

    private int countBounties;

    private int countBarriers;

    private final LabyrinthGenerator generator = new EllerGenerator();

    @Override
    public Labyrinth generate(int width, int height) {
        Labyrinth labyrinth = generator.generate(width, height);
        this.countBounties = (width + height) / 5;
        this.countBarriers = (width + height) / 5;
        setBarriers(labyrinth);
        setBounties(labyrinth);
        return labyrinth;
    }

    private void setBarriers(Labyrinth labyrinth) {
        int counter = 0;

        for (Cell[] cells : labyrinth.grid()) {
            for (Cell cell : cells) {
                if (RANDOM.nextInt(100) % 25 == 0 && cell.type().equals(CellType.PASSAGE) && counter <= countBarriers) {
                    cell.type(CellType.BARRIER);
                    counter++;
                }
            }
        }
    }

    private void setBounties(Labyrinth labyrinth) {
        int counter = 0;

        for (Cell[] cells : labyrinth.grid()) {
            for (Cell cell : cells) {
                if (RANDOM.nextInt(100) % 25 == 0 && cell.type().equals(CellType.PASSAGE) && counter <= countBounties) {
                    cell.type(CellType.BOUNTY);
                    counter++;
                }
            }
        }
    }
}
