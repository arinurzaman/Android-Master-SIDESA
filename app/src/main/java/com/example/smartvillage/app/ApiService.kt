package com.example.smartvillage.app

import com.example.smartvillage.model.ResponModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("nik") nik: String,
        @Field("password") password: String,
        @Field("fcm") fcm: String
    ): Call<ResponModel>


    @FormUrlEncoded
    @POST("domisili")
    fun domisili(
        @Field("nik") nik: String,
        @Field("nama") nama: String,
        @Field("jenis_kelamin") jenis_kelamin: String,
        @Field("tempat_lahir") tempat_lahir: String,
        @Field("tanggal_lahir") tanggal_lahir: String,
        @Field("kewarganegaraan") kewarganegaraan: String,
        @Field("agama") agama: String,
        @Field("pekerjaan") pekerjaan: String,
        @Field("alamat") alamat: String
    ): Call<ResponModel>

    @FormUrlEncoded
    @POST("kematian")
    fun kematian(
        @Field("nama_pelapor") nama_pelapor: String,
        @Field("umur_pelapor") umur_pelapor: String,
        @Field("pekerjaan_pelapor") pekerjaan_pelapor: String,
        @Field("alamat_pelapor") alamat_pelapor: String,
        @Field("agama_pelapor") agama_pelapor: String,
        @Field("nik_pelapor") nik_pelapor: String,
        @Field("nama_jenazah") nama_jenazah: String,
        @Field("jk_jenazah") jk_jenazah: String,
        @Field("tempat_lahir_jenazah") tempat_lahir_jenazah: String,
        @Field("tanggal_lahir_jenazah") tanggal_lahir_jenazah: String,
        @Field("agama_jenazah") agama_jenazah: String,
        @Field("ayah_jenazah") ayah_jenazah: String,
        @Field("ibu_jenazah") ibu_jenazah: String,
        @Field("alamat_jenazah") alamat_jenazah: String,
        @Field("hari_meninggal") hari_meninggal: String,
        @Field("tanggal_meninggal") tanggal_meninggal: String,
        @Field("tempat_meninggal") tempat_meninggal: String
    ): Call<ResponModel>

    @FormUrlEncoded
    @POST("sku")
    fun sku(
        @Field("nik") nik: String,
        @Field("nama") nama: String,
        @Field("umur") umur: String,
        @Field("alamat") alamat: String,
        @Field("no_hp") no_hp: String,
        @Field("nama_usaha") nama_usaha: String
    ): Call<ResponModel>

    @FormUrlEncoded
    @POST("kelahiran")
    fun kelahiran(
        @Field("nama_ibu") nama_ibu: String,
        @Field("umur_ibu") umur_ibu: String,
        @Field("pekerjaan_ibu") pekerjaan_ibu: String,
        @Field("nama_suami") nama_suami: String,
        @Field("umur_suami") umur_suami: String,
        @Field("pekerjaan_suami") pekerjaan_suami: String,
        @Field("alamat_suami") alamat_suami: String,
        @Field("tgl_lahir_anak") tgl_lahir_anak: String,
        @Field("jam_lahir") jam_lahir: String,
        @Field("nama_anak") nama_anak: String,
        @Field("jk_anak") jk_anak: String,
        @Field("bb_anak") bb_anak: String,
        @Field("pb_anak") pb_anak: String,
        @Field("tempat_lahir") tempat_lahir: String,
        @Field("anak_ke") anak_ke: String,
        @Field("agama") agama: String
    ): Call<ResponModel>

    @FormUrlEncoded
    @POST("blmnikah")
    fun blmnikah(
        @Field("nik") nik: String,
        @Field("nama_lengkap") nama: String,
        @Field("tempat_lahir") tempat_lahir: String,
        @Field("tanggal_lahir") tanggal_lahir: String,
        @Field("jenis_kelamin") jenis_kelamin: String,
        @Field("agama") agama: String,
        @Field("pekerjaan") pekerjaan: String,
        @Field("alamat") alamat: String
    ): Call<ResponModel>

    @FormUrlEncoded
    @POST("keramaian")
    fun keramaian(
        @Field("nik") nik: String,
        @Field("nama_lengkap") nama: String,
        @Field("umur") umur: String,
        @Field("pekerjaan") pekerjaan: String,
        @Field("alamat") alamat: String,
        @Field("tgl_keramaian") tanggal_lahir: String,
        @Field("tempat_keramaian") tempat_lahir: String,
        @Field("kegiatan") jenis_kelamin: String

    ): Call<ResponModel>


}