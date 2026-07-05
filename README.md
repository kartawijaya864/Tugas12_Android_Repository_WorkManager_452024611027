# Tugas12_Android_Repository_WorkManager_[NIM]

## Identitas

* **Nama:** Achmad Syahiir
* **NIM:** 452024611027
* **Mata Kuliah:** Pemrograman Mobile
* **Topik:** Repository Pattern & WorkManager

---

## Deskripsi Aplikasi

Aplikasi Android ini merupakan implementasi **Repository Pattern** dan **WorkManager** menggunakan **Kotlin** serta **Jetpack Compose**. Data diambil dari REST API menggunakan **Retrofit**, kemudian disimpan ke database lokal menggunakan **Room** sehingga aplikasi dapat menampilkan hasil caching lokal. Proses sinkronisasi data dilakukan secara asynchronous menggunakan **WorkManager** dengan `CoroutineWorker`.

---

## Teknologi yang Digunakan

* Kotlin
* Jetpack Compose
* Room Database
* Retrofit
* Gson
* Repository Pattern
* MVVM Architecture
* WorkManager
* Coroutines

---

## Struktur Project

```text
app
├── database
│   ├── AppDatabase.kt
│   └── PostDao.kt
├── model
│   └── Post.kt
├── network
│   ├── ApiService.kt
│   └── RetrofitInstance.kt
├── repository
│   └── PostRepository.kt
├── ui
│   └── PostViewModel.kt
├── worker
│   └── RefreshWorker.kt
└── MainActivity.kt
```

---

## Implementasi Repository Pattern

Repository Pattern digunakan sebagai penghubung antara **Room Database** dan **Retrofit** sehingga ViewModel tidak berinteraksi langsung dengan kedua sumber data tersebut. Seluruh proses pengambilan data dari jaringan maupun penyimpanan ke database dilakukan oleh Repository sehingga kode menjadi lebih terstruktur, mudah dikembangkan, dan mudah diuji.

---

## Implementasi WorkManager

WorkManager digunakan untuk menjalankan proses sinkronisasi data secara background menggunakan `CoroutineWorker`. Worker mengambil data dari REST API melalui Repository kemudian menyimpannya ke Room Database. Pada implementasi ini WorkManager dijalankan menggunakan `OneTimeWorkRequest` dengan konfigurasi `Constraints`.

Contoh Constraints:

* NetworkType.UNMETERED (Wi-Fi)
* Requires Charging

---


## Keuntungan Repository Pattern

Repository Pattern memberikan pemisahan tanggung jawab antara ViewModel, database lokal, dan sumber data jaringan. Dengan pendekatan ini, ViewModel hanya berkomunikasi dengan Repository tanpa mengetahui asal data yang digunakan. Hal tersebut membuat aplikasi lebih fleksibel karena perubahan pada sumber data tidak memengaruhi lapisan presentasi.

Selain meningkatkan maintainability, Repository Pattern juga mempermudah proses pengujian (testing), memperjelas arsitektur aplikasi, serta memungkinkan implementasi strategi caching lokal menggunakan Room dan sinkronisasi data melalui WorkManager.

---

## Cara Menjalankan Aplikasi

1. Clone repository.
2. Buka project menggunakan Android Studio.
3. Sync Gradle.
4. Jalankan aplikasi pada emulator atau perangkat Android.
5. Pastikan perangkat memiliki koneksi internet agar proses sinkronisasi berjalan.

---

## Repository GitHub

```
https://github.com/kartawijaya864/Tugas12_Android_Repository_WorkManager_452024611027
```
