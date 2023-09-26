package GraphicsObjects;

import org.lwjgl.opengl.GL11;

//Vertex class

public class vertex {
	public static int LENGTH = vec3.LENGTH + vec3.LENGTH + vec2.LENGTH;
	
	public vec3 position = new vec3();
	public vec3 normal = new vec3();
	//public vec4 color = new vec4(1.0f);
	public vec2 texcoord = new vec2();
	
	public vertex()
	{
		
	}
	
	public vertex(vec3 p, vec3 n, vec2 uv)
	{
		position = p;
		normal = n;
		texcoord = uv;
	}
	
	public vertex(float x, float y, float z, float nx, float ny, float nz, float u, float v)
	{
		position.set(x,  y,  z);
		normal.set(nx, ny, nz);
		texcoord.set(u,  v);
	}
	
	public void draw()
	{
		GL11.glTexCoord2f(texcoord.x, texcoord.y);
		GL11.glNormal3f(normal.x, normal.y, normal.z);
		GL11.glVertex3f(position.x, position.y, position.z);
	}
}
