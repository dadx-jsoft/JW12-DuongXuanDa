-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: shop
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tbl_category`
--

DROP TABLE IF EXISTS `tbl_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `status` tinyint(1) DEFAULT '1',
  `seo` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_internal_idx` (`parent_id`),
  CONSTRAINT `fk_internal` FOREIGN KEY (`parent_id`) REFERENCES `tbl_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='Bảng dữ liệu chứa danh mục sản phẩm';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_category`
--

LOCK TABLES `tbl_category` WRITE;
/*!40000 ALTER TABLE `tbl_category` DISABLE KEYS */;
INSERT INTO `tbl_category` VALUES (12,'Java','Java',NULL,NULL,NULL,NULL,NULL,1,'java-1606908198354'),(13,'C++','C++',NULL,NULL,NULL,NULL,NULL,1,'c-sharp-1606908215554'),(14,'Pascal','Pascal',NULL,NULL,NULL,NULL,NULL,1,'pascal-1606908273328'),(15,'Python','Python',NULL,NULL,NULL,NULL,NULL,1,'python-1606908245076'),(16,'C#','C#',NULL,NULL,NULL,NULL,NULL,1,'c-1606908291902');
/*!40000 ALTER TABLE `tbl_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_contact`
--

DROP TABLE IF EXISTS `tbl_contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `last_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `request_type` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `message` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `status` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='Bảng dữ liệu chứa thông tin liên hệ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_contact`
--

LOCK TABLES `tbl_contact` WRITE;
/*!40000 ALTER TABLE `tbl_contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_products`
--

DROP TABLE IF EXISTS `tbl_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` decimal(13,2) DEFAULT NULL,
  `price_sale` decimal(13,2) DEFAULT NULL,
  `short_description` varchar(3000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `detail_description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `avatar` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `status` tinyint(1) DEFAULT '1',
  `seo` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_category_products_idx` (`category_id`),
  CONSTRAINT `fk_category_products` FOREIGN KEY (`category_id`) REFERENCES `tbl_category` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='Bảng dữ liệu chứa danh mục sản phẩm';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_products`
--

LOCK TABLES `tbl_products` WRITE;
/*!40000 ALTER TABLE `tbl_products` DISABLE KEYS */;
INSERT INTO `tbl_products` VALUES (49,' Top 5 thư viện Javascript Code Highlighter tốt nhất ',100000.00,90000.00,'Bạn có 1 trang web, bạn biết một ngôn ngữ lập trình, bạn muốn chia sẻ những đoạn mã code mà bạn viết. Sau đây là 5 thư viện Javascript Code HighLighter dùng để hiển thị mã code một cách đẹp mắt và dễ nhìn.','<div class=\"section entry-content pstyle\">\r\n              <p>Bạn có 1 trang web, bạn biết một ngôn ngữ lập trình, bạn muốn chia sẻ những đoạn mã code mà bạn viết. Sau đây là 5 <a href=\"https://vinasupport.com/tag/thu-vien-javascript/\">thư viện Javascript</a> Code HighLighter dùng để hiển thị mã code một cách đẹp mắt và dễ nhìn.</p>\r\n<h2>1.&nbsp;highlightJS</h2>\r\n<p><strong><a href=\"https://vinasupport.com/tag/highlightjs/\">HighLightJS</a></strong> là 1 trong những thư viện <a href=\"https://vinasupport.com/tag/code-highlighter/\">Code HighLighter</a> phổ biến nhất, hỗ trợ 185 <a href=\"https://vinasupport.com/tag/ngon-ngu-lap-trinh/\">ngôn ngữ lập trình</a> và 89 theme. Ngoài ra nó còn support nhiều tính năng sau:</p>\r\n<p>– Tự động phát hiện ngôn ngữ<br>\r\n– multi-language code highlighting<br>\r\n– Hỗ trợ cho node.js<br>\r\n– Support markup<br>\r\n– Tương thích với nhiều JS framework</p>\r\n<ul><li><strong>Website</strong>:&nbsp;<a href=\"https://highlightjs.org/\" target=\"_blank\" rel=\"external nofollow noopener\">https://highlightjs.org/</a></li><li><strong>Download</strong>:&nbsp;<a href=\"https://highlightjs.org/download/\" target=\"_blank\" rel=\"external nofollow noopener\">https://highlightjs.org/download/</a></li></ul>\r\n<p><img class=\"alignnone size-full wp-image-928\" src=\"https://vinasupport.com/uploads/2019/04/HighlightJs-Syntax-highlighting-for-the-Web.png\" alt=\"\" style=\"width: 50%;\" width=\"983\" height=\"561\"></p>\r\n<h2>2.&nbsp;PrismJS</h2>\r\n<p><a href=\"https://vinasupport.com/tag/prismjs/\">Prism</a> là 1&nbsp;syntax \r\nhighlighter nhẹ và có tính mở rộng, được xây dựng với tiêu chuẩn web \r\nhiện đại. Nó được sử dụng trong hàng ngàn trang web, bao gồm một số \r\ntrang bạn truy cập hàng ngày như Drupan, React…</p>\r\n<ul><li><strong>Website</strong>:&nbsp;<a href=\"https://prismjs.com/\" target=\"_blank\" rel=\"external nofollow noopener\">https://prismjs.com/</a></li><li><strong>Download</strong>:&nbsp;<a href=\"https://prismjs.com/download.html\" target=\"_blank\" rel=\"external nofollow noopener\">https://prismjs.com/download.html</a></li></ul>\r\n<p><img class=\"alignnone size-full wp-image-930\" src=\"https://vinasupport.com/uploads/2019/04/Prism-is-a-lightweight-syntax-highlighter.jpg\" alt=\"\" style=\"width: 50%;\" width=\"978\" height=\"496\"></p>\r\n<h2>3. Google Code Prettify</h2>\r\n<p>Đây là 1 <a href=\"https://vinasupport.com/tag/code-highlighter/\">Code Highlighter</a> do Google và cộng đồng <a href=\"https://vinasupport.com/tag/stack-overflow/\">Stack Overflow</a> phát triển, support nhiều ngôn ngữ lập trình, trình duyệt và nhiều skin.</p>\r\n<ul><li><strong>Website</strong>:&nbsp;<a href=\"https://github.com/google/code-prettify\" target=\"_blank\" rel=\"external nofollow noopener\">https://github.com/google/code-prettify</a></li><li><strong>Demo</strong>:&nbsp;<a href=\"https://raw.githack.com/google/code-prettify/master/examples/quine.html\" target=\"_blank\" rel=\"external nofollow noopener\">https://raw.githack.com/google/code-prettify/master/examples/quine.html</a></li></ul>\r\n<h2>4.&nbsp;Rainbow</h2>\r\n<p><a href=\"https://vinasupport.com/tag/rainbow/\">Rainbow</a> là một thư\r\n viện Code Highlighting được viết bằng Javascript. Nó được thiết kế để \r\ncó kích thước (1,4kb), dễ sử dụng và có thể mở rộng. Nó hoàn toàn có thể\r\n sử dụng thông qua CSS. Nó hỗ trợ hiện tại là 21 ngôn ngữ lập trình và \r\n20 skin.</p>\r\n<ul><li><strong>Website</strong>:&nbsp;<a href=\"https://craig.is/making/rainbows\" target=\"_blank\" rel=\"external nofollow noopener\">https://craig.is/making/rainbows</a></li><li><strong>Github</strong>:&nbsp;<a href=\"https://github.com/ccampbell/rainbow\" target=\"_blank\" rel=\"external nofollow noopener\">https://github.com/ccampbell/rainbow</a></li></ul>\r\n<p><img class=\"alignnone size-full wp-image-931\" src=\"https://vinasupport.com/uploads/2019/04/Rainbow-Your-code-is-beautiful.png\" alt=\"\" style=\"width: 50%;\" width=\"984\" height=\"726\"></p>\r\n<h2>5.&nbsp;SHJS</h2>\r\n<p><a href=\"https://vinasupport.com/tag/shjs/\">SHJS</a> là một thư viện \r\nJavaScript làm nổi bật các đoạn mã nguồn trong các trang HTML. Nó hỗ trợ\r\n 39 ngôn ngữ lập trình và 40 theme khác nhau.</p>\r\n<ul><li><strong>Website</strong>:&nbsp;<a href=\"http://shjs.sourceforge.net/\" target=\"_blank\" rel=\"external nofollow noopener\">http://shjs.sourceforge.net/</a></li><li><strong>Download</strong>:&nbsp;<a href=\"http://shjs.sourceforge.net/doc/download.html\" target=\"_blank\" rel=\"external nofollow noopener\">http://shjs.sourceforge.net/doc/download.html</a></li></ul>\r\n<p><img class=\"alignnone wp-image-933 size-full\" src=\"https://vinasupport.com/uploads/2019/04/SHJS-Syntax-Highlighting-in-JavaScript0A.png\" alt=\"\" style=\"width: 50%;\" width=\"998\" height=\"612\"></p>\r\n<p style=\"text-align: right;\">Nguồn: vinasupport.com</p>\r\n              </div>',NULL,12,NULL,'2020-03-01 00:00:00',NULL,NULL,0,'top-5-thu-vien-javascript-code-highlighter-tot-nhat-1607689687874'),(50,' Upload file tới Amazon S3 sử dụng Python 3 ',100000.00,10000.00,'Bài viết này mình sẽ hướng dẫn các bạn upload file, hình ảnh tới Amazon S3 – Dịch vụ lưu trữ file nổi tiếng của Amazon. Để thực hiện các bạn vui lòng tham khảo các bài viết sau:','<div class=\"section entry-content pstyle\">\r\n              <p>Bài viết này mình sẽ hướng dẫn các bạn upload file, hình ảnh tới <a href=\"https://vinasupport.com/tag/amazon-s3/\">Amazon S3</a> – Dịch vụ lưu trữ file nổi tiếng của <a href=\"https://vinasupport.com/tag/amazon/\">Amazon</a>. Để thực hiện các bạn vui lòng tham khảo các bài viết sau:</p>\r\n<ul><li><a href=\"https://vinasupport.com/amazon-s3-la-gi-tao-va-quan-ly-bucket-tren-amazon-s3/\">Giới thiệu Amazon S3, tạo và quản lý bucket</a></li><li><a href=\"https://vinasupport.com/aws-huong-dan-tao-va-quan-ly-tai-khoan-iam/\">Tạo tài khoản AMI để lấy Access Key và Secret Key</a></li></ul>\r\n<h2>Cài đặt thư viện boto3 của Amazon Web Services</h2>\r\n<p>Để cài đặt thư viện boto3 chúng ta sử dụng công cụ quản lý <a href=\"https://vinasupport.com/tag/pip3/\">pip3</a> của Python 3</p>\r\n<div class=\"EnlighterJSWrapper mochaEnlighterJSWrapper\"><ul class=\"hoverEnabled mochaEnlighterJS EnlighterJS\"><li class=\" odd\"><span class=\"\">pip3 install boto3</span></li></ul></div>\r\n<p><img class=\"alignnone size-full wp-image-2200\" src=\"https://vinasupport.com/uploads/2020/10/Cai-Dat-Thu-Vien-Boto3.png\" alt=\"\" style=\"width: 50%;\" width=\"808\" height=\"414\"></p>\r\n<p>Kiểm tra version sau khi cài đặt bằng lệnh pip3 show boto3</p>\r\n<p><img class=\"alignnone size-full wp-image-2201\" src=\"https://vinasupport.com/uploads/2020/10/Kiem-Tra-Thu-Vien-Boto3-Sau-Khi-Cai-Dat.png\" alt=\"\" style=\"width: 50%;\" width=\"808\" height=\"191\"></p>\r\n<h2>Upload file tới Amazon S3 sử dụng Python 3</h2>\r\n<p>Để upload file chúng ta cần các thông tin sau:</p>\r\n<ul><li>&lt;ENDPOINT_URL&gt;: Đường dẫn API của Amazon S3. VD: https://s3.<span class=\"_1ViyaxyY9E49OGbzIQbuwY\"><span class=\"_1J0EhdaCU7pFwHlcvIkyKq\">ap-northeast-1</span></span>.amazonaws.com. Với&nbsp;<span class=\"_1ViyaxyY9E49OGbzIQbuwY\"><span class=\"_1J0EhdaCU7pFwHlcvIkyKq\"><strong>ap-northeast-1</strong> là region mà bạn muốn upload tới.&nbsp;</span></span></li><li>&lt;ACCESS_KEY&gt;: Access Key</li><li>&lt;SECRET_KEY&gt;: Secret Key</li><li>&lt;BUCKET_NAME&gt;: Tên của bucket</li></ul>\r\n<p>Source python 3 để upload</p>\r\n<div class=\"EnlighterJSWrapper godzillaEnlighterJSWrapper\"><ol class=\"hoverEnabled godzillaEnlighterJS EnlighterJS\"><li class=\" odd\"><span class=\"co1\">#!/usr/bin/python3</span><span class=\"\"></span></li><li class=\" even\"><span class=\"\"></span><span class=\"kw1\">import</span><span class=\"\"> os</span></li><li class=\" odd\"><span class=\"\"></span><span class=\"kw1\">import</span><span class=\"\"> boto3</span></li><li class=\" even\"><span class=\"\"></span><span class=\"kw1\">import</span><span class=\"\"> mimetypes</span></li><li class=\" odd\"><span class=\"\"></span><span class=\"kw1\">from</span><span class=\"\"> datetime </span><span class=\"kw1\">import</span><span class=\"\"> datetime</span></li><li class=\" even\"><span class=\"\"></span><br></li><li class=\" odd\"><span class=\"\">image_path = </span><span class=\"st0\">\'/home/vinasupport/Pictures/vinasupport.jpg\'</span><span class=\"\"></span></li><li class=\" even\"><span class=\"\">endpoint_url = </span><span class=\"st0\">\'&lt;ENDPOINT_URL&gt;\'</span><span class=\"\"></span></li><li class=\" odd\"><span class=\"\">access_key = </span><span class=\"st0\">\'&lt;ACCESS_KEY&gt;\'</span><span class=\"\"></span></li><li class=\" even\"><span class=\"\">secret_key = </span><span class=\"st0\">\'&lt;SECRET_KEY&gt;\'</span><span class=\"\"></span></li><li class=\" odd\"><span class=\"\">region = </span><span class=\"st0\">\'&lt;REGION_NAME&gt;\'</span><span class=\"\"></span></li><li class=\" even\"><span class=\"\">bucket_name = </span><span class=\"st0\">\'vinasupport.com\'</span><span class=\"\"></span></li><li class=\" odd\"><span class=\"\">app_url = </span><span class=\"st0\">\'https://s3-ap-northeast-1.amazonaws.com/vinasupport.com\'</span><span class=\"\"></span></li><li class=\" even\"><span class=\"\"></span><br></li><li class=\" odd\"><span class=\"\"></span><br></li><li class=\" even\"><span class=\"\"></span><span class=\"co1\"># Create Session</span><span class=\"\"></span></li><li class=\" odd\"><span class=\"\">session = boto3.</span><span class=\"kw3\">Session</span><span class=\"br0\">(</span><span class=\"\"></span></li><li class=\" even\"><span class=\"\">    region_name=region,</span></li><li class=\" odd\"><span class=\"\">    aws_access_key_id=access_key,</span></li><li class=\" even\"><span class=\"\">    aws_secret_access_key=secret_key,</span></li><li class=\" odd\"><span class=\"\"></span><span class=\"br0\">)</span><span class=\"\"></span></li><li class=\" even\"><span class=\"\"></span><br></li><li class=\" odd\"><span class=\"\"></span><span class=\"co1\"># Get bucket resource</span><span class=\"\"></span></li><li class=\" even\"><span class=\"\">s3 = session.</span><span class=\"me0\">resource</span><span class=\"br0\">(</span><span class=\"st0\">\'s3\'</span><span class=\"\">, endpoint_url=endpoint_url</span><span class=\"br0\">)</span><span class=\"\"></span></li><li class=\" odd\"><span class=\"\">bucket = s3.</span><span class=\"kw3\">Bucket</span><span class=\"br0\">(</span><span class=\"\">bucket_name</span><span class=\"br0\">)</span><span class=\"\"></span></li><li class=\" even\"><span class=\"\"></span><span class=\"co1\"># bucket.put_object(Key=\'Test/\')</span><span class=\"\"></span></li><li class=\" odd\"><span class=\"\">image_name = os.</span><span class=\"me0\">path</span><span class=\"\">.</span><span class=\"me0\">basename</span><span class=\"br0\">(</span><span class=\"\">image_path</span><span class=\"br0\">)</span><span class=\"\"></span></li><li class=\" even\"><span class=\"\">date_now = datetime.</span><span class=\"me0\">now</span><span class=\"br0\">(</span><span class=\"br0\">)</span><span class=\"\"></span></li><li class=\" odd\"><span class=\"\">upload_path = </span><span class=\"st0\">\"uploads/{:%Y/%m/%d}/{}\"</span><span class=\"\">.</span><span class=\"kw2\">format</span><span class=\"br0\">(</span><span class=\"\">date_now, image_name</span><span class=\"br0\">)</span><span class=\"\"></span></li><li class=\" even\"><span class=\"\">upload_url = </span><span class=\"st0\">\"{}/uploads/{:%Y/%m/%d}/{}\"</span><span class=\"\">.</span><span class=\"kw2\">format</span><span class=\"br0\">(</span><span class=\"\">app_url, date_now, image_name</span><span class=\"br0\">)</span><span class=\"\"></span></li><li class=\" odd\"><span class=\"\">content_type = mimetypes.</span><span class=\"me0\">guess_type</span><span class=\"br0\">(</span><span class=\"\">image_path</span><span class=\"br0\">)</span><span class=\"\"></span></li><li class=\" even\"><span class=\"\">s3.</span><span class=\"me0\">meta</span><span class=\"\">.</span><span class=\"me0\">client</span><span class=\"\">.</span><span class=\"me0\">upload_file</span><span class=\"br0\">(</span><span class=\"\">image_path, bucket_name, upload_path, </span><span class=\"kw3\">ExtraArgs</span><span class=\"\">=</span><span class=\"br0\">{</span><span class=\"\"></span></li><li class=\" odd\"><span class=\"\">    </span><span class=\"st0\">\'ACL\'</span><span class=\"\">: </span><span class=\"st0\">\'public-read\'</span><span class=\"\">,</span></li><li class=\" even\"><span class=\"\">    </span><span class=\"st0\">\'ContentType\'</span><span class=\"\">: content_type</span><span class=\"br0\">[</span><span class=\"nu0\">0</span><span class=\"br0\">]</span><span class=\"\"></span></li><li class=\" odd\"><span class=\"\"></span><span class=\"br0\">}</span><span class=\"br0\">)</span><span class=\"\"></span></li><li class=\" even\"><span class=\"\"></span><br></li><li class=\" odd\"><span class=\"\"></span><span class=\"co1\"># List files and folders</span><span class=\"\"></span></li><li class=\" even\"><span class=\"\"></span><span class=\"kw1\">for</span><span class=\"\"> obj </span><span class=\"kw1\">in</span><span class=\"\"> bucket.</span><span class=\"me0\">objects</span><span class=\"\">.</span><span class=\"kw2\">filter</span><span class=\"br0\">(</span><span class=\"kw3\">Prefix</span><span class=\"\">=</span><span class=\"st0\">\'uploads/\'</span><span class=\"br0\">)</span><span class=\"\">:</span></li><li class=\" odd\"><span class=\"\">    </span><span class=\"kw1\">print</span><span class=\"br0\">(</span><span class=\"st0\">\'{0}:{1}\'</span><span class=\"\">.</span><span class=\"kw2\">format</span><span class=\"br0\">(</span><span class=\"\">bucket.</span><span class=\"me0\">name</span><span class=\"\">, obj.</span><span class=\"me0\">key</span><span class=\"br0\">)</span><span class=\"br0\">)</span></li></ol></div>\r\n<p>Kết quả:</p>\r\n<p>Bạn đã upload file thành công đến bucket: <strong>vinasupport.com</strong></p>\r\n<p><img class=\"alignnone size-full wp-image-2254\" src=\"https://vinasupport.com/uploads/2020/11/Upload-File-Bang-Python-3-Toi-Amazon-S3-Thanh-Cong.png\" alt=\"\" style=\"width: 50%;\" width=\"835\" height=\"218\"></p>\r\n<p>Trên giao diện quản lý</p>\r\n<p><img class=\"alignnone size-full wp-image-2255\" src=\"https://vinasupport.com/uploads/2020/11/File-Upload-Tren-Amazon-S3.png\" alt=\"\" style=\"width: 50%;\" width=\"1010\" height=\"813\"></p>\r\n<div id=\"gtx-trans\" style=\"position: absolute; left: -12px; top: 1240.62px;\">\r\n\r\n</div>\r\n              </div>\r\n              <div>\r\n    <ins class=\"adsbygoogle\" style=\"display: block; height: 421px;\" data-ad-format=\"autorelaxed\" data-ad-client=\"ca-pub-6107623425930461\" data-ad-slot=\"7549293825\" data-adsbygoogle-status=\"done\"><ins id=\"aswift_1_expand\" style=\"display:inline-table;border:none;height:421px;margin:0;padding:0;position:relative;visibility:visible;width:703px;background-color:transparent;\"><ins id=\"aswift_1_anchor\" style=\"display:block;border:none;height:421px;margin:0;padding:0;position:relative;visibility:visible;width:703px;background-color:transparent;\"></ins></ins></ins>\r\n    \r\n</div>\r\n ',NULL,15,NULL,NULL,NULL,NULL,1,'upload-file-toi-amazon-s3-su-dung-python-3-1606908364178');
/*!40000 ALTER TABLE `tbl_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_products_images`
--

DROP TABLE IF EXISTS `tbl_products_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_products_images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `product_id` int(11) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `status` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_product_images_idx` (`product_id`),
  CONSTRAINT `fk_product_images` FOREIGN KEY (`product_id`) REFERENCES `tbl_products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='Bảng dữ liệu danh sách ảnh sản phẩm';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_products_images`
--

LOCK TABLES `tbl_products_images` WRITE;
/*!40000 ALTER TABLE `tbl_products_images` DISABLE KEYS */;
INSERT INTO `tbl_products_images` VALUES (20,'HighlightJs-Syntax-highlighting-for-the-Web.png','HighlightJs-Syntax-highlighting-for-the-Web.png',49,NULL,NULL,NULL,NULL,0),(21,'Cai-Dat-Thu-Vien-Boto3.png','Cai-Dat-Thu-Vien-Boto3.png',50,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `tbl_products_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_roles`
--

DROP TABLE IF EXISTS `tbl_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `description` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `status` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='Bảng dữ liệu chứa thông tin roles';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_roles`
--

LOCK TABLES `tbl_roles` WRITE;
/*!40000 ALTER TABLE `tbl_roles` DISABLE KEYS */;
INSERT INTO `tbl_roles` VALUES (7,'ADMIN','ADMIN',NULL,NULL,NULL,NULL,1),(8,'GUEST','GUEST',NULL,NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `tbl_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_saleorder`
--

DROP TABLE IF EXISTS `tbl_saleorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_saleorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `total` decimal(13,2) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `status` tinyint(1) DEFAULT '1',
  `customer_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `customer_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `seo` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `customer_phone` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `cutomer_email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='Bảng dữ liệu chứa phiếu mua hàng';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_saleorder`
--

LOCK TABLES `tbl_saleorder` WRITE;
/*!40000 ALTER TABLE `tbl_saleorder` DISABLE KEYS */;
INSERT INTO `tbl_saleorder` VALUES (7,'1606312487050',NULL,200000.00,'2020-11-25 20:54:47',NULL,NULL,NULL,0,'Nguyễn Văn A','Hà Nội',NULL,NULL,NULL),(8,'1606312582507',NULL,200000.00,'2020-11-25 20:56:23',NULL,NULL,NULL,0,'Nguyễn Văn A','Hà Nội',NULL,NULL,NULL);
/*!40000 ALTER TABLE `tbl_saleorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_saleorder_products`
--

DROP TABLE IF EXISTS `tbl_saleorder_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_saleorder_products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `saleorder_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quality` int(11) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `status` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_saleorder_product_idx` (`saleorder_id`),
  CONSTRAINT `fk_saleorder_product` FOREIGN KEY (`saleorder_id`) REFERENCES `tbl_saleorder` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='Bảng dữ liệu chứa phiếu mua hàng';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_saleorder_products`
--

LOCK TABLES `tbl_saleorder_products` WRITE;
/*!40000 ALTER TABLE `tbl_saleorder_products` DISABLE KEYS */;
INSERT INTO `tbl_saleorder_products` VALUES (11,7,49,2,'2020-11-25 20:54:47',NULL,NULL,NULL,0),(12,7,50,2,'2020-11-25 20:54:47',NULL,NULL,NULL,0),(13,8,49,1,'2020-11-25 20:56:23',NULL,NULL,NULL,0),(14,8,50,1,'2020-11-25 20:56:23',NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `tbl_saleorder_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_users`
--

DROP TABLE IF EXISTS `tbl_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `status` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='Bảng dữ liệu chứa thông tin người dùng';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_users`
--

LOCK TABLES `tbl_users` WRITE;
/*!40000 ALTER TABLE `tbl_users` DISABLE KEYS */;
INSERT INTO `tbl_users` VALUES (7,'guest','$2a$08$L.03fm/tpKoXvxlkpoqfdOUmUHu.Kdc8ucOJwRvyw2OKvR0u5rX3u','guest@abc.com',NULL,NULL,NULL,NULL,1),(8,'admin','$2a$08$VDmodElsmH5fOaGt6sTqCui0d3D.aurg9pldQTUiAvIgPLg60u5.q','amdin@abc.com',NULL,NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `tbl_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_users_roles`
--

DROP TABLE IF EXISTS `tbl_users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `fk_roles_idx` (`role_id`),
  CONSTRAINT `fk_roles` FOREIGN KEY (`role_id`) REFERENCES `tbl_roles` (`id`),
  CONSTRAINT `fk_users` FOREIGN KEY (`user_id`) REFERENCES `tbl_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='bảng trung gian thể hiện quan hệ n-n của users và roles';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_users_roles`
--

LOCK TABLES `tbl_users_roles` WRITE;
/*!40000 ALTER TABLE `tbl_users_roles` DISABLE KEYS */;
INSERT INTO `tbl_users_roles` VALUES (8,7),(7,8);
/*!40000 ALTER TABLE `tbl_users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-20 19:51:58
