// Karam Ganaiem

package geometry;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a rectangle in a 2D plane defined by an upper-left point, width, and height.
 */
public class Rectangle {
    private double rectWidth;
    private double rectHeight;
    private Point rectUpperLeft;
    private Point rectUpperRight;
    private Point rectBottomLeft;
    private Point rectBottomRight;
    private Line upperLine;
    private Line bottomLine;
    private Line leftLine;
    private Line rightLine;

    /**
     * Create a new rectangle with a specified upper-left point, width, and height.
     *
     * @param upperLeft The upper-left point of the rectangle.
     * @param width     The width of the rectangle.
     * @param height    The height of the rectangle.
     */

    public Rectangle(Point upperLeft, int width, int height) {
        this.rectHeight = height;
        this.rectWidth = width;
        this.rectUpperLeft = upperLeft;

        this.rectUpperRight = new Point(rectUpperLeft.getX() + rectWidth, rectUpperLeft.getY());
        this.rectBottomLeft = new Point(rectUpperLeft.getX(), rectUpperLeft.getY() + rectHeight);
        this.rectBottomRight = new Point(rectUpperLeft.getX() + rectWidth, rectUpperLeft.getY() + rectHeight);

        this.upperLine = new Line(rectUpperLeft, rectUpperRight);
        this.bottomLine = new Line(rectBottomLeft, rectBottomRight);
        this.leftLine = new Line(rectUpperLeft, rectBottomLeft);
        this.rightLine = new Line(rectUpperRight, rectBottomRight);
    }

    /**
     * Returns a list of intersection points with the specified line.
     *
     * @param line The line to check for intersections.
     * @return A list of intersection points, possibly empty.
     */
    public java.util.List<Point> intersectionPoints(Line line) {

        Line[] rectLines = new Line[4];
        rectLines[0] = this.upperLine;
        rectLines[1] = this.bottomLine;
        rectLines[2] = this.leftLine;
        rectLines[3] = this.rightLine;

        List<Point> interPoints = new LinkedList<>();
        // check the intersuction between the lines.
        for (int h = 0; h < 4; h++) {
            Point intersection = line.intersectionWith(rectLines[h]); // calling the intersectionWith methode.
            if (intersection != null) {
                interPoints.add(intersection);
            }
        }
        return interPoints; // return the list of the intersuctions.
    }


    /**
     * Returns the width of the rectangle.
     *
     * @return The width of the rectangle.
     */
    public double getWidth() {
        return this.rectWidth;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return The height of the rectangle.
     */
    public double getHeight() {
        return this.rectHeight;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return The upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.rectUpperLeft;
    }
}