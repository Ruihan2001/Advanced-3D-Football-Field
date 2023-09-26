package objects3D;

import org.lwjgl.opengl.GL11;

import GraphicsObjects.*;

public class Cube {
	
	vertex_buffer buffer;

	public Cube()
	{
		this.init();
	}
	
	public void init()
	{
		buffer = new vertex_buffer();
		buffer.begin(24, 36);
		
		/*
		//front
		buffer.push(new vec3(1.0f, 1.0f, -1.0f), new vec3(0.0f, 0.0f, -1.0f), new vec2(0.0f, 0.0f));
		buffer.push(new vec3(1.0f, -1.0f, -1.0f), new vec3(0.0f, 0.0f, -1.0f), new vec2(0.0f, 1.0f));
		buffer.push(new vec3(-1.0f, -1.0f, -1.0f), new vec3(0.0f, 0.0f, -1.0f), new vec2(1.0f, 1.0f));
		buffer.push(new vec3(-1.0f, 1.0f, -1.0f), new vec3(0.0f, 0.0f, -1.0f), new vec2(1.0f, 0.0f));

		//back
		buffer.push(new vec3(-1.0f, 1.0f, 1.0f), new vec3(0.0f, 0.0f, 1.0f), new vec2(0.0f, 0.0f));
		buffer.push(new vec3(-1.0f, -1.0f, 1.0f), new vec3(0.0f, 0.0f, 1.0f), new vec2(0.0f, 1.0f));
		buffer.push(new vec3(1.0f, -1.0f, 1.0f), new vec3(0.0f, 0.0f, 1.0f), new vec2(1.0f, 1.0f));
		buffer.push(new vec3(1.0f, 1.0f, 1.0f), new vec3(0.0f, 0.0f, 1.0f), new vec2(1.0f, 0.0f));

		//left
		buffer.push(new vec3(-1.0f, 1.0f, -1.0f), new vec3(-1.0f, 0.0f, 0.0f), new vec2(0.0f, 0.0f));
		buffer.push(new vec3(-1.0f, -1.0f, -1.0f), new vec3(-1.0f, 0.0f, 0.0f), new vec2(0.0f, 1.0f));
		buffer.push(new vec3(-1.0f, -1.0f, 1.0f), new vec3(-1.0f, 0.0f, 0.0f), new vec2(1.0f, 1.0f));
		buffer.push(new vec3(-1.0f, 1.0f, 1.0f), new vec3(-1.0f, 0.0f, 0.0f), new vec2(1.0f, 0.0f));

		//right
		buffer.push(new vec3(1.0f, 1.0f, 1.0f), new vec3(1.0f, 0.0f, 0.0f), new vec2(0.0f, 0.0f));
		buffer.push(new vec3(1.0f, -1.0f, 1.0f), new vec3(1.0f, 0.0f, 0.0f), new vec2(0.0f, 1.0f));
		buffer.push(new vec3(1.0f, -1.0f, -1.0f), new vec3(1.0f, 0.0f, 0.0f), new vec2(1.0f, 1.0f));
		buffer.push(new vec3(1.0f, 1.0f, -1.0f), new vec3(1.0f, 0.0f, 0.0f), new vec2(1.0f, 0.0f));

		//top
		buffer.push(new vec3(-1.0f, 1.0f, -1.0f), new vec3(0.0f, 1.0f, 0.0f), new vec2(0.0f, 0.0f));
		buffer.push(new vec3(-1.0f, 1.0f, 1.0f), new vec3(0.0f, 1.0f, 0.0f), new vec2(0.0f, 1.0f));
		buffer.push(new vec3(1.0f, 1.0f, 1.0f), new vec3(0.0f, 1.0f, 0.0f), new vec2(1.0f, 1.0f));
		buffer.push(new vec3(1.0f, 1.0f, -1.0f), new vec3(0.0f, 1.0f, 0.0f), new vec2(1.0f, 0.0f));

		//bottom
		buffer.push(new vec3(-1.0f, -1.0f, 1.0f), new vec3(0.0f, -1.0f, 0.0f), new vec2(0.0f, 0.0f));
		buffer.push(new vec3(-1.0f, -1.0f, -1.0f), new vec3(0.0f, -1.0f, 0.0f), new vec2(0.0f, 1.0f));
		buffer.push(new vec3(1.0f, -1.0f, -1.0f), new vec3(0.0f, -1.0f, 0.0f), new vec2(1.0f, 1.0f));
		buffer.push(new vec3(1.0f, -1.0f, 1.0f), new vec3(0.0f, -1.0f, 0.0f), new vec2(1.0f, 0.0f));
		*/
		
		
		vec3 vertices[] = {
			new vec3(0.5f, 1.0f, -0.5f),
			new vec3(0.5f, 0.0f, -0.5f),
			new vec3(-0.5f, 0.0f, -0.5f),
			new vec3(-0.5f, 1.0f, -0.5f),
			new vec3(-0.5f, 1.0f, 0.5f),
			new vec3(-0.5f, 0.0f, 0.5f),
			new vec3(0.5f, 0.0f, 0.5f),
			new vec3(0.5f, 1.0f, 0.5f)
		};

		vec3 normals[] = {
			new vec3(0.0f, 0.0f, -1.0f),
			new vec3(0.0f, 0.0f, 1.0f),
			new vec3(-1.0f, 0.0f, 0.0f),
			new vec3(1.0f, 0.0f, 0.0f),
			new vec3(0.0f, 1.0f, 0.0f),
			new vec3(0.0f, -1.0f, 0.0f)
		};
		
		vec2 texcoords[] = {
			new vec2(0.0f, 0.0f),
			new vec2(0.0f, 1.0f),
			new vec2(1.0f, 1.0f),
			new vec2(1.0f, 0.0f)
		};
		
		
		//front
		buffer.push(vertices[0], normals[0], texcoords[0]);
		buffer.push(vertices[1], normals[0], texcoords[1]);
		buffer.push(vertices[2], normals[0], texcoords[2]);
		buffer.push(vertices[3], normals[0], texcoords[3]);

		//back
		buffer.push(vertices[4], normals[1], texcoords[0]);
		buffer.push(vertices[5], normals[1], texcoords[1]);
		buffer.push(vertices[6], normals[1], texcoords[2]);
		buffer.push(vertices[7], normals[1], texcoords[3]);

		//left
		buffer.push(vertices[3], normals[2], texcoords[0]);
		buffer.push(vertices[2], normals[2], texcoords[1]);
		buffer.push(vertices[5], normals[2], texcoords[2]);
		buffer.push(vertices[4], normals[2], texcoords[3]);

		//right
		buffer.push(vertices[7], normals[3], texcoords[0]);
		buffer.push(vertices[6], normals[3], texcoords[1]);
		buffer.push(vertices[1], normals[3], texcoords[2]);
		buffer.push(vertices[0], normals[3], texcoords[3]);

		//top
		buffer.push(vertices[3], normals[4], texcoords[0]);
		buffer.push(vertices[4], normals[4], texcoords[1]);
		buffer.push(vertices[7], normals[4], texcoords[2]);
		buffer.push(vertices[0], normals[4], texcoords[3]);

		//bottom
		buffer.push(vertices[5], normals[5], texcoords[0]);
		buffer.push(vertices[2], normals[5], texcoords[1]);
		buffer.push(vertices[1], normals[5], texcoords[2]);
		buffer.push(vertices[6], normals[5], texcoords[3]);
		
	    int indices[] = {0,1,2,0,2,3,  4,5,6,4,6,7,  8,9,10, 8,10,11,  12,13,14,12,14,15,  16,17,18,16,18,19,  20,21,22,20,22,23};
	    buffer.push(indices);
		
		buffer.end();
		
	}
	
	public void draw()
	{
		buffer.draw_elements(GL11.GL_TRIANGLES);
	}
}
