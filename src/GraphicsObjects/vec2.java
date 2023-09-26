package GraphicsObjects;

public class vec2 {
	public static int LENGTH = 2;
	
	public float x = 0;
	public float y = 0;

	public vec2() {
		x = 0.0f;
		y = 0.0f;
	}

	public vec2(float scalar) {
		this.x = scalar;
		this.y = scalar;
	}

	public vec2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public vec2(vec2 v) {
		this.x = v.x;
		this.y = v.y;
	}
	
	// set Vector values
	public void set(float scalar)
	{
		this.x = scalar;
		this.y = scalar;
	}
	
	// set Vector values
	public void set(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	// implement Vector plus a Vector
	public vec2 add(vec2 v) {
		return new vec2(x + v.x, y + v.y);
	}

	// implement Vector minus a Vector
	public vec2 sub(vec2 v) {
		return new vec2(x - v.x, y - v.y);
	}

	// implement Vector plus a Vector
	public vec2 mul(vec2 v) {
		return new vec2(x * v.x, y * v.y);
	}

	// Implement a Vector + Scalar
	public vec2 add(float scalar) {
		return new vec2(x + scalar, y + scalar);
	}

	// Implement a Vector - Scalar
	public vec2 sub(float scalar) {
		return new vec2(x - scalar, y - scalar);
	}

	// Implement a Vector * Scalar
	public vec2 mul(float scalar) {
		return new vec2(x * scalar, y * scalar);
	}

	// implement returning the negative of a Vector
	public vec2 negate() {
		return new vec2(-x, -y);
	}

	// implement getting the dot product of Vector.Vector
	public float dot(vec2 v) {
		return x * v.x + y * v.y;
	}

	// implement getting the length of a Vector
	public float length() {
		return (float) Math.sqrt(dot(this));
	}

	// Just to avoid confusion here is getting the Normal of a Vector
	public vec2 normal() {
		float LengthOfTheVector = this.length();
		return this.mul(1.0f / LengthOfTheVector);
	}
}
