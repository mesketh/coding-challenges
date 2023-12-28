package floodfillvariant;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Given a 2D grid of colours - identify the highest count colour in the grid.
 *
 * <p>
 * Modified Flood Fill that counts the most prominent colour in a grid of coloured patterns.
 * Traditional FF performs colour replacement given a colour target and a replacement colour.
 * <p>
 * Source: techlead YouTube channel (FB interview question ep).
 *
 * @author Mark Hesketh
 */
public class FloodFillVariant {

    private Map<Color, Integer> colourCountMap;
    private Color[][] colourGrid = new Color[2][5];
    private boolean[][] visitRecord = new boolean[2][5];
    private Color[] choices = new Color[]{Color.BLACK, Color.RED, Color.WHITE};


    public static void main(String[] args) {
        new FloodFillVariant().
                setupColourGrid().
                visitGrid(0, 0).
                printResult();
    }

    /**
     * Populates a 2D grid of {@link Color}s to perform the Flood Fill algorithm on.
     *
     * @return Fluent api - returns itself.
     */
    private FloodFillVariant setupColourGrid() {

        colourCountMap = Arrays.stream(choices).collect(Collectors.toMap(c -> c, c -> 0));
        Stream<Color> allColourChoices = Stream.iterate(choices[(int) (Math.random() * 3)], (c) -> choices[(int) (Math.random() * 3)]).limit(10);
        Color[] colors = allColourChoices.collect(Collectors.toList()).toArray(new Color[10]);

        // split all choices into 2d for the grid
        Spliterator<Color> spliterator1 = Arrays.stream(colors).spliterator();
        Spliterator<Color> spliterator2 = spliterator1.trySplit();

        java.util.List<Color> rowOfColor = new ArrayList<>(5);
        spliterator1.forEachRemaining(rowOfColor::add);
        colourGrid[0] = rowOfColor.toArray(new Color[5]);
        rowOfColor = new ArrayList<>(5);
        spliterator2.forEachRemaining(rowOfColor::add);
        colourGrid[1] = rowOfColor.toArray(new Color[5]);

        // dump the grid
        Arrays.stream(colourGrid).forEach((a) -> Arrays.stream(a).forEach(System.out::println));

        return this;
    }

    /**
     * Recursive visitor to each grid cell. Ignores out of bounds coordinates, ensures
     * single visit policy, updates colour count for matching colour.
     *
     * @param x The x coordinate of the cell to visit
     * @param y The y coordinate of the cell to visit
     * @return Flunt api - return itself
     */
    private FloodFillVariant visitGrid(int x, int y) {

        // ignore cells off the grid
        if (x < 0 || y >= colourGrid.length || x >= colourGrid[0].length || y < 0) return this;

        // check and count colour of current cell
        if (!visitRecord[y][x]) {
            visitRecord[y][x] = true;  // remember the visit (single visit policy)
            colourCountMap.computeIfPresent(colourGrid[y][x], (c, i) -> ++i);
        } else {
            return this;
        }

        // left/right/top/bottom
        visitGrid(x - 1, y);
        visitGrid(x + 1, y);
        visitGrid(x, y - 1);
        visitGrid(x, y + 1);

        return this;
    }

    private String toColourName(Color colorToMatch) {
        if (colorToMatch == Color.RED) return "Red";
        else if (colorToMatch == Color.BLACK) return "Black";
        else return "White";
    }


    /**
     * Output the results of the colour counting.
     */
    private void printResult() {

        System.out.println("\n Result:\n\t " + colourCountMap.entrySet().stream().
                max(Map.Entry.comparingByValue()).
                map(e -> toColourName(e.getKey()) + " is the most prevalent colour with a count of " + e.getValue()).get());
    }

}
