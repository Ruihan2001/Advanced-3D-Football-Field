package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import GraphicsObjects.*;

public class Sphere {
	vertex_buffer buffer;
	
	public Sphere() {
		makeSphere(1.0f, 16, 16);
	}
	
	public Sphere(float radius, int nSlices, int nSegments) {
		makeSphere(radius, nSlices, nSegments);
	}
	
	//Generate sphere vertices
	int makeSphere(float radius, int nSlices, int nSegments) {
		if(nSlices < 3)nSlices = 3;
		if(nSegments < 3)nSegments = 3;
		
		float coord_x_step = 1.0f / nSlices;
        float coord_y_step = 1.0f / nSegments;

        float x_step = 360.0f / (nSlices - 0);
        float y_step = 180.0f / (nSegments - 1);

        double angle_x, angle_y;
        double sine_x, cosine_x;
        double radius_y;
        
        ++nSlices;
        
        // init buffer size
        buffer = new vertex_buffer();
        buffer.begin(nSlices * nSegments, (nSlices - 1) * (nSegments - 1) * 6);

        // init vertices
		for (int y = 0; y < nSegments; ++y) {
			angle_x = Math.toRadians(y * y_step);
            sine_x = Math.sin(angle_x);
            cosine_x = Math.cos(angle_x);
            radius_y = sine_x * radius;
			for (int x = 0; x < nSlices; ++x) {
				angle_y = Math.toRadians(x * x_step);
				
				vertex v = new vertex();
				v.position.x = (float)(-Math.cos(angle_y) * radius_y);
				v.position.y = (float)(cosine_x * radius);
				v.position.z = (float)(Math.sin(angle_y) * radius_y);
				v.normal = v.position.normal();
				//v.color.set(1.0f,  1.0f,  1.0f,  1.0f);
				v.texcoord.set(x * coord_x_step, y * coord_y_step);
				
				buffer.push(v);
			}
		}
		
		// init indices
		int id = 0;
		for (int y = 0; y < nSegments - 1; ++y) {
			for (int x = 0; x < nSlices - 1; ++x) {
				buffer.push(id + x);
				buffer.push(id + x + nSlices);
				buffer.push(id + x + nSlices + 1);
				buffer.push(id + x);
				buffer.push(id + x + nSlices + 1);
				buffer.push(id + x + 1);
			}
			id += nSlices;
		}
		
		return 0;
	}
	
	public void draw(Texture myTexture) {
		


		gl.bind_texture(myTexture);
		buffer.draw_elements(GL11.GL_TRIANGLES);
	}
	
	public void draw(float scale, Texture myTexture) {
		GL11.glPushMatrix();
		GL11.glScalef(scale, scale, scale);
		this.draw(myTexture);
		GL11.glPopMatrix();
	}
	
}
 