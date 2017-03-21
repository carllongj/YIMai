SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ym_category
-- ----------------------------
DROP TABLE IF EXISTS `ym_category`;
CREATE TABLE `ym_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类表的主键',
  `name` varchar(20) NOT NULL COMMENT '分类名称',
  `status` int(1) NOT NULL COMMENT '当前分类信息的状态是否可用 1为可用 0为不可用',
  `uid` varchar(32) DEFAULT NULL COMMENT '修改或者增加分类信息的管理员的id',
  `icon` varchar(20) NOT NULL COMMENT '图标的名称',
  `created` date NOT NULL,
  `updated` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42898361594380821 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ym_category
-- ----------------------------
INSERT INTO `ym_category` VALUES ('42834901508414696', '手机', '1', null, 'mobile', '2017-01-05', '2017-01-05 20:16:46');
INSERT INTO `ym_category` VALUES ('42898361594339020', '汽车', '1', null, 'car', '2017-03-01', '2017-03-03 14:27:39');
INSERT INTO `ym_category` VALUES ('42898361594380820', '电子', '1', null, 'laptop', '2017-01-05', '2017-01-05 20:16:15');

-- ----------------------------
-- Table structure for ym_item
-- ----------------------------
DROP TABLE IF EXISTS `ym_item`;
CREATE TABLE `ym_item` (
  `id` varchar(32) NOT NULL COMMENT '商品的唯一标识',
  `title` varchar(50) NOT NULL COMMENT '商品的概述信息',
  `price` int(11) NOT NULL COMMENT '商品的价格',
  `uid` varchar(32) NOT NULL COMMENT '商品的出售者的id',
  `status` int(1) NOT NULL COMMENT '商品的状态 0,待售 1:已被拍下,待付款 2:已交易',
  `cateid` bigint(20) NOT NULL COMMENT '分类的唯一标识',
  `descid` varchar(32) DEFAULT NULL COMMENT '对商品的要点描述',
  `image` varchar(500) DEFAULT NULL COMMENT '商品的图片',
  `pass_status` int(1) NOT NULL COMMENT '该商品的信息通过审核状态 0:未通过 1.已通过',
  `editor` varchar(32) DEFAULT NULL COMMENT '编辑商品的管理员的id',
  `created` datetime DEFAULT NULL COMMENT '商品的创建时间',
  `updated` datetime DEFAULT NULL COMMENT '商品的更新时间',
  PRIMARY KEY (`id`),
  KEY `FK_ym_item` (`uid`),
  KEY `FK_ym_item_desc` (`descid`),
  KEY `FK_ym_item_category` (`cateid`),
  CONSTRAINT `FK_ym_item` FOREIGN KEY (`uid`) REFERENCES `ym_user` (`id`),
  CONSTRAINT `FK_ym_item_category` FOREIGN KEY (`cateid`) REFERENCES `ym_category` (`id`),
  CONSTRAINT `FK_ym_item_desc` FOREIGN KEY (`descid`) REFERENCES `ym_item_desc` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ym_item
-- ----------------------------
INSERT INTO `ym_item` VALUES ('098D2878C3E140C6B9E1509C8FFC88F2', '的撒反抗就啊哈苏德就恢复看了就啊哈', '700000', '123456789', '0', '42834901508414696', 'F7030B9B80EB4D6F8DF02E49C9248012', 'http://115.159.187.114/d/c/2F9F7F492AE94537BA8D92E206F79B92_m11.jpg', '1', null, '2017-03-08 10:45:49', '2017-03-08 10:45:49');
INSERT INTO `ym_item` VALUES ('1FC5BAE808DE40EE9B4476DF8A11DDCA', '蓝牙 耳机', '20000', '123456789', '0', '42898361594380820', 'BE9A420C24814FF19E8B9A2A8300FE51', 'http://115.159.187.114/a/f/365038DD1F1D411FA6FD805602DC2118_e3.jpg', '1', null, '2017-03-06 09:47:26', '2017-03-06 09:47:26');
INSERT INTO `ym_item` VALUES ('23D989C20C4147CD878EC370C6396F2F', 'dfhkjasdhfh', '100000', '123456789', '1', '42834901508414696', '20AAA7A09DBA4E8F95D42791902D98C7', 'http://115.159.187.114/b/0/214F31E1EE3A47AC9F8CB6F2D3A3B4A1_f1.jpg', '1', null, '2017-03-08 10:43:14', '2017-03-08 10:43:14');
INSERT INTO `ym_item` VALUES ('298B607DEA0249EEAE614F635B7EF3FF', '诺基亚 N9', '300000', '123456789', '0', '42834901508414696', '3D6455983CC344279875242E6C19FAEF', 'http://115.159.187.114/c/2/8B57424A2A20421EB35DC7AFAA5AD855_p3.jpg', '1', null, '2017-03-05 10:33:17', '2017-03-05 10:33:17');
INSERT INTO `ym_item` VALUES ('32BF4B0A6FDC483789D76A90D165FB38', 'asdjfhwjerbkqjhwekjrhkqwerqwer', '12300', '123456789', '1', '42834901508414696', '58776E8454974269B95DB289AB5FF728', 'http://115.159.187.114/b/d/AC93701F6A1042C4AF33AB07E9AFF25E_m5.jpg', '1', null, '2017-03-08 10:43:53', '2017-03-08 10:43:53');
INSERT INTO `ym_item` VALUES ('48E2693A04334186BC36114F3835CCFC', '奔驰C200', '15000000', '123456789', '0', '42898361594339020', '7C2E1259234F426CAF0E28DD82B1084B', 'http://115.159.187.114/a/c/5DD9CC7A012947579BA253F9FB9E805B_c7.jpg', '1', null, '2017-03-06 09:42:15', '2017-03-06 09:42:15');
INSERT INTO `ym_item` VALUES ('7965FE646CAE4426AA05BD5CC355A99C', '倒计时弗拉季考核结束款东风科技手段发', '13213', '123456789', '0', '42834901508414696', 'DA8AFA4D4DA64D8E96033984D353966B', 'http://115.159.187.114/b/c/0065AFAB968E4048848587FDE8CCF6CA_m3.jpg', '1', null, '2017-03-08 10:45:11', '2017-03-08 10:45:11');
INSERT INTO `ym_item` VALUES ('831FEC407CCB422BAC9DAEBE97DD7AA9', '撒旦法艰苦环境宽松的累计核发类库', '123400', '123456789', '0', '42834901508414696', '1B582E38BC6A4D7EADD5F4E86EA1BB16', 'http://115.159.187.114/b/d/3C6DA8E2A49047C8A5F4327470FE4F01_m8.jpg', '1', null, '2017-03-08 10:44:35', '2017-03-08 10:44:35');
INSERT INTO `ym_item` VALUES ('8FFB2E0ADBC64561AA827D26AC99DD45', 'HTC ONE', '329900', '123456789', '0', '42834901508414696', 'C098CDB9B1CC48F4984B7DF952331F2A', 'http://115.159.187.114/7/b/4EF4B47EA5294EC593B27205F1349B8D_p11.jpg', '1', null, '2017-03-05 10:36:33', '2017-03-05 10:36:33');
INSERT INTO `ym_item` VALUES ('9050ADA205D34280A8527F0322CF657C', '三星 Galaxy S7', '150000', '123456789', '0', '42834901508414696', 'B2363DB1A53A4946ACE1ED8EC1873B97', 'http://115.159.187.114/2/c/30A5FF3F055F445AB57D95A895940905_1.jpg', '1', null, '2017-03-05 10:23:54', '2017-03-05 10:23:54');
INSERT INTO `ym_item` VALUES ('A03512C3B8E64C4F8BCA1FE537DFF9D3', 'Galaxy S7将会采用无边框设计，是真正意义上的', '250000', '123456789', '0', '42834901508414696', 'FC2B9C720DDE4C6A9D11B2BE8A65AA1B', 'http://115.159.187.114/2/c/9CE6571DE5234E67BF9D697CF50B442A_1.jpg', '1', null, '2017-03-05 10:27:26', '2017-03-05 10:27:26');
INSERT INTO `ym_item` VALUES ('E344BF7CEB7647798FE1A89B10FAFE19', '三星 Galaxy S7', '200000', '123456789', '0', '42834901508414696', 'E75ABE8166804D44B08E1A86BB27E8C8', 'http://115.159.187.114/2/c/C6912BAD372F49EFB96751AED357DA52_1.jpg', '1', null, '2017-03-05 10:23:16', '2017-03-05 10:23:16');

-- ----------------------------
-- Table structure for ym_item_desc
-- ----------------------------
DROP TABLE IF EXISTS `ym_item_desc`;
CREATE TABLE `ym_item_desc` (
  `id` varchar(32) NOT NULL COMMENT '商品详细描述的主键id',
  `content` text NOT NULL,
  `status` int(1) NOT NULL COMMENT '商品详细描述的状态,0.待审核 1.可用 2.已删除',
  `editor` varchar(32) DEFAULT NULL COMMENT '审核或者编辑者的管理员的id',
  `created` date DEFAULT NULL COMMENT '此商品描述创建的时间',
  `updated` datetime DEFAULT NULL COMMENT '此商品描述更新的时间',
  `item_id` varchar(32) NOT NULL COMMENT '关联的商品的id',
  PRIMARY KEY (`id`),
  KEY `FK_ym_desc_item` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ym_item_desc
-- ----------------------------
INSERT INTO `ym_item_desc` VALUES ('1B582E38BC6A4D7EADD5F4E86EA1BB16', '使得副科级啊黑客技术东风科技哈斯看见对方会看见撒旦海口警方看见撒旦法哈苏德分速读法dfs电风扇', '0', null, '2017-03-08', '2017-03-08 10:44:35', '831FEC407CCB422BAC9DAEBE97DD7AA9');
INSERT INTO `ym_item_desc` VALUES ('20AAA7A09DBA4E8F95D42791902D98C7', 'dsjfaiuwehrikljsehrfkjasdhkjfhwkeujrqkjerkjhkjsdfasdqrewrq', '0', null, '2017-03-08', '2017-03-08 10:43:14', '23D989C20C4147CD878EC370C6396F2F');
INSERT INTO `ym_item_desc` VALUES ('3D6455983CC344279875242E6C19FAEF', '诺基亚于2011年11月发布诺基亚N9。诺基亚N9采用了一块3.9英寸电容触摸屏，分辨率为480x854，手机的显示效果在2011年可以算非常出色，在2011年的诺基亚众多手机中处于一流水平。手机采用德州仪器OMAP3630 处理器，拥有1GB RAM以及16GB的机身存储空间，诺基亚N9采用了MeeGo1.2 Harmattan操作系统。2011年9月28日，继诺基亚宣布放弃Meego之后，英特尔正式宣布放弃Meego，诺基亚N9成为唯一一台Meego手机。\r\n诺基亚N9是一款充满悲情色彩的手机,该机是诺基亚转战WP7系统之前的第一款也是最后一款MEEGO系统，有着非常漂亮的外形，极致超薄，性能表现不俗。\r\n诺基亚N9屏幕方面是使用了一块3.9英寸AMOLED材质的电容屏幕，支持多点触控，并且显示效果也很让人满意。分辨率达到了480×854像素，从分辨率来讲，已经达到诺基亚当时全部机型中的最高水平。机身搭载的是全球首款MeeGo操作系统，这款系统是塞班、WP7、Android以及iOS之外的一款新系统，有一些类似塞班的图标加上Android与WP7的融合后的触控感', '0', null, '2017-03-05', '2017-03-05 10:33:17', '298B607DEA0249EEAE614F635B7EF3FF');
INSERT INTO `ym_item_desc` VALUES ('58776E8454974269B95DB289AB5FF728', 'dsfwh李弘基文件和人口i撒旦法i建立快速扩大非i江苏大丰哦几sad缺乏维权人士的', '0', null, '2017-03-08', '2017-03-08 10:43:53', '32BF4B0A6FDC483789D76A90D165FB38');
INSERT INTO `ym_item_desc` VALUES ('7C2E1259234F426CAF0E28DD82B1084B', '先看BENZ C200：离我家住不远就有很多汽车店，LEXUS、BENZ、BMW都有，而是无聊就进去看看，离的最近的就是BENZ店，进门就直奔C级。那是我第一次看到C200，或许那是一种一见钟情的感觉。BENZ的销售人员很热情，耐心介绍了一番后，让我试架车，上车一番熟悉后，便让我使劲踩油门不要怕。于是我使劲踩了下去，作为1.8机械增压的发动力，虽然不如BMW那么狂暴，但加速起来确实也把我老婆给吓住了，加速非常猛。当然对于总开3.0车的人自然不会激动，但是相比我以前的1.3那确实是天上地下。另外，小C的方向盘十分轻，我觉得很舒服', '0', null, '2017-03-06', '2017-03-06 09:42:15', '48E2693A04334186BC36114F3835CCFC');
INSERT INTO `ym_item_desc` VALUES ('B2363DB1A53A4946ACE1ED8EC1873B97', '三星Galaxy S7是三星公司2016年2月22日在巴塞罗那发布的新产品，配置Exynos 8890处理器版本和骁龙820版本，搭载4GB运行内存以及32/64GB UFS2.0机身存储空间，支持存储卡扩展，摄像头像素缩水至1200万像素，最大光圈能够达到F1.7，单个像素面积从S6的 1.2μm提升到1.4μm，从而在弱光环境下具备更大的画质优势，并能够快速对焦', '0', null, '2017-03-05', '2017-03-05 10:23:54', '9050ADA205D34280A8527F0322CF657C');
INSERT INTO `ym_item_desc` VALUES ('BE9A420C24814FF19E8B9A2A8300FE51', '蓝牙耳机就是将蓝牙技术应用在免持耳机上，让使用者可以免除恼人电线的牵绊，自在地以各种方式轻松通话。自从蓝牙耳机问世以来，一直是行动商务族提升效率的好工具。\r\n蓝牙是一种低成本大容量的短距离无线通信规范。蓝牙笔记本电脑，就是具有蓝牙无线通信功能的笔记本电脑。蓝牙这个名字还有一段传奇故事呢。公元10世纪，北欧诸侯争霸，丹麦国王挺身而出，在他的不懈努力下，血腥的战争被制止了，各方都坐到了谈判桌前。通过沟通，诸侯们冰释前嫌，成为朋友。由 于丹麦国王酷爱吃蓝梅，以至于牙齿都被染成了蓝色，人称蓝牙国王，所以，蓝牙也就成了沟通的代名词。一千年后的今天，当新的无线通信规范出台时，人们又用蓝牙来为它命名。1995年，爱立信公司最先提出蓝牙概念。蓝牙规范采用微波频段工作，传输速率每秒1M字节，最大传输距离10米，通过增加发射功率可达到100米。蓝牙技术是全球开放的，在全球范围内具有很好的兼容性，全世界可以通过低成本的无形蓝牙网连成一体', '0', null, '2017-03-06', '2017-03-06 09:47:26', '1FC5BAE808DE40EE9B4476DF8A11DDCA');
INSERT INTO `ym_item_desc` VALUES ('C098CDB9B1CC48F4984B7DF952331F2A', 'The New HTC One是HTC于2013年2月19日发布的新一代旗舰型智能手机，是全球首款全金属智能手机，属于HTC One系列之中的高阶旗舰机型，代号为M7。\r\n新HTC One配备4.7英寸1920×1080像素的屏幕，解析度高达468ppi，搭载Android 4.1.2操作系统，采用了全新的HTC Sense 5界面和超像素Ultrapixel摄像头。[1] \r\nHTC One配备了精心打造的全金属外壳，整个手机拥有干净的线条，机身厚度9.3毫米（最薄处仅4mm），同时相对锋利的边角也带让HTC One显得更加干练和简约，极具美感。\r\n2013年12月，美国知名科技媒体《商业内幕》整理出了“2013年度最具创新力的十大设备”，HTC ONE位列其中', '0', null, '2017-03-05', '2017-03-05 10:36:33', '8FFB2E0ADBC64561AA827D26AC99DD45');
INSERT INTO `ym_item_desc` VALUES ('DA8AFA4D4DA64D8E96033984D353966B', '的撒反抗计划的撒反抗就阿隆索的健康附近卢卡斯的你发撒旦解放地煞符客家话放大了看见撒旦法', '0', null, '2017-03-08', '2017-03-08 10:45:11', '7965FE646CAE4426AA05BD5CC355A99C');
INSERT INTO `ym_item_desc` VALUES ('E75ABE8166804D44B08E1A86BB27E8C8', '三星Galaxy S7是三星公司2016年2月22日在巴塞罗那发布的新产品，配置Exynos 8890处理器版本和骁龙820版本，搭载4GB运行内存以及32/64GB UFS2.0机身存储空间，支持存储卡扩展，摄像头像素缩水至1200万像素，最大光圈能够达到F1.7，单个像素面积从S6的 1.2μm提升到1.4μm，从而在弱光环境下具备更大的画质优势，并能够快速对焦', '0', null, '2017-03-05', '2017-03-05 10:23:16', 'E344BF7CEB7647798FE1A89B10FAFE19');
INSERT INTO `ym_item_desc` VALUES ('F7030B9B80EB4D6F8DF02E49C9248012', '撒旦解放噶就会死得很哈师大建房快速拉动分散进口大黄蜂了看见后萎缩看见对方酷基金斯达康龙卷风了看见哈苏德发卡计划山东科技发火款i啊ujsdjhfkjhsdakjfh恐惧三大', '0', null, '2017-03-08', '2017-03-08 10:45:48', '098D2878C3E140C6B9E1509C8FFC88F2');
INSERT INTO `ym_item_desc` VALUES ('FC2B9C720DDE4C6A9D11B2BE8A65AA1B', '三星Galaxy S7是三星公司2016年2月22日在巴塞罗那发布的新产品，配置Exynos 8890处理器版本和骁龙820版本，搭载4GB运行内存以及32/64GB UFS2.0机身存储空间，支持存储卡扩展，摄像头像素缩水至1200万像素，最大光圈能够达到F1.7，单个像素面积从S6的 1.2μm提升到1.4μm，从而在弱光环境下具备更大的画质优势，并能够快速对焦', '0', null, '2017-03-05', '2017-03-05 10:27:26', 'A03512C3B8E64C4F8BCA1FE537DFF9D3');

-- ----------------------------
-- Table structure for ym_order
-- ----------------------------
DROP TABLE IF EXISTS `ym_order`;
CREATE TABLE `ym_order` (
  `id` bigint(20) NOT NULL COMMENT '订单的唯一标识',
  `status` int(1) NOT NULL COMMENT '订单的状态 0:被拍下 1:已付款 2:已完成',
  `sellerid` varchar(32) NOT NULL COMMENT '物品出售者的id',
  `buyerid` varchar(32) NOT NULL COMMENT '购买者的id',
  `price` int(10) NOT NULL COMMENT '购买的商品的价格',
  `image` varchar(20) DEFAULT NULL COMMENT '图片的信息',
  `comment` varchar(255) DEFAULT NULL COMMENT '用户的留言项',
  `expressid` int(15) DEFAULT NULL COMMENT '商品的物流信息id',
  `editor` varchar(32) DEFAULT NULL COMMENT '编辑信息的管理员的id',
  `created` datetime NOT NULL COMMENT '订单的创建的时间',
  `expire` datetime NOT NULL COMMENT '订单过期时间',
  `finish` datetime DEFAULT NULL COMMENT '订单交易完成时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ym_order
-- ----------------------------

-- ----------------------------
-- Table structure for ym_user
-- ----------------------------
DROP TABLE IF EXISTS `ym_user`;
CREATE TABLE `ym_user` (
  `id` varchar(32) NOT NULL COMMENT '用户的唯一标识',
  `username` varchar(20) NOT NULL COMMENT '用户登录的用户名',
  `passwd` varchar(50) NOT NULL COMMENT '用户登录的密码,使用MD5加密存储',
  `nickname` varchar(20) DEFAULT NULL COMMENT '用户的昵称,填入此项,在页面展示时显示此昵称',
  `phone` varchar(20) DEFAULT NULL,
  `birthday` date DEFAULT NULL COMMENT '用户的生日',
  `addressid` varchar(32) DEFAULT NULL,
  `email` varchar(30) NOT NULL COMMENT '用户的邮箱',
  `state` int(1) unsigned zerofill NOT NULL COMMENT '当前账户的状态 0为不可用,1为可用',
  `editor` varchar(32) DEFAULT NULL COMMENT '对用户进行信息管理的管理员的id',
  `created` datetime NOT NULL COMMENT '用户的注册时间',
  `updated` datetime NOT NULL COMMENT '用户更新信息时的时间',
  `admin` int(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT '是否为管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ym_user
-- ----------------------------
INSERT INTO `ym_user` VALUES ('0B900C8266C644538F16D502414AB0C7', 'apache', '123', null, null, null, null, 'carllongj@gmail.com', '0', null, '2017-01-03 18:52:20', '2017-01-03 18:52:20', '0');
INSERT INTO `ym_user` VALUES ('123456789', '123456789', '123456', null, '15551115151', null, null, '1234565@qq.com', '1', null, '2017-01-06 09:50:19', '2017-01-06 09:50:22', '0');
INSERT INTO `ym_user` VALUES ('26C87CE6FE0E4D3095DCDEF1A795EBF9', 'spring', 'b5d9c5e13ea249b23f4761d5ac2a3649', null, null, null, null, '354023264@qq.com', '1', null, '2017-03-09 15:36:03', '2017-03-09 15:36:03', '0');

-- ----------------------------
-- Table structure for ym_user_addr
-- ----------------------------
DROP TABLE IF EXISTS `ym_user_addr`;
CREATE TABLE `ym_user_addr` (
  `id` varchar(32) NOT NULL COMMENT '用户地址的唯一标识',
  `address_main` varchar(200) NOT NULL COMMENT '用户的默认地址',
  `address_second` varchar(200) DEFAULT NULL COMMENT '用户的备选地址1',
  `address_third` varchar(200) DEFAULT NULL COMMENT '用户的备选地址2',
  `created` date NOT NULL COMMENT '用户地址的创建时间',
  `updated` datetime NOT NULL COMMENT '时间地址的更新的时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ym_user_addr
-- ----------------------------

-- ----------------------------
-- Table structure for ym_wallet
-- ----------------------------
DROP TABLE IF EXISTS `ym_wallet`;
CREATE TABLE `ym_wallet` (
  `id` varchar(32) NOT NULL COMMENT '用户钱包的唯一标识',
  `status` int(1) NOT NULL COMMENT '用户钱包的状态 0.未开通 1.已开通',
  `remain` int(15) NOT NULL COMMENT '用户钱包的余额',
  `userid` varchar(32) NOT NULL COMMENT '标识用户的信息',
  `created` date DEFAULT NULL COMMENT '创建用户钱包的时间',
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_wallet` (`userid`),
  CONSTRAINT `fk_user_wallet` FOREIGN KEY (`userid`) REFERENCES `ym_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ym_wallet
-- ----------------------------

-- ----------------------------
-- Table structure for ym_wallet_action
-- ----------------------------
DROP TABLE IF EXISTS `ym_wallet_action`;
CREATE TABLE `ym_wallet_action` (
  `id` int(20) NOT NULL COMMENT '用户消费消息记录的唯一标识',
  `subject` varchar(500) NOT NULL COMMENT '用户消费的主题',
  `status` int(1) NOT NULL COMMENT '交易的状态  1.交易成功 2.交易失败',
  `fee` int(15) NOT NULL COMMENT '消费的费用金额',
  `state` int(1) NOT NULL COMMENT '消费记录目的  1.收入  2.支出',
  `walletid` varchar(32) NOT NULL COMMENT '用户钱包id的唯一标识',
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_wallet_action` (`walletid`),
  CONSTRAINT `fk_wallet_action` FOREIGN KEY (`walletid`) REFERENCES `ym_wallet` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ym_wallet_action
-- ----------------------------
