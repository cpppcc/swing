package com.company;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class LineArrow {

    private final int x;
    private final int y;
    private final int endX;
    private final int endY;
    private final double angleRadians;
    private final Color color;
    private final int thickness;
    private final double scale;

    private static final int TRIANGLE_LENGTH = 2;
    private static final Polygon ARROW_HEAD = new Polygon();

    static {
        ARROW_HEAD.addPoint(TRIANGLE_LENGTH, 0);
        ARROW_HEAD.addPoint(0, -TRIANGLE_LENGTH / 2);
        ARROW_HEAD.addPoint(0, TRIANGLE_LENGTH / 2);
    }

    public LineArrow(int x, int y, double angleDegrees, int length, Color color, int thickness, float headSize) {
        super();
        this.x = x;
        this.y = y;
        this.color = color;
        this.thickness = thickness;
        this.angleRadians = Math.toRadians(angleDegrees);
        this.scale = headSize / TRIANGLE_LENGTH;
        this.endX = (int) (x + (length - headSize) * Math.cos(angleRadians));
        this.endY = (int) (y + (length - headSize) * Math.sin(angleRadians));
    }

    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.setStroke(new BasicStroke(thickness));
        g2.drawLine(x, y, endX, endY);
        AffineTransform tx1 = g2.getTransform();
        AffineTransform tx2 = (AffineTransform) tx1.clone();
        tx2.translate(endX, endY);
        tx2.scale(scale, scale);
        tx2.rotate(angleRadians);
        g2.setTransform(tx2);
        g2.fill(ARROW_HEAD);
        g2.setTransform(tx1);
    }
}