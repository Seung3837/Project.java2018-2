package matsudda;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class UserPanel extends JPanel {
   protected UserData dataUser; // 유저
   protected UserData dataCom; // 컴퓨터
   protected JPanel buttonPanel, betMoneyPanel, userMoneyPanel, comMoneyPanel, WinPanel;
   protected JButton btnQuarter, btnCall, btnHalf, btnDie, btnContinue, btnDdadang, btnCheck, btnBbing, btnStart;
   protected ButtonListener buttonL;
   protected JLabel lblUserMoney, lblComMoney, lblUserBatting, lblComBatting, lblBatting, lblUsersID, lblComID, MyRating;
   protected ImageIcon[] deckIcon; // 패의 이미지
   protected CardThread[] lbldeck; // 게임이 끝나기 전 컴퓨터의 패를 뒤집어 놓음
   protected CardThread[] Mydeck; // 나의 패
   protected JLabel[] Comdeck; // 컴퓨터의 패
   protected JLabel[] WinLose; // 승패결과창에 쓸 글 (승 /패 /재경기/최종 승 /최종 패)
   protected int BattingMoney = 2000000; // 배팅금액
   protected ImageIcon back; // 화투의 뒷면
   protected Calculation user, com;
   protected JButton backbtn; // 뒤로가기버튼
   protected int saveMoney;// 바로직전 배팅한 금액
   protected int saveBeforeBattingMoney; // 전의 배팅금액
   protected int count; // 턴 수
   protected int userAction, comAction; // 유저가 한 행동, 컴퓨터가 한 행동
   protected int result; // 게임 결과 1:승, 2:패, 3:재경기
   protected Thread delay; // 딜레이 시간
   protected JLabel comRate;
   private int x; //유저의 winRating
   private int reGame;
   
   
   private ImageIcon[] comMoney, userMoney, Bat, imageEnding;
   private JLabel[] lblComMoneyIcon, lblUserMoneyIcon, lblBatMoneyIcon, lblEnding;
   
   private ImageIcon oneImage, backImage;
   private Image one, other;

   private Color backColor, moneyColor, buttonColor, cardPlace;
   private Font fntButton, moneyFont;
   private LineBorder buttonBorder, changeB;
   private MainListener mainL;
   
   private DecimalFormat formatter;

   public UserPanel() {
      oneImage = new ImageIcon("images/gameback.PNG");
      one = oneImage.getImage();
      other = one.getScaledInstance(1000, 760, Image.SCALE_SMOOTH);
      backImage = new ImageIcon(other);
      reGame = 0;
      
      // 승패를 띄울 패널
      WinPanel = new JPanel();
      WinPanel.setBounds(255, 250, 450, 300);
      WinPanel.setBackground(Color.white);
      WinPanel.setVisible(false);
      WinPanel.setLayout(null);
      add(WinPanel);

      // 승패를 띄울 패널에 쓸 글(승,패,재경기,최종 승,최종 패)
      imageEnding = new ImageIcon[5];
      lblEnding = new JLabel[5];
      WinLose = new JLabel[5];
      WinLose[0] = new JLabel("WIN");
      WinLose[1] = new JLabel("LOSE");
      WinLose[2] = new JLabel("REGAME");
      WinLose[3] = new JLabel("User win");
      WinLose[4] = new JLabel("Computer win");
      for(int i = 0; i < 5; i++) {
    	  oneImage = new ImageIcon("images/winlose" + i + ".JPG");
    	  one = oneImage.getImage();
    	  other = one.getScaledInstance(450, 300, Image.SCALE_SMOOTH);
    	  imageEnding[i] = new ImageIcon(other);
    	  lblEnding[i] = new JLabel(imageEnding[i]);
    	  lblEnding[i].setBounds(0, 0, 450, 300);
    	  lblEnding[i].setVisible(false);
    	  WinLose[i].setBounds(135, 124, 50, 30);
          WinLose[i].setVisible(false);
    	  WinPanel.add(lblEnding[i]);
    	  WinPanel.add(WinLose[i]);
      }
      
      comMoney = new ImageIcon[3];
      userMoney = new ImageIcon[3];
      Bat = new ImageIcon[3];
      lblComMoneyIcon = new JLabel[3];
      lblUserMoneyIcon = new JLabel[3];
      lblBatMoneyIcon = new JLabel[3];
      for(int i = 0; i < 3; i++) {
    	  oneImage = new ImageIcon("images/money" + i + ".PNG");
    	  one = oneImage.getImage();
    	  other = one.getScaledInstance(150, 200, Image.SCALE_SMOOTH);
    	  comMoney[i] = new ImageIcon(other);
    	  userMoney[i] = new ImageIcon(other);
    	  Bat[i] = new ImageIcon(other);
    	  lblComMoneyIcon[i] = new JLabel(comMoney[i]);
    	  lblComMoneyIcon[i].setBounds(200, 20, 150, 200);
    	  lblComMoneyIcon[i].setVisible(false);
    	  lblUserMoneyIcon[i] = new JLabel(userMoney[i]);
    	  lblUserMoneyIcon[i].setBounds(230, 535, 150, 200);
    	  lblUserMoneyIcon[i].setVisible(false);
    	  lblBatMoneyIcon[i] = new JLabel(Bat[i]);
    	  lblBatMoneyIcon[i].setBounds(260, 270, 150, 200);
    	  lblBatMoneyIcon[i].setVisible(false);
    	  add(lblComMoneyIcon[i]);
    	  add(lblUserMoneyIcon[i]);
    	  add(lblBatMoneyIcon[i]);
      }
      lblComMoneyIcon[2].setVisible(true);
      lblUserMoneyIcon[2].setVisible(true);
      lblBatMoneyIcon[0].setVisible(false);
      
      backColor = new Color(255, 0, 0, 0);
      moneyColor = new Color(237, 166, 134);
      buttonColor = new Color(81, 15, 15);
      cardPlace = new Color(0, 0, 0, 100);
      fntButton = new Font("HY견명조 보통", Font.BOLD, 20);
      moneyFont = new Font("Alien Encounters", Font.BOLD, 25);
      buttonBorder = new LineBorder(moneyColor, 3);
      changeB = new LineBorder(buttonColor, 3);
      mainL = new MainListener();
      
      formatter = new DecimalFormat("###,###");
      
      MyRating = new JLabel();
      MyRating.setBounds(375, 690, 270, 30);
      MyRating.setHorizontalAlignment(SwingConstants.CENTER);
      MyRating.setFont(fntButton);
      MyRating.setForeground(Color.white);
      MyRating.setVisible(true);
      add(MyRating);
      
      dataUser = new UserData();
      dataCom = new UserData(dataUser.getCard1(), dataUser.getCard2());
      setBackground(new Color(32, 164, 71));
      setPreferredSize(new Dimension(1000, 760));
      setLayout(null);

      buttonL = new ButtonListener();

      // 유저와 컴퓨터의 카드를 받음
      user = new Calculation(dataUser.getCard1(), dataUser.getCard2());
      com = new Calculation(dataCom.getCard1(), dataCom.getCard2());
      x = 0;
      
      btnStart = new JButton("패돌리기");
      btnStart.setBounds(432, 310, 150, 150);
      btnStart.setBorderPainted(false);
      btnStart.setFont(fntButton);
      btnStart.setForeground(Color.white);
      btnStart.setContentAreaFilled(false);
      btnStart.addActionListener(buttonL);
      add(btnStart);

      // 이미지 파일 각 객체배열에 생성
      deckIcon = new ImageIcon[20];
      deckIcon[0] = new ImageIcon("images/0.PNG");
      deckIcon[1] = new ImageIcon("images/1.PNG");
      deckIcon[2] = new ImageIcon("images/2.PNG");
      deckIcon[3] = new ImageIcon("images/3.PNG");
      deckIcon[4] = new ImageIcon("images/4.PNG");
      deckIcon[5] = new ImageIcon("images/5.PNG");
      deckIcon[6] = new ImageIcon("images/6.PNG");
      deckIcon[7] = new ImageIcon("images/7.PNG");
      deckIcon[8] = new ImageIcon("images/8.PNG");
      deckIcon[9] = new ImageIcon("images/9.PNG");
      deckIcon[10] = new ImageIcon("images/10.PNG");
      deckIcon[11] = new ImageIcon("images/11.PNG");
      deckIcon[12] = new ImageIcon("images/12.PNG");
      deckIcon[13] = new ImageIcon("images/13.PNG");
      deckIcon[14] = new ImageIcon("images/14.PNG");
      deckIcon[15] = new ImageIcon("images/15.PNG");
      deckIcon[16] = new ImageIcon("images/16.PNG");
      deckIcon[17] = new ImageIcon("images/17.PNG");
      deckIcon[18] = new ImageIcon("images/18.PNG");
      deckIcon[19] = new ImageIcon("images/19.PNG");

      back = new ImageIcon("images/back.PNG");

      // 가운데 여러개의 패 이미지 구현(나중에 이동을 위해 쓰레드로 만듬)
      lbldeck = new CardThread[22];
      for (int i = 0; i < 20; i++) {
         lbldeck[i] = new CardThread(back);
         lbldeck[i].setBounds(450 + i, 295 + i, 115, 180);
         add(lbldeck[i]);
      }

      // 유저의 정보를 담은 패널
      userMoneyPanel = new JPanel();
      userMoneyPanel.setBounds(375, 505, 440, 185);
      userMoneyPanel.setBackground(cardPlace);
      userMoneyPanel.setLayout(null);
      add(userMoneyPanel);

      // 컴퓨터의 정보를 담은 패널
      comMoneyPanel = new JPanel();
      comMoneyPanel.setBounds(375, 85, 440, 185);
      comMoneyPanel.setBackground(cardPlace);
      comMoneyPanel.setLayout(null);
      add(comMoneyPanel);
      
      // 나의 패
      Mydeck = new CardThread[2];
      for (int i = 0; i < 20; i++) {
         if (i == dataUser.getCard1()) {
            Mydeck[0] = new CardThread(deckIcon[i]);
            Mydeck[0].setBounds(5, 2, 115, 180);
            Mydeck[0].setVisible(false);
            userMoneyPanel.add(Mydeck[0]);
         }
         if (i == dataUser.getCard2()) {
            Mydeck[1] = new CardThread(deckIcon[i]);
            Mydeck[1].setBounds(145, 2, 115, 180);
            Mydeck[1].setVisible(false);
            userMoneyPanel.add(Mydeck[1]);
         }
      }

      // 컴퓨터의 패
      Comdeck = new JLabel[2];
      for (int i = 0; i < 20; i++) {
         if (i == dataCom.getCard1()) {
            Comdeck[0] = new JLabel(deckIcon[i]);
            Comdeck[0].setBounds(5, 2, 115, 180);
            Comdeck[0].setVisible(false);
            comMoneyPanel.add(Comdeck[0]);
         }
         if (i == dataCom.getCard2()) {
            Comdeck[1] = new JLabel(deckIcon[i]);
            Comdeck[1].setBounds(145, 2, 115, 180);
            Comdeck[1].setVisible(false);
            comMoneyPanel.add(Comdeck[1]);
         }
      }

      // 컴퓨터의 패를 뒤집어 놓은 것 구현
      lbldeck[20] = new CardThread(back);
      lbldeck[21] = new CardThread(back);
      lbldeck[20].setBounds(5, 2, 115, 180);
      lbldeck[21].setBounds(145, 2, 115, 180);
      lbldeck[20].setVisible(false);
      lbldeck[21].setVisible(false);
      comMoneyPanel.add(lbldeck[20]);
      comMoneyPanel.add(lbldeck[21]);

      // 계속하기 버튼
      btnContinue = new JButton("계속하기");
      btnContinue.setBounds(700, 0, 150, 40);
      btnContinue.addActionListener(buttonL);
      btnContinue.setBackground(new Color(55, 30, 6));
      btnContinue.setForeground(new Color(124, 92, 58));
      btnContinue.setBorder(new LineBorder(new Color(124, 92, 58), 1));
      btnContinue.setFocusPainted(false);
      btnContinue.setFont(new Font("HY견명조 보통", Font.BOLD, 23));
      btnContinue.setVisible(false);
      add(btnContinue);

      // 버튼이 위치할 곳의 패널
      buttonPanel = new JPanel();
      buttonPanel.setBounds(700, 270, 260, 235);
      buttonPanel.setBackground(new Color(255, 0, 0, 0));
      buttonPanel.setLayout(null);
      add(buttonPanel);

      // 따당 버튼
      btnDdadang = new JButton("따당");
      btnDdadang.setBounds(5, 159, 80, 60);
      btnDdadang.addActionListener(buttonL);
      btnDdadang.setEnabled(false);
      btnDdadang.setForeground(moneyColor);
      btnDdadang.setBackground(buttonColor);
      btnDdadang.setFont(fntButton);
      btnDdadang.setBorder(buttonBorder);
      btnDdadang.addMouseListener(mainL);
      btnDdadang.setFocusPainted(false);
      buttonPanel.add(btnDdadang);

      // 하프 버튼
      btnHalf = new JButton("하프");
      btnHalf.setBounds(90, 86, 80, 60);
      btnHalf.addActionListener(buttonL);
      btnHalf.setEnabled(false);
      btnHalf.setForeground(moneyColor);
      btnHalf.setBackground(buttonColor);
      btnHalf.setFont(fntButton);
      btnHalf.setBorder(buttonBorder);
      btnHalf.addMouseListener(mainL);
      btnHalf.setFocusPainted(false);
      buttonPanel.add(btnHalf);

      // 쿼터 버튼
      btnQuarter = new JButton("쿼터");
      btnQuarter.setBounds(5, 86, 80, 60);
      btnQuarter.addActionListener(buttonL);
      btnQuarter.setEnabled(false);
      btnQuarter.setForeground(moneyColor);
      btnQuarter.setBackground(buttonColor);
      btnQuarter.setFont(fntButton);
      btnQuarter.setBorder(buttonBorder);
      btnQuarter.addMouseListener(mainL);
      btnQuarter.setFocusPainted(false);
      buttonPanel.add(btnQuarter);

      // 콜 버튼
      btnCall = new JButton("콜");
      btnCall.setBounds(175, 86, 80, 60);
      btnCall.addActionListener(buttonL);
      btnCall.setEnabled(false);
      btnCall.setForeground(moneyColor);
      btnCall.setBackground(buttonColor);
      btnCall.setFont(fntButton);
      btnCall.setBorder(buttonBorder);
      btnCall.addMouseListener(mainL);
      btnCall.setFocusPainted(false);
      buttonPanel.add(btnCall);

      // 삥 버튼
      btnBbing = new JButton("삥");
      btnBbing.setBounds(90, 159, 80, 60);
      btnBbing.addActionListener(buttonL);
      btnBbing.setEnabled(false);
      btnBbing.setForeground(moneyColor);
      btnBbing.setBackground(buttonColor);
      btnBbing.setFont(fntButton);
      btnBbing.setBorder(buttonBorder);
      btnBbing.addMouseListener(mainL);
      btnBbing.setFocusPainted(false);
      buttonPanel.add(btnBbing);

      // 다이 버튼
      btnDie = new JButton("다이");
      btnDie.setBounds(175, 159, 80, 60);
      btnDie.addActionListener(buttonL);
      btnDie.setEnabled(false);
      btnDie.setForeground(moneyColor);
      btnDie.setBackground(buttonColor);
      btnDie.setFont(fntButton);
      btnDie.setBorder(buttonBorder);
      btnDie.addMouseListener(mainL);
      btnDie.setFocusPainted(false);
      buttonPanel.add(btnDie);

      // 체크 버튼
      btnCheck = new JButton("체크");
      btnCheck.setBounds(5, 13, 250, 60);
      btnCheck.addActionListener(buttonL);
      btnCheck.setEnabled(false);
      btnCheck.setForeground(moneyColor);
      btnCheck.setBackground(buttonColor);
      btnCheck.setFont(fntButton);
      btnCheck.setBorder(buttonBorder);
      btnCheck.addMouseListener(mainL);
      btnCheck.setFocusPainted(false);
      buttonPanel.add(btnCheck);

      // 배팅금액을 다음 패널
      betMoneyPanel = new JPanel();
      betMoneyPanel.setBounds(40, 230, 260, 300);
      betMoneyPanel.setBackground(backColor);
      betMoneyPanel.setLayout(null);
      add(betMoneyPanel);

      // 유저의 돈
      dataUser.nMoney = dataUser.nMoney - 1000000;
      lblUserMoney = new JLabel("[" + formatter.format(dataUser.nMoney) + "]");
      lblUserMoney.setBounds(270, 90, 170, 30);
      lblUserMoney.setForeground(moneyColor);
      lblUserMoney.setFont(moneyFont);
      userMoneyPanel.add(lblUserMoney);

      // 컴퓨터의 돈
      dataCom.nMoney = dataCom.nMoney - 1000000;
      lblComMoney = new JLabel("[" + formatter.format(dataCom.nMoney) + "]");
      lblComMoney.setBounds(270, 90, 170, 30);
      lblComMoney.setForeground(moneyColor);
      lblComMoney.setFont(moneyFont);
      comMoneyPanel.add(lblComMoney);

      // 유저의 이름
      lblUsersID = new JLabel("Player");
      lblUsersID.setBounds(270, 50, 170, 30);
      lblUsersID.setFont(new Font("Verdana", Font.BOLD, 20));
      lblUsersID.setForeground(Color.white);
      userMoneyPanel.add(lblUsersID);

      // 컴퓨터의 이름
      lblComID = new JLabel("COM");
      lblComID.setBounds(270, 50, 170, 30);
      lblComID.setFont(new Font("Verdana", Font.BOLD, 20));
      lblComID.setForeground(Color.white);
      comMoneyPanel.add(lblComID);

      // 배팅금액
      lblBatting = new JLabel(formatter.format(BattingMoney) + "Won");
      lblBatting.setBounds(0, 100, 260, 60);
      lblBatting.setHorizontalAlignment(SwingConstants.CENTER);
      lblBatting.setVerticalAlignment(SwingConstants.CENTER);
      lblBatting.setForeground(moneyColor);
      lblBatting.setFont(moneyFont);
      betMoneyPanel.add(lblBatting);

      // 유저가 어떤 배팅을 했는지 알려주는 JLabel
      lblUserBatting = new JLabel();
      lblUserBatting.setBounds(0, 130, 260, 130);
      lblUserBatting.setHorizontalAlignment(SwingConstants.CENTER);
      lblUserBatting.setVerticalAlignment(SwingConstants.CENTER);
      lblUserBatting.setFont(fntButton);
      lblUserBatting.setForeground(buttonColor);
      lblUserBatting.setVisible(false);
      betMoneyPanel.add(lblUserBatting);

      // 컴퓨터가 어떤 배팅을 했는지 알려주는 JLabel
      lblComBatting = new JLabel("CALL");
      lblComBatting.setBounds(0, 0, 260, 130);
      lblComBatting.setHorizontalAlignment(SwingConstants.CENTER);
      lblComBatting.setVerticalAlignment(SwingConstants.CENTER);
      lblComBatting.setFont(fntButton);
      lblComBatting.setForeground(buttonColor);
      lblComBatting.setVisible(false);
      betMoneyPanel.add(lblComBatting);

      // 나가기 버튼
      backbtn = new JButton("돌아가기");
      backbtn.setBounds(850, 0, 150, 40);
      backbtn.setBackground(new Color(55, 30, 6));
      backbtn.setForeground(new Color(124, 92, 58));
      backbtn.setBorder(new LineBorder(new Color(124, 92, 58), 1));
      backbtn.setFocusPainted(false);
      backbtn.setFont(new Font("HY견명조 보통", Font.BOLD, 23));
      add(backbtn);

      // 값 초기화
      count = 0;
      saveBeforeBattingMoney = 1000000;
      saveMoney = 1000000;
      userAction = -1;
      comAction = -1;
      
   } // UserPanel()

   private class ButtonListener implements ActionListener { // 버튼리스너
      public void actionPerformed(ActionEvent event) {
         Object obj = event.getSource();

         if (obj == btnStart) { // 스타트 버튼
        	 reGame = 0;
        	 lblUserMoney.setText("[" + formatter.format(dataUser.nMoney) + "]");
             lblComMoney.setText("[" + formatter.format(dataCom.nMoney) + "]");
             lblBatting.setText(formatter.format(BattingMoney) + "Won");
        	result = 0;
            userAction = -1;
            btnStart.setVisible(false);
            btnCheck.setEnabled(true);
            btnBbing.setEnabled(true);
            btnDdadang.setEnabled(true);
            btnQuarter.setEnabled(true);
            btnHalf.setEnabled(true);
            btnDie.setEnabled(true);
            btnCall.setEnabled(true);
            MyRating.setVisible(true);

            Mydeck = new CardThread[2];
            for (int i = 0; i < 20; i++) {
                if (i == dataUser.getCard1()) {
                   Mydeck[0] = new CardThread(deckIcon[i]);
                   Mydeck[0].setBounds(5, 2, 115, 180);
                   Mydeck[0].setVisible(false);
                   userMoneyPanel.add(Mydeck[0]);
                }
                if (i == dataUser.getCard2()) {
                   Mydeck[1] = new CardThread(deckIcon[i]);
                   Mydeck[1].setBounds(145, 2, 115, 180);
                   Mydeck[1].setVisible(false);
                   userMoneyPanel.add(Mydeck[1]);
                }
             }
            
            // 쓰레드를 이용하여 이동할 패 들을 구현
            lbldeck[0].setnumber(1);
            lbldeck[0].setStart(1);
            lbldeck[0].setEnd(280);
            lbldeck[0].start();

            lbldeck[1].setnumber(2);
            lbldeck[1].setStart(1);
            lbldeck[1].setEnd(280);
            lbldeck[1].start();

            lbldeck[2].setnumber(3);
            lbldeck[2].setStart(1);
            lbldeck[2].setEnd(280);
            lbldeck[2].start();

            lbldeck[3].setnumber(4);
            lbldeck[3].setStart(1);
            lbldeck[3].setEnd(280);
            lbldeck[3].start();

            lbldeck[20].setnumber(7);
            lbldeck[20].setStart(1);
            lbldeck[20].setEnd(280);
            lbldeck[20].start();

            lbldeck[21].setnumber(8);
            lbldeck[21].setStart(1);
            lbldeck[21].setEnd(280);
            lbldeck[21].start();

            Mydeck[0].setnumber(5);
            Mydeck[0].setStart(1);
            Mydeck[0].setEnd(280);
            Mydeck[0].start();

            Mydeck[1].setnumber(6);
            Mydeck[1].setStart(1);
            Mydeck[1].setEnd(280);
            Mydeck[1].start();
            
            user = new Calculation(dataUser.getCard1(), dataUser.getCard2());
            com = new Calculation(dataCom.getCard1(), dataCom.getCard2());
            x = user.winRating;

            if(x == -1) {
            	MyRating.setText("구사(멍텅구리)");
            } else if(x == -2) {
            	MyRating.setText("구사");
            } else if(user.winRating == -3) {
            	MyRating.setText("암행어사");
            } else if(x == -4) {
            	MyRating.setText("땡잡이");
            } else if(x == 0) {
            	MyRating.setText("38광땡");
            } else if(x == 1) {
            	MyRating.setText("광땡");
            } else if(x == 2) {
            	MyRating.setText("장땡");
            } else if(x == 3) {
            	MyRating.setText("9땡");
            } else if(x == 4) {
            	MyRating.setText("8땡");
            } else if(x == 5) {
            	MyRating.setText("7땡");
            } else if(x == 6) {
            	MyRating.setText("6땡");
            } else if(x == 7) {
            	MyRating.setText("5땡");
            } else if(x == 8) {
            	MyRating.setText("4땡");
            } else if(x == 9) {
            	MyRating.setText("3땡");
            } else if(x == 10) {
            	MyRating.setText("2땡");
            } else if(x == 11) {
            	MyRating.setText("1땡");
            } else if(x == 12) {
            	MyRating.setText("알리");
            } else if(x== 13) {
            	MyRating.setText("독사");
            } else if(x == 14) {
            	MyRating.setText("구삥");
            } else if(x == 15) {
            	MyRating.setText("장삥");
            } else if(x == 16) {
            	MyRating.setText("장사");
            } else if(x == 17) {
            	MyRating.setText("새륙");
            } else if(x == 18) {
            	MyRating.setText("갑오");
            } else if(x >= 19 && x <= 26) {
            	MyRating.setText((27 - x) + "끗");
            } else if(user.winRating == 27) {
            	MyRating.setText("망통");
            }
         }

         // 유저배팅
         else if (obj == btnCheck) { // Check 버튼
            userAction = 1;
            btnCheck.setEnabled(false);
            lblUserBatting.setText("Check");
            lblUserBatting.setVisible(true);

         } else if (obj == btnQuarter) { // 쿼터 버튼
            userAction = 4;
            if (count % 2 == 0) { // count가 짝수이면 saveBeforeBattingMoney 갱신
               saveBeforeBattingMoney = BattingMoney;
            }
            count++;
            saveMoney = saveBeforeBattingMoney / 4;
            BattingMoney = BattingMoney + saveBeforeBattingMoney / 4;
            lblBatting.setText(formatter.format(BattingMoney) + "Won");
            lblUserBatting.setText("Quarter");
            lblUserBatting.setVisible(true);
            dataUser.nMoney = dataUser.nMoney - saveBeforeBattingMoney / 4;
            lblUserMoney.setText("[" + formatter.format(dataUser.nMoney) + "]");
            betMoneyPanel.setVisible(false);
            userMoneyPanel.setVisible(false);
            comMoneyPanel.setVisible(false);
            betMoneyPanel.setVisible(true);
            userMoneyPanel.setVisible(true);
            comMoneyPanel.setVisible(true);
         } else if (obj == btnHalf) { // 하프 버튼
            userAction = 2;
            if (count % 2 == 0) { // count가 짝수이면 saveBeforeBattingMoney 갱신
               saveBeforeBattingMoney = BattingMoney;
            }
            count++;
            saveMoney = saveBeforeBattingMoney / 2;
            BattingMoney = BattingMoney + saveBeforeBattingMoney / 2;
            lblBatting.setText(formatter.format(BattingMoney) + "Won");
            lblUserBatting.setText("Half");
            lblUserBatting.setVisible(true);
            dataUser.nMoney = dataUser.nMoney - saveBeforeBattingMoney / 2;
            lblUserMoney.setText("[" + formatter.format(dataUser.nMoney) + "]");
            betMoneyPanel.setVisible(false);
            userMoneyPanel.setVisible(false);
            comMoneyPanel.setVisible(false);
            betMoneyPanel.setVisible(true);
            userMoneyPanel.setVisible(true);
            comMoneyPanel.setVisible(true);
         } else if (obj == btnCall) { // 콜 버튼
            userAction = 3;
            dataUser.nMoney = dataUser.nMoney - saveMoney;
            lblUserMoney.setText("[" + formatter.format(dataUser.nMoney) + "]");
            lblUserBatting.setText("Call");
            lblUserBatting.setVisible(true);
            BattingMoney = BattingMoney + saveMoney;
            lblBatting.setText(formatter.format(BattingMoney) + "Won");
            betMoneyPanel.setVisible(false);
            userMoneyPanel.setVisible(false);
            comMoneyPanel.setVisible(false);
            betMoneyPanel.setVisible(true);
            userMoneyPanel.setVisible(true);
            comMoneyPanel.setVisible(true);
         } else if (obj == btnDdadang) { // 따당 버튼
            userAction = 5;
            dataUser.nMoney = dataUser.nMoney - (saveMoney * 2);
            lblUserMoney.setText("[" + formatter.format(dataUser.nMoney) + "]");
            saveMoney = saveMoney * 2;
            BattingMoney = BattingMoney + saveMoney;
            lblBatting.setText(formatter.format(BattingMoney) + "Won");
            lblUserBatting.setText("Ddadang");
            lblUserBatting.setVisible(true);
            betMoneyPanel.setVisible(false);
            userMoneyPanel.setVisible(false);
            comMoneyPanel.setVisible(false);
            betMoneyPanel.setVisible(true);
            userMoneyPanel.setVisible(true);
            comMoneyPanel.setVisible(true);
         } else if (obj == btnBbing) { // 삥 버튼
            userAction = 6;
            dataUser.nMoney = dataUser.nMoney - 1000000;
            lblUserMoney.setText("[" + formatter.format(dataUser.nMoney) + "]");
            saveMoney = 1000000;
            BattingMoney = BattingMoney + 1000000;
            lblBatting.setText(formatter.format(BattingMoney) + "Won");
            lblUserBatting.setText("Bbing");
            lblUserBatting.setVisible(true);
            betMoneyPanel.setVisible(false);
            userMoneyPanel.setVisible(false);
            comMoneyPanel.setVisible(false);
            betMoneyPanel.setVisible(true);
            userMoneyPanel.setVisible(true);
            comMoneyPanel.setVisible(true);
         } else if (obj == btnDie) { // 다이 버튼
            dataCom.nMoney = dataCom.nMoney + BattingMoney;
            lblComMoney.setText("[" + formatter.format(dataCom.nMoney) + "]");
            BattingMoney = 0;
            lblBatting.setText(formatter.format(BattingMoney) + "Won");
            lblUserBatting.setText("Die");
            lblUserBatting.setVisible(true);
            
            lblBatMoneyIcon[0].setVisible(false);
            lblBatMoneyIcon[1].setVisible(false);
            lblBatMoneyIcon[2].setVisible(false);
            
            Comdeck[0].setVisible(true);
            Comdeck[1].setVisible(true);
            WinLose[0].setVisible(false);
            WinLose[1].setVisible(true);
            WinLose[2].setVisible(false);
            
            lblEnding[0].setVisible(false);
            lblEnding[1].setVisible(true);
            lblEnding[2].setVisible(false);
            
            WinPanel.setVisible(true);
            btnContinue.setVisible(true);
            btnHalf.setEnabled(false);
            btnCall.setEnabled(false);
            btnDie.setEnabled(false);
            btnQuarter.setEnabled(false);
            btnBbing.setEnabled(false);
            btnCheck.setEnabled(false);
            btnDdadang.setEnabled(false);
            lbldeck[20].setVisible(false);
            lbldeck[21].setVisible(false);
            betMoneyPanel.setVisible(false);
            userMoneyPanel.setVisible(false);
            comMoneyPanel.setVisible(false);
            betMoneyPanel.setVisible(true);
            userMoneyPanel.setVisible(true);
            comMoneyPanel.setVisible(true);
            if (dataUser.nMoney <= 0) { // 컴퓨터의 최종 승
               WinPanel.setVisible(true);
               WinLose[0].setVisible(false);
               WinLose[1].setVisible(false);
               WinLose[2].setVisible(false);
               WinLose[3].setVisible(false);
               WinLose[4].setVisible(true);
               btnContinue.setVisible(false);
               
               lblEnding[0].setVisible(false);
               lblEnding[1].setVisible(false);
               lblEnding[2].setVisible(false);
               lblEnding[3].setVisible(false);
               lblEnding[4].setVisible(true);
               
            } else if (dataCom.nMoney <= 0) { // 유저의 최종 승
               WinPanel.setVisible(true);
               WinLose[0].setVisible(false);
               WinLose[1].setVisible(false);
               WinLose[2].setVisible(false);
               WinLose[3].setVisible(true);
               WinLose[4].setVisible(false);
               btnContinue.setVisible(false);
               
               lblEnding[0].setVisible(false);
               lblEnding[1].setVisible(false);
               lblEnding[2].setVisible(false);
               lblEnding[3].setVisible(true);
               lblEnding[4].setVisible(false);
            }

         } else if (obj == btnContinue) { // 계속하기 버튼
        	if (reGame == 0) {
		BattingMoney = 2000000;
	} else{
	   BattingMoney += 2000000;
	}
        	count = 0;
            saveBeforeBattingMoney = 1000000;
            saveMoney = 1000000;
            userAction = -1;
            comAction = -1;
            WinPanel.setVisible(false);
           
            dataUser.nMoney = dataUser.nMoney - 1000000;
            lblUserMoney.setText("[" + formatter.format(dataUser.nMoney) + "]");
            dataCom.nMoney = dataCom.nMoney - 1000000;
            lblComMoney.setText("[" + formatter.format(dataCom.nMoney) + "]");
            lblBatting.setText(formatter.format(BattingMoney) + "Won");
            btnContinue.setVisible(false);
            btnHalf.setEnabled(false);
            btnCall.setEnabled(false);
            btnDie.setEnabled(false);
            btnQuarter.setEnabled(false);
            btnBbing.setEnabled(false);
            btnCheck.setEnabled(false);
            btnDdadang.setEnabled(false);
            btnStart.setVisible(true);
            MyRating.setVisible(false);

            dataUser.setCard();
            dataCom.setCard(dataUser.getCard1(), dataUser.getCard2());
            
            lblUserBatting.setVisible(false);
            lblComBatting.setVisible(false);
            Comdeck[0].setVisible(false);
            Comdeck[1].setVisible(false);
            Mydeck[0].setVisible(false);
            Mydeck[1].setVisible(false);
            lbldeck[20].setVisible(false);
            lbldeck[21].setVisible(false);
            betMoneyPanel.setVisible(false);
            userMoneyPanel.setVisible(false);
            comMoneyPanel.setVisible(false);
            betMoneyPanel.setVisible(true);
            userMoneyPanel.setVisible(true);
            comMoneyPanel.setVisible(true);

            for (int i = 0; i < 20; i++) {
               if (i == dataCom.getCard1()) {
                  Comdeck[0] = new JLabel(deckIcon[i]);
                  Comdeck[0].setBounds(5, 2, 115, 180);
                  Comdeck[0].setVisible(false);
                  comMoneyPanel.add(Comdeck[0]);
               }
               if (i == dataCom.getCard2()) {
                  Comdeck[1] = new JLabel(deckIcon[i]);
                  Comdeck[1].setBounds(145, 2, 115, 180);
                  Comdeck[1].setVisible(false);
                  comMoneyPanel.add(Comdeck[1]);
               }
            }
            Comdeck[0].setVisible(false);
            Comdeck[1].setVisible(false);
         }

         // delay....

         // 컴퓨터의 배팅
         if (obj != btnStart && obj != btnContinue && userAction != -1) { // 전 사람의 배팅 행동마다 상대가 할 수 있는 배팅 행동이 달라짐
            comAction = user.ComputerChioce(com, userAction);
         }
         if ((obj != btnContinue) && (obj != btnDie) && (obj != btnStart)) {

            if (comAction == 0) { // 컴퓨터가 Die를 한 경우
               lblComBatting.setText("Die");
               lblComBatting.setVisible(true);
               dataUser.nMoney = dataUser.nMoney + BattingMoney;
               lblUserMoney.setText("[" + formatter.format(dataUser.nMoney) + "]");
               BattingMoney = 2000000;
               lblBatting.setText(formatter.format(BattingMoney) + "Won");
              
               lblBatMoneyIcon[0].setVisible(false);
               lblBatMoneyIcon[1].setVisible(false);
               lblBatMoneyIcon[2].setVisible(false);
               
               WinLose[0].setVisible(true);
               WinLose[1].setVisible(false);
               WinLose[2].setVisible(false);
               
               lblEnding[0].setVisible(true);
               lblEnding[1].setVisible(false);
               lblEnding[2].setVisible(false);
               
               WinPanel.setVisible(true);
               btnContinue.setVisible(true);
               btnHalf.setEnabled(false);
               btnCall.setEnabled(false);
               btnDie.setEnabled(false);
               btnQuarter.setEnabled(false);
               btnBbing.setEnabled(false);
               btnCheck.setEnabled(false);
               btnDdadang.setEnabled(false);
               Comdeck[0].setVisible(true);
               Comdeck[1].setVisible(true);
               lbldeck[20].setVisible(false);
               lbldeck[21].setVisible(false);
               betMoneyPanel.setVisible(false);
               userMoneyPanel.setVisible(false);
               comMoneyPanel.setVisible(false);
               betMoneyPanel.setVisible(true);
               userMoneyPanel.setVisible(true);
               comMoneyPanel.setVisible(true);
               if (dataUser.nMoney <= 0) { // 컴퓨터의 최종 승
                  WinPanel.setVisible(true);
                  WinLose[0].setVisible(false);
                  WinLose[1].setVisible(false);
                  WinLose[2].setVisible(false);
                  WinLose[3].setVisible(false);
                  WinLose[4].setVisible(true);
                  btnContinue.setVisible(false);
                  
                  lblEnding[0].setVisible(false);
                  lblEnding[1].setVisible(false);
                  lblEnding[2].setVisible(false);
                  lblEnding[3].setVisible(false);
                  lblEnding[4].setVisible(true);
                  
               } else if (dataCom.nMoney <= 0) { // 유저의 최종 승
                  WinPanel.setVisible(true);
                  WinLose[0].setVisible(false);
                  WinLose[1].setVisible(false);
                  WinLose[2].setVisible(false);
                  WinLose[3].setVisible(true);
                  WinLose[4].setVisible(false);
                  btnContinue.setVisible(false);
               
                  lblEnding[0].setVisible(false);
                  lblEnding[1].setVisible(false);
                  lblEnding[2].setVisible(false);
                  lblEnding[3].setVisible(true);
                  lblEnding[4].setVisible(false);
                  
               }

            } else if (comAction == 1) { // 컴퓨터가 Check를 한 경우
               lblComBatting.setText("Check");
               lblComBatting.setVisible(true);
               count++;

               btnDie.setEnabled(false);
               btnCheck.setEnabled(false);
               btnHalf.setEnabled(true);
               btnCall.setEnabled(true);
               btnQuarter.setEnabled(true);
               btnDdadang.setEnabled(true);
               btnBbing.setEnabled(false);
               betMoneyPanel.setVisible(false);
               userMoneyPanel.setVisible(false);
               comMoneyPanel.setVisible(false);
               betMoneyPanel.setVisible(true);
               userMoneyPanel.setVisible(true);
               comMoneyPanel.setVisible(true);
            } else if (comAction == 2) { // 컴퓨터가 Half를 한 경우
               lblComBatting.setText("Half");
               lblComBatting.setVisible(true);
               count++;
               saveMoney = saveBeforeBattingMoney / 2;
               dataCom.nMoney = dataCom.nMoney - saveBeforeBattingMoney / 2;
               lblComMoney.setText("[" + formatter.format(dataCom.nMoney) + "]");
               BattingMoney = BattingMoney + saveBeforeBattingMoney / 2;
               lblBatting.setText(formatter.format(BattingMoney) + "Won");

               btnDie.setEnabled(true);
               btnCheck.setEnabled(false);
               btnHalf.setEnabled(true);
               btnCall.setEnabled(true);
               btnQuarter.setEnabled(false);
               btnDdadang.setEnabled(true);
               btnBbing.setEnabled(false);
               betMoneyPanel.setVisible(false);
               userMoneyPanel.setVisible(false);
               comMoneyPanel.setVisible(false);
               betMoneyPanel.setVisible(true);
               userMoneyPanel.setVisible(true);
               comMoneyPanel.setVisible(true);
            } else if (comAction == 3) { // 컴퓨터가 Call을 한 경우
               lblComBatting.setText("Call");
               lblComBatting.setVisible(true);
               dataCom.nMoney = dataCom.nMoney - saveMoney;
               lblComMoney.setText("[" + formatter.format(dataCom.nMoney) + "]");
               BattingMoney = BattingMoney + saveMoney;
               lblBatting.setText(formatter.format(BattingMoney) + "Won");

               btnDie.setEnabled(true);
               btnCheck.setEnabled(false);
               btnHalf.setEnabled(false);
               btnCall.setEnabled(true);
               btnQuarter.setEnabled(false);
               btnDdadang.setEnabled(false);
               btnBbing.setEnabled(false);

               result = user.winner(com); // 게임 결과 저장

               if (result == 1) { // 유저의 승
                  dataUser.nMoney = dataUser.nMoney + BattingMoney;
                  lblUserMoney.setText("[" + formatter.format(dataUser.nMoney) + "]");
                  BattingMoney = 0;
                  lblBatting.setText(formatter.format(BattingMoney) + "Won");
                  reGame = 0;
                  
                  lblBatMoneyIcon[0].setVisible(false);
 	         	  lblBatMoneyIcon[1].setVisible(false);
 	        	  lblBatMoneyIcon[2].setVisible(false);
                  
                  WinLose[0].setVisible(true);
                  WinLose[1].setVisible(false);
                  WinLose[2].setVisible(false);
                  
                  lblEnding[0].setVisible(true);
                  lblEnding[1].setVisible(false);
                  lblEnding[2].setVisible(false);
                  
                  WinPanel.setVisible(true);
                  btnContinue.setVisible(true);
                  btnHalf.setEnabled(false);
                  btnCall.setEnabled(false);
                  btnDie.setEnabled(false);
                  btnQuarter.setEnabled(false);
                  btnBbing.setEnabled(false);
                  btnCheck.setEnabled(false);
                  btnDdadang.setEnabled(false);
                  Comdeck[0].setVisible(true);
                  Comdeck[1].setVisible(true);
                  lbldeck[20].setVisible(false);
                  lbldeck[21].setVisible(false);

                  if (dataUser.nMoney <= 0) { // 컴퓨터의 최종 승
                     WinPanel.setVisible(true);
                     WinLose[0].setVisible(false);
                     WinLose[1].setVisible(false);
                     WinLose[2].setVisible(false);
                     WinLose[3].setVisible(false);
                     WinLose[4].setVisible(true);
                     btnContinue.setVisible(false);
                     
                     lblEnding[0].setVisible(false);
                     lblEnding[1].setVisible(false);
                     lblEnding[2].setVisible(false);
                     lblEnding[3].setVisible(false);
                     lblEnding[4].setVisible(true);
                     
                  } else if (dataCom.nMoney <= 0) { // 유저의 최종 승
                     WinPanel.setVisible(true);
                     WinLose[0].setVisible(false);
                     WinLose[1].setVisible(false);
                     WinLose[2].setVisible(false);
                     WinLose[3].setVisible(true);
                     WinLose[4].setVisible(false);
                     btnContinue.setVisible(false);
                     
                     lblEnding[0].setVisible(false);
                     lblEnding[1].setVisible(false);
                     lblEnding[2].setVisible(false);
                     lblEnding[3].setVisible(true);
                     lblEnding[4].setVisible(false);
                     
                  }
               } else if (result == 2) { // 컴퓨터의 승
                  dataCom.nMoney = dataCom.nMoney + BattingMoney;
                  lblComMoney.setText("[" + formatter.format(dataCom.nMoney) + "]");
                  BattingMoney = 0;
                  lblBatting.setText(formatter.format(BattingMoney) + "Won");
                  reGame = 0;
                  
                  lblBatMoneyIcon[0].setVisible(false);
                  lblBatMoneyIcon[1].setVisible(false);
                  lblBatMoneyIcon[2].setVisible(false);
                  
                  WinLose[0].setVisible(false);
                  WinLose[1].setVisible(true);
                  WinLose[2].setVisible(false);
                  
                  lblEnding[0].setVisible(false);
                  lblEnding[1].setVisible(true);
                  lblEnding[2].setVisible(false);
                  
                  WinPanel.setVisible(true);
                  btnContinue.setVisible(true);
                  btnHalf.setEnabled(false);
                  btnCall.setEnabled(false);
                  btnDie.setEnabled(false);
                  btnQuarter.setEnabled(false);
                  btnBbing.setEnabled(false);
                  btnCheck.setEnabled(false);
                  btnDdadang.setEnabled(false);
                  Comdeck[0].setVisible(true);
                  Comdeck[1].setVisible(true);
                  lbldeck[20].setVisible(false);
                  lbldeck[21].setVisible(false);
                  
                  if (dataUser.nMoney <= 0) { // 컴퓨터의 최종 승
                     WinPanel.setVisible(true);
                     WinLose[0].setVisible(false);
                     WinLose[1].setVisible(false);
                     WinLose[2].setVisible(false);
                     WinLose[3].setVisible(false);
                     WinLose[4].setVisible(true);
                     btnContinue.setVisible(false);
                     
                     lblEnding[0].setVisible(false);
                     lblEnding[1].setVisible(false);
                     lblEnding[2].setVisible(false);
                     lblEnding[3].setVisible(false);
                     lblEnding[4].setVisible(true);
                     
                  } else if (dataCom.nMoney <= 0) { // 유저의 최종 승
                     WinPanel.setVisible(true);
                     WinLose[0].setVisible(false);
                     WinLose[1].setVisible(false);
                     WinLose[2].setVisible(false);
                     WinLose[3].setVisible(true);
                     WinLose[4].setVisible(false);
                     btnContinue.setVisible(false);
                     
                     lblEnding[0].setVisible(false);
                     lblEnding[1].setVisible(false);
                     lblEnding[2].setVisible(false);
                     lblEnding[3].setVisible(true);
                     lblEnding[4].setVisible(false);
                     
                  }
               } else if (result == 3) { // 재경기 시
            	   
            	 lblBatMoneyIcon[0].setVisible(false);
  	        	 lblBatMoneyIcon[1].setVisible(false);
  	        	 lblBatMoneyIcon[2].setVisible(false);
            	   
  	        	reGame = 1;
  	        	
                  WinLose[0].setVisible(false);
                  WinLose[1].setVisible(false);
                  WinLose[2].setVisible(true);
                  
                  lblEnding[0].setVisible(false);
                  lblEnding[1].setVisible(false);
                  lblEnding[2].setVisible(true);
                  
                  WinPanel.setVisible(true);
                  btnContinue.setVisible(true);
                  btnHalf.setEnabled(false);
                  btnCall.setEnabled(false);
                  btnDie.setEnabled(false);
                  btnQuarter.setEnabled(false);
                  btnBbing.setEnabled(false);
                  btnCheck.setEnabled(false);
                  btnDdadang.setEnabled(false);
                  Comdeck[0].setVisible(true);
                  Comdeck[1].setVisible(true);
                  lbldeck[20].setVisible(false);
                  lbldeck[21].setVisible(false);

                  if (dataUser.nMoney <= 0) { // 컴퓨터의 최종 승
                     WinPanel.setVisible(true);
                     WinLose[0].setVisible(false);
                     WinLose[1].setVisible(false);
                     WinLose[2].setVisible(false);
                     WinLose[3].setVisible(false);
                     WinLose[4].setVisible(true);
                     btnContinue.setVisible(false);
                     
                     lblEnding[0].setVisible(false);
                     lblEnding[1].setVisible(false);
                     lblEnding[2].setVisible(false);
                     lblEnding[3].setVisible(false);
                     lblEnding[4].setVisible(true);
                     
                  } else if (dataCom.nMoney <= 0) { // 유저의 최종 승
                     WinPanel.setVisible(true);
                     WinLose[0].setVisible(false);
                     WinLose[1].setVisible(false);
                     WinLose[2].setVisible(false);
                     WinLose[3].setVisible(true);
                     WinLose[4].setVisible(false);
                     btnContinue.setVisible(false);
                     
                     lblEnding[0].setVisible(false);
                     lblEnding[1].setVisible(false);
                     lblEnding[2].setVisible(false);
                     lblEnding[3].setVisible(true);
                     lblEnding[4].setVisible(false);
                     
                  }
                  betMoneyPanel.setVisible(false);
                  userMoneyPanel.setVisible(false);
                  comMoneyPanel.setVisible(false);
                  betMoneyPanel.setVisible(true);
                  userMoneyPanel.setVisible(true);
                  comMoneyPanel.setVisible(true);
               }

            } else if (comAction == 4) { // 컴퓨터가 Quarter를 한 경우
               lblComBatting.setText("Quarter");
               lblComBatting.setVisible(true);
               count++;
               saveMoney = saveBeforeBattingMoney / 4;
               dataCom.nMoney = dataCom.nMoney - saveBeforeBattingMoney / 4;
               lblComMoney.setText("[" + formatter.format(dataCom.nMoney) + "]");
               BattingMoney = BattingMoney + saveBeforeBattingMoney / 4;
               lblBatting.setText(formatter.format(BattingMoney) + "Won");

               btnDie.setEnabled(true);
               btnCheck.setEnabled(false);
               btnHalf.setEnabled(true);
               btnCall.setEnabled(true);
               btnQuarter.setEnabled(true);
               btnDdadang.setEnabled(true);
               btnBbing.setEnabled(false);
               betMoneyPanel.setVisible(false);
               userMoneyPanel.setVisible(false);
               comMoneyPanel.setVisible(false);
               betMoneyPanel.setVisible(true);
               userMoneyPanel.setVisible(true);
               comMoneyPanel.setVisible(true);
            } else if (comAction == 5) { // 유저가 Ddadang을 한 경우
               lblComBatting.setText("Ddadang");
               lblComBatting.setVisible(true);
               dataCom.nMoney = dataCom.nMoney - (saveMoney * 2);
               lblComMoney.setText("[" + formatter.format(dataCom.nMoney) + "]");
               saveMoney = saveMoney * 2;
               BattingMoney = BattingMoney + saveMoney;
               lblBatting.setText(formatter.format(BattingMoney) + "Won");

               btnDie.setEnabled(true);
               btnCheck.setEnabled(false);
               btnHalf.setEnabled(false);
               btnCall.setEnabled(true);
               btnQuarter.setEnabled(false);
               btnDdadang.setEnabled(true);
               btnBbing.setEnabled(false);
               betMoneyPanel.setVisible(false);
               userMoneyPanel.setVisible(false);
               comMoneyPanel.setVisible(false);
               betMoneyPanel.setVisible(true);
               userMoneyPanel.setVisible(true);
               comMoneyPanel.setVisible(true);
            } else { // ...else(기능없음)
               lblComBatting.setVisible(true);
            }
         }
         if(dataUser.nMoney >= 10000000) {
        	 lblUserMoneyIcon[2].setVisible(true);
        	 lblUserMoneyIcon[1].setVisible(false);
        	 lblUserMoneyIcon[0].setVisible(false);
         }
         else if(dataUser.nMoney >= 5000000) {
        	 lblUserMoneyIcon[1].setVisible(true);
        	 lblUserMoneyIcon[2].setVisible(false);
        	 lblUserMoneyIcon[0].setVisible(false);
         }
         else if(dataUser.nMoney > 0){
        	 lblUserMoneyIcon[0].setVisible(true);
        	 lblUserMoneyIcon[1].setVisible(false);
        	 lblUserMoneyIcon[2].setVisible(false);
         }
         else {
        	 lblUserMoneyIcon[0].setVisible(false);
        	 lblUserMoneyIcon[1].setVisible(false);
        	 lblUserMoneyIcon[2].setVisible(false);
         }
         if(dataCom.nMoney >= 10000000) {
        	 lblComMoneyIcon[2].setVisible(true);
        	 lblComMoneyIcon[1].setVisible(false);
        	 lblComMoneyIcon[0].setVisible(false);
         }
         else if(dataCom.nMoney >= 5000000) {
        	 lblComMoneyIcon[1].setVisible(true);
        	 lblComMoneyIcon[2].setVisible(false);
        	 lblComMoneyIcon[0].setVisible(false);
         }
         else if(dataCom.nMoney > 0){
        	 lblComMoneyIcon[0].setVisible(true);
        	 lblComMoneyIcon[1].setVisible(false);
        	 lblComMoneyIcon[2].setVisible(false);
         }
         else {
        	 lblComMoneyIcon[0].setVisible(false);
        	 lblComMoneyIcon[1].setVisible(false);
        	 lblComMoneyIcon[2].setVisible(false);
         }
         
         if(result == 0) {
	         if(BattingMoney >= 10000000) {
	        	 lblBatMoneyIcon[2].setVisible(true);
	        	 lblBatMoneyIcon[1].setVisible(false);
	        	 lblBatMoneyIcon[0].setVisible(false);
	         }
	         else if(BattingMoney >= 5000000) {
	        	 lblBatMoneyIcon[1].setVisible(true);
	        	 lblBatMoneyIcon[2].setVisible(false);
	        	 lblBatMoneyIcon[0].setVisible(false);
	         }
	         else {
	        	 lblBatMoneyIcon[0].setVisible(true);
	        	 lblBatMoneyIcon[1].setVisible(false);
	        	 lblBatMoneyIcon[2].setVisible(false);
	         }
         }
         else {
        	 lblBatMoneyIcon[0].setVisible(false);
        	 lblBatMoneyIcon[1].setVisible(false);
        	 lblBatMoneyIcon[2].setVisible(false);
         }
         
      } // actionPerformed()

   }

   public void paintComponent(Graphics page) { // 유저와 컴퓨터의 패를 놓는 자리
      super.paintComponent(page);

      page.drawImage(backImage.getImage(), 0, 0, null);

   }

   class MainListener implements MouseListener {
      public void mouseClicked(MouseEvent event) {
      }

      public void mouseEntered(MouseEvent event) {
         JButton obj = (JButton) event.getSource();
         obj.setBackground(moneyColor);
         obj.setBorder(changeB);
         obj.setForeground(buttonColor);
      }

      public void mouseExited(MouseEvent event) {
         JButton obj = (JButton) event.getSource();
         obj.setBackground(buttonColor);
         obj.setBorder(buttonBorder);
         obj.setForeground(moneyColor);
      }

      public void mousePressed(MouseEvent event) {
      }

      public void mouseReleased(MouseEvent event) {
      }
   }
}