package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;

public class SkyCube {

	public SkyCube() {

	}

	// Implement using notes and looking at TexSphere
	public void DrawTexCube(Texture myTexture) {
		// the eight points that make up the square
		// The square has a center of gravity at point (0,0,0) and a side length of 2.
		// assign the vertex coordinates in the order of the formula
		Point4f vertices[] = {
				new Point4f(-1.0f, -1.0f, -1.0f, 0.0f), new Point4f(-1.0f, -1.0f, 1.0f, 0.0f),
				new Point4f(-1.0f, 1.0f, -1.0f, 0.0f), new Point4f(-1.0f, 1.0f, 1.0f, 0.0f),
				new Point4f(1.0f, -1.0f, -1.0f, 0.0f), new Point4f(1.0f, -1.0f, 1.0f, 0.0f),
				new Point4f(1.0f, 1.0f, -1.0f, 0.0f), new Point4f(1.0f, 1.0f, 1.0f, 0.0f) };

		int faces[][] = { { 0, 4, 5, 1 }, // Per face is made up of four points
				{ 0, 2, 6, 4 }, { 0, 1, 3, 2 }, { 4, 6, 7, 5 }, { 1, 5, 7, 3 }, { 2, 3, 7, 6 } };
		/*
		 * Since tiling map can be thought of as dividing a complete rendering into
		 * small square pieces, I set t=1/4 to the portion of the tiling that each side
		 * is divided into, according to the diagram
		 */
		float t = 1 / 4f;

		/*
		 * Note: I used a tiled map as the texture rendering map, so each face is set up
		 * separately
		 */
		
		myTexture.bind();//  Bind the current texture
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_CLAMP);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);

		GL11.glBegin(GL11.GL_QUADS);

		// Draw bottom surface

		// Calculate the point normals according to the sequence of faces
		Vector4f v1 = vertices[faces[0][1]].MinusPoint(vertices[faces[0][0]]);
		Vector4f w1 = vertices[faces[0][3]].MinusPoint(vertices[faces[0][0]]);
		Vector4f normal1 = v1.cross(w1).Normal();
		/*
		 * The four points that make up the square are obtained, so that the square can
		 * be drawn On each side, set four points at the top, bottom, left and right as
		 * mapping vertices. The coordinates of the mapping vertices are calculated
		 * based on the coordinates of the face's position on the tiled map.
		 */
		GL11.glNormal3f(normal1.x, normal1.y, normal1.z);
//		GL11.glTexCoord2f(2.0f * t, 1.0f * t);
//		GL11.glVertex3f(vertices[faces[0][0]].x, vertices[faces[0][0]].y, vertices[faces[0][0]].z);
//		GL11.glTexCoord2f(1.0f * t, 1.0f * t);
//		GL11.glVertex3f(vertices[faces[0][1]].x, vertices[faces[0][1]].y, vertices[faces[0][1]].z);
//		GL11.glTexCoord2f(1.0f * t, 0.0f);
//		GL11.glVertex3f(vertices[faces[0][2]].x, vertices[faces[0][2]].y, vertices[faces[0][2]].z);
//		GL11.glTexCoord2f(2.0f * t, 0.0f);
//		GL11.glVertex3f(vertices[faces[0][3]].x, vertices[faces[0][3]].y, vertices[faces[0][3]].z);
		
		GL11.glTexCoord2f(1.0f * t, 0.0f);
		GL11.glVertex3f(vertices[faces[0][0]].x, vertices[faces[0][0]].y, vertices[faces[0][0]].z);
		GL11.glTexCoord2f(2.0f * t, 0.0f);
		GL11.glVertex3f(vertices[faces[0][1]].x, vertices[faces[0][1]].y, vertices[faces[0][1]].z);
		GL11.glTexCoord2f(2.0f * t, 1.0f * t);
		GL11.glVertex3f(vertices[faces[0][2]].x, vertices[faces[0][2]].y, vertices[faces[0][2]].z);
		GL11.glTexCoord2f(1.0f * t, 1.0f * t);
		GL11.glVertex3f(vertices[faces[0][3]].x, vertices[faces[0][3]].y, vertices[faces[0][3]].z);

		// Draw back surface

		// Calculate the point normals according to the sequence of faces
		Vector4f v2 = vertices[faces[1][1]].MinusPoint(vertices[faces[1][0]]);
		Vector4f w2 = vertices[faces[1][3]].MinusPoint(vertices[faces[1][0]]);
		Vector4f normal2 = v2.cross(w2).Normal();
		GL11.glNormal3f(normal2.x, normal2.y, normal2.z);
		/*
		 * The four points that make up the square are obtained, so that the square can
		 * be drawn On each side, set four points at the top, bottom, left and right as
		 * mapping vertices. The coordinates of the mapping vertices are calculated
		 * based on the coordinates of the face's position on the tiled map.
		 */
		GL11.glTexCoord2f(1.0f, 1.0f * t);
		GL11.glVertex3f(vertices[faces[1][0]].x, vertices[faces[1][0]].y, vertices[faces[1][0]].z);
		GL11.glTexCoord2f(1.0f, 2.0f * t);
		GL11.glVertex3f(vertices[faces[1][1]].x, vertices[faces[1][1]].y, vertices[faces[1][1]].z);
		GL11.glTexCoord2f(3.0f * t, 2.0f * t);
		GL11.glVertex3f(vertices[faces[1][2]].x, vertices[faces[1][2]].y, vertices[faces[1][2]].z);
		GL11.glTexCoord2f(3.0f * t, 1.0f * t);
		GL11.glVertex3f(vertices[faces[1][3]].x, vertices[faces[1][3]].y, vertices[faces[1][3]].z);

		// Draw left surface

		// Calculate the point normals according to the sequence of faces
		Vector4f v3 = vertices[faces[2][1]].MinusPoint(vertices[faces[2][0]]);
		Vector4f w3 = vertices[faces[2][3]].MinusPoint(vertices[faces[2][0]]);
		Vector4f normal3 = v3.cross(w3).Normal();
		GL11.glNormal3f(normal3.x, normal3.y, normal3.z);
		/*
		 * The four points that make up the square are obtained, so that the square can
		 * be drawn On each side, set four points at the top, bottom, left and right as
		 * mapping vertices. The coordinates of the mapping vertices are calculated
		 * based on the coordinates of the face's position on the tiled map.
		 */
		GL11.glTexCoord2f(0.0f, 1.0f * t);
		GL11.glVertex3f(vertices[faces[2][0]].x, vertices[faces[2][0]].y, vertices[faces[2][0]].z);
		GL11.glTexCoord2f(1.0f * t, 1.0f * t);
		GL11.glVertex3f(vertices[faces[2][1]].x, vertices[faces[2][1]].y, vertices[faces[2][1]].z);
		GL11.glTexCoord2f(1.0f * t, 2.0f * t);
		GL11.glVertex3f(vertices[faces[2][2]].x, vertices[faces[2][2]].y, vertices[faces[2][2]].z);
		GL11.glTexCoord2f(0.0f, 2.0f * t);
		GL11.glVertex3f(vertices[faces[2][3]].x, vertices[faces[2][3]].y, vertices[faces[2][3]].z);

		// Draw right surface

		// Calculate the point normals according to the sequence of faces
		Vector4f v4 = vertices[faces[3][1]].MinusPoint(vertices[faces[3][0]]);
		Vector4f w4 = vertices[faces[3][3]].MinusPoint(vertices[faces[3][0]]);
		Vector4f normal4 = v4.cross(w4).Normal();
		GL11.glNormal3f(normal4.x, normal4.y, normal4.z);
		/*
		 * The four points that make up the square are obtained, so that the square can
		 * be drawn On each side, set four points at the top, bottom, left and right as
		 * mapping vertices. The coordinates of the mapping vertices are calculated
		 * based on the coordinates of the face's position on the tiled map.
		 */
		GL11.glTexCoord2f(3.0f * t, 1.0f * t);
		GL11.glVertex3f(vertices[faces[3][0]].x, vertices[faces[3][0]].y, vertices[faces[3][0]].z);
		GL11.glTexCoord2f(3.0f * t, 2.0f * t);
		GL11.glVertex3f(vertices[faces[3][1]].x, vertices[faces[3][1]].y, vertices[faces[3][1]].z);
		GL11.glTexCoord2f(2.0f * t, 2.0f * t);
		GL11.glVertex3f(vertices[faces[3][2]].x, vertices[faces[3][2]].y, vertices[faces[3][2]].z);
		GL11.glTexCoord2f(2.0f * t, 1.0f * t);
		GL11.glVertex3f(vertices[faces[3][3]].x, vertices[faces[3][3]].y, vertices[faces[3][3]].z);

		// Draw front surface

		// Calculate the point normals according to the sequence of faces
		Vector4f v5 = vertices[faces[4][1]].MinusPoint(vertices[faces[4][0]]);
		Vector4f w5 = vertices[faces[4][3]].MinusPoint(vertices[faces[4][0]]);
		Vector4f normal5 = v5.cross(w5).Normal();
		GL11.glNormal3f(normal5.x, normal5.y, normal5.z);
		/*
		 * The four points that make up the square are obtained, so that the square can
		 * be drawn On each side, set four points at the top, bottom, left and right as
		 * mapping vertices. The coordinates of the mapping vertices are calculated
		 * based on the coordinates of the face's position on the tiled map.
		 */
		GL11.glTexCoord2f(1.0f * t, 1.0f * t);
		GL11.glVertex3f(vertices[faces[4][0]].x, vertices[faces[4][0]].y, vertices[faces[4][0]].z);
		GL11.glTexCoord2f(2.0f * t, 1.0f * t);
		GL11.glVertex3f(vertices[faces[4][1]].x, vertices[faces[4][1]].y, vertices[faces[4][1]].z);
		GL11.glTexCoord2f(2.0f * t, 2.0f * t);
		GL11.glVertex3f(vertices[faces[4][2]].x, vertices[faces[4][2]].y, vertices[faces[4][2]].z);
		GL11.glTexCoord2f(1.0f * t, 2.0f * t);
		GL11.glVertex3f(vertices[faces[4][3]].x, vertices[faces[4][3]].y, vertices[faces[4][3]].z);

		// Draw top surface

		// Calculate the point normals according to the sequence of faces
		Vector4f v6 = vertices[faces[5][1]].MinusPoint(vertices[faces[5][0]]);
		Vector4f w6 = vertices[faces[5][3]].MinusPoint(vertices[faces[5][0]]);
		Vector4f normal6 = v6.cross(w6).Normal();
		GL11.glNormal3f(normal6.x, normal6.y, normal6.z);
		/*
		 * The four points that make up the square are obtained, so that the square can
		 * be drawn On each side, set four points at the top, bottom, left and right as
		 * mapping vertices. The coordinates of the mapping vertices are calculated
		 * based on the coordinates of the face's position on the tiled map.
		 */
		GL11.glTexCoord2f(1.0f * t, 3.0f * t);
		GL11.glVertex3f(vertices[faces[5][0]].x, vertices[faces[5][0]].y, vertices[faces[5][0]].z);
		GL11.glTexCoord2f(1.0f * t, 2.0f * t);
		GL11.glVertex3f(vertices[faces[5][1]].x, vertices[faces[5][1]].y, vertices[faces[5][1]].z);
		GL11.glTexCoord2f(2.0f * t, 2.0f * t);
		GL11.glVertex3f(vertices[faces[5][2]].x, vertices[faces[5][2]].y, vertices[faces[5][2]].z);
		GL11.glTexCoord2f(2.0f * t, 3.0f * t);
		GL11.glVertex3f(vertices[faces[5][3]].x, vertices[faces[5][3]].y, vertices[faces[5][3]].z);

		GL11.glEnd();

	}
}
