package netcracker.practice.crossgen.logic.crossword;

import java.io.IOException;
import java.util.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import netcracker.practice.crossgen.logic.grid.CharGrid;
import netcracker.practice.crossgen.logic.grid.Coordinate;
import netcracker.practice.crossgen.logic.word.Clue;
import netcracker.practice.crossgen.logic.word.ClueComparator;
import netcracker.practice.crossgen.logic.word.Direction;

public class CanadianCrossword extends Crossword {
    private final TreeSet<Clue> clues = new TreeSet<>(new ClueComparator());

    public CanadianCrossword() {
        super();
    }

    public CanadianCrossword(Collection<Clue> clues) {
        super();
        this.clues.addAll(clues);
    }

    public int cluesCount() {
        return clues.size();
    }

    public Map<String, List<String>> getSortedClues() {
        LinkedHashMap<String, List<String>> sortedClues = new LinkedHashMap<>();
        for (Clue clue : clues) {
            String direction = clue.getDirection().getName();
            if (!sortedClues.containsKey(direction)) {
                sortedClues.put(direction, new ArrayList<>());
            }
            sortedClues.get(direction).add(
                    "(" + (clue.getRow() + 1) + ", " + (clue.getCol() + 1) + ") " + " " + clue.getClue());
        }
        return sortedClues;
    }

    /* unused */
    public void setClueNumbers() {
        ClueComparator comparator = new ClueComparator();
        Clue previous = clues.first();
        for (Clue clue :clues) {
            int number = comparator.compare(clue, previous) == 0 ?
                    previous.getClueNumber() :
                    previous.getClueNumber() + 1;
            clue.setClueNumber(number);
        }
    }

    @Override
    public Set<Coordinate> getConstraints() {
        int height = getHeight(), width = getWidth();
        HashSet<Coordinate> constraints = new HashSet<>(height * width);
        for (int r = 0; r < height; r++)
            for (int c = 0; c < width; c++)
                constraints.add(new Coordinate(r, c));

        for (Clue clue : clues) {
            constraints.removeAll(clue.getAngle().getCoordinates(clue));
        }
        return constraints;
    }

    @Override
    public int getHeight() {
        int h = 0;
        for (Clue clue : clues) {
            int endRow = clue.getEndRow();
            if (endRow >= h) h = endRow + 1;
        }
        return h;
    }

    @Override
    public int getWidth() {
        int w = 0;
        for (Clue clue : clues) {
            int endCol = clue.getEndCol();
            if (endCol >= w) w = endCol + 1;
        }
        return w;
    }

    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Подсказки (всего ").append(cluesCount()).append(")\n");
        for (Map.Entry<String, List<String>> entry : getSortedClues().entrySet()) {
            sb.append(entry.getKey()).append(":\n");
            for (String clue : entry.getValue()) {
                sb.append(clue).append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public char getSymbol(int row, int col) {
        return getCharGrid().getSymbol(row, col);
    }

    private CharGrid getCharGrid() {
        CharGrid charGrid = new CharGrid(this);
        for (Clue clue : clues)
            charGrid.put(clue);
        return charGrid;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof CanadianCrossword)) return false;
        return clues.equals(((CanadianCrossword) o).clues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clues);
    }

    @Override
    public char[][] toCharMatrix() {
        return getCharGrid().toCharMatrix();
    }

    @Override
    public Formatter getFormatter() {
        return new CanadianCrosswordFormatter();
    }

    @Override
    public String toString() {
        return getCharGrid().toString();
    }

    private class CanadianCrosswordFormatter extends Crossword.CrosswordFormatter<CanadianCrosswordFormatter> {
        final Pattern cluePattern = Pattern.compile( "([^ ]+) \\((\\d+), (\\d+)\\) ([^:]+): (.+)");

        @Override
        public void deserialize(String filename) throws IOException {
            try (Scanner scanner = new Scanner(new FileReader(filename))) {
                if (!scanner.hasNextLine()) throw new IOException("File is empty");

                // read clues
                Matcher m = cluePattern.matcher("");
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    m.reset(line);
                    if (!m.matches())
                        throw new IOException("Line doesn't match grid clue pattern: \"" + line + "\"");

                    Direction dir = Direction.valueOf(m.group(1));
                    int row = Integer.parseInt(m.group(2));
                    int col = Integer.parseInt(m.group(3));
                    Clue clue = new Clue(row, col, dir, m.group(4), m.group(5));
                    clues.add(clue);
                }
            }
        }

        @Override
        public void serialize(String filename) throws IOException {
            try (PrintWriter printWriter = new PrintWriter(new FileWriter(filename))) {
                // save clues
                for (Clue clue : clues) {
                    printWriter.printf("%s (%d, %d) %s: %s%n",
                            clue.getDirection().toString(),
                            clue.getRow(), clue.getCol(),
                            clue.getString(), clue.getClue());
                }
            }
        }
    }
}
