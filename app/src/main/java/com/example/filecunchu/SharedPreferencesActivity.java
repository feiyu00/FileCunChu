package com.example.filecunchu;

import android.Manifest;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class SharedPreferencesActivity extends AppCompatActivity {
    private EditText mEtName;
    private Button mBtnSave, mBtnShow;
    private TextView mTvContent;
    private final String mFile = "test.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        mEtName = findViewById(R.id.et_name);
        mBtnSave = findViewById(R.id.btn_save);
        mBtnShow = findViewById(R.id.btn_show);
        mTvContent = findViewById(R.id.tv_content);
        //参数为（名字加模式） MODE_PRIVATE模式
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sava(mEtName.getText().toString().trim());
            }
        });
        mBtnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvContent.setText(read());
            }
        });
    }
/*
//内部存储
    //存储信息
    private void sava(String content) {
        FileOutputStream fileOutputStream = null;
        try {
        //存在data/data/包名/files
            fileOutputStream = openFileOutput(mFile, MODE_PRIVATE);
            fileOutputStream.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //读取信息
    private String read() {
        FileInputStream fis = null;
        StringBuilder sb = null;
        try {
            fis = openFileInput(mFile);
            byte[] buff = new byte[1024];
            sb = new StringBuilder("");
            int len = 0;
            while ((len=fis.read(buff)) > 0) {
                sb.append(new String(buff, 0, len));
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
*/

  /*  *
     *  外部存储
     * @param content
     *  需要加权限在manifests
*/
    private void sava(String content) {
        FileOutputStream fileOutputStream = null;
        try {
            //存在storage/emulated/0/包名/
            File dir = new File(Environment.getExternalStorageDirectory(),"skypan");
            Log.d("File",Environment.getExternalStorageDirectory()+"");
            //如果文件夹不存在
            if (!dir.exists()){
                dir.mkdirs();
            }
            //文件
            File file = new File(dir,mFile);
            if (!file.exists()){
                file.createNewFile();

            }
            //追加,不加true为覆盖
            fileOutputStream = new FileOutputStream(file,true);
            fileOutputStream.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //读取信息
    private String read() {
        FileInputStream fileInputStream = null;
        try {
//          fileInputStream = openFileInput(mFile);
//          File.separator 代表斜杠
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"skypan",mFile);
            fileInputStream = new FileInputStream(file);
            byte[] buff = new byte[1024];
            StringBuilder sb = new StringBuilder("");
            int len = 0;
            //循环读取文件内容，输入流中将最多buf.length个字节的数据读入一个buf数组中,返回类型是读取到的字节数。
            //当文件读取到结尾时返回 -1,循环结束。
            while ((len=fileInputStream.read(buff)) > 0) {
                sb.append(new String(buff, 0, len));
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
   /* public void sa(){
        String str = "";
        FileOutputStream outputStream = null;
        BufferedWriter bufferedWriter = null;
        try {
            outputStream = openFileOutput("text2", MODE_PRIVATE);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedWriter.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/
}
