
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.CursorLoader;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import GraphicsObjects.*;
import objects3D.*;

//Main windows class controls and creates the 3D virtual world , please do not change this class but edit the other classes to complete the assignment. 
// Main window is built upon the standard Helloworld LWJGL class which I have heavily modified to use as your standard openGL environment. 
// 

/* There are two visual modes: free visual and character controlled visual
 * Press v to toggle vision
 * Free vision mode: wasd controls movement qe turn, mouse drag to control direction
 * Character control mode: wasd controls movement qe turn, mouse movement controls direction, no mouse pointer displayed
 */

// Do not touch this class, I will be making a version of it for your 3rd Assignment 
public class MainWindow {

	private boolean MouseOnepressed = true;
	private boolean BadAnimation = false; // Load my new animation, so the inital bad animation is false
	private boolean Earth = false;
	
	/** position of pointer */
	//float x = 400, y = 300;
	/** angle of rotation */
	//float rotation = 0;
	/** time at last frame */
	long lastFrame;
	/** frames per second */
	int fps;
	/** last fps time */
	long lastFPS;
	
	//mouse control
	private boolean dragMode = false;
	int LastMouseX = -1;	//Mouse movement
	int LastMouseY = -1;
	Cursor backupCur;

	boolean ani=false;
	boolean freeView = true;
	//Camera
	Camera camera = new Camera();
	//Player-controlled visual of the character
	AnimationView humanView = new AnimationView();

	long myDelta = 0; // to use for animation
	float Alpha = 0; // to use for animation
	long StartTime; // beginAnimiation

	//Arcball MyArcball = new Arcball();

	boolean DRAWGRID = false;
	boolean waitForKeyrelease = true;
	
	float pullX = 0.0f; // arc ball X cord.
	float pullY = 0.0f; // arc ball Y cord.
	
	//Model buffering, here I changed the way models are drawn to ensure speed
	public static Human myHuman = new Human();
	MatchMan myMatchman = new MatchMan();
	
	//Texture
	Texture texEarth;
	Texture texRoom;
	Texture texSkybox;
	Texture texDirt;
	Texture texFootball;
	Texture texture4;
	Texture tex2021;
	Texture texStone;
	Texture texAD1;
	Texture texAD2;
	
	//font
	glsprite_font font = new glsprite_font();
	
	Lamp lamps[] = new Lamp[4];
	
	//Three fireflies
	GlowWorm worm1;	
	GlowWorm worm2;	
	GlowWorm worm3;	

	static int ballCount = 10;
	Ball balls[];
	
	//Character1 chases fireflies
	vec3 humanPosition = new vec3(0.0f);	//Current position of character 1
	float humanAngle = 0.0f;				//Angle of character1
	int wormID = 0;
	
	int OrthoNumber = 1200; // using this for screen size, making a window of 1200 x 800 so aspect ratio 3:2
							// do not change this for assignment 3
							// but you can change everything for your project

	static vec3 light0_position = new vec3(100.0f, 100.0f, 100.0f);
	static vec3 light1_position = new vec3(0.0f, 100.0f, 0.0f);
	static vec3 light2_position = new vec3(40.0f, 20.0f, 0.0f);

	/**
	 * Calculate how many milliseconds have passed since last frame.
	 * 
	 * @return milliseconds passed since last frame
	 */
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		
		return delta;
	}

	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}
	
	// Show and hide the cursor when switching modes
	void showCursor(boolean value)
	{
		if(value) {
			try {
				Mouse.setNativeCursor(backupCur);
			} catch (LWJGLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			backupCur = Mouse.getNativeCursor();
			ByteBuffer buf = BufferUtils.createByteBuffer(4);
			byte b = 0;
			buf.put(b).put(b).put(b).put(b).flip();
			Cursor cur;
			try {
				cur = CursorLoader.get().getCursor(buf, 0, 0, 1, 1);
				Mouse.setNativeCursor(cur);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LWJGLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//Initializing opengl
	public void initGL() {
		GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		
		//Switch character control initially
		freeView = false;
		showCursor(false);
		ani=false;
		LastMouseX = Display.getWidth() / 2;
		LastMouseY = Display.getHeight() / 2;
		
		
		gl.directionLight(GL11.GL_LIGHT0, light0_position, colors.white.mul(0.2f));
		GL11.glEnable(GL11.GL_LIGHTING);		// switch lighting on
		GL11.glEnable(GL11.GL_DEPTH_TEST); 		// make sure depth buffer is switched on
		GL11.glEnable(GL11.GL_NORMALIZE); 		// normalize normal vectors for safety
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);	// enable color material
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);		// enable texture 2D

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		//Initialising the font
		font.init("res/font20.png");
		
		try {
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // load in texture

		//Lamp
		lamps[0] = new Lamp(GL11.GL_LIGHT1, new vec3(58.0f, 0.0f, 40.0f), 20.0f, 45.0f, colors.white);
		lamps[1] = new Lamp(GL11.GL_LIGHT2, new vec3(-58.0f, 0.0f, 40.0f), 20.0f, 135.0f, colors.white);
		lamps[2] = new Lamp(GL11.GL_LIGHT3, new vec3(58.0f, 0.0f, -40.0f), 20.0f, -45.0f, colors.white);
		lamps[3] = new Lamp(GL11.GL_LIGHT4, new vec3(-58.0f, 0.0f, -40.0f), 20.0f, -135.0f, colors.white);
		
		worm1 = new GlowWorm(GL11.GL_LIGHT5, colors.yellow);		//Firefly1, occupying light source No. 5
		worm2 = new GlowWorm(GL11.GL_LIGHT6, colors.magenta);	//Firefly2, occupying light source No. 5
		worm3 = new GlowWorm(GL11.GL_LIGHT7, colors.cyan);		//Firefly3, occupying light source No. 5
		
		balls = new Ball[ballCount];
		for(int i=0; i<ballCount ; ++i) {
			balls[i] = new Ball();
			balls[i].reset();
		}
	}
	
	/*
	 * Any additional textures for your assignment should be written in here. Make a
	 * new texture variable for each one so they can be loaded in at the beginning
	 */
	public void init() throws IOException {

		texEarth = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/earthspace.png"));
		texRoom = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/room.png"));
		texFootball = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/football.png"));
		texture4 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/shoulder.png"));

		texSkybox = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/sky.jpg"));
		texDirt = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/scene.png"));
		tex2021 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/2021.png"));
		texStone = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/concreteTexture.png"));
		
		texAD1 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/ad1.png"));
		texAD2 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/ad2.png"));
	}
	
	//Wait for a while
	public void sleep(long ms)
	{
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Camera view hunmanView object
	void updateView()
	{
		vec3 dir = new vec3(humanView.dir);
		vec3 pos = humanView.position.sub(dir.mul(5.0f));
		camera.LookAt(pos.x, pos.y + 6.0f, pos.z, humanView.position.x, humanView.position.y + 4.0f, humanView.position.z);
	}
	
	//Mouse operation
	void updateMouse()
	{
		int MouseX = Mouse.getX();
		int MouseY = Mouse.getY();
		int WheelPostion = Mouse.getDWheel();

		boolean MouseButonPressed = Mouse.isButtonDown(0);
		
		if(freeView) {
			if (MouseButonPressed && !MouseOnepressed) {
				MouseOnepressed = true;
				dragMode = true;
			} else if (!MouseButonPressed) {
				// System.out.println("Mouse drag mode end ");
				MouseOnepressed = false;
				dragMode = false;
			}
			
			if (dragMode) {
				float ox = (LastMouseX - MouseX) * 0.05f;
				float oy = (LastMouseY - MouseY) * 0.05f;
				
				camera.touth(-ox, oy);
				//humanView.touth(-ox * 2.0f,  oy);
			}

			LastMouseX = MouseX;
			LastMouseY = MouseY;
		}
		else {
			
			if (MouseButonPressed && !MouseOnepressed) {
				
				MouseOnepressed = true;
				// System.out.println("Mouse drag mode");
				//MyArcball.startBall(MouseX, MouseY, 1200, 800);
				dragMode = true;
				
				//Mouse click to shoot the ball
				if(freeView == false) {
					//Select the position a little in front of the character
					ani= true;
					vec3 dir = new vec3(humanView.dir.x, 0.0f, humanView.dir.z);
					vec3 pos = humanView.position.add(dir.mul(2.0f));
					for(int i=0; i< ballCount; ++i) {
						//Calculate the distance between the ball and the pos
						dir = balls[i].position.sub(pos);
						float len = dir.length();
						//Can play soccer
						if(len < 1.5f) {
							dir = balls[i].position.sub(humanView.position);
							dir.y += 2.0f;//The height of the kick
							dir = dir.normal();
							balls[i].setForce(dir.mul(20.0f));
						}
					}
				}
		
			} else if (!MouseButonPressed) {
				// System.out.println("Mouse drag mode end ");
				MouseOnepressed = false;
				dragMode = false;
			}
			
			float ox = (LastMouseX - MouseX) * 0.05f;
			float oy = (LastMouseY - MouseY) * 0.05f;
			humanView.touth(-ox * 2.0f,  -oy * 0.5f);

			LastMouseX = Display.getWidth() / 2;
			LastMouseY = Display.getHeight() / 2;
			Mouse.setCursorPosition(LastMouseX, LastMouseY);
		}
	}
	
	public void update(int delta) {
		// rotate quad
		// rotation += 0.01f * delta;
		
		vec3 dir;

		//Delay per frame, converted to floating point
		float delay = (float)delta * 0.001f;
		//System.out.printf("%f\n", delay);
		
		//Press esc to exit
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			System.exit(0);
		}
		else if(Keyboard.isKeyDown(Keyboard.KEY_G)) {
			DRAWGRID = !DRAWGRID;
			sleep(100);
		}
		else if(Keyboard.isKeyDown(Keyboard.KEY_V)) {
			freeView = !freeView;
			
			if(freeView) {
				showCursor(true);
			}
			else {
				showCursor(false);
			}
			
			sleep(100);
		}
		else {
			//If in free view mode, control the camera to move freely. Otherwise follow the villain vision
			if(freeView) {
				camera.update(delay);
				ani=false;
			}
			else {
				if(humanView.ani()==true) {
					ani=true;
					
				}else {
					ani=false;
					
				}
				humanView.update(delay);
				updateView();
			}
		}
		
		
		//Mouse
		updateMouse();
		
		//Update Firefly Logic
		worm1.update(delay);
		worm2.update(delay);
		worm3.update(delay);
		
		//balls
		for(int i=0; i<ballCount; ++i) {
			balls[i].update(delay);
		}
		
		//Character1 charing for fireflies
		GlowWorm w;
		if(wormID == 0) {
			w = worm1;
		}
		else if(wormID == 1) {
			w = worm2;
		}
		else {
			w = worm3;
		}
		
		//The distance between the character1 and the fireflies
		dir = w.position.sub(humanPosition);
		dir.y = 0.0f;
		float len = dir.length();
		if(len < 1.0f) {
			++wormID;
			if(wormID >= 3) {
				wormID = 0;
			}
		}
		else {
			//Calculating the moving vector normals
			dir = dir.normal();
			
			//Velocity vector
			dir = dir.mul(2.0f * delay);
			
			//Movement
			humanPosition = humanPosition.add(dir);
			//Calculating angle
			humanAngle = (float)Math.toDegrees(Math.atan2(dir.x, dir.z)) + 180.0f;
		}
		
		
		updateFPS(); // update FPS Counter
	}
	
	//Perspective projection mode
	public void projectiveView() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		
		float width = OrthoNumber;
		float height = OrthoNumber * 0.66f;
		float assept = width / height;
		GLU.gluPerspective(60.0f, assept, 1.0f, 10000.0f);
		
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		//GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_LIGHTING);
	}
	
	//Orthogonal projection mode (for displaying fonts)
	public void orthoView()
	{
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();

		//float width = OrthoNumber;
		//float height = OrthoNumber * 0.66f;
		float width = Display.getWidth();
		float height = Display.getHeight();
		GLU.gluOrtho2D(0.0f, width, height, 0.0f);
		
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_LIGHTING);
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}
	
	//Extended functions, drawing figures and shadows
	void drawHuman(float delta, float angle,boolean ani)
	{
		gl.setMaterial(colors.white.mul(0.2f), colors.white.mul(0.6f), colors.white.mul(1.0f), 32.0f);
		gl.color(colors.white);
		myHuman.DrawHuman(delta, true, ani);
		
		//Shadows
		GL11.glPushMatrix();
		{
			gl.color(colors.black);
			gl.setMaterial(colors.black, colors.black, colors.black, 1.0f);
			GL11.glRotatef(angle, 0.0f, 1.0f, 0.0f);
			GL11.glScalef(1.0f, 0.01f, 1.0f);
			GL11.glRotatef(45.0f, 1.0f, 0.01f, 1.0f);
			myHuman.DrawHuman(delta, false,ani);
		}
		GL11.glPopMatrix();
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		gl.setMaterial(colors.white.mul(0.2f), colors.white.mul(0.6f), colors.white.mul(1.0f), 32.0f);
	}
	
	
	
	
	/*
	 * You can edit this method to add in your own objects / remember to load in
	 * textures in the INIT method as they take time to load
	 * 
	 */
	public void renderGL() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		//Switching to perspective projection
		projectiveView();
		
		myDelta = getTime() - StartTime;
		float delta = ((float) myDelta) / 10000;
		
		GL11.glLoadIdentity();
		
		//Applying camera vision
		camera.setView();
		
		//Painting the sky box
		ObjectInstance.drawSkybox(camera, texSkybox);

		GL11.glEnable(GL11.GL_LIGHTING);
		//Turning on the directional light will make the whole map brighter at a certain angle
		gl.directionLightPosition(GL11.GL_LIGHT0, light0_position);
		
		//setting light source for lamps
		for(int i=0; i<4; ++i) {
			lamps[i].setLight();
		}
		//setting light source for fireflies
		worm1.setLight();
		worm2.setLight();
		worm3.setLight();
		
	
		/*
		 * This code draws a grid to help you view the human models movement You may
		 * change this code to move the grid around and change its starting angle as you
		 * please
		 */
		if (DRAWGRID) {
			GL11.glColor3f(0.5f, 0.5f, 1.0f);
			GL11.glPushMatrix();
			Grid MyGrid = new Grid();
			MyGrid.DrawGrid();
			GL11.glPopMatrix();
		}
		
		gl.color(colors.white);
		gl.setMaterial(colors.white.mul(0.2f), colors.white.mul(0.6f), colors.white.mul(1.0f), 16.0f);
		
		//Draw the plane
		Plane plane = new Plane();
		plane.draw(128, 128, texDirt);
		
		//Draw trees
		ObjectInstance.drawTree(50.0f, 0.0f, -45.0f);
		ObjectInstance.drawTree(25.0f, 0.0f, -45.0f);
		ObjectInstance.drawTree(0.0f, 0.0f, -45.0f);
		ObjectInstance.drawTree(-25.0f, 0.0f, -45.0f);
		ObjectInstance.drawTree(-50.0f, 0.0f, -45.0f);
		
		{//Draw the earth
			GL11.glColor3f(1.0f, 1.0f, 1.0f);
			GL11.glPushMatrix();
			GL11.glTranslatef(0.0f, 10.0f, 45.0f);
			GL11.glRotatef(getTime() * 0.01f, 0.0f, 1.0f, 0.0f);
			//GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
			//GL11.glScalef(10.0f,  10.0f,  10.0f);
			ObjectInstance.sphere.draw(5.0f, texEarth);
			GL11.glPopMatrix();
			
			GL11.glPushMatrix();
			GL11.glTranslatef(0.0f, 0.0f, 45.0f);
			GL11.glScalef(5.0f, 5.0f, 5.0f);
			tex2021.bind();
			ObjectInstance.cube.draw();
			GL11.glPopMatrix();
		}
		
		//draw lamps
		for(int i=0; i<4; ++i) {
			lamps[i].draw();
		}
		
		//draw fireflies
		worm1.draw();
		worm2.draw();
		worm3.draw();
		
		//draw bouching footballs
		GL11.glColor3f(0.8f, 0.8f, 0.8f);
		for(int i=0; i<ballCount; ++i) {
			GL11.glPushMatrix();
			GL11.glTranslatef(balls[i].position.x, balls[i].position.y, balls[i].position.z);
			GL11.glScalef(0.5f, 0.5f, 0.5f);
			vec3 p = balls[i].position.mul(100.0f);
			gl.rotate(p.x, 1.0f, 0.0f, 0.0f);//A simple simulation of the spin of the ball
			gl.rotate(p.z, 0.0f, 0.0f, 1.0f);
			ObjectInstance.sphere.draw(texFootball);
			GL11.glPopMatrix();
		}
		
		// Two goals
		gl.push_matrix();
		gl.translate(-57.0f, 0.0f, 0.0f);
		gl.rotate(90.0f, 0.0f, 1.0f, 0.0f);
		ObjectInstance.draw_door();
		gl.pop_matrix();
		
		gl.push_matrix();
		gl.translate(57.0f, 0.0f, 0.0f);
		gl.rotate(-90.0f, 0.0f, 1.0f, 0.0f);
		ObjectInstance.draw_door();
		gl.pop_matrix();
		
		
		//Spectator stands
		ObjectInstance.drawStage(texStone, 1.0f);
		ObjectInstance.drawStage(texStone, -1.0f);
		
		//Drawing the audience
		
		//myMatchman.draw_move(delta * 10.0f);
		//left
		gl.push_matrix();
		gl.translate(16.0f, 0.0f, 42.0f);
		gl.rotate(180.0f, 0.0f, 1.0f, 0.0f);
		myMatchman.draw_jump(1.0f, delta * 100.0f);
		gl.pop_matrix();
		
		gl.push_matrix();
		gl.translate(30.0f, 2.0f, 47.0f);
		gl.rotate(180.0f, 0.0f, 1.0f, 0.0f);
		myMatchman.draw_sit(delta * 100.0f);
		gl.pop_matrix();
		
		gl.push_matrix();
		gl.translate(20.0f, 2.0f, 47.0f);
		gl.rotate(180.0f, 0.0f, 1.0f, 0.0f);
		myMatchman.draw_sit(delta * 100.0f);
		gl.pop_matrix();
		
		gl.push_matrix();
		gl.translate(35.0f, 0.0f, 45.0f);
		gl.rotate(180.0f, 0.0f, 1.0f, 0.0f);
		myMatchman.draw_sit(delta * 100.0f);
		gl.pop_matrix();
		
		//right
		gl.push_matrix();
		gl.translate(-21.0f, 6.0f, 51.0f);
		gl.rotate(180.0f, 0.0f, 1.0f, 0.0f);
		myMatchman.draw_sit(delta * 100.0f);
		gl.pop_matrix();
		
		gl.push_matrix();
		gl.translate(-26.0f, 2.0f, 47.0f);
		gl.rotate(180.0f, 0.0f, 1.0f, 0.0f);
		myMatchman.draw_sit(delta * 100.0f);
		gl.pop_matrix();
		
		gl.push_matrix();
		gl.translate(-15.0f, 2.0f, 47.0f);
		gl.rotate(180.0f, 0.0f, 1.0f, 0.0f);
		myMatchman.draw_sit(delta * 100.0f);
		gl.pop_matrix();
		
		gl.push_matrix();
		gl.translate(-30.0f, 0.0f, 45.0f);
		gl.rotate(180.0f, 0.0f, 1.0f, 0.0f);
		myMatchman.draw_sit(delta * 100.0f);
		gl.pop_matrix();
		
		//Billboards
		gl.push_matrix();
		gl.translate(-12.5f, 0.0f, -45.0f);
		ObjectInstance.draw_board(16.0f, 9.0f, 4.0f, texAD1);
		gl.pop_matrix();
		
		gl.push_matrix();
		gl.translate(-37.5f, 0.0f, -45.0f);
		ObjectInstance.draw_board(16.0f, 9.0f, 4.0f, texAD2);
		gl.pop_matrix();
		
		gl.push_matrix();
		gl.translate(12.5f, 0.0f, -45.0f);
		ObjectInstance.draw_board(16.0f, 9.0f, 4.0f, texAD1);
		gl.pop_matrix();
		
		gl.push_matrix();
		gl.translate(37.5f, 0.0f, -45.0f);
		ObjectInstance.draw_board(16.0f, 9.0f, 4.0f, texAD2);
		gl.pop_matrix();
		
		gl.push_matrix();
		gl.translate(25.0f, 0.0f, 56.0f);
		gl.rotate(180.0f, 0.0f, 1.0f, 0.0f);
		ObjectInstance.draw_board(40.0f, 9.0f, 8.0f, texAD1);
		gl.pop_matrix();
		
		gl.push_matrix();
		gl.translate(-25.0f, 0.0f, 56.0f);
		gl.rotate(180.0f, 0.0f, 1.0f, 0.0f);
		ObjectInstance.draw_board(40.0f, 9.0f, 8.0f, texAD2);
		gl.pop_matrix();
		
			
		//draw character1
		GL11.glPushMatrix();
		GL11.glTranslatef(humanPosition.x, humanPosition.y, humanPosition.z);
		GL11.glRotatef(humanAngle, 0.0f, 1.0f, 0.0f);
		drawHuman(delta, -humanAngle,true);
		GL11.glPopMatrix();
		
		//Drawing the control character2
		GL11.glPushMatrix();
		GL11.glTranslatef(humanView.position.x, humanView.position.y, humanView.position.z);
		GL11.glRotatef(-humanView.yAngle - 90.0f, 0.0f, 1.0f, 0.0f);
		drawHuman(delta, -humanView.yAngle,ani);
		
		
		GL11.glPopMatrix();
	}
	
	//display font
	public void renderFont()
	{
		//Switching to orthogonal projection
		orthoView();
		
		//font color
		GL11.glColor3f(1.0f, 1.0f, 1.0f);
		
		float y = 0;// 600.0f;

		long t = this.getTime();
		long ms = t % 1000;
		t = t / 1000;
		
		font.printf(0.0f,  y += 24.0f, "Mouse: %d, %d", Mouse.getX(), Mouse.getY());
		
		font.printf(0.0f,  y += 24.0f, "Camera.Pos:  %f, %f, %f", camera.position.x, camera.position.y, camera.position.z);
		font.printf(0.0f,  y += 24.0f, "Camera.Dir:  %f, %f, %f", camera.dir.x, camera.dir.y, camera.dir.z);
		
		font.printf(0.0f,  y += 24.0f, "Human.Pos:  %f, %f, %f", humanView.position.x, humanView.position.y, humanView.position.z);
		font.printf(0.0f,  y += 24.0f, "Human.Angle  :  %f, %f", humanView.yAngle, humanView.pitchAngle);
		
		font.printf(0.0f,  y += 24.0f, "Free View [V]: %s", freeView ? "true" : "false");
		font.printf(0.0f,  y += 24.0f, "Show Grid [G]: %s", DRAWGRID ? "true" : "false");
		
		
		//drawing.draw_image(100,  100, 192, 132, 0.0f, 0.0f, 0.50f, 0.50f);
	}
	
	// support method to aid in converting a java float array into a Floatbuffer
	// which is faster for the opengl layer to process

	public void start() {

		StartTime = getTime();
		try {
			Display.setDisplayMode(new DisplayMode(1200,800));//1200,800
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		initGL(); // init OpenGL
		getDelta(); // call once before loop to initialise lastFrame
		lastFPS = getTime(); // call before loop to initialise fps timer

		while (!Display.isCloseRequested()) {
			int delta = getDelta();
			update(delta);
			renderGL();
			renderFont();		//output texts
			Display.update();
			Display.sync(120);  //cap fps to 120fps
		}

		Display.destroy();
	}

	// main
	public static void main(String[] argv) {
		MainWindow hello = new MainWindow();
		hello.start();
	}
}
