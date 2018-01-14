package orgs.androidtown.viewpager;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager 사용하기
 */

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        // 가. 가상 데이터로드
        List<String> data = new ArrayList<>();
        for(int i=0; i<100; i++){
            data.add("Temp Data="+i);
        }
        //나. 아답터 생성 - viewPager는 생성과 함꼐 데이터를 넘긴다.
        CustomAdapter adapter= new CustomAdapter(this, data);
        //다. 아답터 연결
        viewPager.setAdapter(adapter);
        // ** 생성후에 데이터를 세팅할 경우
        // adapter.notifyDataSetChanged(); 를 꼭 호출해야 한다.

    }
}

class CustomAdapter extends PagerAdapter {
    Context context;
    List<String> data;

    public CustomAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }
    // getView와 같은.....
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // 1. 여기서 레이아웃파일을 inflate해서 view로 만든다.
       View view =  LayoutInflater.from(context).inflate(R.layout.item_viewpager, null);//null은 뷰그룹

        //2. 데이터를 화면에 세팅
        String value = data.get(position);
        TextView textView = view.findViewById(R.id.textView);
        textView.setText(value);
        //3. 뷰 그룹에 만들어진 view를 add한다.
        container.addView(view);

        return view;
    }
    // instantiateItem 에서 리턴된 object가 View가 맞는지 확인
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    // 현재 사용하지 않는 View는 제거, 없어도 자동으로 제거해주나, 뷰 안에 있는 내용물들 예를 들어 비트맵 같은 것은 제거가 불가능
    // container : 뷰페이저
    // object : 뷰 아이템 (뷰페이저 안에 있는)

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
//        super.destroyItem(container, position, object);
    }
}


