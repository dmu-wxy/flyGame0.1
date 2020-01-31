package flyGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class plane extends GameObject{
	boolean left,right,up,down;
	boolean live = true;
	public plane(Image image ,int x, int y) {
		this.setImg(image);
		this.setX(x);
		this.setY(y);
		this.setSpeed(10);
		this.setWidth(constant.planeWidth);
		this.setHeight(constant.planeHeight);
	}
	
	@Override
	public void drawSelf(Graphics g) {
		if(live) {
			super.drawSelf(g);
			if(left) { 	
				this.setX(this.getX() - this.getSpeed());		
			}else if(right) {
				this.setX(this.getX() + this.getSpeed());
			}else if(up) {
				this.setY(this.getY() - this.getSpeed());
			}else if(down) {
				this.setY(this.getY() + this.getSpeed());
			}	
		}else {
			
		}
	}
	
	@Override
	public void setX(int x) {
		if(x <= 0) super.setX(0);
		else if(x >= constant.winWidth - this.getWidth()) super.setX(constant.winWidth - this.getWidth());
		else super.setX(x);
	}
	@Override
	public void setY(int y) {
		if(y <= 30) super.setY(30);
		else if(y >= constant.winHeight - this.getHeight()) super.setY(constant.winHeight - this.getHeight());
		else super.setY(y);
	}
	
	public void addDirection(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		default:
			break;
		}
	}
	
	public void minusDirection(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		case KeyEvent.VK_UP:
			up = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		case KeyEvent.VK_DOWN:
			down = false;
			break;
		default:
			break;
		}
	}
}
