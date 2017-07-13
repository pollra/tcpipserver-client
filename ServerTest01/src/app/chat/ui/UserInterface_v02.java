package app.chat.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.Border;
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
	
	JPanel centerPanel, topPanel, bottomPanel, nullPanel;
	
	JTextArea chatView, inputChar;
	JScrollPane scrollPane;
	
	JLabel titleLabel, chatLabel;
	
	JButton close, inputBtn;
	
	Color centerColor, topColor, bottomColor;

	int windowWidth = 354;
	int windowHeigth = 700;

	String outText="";
	
	Scanner sc;
	
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
		
		centerColor = new Color(204,229,255);
		topColor = new Color(208,255,255);
		bottomColor = new Color(204,204,255);
		
		chatView= new JTextArea("", 33, 31);
			chatView.setBackground(centerColor);
			chatView.setEditable(false);
		inputChar= new JTextArea("",7,25);
		scrollPane = new JScrollPane(chatView);
		
		chatLabel = new JLabel();
		
//		sc = new Scanner(inputChar.getText());
		
		titleLabel = new JLabel("TcpIpClient v0.1");
		close = new JButton("X");
		inputBtn = new JButton(new ImageIcon("src/img/inputBtn.png"));
		setUndecorated(true);
		setTitle(title);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(dim.width - windowWidth, (dim.height - windowHeigth - 40), windowWidth, windowHeigth);
		this.setLayout(new BorderLayout());
		
		Border lineBorder = BorderFactory.createLineBorder(Color.WHITE, 0);
		add("North", topPanel);
			topPanelSetting();
		
		add("Center", centerPanel);
			centerPanelSetting(lineBorder);
		add("South", bottomPanel);
			bottomPanelSetting(lineBorder);
		
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

	private void bottomPanelSetting(Border lineBorder) {
		bottomPanel.setBackground(bottomColor);
		bottomPanel.setPreferredSize(new Dimension(350, 120));
		bottomPanel.add("Center",inputChar);
			inputChar.setBorder(lineBorder);
			inputChar.addKeyListener(keyComendListener());
			
			bottomPanel.add("East",inputBtn);
			inputBtn.setPreferredSize(new Dimension(50, 110));
			inputBtn.setBorderPainted(false); 
			inputBtn.setFocusPainted(false); 
			inputBtn.setContentAreaFilled(false);
	}

	private void centerPanelSetting(Border lineBorder) {
		centerPanel.setBackground(centerColor);
		centerPanel.setPreferredSize(new Dimension(350, 550));
		centerPanel.add("Center", scrollPane);
		scrollPane.setFont(fontAll);
		scrollPane.setBorder(lineBorder);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	}

	private void topPanelSetting() {
		topPanel.setBackground(topColor);
		topPanel.setPreferredSize(new Dimension(350, 30));
		topPanel.add("West",titleLabel);
		topPanel.add("center", nullPanel);
			nullPanel.setPreferredSize(new Dimension(180, 30));
			nullPanel.setBackground(topColor);
		topPanel.add("East",close);
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
					sc = new Scanner(inputChar.getText());
					sc.nextLine();
					inputChar.setText(" ");
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
		new UiService_v02("Guest");
		
	}

}