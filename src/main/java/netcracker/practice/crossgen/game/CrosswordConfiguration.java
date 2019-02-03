package netcracker.practice.crossgen.game;

import netcracker.practice.crossgen.logic.grid.Coordinate;

import java.util.*;

public class CrosswordConfiguration implements Configuration {
    private int height;
    private int width;
    private String clues ;
    private String constraints;

    public CrosswordConfiguration() {
        clues = "";
        constraints = "";
    }

    public String getAngle() {
        return "straight";
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getClues() {
        return clues;
    }

    public String getConstraints() {
        return constraints;
    }

    public void setClues(String clues) {
        this.clues = Objects.requireNonNull(clues);
    }

    public void setConstraints(String constraints) {
        this.constraints = Objects.requireNonNull(constraints);
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Map<String, String> parseClues() throws ParseException {
        HashMap<String, String> parsedClues = new HashMap<>();
        for (String line : clues.split("\n")) {
            if (line.isEmpty()) continue;
            int semicolonIndex = line.indexOf(':');
            if (semicolonIndex == -1) {
                throw new ParseException("Invalid clue format: no semicolon in line \"" +
                        line + "\"");
            }
            String word = line.substring(0, semicolonIndex).trim();
            String clue = line.substring(semicolonIndex + 1).trim();
            parsedClues.put(word, clue);
        }
        return parsedClues;
    }

    public Set<Coordinate> parseConstraints() throws ParseException {
        HashSet<Coordinate> parsedConstraints = new HashSet<>();
        for (String line : constraints.split("\n")) {
            if (line.isEmpty()) continue;
            int spaceIndex = line.indexOf(' ');
            if (spaceIndex == -1) {
                throw new ParseException("Invalid constraint format: no whitespace in line \"" +
                        line + "\"");
            }
            try {
                Coordinate constraint = new Coordinate(
                        Integer.parseInt(line.substring(0, spaceIndex).trim()),
                        Integer.parseInt(line.substring(spaceIndex + 1).trim())
                );
                parsedConstraints.add(constraint);
            } catch (Exception e) {
                throw new ParseException(e);
            }
        }
        return parsedConstraints;
    }

    public class ParseException extends Exception {
        public ParseException () {

        }

        public ParseException (String message) {
            super (message);
        }

        public ParseException (Throwable cause) {
            super (cause);
        }

        public ParseException (String message, Throwable cause) {
            super (message, cause);
        }
    }
}
