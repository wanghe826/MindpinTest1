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
	private int vX, vY;       //  �ٶ�
	private int currentX, currentY;    //С���λ��
	private int COLOR[] =     //�����ɫ���Ӵ������л�ȡ
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
		r = new Random();    //����࣬  С���λ�ú��ٶȽ����õ�
		currentX = r.nextInt(540-30);     // �˴��� ���ֻ��ķֱ����� 960*540��
		currentY = r.nextInt(960-30);
		
		vX = r.nextInt(5)-3;
		vY = r.nextInt(5)-3;
		p = new Paint(); 
		randomColor = r.nextInt(9);
		p.setColor(COLOR[randomColor]);
		sfh = getHolder();
		sfh.addCallback(this);
	}
	
	//һ������SurfaceView ��ͼ�����ô˺���
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
		//�ظ����ñ���ɫ
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

