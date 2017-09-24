package com.itheima.xutils3demo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.commons.io.IOUtils;
import org.xutils.HttpManager;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.http.body.StringBody;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {


    @ViewInject(R.id.tv_id)
    private TextView tv_id;

    @ViewInject(R.id.btn_id)
    private Button btn_id;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            tv_id.setText(msg.obj.toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        tv_id.setText("chhanghxshafa");
        ImageView imageView = (ImageView) findViewById(R.id.iv_id);
        x.image().bind(imageView,"http://pic4.nipic.com/20091212/1275046_212219786758_2.jpg");
    }

    @Event(type= View.OnClickListener.class,value = R.id.btn_id)
    private void clickBtn(View v) {


       x.task().run(new Runnable() {
           @Override
           public void run() {
               try {
                   URL url = new URL("http://192.168.128.1/lastVersionInfo");
                   HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                   httpURLConnection.setReadTimeout(2000);
                   httpURLConnection.setReadTimeout(2000);
                   httpURLConnection.setRequestMethod("GET");
                   if (httpURLConnection.getResponseCode()==200) {
                       InputStream inputStream = httpURLConnection.getInputStream();
                       String result = IOUtils.toString(inputStream, "UTF-8");
                      // result = "dasssssssssssssssdsaaaadsaasdasdssss";
                       Message message = mHandler.obtainMessage(1);
                       message.obj = result;
                       mHandler.sendMessage(message);
//                      tv_id.setText(result);

//                       ddsad
                   }
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       });


       // tv_id.setText("dsadasdfqfq");
        /*new Thread(){
            @Override
            public void run() {
                try {
                    URL url = new URL("http://192.168.128.1/lastVersionInfo");
                  HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setReadTimeout(2000);
                    httpURLConnection.setReadTimeout(2000);
                    httpURLConnection.setRequestMethod("GET");
                    if (httpURLConnection.getResponseCode()==200) {
                        InputStream inputStream = httpURLConnection.getInputStream();
                        String result = IOUtils.toString(inputStream, "UTF-8");
                        Message message = mHandler.obtainMessage(1);
                        message.obj = result;
                        mHandler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();*/
    }
}
