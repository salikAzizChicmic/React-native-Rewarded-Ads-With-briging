package com.placementportal


import android.annotation.SuppressLint
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback


class AndroidActivity : AppCompatActivity(), OnUserEarnedRewardListener {
    private var rewardedInterstitialAd:RewardedInterstitialAd?=null
    private final var TAG = "AndroidActivity"
    lateinit var txt:TextView
    lateinit var btn: Button
    lateinit var prg: ProgressBar
    var revardPoint:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android)

        txt=findViewById(R.id.txt1)
        btn=findViewById(R.id.button)
        prg=findViewById(R.id.progressBar)
        prg.visibility=View.INVISIBLE

        btn.setOnClickListener {
            btn.visibility= View.INVISIBLE
            prg.visibility= View.VISIBLE
            MobileAds.initialize(this){
                loadAd()
            }
        }
    }
    private fun loadAd() {
        RewardedInterstitialAd.load(this, "ca-app-pub-3940256099942544/5354046379",
            AdRequest.Builder().build(), object : RewardedInterstitialAdLoadCallback() {

                override fun onAdLoaded(ad: RewardedInterstitialAd) {
                    Log.d(TAG, "Ad was loaded.")
                    Toast.makeText(applicationContext,"Ad Loaded",Toast.LENGTH_LONG).show()
                    rewardedInterstitialAd = ad

                    rewardedInterstitialAd?.show(this@AndroidActivity,this@AndroidActivity)
                    btn.visibility= View.VISIBLE
                    prg.visibility=View.INVISIBLE
                }

                override fun onAdFailedToLoad(adError: LoadAdError) {
                    adError?.toString()?.let { Log.d(TAG, it) }
                    rewardedInterstitialAd = null
                    btn.visibility= View.VISIBLE
                    prg.visibility=View.INVISIBLE
                }


            })
    }
    override fun onUserEarnedReward(rewardItem: RewardItem) {
        Toast.makeText(applicationContext,rewardItem.toString(),Toast.LENGTH_LONG).show()
        // TODO: Reward the user!
        revardPoint=revardPoint+5
        txt.setText("Point -: "+revardPoint.toString())
    }
}