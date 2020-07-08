package matsudda;

import java.util.Random;

public class Card {
   private int card1, card2;
   private int priority;

   public Card() {
      Random random = new Random();
      card1 = random.nextInt(20);
      card2 = random.nextInt(20);
      while (card2 == card1) {
         card2 = random.nextInt(20);
      }

      if(card2<card1) {
         int temp = card2;
         card2 = card1;
         card1 = temp;
      }
      
      // priority 설정
   } // Card()

   public Card(int userCard1, int userCard2) {
      Random random = new Random();
      card1 = random.nextInt(20);
      while (card1 == userCard1 || card1 == userCard2) {
         card1 = random.nextInt(20);
      }
      card2 = random.nextInt(20);
      while (card2 == card1 || card2 == userCard1 || card2 == userCard2) {
         card2 = random.nextInt(20);
      }
      
      if(card2<card1) {
         int temp = card2;
         card2 = card1;
         card1 = temp;
      }

      // priority 설정

   } // Card()

   public void setCard() {
      Random random = new Random();
      card1 = random.nextInt(20);
      card2 = random.nextInt(20);
      while (card2 == card1) {
         card2 = random.nextInt(20);
      }
      
      if(card2<card1) {
         int temp = card2;
         card2 = card1;
         card1 = temp;
      }
   } // setCard()

   public void setCard(int userCard1, int userCard2) {
      Random random = new Random();
      card1 = random.nextInt(20);
      while (card1 == userCard1 || card1 == userCard2) {
         card1 = random.nextInt(20);
      }
      card2 = random.nextInt(20);
      while (card2 == card1 || card2 == userCard1 || card2 == userCard2) {
         card2 = random.nextInt(20);
      }
      
      if(card2<card1) {
         int temp = card2;
         card2 = card1;
         card1 = temp;
      }
      
   } // setCard()

   public int getCard1() {
      return card1;
   }

   public int getCard2() {
      return card2;
   }

   public int getPriority() {
      return priority;
   }
} // Card class