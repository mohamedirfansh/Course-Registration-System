package entities;

import java.io.*;

class EraserThread implements Runnable {
   private boolean stop;

   /**
    *@param: The prompt displayed to the user
    * In this case specifically, prompt = "Enter password: ", called from PasswordField class
    */
   public EraserThread(String prompt) {
       System.out.print(prompt);
   }

   /**
    * Begin masking...display asterisks (*) in place of any input character by the user
    */
   public void run () {
      stop = true;
      while (stop) {
         System.out.print("\b*");
	 try {
	    Thread.currentThread().sleep(1);
         } catch(InterruptedException ie) {
            ie.printStackTrace();
         }
      }
   }

   /**
    * Instruct the thread to stop masking
    */
   public void stopMasking() {
      this.stop = false;
   }
}
