<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <style type="text/css">
	body{background-color: #333;}
	.bg-div{position:relative;background-image: url(assets/river.jpg);width:1228px;height:690px;margin: 0 auto;}
	.logo{background-image: url(assets/logo.png);height:53px;width: 107px; float: left;margin: -4px 18px 0 0;}
	.search-form{float: left; background-color: #fff;padding:5px;}
	.search-text{height:25px;line-height: 25px;float: left;width: 350px;border: 0;outline: none;}
	.search-button{background-image: url(assets/search-button.png);width:29px;height:29px;float: left;border: 0}

	.search-box{position:absolute;top:150px;left: 200px; }
	</style>
  </head>
  
  <body>
    <div class="bg-div">
 	<div class="search-box">
 	<div class="logo"></div>

	 	<form class="search-form" action="Test" target="_blank" method="post">
	 		<input type="text" class="search-text" name="keyword"/>
	 		<input type="submit" class="search-button" value=""/>
	 	</form>
 	</div>
  </body>
</html>
