package utility;

import gamelogic.entities.Entity;

import java.awt.*;
import java.util.*;

public final class ShortestPathFinder {

    /**
     * Finds the shortest path between two points using the BFS algorithm
     *
     * @param start the <code>Point</code> where the path starts
     * @param end   the <code>Point</code> where the path ends
     * @return the next <code>Point</code> on the shortest path
     */
    public static Point findNextCellForShortestPath(Point start, Point end) {
        if(start.equals(end)){
            return start;
        }
        PriorityQueue<LinkedList<Point>> paths = new PriorityQueue<>(Comparator.comparing(LinkedList::size));
        Set<Point> discovered = new HashSet<>();

        LinkedList<Point> initialPath = new LinkedList<>();
        initialPath.add(start);
        paths.add(initialPath);
        discovered.add(start);

        while (!paths.isEmpty()) {
            LinkedList<Point> path = paths.poll();
            Point lastPoint = path.getLast();

            if (lastPoint.equals(end) && path.size() > 1) {
                return path.get(1);
            }

            for (Point neighbor : getValidNeighbours(lastPoint)) {
                if (!discovered.contains(neighbor)) {
                    LinkedList<Point> newPath = new LinkedList<>(path);
                    newPath.add(neighbor);
                    paths.add(newPath);
                    discovered.add(neighbor);
                }
            }
        }

        // if there isn't a path between the two points
        return start;
    }

    /**
     * @param p the <code>Point</code> for which we want to get the neighbours of
     * @return a list of neighbours
     */
    private static LinkedList<Point> getValidNeighbours(Point p){
        ArrayList<ArrayList<Entity>> level = ResourceHandler.getCurrentLevel();
        LinkedList<Point> neighbors = new LinkedList<>();
        if(p.x > 0 &&level.get(p.y).get(p.x - 1).isTraversableByGhosts()){
            neighbors.add(new Point(p.x - 1, p.y));
        }
        if(p.x < 27 && level.get(p.y).get(p.x + 1).isTraversableByGhosts()){
            neighbors.add(new Point(p.x + 1, p.y));
        }
        if(p.y > 0 && level.get(p.y - 1).get(p.x).isTraversableByGhosts()){
            neighbors.add(new Point(p.x, p.y - 1));
        }
        if(p.y < 30 && level.get(p.y + 1).get(p.x).isTraversableByGhosts()){
            neighbors.add(new Point(p.x, p.y + 1));
        }
        return neighbors;
    }

    /**
     * Private constructor to prevent instantiation
     * of utility class
     */
    private ShortestPathFinder() {
    }
}
