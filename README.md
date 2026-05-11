# Restful Booker API Test Automation Project

Bu proje, Restful Booker API servisinin otomasyon testlerini gerçekleştirmek amacıyla geliştirilmiştir.

Projede Java, RestAssured ve TestNG kullanılarak temel API test senaryoları oluşturulmuştur.

---

# Kullanılan Teknolojiler

* Java 17
* Maven
* RestAssured
* TestNG
* Allure Report
* Lombok
* Jackson Databind
* Dotenv

---

# Proje Yapısı

```text
src
 ├── main
 │    ├── java
 │    │     ├── config
 │    │     ├── models
 │    │     └── utils
 │
 └── test
      ├── java
      │     └── com.booker.tests
      │
      └── resources
```

---

# Test Kapsamı

Projede aşağıdaki API endpointleri için otomasyon testleri bulunmaktadır:

* Auth
* Ping / Health Check
* Get Booking
* Create Booking
* Update Booking (PUT)
* Partial Update Booking (PATCH)
* Delete Booking

---

# Projeyi Çalıştırma

## 1. Repository’i clone edin

```bash
git clone <repo-url>
```

---

## 2. Proje dizinine girin

```bash
cd RestAssuredExample
```

---

## 3. Environment variable veya .env dosyası oluşturun

Proje kök dizinine `.env` dosyası ekleyin:

```env
BASE_URL=https://restful-booker.herokuapp.com
ADMIN_USERNAME=admin
ADMIN_PASSWORD=password123
```

---

# Testleri Çalıştırma

Terminal üzerinden aşağıdaki komutu çalıştırın:

```bash
mvn clean test
```

---

# Allure Report Görüntüleme

Testler çalıştıktan sonra aşağıdaki komut ile Allure raporu açılabilir:

```bash
allure serve target/allure-results
```

---

# Notlar

* Testlerde reusable yapı kullanılmıştır.
* Authentication işlemleri BaseTest üzerinden yönetilmektedir.
* Request configuration işlemleri SpecBuilder sınıfı üzerinden gerçekleştirilmektedir.
* Hassas bilgiler .env yapısı ile yönetilmektedir.
* Testler bağımsız çalışabilecek şekilde tasarlanmıştır.
* Thread.sleep() kullanılmamıştır.
