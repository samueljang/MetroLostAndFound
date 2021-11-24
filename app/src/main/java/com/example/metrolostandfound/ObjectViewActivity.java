package com.example.metrolostandfound;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ObjectViewActivity extends AppCompatActivity {
    private LostObject printObject;

    TextView test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_view);

        test = (TextView) findViewById(R.id.test_text);


    }

    private void loadItem() throws ParseException {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);
        String mc = intent.getStringExtra("mc");
        String sc = intent.getStringExtra("sc");
        String Station = intent.getStringExtra("Station");
        String date = intent.getStringExtra("date");
        String date2 = intent.getStringExtra("date2");
        String time = intent.getStringExtra("time");
        String time2 = intent.getStringExtra("time2");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/ dd ");

        List<LostObject> list = new ArrayList<LostObject>();
        List<LostObject> sublist = new ArrayList<LostObject>();
        if (id != -1) {
            if (mc != null) {
                if (sc != null) {
                    //new DBLoadCall(mc,sc);
                    list = DBController.getItems(mc, sc);
                } else {
                    list = DBController.getItems(mc);
                }
            } else {
                list = DBController.getItems();
            }
        }//대분류와 소분류가 입력되었을때 안돼었을때 나눠서 현제 있는 아이템들을 리스트에 삽입
        if (Station != null) {
            for (int i = 0; i < list.size(); i++) {
                //if (list.l) //여기서 역위치 일치하는 아이템만 따로 뷴류해야하는데 Station에 접근이 안됌;;;

            }
        }
    }
    private void printItem(){
        test.setText(printObject.toString());
    }//printObject에 저장이 완료되면 TextView 같은 곳에 오브젝트 내용을 쓴다.
    //그니까 printObject 쓰는 건 여기서 다 해야한다 다른 곳에서는 에러 뜰 수 있다
    private class DBLoadCall extends AsyncTask<Integer, String, String> {

        @Override
        protected String doInBackground(Integer[] params) {
            printObject = DBController.getItem(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            printItem();
        }
    } //동기 처리

public int Compare_Date(String Date1, String Date2) throws ParseException {
        Date Day1 = null;
        Date Day2 = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/ DD ");
        Day1 = sdf.parse(Date1.substring(0,12));
        Day2 = sdf.parse(Date2.substring(0,12));
        if(Day1.compareTo(Day2)>0){
            return 1; //1=이후
        }else if(Day1.compareTo(Day2)<0) {
            return 0; //1=이후
        }else{
            return 1;
    }

}
}
