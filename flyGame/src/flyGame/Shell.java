package flyGame;

import java.awt.Color;
import java.awt.Graphics;

public class Shell extends GameObject{
	double degree;
	
	final int shellX = 200,shellY = 200;//�ڵ���ʼλ��
	final int shellWidth = 10,shellHeight = 10;//�ڵ���С
	final int shellSpeed = 5; //�ڵ��ٶ�
	
	public Shell() {
		this.setX(shellX);
		this.setY(shellY);
		this.setWidth(shellWidth);
		this.setHeight(shellHeight);
		this.setSpeed(shellSpeed);
		degree = Math.random() * Math.PI * 2;
	}
	
	public void draw(Graphics g) {
		Color c = g.getColor();
		
		g.setColor(Color.yellow);
		g.fillOval(this.getX(),this.getY(),this.getWidth(),this.getHeight());
		this.setX((int)(this.getX() + shellSpeed * Math.cos(degree)));
		this.setY((int)(this.getY() + shellSpeed * Math.sin(degree)));
		
		if(this.getX() <= 0 || this.getX() >= constant.winWidth - shellWidth) {
			degree = Math.PI - degree;
		}
		if(this.getY() <= 30 || this.getY() >= constant.winHeight - shellHeight) {
			degree = 2 * Math.PI - degree;
		}
		
		g.setColor(c);
	}
}
