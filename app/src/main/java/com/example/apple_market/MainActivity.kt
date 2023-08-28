package com.example.apple_market

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apple_market.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // 뒤로가기 다이얼로그
    override fun onBackPressed() {
        //다이얼로그 UI
        var builder = AlertDialog.Builder(this)
        builder.setTitle("종료")
        builder.setMessage("정말 종료하시겠습니까?")
        builder.setIcon(R.drawable.chat_image)

        //버튼 클릭 시 작업 내용
        val listener = object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                when(p1) {
                    DialogInterface.BUTTON_NEGATIVE ->
                        return
                    DialogInterface.BUTTON_POSITIVE ->
                        finish()
                }
            }
        }
        builder.setNegativeButton("취소",null)
        builder.setPositiveButton("확인",listener)
        builder.show()
    }

    @SuppressLint("NotificationPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //알림 클릭
        val notification = findViewById<ImageView>(R.id.notification_btn)

        notification.setOnClickListener {

            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            val builder: NotificationCompat.Builder
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // 26 버전 이상
                val channelId = "one-channel"
                val channelName = "My Channel One"
                val channel = NotificationChannel(
                    channelId,
                    channelName,
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply {
                    // 채널에 다양한 정보 설정
                    description = "My Channel One Description"
                    setShowBadge(true)

                     //알림 사운드 추가
//                    val uri: Uri =
//                        RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
//                    val audioAttributes = AudioAttributes.Builder()
//                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
//                        .setUsage(AudioAttributes.USAGE_ALARM)
//                        .build()
//                    setSound(uri, audioAttributes)


//                    enableVibration(true)  //알림 진동 추가
                }
                // 채널을 NotificationManager에 등록
                manager.createNotificationChannel(channel)

                // 채널을 이용하여 builder 생성
                builder = NotificationCompat.Builder(this, channelId)

            } else {
                // 26 버전 이하
                builder = NotificationCompat.Builder(this)
            }

                  // 알림에 사진 넣기
//                val bitmap = BitmapFactory.decodeResource(resources, R.drawable.flower)

                  // 알림 클릭 시 SecondActivity 로 화면이동
//                val intent = Intent(this, SecondActivity::class.java)

//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            val pendingIntent = PendingIntent.getActivity(
//                this,
//                0,
//                intent,
//                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
//            )

            // 알림의 기본 정보
            builder.run {
                setSmallIcon(R.mipmap.ic_launcher)     //알림을 설정할 때 아이콘/텍스트/시간은 필수요소 없을 시 에러
                setWhen(System.currentTimeMillis())
                setContentTitle("키워드 알림.")
                setContentText("설정한 키워드의 알림이 잘 도착했습니다.")

                //알림 상세정보
//                setStyle(
//                    NotificationCompat.BigTextStyle()
//                        .bigText(
//                            "이것은 긴텍스트 샘플입니다. 아주 긴 텍스트를 쓸때는 여기다 하면 됩니다.이것은 긴텍스트 샘플입니다. 아주 긴 텍스트를 쓸때는 여기다 하면 됩니다 . 이것은 긴텍스트 샘플입니다 . 아주 긴 텍스트를 쓸때는 여기다 하면 됩니다 ."
//                        )
//                )
//                setLargeIcon(bitmap)                            //알림 사진 보이기

//            setStyle(NotificationCompat.BigPictureStyle()       //사진 크게 보이기
//                    .bigPicture(bitmap)
//                    .bigLargeIcon(null))  // hide largeIcon while expanding

//                addAction(                      // Action이라는 알림의 버튼을 눌렀을 때 동작하는 문
//                    R.mipmap.ic_launcher,
//                    "Action",
//                    pendingIntent
//                )
            }
            manager.notify(11, builder.build())
        }





        // 데이터 원본 준비
        val dataList = mutableListOf<MyItem>()
        dataList.add(MyItem(R.drawable.sample1, "산지 한달된 선풍기 팝니다", "서울 서대문구 창천동", 1000, 25, 13))
        dataList.add(MyItem(R.drawable.sample2, "김치냉장고", "인천 계양구 귤현동", 20000, 28, 8))
        dataList.add(MyItem(R.drawable.sample3, "샤넬 카드지갑", "수성구 범어동", 10000, 5, 23))
        dataList.add(MyItem(R.drawable.sample4, "금고", "해운대구 우제2동", 10000, 17, 14))
        dataList.add(MyItem(R.drawable.sample5, "갤럭시Z플립3 팝니다", "연제구 연산제8동", 150000, 9, 22))
        dataList.add(MyItem(R.drawable.sample6, "프라다 복조리백", "수원시 영통구 원천동", 50000, 16, 25))
        dataList.add(MyItem(R.drawable.sample7, "울산 동해오션뷰 60평 복층 펜트하우스 1일 숙박권 펜션 힐링 숙소 별장", "남구 옥동", 150000, 54, 142))
        dataList.add(MyItem(R.drawable.sample8, "샤넬 탑핸들 가방", "동래구 온천제2동", 180000, 7, 31))
        dataList.add(MyItem(R.drawable.sample9, "4행정 엔진분무기 판매합니다.", "원주시 명륜2동", 30000, 28, 7))
        dataList.add(MyItem(R.drawable.sample10, "셀린느 버킷 가방", "중구 동화동", 190000, 6, 40))



        binding.recyclerview.adapter = MyAdapter(dataList)

        val adapter = MyAdapter(dataList)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        adapter.itemClick =
            object : MyAdapter.ItemClick {
                override fun onClick(view: View, position: Int) {
//                Toast.makeText(this, "음악게시판으로 이동합니다 ", Toast.LENGTH_SHORT).show()
//                val intent = Intent(this,Topic2::class.java)
//                startActivity(intent)
                }
            }
    }
}