/*
 *
 *  7272   72   72  72   72
 * 72  72  72   72  727 727
 * 72      72 7 72  72 2 72
 * 72  72  7272772  72   72
 *  7272    72 72   72   72
 *
 * @author Aradam
 */

package com.aradam.catwar.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class CwShapeRenderer extends ShapeRenderer
{
  private final ImmediateModeRenderer mRenderer;

  public CwShapeRenderer ()
  {
    super ();
    mRenderer = getRenderer ();
  }

  /**
   * Рисование круглого HealthBar'a
   */
  public void drawCircleHealthBar (float x, float y, float radius, float wide, float amt, Color emptyColor, Color fillColor, float alpha)
  {
    float angle = 360 * amt; //Сколько градусов дуга с fillColor
    int allSegments = 50; //(int) (12 * Math.cbrt (radius)); //Кол-во сегментов круга
    int fillSegments = (int) Math.ceil (allSegments * amt); //Кол-во сегментов в дуге с emptyColor

    fillColor.a = alpha;
    emptyColor.a = alpha;
    strokeArc (x, y, radius, wide, 90, angle, fillSegments, fillColor); //Рисуем дугу fillColor
    strokeArc (x, y, radius, wide, angle + 90, 360 - angle, allSegments - fillSegments + 1, emptyColor); //Рисуем дугу emptyColor
    fillColor.a = 1;
    emptyColor.a = 1;
  }

  /**
   * Рисование дуги с помощью вертексов
   */
  public void strokeArc (float x, float y, float radius, float wide, float start, float degrees, int segments, Color color)
  {
    float colorBits = color.toFloatBits ();
    float degreesForSegment = degrees / segments;
    float radiusWithoutWide = radius - wide;
    for (int i = 0; i < segments; i++)
    {
      float x1 = radius * MathUtils.cosDeg (start + degreesForSegment * i);
      float y1 = radius * MathUtils.sinDeg (start + degreesForSegment * i);

      float x2 = radiusWithoutWide * MathUtils.cosDeg (start + degreesForSegment * i);
      float y2 = radiusWithoutWide * MathUtils.sinDeg (start + degreesForSegment * i);

      float x3 = radius * MathUtils.cosDeg (start + degreesForSegment * (i + 1));
      float y3 = radius * MathUtils.sinDeg (start + degreesForSegment * (i + 1));

      float x4 = radiusWithoutWide * MathUtils.cosDeg (start + degreesForSegment * (i + 1));
      float y4 = radiusWithoutWide * MathUtils.sinDeg (start + degreesForSegment * (i + 1));

      mRenderer.color (colorBits);
      mRenderer.vertex (x + x1, y + y1, 0);
      mRenderer.color (colorBits);
      mRenderer.vertex (x + x3, y + y3, 0);
      mRenderer.color (colorBits);
      mRenderer.vertex (x + x2, y + y2, 0);

      mRenderer.color (colorBits);
      mRenderer.vertex (x + x3, y + y3, 0);
      mRenderer.color (colorBits);
      mRenderer.vertex (x + x2, y + y2, 0);
      mRenderer.color (colorBits);
      mRenderer.vertex (x + x4, y + y4, 0);
    }
  }
}