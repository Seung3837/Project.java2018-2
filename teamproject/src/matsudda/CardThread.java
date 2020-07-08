package matsudda;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CardThread extends JLabel implements Runnable {

   private int nStart, nEnd;
   private int nDelayTime;
   private int number;
   private ImageIcon deck;
   private Thread myThread;

   public CardThread() {
      nStart = nEnd = 0;
      nDelayTime = 1000;
      number = 1;
      myThread = null;
   } // LabelThread()

   public CardThread(ImageIcon back) {
      super(back);
      nStart = nEnd = 0;
      nDelayTime = 10;
      number = 1;
      myThread = null;
   } // LabelThread()

   public int getStart() {
      return nStart;
   }

   public int getEnd() {
      return nEnd;
   }

   public int getnumber() {
      return number;
   }

   public int getDelayTime() {
      return nDelayTime;
   }

   public void setStart(int start) {
      nStart = start;
   }

   public void setEnd(int end) {
      nEnd = end;
   }

   public void setnumber(int num) {
      number = num;
   }

   public void setDelayTime(int delay) {
      nDelayTime = delay;
   }

   public void start() {
      if (myThread == null) {
         myThread = new Thread(this);
      } // if
      else if (myThread != null) {
         myThread = new Thread(this);
      }
      myThread.start();
   } // start()

   public void stop() {
      if (myThread != null) {
         myThread.stop();
      } // if
   } // stop()

   @Override
   public void run() {
      // TODO Auto-generated method stub

      if (number == 1) { // 패 이동 1
         for (int i = 0; i <= 70; i++) {
            setBounds(450 - i, 295 + i * 3, 115, 180);

            if (i == 70) {
               setVisible(false);
               setBounds(450, 295, 115, 180);
               setVisible(true);
            }
            try {
               myThread.sleep(nDelayTime);
            } catch (Exception e) {
            }
         }
      } else if (number == 2) { // 패 이동 2
         try {
            myThread.sleep(1000);
         } catch (Exception e) {
         }

         for (int i = 0; i <= 70; i++) {
            setBounds(450 + i, 295 + i * 3, 115, 180);

            if (i == 70) {
               setVisible(false);
               setBounds(450, 295, 115, 180);
               setVisible(true);
            }
            try {
               myThread.sleep(nDelayTime);
            } catch (Exception e) {
            }
         }
      } else if (number == 3) { // 패 이동 3
         try {
            myThread.sleep(2000);
         } catch (Exception e) {
         }

         for (int i = 0; i <= 70; i++) {
            setBounds(450 - i, 295 - i * 3, 115, 180);

            if (i == 70) {
               setVisible(false);
               setBounds(450, 295, 115, 180);
               setVisible(true);
            }
            try {
               myThread.sleep(nDelayTime);
            } catch (Exception e) {
            }
         }
      } else if (number == 4) { // 패 이동 4
         try {
            myThread.sleep(3000);
         } catch (Exception e) {
         }

         for (int i = 0; i <= 70; i++) {
            setBounds(450 + i, 295 - i * 3, 115, 180);

            if (i == 70) {
               setVisible(false);
               setBounds(450, 295, 115, 180);
               setVisible(true);
            }
            try {
               myThread.sleep(nDelayTime);
            } catch (Exception e) {
            }

         }
      } else if (number == 5) { 
         for (int i = 0; i <= 70; i++) {
            if (i == 70)
               setVisible(true);
            try {
               myThread.sleep(nDelayTime);
            } catch (Exception e) {
            }

         }
      } else if (number == 6) { 
         try {
            myThread.sleep(1000);
         } catch (Exception e) {
         }
         
         for (int i = 0; i <= 70; i++) {
            if (i == 70)
               setVisible(true);
            try {
               myThread.sleep(nDelayTime);
            } catch (Exception e) {
            }

         }

      } else if (number == 7) { 
         try {
            myThread.sleep(2000);
         } catch (Exception e) {
         }
         
         
         for (int i = 0; i <= 70; i++) {
            if (i == 70)
               setVisible(true);
            try {
               myThread.sleep(nDelayTime);
            } catch (Exception e) {
            }

         }
      } else if (number == 8) { // 유저의 패
         try {
            myThread.sleep(3000);
         } catch (Exception e) {
         }
         
         
         for (int i = 0; i <= 70; i++) {
            if (i == 70)
               setVisible(true);
            try {
               myThread.sleep(nDelayTime);
            } catch (Exception e) {
            }

         }
      }
   }
}