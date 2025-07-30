package com.buskitorang.views.payment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.buskitorang.R

class SnapPaymentWebActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snap_payment_web)

        webView = WebView(this)
        setContentView(webView)

        val snapToken = intent.getStringExtra("SNAP_TOKEN") ?: return
        val snapUrl = "https://app.sandbox.midtrans.com/snap/v2/vtweb/$snapToken"

        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            allowContentAccess = true
            allowFileAccess = true
        }


        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                url?.let {
                    when {
                        it.contains("gojek://") || it.contains("shopeepay://") ||
                                it.contains("ovo://") || it.contains("dana://") -> {
                            // Handle deep links for e-wallet apps
                            try {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                                startActivity(intent)
                                return true
                            } catch (e: Exception) {
                                Log.e("WebView", "Cannot open deep link: $it", e)
                            }
                        }
                        it.contains("status_code=200") -> {
                            // Payment success
                            Toast.makeText(this@SnapPaymentWebActivity, "Payment Success", Toast.LENGTH_LONG).show()
                            finish()
                            return true
                        }
                        it.contains("status_code=201") -> {
                            // Payment pending
                            Toast.makeText(this@SnapPaymentWebActivity, "Payment Pending", Toast.LENGTH_LONG).show()
                            finish()
                            return true
                        }
                        it.contains("status_code") -> {
                            // Payment failed or cancelled
                            Toast.makeText(this@SnapPaymentWebActivity, "Payment Failed/Cancelled", Toast.LENGTH_LONG).show()
                            finish()
                            return true
                        }
                    }
                }
                return false
            }
        }

        // Load the Snap payment page
        webView.loadUrl(snapUrl)
    }


}