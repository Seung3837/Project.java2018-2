package matsudda;

import javax.swing.JFrame;

public class Viewer {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("sudda");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		MainPanel main = new MainPanel();

		frame.getContentPane().add(main);
		frame.pack();
		frame.setVisible(true);
	}

}
