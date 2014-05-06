package com.example.mindpintest1;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class Ball extends SurfaceView implements Callback, Runnable
{
	private int width, height;
	private SurfaceHolder sfh;
	private Paint p;
	private int vX, vY;       //  速度
	private int currentX, currentY;    //小球的位置
	private int COLOR[] =     //随机颜色将从此数组中获取
		{
			Color.BLACK,
			Color.BLUE,
			Color.CYAN,
			Color.DKGRAY,
			Color.GRAY,
			Color.GREEN,
			Color.LTGRAY,
			Color.RED,
			Color.MAGENTA
		};
	private Random r;
	private int randomColor;
	private Canvas canvas;
	private boolean isRunnable = true;
	public Ball(Context context)
	{
		super(context);
		r = new Random();    //随机类，  小球的位置和速度将会用到
		currentX = r.nextInt(540-30);     // 此处， 我手机的分辨率是 960*540的
		currentY = r.nextInt(960-30);
		
		vX = r.nextInt(5)-3;
		vY = r.nextInt(5)-3;
		p = new Paint(); 
		randomColor = r.nextInt(9);
		p.setColor(COLOR[randomColor]);
		sfh = getHolder();
		sfh.addCallback(this);
	}
	
	//一旦创建SurfaceView 视图即调用此函数
	public void surfaceCreated(SurfaceHolder sfh)
	{
		isRunnable = true;
		new Thread(this).start();
	}
	public void surfaceChanged(SurfaceHolder sfh, int format, int width, int height)
	{
	}
	public void surfaceDestroyed(SurfaceHolder sfh)
	{	
		isRunnable = false;
	}
	public void run()
	{
		while(isRunnable)
		{
			canvas = sfh.lockCanvas();
			currentX = currentX + vX;
			currentY = currentY + vY;
			draw(canvas);
		
		}
	}
	public void draw(Canvas canvas)
	{
		//重复设置背景色
		if(canvas != null)
		{
			canvas.drawColor(Color.TRANSPARENT, android.graphics.PorterDuff.Mode.CLEAR);
		}
		try
		{
			synchronized(sfh)
			{
				
				canvas.drawCircle(currentX, currentY, 30, p);
				canvas.save();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(canvas != null)
			{
				sfh.unlockCanvasAndPost(canvas);
			}
		}
		
		/*try
		{
			Thread.sleep(200);
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		} */
	}
}

