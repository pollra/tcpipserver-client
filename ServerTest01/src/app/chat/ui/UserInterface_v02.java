package app.chat.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardUpLeftHandler;
import javax.swing.text.html.HTML;

public class UserInterface_v02 extends JFrame implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int ScrollOff = 3;
	private static final int ScrollOn = 1;
	
	private static Point point = new Point();

	private Font fontAll = new Font("돋움", Font.PLAIN, 16);
	
	private String Testtext="";
	
	JPanel centerPanel;
	JPanel topPanel;
	JPanel bottomPanel;
	JPanel nullPanel;
	
	TextArea chatView;
	TextArea inputChar;
	
	
	JLabel titleLabel;
	JLabel chatLabel;
	
	JButton close;
	JButton inputBtn;

	int windowWidth = 350;
	int windowHeigth = 700;

	String outText="";
	
	public UserInterface_v02() {
		this("UserInterface_v02");
	}

	public UserInterface_v02(String title) {
		for(int i=0; i<200; i++) {
			Testtext+="빼애애앢";
		}
		
		centerPanel = new JPanel();
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		nullPanel = new JPanel();
		
		chatView= new TextArea(Testtext, 27, 37, ScrollOff);
		inputChar= new TextArea("",6,37,ScrollOff);
		
		chatLabel = new JLabel();
		
		titleLabel = new JLabel("TcpIpClient v0.1");
		close = new JButton("X");
		inputBtn = new JButton();
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
			centerPanel.setBackground(new Color(204,229,255));
			centerPanel.setPreferredSize(new Dimension(350, 550));
			centerPanel.add("Center", chatLabel);
				chatLabel.setFont(fontAll);
				chatLabel.setPreferredSize(new Dimension(345, 545));
				chatLabel.setHorizontalAlignment(SwingConstants.LEFT);
				chatLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		
		add("South", bottomPanel);
			bottomPanel.setBackground(new Color(204,204,255));
			bottomPanel.setPreferredSize(new Dimension(350, 120));
			bottomPanel.add(inputChar);
				inputChar.addKeyListener(keyComendListener());
			bottomPanel.add("East",inputBtn);
				inputBtn.setPreferredSize(new Dimension(50, 110));
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

	private KeyAdapter keyComendListener() {
		return new KeyAdapter() {
			@Override //누르고 있을때
			public void keyPressed(KeyEvent e) {
				if(e.isShiftDown()&&e.getKeyCode()==10) {
					System.out.println("input Comend");
				}else if(e.getKeyCode()==10) {
					System.out.println("on The Enter");
					
					// 입력한 내용을 서버로 보내는 역할
					inputChar.setText("");
					chatLabel.repaint();
				}
			}
		};
	}

	public String textInput(String text) {
		inputChar.getText().replaceAll("(.*)\n", "<br/>");
		return "";
	}
	
	// 서버와 통신을 담당하는 쓰레드
	@Override
	public void run() {
		
	}

}