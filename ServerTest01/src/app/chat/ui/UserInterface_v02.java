package app.chat.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UserInterface_v02 extends JFrame implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int ScrollOff = 3;
	private static final int ScrollOn = 1;
	
	private static Point point = new Point();

	private Font fontAll = new Font("돋움", Font.PLAIN, 16);
	
	private String Testtext = "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사"
			+ "가나다라마바사아자차카타파하가나다라마바사";
	
	JPanel centerPanel;
	JPanel topPanel;
	JPanel bottomPanel;
	JPanel nullPanel;
	
	TextArea chatView;
	TextArea inputChar;
	
	
	JLabel titleLabel;
	
	JButton close;

	int windowWidth = 350;
	int windowHeigth = 700;

	public UserInterface_v02() {
		this("UserInterface_v02");
	}

	public UserInterface_v02(String title) {
		
		centerPanel = new JPanel();
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		nullPanel = new JPanel();
		
		chatView= new TextArea(Testtext, 27, 37, ScrollOff);
		inputChar= new TextArea("",6,37,ScrollOff);
		
		titleLabel = new JLabel("TcpIpClient v0.1");
		close = new JButton("X");
		
		setUndecorated(true);
		setTitle(title);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(dim.width - windowWidth, (dim.height - windowHeigth - 40), windowWidth, windowHeigth);
		this.setLayout(new BorderLayout());
		
		add("North", topPanel);
			topPanel.setBackground(new Color(208,255,255));
			topPanel.setPreferredSize(new Dimension(350, 30));
			topPanel.add("West",titleLabel);
			topPanel.add("center", nullPanel);
				nullPanel.setPreferredSize(new Dimension(180, 30));
				nullPanel.setBackground(new Color(208,255,255));
			topPanel.add("East",close);
		
		add("Center", centerPanel);
			centerPanel.add("Center",chatView);
			centerPanel.setBackground(new Color(204,229,255));
			centerPanel.setPreferredSize(new Dimension(350, 550));
		
		
		
		add("South", bottomPanel);
			bottomPanel.setBackground(new Color(204,204,255));
			bottomPanel.setPreferredSize(new Dimension(350, 120));
			bottomPanel.add(inputChar);
		
		chatView.setFont(fontAll);
		
		close.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		
		
		topPanel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				point.x = e.getX();
				point.y = e.getY();
			}
		});
		topPanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Point p = getLocation();
				setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y);
			}

		});
		setVisible(true);
	}

	@Override
	public void run() {
		
		
	}

}