package com.example.apple_market

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apple_market.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //알림 클릭
        val notification = findViewById<ImageView>(R.id.notification_btn)
        notification.setOnClickListener {


        }


        // 데이터 원본 준비
        val dataList = mutableListOf<MyItem>()
        dataList.add(MyItem(R.drawable.sample1, "산지 한달된 선풍기 팝니다", "서울 서대문구 창천동","1,000원",25,13))
        dataList.add(MyItem(R.drawable.sample2, "김치냉장고", "인천 계양구 귤현동","20,000원",28,8))
        dataList.add(MyItem(R.drawable.sample3, "샤넬 카드지갑", "수성구 범어동","10,000원",5,23))
        dataList.add(MyItem(R.drawable.sample4, "금고", "해운대구 우제2동","10,000원",17,14))
        dataList.add(MyItem(R.drawable.sample5, "갤럭시Z플립3 팝니다", "연제구 연산제8동","150,000원",9,22))
        dataList.add(MyItem(R.drawable.sample6, "프라다 복조리백", "수원시 영통구 원천동","50,000원",16,25))
        dataList.add(MyItem(R.drawable.sample7, "울산 동해오션뷰 60평 복층 펜트하우스 1일 숙박권 펜션 힐링 숙소 별장", "남구 옥동","150,000원",54,142))
        dataList.add(MyItem(R.drawable.sample8, "샤넬 탑핸들 가방", "동래구 온천제2동","180,000원",7,31))
        dataList.add(MyItem(R.drawable.sample9, "4행정 엔진분무기 판매합니다.", "원주시 명륜2동","30,000원",28,7))
        dataList.add(MyItem(R.drawable.sample10, "셀린느 버킷 가방", "중구 동화동","190,000원",6,40))



        binding.recyclerview.adapter = MyAdapter(dataList)

        val adapter = MyAdapter(dataList)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        adapter.itemClick = object : MyAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {

            }
        }
    }
}