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
    float rad_xz;                            //�Ƕ�    0
    float g_Angle;                           //����ת  0
    float g_elev;                            //������  0
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
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_FASTEST);    // ������ϸ��͸������


        g_eye[0] = MAP;   //
        g_eye[2] = -MAP;  //
        g_Angle = 0;      //��λ��
        g_elev = 0;       //������


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
            g_elev = -100;  //������
        }
        if (g_elev > 100) {
            g_elev = 100;   //������
        }
        rad_xz = 3.13149f * (float) g_Angle / 180.0f;

        /*if (g_eye[0] < -(MAP * 2 - 20)) {
            g_eye[0] = -(MAP * 2 - 20); //�ӵ��X��������
        }
        if (g_eye[0] > (MAP * 2 - 20)) {
            g_eye[0] = (MAP * 2 - 20);
        }
        if (g_eye[2] < -(MAP * 2 - 20)) {
            g_eye[2] = -(MAP * 2 - 20); //�ӵ��Z��������
        }
        if (g_eye[2] > (MAP * 2 - 20)) {
            g_eye[2] = (MAP * 2 - 20);
        }*/
        g_eye[1] = 1.8;//����������Ե�λ�ø�


        //������ķ���
        g_look[0] = g_eye[0] + 100 * (double) Math.cos(rad_xz);
        g_look[2] = g_eye[2] + 100 * (double) Math.sin(rad_xz);
        g_look[1] = g_eye[1];
        //����modelview������

        /*
         * v1Խ�󣬱�ʾ�߶�Խ��
         * */
        glu.gluLookAt(1f, 2.5, -23.5,
                0, (g_look[1]), 0,
                0.0, 1.0, 0.0);


        return true;
    }

    boolean DisplayStereoScene() {

        if (g_elev < -100) {
            g_elev = -100;  //������
        }
        if (g_elev > 100) {
            g_elev = 100;   //������
        }
        rad_xz = 3.13149f * (float) g_Angle / 180.0f;

        /*if (g_eye[0] < -(MAP * 2 - 20)) {
            g_eye[0] = -(MAP * 2 - 20); //�ӵ��X��������
        }
        if (g_eye[0] > (MAP * 2 - 20)) {
            g_eye[0] = (MAP * 2 - 20);
        }
        if (g_eye[2] < -(MAP * 2 - 20)) {
            g_eye[2] = -(MAP * 2 - 20); //�ӵ��Z��������
        }
        if (g_eye[2] > (MAP * 2 - 20)) {
            g_eye[2] = (MAP * 2 - 20);
        }*/
        g_eye[1] = 1.8;//����������Ե�λ�ø�


        //������ķ���
        g_look[0] = g_eye[0] + 100 * (double) Math.cos(rad_xz);
        g_look[2] = g_eye[2] + 100 * (double) Math.sin(rad_xz);
        g_look[1] = g_eye[1];
        //����modelview������

        /*
        * v1Խ�󣬱�ʾ�߶�Խ��
        * */
        glu.gluLookAt(1.0f, 5.5, -22,
                0, (g_look[1] -30), 0,
                0.0, 1.0, 0.0);


        return true;
    }

    boolean DisplayTopScene() {

        if (g_elev < -100) {
            g_elev = -100;  //������
        }
        if (g_elev > 100) {
            g_elev = 100;   //������
        }
        rad_xz = 3.13149f * (float) g_Angle / 180.0f;

        /*if (g_eye[0] < -(MAP * 2 - 20)) {
            g_eye[0] = -(MAP * 2 - 20); //�ӵ��X��������
        }
        if (g_eye[0] > (MAP * 2 - 20)) {
            g_eye[0] = (MAP * 2 - 20);
        }
        if (g_eye[2] < -(MAP * 2 - 20)) {
            g_eye[2] = -(MAP * 2 - 20); //�ӵ��Z��������
        }
        if (g_eye[2] > (MAP * 2 - 20)) {
            g_eye[2] = (MAP * 2 - 20);
        }*/
        g_eye[1] = 1.8;//����������Ե�λ�ø�


        //������ķ���
        g_look[0] = g_eye[0] + 100 * (double) Math.cos(rad_xz);
        g_look[2] = g_eye[2] + 100 * (double) Math.sin(rad_xz);
        g_look[1] = g_eye[1];
        //����modelview������
        /*glu.gluLookAt(g_eye[0], g_eye[1], g_eye[2],
                g_look[0], g_look[1] + g_elev, g_look[2],
                0.0, 0.0, 0.0);*/

        /*
        * g_eye[0]����������
        * g_eye[1]�ǽ����������̧
        * g_eye[2]�ǽ����������
        * */
        glu.gluLookAt(1.0f, 5, -20,//40,2,-40
                0, g_look[1]-90, 0,
                0.0, 1.0, 0.0);

        return true;
    }

    void DrawGround() {
        gl.glPushAttrib(GL2.GL_CURRENT_BIT);//����������ɫ��ʵ��
        gl.glEnable(GL2.GL_BLEND);//ʹ������
        gl.glPushMatrix();
        gl.glColor3f(0.5f, 0.7f, 1.0f);//������ɫ
        gl.glTranslatef(0.0f, 0.0f, 0.0f);      //ƽ̨�Ķ�λ
        int size0 = (int) (MAP * 2);
        gl.glLineWidth(1.0f);

        gl.glBegin(GL2.GL_LINES);//��һ����
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
        gl.glPopAttrib();//�ָ�ǰһ����
    }

    void airplane(float x, float y, float z)//��Ϸɻ�
    {
        glu = new GLU();
        gl.glPushMatrix();//ѹ���ջ
        gl.glTranslatef(x, y, z);//�ɻ��Ķ�λ
        gl.glRotatef(-r2, 0.0f, 1.0f, 0.0f);//�ɻ�����ת
        gl.glTranslatef(30.0f, 0.0f, 0.0f);      //�ɻ�����ת�뾶
        gl.glRotatef(30.0f, 0.0f, 0.0f, 1.0f);//�ɻ�����б
        //=============================================
        gl.glPushMatrix();//
        gl.glRotatef(-r2 * 30.0f, 0.0f, 0.0f, 1.0f);//�ɻ�����ת
        gl.glColor3f(0.0f, 0.0f, 1.0f);
        this.box(1.0f, 0.1f, 0.02f);        //����������
        gl.glPopMatrix();
        gl.glColor3f(1.0f, 1.0f, 1.0f);     //��ɫ
        gl.glEnable(GL2.GL_TEXTURE_2D);      //ʹ������
        gl.glBindTexture(GL2.GL_TEXTURE_2D, textureArr[1]);//
        gl.glTranslatef(0.0f, 0.0f, -0.5f); //����
        glu.gluSphere(g_text, 0.4f, 8, 8);  //��ͷ԰(�뾶)
        //=============================================
        gl.glTranslatef(0.0f, -0.0f, -2);   //λ�õ���,���ͷ�Խ�
        glu.gluCylinder(g_text, 0.4, 0.4, 2.0, 8, 4);//����,԰��(�뾶����)
        //=====================================================
        gl.glRotatef(-180.0f, 1.0f, 0.0f, 0.0f); //�Ƕȵ���
        gl.glTranslatef(0.0f, -0.0f, 0.0f); //λ�õ���,����һ��
        glu.gluCylinder(g_text, 0.4, 0.1, 1.5, 8, 4);//��β,԰׶(�װ뾶����)
        //======================================================
        gl.glBindTexture(GL2.GL_TEXTURE_2D, textureArr[0]);//
        gl.glTranslatef(0.0f, -0.8f, 1.2f); //λ�õ���
        this.box(1.0f, 0.05f, 0.3f);            //����
        gl.glTranslatef(0.0f, 0.1f, 0.0f);  //λ�õ���
        this.box(0.05f, 0.6f, 0.30f);           //����
        //======================================================
        gl.glTranslatef(0.0f, 0.7f, -1.9f); //λ�õ���
        this.box(3.0f, 0.05f, 0.5f);                //ǰ��
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

        gl.glPushAttrib(GL2.GL_CURRENT_BIT);//����������ɫ��ʵ��

        gl.glPushMatrix();//����==============================
        gl.glTranslatef(x, y + 2.1f, z);        //��λ

        //�켣��
        gl.glLineWidth(0.5f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(-1f,0f, -0.1f);
        gl.glVertex3f(1f,0f, -0.1f);
        gl.glEnd();

        gl.glRotatef(r + 40.0f, 0.0f, 1.0f, 0.0f);  //�״���ת2
        //=======================================
        //gl.glColor3f(1.0f, 1.0f, 1.0f);     //��ɫ


        gl.glColor3f(1.0f, 1.0f, 0.0f);
        //����
        gl.glLineWidth(2.5f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0f,2f, 0);
        gl.glVertex3f(1f,0f, 0);
        gl.glEnd();

        gl.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);  //�̵ĽǶȵ�������30��
        //glut.glutWireCone(1f,2f,20,20);
        gl.glTranslatef(1f,0,0);
        //gl.glColor3f(0.0f, 0.0f, 0.0f);
        glut.glutSolidSphere(0.15,12,12);

        gl.glPopMatrix();
        gl.glPopAttrib();//�ָ�ǰһ����
    }

    void picterStereo(float x, float y, float z) {
        GLUT glut = new GLUT();

        gl.glPushAttrib(GL2.GL_CURRENT_BIT);//����������ɫ��ʵ��

        gl.glPushMatrix();//����==============================
        gl.glTranslatef(x, y + 2.1f, z);        //�״�Ķ�λ1
        //gl.glTranslatef(0, 1f, 0f);
        gl.glRotatef(r + 40.0f, 0.0f, 1.0f, 0.0f);  //�״���ת2
        //=======================================
        gl.glColor3f(1.0f, 1.0f, 1.0f);     //��ɫ
        gl.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);  //�̵ĽǶȵ�������30��


        //��Բ׶
        gl.glLineWidth(0.5f);

        gl.glColor3f(0.0f, 0.0f, 0.0f);
        glut.glutWireCone(1f,2f,20,20);


        gl.glColor3f(1.0f, 1.0f, 0.0f);     //��ɫ
        //����
        gl.glLineWidth(2.5f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.0f, 0.0f, 2.0f);
        gl.glVertex3f(1.0f, 0.0f, 0.0f);
        gl.glEnd();

        gl.glTranslatef(1f,0,0);
        glut.glutSolidSphere(0.2,10,10);


        gl.glPopMatrix();
        gl.glPopAttrib();//�ָ�ǰһ����

    }

    void picterTop(float x, float y, float z) {
        GLUT glut = new GLUT();

        gl.glPushAttrib(GL2.GL_CURRENT_BIT);//����������ɫ��ʵ��

        gl.glPushMatrix();//����==============================
        gl.glTranslatef(x, y + 2.1f, z+1.0f);        //��λ


        gl.glRotatef(r + 40.0f, 0.0f, 1.0f, 0.0f);  //�״���ת2
        //=======================================
        gl.glColor3f(1.0f, 1.0f, 1.0f);     //��ɫ
        gl.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);  //�̵ĽǶȵ�������30��


        //�켣
        gl.glBegin(GL2.GL_POLYGON);// ����������������δ�
        //gl.glVertex3f(0, 0, 0.0f);
        for (int i = 0; i <= 720; i += 10) {
            float p = (float) (i * 3.14 / 180);
            gl.glVertex3f((float) Math.sin(p), (float) Math.cos(p), 0.0f);// ԰�켣
        }
        gl.glEnd();


        gl.glColor3f(1.0f, 1.0f, 0.0f);
        //����
        gl.glLineWidth(2.5f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.0f,0.0f, 0);
        gl.glVertex3f(1.0f,0.0f, 0);
        gl.glEnd();

        //glut.glutWireCone(1f,2f,20,20);��Բ׶
        gl.glTranslatef(1f,0,0);
        glut.glutSolidSphere(0.2,10,10);

        gl.glPopMatrix();
        gl.glPopAttrib();//�ָ�ǰһ����

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
        gl.glPushMatrix();//ѹ���ջ
        gl.glScalef(x, y, z);
        gl.glEnable(GL2.GL_TEXTURE_2D);      //ʹ������
        gl.glBegin(GL2.GL_QUADS);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);// ǰ
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, 1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, 1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);// ��
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);// ��
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, 1.0f, 1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);// ��
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, 1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);// ��
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, 1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);// ��
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
