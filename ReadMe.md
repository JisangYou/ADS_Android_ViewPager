# ADS04 Android

## 수업 내용

- ViewPager 기본적인 문법 및 사용법 학습

## Code Review

### Main.xml
``` XML
<android.support.v4.view.ViewPager
        android:id="@+id/viewPager"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:layout_marginBottom="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginRight="8dp"
        android:layout_marginTop="8dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        android:layout_marginStart="8dp"
        android:layout_marginEnd="8dp">

    </android.support.v4.view.ViewPager>
```

### item_viewpager
```XML
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/textView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="TextView"
        android:textAlignment="center"
        android:textAppearance="@style/TextAppearance.AppCompat.Large" />
</LinearLayout>
```

### MainActivity

```Java
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
```


## 보충설명

### ViewPager이란?
- Support Library에 포함
- AdapterView의 일종
- ViewPager 표시하는 View는 PagerAdapter를 통해 공급 받습니다. PagerAdapter를 통해 화면에 표시 될 View의 라이프 사이클을 관리 할 수 있음.
- 양쪽의 View를 생성하거나 유지 시키고 나머지 포지션은 삭제 하는 형태 


### PagerAdapter API

getCount() : 현재 PagerAdapter에서 관리할 갯수를 반환 한다. 
instantiateItem() : ViewPager에서 사용할 뷰객체 생성 및 등록 한다.  
destroyItem() : View 객체를 삭제 한다.
isViewFromObject() : instantiateItem메소드에서 생성한 객체를 이용할 것인지 여부를 반환 한다. 
restoreState() : saveState() 상태에서 저장했던 Adapter와 page를 복구 한다. 
saveState()  : 현재 UI 상태를 저장하기 위해 Adapter와 Page 관련 인스턴스 상태를 저장 합니다. 
startUpdate() : 페이지 변경이 시작될때 호출 됩니다.
finishUpdate() : 페이지 변경이 완료되었을때 호출 됩니다.



#### 참고 

>> API(Application Programming Interface, 응용 프로그램 프로그래밍 인터페이스)는 응용 프로그램에서 사용할 수 있도록, 운영 체제나 프로그래밍 언어가 제공하는 기능을 제어할 수 있게 만든 인터페이스를 뜻한다.

### 출처

- 출처: http://arabiannight.tistory.com/entry/안드로이드Andorid-Viewpager-사용-하기 [아라비안나이트]

## TODO

- AdapterView의 다른 종류 찾아보기
- API 읽어보기

## Retrospect

- 이런 식으로 화면을 구성할 수 있다는 것은 알았지만, 이런 구조로 이루어 져 있다는 것을 처음 알게 되었다.

## Output

-  참고[http://arabiannight.tistory.com](http://arabiannight.tistory.com/entry/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9CAndorid-Viewpager-%EC%82%AC%EC%9A%A9-%ED%95%98%EA%B8%B0)
- ![ViewPager](http://cfile1.uf.tistory.com/image/130635474F5758B8118FF7)
