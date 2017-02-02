package com.typokign.fps.engine.core;

import com.typokign.fps.engine.math.Matrix4f;
import com.typokign.fps.engine.math.Vector3f;
import com.typokign.fps.engine.rendering.Camera;

/**
 * Created by Typo Kign on 1/23/2017.
 */
public class Transform {
	private static float zNear; //clipping
	private static float zFar;
	private static float width; // screen width
	private static float height; // screen height
	private static float fov; // field of view
    private static Camera camera;

	// dx, dy, dz of the translation
	private Vector3f translation;
	private Vector3f rotation;
	private Vector3f scale;

	public Transform() {
		translation = new Vector3f(0,0,0);
		rotation = new Vector3f(0, 0, 0);

		// note to self; don't set this default to 0,0,0. 48 hours of debugging for this
		scale = new Vector3f(1, 1, 1);
	}

	public Transform(Vector3f translation, Vector3f rotation, Vector3f scale) {
		this.translation = translation;
		this.rotation = rotation;
		this.scale = scale;
	}

	// combine all 3 transformations into a single matrix4f
	public Matrix4f getTransformation() {
		Matrix4f transMatrix = new Matrix4f().initTranslation(translation.getX(), translation.getY(), translation.getZ());
		Matrix4f rotMatrix = new Matrix4f().initRotation(rotation.getX(), rotation.getY(), rotation.getZ());
		Matrix4f scaleMatrix = new Matrix4f().initScale(scale.getX(), scale.getY(), scale.getZ());

		// order matters :
		// first scale (does not affect center)
		// then rotate (in order to rotate on the pre-existing axis)
		// then translate
		return transMatrix.mul(rotMatrix.mul(scaleMatrix));
	}

	public Matrix4f getProjectedTransformation() {
		Matrix4f transformationMatrix = getTransformation();
		Matrix4f projectionMatrix = new Matrix4f().initProjection(fov, width, height, zNear, zFar);
		Matrix4f cameraRotation = new Matrix4f().initCamera(camera.getForward(), camera.getUp());
		Matrix4f cameraTranslation = new Matrix4f().initTranslation(-camera.getPosition().getX(), -camera.getPosition().getY(), - camera.getPosition().getZ()); // camera never actually moves, move the world opposite the direction of camera for effect

		return projectionMatrix.mul(cameraRotation.mul(cameraTranslation.mul(transformationMatrix)));
	}

	public Vector3f getTranslation() {
		return translation;
	}

	public static void setProjection(float fov, float width, float height, float zNear, float zFar) {
		Transform.fov = fov;
		Transform.width = width;
		Transform.height = height;
		Transform.zNear = zNear;
		Transform.zFar = zFar;
	}

	public void setTranslation(Vector3f translation) {
		this.translation = translation;
	}

	public void setTranslation(float x, float y, float z) {
		this.translation = new Vector3f(x, y, z);
	}

	public Vector3f getRotation() {
		return rotation;
	}

	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}

	public void setRotation(float x, float y, float z) {
		this.rotation = new Vector3f(x, y, z);
	}

	public Vector3f getScale() {
		return scale;
	}

	public void setScale(Vector3f scale) {
		this.scale = scale;
	}

	public void setScale(float x, float y, float z) {
		this.scale = new Vector3f(x, y, z);
	}

	public static Camera getCamera() {
		return camera;
	}

	public static void setCamera(Camera camera) {
		Transform.camera = camera;
	}
}
