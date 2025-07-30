package com.buskitorang.utils

import android.annotation.SuppressLint
import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@SuppressLint("NewApi")
class ConvertUtils {

    fun formatToRupiah(amount: Double): String {
        // 1. Dapatkan instance NumberFormat untuk mata uang
        // Gunakan Locale("in", "ID") untuk Indonesia.
        val formatter = NumberFormat.getCurrencyInstance(Locale("in", "ID"))

        // 2. Format jumlah (amount) ke string Rupiah
        // Secara default, NumberFormat akan menambahkan simbol mata uang (Rp)
        // dan mengatur pemisah ribuan/desimal sesuai locale Indonesia.
        return formatter.format(amount)
    }
    fun convertToHourMinuteLocalDateTime(apiDateTimeString: String): String {
        // 1. Definisikan formatter untuk mem-parsing string input dari API
        // 'T' dan 'Z' perlu di-escape jika tidak ada di format string API secara literal.
        // Jika 'Z' menunjukkan UTC dan Anda ingin mengabaikan zona waktu untuk display lokal,
        // Anda bisa mem-parsing sebagai Instant atau menggunakan format lain.
        // Untuk kasus ini, kita asumsikan LocalDateTime sudah cukup dan 'Z' diabaikan
        // jika API string tidak benar-benar termasuk info zona waktu di LocalDateTime
        // Atau jika string API selalu berakhir dengan 'Z' dan Anda ingin mengabaikannya:
        val parser = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'") // Perhatikan 'Z' dan 'T' di-escape

        // 2. Parse string API menjadi objek LocalDateTime
        val dateTime = LocalDateTime.parse(apiDateTimeString, parser)

        // 3. Definisikan formatter untuk output yang diinginkan (Jam:Menit)
        val outputFormatter = DateTimeFormatter.ofPattern("HH:mm")

        // 4. Format objek LocalDateTime ke string Jam:Menit
        return dateTime.format(outputFormatter)
    }

    fun convertToDayMonthYear(date : String): String{
        val parser = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'") // Perhatikan 'Z' dan 'T' di-escape
        val dateTime = LocalDateTime.parse(date, parser)
        val outputFormatter = DateTimeFormatter.ofPattern("dd:MM:yyyy")
        return dateTime.format(outputFormatter)
    }
}