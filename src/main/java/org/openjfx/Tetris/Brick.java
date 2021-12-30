package org.openjfx.Tetris;

import javafx.geometry.Point2D;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class Brick {
	private Point2D[] brick;
	private Background bg;
	private Point2D pos=new Point2D(0,0);
	private boolean rotate;
	
	
	Brick(){
	
		brick=setbrick();
	}
	
	public Point2D[]givbrick(){
		return brick;
	}
	
	public Background givcol() {
		return bg;
	}
	public Background nocol() {
		
		return new Background(new BackgroundFill(Color.ALICEBLUE, null, null));
	}
	public Point2D givposition() {
		return pos;
	}
	public void moveLeft() {
		pos=new Point2D(pos.getX()-1,pos.getY());
	}
	public void moveRight() {
		pos=new Point2D(pos.getX()+1,pos.getY());
	}public void moveDown() {
		pos=new Point2D(pos.getX(),pos.getY()+1);
	}
	public void rotatebrick() {
		if(rotate) {
		for (int i = 0; i < brick.length; i++) {
			brick[i]=new Point2D(brick[i].getY()*-1,brick[i].getX());
		};
		}
	}
	private Point2D[] setbrick() {
		switch ((int)(Math.random()*7)) {
		case 0:bg=new Background(new BackgroundFill(Color.DARKOLIVEGREEN, null, null));
			rotate=true;
			return new Point2D[] {
				new Point2D(0,0),
				new Point2D(-1,0),
				new Point2D(1,0),
				new Point2D(2,0)
			};
		case 1:bg=new Background(new BackgroundFill(Color.FORESTGREEN, null, null));
			rotate=true;
			return new Point2D[] {
				new Point2D(1,-1),
				new Point2D(-1,0),
				new Point2D(0,0),
				new Point2D(1,0)
			};
		case 2:bg=new Background(new BackgroundFill(Color.DARKGREEN, null, null));
			rotate=true;
			return new Point2D[] {
				new Point2D(0,0),
				new Point2D(-1,0),
				new Point2D(1,0),
				new Point2D(1,1)
			};
		case 3:bg=new Background(new BackgroundFill(Color.LIGHTGREEN, null, null));
			rotate=false;
			return new Point2D[] {
				new Point2D(0,0),
				new Point2D(0,1),
				new Point2D(1,0),
				new Point2D(1,1)
			};
		case 4:bg=new Background(new BackgroundFill(Color.LIMEGREEN, null, null));
			rotate=true;
			return new Point2D[] {
				new Point2D(0,0),
				new Point2D(-1,0),
				new Point2D(0,-1),
				new Point2D(1,-1)
			};
		case 5:bg=new Background(new BackgroundFill(Color.LAWNGREEN, null, null));
			rotate=true;
			return new Point2D[] {
				new Point2D(0,0),
				new Point2D(0,-1),
				new Point2D(0,1),
				new Point2D(-1,0)
			};
		case 6:bg=new Background(new BackgroundFill(Color.GREEN, null, null));
		rotate=true;
		return new Point2D[] {
			new Point2D(0,0),
			new Point2D(-1,0),
			new Point2D(0,1),
			new Point2D(1,1)
		};		
	       default:
               return null;
	
		}
	}

	
}
