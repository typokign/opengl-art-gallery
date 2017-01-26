package com.typokign.fps;

import org.lwjgl.input.Keyboard;

/**
 * Created by Typo Kign on 1/25/2017.
 */
public class Camera {

	public static final Vector3f yAxis = new Vector3f(0,1,0); // world-up

	private Vector3f position;
	private Vector3f forward; // cam-forward
	private Vector3f up; // cam-up

	public Camera() {
		this(new Vector3f(0,0,0), new Vector3f(0,0,1), new Vector3f(0,1,0));
	}

	public Camera(Vector3f position, Vector3f forward, Vector3f up) {
		this.position = position;
		this.forward = forward;
		this.up = up;

		up.normalize();
		forward.normalize();
	}

	public void input() {
		float movAmt = (float) (10 * Time.getDelta());
		float rotAmt = (float) (100 * Time.getDelta());

		if (Input.getKey(Keyboard.KEY_W)) {
			move(getForward(), movAmt);
		}
		if (Input.getKey(Keyboard.KEY_S)) {
			move(getForward(), -movAmt);
		}
		if (Input.getKey(Keyboard.KEY_A)) {
			move(getLeft(), movAmt);
		}
		if (Input.getKey(Keyboard.KEY_D)) {
			move(getRight(), movAmt);
		}

		if (Input.getKey(Keyboard.KEY_UP)) {
			rotateX(-rotAmt);
		}
		if (Input.getKey(Keyboard.KEY_DOWN)) {
			rotateX(rotAmt);
		}
		if (Input.getKey(Keyboard.KEY_LEFT)) {
			rotateY(-rotAmt);
		}
		if (Input.getKey(Keyboard.KEY_RIGHT)) {
			rotateY(rotAmt);
		}

	}

	public void move(Vector3f direction, float magnitude) {
		position = position.add(direction.mul(magnitude));
	}

	public void rotateX(float angle) {
		Vector3f hAxis = yAxis.crossProduct(forward);
		hAxis.normalize();

		forward.rotate(angle, hAxis);
		forward.normalize();

		up = forward.crossProduct(hAxis);
		up.normalize();
	}

	public void rotateY(float angle) {
		Vector3f hAxis = yAxis.crossProduct(forward);
		hAxis.normalize();

		forward.rotate(angle, yAxis);
		forward.normalize();

		up = forward.crossProduct(hAxis);
		up.normalize();
	}

	public Vector3f getLeft() {
		Vector3f left = forward.crossProduct(up); // remember right-hand rule
		left.normalize();

		return left;
	}

	public Vector3f getRight() {
		Vector3f right = up.crossProduct(forward);
		right.normalize();

		return right;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Vector3f getForward() {
		return forward;
	}

	public void setForward(Vector3f forward) {
		this.forward = forward;
	}

	public Vector3f getUp() {
		return up;
	}

	public void setUp(Vector3f up) {
		this.up = up;
	}
}
