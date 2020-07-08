package matsudda;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainPanel extends JPanel {

   private JButton start, Explanation, finish;
   private JPanel main;
   private MainListener mainL;
   private RuleExplanationPanel ExplanationPanel;
   private UserPanel startPanel;
   private ImageIcon oneImage, backImage;
   private Image one, other;
   private Font fnt1, fnt2;
   
   private DecimalFormat formatter;

   public MainPanel() {
      setPreferredSize(new Dimension(1000, 760));
      setLayout(null);
      
      formatter = new DecimalFormat("###,###");
      
      oneImage = new ImageIcon("images/background.PNG");
      one = oneImage.getImage();
      other = one.getScaledInstance(1000, 760, Image.SCALE_SMOOTH);
      backImage = new ImageIcon(other);

      start = new JButton("게임시작");
      Explanation = new JButton("게임설명");
      finish = new JButton("게임종료");

      fnt1 = new Font("HY견명조 보통", Font.BOLD, 50);
      fnt2 = new Font("HY견명조 보통", Font.BOLD, 55);

      mainL = new MainListener();
      start.addMouseListener(mainL);
      Explanation.addMouseListener(mainL);
      finish.addMouseListener(mainL);
      start.setFont(fnt1);
      Explanation.setFont(fnt1);
      finish.setFont(fnt1);
      start.setForeground(Color.black);
      finish.setForeground(Color.black);
      Explanation.setForeground(Color.black);
      start.setBounds(350, 350, 300, 100);
      Explanation.setBounds(350, 450, 300, 100);
      finish.setBounds(350, 550, 300, 100);
      start.setContentAreaFilled(false);
      start.setBorderPainted(false);
      start.setFocusPainted(false);
      finish.setContentAreaFilled(false);
      finish.setBorderPainted(false);
      finish.setFocusPainted(false);
      Explanation.setContentAreaFilled(false);
      Explanation.setBorderPainted(false);
      Explanation.setFocusPainted(false);
      add(start);
      add(Explanation);
      add(finish);

      ExplanationPanel = new RuleExplanationPanel();
      ExplanationPanel.setVisible(false);
      ExplanationPanel.setBounds(0, 0, 1000, 760);
      add(ExplanationPanel);

      startPanel = new UserPanel();
      startPanel.setVisible(false);
      startPanel.setBounds(0, 0, 1000, 760);
      add(startPanel);

      ExplanationPanel.backbtn.setBounds(850, 0, 150, 40);
      ExplanationPanel.add(ExplanationPanel.backbtn);

      startPanel.backbtn.setBounds(850, 0, 150, 40);
      startPanel.add(startPanel.backbtn);

      startPanel.backbtn.addMouseListener(mainL);
      ExplanationPanel.backbtn.addMouseListener(mainL);
   }

   public void paintComponent(Graphics page) {
      super.paintComponent(page);

      page.drawImage(backImage.getImage(), 0, 0, null);
   }

   class MainListener implements MouseListener {
      public void mouseClicked(MouseEvent event) {
         JButton obj = (JButton) event.getSource();
         if (obj == start) {
            startPanel.setVisible(true);
            start.setVisible(false);
            Explanation.setVisible(false);
            finish.setVisible(false);

            System.out.println("##"+ startPanel.dataUser.getCard1());
            System.out.println("##"+ startPanel.dataUser.getCard2());
      
            startPanel.count = 0;
            startPanel.saveBeforeBattingMoney = 1000000;
            startPanel.saveMoney = 1000000;
            startPanel.userAction = -1;
            startPanel.comAction = -1;
            startPanel.dataUser.nMoney = 19000000;
            startPanel.dataCom.nMoney = 19000000;
            startPanel.BattingMoney = 2000000;
            startPanel.lblUserMoney.setText("[" + formatter.format(startPanel.dataUser.nMoney) + "]");
            startPanel.lblComMoney.setText("[" + formatter.format(startPanel.dataCom.nMoney) + "]");
            startPanel.lblBatting.setText(formatter.format(startPanel.BattingMoney) + "Won");
            startPanel.lblUserBatting.setVisible(false);
            startPanel.lblComBatting.setVisible(false);
            startPanel.WinLose[0].setVisible(false);
            startPanel.WinLose[1].setVisible(false);
            startPanel.WinLose[2].setVisible(false);
            startPanel.WinPanel.setVisible(false);
            startPanel.btnStart.setVisible(true);
            startPanel.btnContinue.setVisible(false);
            startPanel.btnHalf.setEnabled(false);
            startPanel.btnCall.setEnabled(false);
            startPanel.btnDie.setEnabled(false);
            startPanel.btnQuarter.setEnabled(false);
            startPanel.btnBbing.setEnabled(false);
            startPanel.btnCheck.setEnabled(false);
            startPanel.btnDdadang.setEnabled(false);

            startPanel.Mydeck[0].setVisible(false);
            startPanel.Mydeck[1].setVisible(false);
            startPanel.Comdeck[0].setVisible(false);
            startPanel.Comdeck[1].setVisible(false);
            startPanel.lbldeck[20].setVisible(false);
            startPanel.lbldeck[21].setVisible(false);
            
         } else if (obj == Explanation) {
            ExplanationPanel.setVisible(true);
            start.setVisible(false);
            Explanation.setVisible(false);
            finish.setVisible(false);
         } else if (obj == finish) {
            System.exit(0);
         } else if (obj == ExplanationPanel.backbtn) {
            ExplanationPanel.setVisible(false);
            start.setVisible(true);
            Explanation.setVisible(true);
            finish.setVisible(true);
         } else if (obj == startPanel.backbtn) {
            startPanel.setVisible(false);
            start.setVisible(true);
            Explanation.setVisible(true);
            finish.setVisible(true);
         } else if (obj == Explanation) {
            ExplanationPanel.setVisible(true);
            start.setVisible(false);
            Explanation.setVisible(false);
            finish.setVisible(false);
         } else if (obj == finish) {
            System.exit(0);
         } else if (obj == ExplanationPanel.backbtn) {
            ExplanationPanel.setVisible(false);
            start.setVisible(true);
            Explanation.setVisible(true);
            finish.setVisible(true);
         } else if (obj == startPanel.backbtn) {
            startPanel.setVisible(false);
            start.setVisible(true);
            Explanation.setVisible(true);
            finish.setVisible(true);
         }
      }

      public void mouseEntered(MouseEvent event) {
         JButton obj = (JButton) event.getSource();
         if(obj == start || obj == Explanation || obj == finish) {
            obj.setForeground(Color.red);
            obj.setFont(fnt2);
         }
      }

      public void mouseExited(MouseEvent event) {
         JButton obj = (JButton) event.getSource();
         if(obj == start || obj == Explanation || obj == finish) {
             obj.setForeground(Color.black);
             obj.setFont(fnt1);
         }
      }

      public void mousePressed(MouseEvent event) {

      }

      public void mouseReleased(MouseEvent event) {

      }
   }
}