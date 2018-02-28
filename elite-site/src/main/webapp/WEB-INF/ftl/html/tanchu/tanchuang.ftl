	<@extend name="layout">
    <@block name="cs">
    <style type="text/css">
	/*å®½åº¦æ”¹äº†*/
	.conteanb {
		width:740px;
		padding:30px;
		margin:0 auto;
		border-radius:20px;
		background:#999;
		position: relative;
	}
	.conteanb h1 {
		text-align:center;
		font-size:20px;
		padding-bottom:15px;
		border-bottom:2px solid #95d6e2;
	}
	/*æ”¹äº†å®½åº¦*/
	.conteanb label {
		width:26%;
		line-height: 50px;
		margin-right:19px;
		text-align:right;
		display: inline-block;
		float:left;
	}
	/*æ”¹äº†å®½åº¦*/
	/*å·²æ”¹æˆ–è€…åŠ äº†*/
	#for-btn-ul {
		width:498px;
		height:220px;
		margin-right: 16px;
		float:right;
		padding:6px;
		list-style:none;
		border:1px solid #999;
		border-radius: 8px;
		background: #fff;
	}
	#for-btn-ul li {
		display: inline-block;
		margin:6px;
	}
	#for-btn-ul li a {
		display: block;
		padding: 8px 23px;
		color:#333;
		border:1px solid #999;
		border-radius: 8px;
		text-decoration: none;
	}
	#for-btn-ul li a:hover {
		border:1px solid #fff;
		color:#fff;
		background:#7dd2e1;
	}
 	/*å·²æ”¹æˆ–è€…åŠ äº†*/






	
	.conteanb form .for {
		margin:15px 0;
		color:#fff;
		position:relative;
	}
	.detu {
		display:block;
		width:20px;
		height:27px;
		position: absolute;
		top:14px;
		left:132px;
		background:url(imgs/map.png) no-repeat center;
	}
	.for-btn {
		padding-bottom:30px;
		border-bottom:1px dashed #fff;
		overflow: hidden;
	}
	.triangle {
		display:inline-block;
		width:0px;
		height:0px;
		border-top:7px solid #d8d8d8;
		border-left:7px solid transparent;
		border-right:7px solid transparent;
		position: absolute;
		top:23px;
	}
	.for-btn-triangel {
		left:250px;
	}
	.for-btn-triangela {
		left:253px;
	}
	.for-btn-triangelb {
		position: absolute;
		top:23px;
		left:435px;
	}
	.conteanb form .for-sbt {
		width:410px;
		margin:35px auto;
		overflow:hidden;
	}
	.conteanb .for-sbt button {
		width:180px;
		height:60px;
		float:left;
		border:none;
		color:#fff;
		border:1px solid #fff;
		border-radius:100px;
		background:none;
		font-size:18px;
	}
	.conteanb .for-sbt button:hover {
		background:#fea600;
		border:none;
	}
	.conteanb .for-sbt input[type="reset"] {
		margin-left: 50px
	}
	.conteanb .tab,
	.conteanb form .for input {
		width:360px;
		height:50px;
		border-radius:8px;
		outline: none;
		
		border:1px solid #95d6e2;
	}

	.conteanb form .for input {
text-indent:.5cm;
	}
	.conteanb .tab {
		border:none;
		list-style:none;
		border-radius:8px;
		border:1px solid #95d6e2;
		overflow: hidden;
	}

	.conteanb .tab-li li {
		float:left;
		line-height: 50px;
		text-align:center;
		font-size:14px;
		background:#fff;
		color:#333;
	}
	.td-border {
		border-right:1px solid #95d6e2;
	}
	.conteanb .tab li:hover {
		background:#95d6e2;
		color:#fff;
	}
	.cop {
		display: block;
		width:30px;
		height:30px;
		background: url(imgs/copy.png) no-repeat center;
		position: absolute;
		top:20px;
		right:25px;
}
.for-ul-li {
	list-style: none;
}
.for-ul-li li {
	display: inline-block;
	margin:5px;
}
.for-ul-li li a {
	display: block;
	text-decoration: none;
	padding: 5px 10px;
	border-radius: 6px;
	border:1px solid #333;
	color:#fff;
}
.for-ul-li li a:hover {
	background:#95d6e2;
	border:1px solid #95d6e2;
}
.tradio {
	width: 398px;
	float: left;
	height: 26px;
	margin-top:12px;
	/*overflow: hidden;*/
}

.tradio span {
	display: inline-block;
	width: 26px;
	height:26px;
	line-height: 28px;
	border:1px solid #fff;
	border-radius: 50%;
	margin-left: 33px;
}
.tradio .radio-btn {
	width:16px;
	height:16px;
	border:none;
	background:none;
	border-radius: 50%;
	background:transparent;
	margin-left:5px;
}
.tradio .radio-btn.atioc {
	background:#2cb7c9;
}
 .tradio span.atioc {
	border:1px solid #2cb7c9;
 }
 .for textarea {
 	width:460px;
 	height:120px;
 	border-radius: 8px;
 	padding:10px;
 }
	
	
	
	 </style>
    </@block>
    
     <@block name="body">
	<div class="conteanb">
		<form action="">
			<div class="for">
				<label for="">项目联系人姓名</label>
				<input type="text" placeholder="请输入您的姓名">
			</div>
			<div class="for">
				<label for="">联系人手机号</label>
				<input type="text" placeholder="请输入11位手机号">
			</div>
			<div class="for for-btn">
				<label for="">项目所属行业<span>(少于3个)</span></label>
				<ul id="for-btn-ul">
					<li>
						<a href="javascript:;">教育</a>
					</li>
					<li>
						<a href="javascript:;">教育</a>
					</li>
					<li>
						<a href="javascript:;">教育</a>
					</li>
					<li>
						<a href="javascript:;">教育</a>
					</li>
					<li>
						<a href="javascript:;">教育</a>
					</li>
					<li>
						<a href="javascript:;">教育</a>
					</li>
					<li>
						<a href="javascript:;">教育</a>
					</li>
					<li>
						<a href="javascript:;">教育</a>
					</li>
				</ul>
			</div>
			<div class="for">
				<label for="">人才属性</label>
				<ul class="for-ul-li">
					<li><a href="">afsdf</a></li>
					<li><a href="">fasdf</a></li>
					<li><a href="">sfsadf</a></li>
					<li><a href="">afsfsadsddf</a></li>
					<li><a href="">fasdf</a></li>
					<li><a href="">sfsadf</a></li>
					<li><a href="">afsdf</a></li>
					<li><a href="">fasdf</a></li>
					<li><a href="">sfsadf</a></li>
					<li><a href="">afsdf</a></li>
				</ul>
			</div>
			<div class="for ">
				<label for="">相关工作年限</label>
				<input type="email" placeholder="6年" style="width:180px">
				<span class="triangle for-btn-triangel"></span>
			</div>
			<div class="for">
				<label for="">所在城市</label>
				<span class="detu"></span>
				<ul class="tab tab-li">
					<li style="width:50%">上海</li>
					<li style="width:50%">上海市</li>
				</ul>
				<span class="triangle for-btn-triangela"></span>
				<span class="triangle for-btn-triangelb"></span>
			</div>
			<div class="for">
				<label for="">是否能以您的名义进行联系</label>
				<p class="tradio">	
					<span ><button class="radio-btn"></button></span>
					是
					<span class="atioc">
					<button class="radio-btn atioc"></button></span>
					否
				</p>
			</div>
			<div class="for">
				<label for="">项目简单描述</label>
				<textarea name="" id="" placeholder="少于100字体"></textarea>
			</div>
			<div class="for for-sbt">
				<button type="submit" value="保存" style="margin-right:50px;">保存</button> 
				<button type="reset" value="取消">取消</button> 
			</div>
		</form>
		<span class="cop"></span>
	</div>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<div class="conteanb">
		<form action="">
			<div class="for">
				<label for="">项目联系人姓名</label>
				<input type="text" placeholder="请输入您的姓名">
			</div>
			<div class="for">
				<label for="">联系人手机号</label>
				<input type="text" placeholder="请输入11位手机号">
			</div>
			
			
			<div class="for">
				<label for="">所在城市</label>
				<span class="detu"></span>
				<ul class="tab tab-li">
					<li style="width:50%">上海</li>
					<li style="width:50%">上海市</li>
				</ul>
				<span class="triangle for-btn-triangela"></span>
				<span class="triangle for-btn-triangelb"></span>
			</div>
			<div class="for">
				<label for="">是否能以您的名义进行联系</label>
				<p class="tradio">	
					<span ><button class="radio-btn"></button></span>
					是
					<span class="atioc">
					<button class="radio-btn atioc"></button></span>
					否
				</p>
			</div>
			<div class="for">
				<label for="">项目简单描述</label>
				<textarea name="" id="" placeholder="少于100字体"></textarea>
			</div>
			<div class="for for-sbt">
				<button type="submit" value="保存" style="margin-right:50px;">保存</button> 
				<button type="reset" value="取消">取消</button> 
			</div>
		</form>
		<span class="cop"></span>
	</div>	

<br><br><br><br><br><br><br><br><br><br><br><br><br>

	<div class="conteanb">
		<form action="">
			<div class="for">
				<label for="">项目联系人姓名</label>
				<input type="text" placeholder="请输入您的姓名">
			</div>
			<div class="for">
				<label for="">联系人手机号</label>
				<input type="text" placeholder="请输入11位手机号">
			</div>
			
			
			<div class="for">
				<label for="">所在城市</label>
				<span class="detu"></span>
				<ul class="tab tab-li">
					<li style="width:50%">上海</li>
					<li style="width:50%">上海市</li>
				</ul>
				<span class="triangle for-btn-triangela"></span>
				<span class="triangle for-btn-triangelb"></span>
			</div>
			<div class="for">
				<label for="">是否能以您的名义进行联系</label>
				<p class="tradio">	
					<span ><button class="radio-btn"></button></span>
					是
					<span class="atioc">
					<button class="radio-btn atioc"></button></span>
					否
				</p>
			</div>
			<div class="for">
				<label for="">项目简单描述</label>
				<textarea name="" id="" placeholder="少于100字体"></textarea>
			</div>
			<div class="for for-sbt">
				<button type="submit" value="保存" style="margin-right:50px;">保存</button> 
				<button type="reset" value="取消">取消</button> 
			</div>
		</form>
		<span class="cop"></span>
	</div>
	
 </@block>