package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import GraphicsObjects.*;
import java.math.*;

public class Cylinder {
	vertex_buffer buffer;
	
	public Cylinder() {
		makeCylinder(1.0f, 1.0f, 16);
	}
	
	public Cylinder(float radius, float height, int nSegments)
	{
		makeCylinder(radius, height, nSegments);
	}
	
	void makeCylinder(float radius, float height, int nSegments)
	{
		if (nSegments < 3) {
			nSegments = 3;
	    }

	    float coord_x_step = 1.0f / nSegments;
	    float step = 360.0f / nSegments;
	    float angle;
	    float vx, vz;

	    ++nSegments;
	    
	    // init buffer
	    buffer = new vertex_buffer();
	    buffer.begin(nSegments * 6, (nSegments - 1) * 3 * 6);
	    
	    // init vertices
	    vertex v = new vertex();
	    for (int y = 0; y < 6; ++y) {
	        for (int x = 0; x < nSegments; ++x) {
	            angle = (float)Math.toRadians(x * step);
	            vx = (float)-Math.cos(angle);
	            vz = (float) Math.sin(angle);

	            switch (y) {
	            case 0:
	                v.position = new vec3(0.0f, 0.0f, 0.0f);
	                v.normal = new vec3(0.0f, 1.0f, 0.0f);
	                v.texcoord = new vec2(x * coord_x_step, 0.0f);
	                break;
	            case 1:
	                v.position = new vec3(vx * radius, vz * radius, 0.0f);
	                v.normal = new vec3(0.0f, 1.0f, 0.0f);
	                v.texcoord = new vec2(x * coord_x_step, 1.0f);
	                break;
	            case 2:
	                v.position = new vec3(vx * radius, vz * radius, 0.0f);
	                v.normal = new vec3(vx, 0.0f, vz);
	                v.texcoord = new vec2(x * coord_x_step, 0.0f);
	                break;
	            case 3:
	                v.position = new vec3(vx * radius, vz * radius, height);
	                v.normal = new vec3(vx, 0.0f, vz);
	                v.texcoord = new vec2(x * coord_x_step, 1.0f);
	                break;
	            case 4:
	                v.position = new vec3(vx * radius, vz * radius, height);
	                v.normal = new vec3(0.0f, -1.0f, 0.0f);
	                v.texcoord = new vec2(x * coord_x_step, 1.0f);
	                break;
	            case 5:
	                v.position = new vec3(0.0f, 0.0f, height);
	                v.normal = new vec3(0.0f, -1.0f, 0.0f);
	                v.texcoord = new vec2(x * coord_x_step, 0.0f);
	                break;
	            }
	            buffer.push(v);
	        }
	    }
	    
	    // init indices
	    int id = 0;
	    for(int y = 0; y < 3; ++y) {
			for (int x = 0; x < nSegments - 1; ++x) {
				buffer.push(id + x);
				buffer.push(id + x + nSegments + 1);
				buffer.push(id + x + nSegments);
				buffer.push(id + x);
				buffer.push(id + x + nSegments + 1);
				buffer.push(id + x + 1);
			}
			id += nSegments * 2;
	    }
	}
	
	// remember to use Math.PI isntead PI 
	// Implement using notes and examine Tetrahedron to aid in the coding  look at lecture  7 , 7b and 8 
	
	/*For a cylinder, it can be divided into two parts: the bottom surface and the column surface. 
	 * Both parts can be seen as planes made up of triangular splices. 
	 * 
	 * First, this method has three parameters: radius, height and the number of triangles contained in the base surface of a single-sided cylinder. 
	 * Following the radian system, the angle of each triangle with respect to the radius of the starting point is first counted in a cycle. 
	 * The angle of the first triangle from the starting point is 2pi/nSegment, then the angle of the adjacent triangle is 2pi*2/nSegment. 
	 * i triangle from the starting radius is 2i*Pi/nSegment, then the sin and cos values of each angle are calculated. 
	 * 
	 * There are four arrays, representing the upper and lower bases and the two sets of triangles that form the face of the column with the upper and lower bases on one side. 
	 * The coordinates of the triangles are obtained by multiplying the sin and cos values by their respective radii. 
	 * Four loops are drawn for each of the two bottom sides and half of the two column sides. 
	 * 
	 * Using the for loop, set the three point coordinates and normal vectors of each triangle and draw the triangle. Form the faces, then form the cylinder
	 */
	public void draw(Texture myTexture) 
	{
//		if(myTexture != null) {
//			GL11.glEnable(GL11.GL_TEXTURE_2D);
//			myTexture.bind();//绑定当前纹理
//		}
//		else {
//			GL11.glDisable(GL11.GL_TEXTURE_2D);
//		}
		
//		GL11.glBegin(GL11.GL_QUADS);
//		
//		int id = 0;
//		for (int x = 0; x < segments - 1; ++x) {
//			vertices[id + x].draw();
//			vertices[id + x + 1].draw();
//			vertices[id + x + segments + 1].draw();
//			vertices[id + x + segments].draw();
//		}
//		
//		id = segments * 2;
//		for (int x = 0; x < segments - 1; ++x) {
//			vertices[id + x].draw();
//			vertices[id + x + 1].draw();
//			vertices[id + x + segments + 1].draw();
//			vertices[id + x + segments].draw();
//		}
//		
//		id = segments * 4;
//		for (int x = 0; x < segments - 1; ++x) {
//			vertices[id + x].draw();
//			vertices[id + x + 1].draw();
//			vertices[id + x + segments + 1].draw();
//			vertices[id + x + segments].draw();
//		}
//		GL11.glEnd();
		
		gl.bind_texture(myTexture);
		buffer.draw_elements(GL11.GL_TRIANGLES);
	}
	
	public void draw(float scaleX, float scaleY, float scaleZ, Texture myTexture) 
	{
		GL11.glPushMatrix();
		GL11.glScalef(scaleX, scaleX, scaleY);
		this.draw(myTexture);
		GL11.glPopMatrix();
	}
}


/*
for(float i=(float) 0.0;i<nSegments;i+=1.0) { //find the angle of each triangle with respect to the radius of the starting point is first counted in a cycle
	 float angle=(float) (Math.PI*i*2.0/nSegments);
	 float nextAngle=(float) (Math.PI*(i+1.0)*2.0/nSegments);
	 
	 float x1=(float) (radius*Math.sin(angle)); 
	 float y1=(float) (radius*Math.cos(angle));
	 float x2=(float)(radius*Math.sin(nextAngle)); //the x coordinate of next point
	 float y2=(float)(radius*Math.cos(nextAngle)); //the y coordinate of next point
	
	 Point4f vertices[] = { 	//half of column side
			new Point4f(x1,y1,0.0f,0.0f), 
			new Point4f(x2,y2,height,0.0f),				
			new Point4f(x1,y1,height,0.0f), 
				 };

	 Point4f vertices1[] = { //another half of the column side
			 new Point4f(x1,y1,0.0f,0.0f), 
			 new Point4f(x2,y2,0.0f,0.0f),
			 new Point4f(x2,y2,height,0.0f), 
	 };
	 
	 Point4f vertices2[] = {   //the circle on the top
			 new Point4f(x1,y1,height,0.0f), 
			 new Point4f(0.0f,0.0f,height,0.0f),
			 new Point4f(x2,y2,height,0.0f), 
	 };
	 
	 Point4f vertices3[] = {  //the circle on the bottom
			 new Point4f(x1,y1,0.0f,0.0f), 
			 new Point4f(0.0f,0.0f,0.0f,0.0f),
			 new Point4f(x2,y2,0.0f,0.0f), 
	 };
	 
	 GL11.glBegin(GL11.GL_TRIANGLES);

	 for (int face = 0; face < nSegments; face++) { // for each triangle of vertices/vertices1/vertices2/vertice3

		 GL11.glNormal3f(vertices[0].x, vertices[0].y,(float)0.0);
		 GL11.glVertex3f(vertices[0].x, vertices[0].y, vertices[0].z);
		 GL11.glNormal3f(vertices[1].x, vertices[1].y, (float)0.0);
		 GL11.glVertex3f(vertices[1].x, vertices[1].y, vertices[1].z);	
		 GL11.glNormal3f(vertices[2].x, vertices[2].y, (float)0.0);			 				 								 
		 GL11.glVertex3f(vertices[2].x, vertices[2].y, vertices[2].z);
		 
		 GL11.glNormal3f(vertices1[0].x, vertices1[0].y, (float)0.0);	
		 GL11.glVertex3f(vertices1[0].x, vertices1[0].y, vertices1[0].z);
		 GL11.glNormal3f(vertices1[1].x, vertices1[1].y, (float)0.0);
		 GL11.glVertex3f(vertices1[1].x, vertices1[1].y, vertices1[1].z);
		 GL11.glNormal3f(vertices1[2].x, vertices1[2].y, (float)0.0);				 								 
		 GL11.glVertex3f(vertices1[2].x, vertices1[2].y, vertices1[2].z);
		 
		 
	 // for each triangle of circle on the top
		 Vector4f o = vertices2[1].MinusPoint(vertices2[0]); 
		 Vector4f p = vertices2[2].MinusPoint(vertices2[0]);
		 Vector4f normal2 = o.cross(p).Normal();
		 GL11.glNormal3f(normal2.x, normal2.y, normal2.z);
		//Set the vertex IDs of the triangles and calculate the point normals according to the sequence of faces
		 
		 GL11.glVertex3f(vertices2[0].x, vertices2[0].y, vertices2[0].z);
		 GL11.glVertex3f(vertices2[1].x, vertices2[1].y, vertices2[1].z);
		 GL11.glVertex3f(vertices2[2].x, vertices2[2].y, vertices2[2].z);
		 
		 // for each triangle of circle on the bottom
		 Vector4f q = vertices3[1].MinusPoint(vertices3[0]); 
		 Vector4f e = vertices3[2].MinusPoint(vertices3[0]);
		 Vector4f normal3 = q.cross(e).Normal();
		 GL11.glNormal3f(normal3.x, normal3.y, normal3.z);
		//Set the vertex IDs of the triangles and calculate the point normals according to the sequence of faces
		 GL11.glVertex3f(vertices3[0].x, vertices3[0].y, vertices3[0].z);
		 GL11.glVertex3f(vertices3[1].x, vertices3[1].y, vertices3[1].z);
		 GL11.glVertex3f(vertices3[2].x, vertices3[2].y, vertices3[2].z);
	} 
	 GL11.glEnd();
}

*/