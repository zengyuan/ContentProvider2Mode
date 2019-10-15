package com.saiyi.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private StringBuilder Struser = new StringBuilder();
    private StringBuilder Strjob = new StringBuilder();
    private StringBuilder StrDa = new StringBuilder();

    /***
     * ContentProvider1 中对应数据库的KEY
     */
    public   String USER_CAR_AZ_AF = "car_user_az_af";//字符分段
    public   String USER_CAR_NAME_AF = "car_kfb_name_af";// 车名称
    public   String USER_CAR_TYPE_AF = "car_user_cartype_af";// 车型号
    public   String USER_CAR_DATE_AF = "car_kfb_date_af";// 年份
    public   String USER_CAR_LENGM_AF = "car_kfb_lengmei_af";// 冷媒量

    private TextView text_v;
    public  String AUTOHORITY = "Finn.zy.myprovider";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_v = findViewById(R.id.text_v);

        /**
         * 对user表进行操作
         */
        // 设置URI  和 进程一中UriMatcher中注册的名称相同
        Uri uri_user = Uri.parse("content://"+AUTOHORITY+"/user");
        // 插入表中数据
        ContentValues values = new ContentValues();
        values.put("_id", 4);
        values.put("name", "Jordan");

        // 获取ContentResolver
        ContentResolver resolver =  getContentResolver();
        // 通过ContentResolver 根据URI 向ContentProvider中插入数据
        resolver.insert(uri_user,values);

        // 通过ContentResolver 向ContentProvider中查询数据
        Cursor cursor = resolver.query(uri_user, new String[]{"_id","name"}, null, null, null);
        while (cursor.moveToNext()){
            System.out.println("query book:" + cursor.getInt(0) +" "+ cursor.getString(1));
            Struser.append(cursor.getInt(0) +"===="+ cursor.getString(1)+"\n");
            // 将表中数据全部输出
        }
        cursor.close();
        // 关闭游标


        /**
         * 对job表进行操作  // 设置URI  cn.scu.myprovider和进程一中UriMatcher中注册的名称相同
         */
        // 和上述类似,只是URI需要更改,从而匹配不同的URI CODE,从而找到不同的数据资源
        Uri uri_job = Uri.parse("content://"+AUTOHORITY+"/job");//job ---->  链表的名称
        // 插入表中数据
        ContentValues values2 = new ContentValues();
        values2.put("_id", 4);
        values2.put("job", "NBA Player");

        // 获取ContentResolver
        ContentResolver resolver2 =  getContentResolver();
        // 通过ContentResolver 根据URI 向ContentProvider中插入数据
        resolver2.insert(uri_job,values2);

        // 通过ContentResolver 向ContentProvider中查询数据
        Cursor cursor2 = resolver2.query(uri_job, new String[]{"_id","job"}, null, null, null);
        while (cursor2.moveToNext()){
            System.out.println("query job:" + cursor2.getInt(0) +" "+ cursor2.getString(1));
            Strjob.append(cursor2.getInt(0) +"===="+ cursor2.getString(1)+"\n");
            // 将表中数据全部输出
        }
        cursor2.close();
        // 关闭游标

        // 和上述类似,只是URI需要更改,从而匹配不同的URI CODE,从而找到不同的数据资源
        Uri uri_da = Uri.parse("content://"+AUTOHORITY+"/table_user_af");

        // 插入表中数据
        ContentValues values3 = new ContentValues();
        values3.put(USER_CAR_AZ_AF, "00");
        values3.put(USER_CAR_NAME_AF, "NBA0 2");
        values3.put(USER_CAR_TYPE_AF, "NBA0 3");
        values3.put(USER_CAR_DATE_AF, "NBA0 40");
        values3.put(USER_CAR_LENGM_AF, "NBA0 5");

        // 获取ContentResolver
        ContentResolver resolver3 =  getContentResolver();
        // 通过ContentResolver 根据URI 向ContentProvider中插入数据
        resolver3.insert(uri_da,values3);
        // 通过ContentResolver 向ContentProvider中查询数据
        Cursor cursor3 = resolver3.query(uri_da, new String[]{USER_CAR_AZ_AF,USER_CAR_NAME_AF,USER_CAR_TYPE_AF,
                USER_CAR_DATE_AF,USER_CAR_LENGM_AF}, null, null, null);
        while (cursor3.moveToNext()){
            System.out.println("query job:" + cursor3.getInt(0) +" "+ cursor3.getString(1));
            StrDa.append(cursor3.getInt(0) +"=="+ cursor3.getString(1)+"=="+ cursor3.getString(2)
                    +"=="+ cursor3.getString(3)
                    +"=="+ cursor3.getString(4)
                    +"=="+ cursor3.getString(5)
                    +"=="+ cursor3.getString(6)+"\n");
            // 将表中数据全部输出
        }
        cursor3.close();

        text_v.setText(Struser+"\n"+"\n"+"\n"+Strjob+"\n"+"\n"+"\n"+StrDa);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(Strjob != null){
            Strjob.setLength(0);
            Strjob = null;
        }
        if(Struser != null){
            Struser.setLength(0);
            Struser = null;
        }
        if(StrDa != null){
            StrDa.setLength(0);
            StrDa = null;
        }
    }
}
