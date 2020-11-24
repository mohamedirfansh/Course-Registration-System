package entities;

/**
 * EraserThread defines the methods that enable masking of user input, for cases of entering sensitive information.
 *
 * Class Attributes:
 * -> stop: boolean, whose truth value inducates the status of masking.
 *    If true, the input will be masked.
 *    If false, the input masking stops.
 */
class EraserThread implements Runnable {
   private boolean stop;

   /**
    * @param prompt, which is the prompt displayed to the user.
    *                In this case specifically, prompt = "Enter password: ", called from PasswordField class
    */
   public EraserThread(String prompt) {
      System.out.print(prompt);
   }

   /**
    * Begin masking...display asterisks (*) in place of any input character by the user.
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
    * stopMasking() is used to instruct the thread to stop masking.
    * It does so by setting the attribute "stop" to false.
    */
   public void stopMasking() {
      this.stop = false;
   }
}
