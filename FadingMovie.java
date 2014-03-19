/**
 * Auto Generated Java Class.
 */
import java.awt.Color;

public class FadingMovie {
  
  
  public static void main(String[] args) { 
  //choose the starting picture  
 String element1 = FileChooser.pickAFile();
 Picture startPicture2 = new Picture(element1);
 //choose the ending picture
 String element2 = FileChooser.pickAFile();
 Picture endPicture2 = new Picture(element2);
 
  //check if the pictures have the same height AND width
 if((startPicture2.getWidth() != endPicture2.getWidth()) ||
    (startPicture2.getHeight() != endPicture2.getHeight())){
   //if the pictures are not the same size - print error message
   SimpleOutput.showInformation("Please pick two pictures that are the same height and width!");   
 }
 
 else{
   //elseif content - this is the main program
   int numStages = SimpleInput.getIntNumber("Please enter the number of stages.");
   String directoryName = SimpleInput.getString("Please enter a directory name ending with a slash");
  //select the desired color
   Color fadeColor = new Color(0, 0, 255);
   
   //setup the framesequencer
   FrameSequencer frameSequencer = new FrameSequencer(directoryName); 
   
   //create an array
   Picture[] pictureSequence = new Picture[(2*numStages) + 1];
 
   //Determine where to put the start and end picture
   pictureSequence[0] = startPicture2; 
   pictureSequence[2*numStages] = endPicture2;
      
   //Make the intermediate pictures for the array
      for(int i = 1; i< numStages; i++) 
      { 
        //intermediate set
        Picture image_i = new Picture(startPicture2); 
        image_i.fadeOut(startPicture2, fadeColor, numStages, i);
        pictureSequence[i] = image_i; 
      }
      
      for(int i=0;i<numStages; i++)
      {
       Picture image1_i = new Picture(startPicture2);
       image1_i.fadeIn(endPicture2, fadeColor, numStages, i);
       pictureSequence[numStages+i] = image1_i;
      }
      
      //Adds the array to the directory
      for (int i = 0; i < pictureSequence.length; i++) {
        frameSequencer.addFrame(pictureSequence[i]); 
      } 
      
      //Creates and plays the first movie 
      MoviePlayer firstMovie = new MoviePlayer(directoryName);
      firstMovie.playMovie(numStages); 
      
     // Adds the pictures to the end of array backwards  
      for (int i = pictureSequence.length-1; i>=0; i--) {
        
        //then stores the pictures
     frameSequencer.addFrame(pictureSequence[i]); } 
      
      //Creates and plays the second movie 
      String directoryName2 = SimpleInput.getString("Please enter a directory name ending with a slash");
      MoviePlayer secondMovie = new MoviePlayer(directoryName); 
      secondMovie.playMovie();    
 
   }
  }
}

  
