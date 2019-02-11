import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Ant extends Creature
{
  private int survive;
  
  public Ant (Island island)
  {
    super(island);
    survive = 0;
  }
  
    public Color getColor()
  {
    return Color.RED;
  }
  
  public Creature createCreature(Island isl)
  {
    Ant crt = new Ant(null);
    crt.isl = isl;
    crt.dayLastMoved = dayLastMoved;
    crt.survive = 0;
    return crt;
  }
    
  public boolean move (Color color)    //wander
  {
    boolean success;
    success = super.move(Color.WHITE);
    survive++;
    if (survive >= 3)
    {
      Creature a = spawn();
      if (a != null)
        survive = 0;
    }
    return success;
  }
}
  
