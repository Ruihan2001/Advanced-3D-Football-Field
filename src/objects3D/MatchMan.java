package objects3D;

import GraphicsObjects.gl;

public class MatchMan {
	
	//draw head
	void draw_head()
	{
		gl.push_matrix();
	    gl.translate(0.0f, 0.0f, -1.25f);
	    gl.color(0.8f, 0.75f, 0.75f);
	    ObjectInstance.sphere.draw(0.5f, null);
	    
	    gl.translate(0.0f, -0.1f, -0.125f);
	    gl.color(0.2f, 0.2f, 0.2f);
	    ObjectInstance.sphere.draw(0.5f, null);
	    
	    gl.push_matrix();
	    gl.rotate(-20.0f, 1.0f, 0.0f, 0.0f);
	    gl.scale(0.75f, 1.0f, 0.12f);
	    ObjectInstance.sphere.draw(null);
	    gl.pop_matrix();
	    
	    gl.pop_matrix();
	}

	//drawing a limb segment
	void draw_limb(float length, float r)
	{
	    gl.push_matrix();
	    ObjectInstance.sphere.draw(r, null);
	    ObjectInstance.cylinder.draw(r, length, r, null);
	    gl.translate(0.0f, 0.0f, length);
	    ObjectInstance.sphere.draw(r, null);
	    gl.pop_matrix();
	}

	//draw arms
	void draw_arm(float dir, float bigBase, float bigArm, float smallBase, float smallArm, float t)
	{
	    gl.push_matrix();
	    gl.translate(0.0f, 0.0f, -0.2f);
	    gl.rotate(bigBase + (float)Math.sin(t) * bigArm, 1.0f, 0.0f, 0.0f);

	    gl.push_matrix();
	    gl.translate(0.65f * dir, 0.0f, 0.0f);
	    gl.rotate(25.0f * dir, 0.0f, 1.0f, 0.0f);
	    gl.color(0.0f, 0.0f, 1.0f);
	    draw_limb(0.75f, 0.2f);
	    gl.pop_matrix();

	    gl.push_matrix();
	    gl.translate(1.0f * dir, 0.0f, 0.75f);
	    gl.rotate(smallBase + (float)Math.sin(t) * smallArm, 1.0f, 0.0f, 0.0f);
	    gl.color(0.8f, 0.75f, 0.75f);
	    draw_limb(0.65f, 0.15f);
	    gl.pop_matrix();

	    gl.pop_matrix();
	}

	//draw legs
	void draw_leg(float dir, float bigBase, float bigLeg, float smallBase, float smallLeg, float t)
	{
	    gl.push_matrix();
	    gl.translate(0.0f, 0.0f, 1.25f);
	    gl.rotate(bigBase + (float)Math.sin(t) * bigLeg, 1.0f, 0.0f, 0.0f);

	    gl.push_matrix();
	    gl.translate(0.35f * dir, 0.0f, 0.0f);
	    gl.color(0.0f, 0.0f, 1.0f);
	    draw_limb(1.0f, 0.25f);
	    gl.pop_matrix();

	    gl.push_matrix();
	    gl.translate(0.35f * dir, 0.0f, 1.0f);
	    gl.rotate(smallBase + (float)Math.sin(t) * smallLeg, 1.0f, 0.0f, 0.0f);
	    gl.color(0.8f, 0.75f, 0.75f);
	    draw_limb(1.0f, 0.2f);
	    
	    //foot
	    gl.push_matrix();
	    gl.translate(0.0f, 0.0f, 1.0f);
	    gl.scale(0.5f, 0.75f, 0.2f);
	    ObjectInstance.cube.draw();
	    gl.pop_matrix();
	    
	    gl.pop_matrix();

	    gl.pop_matrix();
	}
	
	

	//characters
	public void draw_move(float t)
	{
	    gl.push_matrix();
	    gl.translate(0.0f, 3.5f, 0.0f);
	    gl.rotate(90.0f, 1.0f, 0.0f, 0.0f);

	    //main body
	    gl.push_matrix();
	    gl.color(0.5f, 0.0f, 0.0f);
	    gl.scale(1.0f, 0.75f, 1.0f);
	    ObjectInstance.sphere.draw(0.65f, null);
	    gl.pop_matrix();
	    
	    gl.color(1.0f, 1.0f, 0.0f);
	    draw_limb(1.0f, 0.35f);

	    //neck
	    gl.push_matrix();
	    gl.translate(0.0f, 0.0f, -0.75f);
	    gl.color(0.8f, 0.75f, 0.75f);
	    draw_limb(0.25f, 0.2f);
	    gl.pop_matrix();

	    //haed
	    draw_head();

	    //arms
	    draw_arm(1.0f, 0.0f, 20.0f, -10.0f, 20.0f, t);
	    draw_arm(-1.0f, 0.0f, 20.0f, -10.0f, 20.0f, -t);
	    
	    //legs
	    draw_leg(1.0f, 0.0f, 30.0f, 0.0f, 30.0f, -t);
	    draw_leg(-1.0f, 0.0f, 30.0f, 0.0f, 30.0f, t);

	    gl.pop_matrix();
	}
	
	
	public void draw_jump(float height, float t)
	{
	    gl.push_matrix();
	    gl.translate(0.0f, 3.5f + height * 0.5f + (float)Math.sin(t) * height, 0.0f);
	    gl.rotate(90.0f, 1.0f, 0.0f, 0.0f);

	 
	    gl.push_matrix();
	    gl.color(0.5f, 0.0f, 0.0f);
	    gl.scale(1.0f, 0.75f, 1.0f);
	    ObjectInstance.sphere.draw(0.65f, null);
	    gl.pop_matrix();
	    
	    gl.color(1.0f, 1.0f, 0.0f);
	    draw_limb(1.0f, 0.35f);

	    //limb
	    gl.push_matrix();
	    gl.translate(0.0f, 0.0f, -0.75f);
	    gl.color(0.8f, 0.75f, 0.75f);
	    draw_limb(0.25f, 0.2f);
	    gl.pop_matrix();

	    //head
	    draw_head();

	    //arms
	    draw_arm(1.0f, 0.0f, 20.0f, -10.0f, 20.0f, t);
	    draw_arm(-1.0f, 0.0f, 20.0f, -10.0f, 20.0f, t);
	    
	    //legs
	    draw_leg(1.0f, -40.0f, -40.0f, 80.0f, 60.0f, -t);
	    draw_leg(-1.0f, -40.0f, -40.0f, 80.0f, 60.0f, -t);

	    gl.pop_matrix();
	}
	
	//sit character
	public void draw_sit(float t)
	{
	    gl.push_matrix();
	    gl.translate(0.0f, 3.5f, 0.0f);
	    gl.rotate(90.0f, 1.0f, 0.0f, 0.0f);

	    //body
	    gl.push_matrix();
	    gl.color(0.5f, 0.0f, 0.0f);
	    gl.scale(1.0f, 0.75f, 1.0f);
	    ObjectInstance.sphere.draw(0.65f, null);
	    gl.pop_matrix();
	    
	    gl.color(1.0f, 1.0f, 0.0f);
	    draw_limb(1.0f, 0.35f);

	    //neck
	    gl.push_matrix();
	    gl.translate(0.0f, 0.0f, -0.75f);
	    gl.color(0.8f, 0.75f, 0.75f);
	    draw_limb(0.25f, 0.2f);
	    gl.pop_matrix();

	    //head
	    draw_head();

	    //arms
	    draw_arm(1.0f, -135.0f, 20.0f, -10.0f, 20.0f, t);
	    draw_arm(-1.0f, -135.0f, 20.0f, -10.0f, 20.0f, t);
	    
	    //legs
	    draw_leg(1.0f, -90.0f, 0.0f, 80.0f, 10.0f, -t * 0.2f);
	    draw_leg(-1.0f, -90.0f, 0.0f, 80.0f, 10.0f, t * 0.2f);

	    gl.pop_matrix();
	}
}
