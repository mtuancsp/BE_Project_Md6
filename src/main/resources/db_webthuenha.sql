CREATE DATABASE  IF NOT EXISTS `project_md6` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `project_md6`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: project_md6
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookings` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_at` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `price` int DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `total` int DEFAULT NULL,
  `update_at` date DEFAULT NULL,
  `house_id` bigint DEFAULT NULL,
  `review_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `booking_status` enum('BOOKING','CANCELLED','CHECKED_IN','CHECKED_OUT','MAINTENANCE') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_mbpluvwlundljl1l258edaxu5` (`review_id`),
  KEY `FK7cqglmbjqafa47mmtaoklw3qe` (`house_id`),
  KEY `FKeyog2oic85xg7hsu2je2lx3s6` (`user_id`),
  CONSTRAINT `FK5kh29npp97yyxsxlouuhb35vr` FOREIGN KEY (`review_id`) REFERENCES `reviews` (`id`),
  CONSTRAINT `FK7cqglmbjqafa47mmtaoklw3qe` FOREIGN KEY (`house_id`) REFERENCES `houses` (`id`),
  CONSTRAINT `FKeyog2oic85xg7hsu2je2lx3s6` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
INSERT INTO `bookings` VALUES (10,'2023-07-25','2023-09-15',120,'2023-09-10',600,'2023-07-31',1,NULL,1,'CHECKED_OUT'),(11,'2023-07-25','2023-10-10',90,'2023-10-05',450,'2023-07-26',1,NULL,1,'CHECKED_OUT'),(12,'2023-07-25','2023-11-25',80,'2023-07-28',400,'2023-07-25',1,NULL,1,'CHECKED_OUT'),(14,'2023-07-25','2023-09-15',120,'2023-07-29',600,'2023-07-25',1,NULL,1,'BOOKING'),(15,'2023-07-25','2023-10-10',90,'2023-10-05',450,'2023-07-31',1,NULL,1,'CANCELLED'),(16,'2023-07-25','2023-11-25',80,'2023-11-20',400,'2023-07-25',1,NULL,1,'BOOKING'),(18,'2023-07-25','2023-09-15',120,'2023-09-10',600,'2023-07-25',1,NULL,1,'BOOKING'),(19,'2023-07-25','2023-10-10',90,'2023-10-05',450,'2023-07-25',1,NULL,1,'BOOKING'),(20,'2023-07-25','2023-11-25',80,'2023-11-20',400,'2023-07-25',1,NULL,1,'BOOKING'),(21,'2023-07-25','2023-08-07',100,'2023-08-01',700,'2023-07-25',1,NULL,4,'BOOKING'),(22,'2023-07-25','2023-09-15',120,'2023-09-10',600,'2023-07-25',1,NULL,3,'BOOKING'),(23,'2023-07-25','2023-10-10',90,'2023-10-05',450,'2023-07-25',1,NULL,1,'BOOKING'),(24,'2023-07-25','2023-11-25',80,'2023-11-20',400,'2023-07-25',1,NULL,1,'BOOKING'),(45,'2023-08-01','2023-08-12',2000,'2023-08-09',6300,'2023-07-31',2,NULL,1,'BOOKING'),(46,'2023-08-01','2023-08-22',2000,'2023-08-21',2100,'2023-07-26',2,NULL,1,'BOOKING'),(47,'2023-08-01','2023-08-26',2000,'2023-08-25',2100,'2023-07-25',2,NULL,1,'BOOKING'),(48,'2023-08-01','2023-08-12',1000,'2023-08-10',2100,'2023-07-25',3,NULL,1,'BOOKING'),(49,'2023-08-01','2023-08-18',1000,'2023-08-16',3150,'2023-07-31',3,NULL,1,'BOOKING'),(50,'2023-08-01','2023-08-22',1000,'2023-08-20',4200,'2023-07-25',3,NULL,1,'BOOKING'),(51,'2023-08-01','2023-08-03',1000,'2023-08-01',5250,'2023-07-25',3,NULL,1,'BOOKING'),(52,'2023-08-01','2023-09-21',1000,'2023-09-13',6300,'2023-07-25',3,NULL,1,'BOOKING'),(53,'2023-08-01','2023-09-06',1000,'2023-09-05',1050,'2023-07-25',3,NULL,1,'BOOKING'),(54,'2023-08-01','2023-10-14',1000,'2023-10-10',2100,'2023-07-25',3,NULL,1,'BOOKING'),(55,'2023-08-01','2023-11-10',1000,'2023-11-08',3150,'2023-07-25',3,NULL,1,'BOOKING'),(56,'2023-08-01','2023-09-02',1000,'2023-08-31',4200,'2023-07-25',3,NULL,1,'BOOKING'),(57,'2023-08-02','2023-09-15',2000,'2023-09-12',6300,'2023-07-25',2,NULL,1,'BOOKING'),(58,'2023-08-02','2023-09-21',2000,'2023-09-18',6300,'2023-07-31',2,NULL,1,'BOOKING'),(59,'2023-08-02','2024-01-04',2000,'2024-01-03',2100,'2023-07-26',2,NULL,1,'BOOKING'),(60,'2023-08-02','2023-08-18',1000,'2023-08-15',3150,'2023-07-25',4,NULL,1,'BOOKING'),(61,'2023-08-02','2023-09-22',1000,'2023-09-21',1050,'2023-07-25',4,NULL,1,'BOOKING'),(62,'2023-08-02','2023-10-06',1000,'2023-10-04',2100,'2023-07-31',4,NULL,1,'BOOKING'),(66,'2023-08-03','2023-08-28',1000,'2023-08-27',1050,'2023-07-25',4,NULL,1,'BOOKING');
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `houses`
--

DROP TABLE IF EXISTS `houses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `houses` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `price` double DEFAULT NULL,
  `total_bathrooms` int NOT NULL,
  `total_bedrooms` int NOT NULL,
  `user_id` bigint DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `description` text,
  `name` varchar(255) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `is_blocked` bit(1) NOT NULL,
  `featured_image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK42al334evgs9vf8bnryjxa1vs` (`user_id`),
  CONSTRAINT `FK42al334evgs9vf8bnryjxa1vs` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `houses`
--

LOCK TABLES `houses` WRITE;
/*!40000 ALTER TABLE `houses` DISABLE KEYS */;
INSERT INTO `houses` VALUES (1,1999,7,7,1,'ha noi','123','Homestay','2023-07-25',_binary '\0',NULL),(2,2000,2,2,3,'codegym phuong my dinh 2 quan nam tu liem thanh pho ha noi','123','Codegym','2023-07-24',_binary '\0',NULL),(3,999,4,3,1,'sơn la','nhà đẹp','string','2023-07-24',_binary '\0',NULL),(4,1000,2,3,1,'thai binh','Bên trong','Villa','2023-07-24',_binary '\0',NULL),(5,1000,2,3,4,'HaNoi','ok','tuan2 House','2023-07-24',_binary '\0',NULL),(6,1000,4,4,1,'HaNoi','Phía sau nhà','Biệt thự','2023-07-24',_binary '\0',NULL),(7,1000,8,6,1,'HaNoi','A beautiful house','hai House','2023-07-24',_binary '\0',NULL),(8,1000,2,3,3,'hai phong','A beautiful house','hai3 House','2023-07-24',_binary '\0',NULL),(9,1000,4,5,1,'HaNoi','A beautiful homestay','tuan homestay','2023-07-24',_binary '\0',NULL),(10,5000,4,4,3,'HaNoi','A beautiful homestay','nam3 homestay','2023-07-24',_binary '\0',NULL),(11,2000,8,7,4,'HaNoi','A beautiful house','nam3 House','2023-07-24',_binary '\0',NULL),(12,1000,2,3,3,'bac ninh','A beautiful homestay','hai do homestay','2023-07-24',_binary '\0',NULL),(13,1000,2,3,7,'HaNoi','A beautiful house','Ngo Hai House','2023-07-24',_binary '\0',NULL),(14,1000,2,3,5,'bac giang','A beautiful house','nam3 House','2023-07-24',_binary '\0',NULL),(15,1000,2,3,5,'HaNoi','A beautiful hotel','tuan hotel','2023-07-24',_binary '\0',NULL),(53,1234,1,2,1,'Thanh Xuân','A beautiful hotel','123','2023-07-27',_binary '\0',NULL),(74,1235,3,4,1,'Long Biên','1234','Nhật','2023-07-27',_binary '\0',NULL),(86,2222,3,3,1,'Hà Nội','9999','Công','2023-08-02',_binary '\0',NULL),(87,1221,1,2,1,'Codegym','A beautiful hotel','Nhà đẹp 2023','2023-08-03',_binary '',NULL),(88,9999,3,1,1,'Codegym Hà Nội','Trung tâm đào tạo lập trình số 1 trong lòng người hâm mộ','Codegym ','2023-08-06',_binary '',NULL),(89,1000,1,2,1,'Hà Nội','123456','Công','2023-08-06',_binary '\0',NULL);
/*!40000 ALTER TABLE `houses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `images` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `file_url` varchar(255) DEFAULT NULL,
  `house_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj7uof92hhgyvh6v49gsi6sava` (`house_id`),
  CONSTRAINT `FKj7uof92hhgyvh6v49gsi6sava` FOREIGN KEY (`house_id`) REFERENCES `houses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=487 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `images`
--

LOCK TABLES `images` WRITE;
/*!40000 ALTER TABLE `images` DISABLE KEYS */;
INSERT INTO `images` VALUES (177,'https://charminghome.com.vn/wp-content/uploads/2018/01/Thi%E1%BA%BFt-K%E1%BA%BF-Homestay-L%C3%A0ng-Homestay-2.jpg',3),(178,'https://thietkenhadepmoi.com/wp-content/uploads/2021/06/homestay-dep-2034.jpg',3),(179,'https://huthouse.vn/wp-content/uploads/2021/12/%C4%90i%E1%BB%83m-danh-nh%E1%BB%AFng-m%E1%BA%ABu-nh%C3%A0-homestay-%C4%91%E1%BA%B9p-v%C3%A0-n%E1%BB%95i-b%E1%BA%ADt-t%E1%BA%A1i-Hut-House-1-1024x724.jpg',3),(180,'https://ancu.me/images/201903/mau-nha-go-homestay-dep-khong-tuong/mau-nha-go-homestay-dep-khong-tuong.jpg?image_id=18916',3),(181,'https://gochat.com.vn/wp-content/uploads/2020/05/nh%C3%A0-g%E1%BB%97-homestay-%C4%91%E1%BA%B9p-11.jpg',3),(182,'https://nissei.vn/wp-content/uploads/2021/12/xay-bungalow-gia-re-9.jpg',4),(183,'https://noithatduongdai.cdn.vccloud.vn/wp-content/uploads/2020/01/thiet-ke-homestay-don-gian-dep-hut-hon-moi-goc-nhin-1.jpeg',4),(184,'https://ytuongnhadep.vn/Images/image/Thiet-ke-nha-go-homstay-dep-trong-cac-khu-du-lich-nghi-duong/Thiet-ke-nha-go-homstay-dep-trong-cac-khu-du-lich-nghi-duong-1.jpg',4),(185,'https://www.tapdoantrananh.com.vn/uploads/files/2020/10/05/mau-nha-homestay-gia-re-0.jpg',4),(186,'https://nissei.vn/wp-content/uploads/2021/12/xay-bungalow-gia-re-9.jpg',4),(187,'https://designvintage.vn/upload/product/nha-homestay-bungalow-9945.jpg',5),(188,'https://noithattailocgialai.com/wp-content/uploads/2021/06/thi-cong-homestay-go-1.jpg',5),(189,'http://kendecor.vn/upload/images/top-3-mau-nha-go-homestay-dep-danh-cho-ban.png',5),(190,'https://ximanggiago.vn/wp-content/uploads/2022/05/nha-lap-ghep-homestay-1024x666.jpg',5),(191,'https://thietkenhadepmoi.com/wp-content/uploads/2021/06/thiet-ke-homestay-dep-2025.jpg',5),(197,'http://nhagoxanh.com/img_data/images/mau-nha-go-homestay-dep-ngat-ngay.jpg',7),(198,'https://xaydung.edu.vn/wp-content/uploads/thiet-ke-bungalow.jpg',7),(199,'https://thietkenoithatatz.com/wp-content/uploads/2019/05/thiet-ke-noi-that-nha-o-homestay-10.jpg',7),(200,'https://xaydungthanhduc.com/wp-content/uploads/2021/06/mau-nha-homestay-dep-05.jpg',7),(201,'https://nhagodidong.net/wp-content/uploads/2019/11/nha-go-bungalow.png',7),(202,'http://gspace.vn/web/media/images/2022/06/27/q1-rs650.jpg',8),(203,'http://charminghome.com.vn/wp-content/uploads/2018/01/Thi%E1%BA%BFt-K%E1%BA%BF-Homestay-L%C3%A0ng-Homestay-5.jpg',8),(204,'https://www.tapdoantrananh.com.vn/uploads/files/2020/10/02/mau-nha-homestay-dep-3.jpg',8),(205,'https://designvintage.vn/thumb/445x345/2/upload/product/nha-bungalow-rustic-22.jpg',8),(206,'https://kientrucsuvietnam.vn/wp-content/uploads/2021/12/thiet-ke-homestay-nha-vuon.jpg',8),(207,'https://langtreviet.com/upload/user/images/nha-go-homestay-3.jpg',9),(208,'https://ancu.me/images/201903/mau-thiet-ke-homestay-ba-vi-ha-noi-embossi-garden/mau-thiet-ke-homestay-ba-vi-ha-noi-embossi-garden.jpg?image_id=18891',9),(209,'https://ytuongnhadep.vn/Images/image/Thiet-ke-nha-go-homstay-dep-trong-cac-khu-du-lich-nghi-duong/Thiet-ke-nha-go-homstay-dep-trong-cac-khu-du-lich-nghi-duong-2.jpg',9),(210,'https://agsevent.vn/wp-content/uploads/2022/09/chi-phi-thiet-ke-homestay-3.jpg',9),(211,'http://ongkien.vn/upload/baiviet/nhagolapghepchohomestay-472.jpg',9),(212,'https://katahome.com/wp-content/uploads/2020/02/homestay-mai-la.jpg',10),(213,'https://acchome.com.vn/wp-content/uploads/2022/02/nhung-mau-nha-go-homestay-dep-6.jpg',10),(214,'https://nhagonamha.vn/upload/files/hinh_anh_mau_nha_go_homestay_1.jpg',10),(215,'https://bdbcons.com/wp-content/uploads/nha-tien-che-homestay-1.jpg',10),(216,'https://thietkenhadepmoi.com/wp-content/uploads/2021/06/homestay-dep-2033.jpg',10),(217,'https://kientrucsuvietnam.vn/wp-content/uploads/2021/12/thiet-ke-homestay-nha-go-2.jpg',11),(218,'https://quatest2.com.vn/wp-content/uploads/2021/04/mau-nha-homestay-la-gi.jpg',11),(219,'https://nhadepshouse.com/hinh-anh/san-pham/thiet-ke-nha-go-bungalow-homestay-farmstay.jpg',11),(220,'https://sp-ao.shortpixel.ai/client/q_glossy,ret_img,w_350/https://nhabungalow.com/wp-content/uploads/2021/02/thiet-ke-thi-cong-nha-bungalow-2-350x350.jpg',11),(221,'https://angcovat.vn/imagesdata/TIN912020/bao-gia-thi-cong-homestay.jpg',11),(222,'https://ancu.me/images/201903/homestay-don-gian-theo-phong-cach-truyen-thong/homestay-don-gian-theo-phong-cach-truyen-thong.jpg?image_id=18910',12),(223,'https://trongoixaynha.com/wp-content/uploads/2021/12/xay-homestay-2.jpg',12),(224,'https://dsdhome.vn/uploads/products/nha-lap-ghep-homestay3.png',12),(225,'https://www.tapdoantrananh.com.vn/uploads/files/2020/10/05/mau-nha-homestay-gia-re-7.jpg',12),(226,'https://adalhome.vn/wp-content/uploads/2021/10/choang-ngop-voi-nhung-mau-homestay-dep-an-tuong-6731-20.jpg',12),(227,'https://ongkien.vn/upload/images/bungalows/nhago/nha-lap-ghep-triangle-4.jpg',13),(228,'https://dsdhome.vn/uploads/products/nha-lap-ghep-homestay5.png',13),(229,'https://designvintage.vn/thumb/445x345/2/upload/product/thi-cong-homestay-ba-ria-vung-tau-4071.jpg',13),(230,'https://angcovat.vn/images/1-thiet-ke-homestay-2-tang-160m2.jpg',13),(231,'https://thietkenhadepmoi.com/wp-content/uploads/2021/06/homestay-dep-2023.jpg',13),(232,'https://thietkenhadepmoi.com/wp-content/uploads/2021/06/homestay-dep-2023.jpg',14),(233,'http://nhagoxanh.com/img_data/images/nha-go-homestay-to-chim.jpg',14),(234,'https://nhadepshouse.com/picture/file/thiet-ke-farmstay-tai-soc-son-ha-noi_(1).jpg',14),(235,'https://quatest2.com.vn/wp-content/uploads/2021/04/thiet-ke-homestay-dep-moc-mac.jpg',14),(236,'https://ancu.me/images/201903/mau-homestay-cho-cap-doi-dep-la-va-lang-man/mau-homestay-cho-cap-doi-dep-la-va-lang-man.jpg?image_id=18911',14),(237,'https://adalhome.vn/wp-content/uploads/2021/10/choang-ngop-voi-nhung-mau-homestay-dep-an-tuong-6731-1.jpg',15),(238,'https://trongoixaynha.com/wp-content/uploads/2021/12/xay-homestay-35.jpg',15),(239,'http://cdn.houseviet.vn/images/news/21042022/132949723759009851.jpg',15),(240,'https://agsevent.vn/wp-content/uploads/2022/09/mau-nha-homestay-gia-re-02.png',15),(241,'https://kientrucauchau.vn/wp-content/uploads/2021/12/ban-ve-3D-thiet-ke-kien-truc-homestay.jpeg',15),(446,'https://w0.peakpx.com/wallpaper/370/732/HD-wallpaper-spectacular-seaside-home-modern-house-view-pool-terrace-sea.jpg',1),(447,'https://nissei.vn/wp-content/uploads/2021/12/xay-bungalow-gia-re-9.jpg',1),(448,'https://w0.peakpx.com/wallpaper/332/789/HD-wallpaper-seaside-holiday-houses.jpg',1),(453,'https://thietkenhadepmoi.com/wp-content/uploads/2021/06/thiet-ke-homestay-dep-2025.jpg',10),(454,'https://ximanggiago.vn/wp-content/uploads/2022/05/nha-lap-ghep-homestay-1024x666.jpg',9),(455,'https://ancu.me/images/201903/mau-nha-go-homestay-dep-khong-tuong/mau-nha-go-homestay-dep-khong-tuong.jpg?image_id=18916',8),(456,'https://huthouse.vn/wp-content/uploads/2021/12/%C4%90i%E1%BB%83m-danh-nh%E1%BB%AFng-m%E1%BA%ABu-nh%C3%A0-homestay-%C4%91%E1%BA%B9p-v%C3%A0-n%E1%BB%95i-b%E1%BA%ADt-t%E1%BA%A1i-Hut-House-1-1024x724.jpg',7),(457,'https://thietkenhadepmoi.com/wp-content/uploads/2021/06/homestay-dep-2034.jpg',6),(458,'https://charminghome.com.vn/wp-content/uploads/2018/01/Thi%E1%BA%BFt-K%E1%BA%BF-Homestay-L%C3%A0ng-Homestay-2.jpg',5),(461,'https://www.tapdoantrananh.com.vn/uploads/files/2020/10/05/mau-nha-homestay-gia-re-0.jpg',4),(462,'https://w0.peakpx.com/wallpaper/370/732/HD-wallpaper-spectacular-seaside-home-modern-house-view-pool-terrace-sea.jpg',3),(463,'https://nissei.vn/wp-content/uploads/2021/12/xay-bungalow-gia-re-9.jpg',2),(464,'https://w0.peakpx.com/wallpaper/332/789/HD-wallpaper-seaside-holiday-houses.jpg',11),(465,'https://w0.peakpx.com/wallpaper/332/789/HD-wallpaper-seaside-holiday-houses.jpg',1),(473,'https://ximanggiago.vn/wp-content/uploads/2022/05/nha-lap-ghep-homestay-1024x666.jpg',74),(474,'https://langtreviet.com/upload/user/images/nha-go-homestay-3.jpg',74),(477,'https://www.tapdoantrananh.com.vn/uploads/files/2020/10/05/mau-nha-homestay-gia-re-0.jpg',1),(481,'https://katahome.com/wp-content/uploads/2020/02/homestay-mai-la.jpg',86),(482,'https://acchome.com.vn/wp-content/uploads/2022/02/nhung-mau-nha-go-homestay-dep-6.jpg',86),(483,'https://nhagonamha.vn/upload/files/hinh_anh_mau_nha_go_homestay_1.jpg',88),(484,'https://bdbcons.com/wp-content/uploads/nha-tien-che-homestay-1.jpg',88),(485,'https://thietkenhadepmoi.com/wp-content/uploads/2021/06/homestay-dep-2033.jpg',89),(486,'https://thietkenhadepmoi.com/wp-content/uploads/2021/06/thiet-ke-homestay-dep-2025.jpg',89);
/*!40000 ALTER TABLE `images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` text,
  `created_at` date DEFAULT NULL,
  `rating` int DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `review_status` enum('APPROVED','PENDING','REJECTED') DEFAULT NULL,
  `house_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgto6vm4vaj3etd9ipjl81we2i` (`house_id`),
  KEY `FKcgy7qjc1r99dp117y9en6lxye` (`user_id`),
  CONSTRAINT `FKcgy7qjc1r99dp117y9en6lxye` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKgto6vm4vaj3etd9ipjl81we2i` FOREIGN KEY (`house_id`) REFERENCES `houses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
INSERT INTO `reviews` VALUES (1,'Chỗ ở đáng lẽ phải nhận phòng lúc 3 giờ, nhưng trời mưa với bạn trai tôi và tôi trong mưa vào ban ngày, vì vậy tôi đã hỏi liệu tôi có thể kiểm tra sớm một chút qua điện thoại không, và tôi rất biết ơn vì anh ấy đã cho tôi nhận phòng lúc 1 giờ. Khi tôi đang đi dạo quanh chỗ ở, tôi đến vào khoảng 12 giờ trưa, và anh ấy nói tôi có thể đến sớm, vì vậy tôi đã có thể vào và nghỉ ngơi sớm hơn nhiều so với thời gian ban đầu. Thật đáng tiếc khi tôi không thể thư giãn trên lối đi dạo trong vườn vì trời mưa, nhưng đó là một chuyến đi nghỉ ngơi trong nhà ngay từ đầu, vì vậy tôi thích chỗ ở nhỏ và dễ thương!! Nếu có thể, tôi muốn nghỉ ngơi trong một tuần. Nội thất rất sạch sẽ và tôi không nấu ăn, nhưng có tất cả các gia vị và dụng cụ nấu ăn, vì vậy tôi nghĩ tôi sẽ sử dụng nó một cách thuận tiện nếu tôi đã nấu ăn. Anh ấy nói rằng con mèo đang sống, nhưng thật tệ là tôi đã không đi qua cửa sổ, nhưng cuối cùng tôi không thể nhìn thấy nó.\nTôi đã hứa sẽ trở lại vào mùa đông tuyết rơi với bạn trai. Tôi đã có một thời gian nghỉ ngơi tốt nhờ chủ nhà rất tốt bụng! Nhờ có bạn, chuyến đi này là một kỷ niệm tuyệt vời.','2023-07-30',5,'2023-08-01','APPROVED',1,1),(2,'Chỗ ở đáng lẽ phải nhận phòng lúc 3 giờ, nhưng trời mưa với bạn trai tôi và tôi trong mưa vào ban ngày, vì vậy tôi đã hỏi liệu tôi có thể kiểm tra sớm một chút qua điện thoại không, và tôi rất biết ơn vì anh ấy đã cho tôi nhận phòng lúc 1 giờ. Khi tôi đang đi dạo quanh chỗ ở, tôi đến vào khoảng 12 giờ trưa, và anh ấy nói tôi có thể đến sớm, vì vậy tôi đã có thể vào và nghỉ ngơi sớm hơn nhiều so với thời gian ban đầu. Thật đáng tiếc khi tôi không thể thư giãn trên lối đi dạo trong vườn vì trời mưa, nhưng đó là một chuyến đi nghỉ ngơi trong nhà ngay từ đầu, vì vậy tôi thích chỗ ở nhỏ và dễ thương!! Nếu có thể, tôi muốn nghỉ ngơi trong một tuần. Nội thất rất sạch sẽ và tôi không nấu ăn, nhưng có tất cả các gia vị và dụng cụ nấu ăn, vì vậy tôi nghĩ tôi sẽ sử dụng nó một cách thuận tiện nếu tôi đã nấu ăn. Anh ấy nói rằng con mèo đang sống, nhưng thật tệ là tôi đã không đi qua cửa sổ, nhưng cuối cùng tôi không thể nhìn thấy nó.\nTôi đã hứa sẽ trở lại vào mùa đông tuyết rơi với bạn trai. Tôi đã có một thời gian nghỉ ngơi tốt nhờ chủ nhà rất tốt bụng! Nhờ có bạn, chuyến đi này là một kỷ niệm tuyệt vời.','2023-07-29',4,'2023-08-01','APPROVED',1,1),(4,'Chỗ ở đáng lẽ phải nhận phòng lúc 3 giờ, nhưng trời mưa với bạn trai tôi và tôi trong mưa vào ban ngày, vì vậy tôi đã hỏi liệu tôi có thể kiểm tra sớm một chút qua điện thoại không, và tôi rất biết ơn vì anh ấy đã cho tôi nhận phòng lúc 1 giờ. Khi tôi đang đi dạo quanh chỗ ở, tôi đến vào khoảng 12 giờ trưa, và anh ấy nói tôi có thể đến sớm, vì vậy tôi đã có thể vào và nghỉ ngơi sớm hơn nhiều so với thời gian ban đầu. Thật đáng tiếc khi tôi không thể thư giãn trên lối đi dạo trong vườn vì trời mưa, nhưng đó là một chuyến đi nghỉ ngơi trong nhà ngay từ đầu, vì vậy tôi thích chỗ ở nhỏ và dễ thương!! Nếu có thể, tôi muốn nghỉ ngơi trong một tuần. Nội thất rất sạch sẽ và tôi không nấu ăn, nhưng có tất cả các gia vị và dụng cụ nấu ăn, vì vậy tôi nghĩ tôi sẽ sử dụng nó một cách thuận tiện nếu tôi đã nấu ăn. Anh ấy nói rằng con mèo đang sống, nhưng thật tệ là tôi đã không đi qua cửa sổ, nhưng cuối cùng tôi không thể nhìn thấy nó.\nTôi đã hứa sẽ trở lại vào mùa đông tuyết rơi với bạn trai. Tôi đã có một thời gian nghỉ ngơi tốt nhờ chủ nhà rất tốt bụng! Nhờ có bạn, chuyến đi này là một kỷ niệm tuyệt vời.','2023-07-29',5,'2023-08-01','REJECTED',1,5),(5,'Chỗ ở đáng lẽ phải nhận phòng lúc 3 giờ, nhưng trời mưa với bạn trai tôi và tôi trong mưa vào ban ngày, vì vậy tôi đã hỏi liệu tôi có thể kiểm tra sớm một chút qua điện thoại không, và tôi rất biết ơn vì anh ấy đã cho tôi nhận phòng lúc 1 giờ. Khi tôi đang đi dạo quanh chỗ ở, tôi đến vào khoảng 12 giờ trưa, và anh ấy nói tôi có thể đến sớm, vì vậy tôi đã có thể vào và nghỉ ngơi sớm hơn nhiều so với thời gian ban đầu. Thật đáng tiếc khi tôi không thể thư giãn trên lối đi dạo trong vườn vì trời mưa, nhưng đó là một chuyến đi nghỉ ngơi trong nhà ngay từ đầu, vì vậy tôi thích chỗ ở nhỏ và dễ thương!! Nếu có thể, tôi muốn nghỉ ngơi trong một tuần. Nội thất rất sạch sẽ và tôi không nấu ăn, nhưng có tất cả các gia vị và dụng cụ nấu ăn, vì vậy tôi nghĩ tôi sẽ sử dụng nó một cách thuận tiện nếu tôi đã nấu ăn. Anh ấy nói rằng con mèo đang sống, nhưng thật tệ là tôi đã không đi qua cửa sổ, nhưng cuối cùng tôi không thể nhìn thấy nó.\nTôi đã hứa sẽ trở lại vào mùa đông tuyết rơi với bạn trai. Tôi đã có một thời gian nghỉ ngơi tốt nhờ chủ nhà rất tốt bụng! Nhờ có bạn, chuyến đi này là một kỷ niệm tuyệt vời.','2023-07-29',5,'2023-08-01','APPROVED',1,3),(6,'Chỗ ở đáng lẽ phải nhận phòng lúc 3 giờ, nhưng trời mưa với bạn trai tôi và tôi trong mưa vào ban ngày, vì vậy tôi đã hỏi liệu tôi có thể kiểm tra sớm một chút qua điện thoại không, và tôi rất biết ơn vì anh ấy đã cho tôi nhận phòng lúc 1 giờ. Khi tôi đang đi dạo quanh chỗ ở, tôi đến vào khoảng 12 giờ trưa, và anh ấy nói tôi có thể đến sớm, vì vậy tôi đã có thể vào và nghỉ ngơi sớm hơn nhiều so với thời gian ban đầu. Thật đáng tiếc khi tôi không thể thư giãn trên lối đi dạo trong vườn vì trời mưa, nhưng đó là một chuyến đi nghỉ ngơi trong nhà ngay từ đầu, vì vậy tôi thích chỗ ở nhỏ và dễ thương!! Nếu có thể, tôi muốn nghỉ ngơi trong một tuần. Nội thất rất sạch sẽ và tôi không nấu ăn, nhưng có tất cả các gia vị và dụng cụ nấu ăn, vì vậy tôi nghĩ tôi sẽ sử dụng nó một cách thuận tiện nếu tôi đã nấu ăn. Anh ấy nói rằng con mèo đang sống, nhưng thật tệ là tôi đã không đi qua cửa sổ, nhưng cuối cùng tôi không thể nhìn thấy nó.\nTôi đã hứa sẽ trở lại vào mùa đông tuyết rơi với bạn trai. Tôi đã có một thời gian nghỉ ngơi tốt nhờ chủ nhà rất tốt bụng! Nhờ có bạn, chuyến đi này là một kỷ niệm tuyệt vời.','2023-07-29',4,'2023-08-02','REJECTED',1,4),(10,'tốt','2023-07-31',5,NULL,'APPROVED',2,1),(11,'công ngu','2023-08-01',5,'2023-08-01','REJECTED',1,1);
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `apply_host` bit(1) NOT NULL,
  `create_at` date DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `is_blocked` bit(1) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `profile_image` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','HOST','USER') DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,_binary '\0','2023-07-23','minhtuan2679769@gmail.com','Tuấn',_binary '\0','Phạm','$2a$10$BXPrLf6XlaahmgOb37YZP.3yzBsuKlx37B9w425QfXSv8ROrQrkdm','0999999999','https://firebasestorage.googleapis.com/v0/b/casemd4-3a742.appspot.com/o/md6%2FScreenshot%202023-03-05%20222413.png?alt=media&token=b6e70f4a-99d7-46cd-aa52-6d0c0a44ef28','ADMIN','mt',NULL),(3,_binary '\0','2023-07-23','minhtuan2672@gmail.com','Nam',_binary '\0','T','$2a$10$BkpTQjGM1hvJuzHzW9xcvuD.o6tbgqgDsk87Z8wIjQdOqMoHH.DR6','0967726796','https://cuongquach.com/wp-content/uploads/2016/05/linux-logo-356x220.png','HOST','minhtuan2',NULL),(4,_binary '\0','2023-07-23','minhtuan2@gmail.com','Hải',_binary '','C','$2a$10$SxlOCCEi0FZWswxyPzvP5.RQGhE084vhBD8iMFrCkr747k9yoag/q','0967726798',NULL,'HOST','mt2',NULL),(5,_binary '\0','2023-07-23','minhtuan267001@gmaiil.com','Hùng',_binary '\0','B','$2a$10$fxVfxvxzUmGu.Myfw4EgOeRJz7zFe4uYTXu.JrL8ghZehxSOlubyG',NULL,'https://cuongquach.com/wp-content/uploads/2016/05/linux-logo-356x220.png','HOST','tuan001',NULL),(7,_binary '\0','2023-07-23','minhtuan26722@gmail.com','Mạnh',_binary '\0','T',NULL,'NoInformation1','https://lh3.googleusercontent.com/a/AAcHTtfLmnN--AI7A9hsbrDw46B-fzMcQvSvHiMOToPzkwtzq2E=s96-c','HOST','a@gmail.com',NULL),(9,_binary '\0','2023-07-23','minhtuan2671@gmail.com','Cường',_binary '\0','A','$2a$10$2FLkOHJDORv.XnS9S8I4yeM57yNKv0AC4hl3p5GpcBJocPQ/CWTcu','0967726799',NULL,'USER','minhtuan',NULL),(10,_binary '\0','2023-07-24','minhtuan267971@gmail.com','Phạm Minh',_binary '\0','Tuấn',NULL,'NoInformation2','https://cuongquach.com/wp-content/uploads/2016/05/linux-logo-356x220.png','USER','minhtuan2@gmail.com',NULL),(15,_binary '\0','2023-07-24','haido@gmail.com','Sơn',_binary '\0','Trong Hai','$2a$10$ro7PhjN6Cgn.Skvp8KkGeucFUdJ.6berWJyrvOw8btfDr4bttGOZ6','0354666699','123456.jgp','ADMIN','haido',NULL),(16,_binary '\0','2023-07-24','haingo@gmail.com','Ngo',_binary '\0','Hoang Hai','$2a$10$ro7PhjN6Cgn.Skvp8KkGeucFUdJ.6berWJyrvOw8btfDr4bttGOZ6','0987654321','123456.jgp','USER','haingo',NULL),(17,_binary '\0','2023-07-24','nam@gmail.com','Nguyen',_binary '\0','Hoai Nam','$2a$10$ro7PhjN6Cgn.Skvp8KkGeucFUdJ.6berWJyrvOw8btfDr4bttGOZ6','0147258369','123456.jgp','HOST','nam',NULL),(18,_binary '\0','2023-07-24','tuan@gmail.com','Pham',_binary '','Minh Tuan','$2a$10$ro7PhjN6Cgn.Skvp8KkGeucFUdJ.6berWJyrvOw8btfDr4bttGOZ6','0789456123','123456.jgp','HOST','tuan',NULL),(19,_binary '\0','2023-07-24','cong@gmail.com','Nguyen',_binary '\0','Minh Cong','$2a$10$ro7PhjN6Cgn.Skvp8KkGeucFUdJ.6berWJyrvOw8btfDr4bttGOZ6','0369852147','123456.jgp','HOST','cong',NULL),(20,_binary '\0','2023-07-24','gathidungru@gmail.com','Huyền',_binary '\0','Trong Hai','$2a$10$ro7PhjN6Cgn.Skvp8KkGeucFUdJ.6berWJyrvOw8btfDr4bttGOZ6','0921345678','123456.jgp','USER','hai123',NULL),(21,_binary '\0','2023-07-24','tronghai1710@gmail.com','Dủa',_binary '\0','Trong Hai','$2a$10$ro7PhjN6Cgn.Skvp8KkGeucFUdJ.6berWJyrvOw8btfDr4bttGOZ6','0123987654','123456.jgp','HOST','tronghai',NULL),(22,_binary '\0','2023-07-24','hai456@gmail.com','Linh',_binary '\0','Trong Hai','$2a$10$ro7PhjN6Cgn.Skvp8KkGeucFUdJ.6berWJyrvOw8btfDr4bttGOZ6','0567489321','123456.jgp','USER','hai456',NULL),(23,_binary '\0','2023-07-24','minhtuan26797@gmail.com','Phạm Minh',_binary '\0','Tuấn',NULL,'NoInformation1','https://lh3.googleusercontent.com/a/AAcHTtdgC9CYvcLLQnA5Ru1_Q4akeVJuF6ylPKS4jT3INBh7=s96-c','USER','minhtuan26797a@gmail.com',NULL),(24,_binary '\0','2023-07-24','minhcongngu@gmail.com','Nam',_binary '\0','E','$2a$10$U2Ta/4xGOrelUGqaZr8/LesIXgWs95Y97BEVmRAGwMPKEbs5kajgq',NULL,'https://cuongquach.com/wp-content/uploads/2016/05/linux-logo-356x220.png','USER','tuan002',NULL),(25,_binary '\0','2023-07-26','minhcong3@gmail.com','Anh',_binary '\0','R','$2a$10$V8W5rjpRZKLJBkjV4gNfjuVxaQVVxo7sAPybJXJlIwyi7GoyEZzNK',NULL,'https://cuongquach.com/wp-content/uploads/2016/05/linux-logo-356x220.png','USER','mtuan1',NULL),(30,_binary '\0','2023-07-27','string@gmail.com','Dũng',_binary '\0','F','$2a$10$7ISjCGb1kJp8wBh8BEZOQe5cJ50lSKHYRq6zTxieLWIQkpSBwMtsW','0393199693','https://cuongquach.com/wp-content/uploads/2016/05/linux-logo-356x220.png','USER','string',NULL),(31,_binary '\0','2023-07-27','minhcong11@gmail.com','Công',_binary '\0','R','$2a$10$O9WddJi3o96PgB6yvIqLVuZAiyvx8GP3tFA3ryMGOL4Z9dP9z9RFG',NULL,'https://cuongquach.com/wp-content/uploads/2016/05/linux-logo-356x220.png','USER','admin1',NULL),(32,_binary '\0','2023-07-27','string1@gmail.com','Hoàng',_binary '\0','F','$2a$10$3hGWU7pKcNrt.GA2bI8Nk.ueVPFKlcNbYL3eDWbTT8a/wCJgH.gOW','0393199699','https://cuongquach.com/wp-content/uploads/2016/05/linux-logo-356x220.png','USER','string1',NULL),(35,_binary '\0','2023-07-27','minhtuan1@gmail.com','Chung',_binary '\0','A','$2a$10$HOWz40ix3JYJRcBe0AvgwuVLDM0CDmKTBnoa6J9Ozo1CqTU8DHFze',NULL,'https://cuongquach.com/wp-content/uploads/2016/05/linux-logo-356x220.png','USER','admin12',NULL),(36,_binary '\0','2023-07-27','minhtuan267@gmaiil.com','Hiếu',_binary '\0','J','$2a$10$Tyzanld0Maup1oQcnw9ac./6FqHSFDAX/5sMRlL7xY2fml85dqDpe','0967726795','https://cuongquach.com/wp-content/uploads/2016/05/linux-logo-356x220.png','USER','admin123',NULL),(37,_binary '\0','2023-07-27','minhcong22@gmail.com','Nga',_binary '\0','Q','$2a$10$8ugKaCWUVIbATysx1gESM.1zu12sJEgdATGrVQ4OLFNnRsEtT33hO','0987656789','https://cuongquach.com/wp-content/uploads/2016/05/linux-logo-356x220.png','USER','minhtuan123',NULL),(38,_binary '\0','2023-07-23','1qaz@gmail.com','Nguyệt',_binary '\0','U',NULL,NULL,'https://cuongquach.com/wp-content/uploads/2016/05/linux-logo-356x220.png','USER','1qaz',NULL),(40,_binary '\0','2023-07-30','minhtuan267@gmail.com','Hằng',_binary '\0','E','$2a$10$pBy80XP8cQQJpTwYlZjsUethVPZpoCdhflVk2mRdn//MnYG8uOsC2','0967726734','https://cuongquach.com/wp-content/uploads/2016/05/linux-logo-356x220.png','USER','mt123456',NULL),(41,_binary '','2023-08-06','minhtuan2678@gmaiil.com','Hạnh',_binary '\0','N','$2a$10$bFHnA1g/RK5qSEMClRjt3OovVveXc0mZd9iK/PZNa.wZXnn0hcnme','0967726797','https://cuongquach.com/wp-content/uploads/2016/05/linux-logo-356x220.png','USER','mtuan2',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-06 22:12:09
