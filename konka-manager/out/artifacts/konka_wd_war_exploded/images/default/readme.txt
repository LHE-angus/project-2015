买卖提家电网产品默认图片规则 v1.0   
更新日期：2010-09-19

---1、9大类产品分别对应各自的产品图片
---2、每大类产品有大小各一张默认图片
------eg、冰箱===>default_big_img_FG.jpg;default_small_img_FG.jpg
---3、每个默认图片有3种名称规则：大类标识、大类、小类，无论那一种都有对应图片
------eg、假设冰箱大类标识FG,pd_type为1，有冰柜/冰箱2个小类，pd_type分别为11,12；
---------则有以下图片：
------------default_big_img_FG.jpg/default_small_img_FG.jpg
------------default_big_img_1.jpg/default_small_img_1.jpg
------------default_big_img_11.jpg/default_small_img_11.jpg
------------default_big_img_12.jpg/default_small_img_12.jpg
---4、如果前台展示没有类别标识，则直接显示default_big_img_.jpg/default_small_img_.jpg