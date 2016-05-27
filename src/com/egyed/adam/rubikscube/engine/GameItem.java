package com.egyed.adam.rubikscube.engine;

import com.egyed.adam.rubikscube.engine.graphics.Mesh;
import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * Created by Adam on 5/18/16.
 */
public class GameItem {

    private final Mesh mesh;

    private final Vector3f position;

    private float scale;

    private Matrix4f rotation;

    public GameItem(Mesh mesh) {
        this.mesh = mesh;
        position = new Vector3f(0, 0, 0);
        scale = 1;
        rotation = new Matrix4f();
        rotation.identity();
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(float x, float y, float z) {
        this.position.x = x;
        this.position.y = y;
        this.position.z = z;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public Matrix4f getRotation() {
        return rotation;
    }


    public void setRotationDegrees(float x, float y, float z) {
        this.rotation = new Matrix4f();

        float rotateXAngle = (float) Math.toRadians(x);
        float rotateYAngle = (float) Math.toRadians(y);
        float rotateZAngle = (float) Math.toRadians(z);

        Matrix4f rotationUpdate = new Matrix4f().identity();

        if (rotateXAngle!=0) {
            Matrix4f xRotation = new Matrix4f().identity();
            float sinAlpha = (float) Math.sin(rotateXAngle);
            float cosAlpha = (float) Math.cos(rotateXAngle);
            xRotation.m11 = cosAlpha;
            xRotation.m12 = -sinAlpha;
            xRotation.m21 = sinAlpha;
            xRotation.m22 = cosAlpha;

            rotationUpdate.mul(xRotation);
        }
        if (rotateYAngle!=0) {
            Matrix4f yRotation = new Matrix4f().identity();
            float sinBeta = (float) Math.sin(rotateYAngle);
            float cosBeta = (float) Math.cos(rotateYAngle);
            yRotation.m00 = cosBeta;
            yRotation.m02 = sinBeta;
            yRotation.m20 = -sinBeta;
            yRotation.m22 = cosBeta;

            rotationUpdate.mul(yRotation);
        }
        if (rotateZAngle!=0) {
            Matrix4f zRotation = new Matrix4f().identity();
            float sinGamma = (float) Math.sin(rotateZAngle);
            float cosGamma = (float) Math.cos(rotateZAngle);
            zRotation.m00 = cosGamma;
            zRotation.m01 = -sinGamma;
            zRotation.m10 = sinGamma;
            zRotation.m11 = cosGamma;

            rotationUpdate.mul(zRotation);
        }

        this.rotation.mul(rotationUpdate);
    }

    /*
    public void addRotation(float x, float y, float z) {
        this.rotation.x += x;
        this.rotation.y += y;
        this.rotation.z += z;
    }*/



    public void addRotation(Matrix4f additionalRotation) {
        Matrix4f additional = new Matrix4f(additionalRotation);
        this.rotation = additional.mul(this.rotation);
    }

    public Mesh getMesh() {
        return mesh;
    }
}