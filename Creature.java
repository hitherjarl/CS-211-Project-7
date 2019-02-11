import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Creature
{
  public Island isl;
  public int x;
  public int y;
  public int dayLastMoved;
  
  public Creature (Island island)
  {
    if(island != null)
    {
      isl = island;
      do{
        x = (int)(Math.random() * island.getRows() );
        y = (int)(Math.random() * island.getCols() );
        dayLastMoved = 0;
      }while(!island.addCreature (this, x, y));
    }
  }
  
  public Creature createCreature(Island isl)
  {
    Creature crt = new Creature(null);
    crt.isl = isl;
    crt.dayLastMoved = dayLastMoved;
    return crt;
  }
  
  public Color getColor()
  {
    return Color.WHITE;
  }
  
  public boolean move (Color color)
  {
    boolean success = false;
    maps next = isl.check(x, y, color);
    if (next != null)
    {
      isl.moveCreature(x, y, next.x, next.y);
      x = next.x;
      y = next.y;
      success = true;
    }
    return success;
  }
  
  public void setDayLastMoved (int dlm)
  {
    dayLastMoved = dlm;
  }
  
  public int getDayLastMoved ()
  {
    return dayLastMoved;
  }
  
  public Creature spawn()
  {
    Creature c = null;
    maps next = isl.check(x, y, Color.WHITE);
    if (next != null)
    {
      c = createCreature(isl);
      isl.addCreature(c, next.x, next.y);
      c.x = next.x;
      c.y = next.y;
    }
    return c;
  }
}