package matsudda;

import java.util.Random;

public class Calculation {

   protected int p1, p2;
   protected int winRating, chance;

   public Calculation() {
      p1 = 0;
      p2 = 0;
      winRating = 0;
      chance = 0;
   }

   public Calculation(int p1, int p2) {
      if (p1 > p2) {
         int temp;

         temp = p1;
         p1 = p2;
         p2 = temp;
      } // p1에 값이 더 작은 패가 오도록 변경


      System.out.println(p1+" "+p2+"패");
      
      if (p1 == 02 && p2 == 07) {
         winRating = 0;
      } // 38 광떙
      else if (p1 == 00 && (p2 == 02 || p2 == 07)) {
         winRating = 1;
      } // 13,18 광떙
      else if (p1 % 10 == p2 % 10) {
         winRating = 11 - p1 % 10; // 장땡 2 ~ 1땡 11
      } // 각종 광떙
      else if (p1 % 10 == 0 && p2 % 10 == 1) {
         winRating = 12;
      } // 알리
      else if (p1 % 10 == 0 && p2 % 10 == 3) {
         winRating = 13;
      } // 독사
      else if (p1 % 10 == 0 && p2 % 10 == 8) {
         winRating = 14;
      } // 구삥
      else if (p1 % 10 == 0 && p2 % 10 == 9) {
         winRating = 15;
      } // 장삥
      else if (p1 % 10 == 3 && p2 % 10 == 7) {
         winRating = 16;
      } // 장사
      else if (p1 % 10 == 5 && p2 % 10 == 8) {
         winRating = 17;
      } // 새륙
      else if (p1 % 10 == 3 && p2 % 10 == 8) {
         if (p1 == 3 && p2 == 8) {
            winRating = -1;
         } // 멍텅구리 구사
         else {
            winRating = -2;
         } // 구사
      } // 구사
      else if (p1 == 3 && p2 == 6) {
         winRating = -3;
      } // 암행어사 (광떙 없음 1끗)
      else if (p1 % 10 == 2 && p2 % 10 == 6) {
         winRating = -4;
      } // 떙잡이 3~11을 이겨야 함 (장땡은 못이기고 땡이 없음 망통)
      else if (p1 % 10 + p2 % 10 == 7) {
         winRating = 18;
      } // 갑오 9끗은 갑오라 부름
      else if (p1 % 10 + p2 % 10 == 8) {
         winRating = 27;
      } // 망통 (섯다에서 가장 낮은 패)
      else {
         p1++;
         p2++;
         winRating = (9 - (((p1 % 10) + (p2 % 10)) % 10)) + 18;
         // 1끗부터 8끗까지
         p1--;
         p2--;
      }

      System.out.println(winRating);
      
   }

   public void againCal(int p1, int p2) {
      if (p1 > p2) {
         int temp;

         temp = p1;
         p1 = p2;
         p2 = temp;
      } // p1에 값이 더 작은 패가 오도록 변경

      if (p1 == 02 && p2 == 07) {
         winRating = 0;
      } // 38 광떙
      else if (p1 == 00 && (p2 == 02 || p2 == 07)) {
         winRating = 1;
      } // 13,18 광떙
      else if (p1 % 10 == p2 % 10) {
         winRating = 11 - p1 % 10; // 장땡 2 ~ 1땡 11
      } // 각종 광떙
      else if (p1 % 10 == 0 && p2 % 10 == 1) {
         winRating = 12;
      } // 알리
      else if (p1 % 10 == 0 && p2 % 10 == 3) {
         winRating = 13;
      } // 독사
      else if (p1 % 10 == 0 && p2 % 10 == 8) {
         winRating = 14;
      } // 구삥
      else if (p1 % 10 == 0 && p2 % 10 == 9) {
         winRating = 15;
      } // 장삥
      else if (p1 % 10 == 3 && p2 % 10 == 7) {
         winRating = 16;
      } // 장사
      else if (p1 % 10 == 5 && p2 % 10 == 8) {
         winRating = 17;
      } // 새륙
      else if (p1 % 10 == 3 && p2 % 10 == 8) {
         if (p1 == 13 && p2 == 18) {
            winRating = -1;
         } // 멍텅구리 구사
         else {
            winRating = -2;
         } // 구사
      } // 구사
      else if (p1 == 13 && p2 == 16) {
         winRating = -3;
      } // 암행어사 (광떙 없음 1끗)
      else if (p1 % 10 == 2 && p2 % 10 == 6) {
         winRating = -4;
      } // 떙잡이 3~11을 이겨야 함 (장땡은 못이기고 땡이 없음 망통)
      else if (p1 % 10 + p2 % 10 == 7) {
         winRating = 18;
      } // 갑오 9끗은 갑오라 부름
      else if (p1 % 10 + p2 % 10 == 8) {
         winRating = 27;
      } // 망통 (섯다에서 가장 낮은 패)
      else {
         p1++;
         p2++;
         winRating = (9 - ((p1 % 10) + (p2 % 10) + 2) % 10) + 18;
         // 1끗부터 8끗까지
      }
   }

   public int winner(Calculation com) {
      // 임시로 승리면 1 패배면 2 출력
      // 재경기 3

      System.out.println("계산");

      System.out.println(this.winRating + " " + com.winRating);

      // 암행어사0
      if (this.winRating == -3 || com.winRating == -3) {
         if (this.winRating == -3) {
            if (com.winRating == 1) {
               System.out.println("1"); // 승리
               // 승리하면 게임은 이 클래스는 종료하고 아닐 시 아랫부분에서 패 계산을 다시함.
               return 1;
            } else {
               this.winRating = 26;
            }
         } else { // winRating2 == -4
            if (this.winRating == 1) {
               this.winRating = 26;
            } else {
               System.out.println("1"); // 승리
               return 1;
            }
         }
      }
      // 땡잡이
      else if (this.winRating == -4 || com.winRating == -4) {
         if (this.winRating == -4) {
            if (com.winRating >= 3 && com.winRating <= 11) {
               System.out.println("1"); // 승리
               return 1;
            } else {
               this.winRating = 27; // 땡잡이는 땡이 없음 망통
            }
         } else { // winRating2 == -4
            if (this.winRating >= 3 && this.winRating <= 11) {
               System.out.println("2"); // 패배
               return 2;
            } else {
               com.winRating = 27; // 땡잡이는 땡이 없음 망통
            }
         }
      }

      // 위 조건문에서 -4랑 -3은 사라짐
      // 위 조건문과 별개로 돌아가야 함.
      // 멍텅구리 구사, 구사는 함께 검사한다.
      if (this.winRating == -1 || this.winRating == -2) {
         if (this.winRating == -1) {
            if (com.winRating < 0 || com.winRating > 2) {
               System.out.println("3");// 재경기
               return 3;
            } else {
               System.out.println("2");// 패배
               return 2;
            }
         } else { // winRating == -2
            if (com.winRating < 0 || com.winRating > 12) {
               System.out.println("3");// 재경기
               return 3;
            } else {
               System.out.println("2");// 패배
               return 2;
            }
         }
      } else if (com.winRating == -1 || com.winRating == -2) {
         if (com.winRating == -1) {
            if (this.winRating < 0 || this.winRating > 2) {
               System.out.println("3");// 재경기
               return 3;
            } else {
               System.out.println("1");// 승리
               return 1;
            }
         } else { // winRating2 == -2
            if (this.winRating < 0 || this.winRating > 12) {
               System.out.println("3");// 재경기
               return 3;
            } else {
               System.out.println("1");// 승리
               return 1;
            }
         }
      }
      // 그냥 패 계산

      if (this.winRating < com.winRating) {
         System.out.println("1");// 승리
         return 1;
      } else if (this.winRating == com.winRating) {
         System.out.println("3");// 재경기
         return 3;
      } else {
         System.out.println("2");// 패배
         return 2;
      }
   }

   public int ComputerChioce(Calculation com, int playerPick) {
      // firstPut 컴퓨터가 먼저할떄가 0
      int chance;

      Random random = new Random();

      // 리턴 값이 0 다이, 1 체크, 2 하프. 3 콜, 4 쿼터, 5 따당, 6 삥

      chance = random.nextInt(10);

      // 알리 12 갑오18 망통27
      // 0 1 2 3 4 5 6 7 8 9
      // winRating(사람의 패), com.winRating(컴퓨터 패)

      if (playerPick == 1) {
         if (com.winRating <= 12) {
            if (chance < 5) {
               return 2;
            } // 0 1 2 3 4
            else if (chance < 6) {
               return 3;
            } // 5
            else if (chance < 8) {
               return 4;
            } // 6 7
            else {
               return 5;
            } // 8 9
         } else if (com.winRating <= 18) {
            if (chance < 2) {
               return 2;
            } // 0 1
            else if (chance < 6) {
               return 3;
            } // 2 3 4 5
            else if (chance < 8) {
               return 4;
            } // 6 7
            else {
               return 5;
            } // 8 9
         } else {
            if (chance < 2) {
               return 2;
            } // 0 1
            else if (chance < 6) {
               return 3;
            } // 2 3 4 5
            else if (chance < 8) {
               return 4;
            } // 6 7
            else {
               return 5;
            } // 8 9
         }
      } // 체크를 했을 경우, 하프2,콜3,쿼터4,따당5
      else if (playerPick == 2) {
         if (com.winRating <= 12) {
            if (chance < 1) {
               return 0;
            } // 0
            else if (chance < 6) {
               return 2;
            } // 1 2 3 4 5
            else if (chance < 8) {
               return 3;
            } // 6 7
            else {
               return 5;
            } // 8 9
         } else if (com.winRating <= 18) {
            if (chance < 2) {
               return 0;
            } // 0 1
            else if (chance < 5) {
               return 2;
            } // 2 3 4
            else if (chance < 8) {
               return 3;
            } // 5 6 7
            else {
               return 5;
            } // 8 9
         } else {
            if (chance < 4) {
               return 0;
            } // 0 1
            else if (chance < 7) {
               return 2;
            } // 2 3 4 5 6
            else if (chance < 9) {
               return 3;
            } // 7 8
            else {
               return 5;
            } // 9
         }
      } // 하프를 했을 경우 선택할 수 있는 것 다이0,하프2,콜3,따당5
      else if (playerPick == 3) {
         if (com.winRating <= 12) {
            if (chance < 1) {
               return 0;
            } // 0
            else {
               return 3;
            } // 1 2 3 4 5 6 7 8 9
         } else if (com.winRating <= 18) {
            if (chance < 2) {
               return 0;
            } // 0 1
            else {
               return 3;
            } // 2 3 4 5 6 7 8 9
         } else {
            if (chance < 3) {
               return 0;
            } // 0 1 2
            else {
               return 3;
            } // 3 4 5 6 7 8 9
         }
      } // 사람이 콜했을 경우 선택할수 있는 것 다이0,콜 3
      else if (playerPick == 4) {
         if (com.winRating <= 12) {
            if (chance < 1) {
               return 0;
            } // 0
            else if (chance < 6) {
               return 2;
            } // 1 2 3 4 5
            else if (chance < 8) {
               return 3;
            } // 6 7
            else if (chance < 9) {
               return 4;
            } // 8
            else {
               return 5;
            } // 9
         } else if (com.winRating <= 18) {
            if (chance < 2) {
               return 0;
            } // 0 1
            else if (chance < 5) {
               return 2;
            } // 2 3 4
            else if (chance < 8) {
               return 3;
            } // 5 6 7
            else if (chance < 9) {
               return 4;
            } // 8
            else {
               return 5;
            } // 9
         } else {
            if (chance < 2) {
               return 0;
            } // 0 1
            else if (chance < 4) {
               return 2;
            } // 2 3
            else if (chance < 8) {
               return 3;
            } // 4 5 6 7
            else if (chance < 9) {
               return 4;
            } // 8
            else {
               return 5;
            } // 9
         }
      } // 쿼터를 했을 경우 다이0,하프2,콜3,쿼터4,따당5
      else if (playerPick == 5) {
         if (com.winRating <= 12) {
            if (chance < 2) {
               return 0;
            } // 0 1
            else if (chance < 5) {
               return 3;
            } // 2 3 4
            else {
               return 5;
            } // 5 6 7 8 9
         } else if (com.winRating <= 18) {
            if (chance < 2) {
               return 0;
            } // 0 1
            else if (chance < 7) {
               return 3;
            } // 2 3 4 5 6
            else {
               return 5;
            } // 7 8 9
         } else {
            if (chance < 2) {
               return 0;
            } // 0 1
            else if (chance < 8) {
               return 3;
            } // 2 3 4 5 6 7
            else {
               return 5;
            } // 8 9
         }
      } // 따당을 했을 경우 다이0,콜3,따당 5
      else if (playerPick == 6) {
         if (com.winRating <= 12) {
            if (chance < 1) {
               return 0;
            } // 0
            else if (chance < 6) {
               return 2;
            } // 1 2 3 4 5
            else if (chance < 8) {
               return 3;
            } // 6 7
            else if (chance < 9) {
               return 4;
            } // 8
            else {
               return 5;
            } // 9
         } else if (com.winRating <= 18) {
            if (chance < 2) {
               return 0;
            } // 0 1
            else if (chance < 5) {
               return 2;
            } // 2 3 4
            else if (chance < 8) {
               return 3;
            } // 5 6 7
            else if (chance < 9) {
               return 4;
            } // 8
            else {
               return 5;
            } // 9
         } else {
            if (chance < 2) {
               return 0;
            } // 0 1
            else if (chance < 6) {
               return 2;
            } // 2 3 4 5
            else if (chance < 8) {
               return 3;
            } // 6 7
            else if (chance < 9) {
               return 4;
            } // 8
            else {
               return 5;
            } // 9
         }
      } // 삥을 했을 경우 다이0, 하프2, 콜3, 쿼터4, 따땅5
      else {
         return 0;
      }
   }
}