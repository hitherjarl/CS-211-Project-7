import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Island
{
  private GridDisplay disp;
  private Creature[][] grid;
  private int dayCount;
  private int rows;
  private int cols;
  
  public Island ()
  {
    rows = 20;
    cols = 20;
    
    disp = new GridDisplay(rows, cols);
    grid = new Creature[rows][cols];
    
    for (int i = 0 ; i < rows ; i++)
      for (int j = 0 ; j < cols ; j++)
    {
      grid[i][j] = null;
      disp.setColor(i, j, Color.WHITE);
    }
    
    dayCount = 0;
  }
  
  public int getRows()
  {
    return rows;
  }
  
  public int getCols()
  {
    return cols;
  }
  
  public boolean isOccupied (int i, int j)
  {
    return (grid[i][j] != null);
  }
  public maps check(int x, int y, Color myFood)
  {
    maps[] array = new maps[8];
    Creature b;
    int currX;
    int currY;
    array[0] = new maps(x-1, y-1);
    array[1] = new maps(x-1, y);
    array[2] = new maps(x-1, y+1);
    array[3] = new maps(x, y-1);
    array[4] = new maps(x, y+1);
    array[5] = new maps(x+1, y-1);
    array[6] = new maps(x+1, y);
    array[7] = new maps(x+1, y+1);
    
    for(int r = 7; r > 0; r--)
    {
      int k = 0;
      if (r > 1)
        k = (int)(Math.random() * r);
      currX = array[k].x;
      currY = array[k].y;
      if (!(currX < 0 || currX >= cols || currY < 0 || currY >= rows))
      {
        b = grid[currX][currY];
        if (b == null && myFood == Color.WHITE)
          return array[k];
        if (b != null && b.getColor() == myFood)
          return array[k];
      }
      for(int i = k; i <= r - 1; i++)
        array[i] = array[i+1];
    }
    return null;
  }
  
  public boolean nextDay () 
  {
    boolean changes = false;
    dayCount++;
    System.out.println ("DayCount: " + dayCount);
    
    for (int i = 0 ; i < rows ; i++)
      for (int j = 0 ; j < cols ; j++)
    {
      Creature b = grid[i][j];
      if (( b != null)  && (b.getColor() == Color.BLUE) && (b.getDayLastMoved() != dayCount))  //green is check for doodlebug
      {
        b.setDayLastMoved (dayCount);
        boolean success = b.move(null);
        changes = changes || success;
        b.setDayLastMoved (dayCount);
      }
    }
    for (int i = 0 ; i < rows ; i++)
      for (int j = 0 ; j < cols ; j++)
    {
      Creature b = grid[i][j];
      if ((b != null) && (b.getColor() == Color.RED) && (b.getDayLastMoved() != dayCount))  //red is check for Ant
      {
        b.setDayLastMoved (dayCount);
        boolean success = b.move(null);
        changes = changes || success;
      }
    }
    return changes;
  }
  
  public boolean addCreature (Creature b, int x, int y)
  {
    // make sure x and y are valid
    if (x < 0 || x >= cols || y < 0 || y >= rows)
      return false;
    
    // make sure no beetle is already at that space
    if ( isOccupied(x, y))
      return false;
    
    grid[x][y] = b;
    //System.out.println ("Creature at: " + x + ", " + y);
    disp.setColor (x, y, b.getColor());
    return true;
  }
  
  public void moveCreature (int currX, int currY, int nextX, int nextY)
  {
    grid[nextX][nextY] = grid[currX][currY];
    grid[currX][currY] = null;
    Creature b = grid[nextX][nextY];
    disp.setColor(nextX,nextY, b.getColor());
    disp.setColor(currX,currY, Color.WHITE);
  }
   
  public void remove(int x, int y)
  {
    grid[x][y] = null;
    disp.setColor(x,y, Color.WHITE);
    System.out.println ("DoodleBug died");
  }
}

class GridDisplay extends JFrame
{
  private JLabel labels[];
  
  private Container container;
  private GridLayout grid1;
  int rowCount;
  int colCount;
  
  // set up GUI
  public GridDisplay(int rows, int cols)
  {
    super( "GridDisplay for CS211" );
    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    
    // set up grid layout struture of the display
    rowCount = rows;
    colCount = cols;
    grid1 = new GridLayout( rows, cols );
    container = getContentPane();
    container.setLayout( grid1 );
    
    // create and add buttons
    labels = new JLabel[ rows * cols ];
    
    for ( int count = 0; count < labels.length; count++ ) {
      labels[ count ] = new JLabel( " " );
      labels[count].setOpaque(true);
      container.add( labels[ count ] );
    }
    
    // set up the size of the window and show it
    setSize( cols * 15 , rows * 15 );
    setVisible( true );
    
  } // end constructor GridLayoutDemo
  
  // display the given char in the (row,col) position of the display
  public void setChar (int row, int col, char c)
  {
    if ((row >= 0 && row < rowCount) && (col >= 0 && col < colCount) )
    {
      int pos = row * colCount + col;
      labels [pos].setText("" + c);  
    }
  }
  
  // display the given color in the (row,col) position of the display
  public void setColor (int row, int col, Color c)
  {
    if ((row >= 0 && row < rowCount) && (col >= 0 && col < colCount) )
    {
      int pos = row * colCount + col;
      labels [pos].setBackground(c);  
    }
  }
  
  // puts the current thread to sleep for some number of milliseconds to allow for "animation"
  public static void mySleep( int milliseconds)
  {
    try
    {
      Thread.sleep(milliseconds);
    }
    catch (InterruptedException ie)
    {
    }
  }
}// end class GridDisplay

class maps
{
  public int x;
  public int y;
  public maps(int x, int y)
  {
    this.x = x;
    this.y = y;
  }
}