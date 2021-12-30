package org.openjfx.Tetris;


import java.util.Arrays;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

public class matchfield {
	
	private StackPane pane=new StackPane();
	private GridPane grid=new GridPane();
	private Label[][]lbls=new Label[20][10];
	private Brick brick;
	private Point2D[] shape;
	private Timeline timer;
	
	
	private int score=0,limit=500;
	public Boolean timerenabled=true;
	private int tempo=280;
	matchfield() {
		
		for (int i = 0; i/10 < 20; i++) {
			lbls[i/10][i%10]=new Label();
			lbls[i/10][i%10].setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, null, null)));
			grid.add(lbls[i/10][i%10], i%10, i/10);
		}
		grid.setGridLinesVisible(true);
		
		brick =new Brick();
		shape=brick.givbrick();
		
		
		pane.getChildren().add(grid);
		
		pane.setOnKeyPressed(e->{
		
			if(e.getCode()==KeyCode.RIGHT) {
				var move =true;cleanbrick();
				for (Point2D p : shape) {
					if((int)(p.getX()+brick.givposition().getX())>4||p.getY()+brick.givposition().getY()>17)move=false;
					else if(lbls[(int)(p.getY()+brick.givposition().getY())+1]
				[(int)(p.getX()+brick.givposition().getX())+5].getBackground().getFills().get(0).getFill()!=Color.ALICEBLUE)
					move=false;
				}

		if(move)brick.moveRight();drawbrick();
			
			}
			if(e.getCode()==KeyCode.LEFT) { 
				var move =true;cleanbrick();
				for (Point2D p : shape) {
					if((int)(p.getX()+brick.givposition().getX())+3<0||p.getY()+brick.givposition().getY()>17)move=false;
					else if(lbls[(int)(p.getY()+brick.givposition().getY())+1]
							[(int)(p.getX()+brick.givposition().getX())+3].getBackground().getFills().get(0).getFill()!=Color.ALICEBLUE)
								move=false;
				}
				if(move)brick.moveLeft();drawbrick();
			}
			if(e.getCode()==KeyCode.DOWN) {
				cleanbrick();var move=true;
				for (Point2D p : shape) {
					if(p.getY()+brick.givposition().getY()>17)move=false;
					else if(lbls[(int)(p.getY()+brick.givposition().getY()+2)]
							[(int)(p.getX()+brick.givposition().getX()+4)]
									.getBackground().getFills().get(0).getFill()!=Color.ALICEBLUE)
						move=false;
				}
				if(move)brick.moveDown();
				drawbrick();
			}
			if(e.getCode()==KeyCode.UP) {
				cleanbrick();var move =true;
				var copy=Arrays.copyOf(shape, shape.length);
				for (int i = 0; i < copy.length; i++) {
					copy[i]=new Point2D(copy[i].getY()*-1,copy[i].getX());
					if((int)(copy[i].getX()+brick.givposition().getX())+4<0)move=false;
					else if((int)(copy[i].getX()+brick.givposition().getX())>5)move=false;
					else if((int)(copy[i].getY()+brick.givposition().getY())>17)move=false;
					else if(lbls[(int)(copy[i].getY()+brick.givposition().getY())+2]
				[(int)(copy[i].getX()+brick.givposition().getX())+4].getBackground().getFills().get(0).getFill()!=Color.ALICEBLUE)
					move=false;
				}
				if(move)brick.rotatebrick();
				drawbrick();
			}
		});
		settimer();
	}
	

	private void checkRows() {
		int z=0,lines=0,pos=0;var col=new Paint[20][10];
		for (int i = 0; i/10 < 20; i++) {
			if(i%10==0)z=0;
			if(lbls[i/10]
					[i%10].getBackground().getFills().get(0).getFill()!=Color.ALICEBLUE)
				z++;
			if(z==10) {pos=i/10;lines++;
			col[i/10][i%10]=lbls[i/10][i%10].getBackground().getFills().get(0).getFill();
			}
		}
		
		if(lines>0) {
			for (int i = 0; i/10 < 20; i++) {
				col[i/10][i%10]=lbls[i/10][i%10].getBackground().getFills().get(0).getFill();
				}
			
		for (int i = lines; i <= pos; i++) {
			for (int j = 0; j < 10; j++) {
				lbls[i][j].setBackground(new Background(new BackgroundFill(col[i-lines][j], null, null)));
			}
		}
		score+=((lines%10)*(int)(Math.random()*100+10));
		if(score>limit&&timerenabled)
		{tempo-=2;
		settimer();limit+=(int)(Math.random()*600+500);
		timer.play();}
	
	
	}}
	private void settimer() {
		
		timer=new Timeline(new KeyFrame(Duration.millis(tempo), e->{
			cleanbrick();//pane.requestFocus();
			var move=true;
			for (Point2D p : shape) {
				if(p.getY()+brick.givposition().getY()>17) {
					move=false;
				}
				else if(lbls[(int)(p.getY()+brick.givposition().getY()+2)]
						[(int)(p.getX()+brick.givposition().getX()+4)]
								.getBackground().getFills().get(0).getFill()!=Color.ALICEBLUE)
					move=false;
			}
			var full=false;
			if(move&&timerenabled)brick.moveDown();
			else if(!timerenabled) {cleanbrick();tempo=290;settimer();limit=10;score=0;timer.stop();}
			else if(!move){
				drawbrick();brick=new Brick();shape=brick.givbrick();
				checkRows();
				for (Point2D p : shape) {
					if(p.getY()+brick.givposition().getY()<3&&lbls[(int)(p.getY()+brick.givposition().getY()+2)]
							[(int)(p.getX()+brick.givposition().getX()+4)]
									.getBackground().getFills().get(0).getFill()!=Color.ALICEBLUE) {
						for (int i = 0; i/10 < 20; i++) {
							lbls[i/10][i%10].setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, null, null)));
						}
					full=true;
					}}
			
			
			}
			if(full){
				timerenabled=false;}else if(timerenabled)  drawbrick();
				
		}));
	
		timer.setCycleCount(Animation.INDEFINITE);//timer.play();
	}
	public void sizePane() {
		for (int i = 0; i/10 < 20 ; i++) {
			lbls[i/10][i%10].setMinWidth(pane.getHeight()/22);
			lbls[i/10][i%10].setMinHeight(pane.getHeight()/22);
		}
	}
	public StackPane givgame() {
		return pane;
	}
	public Timeline givteimer() {
			settimer();
		return timer;
	}
	public int givscore() {
		return score;
	}
	
	private void cleanbrick() {
		for (Point2D p : shape) {
			lbls[1+(int)(p.getY()+brick.givposition().getY())][4+(int)(p.getX()+brick.givposition().getX())].setBackground(brick.nocol());
		}
	}
private void drawbrick() {
	for (Point2D p : shape) {
		lbls[1+(int)(p.getY()+brick.givposition().getY())][4+(int)(p.getX()+brick.givposition().getX())].setBackground(brick.givcol());
	}
	}


}
