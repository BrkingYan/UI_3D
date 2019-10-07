package NewGUI;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.gl2.GLUT;

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
    float rad_xz;                            //角度    0
    float g_Angle;                           //左右转  0
    float g_elev;                            //仰俯角  0
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

    boolean DisplaySideScene() {

        if (g_elev < -100) {
            g_elev = -100;  //仰俯角
        }
        if (g_elev > 100) {
            g_elev = 100;   //仰俯角
        }
        rad_xz = 3.13149f * (float) g_Angle / 180.0f;

        /*if (g_eye[0] < -(MAP * 2 - 20)) {
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
        }*/
        g_eye[1] = 1.8;//设置摄像机对地位置高


        //摄像机的方向
        g_look[0] = g_eye[0] + 100 * (double) Math.cos(rad_xz);
        g_look[2] = g_eye[2] + 100 * (double) Math.sin(rad_xz);
        g_look[1] = g_eye[1];
        //建立modelview矩阵方向

        /*
         * v1越大，表示高度越高
         * */
        glu.gluLookAt(1f, 2.5, -23.5,
                0, (g_look[1]), 0,
                0.0, 1.0, 0.0);


        return true;
    }

    boolean DisplayStereoScene() {

        if (g_elev < -100) {
            g_elev = -100;  //仰俯角
        }
        if (g_elev > 100) {
            g_elev = 100;   //仰俯角
        }
        rad_xz = 3.13149f * (float) g_Angle / 180.0f;

        /*if (g_eye[0] < -(MAP * 2 - 20)) {
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
        }*/
        g_eye[1] = 1.8;//设置摄像机对地位置高


        //摄像机的方向
        g_look[0] = g_eye[0] + 100 * (double) Math.cos(rad_xz);
        g_look[2] = g_eye[2] + 100 * (double) Math.sin(rad_xz);
        g_look[1] = g_eye[1];
        //建立modelview矩阵方向

        /*
        * v1越大，表示高度越高
        * */
        glu.gluLookAt(1.0f, 5.5, -22,
                0, (g_look[1] -30), 0,
                0.0, 1.0, 0.0);


        return true;
    }

    boolean DisplayTopScene() {

        if (g_elev < -100) {
            g_elev = -100;  //仰俯角
        }
        if (g_elev > 100) {
            g_elev = 100;   //仰俯角
        }
        rad_xz = 3.13149f * (float) g_Angle / 180.0f;

        /*if (g_eye[0] < -(MAP * 2 - 20)) {
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
        }*/
        g_eye[1] = 1.8;//设置摄像机对地位置高


        //摄像机的方向
        g_look[0] = g_eye[0] + 100 * (double) Math.cos(rad_xz);
        g_look[2] = g_eye[2] + 100 * (double) Math.sin(rad_xz);
        g_look[1] = g_eye[1];
        //建立modelview矩阵方向
        /*glu.gluLookAt(g_eye[0], g_eye[1], g_eye[2],
                g_look[0], g_look[1] + g_elev, g_look[2],
                0.0, 0.0, 0.0);*/

        /*
        * g_eye[0]是拉近距离
        * g_eye[1]是将摄像机向上抬
        * g_eye[2]是将摄像机向右
        * */
        glu.gluLookAt(1.0f, 5, -20,//40,2,-40
                0, g_look[1]-90, 0,
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

    void picterSide(float x,float y,float z){
        GLUT glut = new GLUT();

        gl.glPushAttrib(GL2.GL_CURRENT_BIT);//保存现有颜色属实性

        gl.glPushMatrix();//摆线==============================
        gl.glTranslatef(x, y + 2.1f, z);        //定位

        //轨迹线
        gl.glLineWidth(0.5f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(-1f,0f, -0.1f);
        gl.glVertex3f(1f,0f, -0.1f);
        gl.glEnd();

        gl.glRotatef(r + 40.0f, 0.0f, 1.0f, 0.0f);  //雷达旋转2
        //=======================================
        //gl.glColor3f(1.0f, 1.0f, 1.0f);     //白色


        gl.glColor3f(1.0f, 1.0f, 0.0f);
        //绳子
        gl.glLineWidth(2.5f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0f,2f, 0);
        gl.glVertex3f(1f,0f, 0);
        gl.glEnd();

        gl.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);  //盘的角度调整，仰30度
        //glut.glutWireCone(1f,2f,20,20);
        gl.glTranslatef(1f,0,0);
        //gl.glColor3f(0.0f, 0.0f, 0.0f);
        glut.glutSolidSphere(0.15,12,12);

        gl.glPopMatrix();
        gl.glPopAttrib();//恢复前一属性
    }

    void picterStereo(float x, float y, float z) {
        GLUT glut = new GLUT();

        gl.glPushAttrib(GL2.GL_CURRENT_BIT);//保存现有颜色属实性

        gl.glPushMatrix();//摆线==============================
        gl.glTranslatef(x, y + 2.1f, z);        //雷达的定位1
        //gl.glTranslatef(0, 1f, 0f);
        gl.glRotatef(r + 40.0f, 0.0f, 1.0f, 0.0f);  //雷达旋转2
        //=======================================
        gl.glColor3f(1.0f, 1.0f, 1.0f);     //白色
        gl.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);  //盘的角度调整，仰30度


        //主圆锥
        gl.glLineWidth(0.5f);
        glut.glutWireCone(1f,2f,20,20);


        gl.glColor3f(1.0f, 1.0f, 0.0f);     //白色
        //绳子
        gl.glLineWidth(2.5f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.0f, 0.0f, 2.0f);
        gl.glVertex3f(1.0f, 0.0f, 0.0f);
        gl.glEnd();

        gl.glTranslatef(1f,0,0);
        glut.glutSolidSphere(0.2,10,10);


        gl.glPopMatrix();
        gl.glPopAttrib();//恢复前一属性

    }

    void picterTop(float x, float y, float z) {
        GLUT glut = new GLUT();

        gl.glPushAttrib(GL2.GL_CURRENT_BIT);//保存现有颜色属实性

        gl.glPushMatrix();//摆线==============================
        gl.glTranslatef(x, y + 2.1f, z+1.0f);        //定位


        gl.glRotatef(r + 40.0f, 0.0f, 1.0f, 0.0f);  //雷达旋转2
        //=======================================
        gl.glColor3f(1.0f, 1.0f, 1.0f);     //白色
        gl.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);  //盘的角度调整，仰30度


        //轨迹
        gl.glBegin(GL2.GL_POLYGON);// 扇形连续填充三角形串
        //gl.glVertex3f(0, 0, 0.0f);
        for (int i = 0; i <= 720; i += 10) {
            float p = (float) (i * 3.14 / 180);
            gl.glVertex3f((float) Math.sin(p), (float) Math.cos(p), 0.0f);// 园轨迹
        }
        gl.glEnd();


        gl.glColor3f(1.0f, 1.0f, 0.0f);
        //绳子
        gl.glLineWidth(2.5f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.0f,0.0f, 0);
        gl.glVertex3f(1.0f,0.0f, 0);
        gl.glEnd();

        //glut.glutWireCone(1f,2f,20,20);主圆锥
        gl.glTranslatef(1f,0,0);
        glut.glutSolidSphere(0.2,10,10);

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
}
