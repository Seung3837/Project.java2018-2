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
      } // p1�� ���� �� ���� �а� ������ ����


      System.out.println(p1+" "+p2+"��");
      
      if (p1 == 02 && p2 == 07) {
         winRating = 0;
      } // 38 ����
      else if (p1 == 00 && (p2 == 02 || p2 == 07)) {
         winRating = 1;
      } // 13,18 ����
      else if (p1 % 10 == p2 % 10) {
         winRating = 11 - p1 % 10; // �嶯 2 ~ 1�� 11
      } // ���� ����
      else if (p1 % 10 == 0 && p2 % 10 == 1) {
         winRating = 12;
      } // �˸�
      else if (p1 % 10 == 0 && p2 % 10 == 3) {
         winRating = 13;
      } // ����
      else if (p1 % 10 == 0 && p2 % 10 == 8) {
         winRating = 14;
      } // ����
      else if (p1 % 10 == 0 && p2 % 10 == 9) {
         winRating = 15;
      } // ���
      else if (p1 % 10 == 3 && p2 % 10 == 7) {
         winRating = 16;
      } // ���
      else if (p1 % 10 == 5 && p2 % 10 == 8) {
         winRating = 17;
      } // ����
      else if (p1 % 10 == 3 && p2 % 10 == 8) {
         if (p1 == 3 && p2 == 8) {
            winRating = -1;
         } // ���ֱ��� ����
         else {
            winRating = -2;
         } // ����
      } // ����
      else if (p1 == 3 && p2 == 6) {
         winRating = -3;
      } // ������ (���� ���� 1��)
      else if (p1 % 10 == 2 && p2 % 10 == 6) {
         winRating = -4;
      } // ������ 3~11�� �̰ܾ� �� (�嶯�� ���̱�� ���� ���� ����)
      else if (p1 % 10 + p2 % 10 == 7) {
         winRating = 18;
      } // ���� 9���� ������ �θ�
      else if (p1 % 10 + p2 % 10 == 8) {
         winRating = 27;
      } // ���� (���ٿ��� ���� ���� ��)
      else {
         p1++;
         p2++;
         winRating = (9 - (((p1 % 10) + (p2 % 10)) % 10)) + 18;
         // 1������ 8������
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
      } // p1�� ���� �� ���� �а� ������ ����

      if (p1 == 02 && p2 == 07) {
         winRating = 0;
      } // 38 ����
      else if (p1 == 00 && (p2 == 02 || p2 == 07)) {
         winRating = 1;
      } // 13,18 ����
      else if (p1 % 10 == p2 % 10) {
         winRating = 11 - p1 % 10; // �嶯 2 ~ 1�� 11
      } // ���� ����
      else if (p1 % 10 == 0 && p2 % 10 == 1) {
         winRating = 12;
      } // �˸�
      else if (p1 % 10 == 0 && p2 % 10 == 3) {
         winRating = 13;
      } // ����
      else if (p1 % 10 == 0 && p2 % 10 == 8) {
         winRating = 14;
      } // ����
      else if (p1 % 10 == 0 && p2 % 10 == 9) {
         winRating = 15;
      } // ���
      else if (p1 % 10 == 3 && p2 % 10 == 7) {
         winRating = 16;
      } // ���
      else if (p1 % 10 == 5 && p2 % 10 == 8) {
         winRating = 17;
      } // ����
      else if (p1 % 10 == 3 && p2 % 10 == 8) {
         if (p1 == 13 && p2 == 18) {
            winRating = -1;
         } // ���ֱ��� ����
         else {
            winRating = -2;
         } // ����
      } // ����
      else if (p1 == 13 && p2 == 16) {
         winRating = -3;
      } // ������ (���� ���� 1��)
      else if (p1 % 10 == 2 && p2 % 10 == 6) {
         winRating = -4;
      } // ������ 3~11�� �̰ܾ� �� (�嶯�� ���̱�� ���� ���� ����)
      else if (p1 % 10 + p2 % 10 == 7) {
         winRating = 18;
      } // ���� 9���� ������ �θ�
      else if (p1 % 10 + p2 % 10 == 8) {
         winRating = 27;
      } // ���� (���ٿ��� ���� ���� ��)
      else {
         p1++;
         p2++;
         winRating = (9 - ((p1 % 10) + (p2 % 10) + 2) % 10) + 18;
         // 1������ 8������
      }
   }

   public int winner(Calculation com) {
      // �ӽ÷� �¸��� 1 �й�� 2 ���
      // ���� 3

      System.out.println("���");

      System.out.println(this.winRating + " " + com.winRating);

      // ������0
      if (this.winRating == -3 || com.winRating == -3) {
         if (this.winRating == -3) {
            if (com.winRating == 1) {
               System.out.println("1"); // �¸�
               // �¸��ϸ� ������ �� Ŭ������ �����ϰ� �ƴ� �� �Ʒ��κп��� �� ����� �ٽ���.
               return 1;
            } else {
               this.winRating = 26;
            }
         } else { // winRating2 == -4
            if (this.winRating == 1) {
               this.winRating = 26;
            } else {
               System.out.println("1"); // �¸�
               return 1;
            }
         }
      }
      // ������
      else if (this.winRating == -4 || com.winRating == -4) {
         if (this.winRating == -4) {
            if (com.winRating >= 3 && com.winRating <= 11) {
               System.out.println("1"); // �¸�
               return 1;
            } else {
               this.winRating = 27; // �����̴� ���� ���� ����
            }
         } else { // winRating2 == -4
            if (this.winRating >= 3 && this.winRating <= 11) {
               System.out.println("2"); // �й�
               return 2;
            } else {
               com.winRating = 27; // �����̴� ���� ���� ����
            }
         }
      }

      // �� ���ǹ����� -4�� -3�� �����
      // �� ���ǹ��� ������ ���ư��� ��.
      // ���ֱ��� ����, ����� �Բ� �˻��Ѵ�.
      if (this.winRating == -1 || this.winRating == -2) {
         if (this.winRating == -1) {
            if (com.winRating < 0 || com.winRating > 2) {
               System.out.println("3");// ����
               return 3;
            } else {
               System.out.println("2");// �й�
               return 2;
            }
         } else { // winRating == -2
            if (com.winRating < 0 || com.winRating > 12) {
               System.out.println("3");// ����
               return 3;
            } else {
               System.out.println("2");// �й�
               return 2;
            }
         }
      } else if (com.winRating == -1 || com.winRating == -2) {
         if (com.winRating == -1) {
            if (this.winRating < 0 || this.winRating > 2) {
               System.out.println("3");// ����
               return 3;
            } else {
               System.out.println("1");// �¸�
               return 1;
            }
         } else { // winRating2 == -2
            if (this.winRating < 0 || this.winRating > 12) {
               System.out.println("3");// ����
               return 3;
            } else {
               System.out.println("1");// �¸�
               return 1;
            }
         }
      }
      // �׳� �� ���

      if (this.winRating < com.winRating) {
         System.out.println("1");// �¸�
         return 1;
      } else if (this.winRating == com.winRating) {
         System.out.println("3");// ����
         return 3;
      } else {
         System.out.println("2");// �й�
         return 2;
      }
   }

   public int ComputerChioce(Calculation com, int playerPick) {
      // firstPut ��ǻ�Ͱ� �����ҋ��� 0
      int chance;

      Random random = new Random();

      // ���� ���� 0 ����, 1 üũ, 2 ����. 3 ��, 4 ����, 5 ����, 6 ��

      chance = random.nextInt(10);

      // �˸� 12 ����18 ����27
      // 0 1 2 3 4 5 6 7 8 9
      // winRating(����� ��), com.winRating(��ǻ�� ��)

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
      } // üũ�� ���� ���, ����2,��3,����4,����5
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
      } // ������ ���� ��� ������ �� �ִ� �� ����0,����2,��3,����5
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
      } // ����� ������ ��� �����Ҽ� �ִ� �� ����0,�� 3
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
      } // ���͸� ���� ��� ����0,����2,��3,����4,����5
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
      } // ������ ���� ��� ����0,��3,���� 5
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
      } // ���� ���� ��� ����0, ����2, ��3, ����4, ����5
      else {
         return 0;
      }
   }
}