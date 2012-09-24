package com.fARmework.modules.SpaceGraphics.Graphics._impl;

import com.fARmework.modules.SpaceGraphics.Graphics.*;

import java.nio.*;

import javax.microedition.khronos.opengles.*;

import android.opengl.*;
import android.util.*;

public class GLHandler implements IGLHandler 
{	
	private float[] _modelMatrix = new float[16];
	private float _direction = 0.0f;
	
	@Override
	public void initialize(GL10 gl)
	{
		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_CULL_FACE);
		gl.glCullFace(GL10.GL_BACK);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
	
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
		gl.glFrustumf(-ratio, ratio, -1.0f, 1.0f, 1.0f, 10.0f);
	}
	
	@Override
	public void setRotationMatrix(float[] rotationMatrix)
	{
		_modelMatrix = rotationMatrix;
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
		
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		
		GLU.gluLookAt(gl, 0.0f, 0.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
		
		ByteBuffer vertexByteBuffer = ByteBuffer.allocateDirect(14 * 3 * 4);
		FloatBuffer vertexBuffer = vertexByteBuffer.order(ByteOrder.nativeOrder()).asFloatBuffer();
		
		for(int i = 0; i < 14; ++i)
		{
			Log.d("BUFFER", "i = " + i);
			float x = model.getVertexBuffer().get();
			float y = model.getVertexBuffer().get();
			float z = model.getVertexBuffer().get();
			
			float[] v = { x, y, z, 1.0f };
			float[] result = new float[4];
			
			Matrix.multiplyMV(result, 0, _modelMatrix, 0, v, 0);
			
			float length = FloatMath.sqrt((result[0] * result[0]) + (result[1] * result[1]) + (result[2] * result[2]));
			
			//result[0] = result[0] / length;
			//result[1] = result[1] / length;
			//result[2] = result[2] / length;
			
			vertexBuffer.put(result[0]);
			vertexBuffer.put(result[1]);
			vertexBuffer.put(result[2]);
		}
		
		model.getVertexBuffer().position(0);
		vertexBuffer.position(0);
		
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, model.getColorBuffer());
		gl.glNormalPointer(GL10.GL_FLOAT, 0, model.getNormalBuffer());
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);

			gl.glDrawElements(GL10.GL_TRIANGLES, model.getVerticesAmount(), GL10.GL_UNSIGNED_BYTE, model.getIndexBuffer());

		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);		
	}
}
