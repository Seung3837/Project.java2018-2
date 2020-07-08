package matsudda;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class RuleExplanationPanel extends JPanel {
   protected int selectedIndex = 0; // 선택되어있는 버튼 인덱스
   protected JPanel selectPanel; // 버튼이 들어있는 패널, 이미지와 글로 설명하는 패널
   protected JLabel textLabel, textLabel2, textLabel3; // 글설명 라벨
   protected JButton[] btnSelectArray; // 각각의 버튼
   protected HoveringListener hoverL; // 마우스클릭리스너
   protected ImageIcon[] cardImage; // 카드이미지아이콘배열
   protected JButton backbtn; // 뒤로가기버튼
   protected JLabel[] cardLabel1, cardLabel2, cardLabel3; // 카드이미지가 들어가있는 라벨x3
   
   private Color fntColor;
   
   private ImageIcon oneImage, backImage;
   private Image one, other;
   
   private LineBorder buttonBorder, changeB;
   
   public RuleExplanationPanel() {
      // 배경색설정, 사이즈설정, 레이아웃제거
      setBackground(new Color(81, 15, 15));
      setPreferredSize(new Dimension(1000, 760));
      setLayout(null);
      
      oneImage = new ImageIcon("images/explainback.PNG");
      one = oneImage.getImage();
      other = one.getScaledInstance(1000, 760, Image.SCALE_SMOOTH);
      backImage = new ImageIcon(other);
      
      buttonBorder = new LineBorder(RuleExplanationConstants.JOKBO_EXIT[1], 3);
      changeB = new LineBorder(RuleExplanationConstants.JOKBO_EXIT[0], 3);

      fntColor = new Color(237, 166, 134);
      
      // 카드이미지아이콘배열 생성 후 라벨에 넣기
      cardImage = new ImageIcon[20];
      for (int i = 0; i < 20; i++) {
         cardImage[i] = new ImageIcon("images/" + i + ".PNG");
      }
      cardLabel1 = new JLabel[20];
      cardLabel2 = new JLabel[20];
      cardLabel3 = new JLabel[20];
      for (int i = 0; i < 20; i++) {
         cardLabel1[i] = new JLabel(cardImage[i]);
         cardLabel2[i] = new JLabel(cardImage[i]);
         cardLabel3[i] = new JLabel(cardImage[i]);
      }

      // 왼쪽에 버튼들이 있는 셀렉패널생성, 설정, 마우스리스너add
      selectPanel = new JPanel();
      selectPanel.setBounds(20, 20, 160, 720);
      selectPanel.setLayout(new GridLayout(6, 1));
      add(selectPanel);

      hoverL = new HoveringListener();
      btnSelectArray = new JButton[6];
      for (int i = 0; i < 6; i++) {
         btnSelectArray[i] = new JButton(RuleExplanationConstants.JOKBO[i]);
         btnSelectArray[i].setFont(new Font("HY견명조 보통", Font.BOLD, 16));
         btnSelectArray[i].setBackground(RuleExplanationConstants.JOKBO_EXIT[0]);
         btnSelectArray[i].setForeground(RuleExplanationConstants.JOKBO_EXIT[1]);
         btnSelectArray[i].setFocusPainted(false);
         btnSelectArray[i].setBorder(buttonBorder);
         btnSelectArray[i].addMouseListener(hoverL);
         selectPanel.add(btnSelectArray[i]);
      }
      btnSelectArray[0].setBackground(RuleExplanationConstants.JOKBO_ENTER[0]);
      btnSelectArray[0].setForeground(RuleExplanationConstants.JOKBO_ENTER[1]);
      btnSelectArray[0].setBorder(changeB);
      // 오른쪽에 이미지와 글을 이용해 설명하는 패널생성, 카드이미지add
      textLabel = new JLabel("<html>섯다는 화투패 2장으로 높은 족보를 만들어서상대를 제압하는 베팅게임입니다.<br>"
            + "삼팔광땡, 장땡 등의 일반적인 족보와, 구사, 땡잡이, 암행어사 등의 특수 족보로 서열을 넘나드는 눈치 게임이 진행됩니다.<br>" + "전체 화투패는 20장입니다.<br>"
            + "전통적인 섯다 방식은 화투패 2장을 모두 받은 다음 베팅하여 승부를 가립니다.<br><br><br>"
            + "일반족보 : 광땡, 땡, 알리, 독사, 구삥, 장삥, 장사, 세륙, 갑오, 끗, 망통<br>" + "특수족보 : 구사, 땡잡이, 암행어사<br><br></html>");
      textLabel2 = new JLabel();
      textLabel3 = new JLabel();
      textLabel.setBounds(200, 40, 760, 680);
      textLabel.setFont(new Font("HY견명조 보통", Font.BOLD, 23));
      textLabel2.setFont(new Font("HY견명조 보통", Font.BOLD, 23));
      textLabel3.setFont(new Font("HY견명조 보통", Font.BOLD, 23));
      textLabel.setForeground(fntColor);
      textLabel2.setForeground(fntColor);
      textLabel3.setForeground(fntColor);
      textLabel.setVerticalAlignment(SwingConstants.CENTER);
      textLabel.setHorizontalAlignment(SwingConstants.LEFT);
      textLabel2.setVerticalAlignment(SwingConstants.TOP);
      textLabel2.setHorizontalAlignment(SwingConstants.LEFT);
      textLabel3.setVerticalAlignment(SwingConstants.TOP);
      textLabel3.setHorizontalAlignment(SwingConstants.LEFT);
      for (int i = 0; i < 20; i++) {
         add(cardLabel1[i]);
         add(cardLabel2[i]);
         add(cardLabel3[i]);
      }
      textLabel.setVisible(true);
      add(textLabel);
      add(textLabel2);
      add(textLabel3);

      backbtn = new JButton("돌아가기");
      backbtn.setBounds(850, 0, 150, 40);
      backbtn.setBackground(new Color(55, 30, 6));
      backbtn.setForeground(new Color(124, 92, 58));
      backbtn.setBorder(new LineBorder(new Color(124, 92, 58), 1));
      backbtn.setFocusPainted(false);
      backbtn.setFont(new Font("HY견명조 보통", Font.BOLD, 23));
      add(backbtn);
   }

   private class HoveringListener implements MouseListener {
      // 마우스로 버튼을 클릭한 버튼에 따라 각각의 설명과 이미지를 setvisible을 통해 보여줌
      public void mouseClicked(MouseEvent event) {
         JButton obj = (JButton) event.getSource();
         for (int i = 0; i < 6; i++) {
            // 지금 선택되어 있는 버튼과 다른경우 바꾸고 같은경우는 유지
            if (obj == btnSelectArray[i]) {
               if (selectedIndex != i) {
                  btnSelectArray[selectedIndex].setBackground(RuleExplanationConstants.JOKBO_EXIT[0]);
                  btnSelectArray[selectedIndex].setForeground(RuleExplanationConstants.JOKBO_EXIT[1]);
                  btnSelectArray[selectedIndex].setBorder(buttonBorder);
                  selectedIndex = i;
               }
               break;
            }
         }
         if (selectedIndex == 0) {
            for (int i = 0; i < 20; i++) {
               cardLabel1[i].setVisible(false);
               cardLabel2[i].setVisible(false);
               cardLabel3[i].setVisible(false);
            }
            textLabel.setVisible(false);
            textLabel2.setVisible(false);
            textLabel3.setVisible(false);
            textLabel.setVerticalAlignment(SwingConstants.CENTER);
            textLabel.setBounds(200, 40, 760, 680);
            textLabel.setText("<html>섯다는 화투패 2장으로 높은 족보를 만들어서상대를 제압하는 베팅게임입니다.<br>"
                  + "삼팔광땡, 장땡 등의 일반적인 족보와, 구사, 땡잡이, 암행어사 등의 특수 족보로 서열을 넘나드는 눈치 게임이 진행됩니다.<br>"
                  + "전체 화투패는 20장입니다.<br>" + "전통적인 섯다 방식은 화투패 2장을 모두 받은 다음 베팅하여 승부를 가립니다.<br><br><br>"
                  + "일반족보 : 광땡, 땡, 알리, 독사, 구삥, 장삥, 장사, 세륙, 갑오, 끗, 망통<br>"
                  + "특수족보 : 구사, 땡잡이, 암행어사<br><br></html>");
            textLabel.setVisible(true);
         } else if (selectedIndex == 1) {
            for (int i = 0; i < 20; i++) {
               cardLabel1[i].setVisible(false);
               cardLabel2[i].setVisible(false);
               cardLabel3[i].setVisible(false);
            }
            textLabel.setVisible(false);
            textLabel2.setVisible(false);
            textLabel3.setVisible(false);
            textLabel.setVerticalAlignment(SwingConstants.TOP);
            cardLabel1[12].setBounds(230, 70, 100, 150);
            cardLabel1[17].setBounds(330, 70, 100, 150);
            cardLabel1[10].setBounds(480, 70, 100, 150);
            cardLabel2[12].setBounds(580, 70, 100, 150);
            cardLabel2[10].setBounds(730, 70, 100, 150);
            cardLabel2[17].setBounds(830, 70, 100, 150);
            cardLabel1[13].setBounds(480, 370, 100, 150);
            cardLabel1[16].setBounds(580, 370, 100, 150);
            cardLabel1[12].setVisible(true);
            cardLabel1[17].setVisible(true);
            cardLabel1[10].setVisible(true);
            cardLabel2[12].setVisible(true);
            cardLabel2[10].setVisible(true);
            cardLabel2[17].setVisible(true);
            cardLabel1[13].setVisible(true);
            cardLabel1[16].setVisible(true);
            textLabel.setBounds(200, 240, 760, 300);
            textLabel.setText("<html>광으로만 구성된 족보로, 가장 강한 족보이다.<br>"
                  + "삼팔광땡은 어떠한 족보도 이길 수 없고, 일삼광땡과 일팔광땡은 암행어사(특수족보)로만 이길 수 있다.<html>");
            textLabel.setVisible(true);
            textLabel2.setBounds(200, 540, 760, 200);
            textLabel2.setText("<html>열자리 4월과 열자리 7월로 된 조합으로서 일삼광땡 혹은 일팔광땡을 이길 수 있습니다.<br>"
                  + "만약 상대방 중에 광땡이 없다면 1끗으로 계산 됩니다.</html>");
            textLabel2.setVisible(true);
         } else if (selectedIndex == 2) {
            for (int i = 0; i < 20; i++) {
               cardLabel1[i].setVisible(false);
               cardLabel2[i].setVisible(false);
               cardLabel3[i].setVisible(false);
            }
            textLabel.setVisible(false);
            textLabel2.setVisible(false);
            textLabel3.setVisible(false);
            textLabel.setVerticalAlignment(SwingConstants.TOP);
            cardLabel1[9].setBounds(230, 70, 100, 150);
            cardLabel1[19].setBounds(330, 70, 100, 150);
            cardLabel1[4].setBounds(480, 70, 100, 150);
            cardLabel1[14].setBounds(580, 70, 100, 150);
            cardLabel1[0].setBounds(730, 70, 100, 150);
            cardLabel1[10].setBounds(830, 70, 100, 150);
            cardLabel1[2].setBounds(380, 370, 100, 150);
            cardLabel1[12].setBounds(480, 370, 100, 150);
            cardLabel1[6].setBounds(580, 370, 100, 150);
            cardLabel1[16].setBounds(680, 370, 100, 150);
            cardLabel1[9].setVisible(true);
            cardLabel1[19].setVisible(true);
            cardLabel1[4].setVisible(true);
            cardLabel1[14].setVisible(true);
            cardLabel1[0].setVisible(true);
            cardLabel1[10].setVisible(true);
            cardLabel1[2].setVisible(true);
            cardLabel1[12].setVisible(true);
            cardLabel1[6].setVisible(true);
            cardLabel1[16].setVisible(true);
            textLabel.setBounds(200, 240, 760, 470);
            textLabel.setText("<html>같은 월의 조합입니다.<br>" + "땡 끼리 만났을 경우에는 장땡(10월)~삥땡(1월)순으로 숫자가 높을수록 이깁니다.</html>");
            textLabel.setVisible(true);
            textLabel2.setBounds(200, 540, 760, 200);
            textLabel2.setText("<html>3월과 7월의 조합으로서 구땡 이하의 족보를 이길 수 있습니다.<br>" + "장땡과 광땡은 잡을 수 없습니다.<br>"
                  + "만약 상대방 중에 땡이 없다면 끗수가0이므로 망통으로 계산됩니다.</html>");
            textLabel2.setVisible(true);
         } else if (selectedIndex == 3) {
            for (int i = 0; i < 20; i++) {
               cardLabel1[i].setVisible(false);
               cardLabel2[i].setVisible(false);
               cardLabel3[i].setVisible(false);
            }
            textLabel.setVisible(false);
            textLabel2.setVisible(false);
            textLabel3.setVisible(false);
            textLabel.setVerticalAlignment(SwingConstants.TOP);
            cardLabel1[0].setBounds(380, 70, 100, 150);
            cardLabel1[10].setBounds(480, 70, 100, 150);
            cardLabel1[1].setBounds(580, 70, 100, 150);
            cardLabel1[11].setBounds(680, 70, 100, 150);
            cardLabel2[0].setBounds(380, 277, 100, 150);
            cardLabel2[10].setBounds(480, 277, 100, 150);
            cardLabel1[3].setBounds(580, 277, 100, 150);
            cardLabel1[13].setBounds(680, 277, 100, 150);
            cardLabel3[0].setBounds(380, 484, 100, 150);
            cardLabel3[10].setBounds(480, 484, 100, 150);
            cardLabel1[8].setBounds(580, 484, 100, 150);
            cardLabel1[18].setBounds(680, 484, 100, 150);
            cardLabel1[0].setVisible(true);
            cardLabel1[10].setVisible(true);
            cardLabel1[1].setVisible(true);
            cardLabel1[11].setVisible(true);
            cardLabel2[0].setVisible(true);
            cardLabel2[10].setVisible(true);
            cardLabel1[3].setVisible(true);
            cardLabel1[13].setVisible(true);
            cardLabel3[0].setVisible(true);
            cardLabel3[10].setVisible(true);
            cardLabel1[8].setVisible(true);
            cardLabel1[18].setVisible(true);
            textLabel.setBounds(200, 230, 760, 207);
            textLabel.setText("<html>순서와 패 상관없이 1월과 2월의 조합입니다.</html>");
            textLabel.setVisible(true);
            textLabel2.setBounds(200, 437, 760, 107);
            textLabel2.setText("<html>순서와 패 상관없이 1월과 4월의 조합입니다.</html>");
            textLabel2.setVisible(true);
            textLabel3.setBounds(200, 644, 760, 96);
            textLabel3.setText("<html>순서와 패 상관없이 1월과 9월의 조합입니다.</html>");
            textLabel3.setVisible(true);
         } else if (selectedIndex == 4) {
            for (int i = 0; i < 20; i++) {
               cardLabel1[i].setVisible(false);
               cardLabel2[i].setVisible(false);
               cardLabel3[i].setVisible(false);
            }
            textLabel.setVisible(false);
            textLabel2.setVisible(false);
            textLabel3.setVisible(false);
            textLabel.setVerticalAlignment(SwingConstants.TOP);
            cardLabel1[0].setBounds(380, 70, 100, 150);
            cardLabel1[10].setBounds(480, 70, 100, 150);
            cardLabel1[9].setBounds(580, 70, 100, 150);
            cardLabel1[19].setBounds(680, 70, 100, 150);
            cardLabel1[3].setBounds(380, 277, 100, 150);
            cardLabel1[13].setBounds(480, 277, 100, 150);
            cardLabel2[9].setBounds(580, 277, 100, 150);
            cardLabel2[19].setBounds(680, 277, 100, 150);
            cardLabel2[3].setBounds(380, 484, 100, 150);
            cardLabel2[13].setBounds(480, 484, 100, 150);
            cardLabel1[5].setBounds(580, 484, 100, 150);
            cardLabel1[15].setBounds(680, 484, 100, 150);
            cardLabel1[0].setVisible(true);
            cardLabel1[10].setVisible(true);
            cardLabel1[9].setVisible(true);
            cardLabel1[19].setVisible(true);
            cardLabel1[3].setVisible(true);
            cardLabel1[13].setVisible(true);
            cardLabel2[9].setVisible(true);
            cardLabel2[19].setVisible(true);
            cardLabel2[3].setVisible(true);
            cardLabel2[13].setVisible(true);
            cardLabel1[5].setVisible(true);
            cardLabel1[15].setVisible(true);
            textLabel.setBounds(200, 230, 760, 207);
            textLabel.setText("<html>순서와 패 상관없이 1월과 10월의 조합입니다.</html>");
            textLabel.setVisible(true);
            textLabel2.setBounds(200, 437, 760, 107);
            textLabel2.setText("<html>순서와 패 상관없이 4월과 10월의 조합입니다.</html>");
            textLabel2.setVisible(true);
            textLabel3.setBounds(200, 644, 760, 96);
            textLabel3.setText("<html>순서와 패 상관없이 4월과 6월의 조합입니다.</html>");
            textLabel3.setVisible(true);
         } else if (selectedIndex == 5) {
            for (int i = 0; i < 20; i++) {
               cardLabel1[i].setVisible(false);
               cardLabel2[i].setVisible(false);
               cardLabel3[i].setVisible(false);
            }
            textLabel.setVisible(false);
            textLabel2.setVisible(false);
            textLabel3.setVisible(false);
            textLabel.setVerticalAlignment(SwingConstants.TOP);
            cardLabel1[2].setBounds(230, 70, 100, 150);
            cardLabel1[5].setBounds(330, 70, 100, 150);
            cardLabel1[0].setBounds(480, 70, 100, 150);
            cardLabel1[12].setBounds(580, 70, 100, 150);
            cardLabel1[6].setBounds(730, 70, 100, 150);
            cardLabel2[12].setBounds(830, 70, 100, 150);
            cardLabel2[3].setBounds(246, 320, 100, 150);
            cardLabel2[13].setBounds(346, 320, 100, 150);
            cardLabel2[8].setBounds(446, 320, 100, 150);
            cardLabel2[18].setBounds(546, 320, 100, 150);
            cardLabel3[13].setBounds(713, 320, 100, 150);
            cardLabel3[18].setBounds(813, 320, 100, 150);
            cardLabel1[2].setVisible(true);
            cardLabel1[5].setVisible(true);
            cardLabel1[0].setVisible(true);
            cardLabel1[12].setVisible(true);
            cardLabel1[6].setVisible(true);
            cardLabel2[12].setVisible(true);
            cardLabel2[3].setVisible(true);
            cardLabel2[13].setVisible(true);
            cardLabel2[8].setVisible(true);
            cardLabel2[18].setVisible(true);
            cardLabel3[13].setVisible(true);
            cardLabel3[18].setVisible(true);
            textLabel.setBounds(200, 240, 760, 250);
            textLabel.setText(
                  "<html>끗수가 9부터 1일때 각각 9끗(갑오), 8끗, 7끗, 6끗, 5끗, 4끗, 3끗, 2끗, 1끗, 0끗(망통)이라고 하며, 숫자가 높을수록 좋은 족보입니다.</html>");
            textLabel.setVisible(true);
            textLabel2.setBounds(200, 490, 760, 250);
            textLabel2.setText("<html>4월과 9월의 조합으로서 알리 이하의 족보와 이번 판을 물리고 재경기를 할 수 있습니다.<br>"
                  + "열자리 4월과 열자리 9월로 된 조합은 멍텅구리 구사로서, 장땡이하의 족보와 재경기를 할 수 있습니다.</html>");
            textLabel2.setVisible(true);
         }
      }

      public void mousePressed(MouseEvent event) {
      }

      public void mouseReleased(MouseEvent event) {

      }

      // 마우스가 버튼에 들어갔을때와 나왔을때 색을 지정하여 가독성을 높임
      public void mouseEntered(MouseEvent event) {
         JButton obj = (JButton) event.getSource();
         obj.setBackground(RuleExplanationConstants.JOKBO_ENTER[0]);
         obj.setForeground(RuleExplanationConstants.JOKBO_ENTER[1]);
         obj.setBorder(changeB);
      }

      public void mouseExited(MouseEvent event) {
         JButton obj = (JButton) event.getSource();
         if (obj != btnSelectArray[selectedIndex]) {
            obj.setBackground(RuleExplanationConstants.JOKBO_EXIT[0]);
            obj.setForeground(RuleExplanationConstants.JOKBO_EXIT[1]);
            obj.setBorder(buttonBorder);
         }
      }
   }
   public void paintComponent(Graphics page) { // 유저와 컴퓨터의 패를 놓는 자리
	      super.paintComponent(page);

	      page.drawImage(backImage.getImage(), 0, 0, this);

	   }
}