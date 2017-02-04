package com.typokign.fps.engine.rendering;

/**
 * Created by Typo Kign on 1/29/2017.
 */
public class Attenuation {
	private float constant;
	private float linear;
	private float exponent;

	public static Attenuation ACCURATE = new Attenuation(0,0,1); // physically accurate attenuation equation is x^2

	public Attenuation(float constant, float linear, float exponent) {
		this.constant = constant;
		this.linear = linear;
		this.exponent = exponent;
	}

	public float getConstant() {
		return constant;
	}

	public void setConstant(float constant) {
		this.constant = constant;
	}

	public float getLinear() {
		return linear;
	}

	public void setLinear(float linear) {
		this.linear = linear;
	}

	public float getExponent() {
		return exponent;
	}

	public void setExponent(float exponent) {
		this.exponent = exponent;
	}
}