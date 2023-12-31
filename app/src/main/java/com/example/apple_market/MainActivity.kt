package com.example.apple_market

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.icu.text.Transliterator.Position
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
                when (p1) {
                    DialogInterface.BUTTON_NEGATIVE ->   // 중앙 버튼
                        return

                    DialogInterface.BUTTON_POSITIVE ->   // 오른쪽 끝 버튼

                        finish()
                }
            }
        }
        builder.setNegativeButton("취소", null)
        builder.setPositiveButton("확인", listener)
        builder.show()
    }

    @SuppressLint("NotificationPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_main)
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
        dataList.add(
            MyItem(
                R.drawable.sample1,
                "산지 한달된 선풍기 팝니다",
                "서울 서대문구 창천동",
                1000,
                25,
                13,
                "대현동",
                "이사가서 필요가 없어졌어요 급하게 내놓습니다"
            )
        )
        dataList.add(
            MyItem(
                R.drawable.sample2,
                "김치냉장고",
                "인천 계양구 귤현동",
                20000,
                28,
                8,
                "안마담",
                "이사로인해 내놔요"
            )
        )
        dataList.add(
            MyItem(
                R.drawable.sample3,
                "샤넬 카드지갑",
                "수성구 범어동",
                10000,
                5,
                23,
                "코코유",
                "고퀄지갑이구요\n사용감이 있어서 싸게 내어둡니다"
            )
        )
        dataList.add(
            MyItem(
                R.drawable.sample4,
                "금고",
                "해운대구 우제2동",
                10000,
                17,
                14,
                "Nicole",
                "금고\n떼서 가져가야함\n대우월드마크센텀\n미국이주관계로 싸게 팝니다"
            )
        )
        dataList.add(
            MyItem(
                R.drawable.sample5,
                "갤럭시Z플립3 팝니다",
                "연제구 연산제8동",
                150000,
                9,
                22,
                "절명",
                "갤럭시 Z플립3 그린 팝니다\n항시 케이스 씌워서 썻고 필름 한장챙겨드립니다\n화면에 살짝 스크래치난거 말고 크게 이상은없습니다!"
            )
        )
        dataList.add(
            MyItem(
                R.drawable.sample6,
                "프라다 복조리백",
                "수원시 영통구 원천동",
                50000,
                16,
                25,
                "미니멀하게",
                "까임 오염없고 상태 깨끗합니다\n정품여부모름"
            )
        )
        dataList.add(
            MyItem(
                R.drawable.sample7,
                "울산 동해오션뷰 60평 복층 펜트하우스 1일 숙박권 펜션 힐링 숙소 별장",
                "남구 옥동",
                150000,
                54,
                142,
                "굿리치",
                "울산 동해바다뷰 60평 복층 펜트하우스 1일 숙박권\n(에어컨이 없기에 낮은 가격으로 변경했으며 8월 초 가장 더운날 다녀가신 분 경우 시원했다고 잘 지내다 가셨습니다)\n1. 인원: 6명 기준입니다. 1인 10,000원 추가요금\n2. 장소: 북구 블루마시티, 32-33층\n3. 취사도구, 침구류, 세면도구, 드라이기 2개, 선풍기 4대 구비\n4. 예약방법: 예약금 50,000원 하시면 저희는 명함을 드리며 입실 오전 잔금 입금하시면 저희는 동.호수를 알려드리며 고객님은 예약자분 신분증 앞면 주민번호 뒷자리 가리시거나 지우시고 문자로 보내주시면 저희는 카드키를 우편함에 놓아 둡니다.\n5. 33층 옥상 야외 테라스 있음, 가스버너 있음\n6. 고기 굽기 가능\n7. 입실 오후 3시, 오전 11시 퇴실, 정리, 정돈 , 밸브 잠금 부탁드립니다.\n8. 층간소음 주의 부탁드립니다.\n9. 방3개, 화장실3개, 비데 3개\n10. 저희 집안이 쓰는 별장입니다."
            )
        )
        dataList.add(
            MyItem(
                R.drawable.sample8,
                "샤넬 탑핸들 가방",
                "동래구 온천제2동",
                180000,
                7,
                31,
                "난쉽",
                "샤넬 트랜디 CC 탑핸들 스몰 램스킨 블랙 금장 플랩백 !\n + \"\n\" + \"색상 : 블랙\n\" + \"사이즈 : 25.5cm * 17.5cm * 8cm\n\" + \"구성 : 본품더스트\n\" + \"\n\" + \"급하게 돈이 필요해서 팝니다 ㅠ ㅠ"
            )
        )
        dataList.add(
            MyItem(
                R.drawable.sample9,
                "4행정 엔진분무기 판매합니다.",
                "원주시 명륜2동",
                30000,
                28,
                7,
                "알뜰한",
                "3년전에 사서 한번 사용하고 그대로 둔 상태입니다. 요즘 사용은 안해봤습니다. 그래서 저렴하게 내 놓습니다. 중고라 반품은 어렵습니다.\n"
            )
        )
        dataList.add(
            MyItem(
                R.drawable.sample10,
                "셀린느 버킷 가방",
                "중구 동화동",
                190000,
                6,
                40,
                "똑태현",
                "22년 신세계 대전 구매입니당\n + \"셀린느 버킷백\n\" + \"구매해서 몇번사용했어요\n\" + \"까짐 스크래치 없습니다.\n\" + \"타지역에서 보내는거라 택배로 진행합니당!\""
            )
        )

        //스크롤 상단 이동
//        val fadeIn = AlphaAnimation(0f, 1f).apply { duration = 500 }    //애니메이션 객체 추가
//        val fadeOut = AlphaAnimation(1f, 0f).apply { duration = 500 }   //애니메이션 객체 추가

        var isTop = true  //최상단임을 알려주는 변수
        val up_pressed = findViewById<ImageView>(R.id.up_pressed)

        binding.recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                //현재 리스트의 최상단을 검사하기 위해 if문을 사용
                if (!binding.recyclerview.canScrollVertically(-1)
                    //canScrollVertically(-1) 이면 최상단일 경우 true 값 리턴
                    && newState == RecyclerView.SCROLL_STATE_IDLE
                ) {// 현재 스크롤되지 않은 상태임을 나타냄 , 이 조건에 추가해주는 이유는 스크롤에 인한 중복 발생을 방지하기 위해서이다.

//                    binding.upPressed.startAnimation(fadeOut)         // 애니메이션 객체 추가
//                    binding.upPressed.visibility = View.GONE          // 애니메이션 객체 추가
                    binding.upPressed.visibility = View.GONE
                    isTop = true
                } else {
                    if (isTop) {
                        //canScrollVertically(1) 최하단일 경우 false값 리턴
//                        binding.upPressed.visibility = View.VISIBLE
//                        binding.upPressed.startAnimation(fadeIn)
                        binding.upPressed.visibility = View.VISIBLE
                        isTop = false

                    }
                }
            }
        })
        //상단 스크롤 클릭 이벤트
        up_pressed.setOnClickListener {
            //smoothScrollToPosition(0)을 통하여 리스트의 0번째 항목으로 이동
            binding.recyclerview.smoothScrollToPosition(0)

        }


        //myadapter의 데이터 리스트 리사이클 뷰에 데이터 뿌려주기
        binding.recyclerview.adapter = MyAdapter(dataList)

        val adapter = MyAdapter(dataList)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        //아이템 클릭 이벤트 설정
        adapter.itemClick = object : MyAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {

                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("ITEM_INDEX", position)
                intent.putExtra("ITEM_OBJECT", dataList[position])
                startActivity(intent)
            }
        }
        //아이템 길게 클릭했을 때 이벤트 설정
        adapter.itemLongClick = object : MyAdapter.ItemLongClick {
            override fun LongCick(view: View, position: Int) {
                //다이얼로그 UI
                var builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("상품삭제")
                builder.setMessage("상품을 정말로 삭제하시겠습니까?")
                builder.setIcon(R.drawable.chat_image)
                //버튼 클릭 시 작업 내용
                builder.setPositiveButton("확인") { dialog, _ ->
                    dataList.removeAt(position)
                    adapter.notifyDataSetChanged()
                }
                builder.setNegativeButton("취소") { dialog, _ ->
                    return@setNegativeButton
                }
                builder.show()

            }

        }
    }
}