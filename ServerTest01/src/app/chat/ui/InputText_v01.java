package app.chat.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.StyledEditorKit.BoldAction;

public class InputText_v01 extends JFrame {

	private static Point point;
	
	public String outText;
	
	Color topPanelColor, centerPanelColor;
	Font allFont, topFont;
	
	JLabel nameLabel;
	JLabel nullLabel;
	JButton close;
	
	JPanel topPanel;
	JPanel centerPanel;
	
	JLabel textLabel;
	JTextPane textPane;
	
	int windowWidth = 250;
	int windowHeigth = 120;
	
	Scanner sc;
	
	
	public InputText_v01() {
		this(-999, -999);
	}
	public InputText_v01(int superWidth, int superHeight) {
		// 모니터 화면 가운데에 위치하게함
		windowCenterSetting(superWidth, superHeight);
		
		// 초기화
		point = new Point();
		allFont = new Font("굴림", FRAMEBITS, 12);
		topFont = new Font("굴림", Font.BOLD, 16);
		
		topPanelColor = new Color(208,255,255);
		centerPanelColor = new Color(204, 229, 255);
		
		nameLabel = new JLabel("Input Name");
		nullLabel = new JLabel("");
		close = new JButton(new ImageIcon("src/img/X_Btn.png"));
		
		topPanel = new JPanel();
		centerPanel = new JPanel();
		
		textLabel = new JLabel("채팅에 사용할 닉네임을 입력후 엔터");
		textPane = new JTextPane();
		
		// 각 위치 지정
		add("North",topPanel);
		add("Center", centerPanel);
		
		topPanel.add("North",nameLabel);
		topPanel.add("North", nullLabel);
		topPanel.add("North",close);
		
		centerPanel.add(textLabel);
		centerPanel.add(textPane);
		textPane.addKeyListener(keyComendListener());
		
		// 크기 지정
		topPanel.setPreferredSize(new Dimension(windowWidth, 30));
		textPane.setPreferredSize(new Dimension(180, 30));
		nullLabel.setPreferredSize(new Dimension(90, 30));
		close.setPreferredSize(new Dimension(15, 15));
		
		// 폰트 지정
		nameLabel.setFont(topFont);
		textLabel.setFont(allFont);
		textPane.setFont(allFont);
		
		// 색깔 지정
		topPanel.setBackground(topPanelColor);
		centerPanel.setBackground(centerPanelColor);
		
		close.setBorderPainted(false); 
		close.setFocusPainted(false); 
		close.setContentAreaFilled(false);
		
		// 포커스 지정
		textPane.grabFocus();
		// close 버튼 활성화
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
		
		setUndecorated(true);
		setVisible(true);
		
	}
	private void windowCenterSetting(int superWidth, int superHeight) {
		Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
		if(superWidth==-999) {
			setBounds(dim.width / 2 - (windowWidth/2),
					dim.height / 2-(windowHeigth/2),
					windowWidth, windowHeigth);
		}else {// (디스플레이 크기 - (디스플레이 크기 - super크기 + this크기))/2 + (디스플레이크기 -super크기)
			setBounds((dim.width-(dim.width-superWidth+windowWidth))/2+(dim.width - superWidth),
					(dim.height-(dim.height-superHeight+windowHeigth))/2+(dim.height - superHeight),
				windowWidth, windowHeigth);
		}
	}
	private KeyAdapter keyComendListener() {
		return new KeyAdapter() {
			@Override //누르고 있을때
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10) {
					System.out.print("Insert name :");
					sc = new Scanner(textPane.getText());
					outText = sc.nextLine();
					System.out.println(outText);
					setVisible(false);
				}
			}
		};
	}
	public String getOutText() {
		return outText;
	}
	public void setOutText(String outText) {
		this.outText = outText;
	}
	
	
	
}
