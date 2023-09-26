package objects3D;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.lwjgl.opengl.GL11;
import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;
import GraphicsObjects.vec3;
import GraphicsObjects.Utils;

public class Human {
	
	Sphere sphere = new Sphere();
	Cylinder cylinder = new Cylinder();
	TexCube ChestMap = new TexCube();
	
	// basic colours
	static float black[] = { 0.0f, 0.0f, 0.0f, 1.0f };
	static float white[] = { 1.0f, 1.0f, 1.0f, 1.0f };
	static float skin[]= {145f/255f, 112f/255f, 68f/255f,1.0f};
	static float dress[]= {70f/255f,130f/255f,180f/255f};
	static float lightyellow[]= {240f/255f,230f/255f,140f/255f};

	static float grey[] = { 0.5f, 0.5f, 0.5f, 1.0f };
	static float spot[] = { 0.1f, 0.1f, 0.1f, 0.5f };

	// primary colours
	static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };
	static float green[] = { 0.0f, 1.0f, 0.0f, 1.0f };
	static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };

	// secondary colours
	static float yellow[] = { 1.0f, 1.0f, 0.0f, 1.0f };
	static float magenta[] = { 1.0f, 0.0f, 1.0f, 1.0f };
	static float cyan[] = { 0.0f, 1.0f, 1.0f, 1.0f };

	// other colours
	static float orange[] = { 1.0f, 0.5f, 0.0f, 1.0f, 1.0f };
	static float brown[] = { 0.5f, 0.25f, 0.0f, 1.0f, 1.0f };
	static float dkgreen[] = { 0.0f, 0.5f, 0.0f, 1.0f, 1.0f };
	static float pink[] = { 1.0f, 0.6f, 0.6f, 1.0f, 1.0f };
	
	public Human() {

	}
	
	// Implement using notes in Animation lecture
	public void DrawHuman(float delta, boolean useColor,boolean ani) {
		/*
		 * Different angles are set for the upper limbs, thighs and calves as each limb
		 * swings at a different angle when running When running, the angle of the front
		 * thigh should be greater than the calf; and the angle of the rear calf should
		 * be greater than the thigh
		 */
		float theta = (float) (delta * 2 * Math.PI) * 10;
		float LimbRotation = 0;
		float UpperLimbRotation = 0;
		float ThighsRotation = 0;
		float CalfRotation = 0;
		float NeckRotation = 0; // Rotation of neck and head
		// Set the basic angle
		UpperLimbRotation = (float) Math.cos(theta) * (45);
		ThighsRotation = (float) Math.cos(theta) * (80);
		CalfRotation = (float) Math.cos(theta) * (50);
		NeckRotation = (float) Math.cos(theta) * (20);

		if(useColor) {
			GL11.glColor3f(dress[0], dress[1], dress[2]);
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
		}
		GL11.glPushMatrix();
		{ // Torso & Pelvis
			// Use the cylindrical shape of the torso as a reference
			GL11.glTranslatef(0.0f, 2.2f, 0.0f);// Move 2.2f in the y direction
			GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);// Rotation of 90 degrees on the x-axis allows the torso to be
													// placed vertically
			cylinder.draw(0.5f, 0.7f, 1.0f, null);

			// Chest
			if(useColor) {
				GL11.glColor3f(lightyellow[0], lightyellow[1], lightyellow[2]);
				GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(green));
			}

			GL11.glPushMatrix();
			{
				GL11.glTranslatef(0.0f, 0.0f, 0.0f);
				// The torso's cylinder is the standard, so it needs to be rotated 180 degrees
				// on the x-axis
				GL11.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);// Rotation of 180 degrees on the x-axis allows the pelvis to
															// be placed vertically above the Plevis.
				cylinder.draw(0.5f, 1.0f, 1.0f, null); // Draw the cylinder for the chest
		
				// Neck
				if(useColor) {
					GL11.glColor3f(skin[0], skin[1], skin[2]);
					GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
				}
				
				GL11.glPushMatrix();
				{
					GL11.glTranslatef(0.0f, 0.0f, 1.0f);// Modify the z value so that the neck is on the chest
					if(ani==true) {
						GL11.glRotatef(-NeckRotation, 1.0f, 0.0f, 0.0f);// The cylinder of the chest is used as a standard.
						}// Using NeckRotation to move the head
						else {GL11.glRotatef(0.0f, 1.0f, 0.0f, 0.0f);}
					
					cylinder.draw(0.15f, 0.3f, 1.0f, null);// Draw the cylinder for the chest	
					
					// Head
					if(useColor) {
						GL11.glColor3f(skin[0], skin[1], skin[2]);
						GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
					}
					
					GL11.glPushMatrix();
					{// The cylinder of the neck is used as a standard
						
						GL11.glTranslatef(0.0f, 0.0f, 0.7f);// Modify the z value so that the head is on the chest
						sphere.draw(0.5f, null); // Draw the sphere for the head
						GL11.glPopMatrix();
						
						// Nose
						if(useColor) {
							GL11.glColor3f(skin[0],skin[1], skin[2]);
							GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
						}
						
						GL11.glPushMatrix();
						{
							GL11.glRotatef(0.0f, 1.0f, 0.0f, 0.0f);// On the sphere of head, it doesn't need extra
																	// rotation.
							GL11.glTranslatef(0.0f, 0.5f, 0.6f); // Modify the y,z value so that the nose protrudes from
																	// the head
							sphere.draw(0.15f, null); // Draw the sphere for the nose
						}
						GL11.glPopMatrix();
						// Left eye
						if(useColor) {
							GL11.glColor3f(white[0], white[1], white[2]);
							GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
						}
						
						GL11.glPushMatrix();
						{
							GL11.glRotatef(0.0f, 1.0f, 0.0f, 0.0f);// On the sphere of head, it doesn't need extra
																	// rotation.
							GL11.glTranslatef(-0.2f, 0.4f, 0.8f);// Modify the x,y,z value of the position so that the
																	// eye protrudes from the head
							sphere.draw(0.1f, null); // Draw the sphere for the eye

							// Left eyeball
							if(useColor) {
								GL11.glColor3f(black[0], black[1], black[2]);
								GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
							}
							
							GL11.glPushMatrix();
							{
								GL11.glRotatef(0.0f, 1.0f, 0.0f, 0.0f);// On the sphere of head, it doesn't need extra
																		// rotation.
								GL11.glTranslatef(0.0f, 0.05f, 0.0f);// Modify the y value of the position so that the
																		// eyeball protrudes from the eye
								sphere.draw(0.06f, null); // Draw the sphere for the eyeball
							}
							GL11.glPopMatrix();
						}
						GL11.glPopMatrix();
						// Right eye
						if(useColor) {
							GL11.glColor3f(white[0], white[1], white[2]);
							GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
						}
						
						GL11.glPushMatrix();
						{
							GL11.glRotatef(0.0f, 1.0f, 0.0f, 0.0f);// On the sphere of head, it doesn't need extra
																	// rotation.
							GL11.glTranslatef(0.2f, 0.4f, 0.8f);// Modify the x,y,z value of the position so that the
																// eye protrudes from the head
							sphere.draw(0.1f, null); // Draw the sphere for the eye

							// Right eyeball
							if(useColor) {
								GL11.glColor3f(black[0], black[1], black[2]);
								GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
							}
							
							GL11.glPushMatrix();
							{
								GL11.glRotatef(0.0f, 1.0f, 0.0f, 0.0f);// On the sphere of head, it doesn't need extra
																		// rotation.
								GL11.glTranslatef(0.0f, 0.05f, 0.0f);// Modify the y value of the position so that the
																		// eyeball protrudes from the eye
								sphere.draw(0.06f, null); // Draw the sphere for the eyeball
							}
							GL11.glPopMatrix();
						}
						GL11.glPopMatrix();

						// Mouse
						if(useColor) {
							GL11.glColor3f(red[0], red[1], red[2]);
							GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
						}
						
						GL11.glPushMatrix();
						{
							GL11.glTranslatef(0.0f, 0.3f, 0.3f);// Modify the y,z value of the position so that the nose
																// protrudes from the head.
							GL11.glRotatef(0.0f, 1.0f, 0.0f, 0.0f);// On the sphere of head, it doesn't need extra
																	// rotation.
							cylinder.draw(0.15f, 0.1f, 1.0f, null);// Draw the cyclinder for the mouse. Make the height
																	// small enough to show like a mouse.
						}
						GL11.glPopMatrix();
					}
					
					GL11.glPopMatrix();

					// Left shoulder
					if(useColor) {
						GL11.glColor3f(dress[0], dress[1], dress[2]);
						GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
					}
					GL11.glPushMatrix();
					{ // The cylinder of the left arm is used as a standard
						GL11.glTranslatef(0.5f, 0.0f, 1.0f);// Modify the z value of the position so that the
																// left elbow belows the left arm.
						sphere.draw(0.3f, null); // Draw the sphere for the left elbow.

						//Left arm
						if(useColor) {
							GL11.glColor3f(skin[0],skin[1], skin[2]);
							GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
						}
						
						GL11.glPushMatrix();
						{// The sphere of the left shoulder is used as a standard
							GL11.glTranslatef(0.1f, 0.0f, 0.0f);// Modify the x value of the position so that the left
																// arm attached to left shoulder.
							GL11.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);// Rotation of 180 degrees on the x-axis allows the
																		// left arm to be placed vertically below the
																		// left shoulder.
							// Set the animation for left arm,rotates around the x-axis.
							// The left and right arms should be swinging in opposite directions at the same
							// moment, so limbrotation is a negative value.
							if(ani==true) {
							GL11.glRotatef(-UpperLimbRotation, 1.0f, 0.0f, 0.0f);
							}
							else{
								GL11.glRotatef(0, 1.0f, 0.0f, 0.0f);
							}
								cylinder.draw(0.15f, 0.7f, 1.0f, null);// Draw the cyclinder for the arm.
							
							// Left elbow
							if(useColor) {
								GL11.glColor3f(dress[0], dress[1], dress[2]);
								GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
							}
							
							GL11.glPushMatrix();
							{ // The cylinder of the left arm is used as a standard
								GL11.glTranslatef(0.0f, 0.0f, 0.75f);// Modify the z value of the position so that the
																		// left elbow belows the left arm.
								sphere.draw(0.2f, null); // Draw the sphere for the left elbow.

								// Left forearm
								if(useColor) {
									GL11.glColor3f(skin[0],skin[1], skin[2]);
									GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
								}
								
								GL11.glPushMatrix();
								{// The sphere of the left elbow is used as a standard
									GL11.glTranslatef(0.0f, 0.0f, 0.0f);// With the left elbow as the base, no change in
																		// x,y,z is required
									// Running with the forearm at right angles to the arm
									//GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
									GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
									cylinder.draw(0.1f, 0.7f, 1.0f, null);// Draw the cyclinder for the left forearm.

									// Left hand
									if(useColor) {
										GL11.glColor3f(lightyellow[0], lightyellow[1], lightyellow[2]);
										GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
									}
									GL11.glPushMatrix();
									{ // The cylinder of the left forearm is used as a standard
										GL11.glTranslatef(0.0f, 0.0f, 0.75f);// Modify the z value of the position so
																				// that the left elbow belows the left
																				// arm.																					
										sphere.draw(0.2f, null);// Draw the sphere for the left
									}
									GL11.glPopMatrix();
								}
								GL11.glPopMatrix();
							}
							GL11.glPopMatrix();
						}
						GL11.glPopMatrix();
					}
					GL11.glPopMatrix();

					// Right shoulder
					if(useColor) {
						GL11.glColor3f(dress[0], dress[1], dress[2]);
						GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
					}
					GL11.glPushMatrix();
					{ // The cylinder of the left arm is used as a standard
						GL11.glTranslatef(-0.5f, 0.0f, 1.0f);// Modify the z value of the position so that the
																// left elbow belows the left arm.
						sphere.draw(0.3f, null); // Draw the sphere for the left elbow.

						// Right arm
						if(useColor) {
							GL11.glColor3f(skin[0], skin[1], skin[2]);
							GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
						}
						
						GL11.glPushMatrix();
						{// The sphere of the right shoulder is used as a standard
							GL11.glTranslatef(-0.1f, 0.0f, 0.0f);// Modify the x value of the position so that the right
																	// arm attached to right shoulder.
							GL11.glRotatef(180.0f, -1.0f, 0.0f, 0.0f); // Rotation of 180 degrees on the x-axis allows
																		// the right arm to be placed vertically below
																		// the right shoulder.

							// Set the animation for left arm,rotates around the x-axis.
							// The left and right arms should be swinging in opposite directions at the same
							// moment, so limbrotation is a positive value.
							if(ani==true) {
							GL11.glRotatef(UpperLimbRotation, 1.0f, 0.0f, 0.0f);
							}
							else {
								GL11.glRotatef(0, 1.0f, 0.0f, 0.0f);
							}
							cylinder.draw(0.15f, 0.7f, 1.0f, null);// Draw the cyclinder for the right arm.

							// Right elbow
							if(useColor) {
								GL11.glColor3f(dress[0], dress[1], dress[2]);
								GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
							}
							
							GL11.glPushMatrix();
							{ // The cylinder of the right arm is used as a standard
								GL11.glTranslatef(0.0f, 0.0f, 0.75f);// Modify the z value of the position so that the
																		// right elbow belows the right arm.
								sphere.draw(0.2f, null); // Draw the sphere for the right elbow.

								// Right forearm
								if(useColor) {
									GL11.glColor3f(skin[0], skin[1], skin[2]);
									//GL11.glColor3f(orange[0], orange[1], orange[2]);
									GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
								}
								
								GL11.glPushMatrix();
								{// The sphere of the right elbow is used as a standard
									GL11.glTranslatef(0.0f, 0.0f, 0.0f);// With the right arm as the base, no change in
																		// x,y,z is required
									// Running with the forearm at right angles to the arm
									GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
									cylinder.draw(0.1f, 0.7f, 1.0f, null);// Draw the cyclinder for the right forearm.

									// Right hand
							
									if(useColor) {
										GL11.glColor3f(lightyellow[0], lightyellow[1], lightyellow[2]);
										GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
									}
									GL11.glPushMatrix();
									{ // The cylinder of the left forearm is used as a standard
										GL11.glTranslatef(0.0f, 0.0f, 0.75f);// Modify the z value of the position so
																				// that the left elbow belows the left
																				// arm.																					
										sphere.draw(0.2f, null);// Draw the sphere for the left
									
									}
									GL11.glPopMatrix();
								}
								GL11.glPopMatrix();
							}
							GL11.glPopMatrix();
						}
						GL11.glPopMatrix();
					}
					GL11.glPopMatrix();

				}
				GL11.glPopMatrix();

				// Left hip
				if(useColor) {
					GL11.glColor3f(dress[0], dress[1], dress[2]);
					GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
				}
				
				GL11.glPushMatrix();
				{
					GL11.glTranslatef(-0.3f, 0.0f, 0.7f); // Modify the x,z value of the position so that the left hip
															// is on the left side of the pelvis at proper level.
					sphere.draw(0.25f, null); // Draw the sphere for the left hip

					// Left high leg
					if(useColor) {
						GL11.glColor3f(skin[0], skin[1], skin[2]);
						GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
					}
					
					GL11.glPushMatrix();
					{
						GL11.glTranslatef(0.0f, 0.0f, 0.0f);// With the left hip as the base, no change in x,y,z is
															// required
						GL11.glRotatef(0.0f, 0.0f, 0.0f, 0.0f);
						// Set the animation for left high leg,rotates around the x-axis.
						// The left and right legs should be swinging in opposite directions at the same
						// moment, so limbrotation is a negative value.
						if(ani==true) {
						GL11.glRotatef(-ThighsRotation, 1.0f, 0.0f, 0.0f);
						}else {
							GL11.glRotatef(0, 0.0f, 0.0f, 0.0f);
						}
						cylinder.draw(0.15f, 0.7f, 1.0f, null);// Draw the cyclinder for the left high leg.

						// Left knee
						if(useColor) {
							GL11.glColor3f(dress[0], dress[1], dress[2]);
							GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
						}
						
						GL11.glPushMatrix();
						{
							GL11.glTranslatef(0.0f, 0.0f, 0.75f);// Modify the z value of the position so that the left
																	// knee belows the left high leg.
							GL11.glRotatef(0.0f, 0.0f, 0.0f, 0.0f); // No change in rotation is required
							sphere.draw(0.25f, null); // Draw the sphere for the left knee

							// left low leg
							if(useColor) {
								GL11.glColor3f(skin[0], skin[1], skin[2]);
								GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
							}
							
							GL11.glPushMatrix();
							{
								GL11.glTranslatef(0.0f, 0.0f, 0.0f);// With the left knee as the base, no change in
																	// x,y,z is required
								// The left and right arms should be swinging in opposite directions at the same
								// moment, so limbrotation is a positive value.
								// There is difference in swing between low leg and forearm.
								if(ani==true) {
								GL11.glRotatef(CalfRotation, 1.0f, 0.0f, 0.0f);
								if (ThighsRotation > 0) {
									GL11.glRotatef(-CalfRotation, 1.0f, 0.0f, 0.0f);
								}
								}else {
									GL11.glRotatef(0, 1.0f, 0.0f, 0.0f);
								}
								cylinder.draw(0.15f, 0.7f, 1.0f, null);// Draw the cyclinder for the left low leg.

								// left foot
								if(useColor) {
									GL11.glColor3f(white[0],white[1], white[2]);
									GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
								}
								
								GL11.glPushMatrix();
								{
									GL11.glTranslatef(0.0f, 0.12f, 0.75f); // Modify the y,z value of the position so
																			// that the left foot is below the left low
																			// leg.
									GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);// Rotation of 90 degrees on the x-axis
																			// allows the left foot to be placed
																			// horizontally
									cylinder.draw(0.15f, 0.7f, 1.0f, null);// Draw the cyclinder for the left foot.

								}
								GL11.glPopMatrix();
							}
							GL11.glPopMatrix();
						}
						GL11.glPopMatrix();
					}
					GL11.glPopMatrix();
				}
				GL11.glPopMatrix();

				// Right hip
				if(useColor) {
					GL11.glColor3f(dress[0], dress[1], dress[2]);
					GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
				}
				
				GL11.glPushMatrix();
				{
					GL11.glTranslatef(0.3f, 0.0f, 0.7f);// Modify the x,z value of the position so that the right hip is
														// on the right side of the pelvis at proper level.
					sphere.draw(0.25f, null); // Draw the sphere for the right hip

					// Right high leg
					if(useColor) {
						GL11.glColor3f(skin[0], skin[1], skin[2]);
						GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
					}
					
					GL11.glPushMatrix();
					{
						GL11.glTranslatef(0.0f, 0.0f, 0.0f);// With the right hip as the base, no change in x,y,z is
															// required
						GL11.glRotatef(0.0f, 0.0f, 0.0f, 0.0f);
						// Set the animation for right high leg,rotates around the x-axis.
						// The left and right legs should be swinging in opposite directions at the same
						// moment, so limbrotation is a positive value.
						if(ani==true) {
						GL11.glRotatef(ThighsRotation, 1.0f, 0.0f, 0.0f);
						}
						else {
							GL11.glRotatef(0, 1.0f, 0.0f, 0.0f);
						}
						cylinder.draw(0.15f, 0.7f, 1.0f, null);

						// Right knee
						if(useColor) {
							GL11.glColor3f(dress[0], dress[1], dress[2]);
							GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
						}
						
						GL11.glPushMatrix();
						{
							GL11.glTranslatef(0.0f, 0.0f, 0.75f);// Modify the z value of the position so that the right
																	// knee belows the right high leg.
							GL11.glRotatef(0.0f, 0.0f, 0.0f, 0.0f); // No change in rotation is required
							sphere.draw(0.25f, null); // Draw the sphere for the right knee

							// Right low leg
							if(useColor) {
								GL11.glColor3f(skin[0], skin[1], skin[2]);
								GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(orange));
							}
							
							GL11.glPushMatrix();
							{
								GL11.glTranslatef(0.0f, 0.0f, 0.0f);// With the right knee as the base, no change in
																	// x,y,z is required
								// The left and right arms should be swinging in opposite directions at the same
								// moment, so limbrotation is a negative value.
								// There is difference in swing between low leg and forearm.
								if(ani==true) {
								GL11.glRotatef(-CalfRotation, 1.0f, 0.0f, 0.0f);
								if (ThighsRotation < 0) {
									GL11.glRotatef(CalfRotation, 1.0f, 0.0f, 0.0f);
								}}
								else {
									GL11.glRotatef(0, 1.0f, 0.0f, 0.0f);
								}
								cylinder.draw(0.15f, 0.7f, 1.0f, null);// Draw the cyclinder for the right low leg.
								// Right foot
								if(useColor) {
									GL11.glColor3f(white[0],white[1], white[2]);
									GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(blue));
								}
								
								GL11.glPushMatrix();
								{
									GL11.glTranslatef(0.0f, 0.12f, 0.75f);// Modify the y,z value of the position so
																			// that the right foot is below the right
																			// low leg.
									GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f); // Rotation of 90 degrees on the x-axis
																				// allows the right foot to be placed
																				// horizontally
									cylinder.draw(0.15f, 0.7f, 1.0f, null); // Draw the cyclinder for the right foot.

								}
								GL11.glPopMatrix();
							}
							GL11.glPopMatrix();
						}
						GL11.glPopMatrix();
					}
					GL11.glPopMatrix();
				}
				GL11.glPopMatrix();

			}
			GL11.glPopMatrix();

		}

	}

}
