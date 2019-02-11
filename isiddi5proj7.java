import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*  white squares are empty 
 * green are doodlebugs
 * red are ants
 */
public class isiddi5proj7
{
  private static boolean debug = false;
  public static void main( String args[] )
  {
    int frame = debugMode(args);
    Island island = new Island();
    int i;
    for(i=0; i < 100; i++)
    {
      new Ant(island);
    }
    for(i = 0; i < 5; i++)
    {
      new Doodlebug(island);
    }
    do{
      GridDisplay.mySleep(frame);
    }while(island.nextDay());
  }
  
  public static int debugMode( String args[] )
  {
    //3 if statements
    if(args.length == 0)
    {
      return 500;
    }
    else if(args.length == 2)
    {
      String flag = "-d";
      if(flag.equals(args[0]))
      {
        try{
          int value = Integer.parseInt(args[1]);
          if(value > 0)
          {
            debug = true;
            return value;
          }
          }
        catch(NumberFormatException nfe)
        {
          System.out.println("Error");
        }
      }
      else
      {
        System.out.println("Error");
      }
    }
    return 500;
  }
}
