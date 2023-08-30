package com.example.apple_market

import android.content.Intent
import android.graphics.drawable.Drawable.ConstantState
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Constants
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import com.example.apple_market.databinding.ActivityDetailBinding
import java.text.DecimalFormat

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val item: MyItem? by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("ITEM_OBJECT", MyItem::class.java)
        }else {
            intent.getParcelableExtra<MyItem>("ITEM_OBJECT")
        }
    }

    private val itemPosition: Int by lazy {
        intent.getIntExtra("ITEM_INDEX",0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //뒤로가기
        val back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            finish()
        }

        binding.detailImage.setImageDrawable(item?.let {
            ResourcesCompat.getDrawable(
                resources,
                it.aIcon,
                null
            )
        })
        // 1,000 콤마 처리
        val comma = DecimalFormat("#,###")
        binding.titleDetail.text = item?.aTitle
        binding.detailAddress.text = item?.aAddress
        binding.priceDetail.text = comma.format(item?.aPrice) +"원"
        binding.detailName.text = item?.aName
        binding.explanationDetail.text = item?.aDetail




    }
}