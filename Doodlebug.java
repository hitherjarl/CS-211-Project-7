import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Doodlebug extends Creature
{
  private int starve;
  private int survive;
  
  public Doodlebug (Island island)
  {
    super(island);
    starve = 0;
    survive = 0;
  }
  
  public Color getColor()
  {
    return Color.BLUE;
  }
  
  public Creature createCreature(Island isl)
  {
    Doodlebug crt = new Doodlebug(null);
    crt.isl = isl;
    crt.dayLastMoved = dayLastMoved;
    crt.starve = 0;
    crt.survive = 0;
    return crt;
  }
  
  public boolean move (Color color)    //check and wander
  {
    boolean success = false;
    starve++;
    survive++;
    success = super.move(Color.RED);
    if (success)
      starve = 0;
    else
      success = super.move(Color.WHITE);
    if (survive >= 8)
    {
      Creature c = spawn();
      if (c != null)
        survive = 0;
    }
    if (starve >= 3)          //starve remove
      isl.remove(x,y);
    return success;
  } 
}
