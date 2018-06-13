package com.mobike.library;


import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import java.util.Random;

/**
 * Created by kimi on 2017/7/8 0008.
 * Email: 24750@163.com
 */

public class UserMedal {

    public static final String TAG = UserMedal.class.getSimpleName();

    private World world;
    private float dt = 1f / 60f;
    private int velocityIterations = 3;
    private int positionIterations = 10;
    private float friction = 0.3f, density = 0.5f, restitution = 0.3f, ratio = 50, RATE = 50;
    private int width, height;
    private boolean enable = true;
    private final Random random = new Random();
    protected Matrix matrix;
    private ViewGroup mViewgroup;
    protected Paint paint;
    protected BodyDef bd;
    protected float rate = 1.6f;//放大倍率


    public UserMedal(ViewGroup viewgroup) {
        this.mViewgroup = viewgroup;
        density = viewgroup.getContext().getResources().getDisplayMetrics().density;

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);// 无锯齿
        paint.setStyle(Paint.Style.FILL);
        matrix = new Matrix();
    }

    public void onSizeChanged(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void onDraw(Canvas canvas) {
        if (!enable) {
            return;
        }
        world.step(dt, velocityIterations, positionIterations);
        int childCount = mViewgroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = mViewgroup.getChildAt(i);
            Body body = (Body) view.getTag(R.id.mobike_body_tag);
            if (body != null) {
                view.setX(metersToPixels(body.getPosition().x) - view.getWidth() / 2);
                view.setY(metersToPixels(body.getPosition().y) - view.getHeight() / 2);
                view.setRotation(radiansToDegrees(body.getAngle() % 360));
            }
        }
//        mYcanvas(canvas);
        mViewgroup.invalidate();
    }


    public void onLayout(boolean changed) {
        createWorld(changed);
    }

    public void onStart() {
        setEnable(true);
    }

    public void onStop() {
        setEnable(false);
    }

    public void update() {
        world = null;
        onLayout(true);
    }

    private void createWorld(boolean changed) {
        if (world == null) {
            world = new World(new Vec2(0, 10.0f));
            createTopAndBottomBounds();
            createLeftAndRightBounds();
        }
        int childCount = mViewgroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = mViewgroup.getChildAt(i);
            Body body = (Body) view.getTag(R.id.mobike_body_tag);
            if (body == null || changed) {
                createBody(world, view);
            }
        }
    }

    private void createBody(World world, View view) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.setType(BodyType.DYNAMIC);

        bodyDef.position.set(pixelsToMeters(view.getX() + view.getWidth() / 2), pixelsToMeters(view.getY() + view.getHeight() / 2));
        Shape shape = null;
        Boolean isCircle = (Boolean) view.getTag(R.id.mobike_view_circle_tag);
        if (isCircle != null && isCircle) {
            shape = createCircleShape(view);
        } else {
            shape = createPolygonShape(view);
        }
        FixtureDef fixture = new FixtureDef();
        fixture.setShape(shape);
        fixture.friction = friction;
        fixture.restitution = restitution;
        fixture.density = density;

        Body body = world.createBody(bodyDef);
        body.createFixture(fixture);
        view.setTag(R.id.mobike_body_tag, body);
        body.setLinearVelocity(new Vec2(random.nextFloat(), random.nextFloat()));
    }

    private Shape createCircleShape(View view) {
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(pixelsToMeters(view.getWidth() / 2));
        return circleShape;
    }

    private Shape createPolygonShape(View view) {
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(pixelsToMeters(view.getWidth() / 2), pixelsToMeters(view.getHeight() / 2));
        return polygonShape;
    }

    private void createTopAndBottomBounds() {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.STATIC;
        PolygonShape box = new PolygonShape();
        float boxWidth = pixelsToMeters(width);
        float boxHeight = pixelsToMeters(ratio);
        box.setAsBox(boxWidth, boxHeight);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = box;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.3f;
        fixtureDef.restitution = 0.5f;

        bodyDef.position.set(0, -boxHeight);
        Body topBody = world.createBody(bodyDef);
        topBody.createFixture(fixtureDef);

        bodyDef.position.set(0, pixelsToMeters(height) + boxHeight);
        Body bottomBody = world.createBody(bodyDef);
        bottomBody.createFixture(fixtureDef);
        bd = new BodyDef();
//        createPolygon(10 + 460 / 2, 305, 460, 10, 0.3f, 0.5f,true);
//        createPolygon2(width / 2, height, boxWidth, 0.3f, 0.5f, true);
    }

    private void mYcanvas(Canvas canvas) {
//        canvas.drawColor(0xff000000);
        //地板
        paint.setColor(0xffffffff);
        paint.setAntiAlias(false); //消除画笔锯齿
        paint.setStyle(Paint.Style.FILL);
//        canvas.drawCircle(width / 2, height, width / 2, paint);
        canvas.drawCircle(width / 2, height * rate, width, paint);
    }


    public Body createPolygon2(float x, float y, float boxWidth,
                               float friction, float restitution,
                               boolean isStatic) {
        //创建多边形皮肤
        CircleShape shape = new CircleShape();
        shape.setRadius(boxWidth);
//        shape.setRadius(boxWidth/2);
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 1.0f; //设置密度
        fd.friction = friction;         //设置摩擦力
        fd.restitution = restitution;   //设置多边形的恢复力

        //设置刚体初始坐标
        bd.type = isStatic ? BodyType.STATIC : BodyType.DYNAMIC;
        bd.position.set(x / RATE, y / RATE * rate);

        //创建物体
        Body body = world.createBody(bd);   //物理世界创建物体

        //此方法改变了
        //body.createShape(pDef);   //为body添加皮肤
        body.createFixture(fd);

        return body;
    }

    public Body createPolygon(float x, float y, float w, float h,
                              float friction, float restitution,
                              boolean isStatic) {
        //创建多边形皮肤
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(w / 2 / RATE, h / 2 / RATE);

        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 1.0f; //设置密度
        fd.friction = friction;         //设置摩擦力
        fd.restitution = restitution;   //设置多边形的恢复力

        //设置刚体初始坐标
        bd.type = isStatic ? BodyType.STATIC : BodyType.DYNAMIC;
        bd.position.set(x / RATE, y / RATE);

        //创建物体
        Body body = world.createBody(bd);   //物理世界创建物体

        //此方法改变了
        //body.createShape(pDef);   //为body添加皮肤
        body.createFixture(fd);

        return body;
    }

    private void createLeftAndRightBounds() {
        float boxWidth = pixelsToMeters(ratio);
        float boxHeight = pixelsToMeters(height);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.STATIC;

        PolygonShape box = new PolygonShape();
        box.setAsBox(boxWidth, boxHeight);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = box;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.3f;
        fixtureDef.restitution = 0.5f;

        bodyDef.position.set(-boxWidth, boxHeight);
        Body leftBody = world.createBody(bodyDef);
        leftBody.createFixture(fixtureDef);


        bodyDef.position.set(pixelsToMeters(width) + boxWidth, 0);
        Body rightBody = world.createBody(bodyDef);
        rightBody.createFixture(fixtureDef);
    }

    private float radiansToDegrees(float radians) {
        return radians / 3.14f * 180f;
    }

    private float degreesToRadians(float degrees) {
        return (degrees / 180f) * 3.14f;
    }

    public float metersToPixels(float meters) {
        return meters * ratio;
    }

    public float pixelsToMeters(float pixels) {
        return pixels / ratio;
    }

    public void random() {
        int childCount = mViewgroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            Vec2 impulse = new Vec2(random.nextInt(1000) - 1000, random.nextInt(1000) - 1000);
            View view = mViewgroup.getChildAt(i);
            Body body = (Body) view.getTag(R.id.mobike_body_tag);
            if (body != null) {
                body.applyLinearImpulse(impulse, body.getPosition(), true);
            }
        }
    }

    public void onSensorChanged(float x, float y) {
        int childCount = mViewgroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            Vec2 impulse = new Vec2(x, y);
            View view = mViewgroup.getChildAt(i);
            Body body = (Body) view.getTag(R.id.mobike_body_tag);
            if (body != null) {
                body.applyLinearImpulse(impulse, body.getPosition(), true);
            }
        }
    }

    public float getFriction() {
        return friction;
    }

    //摩擦
    public void setFriction(float friction) {
        if (friction >= 0) {
            this.friction = friction;
        }
    }

    public float getDensity() {
        return density;
    }

    //密度
    public void setDensity(float density) {
        if (density >= 0) {
            this.density = density;
        }
    }

    public float getRestitution() {
        return restitution;
    }

    //密度
    public void setRestitution(float restitution) {
        if (restitution >= 0) {
            this.restitution = restitution;
        }
    }

    public float getRatio() {
        return ratio;
    }

    //比率
    public void setRatio(float ratio) {
        if (ratio >= 0) {
            this.ratio = ratio;
        }
    }

    public boolean getEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
        mViewgroup.invalidate();
    }
}
