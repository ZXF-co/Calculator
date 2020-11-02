package com.example.calculator;



import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.math.*;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //创建Button对象   也就是activity_main.xml里所设置的ID
    Button btn_0,btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9,btn_pt;
    Button btn_mul,btn_div,btn_add,btn_sub;
    Button btn_clr,btn_del,btn_eq,btn_hs;
    Button btn_sin,btn_cos,btn_tan,btn_sqrt,btn_squ;
    EditText et_input;
    boolean clr_flag;    //判断et编辑文本框中是否清空

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例化对象
        setContentView(R.layout.activity_main);
        btn_0= (Button) findViewById(R.id.btn_0);
        btn_1= (Button) findViewById(R.id.btn_1);
        btn_2= (Button) findViewById(R.id.btn_2);
        btn_3= (Button) findViewById(R.id.btn_3);
        btn_4= (Button) findViewById(R.id.btn_4);
        btn_5= (Button) findViewById(R.id.btn_5);
        btn_6= (Button) findViewById(R.id.btn_6);
        btn_7= (Button) findViewById(R.id.btn_7);
        btn_8= (Button) findViewById(R.id.btn_8);
        btn_9= (Button) findViewById(R.id.btn_9);
        btn_pt= (Button) findViewById(R.id.btn_pt);
        btn_add= (Button) findViewById(R.id.btn_add);
        btn_sub= (Button) findViewById(R.id.btn_sub);
        btn_mul= (Button) findViewById(R.id.btn_mul);
        btn_div= (Button) findViewById(R.id.btn_div);
        btn_clr= (Button) findViewById(R.id.btn_clr);
        btn_del= (Button) findViewById(R.id.btn_del);
        btn_eq= (Button) findViewById(R.id.btn_eq);
        btn_hs= (Button) findViewById(R.id.btn_hs);
        btn_squ= (Button) findViewById(R.id.btn_squ);
        btn_sin= (Button) findViewById(R.id.btn_sin);
        btn_cos= (Button) findViewById(R.id.btn_cos);
        btn_tan= (Button) findViewById(R.id.btn_tan);
        btn_sqrt= (Button) findViewById(R.id.btn_sqrt);
        et_input= (EditText) findViewById(R.id.et_input);

        //给按钮设置的点击事件
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_pt.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_sub.setOnClickListener(this);
        btn_mul.setOnClickListener(this);
        btn_div.setOnClickListener(this);
        btn_clr.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_hs.setOnClickListener(this);
        btn_squ.setOnClickListener(this);
        btn_eq.setOnClickListener(this);
        btn_sin.setOnClickListener(this);
        btn_cos.setOnClickListener(this);
        btn_tan.setOnClickListener(this);
        btn_sqrt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String str = et_input.getText().toString();
        switch (v.getId()){
            case   R.id.btn_0:
            case   R.id.btn_1:
            case   R.id.btn_2:
            case   R.id.btn_3:
            case   R.id.btn_4:
            case   R.id.btn_5:
            case   R.id.btn_6:
            case   R.id.btn_7:
            case   R.id.btn_8:
            case   R.id.btn_9:
            case   R.id.btn_pt://小数点
                if(clr_flag){
                    clr_flag=false;
                    str="";
                    et_input.setText("");
                }
                et_input.setText(str+((Button)v).getText());
                break;
            case R.id.btn_add:
            case R.id.btn_sub:
            case R.id.btn_mul:
            case R.id.btn_div:
                if(clr_flag){
                    clr_flag=false;
                    str="";
                    et_input.setText("");
                }
                if(str.contains("+")||str.contains("-")||str.contains("×")||str.contains("÷")) {
                    str=str.substring(0,str.indexOf(" "));
                }
                et_input.setText(str+" "+((Button)v).getText()+" ");
                break;
            case R.id.btn_sin:
            case R.id.btn_cos:
            case R.id.btn_tan:
            case R.id.btn_sqrt:
            case R.id.btn_squ:
                if(clr_flag){
                    clr_flag=false;
                    str="";
                    et_input.setText("");
                }
                //判断是否进行三角函数运算
                et_input.setText(((Button)v).getText()+" ");
                break;
            case R.id.btn_clr:
                if(clr_flag)
                    clr_flag=false;
                str="";
                et_input.setText("");
                break;
            case R.id.btn_del: //判断是否为空，然后在进行删除
                if(clr_flag){
                    clr_flag=false;
                    str="";
                    et_input.setText("");
                }
                else if(str!=null&&!str.equals("")){
                    et_input.setText(str.substring(0,str.length()-1));
                }
                break;
            case R.id.btn_eq: //单独运算最后结果
                getResult();//调用下面的方法
                break;
            //计算三角函数值
            case R.id.btn_hs:
                getSjResult();
                break;
        }
    }

    private void getResult() {
        String exp=et_input.getText().toString();
        //没有运算符,不用运算
        if(exp==null||exp.equals("")) return ;
        if(!exp.contains(" ")){
            return ;
        }
        //判断文本框是否清空了
        if(clr_flag){
            clr_flag=false;
            return;
        }
        //清空之后
        clr_flag=true;
        //截取运算符前面的字符串
        String s1=exp.substring(0,exp.indexOf(" "));
        //截取的运算符
        String op=exp.substring(exp.indexOf(" ")+1,exp.indexOf(" ")+2);
        //截取运算符后面的字符串
        String s2=exp.substring(exp.indexOf(" ")+3);
        double cnt=0;
        if(!s1.equals("")&&!s2.equals("")){
            double d1=Double.parseDouble(s1);
            double d2=Double.parseDouble(s2);
            if(op.equals("+")){
                cnt=d1+d2;
            }
            if(op.equals("-")){
                cnt=d1-d2;
            }
            if(op.equals("×")){
                cnt=d1*d2;
            }
            if(op.equals("÷")){
                if(d2==0) {
                    cnt = 0;
                    System.out.println("分母不能为0！");
                }
                else cnt=d1/d2;
            }
            if(!s1.contains(".")&&!s2.contains(".")&&!op.equals("÷")) {
                int res = (int) cnt;
                et_input.setText(res+"");
            }else {
                et_input.setText(cnt+"");}
        }
        //如果s1不是空,s2是空,就执行下一步
        else if(!s1.equals("")&&s2.equals("")) {
            if (op.equals("+")) {
                double d1 = Double.parseDouble(s1);
                cnt = d1;
            }
            if (op.equals("-")) {
                double d1 = Double.parseDouble(s1);
                cnt = d1;
            }
            if (op.equals("×")) {
                cnt = 0;
            }
            if (op.equals("÷")) {
                cnt = 0;
            }
            if(!s1.contains(".")) {
                int res = (int) cnt;
                et_input.setText(res+"");
            }else {
                et_input.setText(cnt+"");}
        }
        //如果s1是空,s2不是空,就执行下一步
        else if(s1.equals("")&&!s2.equals("")){
            double d2=Double.parseDouble(s2);
            if(op.equals("+")){
                cnt=d2;
            }
            if(op.equals("-")){
                cnt=0-d2;
            }
            if(op.equals("×")){
                cnt=0;
            }
            if(op.equals("÷")){
                cnt=0;
            }
            if(!s2.contains(".")) {
                int res = (int) cnt;
                et_input.setText(res+"");
            }else {
                et_input.setText(cnt+"");}
        }
        else {
            et_input.setText("");
        }
    }


    private void getSjResult() {
        String exp = et_input.getText().toString();
        //没有运算符,不用运算
        if(exp==null||exp.equals("")) return ;
        if(!exp.contains(" ")){
            return ;
        }
        //判断文本框是否清空了
        if(clr_flag){
            clr_flag=false;
            return;
        }
        //清空之后
        clr_flag=true;
        //截取运算符前面的字符串
        String op=exp.substring(0,exp.indexOf(" "));
        //截取的运算符
        String s=exp.substring(exp.indexOf(" ")+1);
        double cnt=0;
        double shu = Double.parseDouble(s);
        if(!op.equals("")&&!s.equals("")) {
            if (op.equals("sin")) {
                cnt = Math.sin(Math.PI * shu / 180);
            }
            if (op.equals("cos")) {
                cnt = Math.cos(Math.PI * shu / 180);
            }
            if (op.equals("tan")) {
                cnt = Math.tan(Math.PI * shu / 180);
            }
            if(op.equals("sqrt")){
                cnt = Math.sqrt(shu);
            }
            if(op.equals("squ")){
                cnt = shu * shu;
            }
            et_input.setText(cnt + "");
        }
        else {
            et_input.setText(op + "");
        }
    }
}