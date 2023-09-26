package GraphicsObjects;

import java.nio.ByteOrder;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.opengl.GL11;

public class vertex_buffer {
	FloatBuffer vertices;
	FloatBuffer normals;
	FloatBuffer texcoords;
	IntBuffer indices;
	
	public vertex_buffer()
	{
		
	}
	
	public static FloatBuffer allocFloatBuffer(int size)
	{
		ByteBuffer buf = ByteBuffer.allocateDirect(size * 4);
		buf.order(ByteOrder.nativeOrder());
		FloatBuffer fbuf = buf.asFloatBuffer();
		fbuf.position(0);
		return fbuf;
	}
	
	public static IntBuffer allocIntBuffer(int size)
	{
		ByteBuffer buf = ByteBuffer.allocateDirect(size * 4);
		buf.order(ByteOrder.nativeOrder());
		return buf.asIntBuffer();
	}
	
	// vertex only
	public void begin(int vertexSize)
	{
		vertices = allocFloatBuffer(vec3.LENGTH * vertexSize);
		normals = allocFloatBuffer(vec3.LENGTH * vertexSize);
		texcoords = allocFloatBuffer(vec2.LENGTH * vertexSize);
	}
	
	// vertex + index
	public void begin(int vertexSize, int indexSize)
	{
		this.begin(vertexSize);
		indices = allocIntBuffer(indexSize);
	}
	
	public void end()
	{
		vertices.flip();
		normals.flip();
		texcoords.flip();
		if(indices != null) {
			indices.flip();
		}
	}
	
	public void push(float x, float y, float z, float nx, float ny, float nz, float u, float v)
	{
		vertices.put(x).put(y).put(z);
		normals.put(nx).put(ny).put(nz);
		texcoords.put(u).put(v);
	}
	
	public void push(vec3 position, vec3 normal, vec2 texcoord)
	{
		vertices.put(position.x).put(position.y).put(position.z);
		normals.put(normal.x).put(normal.y).put(normal.z);
		texcoords.put(texcoord.x).put(texcoord.y);
	}
	
	public void push(vertex v)
	{
		this.push(v.position, v.normal, v.texcoord);
	}
	
	public void push(int n)
	{
		indices.put(n);
	}
	
	public void push(int arr[])
	{
		indices.put(arr);
	}
	
	// draw
	
	public void draw_arrays(int mode, int offset, int size)
	{
		GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
		GL11.glEnableClientState(GL11.GL_NORMAL_ARRAY);
		GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
		
		vertices.position(0);
		normals.position(0);
		texcoords.position(0);
		
		GL11.glVertexPointer(3, 0, vertices);
		GL11.glNormalPointer(0, normals);
		GL11.glTexCoordPointer(2, 0, texcoords);
		
		GL11.glDrawArrays(mode, offset, size);
		
		GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
		GL11.glDisableClientState(GL11.GL_NORMAL_ARRAY);
		GL11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
	}
	
	public void draw_elements(int mode)
	{
		GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
		GL11.glEnableClientState(GL11.GL_NORMAL_ARRAY);
		GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
		
		vertices.position(0);
		normals.position(0);
		texcoords.position(0);
		
		GL11.glVertexPointer(3, 0, vertices);
		GL11.glNormalPointer (0, normals);
		GL11.glTexCoordPointer(2, 0, texcoords);
		
		indices.position(0);
		GL11.glDrawElements(mode, indices);
		
		GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
		GL11.glDisableClientState(GL11.GL_NORMAL_ARRAY);
		GL11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
	}
}
