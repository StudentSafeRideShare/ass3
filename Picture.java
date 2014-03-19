import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.text.*;
import java.awt.Color;

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * Copyright Georgia Institute of Technology 2004-2005
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
   * @param width the width of the desired picture
   * @param height the height of the desired picture
   */
  public Picture(int width, int height)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  ////////////////////// methods ///////////////////////////////////////

 //This is the fade out method 
  public void fadeOut(Picture startPicture, Color fadeTo, int numStages, int k)
{
   //This is the color I have chose - dark red
   Color finalColor = new Color(245, 10, 10);
   int x=0;
   int y=0;
   //here we specific the individual RGB colors for any given pixel
   int endRed=245;
   int endGreen=10;
   int endBlue=10;
   
   
   //loop through the pixels in the x direction
   for (x=0; x<this.getWidth(); x++){
     //loop through the pixels in the y direction
         for(y=0; y<this.getHeight(); y++){
           //this.get.pixel for x,y
              Pixel pixelObj=this.getPixel(x,y);
              //get the red component of the pixel
           int startRed=pixelObj.getRed();
           //use the algorithm to change the red component of the pixel
           int redValue=startRed+((endRed-startRed)/4)*k;
           //set the red component of the pixel
           pixelObj.setRed(redValue);
           //get the blue component of the pixel
           int startBlue=pixelObj.getBlue();
           //use the algorithm to change the blue component of the pixel
           int blueValue=startBlue+((endBlue-startBlue)/4)*k;
           //set the blue component of the pixel
           pixelObj.setBlue(blueValue);
           //get the green component of the pixel
           int startGreen=pixelObj.getGreen();
           //use the algorithm to change the green component of the pixel
           int greenValue=startGreen+((endGreen-startGreen)/4)*k;
           //set the green component of the pixel
           pixelObj.setGreen(greenValue);
     //}
   }
}
}
  //////////////////////////////////////////////////////////////////////////////////
  
   //This is the fade in method 
  public void fadeIn(Picture endPicture, Color fadeFrom, int numStages, int k)
{
   //This is the color I have chose - dark red
   Color finalColor2 = new Color(245, 10, 10);
   int x=0;
   int y=0;
   //here we specific the individual RGB colors for any given pixel
   int endRed=245;
   int endGreen=10;
   int endBlue=10;
   
   
   //loop through the pixels in the x direction
   for (x=0; x<this.getWidth(); x++){
     //loop through the pixels in the y direction
         for(y=0; y<this.getHeight(); y++){
           //this.get.pixel for x,y
              Pixel pixelObj=this.getPixel(x,y);
              //get the red component of the pixel
           int startRed=pixelObj.getRed();
           //use the algorithm to change the red component of the pixel
           int redValue=endRed+((startRed-endRed)/4)*k;
           //set the red component of the pixel
           pixelObj.setRed(redValue);
           //get the blue component of the pixel
           int startBlue=pixelObj.getBlue();
           //use the algorithm to change the blue component of the pixel
           int blueValue=endBlue+((startBlue-endBlue)/4)*k;
           //set the blue component of the pixel
           pixelObj.setBlue(blueValue);
           //get the green component of the pixel
           int startGreen=pixelObj.getGreen();
           //use the algorithm to change the green component of the pixel
           int greenValue=endGreen+((startGreen-endGreen)/4)*k;
           //set the green component of the pixel
           pixelObj.setGreen(greenValue);
     //}
   }
}
}
  
  
  
  
  ///////////////////////////////////////////////////////////////////////////////  
  
  public Picture blur(int numPixels) {
 
    //create a new Picture object that is the same size
  Picture targetPicture = new Picture(this.getWidth(), this.getHeight());
  
  //Pixel sourcePixel = null;
  Pixel targetPixel = null;
  Pixel samplePixel = null;
  
  //these variables are used to calculate an average red, green and blue value for the target Pixel.  
  //Recall that an average is the sum of all values divided by the number of values (ie. count)
  int redSum=0;
  int greenSum=0;
  int blueSum=0;
  int count=0;
  
  //loop through all pixels;
  for ( int x=0; x < this.getWidth();x++) {
    for ( int y=0; y < this.getHeight();y++) {
      
      //reset the count - and red, green and blue sums - to zero
      //as we are starting a new averaging calcuation
      count = 0;
      redSum = 0;
      greenSum = 0;
      blueSum = 0;
      
      // loop through pixels in a box surrounding the current x,y position
      // the box begins numPixels before and after x, and numPixels before and after y
      for ( int xSample = x - numPixels; xSample <= x + numPixels; xSample++) {
        for (int ySample = y - numPixels; ySample <= y + numPixels; ySample++) {
           
          //at some x,y positions our surrounding box may go outside the image
          //Here we check to make sure we are outside this Picture
          if ( xSample >= 0 && xSample < this.getWidth() && ySample >=0 && ySample < this.getHeight() ) {
            
            samplePixel = this.getPixel(xSample, ySample);
            //we have another valid pixel, so increase count
            count++;
            
            //add to the running sums of red, green and blue values
            redSum = redSum + samplePixel.getRed();
            greenSum = greenSum + samplePixel.getGreen();
            blueSum = blueSum + samplePixel.getBlue();
            
          } // end of if body
        } // end of for ySample body
      } // end of for xSample body
      
      //set the current pixel in the target to the average of the box
      targetPixel = targetPicture.getPixel(x,y);
      targetPixel.setRed( redSum / count );
      targetPixel.setGreen( greenSum / count );
      targetPixel.setBlue( blueSum / count );
      
    } //end of for y body
  } // end of for x body
  return targetPicture;
}
    
  
  
  
  
  
  
    //End of the Fade Out for a picture to a color - end of code//
   
  
  //copyExceptWhite method
  public void copyExceptWhite(Picture sourcePicture, int xStart, int yStart)
 {
   Pixel sourcePixel = null;
   Pixel targetPixel = null;
   
   //loop through the columns
   try{
   for (int sourceX = 0, targetX = xStart;
        sourceX < sourcePicture.getWidth();
        sourceX++, targetX++)
   {
     //loop through the rows
     for (int sourceY = 0,
          targetY = yStart;
          sourceY < sourcePicture.getHeight();
          sourceY++, targetY++)
     {
       sourcePixel = sourcePicture.getPixel(sourceX,sourceY);
       targetPixel = this.getPixel(targetX,targetY);
       targetPixel.setColor(sourcePixel.getColor());
     } 
   }
  }catch(IndexOutOfBoundsException ex){System.out.println("Either xStart or yStart is out of bounds");System.exit(0);}
} 
  
  
  //end of copyExceptWhite method
 





// verticalMirror method

    
    
    //left to right
    

      public void mirrorVerticalRightToLeft() { 
      int mirrorPoint = this.getWidth()/2; 
      Pixel leftPixel = null; Pixel rightPixel = null; 
// loop through the rows 
      for (int y = 0; y < this.getHeight(); y++) { 
// loop from column 0 to just before the mirror point 
        for (int x = 0; x < mirrorPoint; x++) { 
          rightPixel = this.getPixel(this.getWidth()-1-x, y); 
          leftPixel = this.getPixel(x,y); 
          leftPixel.setColor(rightPixel.getColor()); 
        } 
      } 
    }
  
  
/////////////////////////////////////////////////////////////////////////
//This is the start of the first JAVA method - WHILE LOOP - INCREASE BLUE
/////////////////////////////////////////////////////////////////////////
  public void increaseBlue()
  {
    Pixel[] pixelArray = this.getPixels();
    Pixel pixelObj = null;
    int index = 0;
    int value = 0;
    
    //This loops the pixels so that we can modify as many as we would like
    
    while(index<pixelArray.length)
    {
      //We get the current pixel we are at
      pixelObj = pixelArray[index];
      
      //get the blue value
      value = pixelObj.getBlue();
      
      //double the blue pixel value
      value = value * 2;
      
      //set this pixels blue value after modification
      pixelObj.setBlue(value);
      
     //then increase the index value
      index = index + 1;
    }     
  }
  ///////////////////////////////////////////////////////////////////////
  //This is the end of the first JAVA method - WHILE LOOP - INCREASE BLUE
  ///////////////////////////////////////////////////////////////////////
  
  
  
  /////////////////////////////////////////////////////////
  //This is the start of the second  JAVA method - FOR LOOP - GRAYSCALE
  /////////////////////////////////////////////////////////
  
  public void grayscale()
  {
    Pixel[] pixelArray = this.getPixels();
    //This sets us at the null value  - start from scratch
    Pixel pixelObj = null;
    int intensity = 10;
    
    //loop through the pixels
    for(int i = 0; i< pixelArray.length; i++)
    {
      //get the current pixel we are on
      pixelObj = pixelArray[i];
      
      //we then average the pixels which when equivalnet will produce the grayscale effect
      intensity = (pixelObj.getRed() + pixelObj.getBlue() + pixelObj.getGreen())/3;
      
      //set the new pixel colour
      pixelObj.setColor(new Color(intensity,intensity,intensity));
    }
  }  
  
  /////////////////////////////////////////////////////
  //This is the end of the secon JAVA method - FOR LOOP - GRAYSCALE
  /////////////////////////////////////////////////////
  
  ///////////////////////////////////////////////////
  //This is a method that allows us to descrese or increase colours//
  //by a value other than half, using a double for data type//
  //////////////////////////////////////////////////
  
  public void descreaseRed5()
  {
    Pixel[] pixelArray = this.getPixels();
    Pixel pixelObj = null;
    int index = 0;
    int value = 0;
    
    //loop through all the pixels
    
    while(index< pixelArray.length)
    {
      //get the current pixel value
      pixelObj = pixelArray[index];
      
      //get the red value
      value = pixelObj.getRed();
      
      //descrease the red
      value = (int) (value*0.5);
      
      //set the pixels red value
      pixelObj.setRed(value);
      
      //increment the index
      index ++;
    }
  }
  
  ///////////////////////////////////////////////////
  //This method is the new descrease red
  
  public void changeRed(double howMuch)
  {
   Pixel[] pixelArray = this.getPixels();
   Pixel pixelObj = null;
   //double index = 0;
   int value = 0;
   
   //loop through all the pixels
    for (int index = 0; index < pixelArray.length; index ++)
    {
        //get the current pixel value
      pixelObj = pixelArray[index];
      
      //get the red value
      value = pixelObj.getRed();
      
      //descrease the red
      value = (int) (value*howMuch);
      
      //set the pixels red value
      pixelObj.setRed(value);
      
      //increment the index
    }
  }
  ///////////////////////////////////////////////////
  //This is the second clear blue method
  ////////////////////////////////////////////////////
  
  public void clearBlue2()
  {
    Pixel pixelObj;
    //loop through the columns(x direction)
    for(int x = 0; x < this.getWidth(); x++)
    {
      //loop through the rows(y direction)
      for(int y = 0; y < this.getHeight(); y++)
    {
        //get the pxiel at the x and y location
        pixelObj = this.getPixel(x,y);
        
        //set its blue to 0
        pixelObj.setBlue(0);
      }
      //End of the inner loop
    }
    //end of the outer loop
  }
  
  
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
  
  //While loop method to draw the pattern of an 'X'
  
  public void decreaseRed()
  {
    Pixel[] pixelArray = this.getPixels();
    Pixel pixelObj = null;
    int index = 0;
    int value = 0;
    
    //loop through all the pixels
    while(index<pixelArray.length)
    {
      //get the current pixel
      pixelObj = pixelArray[index];
      //get the red value
      value = pixelObj.getRed();
      //decrease the red value
      value = (int)(value*0.5);
      //set the pixels red value
      pixelObj.setRed(value);
      //increment the index
      index++;
    }
  }

  /*****************************************************************
   * Method to mirror a piece of the temple
   *****************************************************************/
  public void mirrorTemple(){
    //Declare local variables
    int mirrorPoint = 276;
    Pixel leftPix = null;
    Pixel rightPix = null;
    
    //Loop through all rows, where the temple must be mirrored
    for (int y=27;y<97;y++){
      for (int x=13; x<mirrorPoint; x++){
        leftPix = getPixel(x,y); //get left pixel (good pixel)
        //Get right pixel (the mirror location)
        rightPix = getPixel(mirrorPoint + (mirrorPoint-x),y);
        //Swap the colours
        rightPix.setColor(leftPix.getColor());
      }
    }
  }
  

  /*****************************************************************
   * Mirror a picture vertically
   *****************************************************************/
  public void mirrorVertical(){
    //Declare local variables
    int width = this.getWidth();
    int mirrorPoint = width/2;
    Pixel leftPix = null;
    Pixel rightPix = null;
    
    //Loop through all rows, only half way
    for (int y=0;y<this.getHeight();y++){
      for (int x=0; x<mirrorPoint; x++){
        leftPix = getPixel(x,y);
        rightPix = getPixel(width-1-x,y);
        rightPix.setColor(leftPix.getColor()); //Swap colours of left and right
      }
    }  
  }
  
  
  /*****************************************************************
   * Method to make a picture grayscale
   *****************************************************************/
  public void goodGrey(){
    //Declare local variables; get an array of pixels out of the picture
    Pixel[] pixelArray = this.getPixels();
    Pixel pix = null;
    int luminance = 0;
    double redV = 0;
    double blueV= 0;
    double greenV= 0;
    
    //Loop through all pixels in the array
    for (int i=0; i<pixelArray.length;i++){
      pix = pixelArray[i];
      redV = pix.getRed() * 0.299;
      blueV = pix.getBlue() * 0.587;
      greenV = pix.getGreen() * 0.114;
      luminance = (int)(redV + greenV + blueV);
      //Change pixel colour to the new colour created using luminance
      pix.setColor(new Color(luminance, luminance, luminance));
    }
  }
  
/***************************************
** Copy picture ***
****************************************/

public void copyPicture (Picture sourcePicture) 
{ 
   Pixel sourcePixel = null; 
   Pixel targetPixel = null; 
   // loop through the columns 
   for (int sourceX = 0, targetX = 0; 
        sourceX < sourcePicture.getWidth(); 
sourceX++, targetX++)    
   {    // loop through the rows 
     for (int sourceY = 0, targetY = 0; 
          sourceY < sourcePicture.getHeight(); 
sourceY++, targetY++)    
     { 
       // set the target pixel color to the source pixel color 
       sourcePixel = sourcePicture.getPixel(sourceX,sourceY); 
       targetPixel = this.getPixel(targetX,targetY); 
       targetPixel.setColor(sourcePixel.getColor()); 
     } 
   } 
 }


/**
  * Modified version of method from page 154 of the textbook for copying pictures
  */

 public void copyPictureTo(Picture sourcePicture, int xStart, int yStart)
 {
   Pixel sourcePixel = null;
   Pixel targetPixel = null;
   
   //loop through the columns
   try{
   for (int sourceX = 0, targetX = xStart;
        sourceX < sourcePicture.getWidth();
        sourceX++, targetX++)
   {
     //loop through the rows
     for (int sourceY = 0,
          targetY = yStart;
          sourceY < sourcePicture.getHeight();
          sourceY++, targetY++)
     {
       sourcePixel = sourcePicture.getPixel(sourceX,sourceY);
       targetPixel = this.getPixel(targetX,targetY);
       targetPixel.setColor(sourcePixel.getColor());
     } 
   }
  }catch(IndexOutOfBoundsException ex){System.out.println("Either xStart or yStart is out of bounds");System.exit(0);}
} 


/****************************
Crop picture
*****************************/
public void copyPictureTo(Picture sourcePicture, int startX, int startY,  
                           int endX, int endY, int targetStartX, int targetStartY) 
  { 
    Pixel sourcePixel = null; 
    Pixel targetPixel = null; 
    // loop through the x values 
    for (int x = startX, tx = targetStartX;  x < endX && x < sourcePicture.getWidth() && tx < this.getWidth();  x++, tx++) 
    { 
      // loop through the y values 
      for (int y = startY, ty = targetStartY; y < endY && y < sourcePicture.getHeight() &&  ty < this.getHeight();  y++, ty++)  
      {

// copy the source color to the target color 
        sourcePixel = sourcePicture.getPixel(x,y); 
        targetPixel = this.getPixel(tx,ty); 
        targetPixel.setColor(sourcePixel.getColor()); 
      } 
    } 
  }
  
  /*****************************************************************
   * Method to make a negative of a picture
   *****************************************************************/
  public void negate(){
    //Local variables
    Pixel[] pixelArray = this.getPixels();
    Pixel pixel = null;
    int redV, blueV, greenV = 0;
    //Loop through all pixels in the picture
    for (int i=0; i<pixelArray.length;i++){
      pixel = pixelArray[i];
      redV=pixel.getRed();
      greenV=pixel.getGreen();
      blueV = pixel.getBlue();
      //Set the color to the negative of current color
      pixel.setColor(new Color(255-redV, 255-greenV, 255-blueV));
    }
  }
  
  
  /*****************************************************************
   * Method to create a fake sunset
   *****************************************************************/
  public void makeSunset(){
    //Create local variables and get array of pixels
    Pixel[] pixelArray = this.getPixels();
    Pixel pixel = null;
    int value = 0;
    int i=0;
    
    //Loop through all pixels
    while (i<pixelArray.length){
      pixel = pixelArray[i]; //get out current picture
      value = pixel.getBlue(); //get the blue value
      pixel.setBlue((int)(value*0.7)); //set a new blue
      value = pixel.getGreen(); //get the green value
      pixel.setGreen((int)(value*0.7)); //set the new green value
      i++; //increment counter
    }
  }
  
  
  /*****************************************************************
   * Method to decrease the red in a picture
   *****************************************************************/
  public void decreaseRed2(){
    //Declare variables and get an array with all the pixels
    Pixel[] pixelArray = this.getPixels();
    Pixel pixel = null;
    int value = 0;
    int index = 0;
    //Loop through all pixels
    while (index < pixelArray.length){
      pixel = pixelArray[index]; //get pixel at each spot in the array in turn
      value = pixel.getRed(); //get the red value
      value=(int)(value*0.25); //change the red value by half
      pixel.setRed(value); //set a new red value
      index= index + 1; //increment so next time you get new pixel
    }
  }
  
  
} // end of class Picture, put all new methods before this

 
