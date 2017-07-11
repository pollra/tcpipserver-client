package app.chat.ui;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class UserInterface_v01 extends Frame implements Runnable, ActionListener {

	static UserInterface_v01 window;
	JButton button1 = new JButton("시작");
	JButton button2 = new JButton("종료");
	boolean flag = true;
	long start, end;

	public UserInterface_v01() {
			this("제목 없는 윈도우");
		}

	public UserInterface_v01(String title) {
			setUndecorated(true);
			setTitle(title);
			setBounds(800, 50, 400, 300);
			setBackground(Color.PINK);
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
			setLayout(new GridLayout(2, 1));
			button1.addActionListener(this);
			button2.addActionListener(this);
			add(button1);
			add(button2);
			setVisible(true);
		}

	public static void main(String[] args) {

		window = new UserInterface_v01("까꿍~~");

	}

	@Override
	public void run() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
		if (start == 0) {
			start = System.currentTimeMillis();
			end = start;
		}
		while (true) {
			long time = 5000 - (end++ - start) - 32400000;
			setTitle(sdf.format(time));
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (!flag) {
				break;
			}
			if (time == -32400000) {
				JOptionPane.showMessageDialog(window, "죽었어!!!");
				System.exit(0);
			}

		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("시작")) {
			Thread thread = new Thread(window);
			thread.start();
			flag = true;
		} else {
			flag = false;
		}
	}

}