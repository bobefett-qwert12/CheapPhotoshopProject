package pixLab.classes;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  public void zeroBlue(int startCol, int startRow)
  {
	  Pixel[][] pixels = this.getPixels2D();
	    for (int row = startRow; row < pixels.length; row++)
	    {
	      for (int col = startCol; col < pixels[0].length; col++)
	      {
	        pixels[row][col].setBlue(0);
	      }
	    }
  }
  
  public void zeroRed()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(0);
      }
    }
  }
  
  public void zeroRed(int startCol, int startRow)
  {
	  Pixel[][] pixels = this.getPixels2D();
	    for (int row = startRow; row < pixels.length; row++)
	    {
	      for (int col = startCol; col < pixels[0].length; col++)
	      {
	        pixels[row][col].setRed(0);
	      }
	    }
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  public void mirrorHorizontal()
  {
	  Pixel[][] pixels = this.getPixels2D();
	  Pixel upPixel = null;
	  Pixel downPixel = null;
	  int height  = pixels.length;
	  for (int col = 0; col < pixels[0].length; col++) {
		  for (int row = 0; row < height/2; row++) {
			  upPixel = pixels[row][col];
			  downPixel = pixels[height - 1 - row][col];
			  downPixel.setColor(upPixel.getColor());
		  }
	  }
  }
  
  public void mirrorDiagonal()
  {
	  Pixel[][] pixels = this.getPixels2D();
	  Pixel upPixel = null;
	  Pixel downPixel = null;
	  int length = pixels.length;
	  int height = pixels[0].length;
	  for (int row = 0; row < length; row++) {
		  for (int col = row; col < height; col++) {
			  if(col < length && row < height) {
				  upPixel = pixels[row][col];
				  downPixel = pixels[col][row];
				  downPixel.setColor(upPixel.getColor());
			  }
		  }
	  }
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  public void mirrorGull() {
	  Pixel beforePixel = null;
	  Pixel afterPixel = null;
	  Pixel[][] pixels = this.getPixels2D();
	  for (int row = 0; row < pixels.length; row++) {
		  for (int col = 0; col < pixels[0].length; col++) {
			  beforePixel = pixels[row][col];
			  for (int i = 0; i < 120; i++) {
				  for (int j = 0; j < 100; j++) {
					  afterPixel = pixels[i + 230][j + 230];
					  if ((row % 120 == i) && (col % 100 == j)) {
						  beforePixel.setColor(afterPixel.getColor());
					  }
				  }
			  }
		  }
	  }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  public void glitchThis()
  {
	  this.mirrorVertical();
	  this.shiftLeftRight(-82);
	  this.mirrorVertical();
	  this.shiftLeftRight(-20);
	  this.mirrorVertical();
	  this.mirrorHorizontal();
	  this.shiftUpDown(134);
	  this.mirrorHorizontal();
	  this.shiftUpDown(95);
	  this.mirrorDiagonal();
	  this.zeroRed(200, 20);
	  this.zeroBlue(20, 200);
  }
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  public void hidePicture(Picture hidden)
  {
	  Pixel[][] pixels = this.getPixels2D();
	  Pixel[][] hiddenPixels = hidden.getPixels2D();
	  
	  for (int row = 0; row < pixels.length && row < hiddenPixels.length; row++) {
		  for (int col = 0; col < pixels[0].length && row < hiddenPixels.length; col++) {
			  if (hiddenPixels[row][col].colorDistance(Color.WHITE) > 5)
			  {
				  if(pixels[row][col].getRed() > 0 && pixels[row][col].getRed() % 2 != 1)
				  {
					  pixels[row][col].setRed(pixels[row][col].getRed() - 1);
				  }
			  }
			  else if (pixels[row][col].getRed() > 0 && pixels[row][col].getRed() % 2 == 1)
			  {
				  pixels[row][col].setRed(pixels[row][col].getRed() - 1);
			  }
		  }
	  }
  }
  
  public void revealPicture()
  {
	  Pixel[][] pixels = this.getPixels2D();
	  
	  for (int row = 0; row < pixels.length; row++) {
		  for (int col = 0; col < pixels[0].length; col++) {
			  if(pixels[row][col].getRed() > 0 && pixels[row][col].getRed() % 2 != 1)
			  {
				  pixels[row][col].setColor(Color.RED);
			  }
			  else if(pixels[row][col].getRed() > 0 && pixels[row][col].getRed() % 2 == 1)
			  {
				  pixels[row][col].setColor(Color.BLUE);
			  }
		  }
	  }
  }
  
  public void shiftLeftRight(int amount)
  {
	  Pixel[][] pixels = this.getPixels2D();
	  Picture temp = new Picture (this);
	  Pixel[][] copied = temp.getPixels2D();
	  
	  int shiftedValue = amount;
	  int width = pixels[0].length;
	  
	  for (int row = 0; row < pixels.length; row++)
	  {
		  for (int col = 0; col < pixels[0].length; col ++)
		  {
			  shiftedValue = (col+amount) % width;
			  if(amount < 0)
			  {
				  shiftedValue = ((col+amount) % width + width) % width;
			  }
			  copied[row][col].setColor(pixels[row][shiftedValue].getColor());
		  }
	  }
	  for (int row = 0; row < pixels.length; row++)
	  {
		  for(int col = 0; col < pixels[0].length; col++)
		  {
			  pixels[row][col].setColor(copied[row][col].getColor());
		  }
	  }
  }
  
  public void shiftUpDown(int amount)
  {
	  Pixel[][] pixels = this.getPixels2D();
	  Picture temp = new Picture (this);
	  Pixel[][] copied = temp.getPixels2D();
	  
	  int shiftedValue = amount;
	  int height = pixels.length;
	  
	  for (int row = 0; row < pixels.length; row++)
	  {
		  for (int col = 0; col < pixels[0].length; col ++)
		  {
			  shiftedValue = (row+amount) % height;
			  if(amount < 0)
			  {
				  shiftedValue = ((row+amount) % height + height) % height;
			  }
			  copied[row][col].setColor(pixels[shiftedValue][col].getColor());
		  }
	  }
	  for (int row = 0; row < pixels.length; row++)
	  {
		  for(int col = 0; col < pixels[0].length; col++)
		  {
			  pixels[row][col].setColor(copied[row][col].getColor());
		  }
	  }
  }
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("koala.jpg");
    beach.explore();
    beach.glitchThis();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
