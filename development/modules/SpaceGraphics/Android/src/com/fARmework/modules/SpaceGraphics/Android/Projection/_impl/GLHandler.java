package com.fARmework.modules.SpaceGraphics.Android.Projection._impl;

import java.nio.*;

import javax.microedition.khronos.opengles.*;

import android.opengl.*;

import com.fARmework.modules.SpaceGraphics.Android.Models.*;
import com.fARmework.modules.SpaceGraphics.Android.Projection.*;

public class GLHandler implements IGLHandler 
{
	private float[] _rotationMatrix = new float[16];
	private float _direction = 0.0f;
	
	private float[] _light = { 1.0f, 1.0f, 1.0f, 1.0f };
	
	@Override
	public void initialize(GL10 gl)
	{
		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_CULL_FACE);
		gl.glCullFace(GL10.GL_BACK);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		gl.glEnable(GL10.GL_LIGHTING);
		gl.glEnable(GL10.GL_LIGHT0);
		gl.glEnable(GL10.GL_COLOR_MATERIAL);
	
		gl.glShadeModel(GL10.GL_SMOOTH);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST); 
	}
	
	@Override
	public void setViewport(GL10 gl, int width, int height) 
	{
		gl.glViewport(0, 0, width, height);
		
		float ratio = (float) width / height;
		
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrthof(-ratio, ratio, -1.0f, 1.0f, 1.0f, 10.0f);
	}
	
	@Override
	public void setRotationMatrix(float[] rotationMatrix)
	{
		_rotationMatrix = rotationMatrix;
	}
	
	@Override
	public void setDirection(float direction)
	{
		_direction = direction;
	}
	
	@Override
	public void draw(GL10 gl, Model model) 
	{
		float[] backgroundColor = model.getBackgroundColor();
		
		gl.glClearColor(backgroundColor[0], 
						backgroundColor[1],
						backgroundColor[2],
						backgroundColor[3]);		
		
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, _light, 0);
		
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		
		GLU.gluLookAt(gl, 0.0f, 0.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
		
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, allocateBuffer(model.getVertices()));
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, allocateBuffer(model.getColors()));
		gl.glNormalPointer(GL10.GL_FLOAT, 0, allocateBuffer(model.getNormals()));
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);

			gl.glPushMatrix();
				gl.glMultMatrixf(_rotationMatrix, 0);
				gl.glRotatef(-1.0f * _direction, 0.0f, 0.0f, 1.0f);
				gl.glDrawArrays(GL10.GL_TRIANGLES, 0, model.getVerticesAmount());
			gl.glPopMatrix();

		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);		
	}
	
	public Buffer allocateBuffer(float[] array)
	{
		final int FLOAT_SIZE = 4;
		
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(array.length * FLOAT_SIZE);
		
		FloatBuffer floatBuffer = byteBuffer.order(ByteOrder.nativeOrder()).asFloatBuffer();
		
		floatBuffer.put(array).position(0);

		return floatBuffer;
	}
}
