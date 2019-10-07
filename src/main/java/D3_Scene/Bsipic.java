package D3_Scene;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.gl2.GLUT;

import java.io.IOException;

public class Bsipic {

    int[] g_cactus = new int[1];
    GLUquadric g_text;
    GL2 gl;
    float r = 0;
    float r2 = 0;
    private int[] textureArr;
    private int texture;
    private String[] filename;
    private final float MAP = 40.0f;

    double[] g_eye = new double[3];
    double[] g_look = new double[3];
    float rad_xz;                            //角度
    float g_Angle;                           //左右转
    float g_elev;                            //仰俯角
    boolean iskeydown = false;

    GLU glu;

    float x=0;
    float y=0;
    float z=0;

    public Bsipic(GL2 gl) {
        this.gl = gl;
        glu = new GLU();
        gl.glShadeModel(GL2.GL_SMOOTH);              // Enable Smooth Shading
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);    // Black Background
        gl.glClearDepth(1.0f);                      // Depth Buffer Setup
        gl.glEnable(GL2.GL_DEPTH_TEST);                          // Enables Depth Testing
        gl.glDepthFunc(GL2.GL_LEQUAL);                               // The Type Of Depth Testing To Do
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_FASTEST);    // 真正精细的透视修正


        g_eye[0] = MAP;   //
        g_eye[2] = -MAP;  //
        g_Angle = 0;      //方位角
        g_elev = 0;       //俯仰角


        g_text = glu.gluNewQuadric();

        filename = new String[]{
                "demos/data/images/cc.bmp",
                "demos/data/images/bb.bmp",
                "demos/data/images/meinv.bmp"
        };

        textureArr = new int[filename.length+1];

        //loadT8(filename, textureArr);


    }

    boolean DisplayScene() {

        if (g_elev < -100) {
            g_elev = -100;  //仰俯角
        }
        if (g_elev > 100) {
            g_elev = 100;   //仰俯角
        }
        rad_xz = 3.13149f * (float) g_Angle / 180.0f;

        if (g_eye[0] < -(MAP * 2 - 20)) {
            g_eye[0] = -(MAP * 2 - 20); //视点的X分量限制
        }
        if (g_eye[0] > (MAP * 2 - 20)) {
            g_eye[0] = (MAP * 2 - 20);
        }
        if (g_eye[2] < -(MAP * 2 - 20)) {
            g_eye[2] = -(MAP * 2 - 20); //视点的Z分量限制
        }
        if (g_eye[2] > (MAP * 2 - 20)) {
            g_eye[2] = (MAP * 2 - 20);
        }
        g_eye[1] = 1.8;//设置摄像机对地位置高


        //摄像机的方向
        double rad_xz_cos = (double) Math.cos(rad_xz);
        double rad_xz_sin = (double) Math.sin(rad_xz);
        g_look[0] = g_eye[0] + 100 * (double) Math.cos(rad_xz);
        g_look[2] = g_eye[2] + 100 * (double) Math.sin(rad_xz);
        g_look[1] = g_eye[1];
        //建立modelview矩阵方向
        glu.gluLookAt(g_eye[0], g_eye[1], g_eye[2],
                g_look[0], g_look[1] + g_elev, g_look[2],
                0.0, 1.0, 0.0);


        return true;
    }

    void DrawGround() {
        gl.glPushAttrib(GL2.GL_CURRENT_BIT);//保存现有颜色属实性
        gl.glEnable(GL2.GL_BLEND);//使用纹理
        gl.glPushMatrix();
        gl.glColor3f(0.5f, 0.7f, 1.0f);//设置蓝色
        gl.glTranslatef(0.0f, 0.0f, 0.0f);      //平台的定位
        int size0 = (int) (MAP * 2);
        gl.glLineWidth(1.0f);

        gl.glBegin(GL2.GL_LINES);//划一界线
        for (int x = -size0; x < size0; x += 4) {
            gl.glVertex3i(x, 0, -size0);
            gl.glVertex3i(x, 0, size0);
        }
        for (int z = -size0; z < size0; z += 4) {
            gl.glVertex3i(-size0, 0, z);
            gl.glVertex3i(size0, 0, z);
        }
        gl.glEnd();
        gl.glPopMatrix();
        gl.glDisable(GL2.GL_BLEND);
        gl.glPopAttrib();//恢复前一属性
    }

    void airplane(float x, float y, float z)//组合飞机
    {
        glu = new GLU();
        gl.glPushMatrix();//压入堆栈
        gl.glTranslatef(x, y, z);//飞机的定位
        gl.glRotatef(-r2, 0.0f, 1.0f, 0.0f);//飞机的旋转
        gl.glTranslatef(30.0f, 0.0f, 0.0f);      //飞机的旋转半径
        gl.glRotatef(30.0f, 0.0f, 0.0f, 1.0f);//飞机的倾斜
        //=============================================
        gl.glPushMatrix();//
        gl.glRotatef(-r2 * 30.0f, 0.0f, 0.0f, 1.0f);//飞机的旋转
        gl.glColor3f(0.0f, 0.0f, 1.0f);
        this.box(1.0f, 0.1f, 0.02f);        //方，螺旋浆
        gl.glPopMatrix();
        gl.glColor3f(1.0f, 1.0f, 1.0f);     //白色
        gl.glEnable(GL2.GL_TEXTURE_2D);      //使用纹理
        gl.glBindTexture(GL2.GL_TEXTURE_2D, textureArr[1]);//
        gl.glTranslatef(0.0f, 0.0f, -0.5f); //后移
        glu.gluSphere(g_text, 0.4f, 8, 8);  //机头园(半径)
        //=============================================
        gl.glTranslatef(0.0f, -0.0f, -2);   //位置调整,与机头对接
        glu.gluCylinder(g_text, 0.4, 0.4, 2.0, 8, 4);//机身,园柱(半径、高)
        //=====================================================
        gl.glRotatef(-180.0f, 1.0f, 0.0f, 0.0f); //角度调整
        gl.glTranslatef(0.0f, -0.0f, 0.0f); //位置调整,缩进一点
        glu.gluCylinder(g_text, 0.4, 0.1, 1.5, 8, 4);//机尾,园锥(底半径、高)
        //======================================================
        gl.glBindTexture(GL2.GL_TEXTURE_2D, textureArr[0]);//
        gl.glTranslatef(0.0f, -0.8f, 1.2f); //位置调整
        this.box(1.0f, 0.05f, 0.3f);            //后翼
        gl.glTranslatef(0.0f, 0.1f, 0.0f);  //位置调整
        this.box(0.05f, 0.6f, 0.30f);           //后垂翼
        //======================================================
        gl.glTranslatef(0.0f, 0.7f, -1.9f); //位置调整
        this.box(3.0f, 0.05f, 0.5f);                //前翼
        //======================================================
        gl.glDisable(GL2.GL_TEXTURE_2D);
        gl.glPopMatrix();
        r2 += 0.5f;
        if (r2 > 360) {
            r2 = 0;
        }
    }

    void drawMeinv2() {
        gl.glLoadIdentity();
        float texMatMix[] = {1.0f, 1.0f, 1.0f, 1.0f};
        gl.glMaterialfv(
                GL2.GL_FRONT_AND_BACK,
                GL2.GL_AMBIENT_AND_DIFFUSE,
                texMatMix, 0);
        gl.glEnable(GL2.GL_TEXTURE_2D);

        // select the texture to work with
        gl.glBindTexture(GL2.GL_TEXTURE_2D, textureArr[2]);

        // the object to draw the texture on is a quad
        gl.glBegin(GL2.GL_QUADS);
        //

        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-0.05f, -0.03f, -0.1f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(0.00f, -0.03f, -0.1f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(0.00f, 0.03f, -0.1f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-0.05f, 0.03f, -0.1f);

        gl.glEnd();
        // stop using textures
        gl.glDisable(GL2.GL_TEXTURE_2D);
    }

    void drawMeinv3() {
        gl.glLoadIdentity();
        float texMatMix[] = {1.0f, 1.0f, 1.0f, 1.0f};
        gl.glMaterialfv(
                GL2.GL_FRONT_AND_BACK,
                GL2.GL_AMBIENT_AND_DIFFUSE,
                texMatMix, 0);
        gl.glEnable(GL2.GL_TEXTURE_2D);

        // select the texture to work with
        gl.glBindTexture(GL2.GL_TEXTURE_2D, textureArr[2]);

        // the object to draw the texture on is a quad
        gl.glTranslatef(x, y, -0.1f);
        gl.glBegin(GL2.GL_QUADS);
        //

        gl.glTexCoord2f(0.0f, 0.0f);
        //gl.glVertex3f(-0.05f, -0.03f, -0.1f);
        gl.glVertex2f(0.00f, -0.03f);
        gl.glTexCoord2f(1.0f, 0.0f);
        //gl.glVertex3f(0.00f, -0.03f, -0.1f);
        gl.glVertex2f(0.05f, -0.03f);
        gl.glTexCoord2f(1.0f, 1.0f);
        //gl.glVertex3f(0.00f, 0.03f, -0.1f);
        gl.glVertex2f(0.05f, 0.03f);
        gl.glTexCoord2f(0.0f, 1.0f);
        //gl.glVertex3f(-0.05f, 0.03f, -0.1f);
        gl.glVertex2f(0.00f, 0.03f);

        gl.glEnd();
        // stop using textures
        gl.glDisable(GL2.GL_TEXTURE_2D);
    }

    void drawMeinv() {
        gl.glLoadIdentity();
        float texMatMix[] = {1.0f, 1.0f, 1.0f, 1.0f};
        gl.glMaterialfv(
                GL2.GL_FRONT_AND_BACK,
                GL2.GL_AMBIENT_AND_DIFFUSE,
                texMatMix, 0);
        gl.glEnable(GL2.GL_TEXTURE_2D);

        // select the texture to work with
        gl.glBindTexture(GL2.GL_TEXTURE_2D, textureArr[2]);

        gl.glOrtho(0, 800, 0, 600, 0, 0);
        // the object to draw the texture on is a quad
        gl.glTranslatef(x, y, -0.1f);
        gl.glBegin(GL2.GL_QUADS);
        //

        gl.glTexCoord2f(0.0f, 0.0f);
        //gl.glVertex3f(-0.05f, -0.03f, -0.1f);
        gl.glVertex2f(0.00f, -0.03f);
        gl.glTexCoord2f(1.0f, 0.0f);
        //gl.glVertex3f(0.00f, -0.03f, -0.1f);
        gl.glVertex2f(0.05f, -0.03f);
        gl.glTexCoord2f(1.0f, 1.0f);
        //gl.glVertex3f(0.00f, 0.03f, -0.1f);
        gl.glVertex2f(0.05f, 0.03f);
        gl.glTexCoord2f(0.0f, 1.0f);
        //gl.glVertex3f(-0.05f, 0.03f, -0.1f);
        gl.glVertex2f(0.00f, 0.03f);

        gl.glEnd();
        // stop using textures
        gl.glDisable(GL2.GL_TEXTURE_2D);
    }

    void picter(float x, float y, float z) {
        GLUT glut = new GLUT();

        gl.glPushAttrib(GL2.GL_CURRENT_BIT);//保存现有颜色属实性
        gl.glPushMatrix();//平台==============================
        gl.glTranslatef(x, y + 0.5f, z);        //平台的定位
        gl.glColor3f(0.0f, 1.0f, 0.2f);     //绿色
        glut.glutSolidCube(1.3f);               //方台(高)
        gl.glTranslatef(0.0f, 0.8f, 0.0f);  //架的位置调整,上升0.8
        gl.glColor3f(0.0f, 0.0f, 1.0f);     //蓝色
        glut.glutSolidCube(0.3f);       //长方架(宽、高、长)
        gl.glPopMatrix();

        gl.glPushMatrix();//雷达==============================
        gl.glTranslatef(x, y + 2.1f, z);        //雷达的定位1
        gl.glRotatef(r - 90.0f, 0.0f, 1.0f, 0.0f);  //雷达旋转2
        //=======================================
        gl.glColor3f(1.0f, 1.0f, 1.0f);     //白色
        gl.glRotatef(50.0f, 1.0f, 0.0f, 0.0f);  //盘的角度调整，仰30度
        glut.glutWireCone(1.5,0.6,20,20);           //线园锥盘(底半径、高)
        //=======================================
        gl.glRotatef(180.0f, 1.0f, 0.0f, 0.0f); //杆的角度调整,反方向转
        gl.glTranslatef(0.0f, 0.0f, -0.7f);  //杆的位置调整,缩进一点
        glut.glutWireCone(0.2,2.0,20,20);               //园锥杆(底半径、高)
        gl.glColor3f((float) Math.random(), 0, 0);          //随机红色
        gl.glTranslatef(0.0f, 0.0f, 2.0f);  //杆的位置调整,缩进一点
        glut.glutSolidSphere(0.3, 10, 10);          //园(半径)
        gl.glPopMatrix();

        gl.glPushMatrix();//火箭=============================
        gl.glTranslatef(x, y + 10.0f, z);       //火箭的定位
        gl.glRotatef(r, 0.0f, 1.0f, 0.0f);  //火箭的旋转
        gl.glTranslatef(15.0f, 0.0f, 0.0f);         //火箭的定位
        //=============================================
        gl.glColor3f(1.0f, 0.0f, 0.0f);     //红色
        gl.glRotatef(180.0f, 0.0f, 1.0f, 0.0f); //角度调整,与雷达平行，箭头朝前
        glut.glutSolidCone(0.2f, 0.6f, 1, 1);           //园锥(底半径、高)
        //=============================================
        gl.glColor3f(1.0f, 1.0f, 1.0f);     //白色
        gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);  //角度调整,与火箭头对接
        gl.glTranslatef(0.0f, -1.0f, 0);        //位置调整,与火箭头对接
        glut.glutSolidCylinder(0.2, 1, 1, 1);       //园柱(半径、高)
        gl.glRotatef(-270.0f, 1.0f, 0.0f, 0.0f);
        gl.glColor3f((float) Math.random() + 0.6f, 0.2f, 0.0f); //随机色
        gl.glTranslatef(0.0f, -0.0f, -0.2f); //位置调整,缩进一点
        glut.glutSolidCone(0.2, 1.5, 1, 1);     //园锥(底半径、高)
        gl.glPopMatrix();
        gl.glPopAttrib();//恢复前一属性

    }

    void picter2(float x, float y, float z) {
        GLUT glut = new GLUT();

        gl.glPushAttrib(GL2.GL_CURRENT_BIT);//保存现有颜色属实性

        gl.glPushMatrix();//摆线==============================
        gl.glTranslatef(x, y + 2.1f, z);        //雷达的定位1
        //gl.glTranslatef(0, 1f, 0f);
        gl.glRotatef(r + 40.0f, 0.0f, 1.0f, 0.0f);  //雷达旋转2
        //=======================================
        gl.glColor3f(1.0f, 1.0f, 1.0f);     //白色
        gl.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);  //盘的角度调整，仰30度
        glut.glutWireCone(1f,2f,20,20);
        gl.glTranslatef(1f,0,0);
        glut.glutSolidSphere(0.1,10,10);

        gl.glPopMatrix();
        gl.glPopAttrib();//恢复前一属性
    }

    void light(float x, float y, float z, float a) {
        float light_position[] = {x, y, z, a};
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, light_position, 0);
        float diffuse[] = {1.0f, 1.0f, 1.0f, 1.0f};
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, diffuse, 0);
        float ambient[] = {0.8f, 0.9f, 0.9f, 1.0f};
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, ambient, 0);
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT0);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glEnable(GL2.GL_COLOR_MATERIAL);
    }


    private void box(float x, float y, float z) {
        gl.glPushMatrix();//压入堆栈
        gl.glScalef(x, y, z);
        gl.glEnable(GL2.GL_TEXTURE_2D);      //使用纹理
        gl.glBegin(GL2.GL_QUADS);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);// 前
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, 1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, 1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);// 后
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);// 上
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, 1.0f, 1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);// 下
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, 1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);// 左
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, 1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);// 右
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, 1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glDisable(GL2.GL_TEXTURE_2D);
        gl.glPopMatrix();

    }

    /*private void makeRGBTexture(GL2 gl, GLU glu, TextureReader.Texture img, int target, boolean mipmapped) {
        if (mipmapped) {

            glu.gluBuild2DMipmaps(target, GL2.GL_RGB8, img.getWidth(), img.getHeight(), GL2.GL_RGB, GL2.GL_UNSIGNED_BYTE, img.getPixels());
        } else {


            *//*target为GL_TEXTURE，参数“0”代表图像的详细程度，通常就由它为零去了。参数三是数据的成分数。因为图像是由红色数据，绿色数据，蓝色数据三种组分组成。
            img.getWidth() 是纹理的宽度。如果您知道宽度，您可以在这里填入，但计算机
            可以很容易的为您指出此值。 img.getHeight() 是纹理的高度。参数零是边框的
            值，一般就是“0”。 GL_RGB 告诉OpenGL图像数据由红、绿、蓝三色数据组成。
            GL_UNSIGNED_BYTE 意味着组成图像的数据是无符号字节类型的。最后... img.getPixels()
            告诉OpenGL纹理数据的来源。*//*
            gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
            gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR);
            gl.glTexImage2D(target, 0,3, img.getWidth(), img.getHeight(), 0, GL2.GL_RGB, GL2.GL_UNSIGNED_BYTE, img.getPixels());

        }
    }*/


    /*private boolean loadT8(String[] filenames, int[] textureArr) {

        gl.glEnable(GL2.GL_TEXTURE_2D);

        gl.glGenTextures(filenames.length, textureArr,0);         //纹理数目，数组，类型设置0为png,1为bmg

        for (int i = 0; i < filenames.length; i++) {
            TextureReader.Texture texture1 = null;
            try {

                texture1 = TextureReader.readTexture(filenames[i]);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }

            if (texture1 == null) {
                return false;
            }



            gl.glBindTexture(GL2.GL_TEXTURE_2D, textureArr[i]);


            makeRGBTexture(gl, glu, texture1, GL2.GL_TEXTURE_2D, false);;


        }

        return true;
    }*/
}
