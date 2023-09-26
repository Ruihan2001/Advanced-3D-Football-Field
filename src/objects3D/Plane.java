package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import GraphicsObjects.gl;

public class Plane {
	public Plane()
	{
		
	}

	public void draw(int size, float textureScale, Texture myTexture)
	{
		float w = size / 2.0f;
		float uv = 1.0f / textureScale;
		
		gl.bind_texture(myTexture);
		
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glNormal3f(0.0f, 1.0f, 0.0f);
		for(int x = 0; x < size; ++x) {
			for(int z = 0; z < size; ++z) {
				GL11.glTexCoord2f(x * uv, z * uv);
				GL11.glVertex3f(x - w, 0.0f, z - w);
				
				GL11.glTexCoord2f((x + 1) * uv, z * uv);
				GL11.glVertex3f((x + 1) - w, 0.0f, z - w);
				
				GL11.glTexCoord2f((x + 1) * uv, (z + 1) * uv);
				GL11.glVertex3f((x + 1) - w, 0.0f, (z + 1) - w);
				
				GL11.glTexCoord2f(x * uv, (z + 1) * uv);
				GL11.glVertex3f(x - w, 0.0f, (z + 1) - w);
			}
		}
		GL11.glEnd();
	}
	
	public void draw(int width, int height, Texture myTexture)
	{
		float w = width / 2.0f;
		float h = height / 2.0f;
		float u = 1.0f / width;
		float v = 1.0f / height;
		
		gl.bind_texture(myTexture);
		
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glNormal3f(0.0f, 1.0f, 0.0f);
		for(int x = 0; x < width; ++x) {
			for(int z = 0; z < height; ++z) {
				GL11.glTexCoord2f(x * u, z * v);
				GL11.glVertex3f(x - w, 0.0f, z - h);
				
				GL11.glTexCoord2f((x + 1) * u, z * v);
				GL11.glVertex3f((x + 1) - w, 0.0f, z - h);
				
				GL11.glTexCoord2f((x + 1) * u, (z + 1) * v);
				GL11.glVertex3f((x + 1) - w, 0.0f, (z + 1) - h);
				
				GL11.glTexCoord2f(x * u, (z + 1) * v);
				GL11.glVertex3f(x - w, 0.0f, (z + 1) - h);
			}
		}
		GL11.glEnd();
	}
}
