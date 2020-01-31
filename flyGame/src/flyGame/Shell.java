package flyGame;

import java.awt.Color;
import java.awt.Graphics;

public class Shell extends GameObject{
	double degree;
	
	final int shellX = 200,shellY = 200;//炮弹初始位置
	final int shellWidth = 10,shellHeight = 10;//炮弹大小
	final int shellSpeed = 5; //炮弹速度
	
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
