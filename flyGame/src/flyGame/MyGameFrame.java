package flyGame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Date;

import javax.swing.JFrame;

public class MyGameFrame extends Frame{


	Image planeImage = GameUtil.getImage("image/fj.png");
	Image bg = GameUtil.getImage("image/bg.png");
	
	plane plane = new plane(planeImage,constant.planeX,constant.planeY);
	Shell[] shells = new Shell[constant.ShellNum];
	Explode beng;
	Date startTime = new Date();
	Date endTime;
	double period;//持续时间
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(bg, 0, 0, null);
		plane.drawSelf(g);
		
		for(int i = 0;i < constant.ShellNum;i++) {
			shells[i].draw(g);
			if(shells[i].getRec().intersects(plane.getRec())) {
				plane.live = false;
				if(beng == null) {
					beng = new Explode(plane.getX(),plane.getY());
					endTime = new Date();
					period = (endTime.getTime() - startTime.getTime()) / 1000;
				}
				beng.draw(g);
			}
			if(plane.live == false) {
				Color c = g.getColor();
				Font f = g.getFont();
				g.setColor(Color.BLUE);
				g.setFont(new Font("宋体",Font.BOLD,25));
				g.drawString("游戏时间：" + period + "秒", plane.getX(), plane.getY());
				g.setColor(c);
				g.setFont(f);
			}
		}
	}
	/*
	 * 用线程重画
	 */
	class paintThread extends Thread{
		@Override
		public void run() {
			while(true) {
				repaint();
				
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	class keyMonitor extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			plane.addDirection(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			plane.minusDirection(e);
		}
		
	}
	/*
	 * 双缓冲技术解决窗口闪烁
	 */
	private Image offScreenImage = null;
	
	public void update(Graphics g) {
		if(offScreenImage == null) 
			offScreenImage = this.createImage(constant.winWidth,constant.winHeight);//游戏窗口宽度和高度
		Graphics goff = offScreenImage.getGraphics();
		paint(goff);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	
	public void launchFrame() {
		this.setTitle("flyGame");
		this.setVisible(true);
		this.setSize(constant.winWidth,constant.winHeight);
		this.setLocation(500,0);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		
		new paintThread().start();
		addKeyListener(new keyMonitor());
		
		
		for(int i = 0;i < constant.ShellNum;i++) {
			shells[i] = new Shell();
		}
	}
	
	
	public static void main(String[] args) {
		MyGameFrame flyGame = new MyGameFrame();
		
		flyGame.launchFrame();
		
	}
}
