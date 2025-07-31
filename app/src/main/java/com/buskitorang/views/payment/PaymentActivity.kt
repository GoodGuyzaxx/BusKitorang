@file:Suppress("DEPRECATION")

package com.buskitorang.views.payment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.buskitorang.R
import com.buskitorang.databinding.ActivityPaymentBinding
import com.buskitorang.utils.ConvertUtils
import com.midtrans.sdk.uikit.api.model.TransactionResult
import com.midtrans.sdk.uikit.external.UiKitApi
import com.midtrans.sdk.uikit.internal.util.UiKitConstants
import com.midtrans.sdk.uikit.internal.util.UiKitConstants.STATUS_CANCELED
import com.midtrans.sdk.uikit.internal.util.UiKitConstants.STATUS_FAILED
import com.midtrans.sdk.uikit.internal.util.UiKitConstants.STATUS_INVALID
import com.midtrans.sdk.uikit.internal.util.UiKitConstants.STATUS_PENDING
import com.midtrans.sdk.uikit.internal.util.UiKitConstants.STATUS_SUCCESS
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PaymentActivity : AppCompatActivity() {

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result?.resultCode == RESULT_OK) {
                result.data?.let {
                    val transactionResult = it.getParcelableExtra<TransactionResult>(UiKitConstants.KEY_TRANSACTION_RESULT)
                    Toast.makeText(this, "${transactionResult?.transactionId}", Toast.LENGTH_LONG).show()
                }
            }
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {
            val transactionResult = data?.getParcelableExtra<TransactionResult>(UiKitConstants.KEY_TRANSACTION_RESULT)
            if (transactionResult != null) {
                when (transactionResult.status) {
                    STATUS_SUCCESS -> {
                        Toast.makeText(this, "Transaction Finished. ID: " + transactionResult.transactionId, Toast.LENGTH_LONG).show()
                    }
                    STATUS_PENDING -> {
                        Toast.makeText(this, "Transaction Pending. ID: " + transactionResult.transactionId, Toast.LENGTH_LONG).show()
                    }
                    STATUS_FAILED -> {
                        Toast.makeText(this, "Transaction Failed. ID: " + transactionResult.transactionId, Toast.LENGTH_LONG).show()
                    }
                    STATUS_CANCELED -> {
                        Toast.makeText(this, "Transaction Cancelled", Toast.LENGTH_LONG).show()
                    }
                    STATUS_INVALID -> {
                        Toast.makeText(this, "Transaction Invalid. ID: " + transactionResult.transactionId, Toast.LENGTH_LONG).show()
                    }
                    else -> {
                        Toast.makeText(this, "Transaction ID: " + transactionResult.transactionId + ". Message: " + transactionResult.status, Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(this, "Transaction Invalid", Toast.LENGTH_LONG).show()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private lateinit var binding: ActivityPaymentBinding
    private val TAG = "PaymentActivity"

    private val viewModel: PaymentViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        val idPayment = intent.getIntExtra("ID_VALUE", 0)
        viewModel.getUserPayment(idPayment)

        setUpData()
        viewModel.paymentResponse.observe(this){
            Toast.makeText(this, "value : ${it.first().ticketId}", Toast.LENGTH_LONG).show()
        }

        try {
            buildUiKit()
            setupPaymentButton()
        } catch (e: Exception) {
            Log.e(TAG, "Error initializing payment: ${e.message}", e)
            Toast.makeText(this, "Error initializing payment system", Toast.LENGTH_LONG).show()
        }
    }

    private fun setUpData(){
        val getWaktuTIba = intent.getStringExtra("WAKTU_TIBA") ?: ""
        val getWaktuBerangkat = intent.getStringExtra("WAKTU_BERANGKAT") ?: ""
        val getRuteAwal = intent.getStringExtra("RUTE_AWAL") ?: ""
        val getRuteTiba = intent.getStringExtra("RUTE_TIBA") ?: ""
        viewModel.paymentResponse.observe(this){ response ->
            response.first().apply {
                val arrivalTime = ConvertUtils().convertToHourMinuteLocalDateTime(getWaktuTIba)
                val departureTime = ConvertUtils().convertToHourMinuteLocalDateTime(getWaktuBerangkat)
                binding.apply {
                    idTiket.text = orderId
                    tvSeatNumber.text = ticket.seatNumber.toString()
                    tvDate.text = ConvertUtils().convertToDayMonthYear(ticket.createdAt)
                    tvPassengerName.text = ticket.passenger.name
                    tvTotalAmount.text = ConvertUtils().formatToRupiah(amount.toDouble())
                    tvRoute.text = getString(R.string.tempat_holder, getRuteAwal, getRuteTiba)
                    tvTime.text = getString(R.string.time_holder, departureTime, arrivalTime)

                }
            }

        }
    }

    private fun setupPaymentButton() {
        viewModel.paymentResponse.observe(this){response ->
            val snapToken = response.first().paymentUrl
            binding.btnBayar.setOnClickListener {
                try {
                    UiKitApi.getDefaultInstance().startPaymentUiFlow(
                        activity = this@PaymentActivity,
                        launcher = launcher,
                        snapToken = snapToken
                    )
                } catch (e: Exception) {
                    Log.e(TAG, "Error starting payment flow: ${e.message}", e)
                    Toast.makeText(this, "Error starting payment: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun buildUiKit() {
        UiKitApi.Builder()
            .withContext(this.applicationContext)
            .withMerchantUrl("https://terrapin-relaxing-seemingly.ngrok-free.app/api/")
            .withMerchantClientKey("SB-Mid-client-vTnJxarV5uMTc9hy")
            .enableLog(true)
//            .withColorTheme(CustomColorTheme("#FFE51255", "#B61548", "#FFE51255"))
            .build()
        uiKitCustomSetting()
    }

    private fun uiKitCustomSetting() {
        val uIKitCustomSetting = UiKitApi.getDefaultInstance().uiKitSetting
        uIKitCustomSetting.saveCardChecked = true
    }




}