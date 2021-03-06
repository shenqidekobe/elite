package com.ledao.elite.core.framework.plugin.pay.pingpp;

/**
 * Ping++的银行编码
 * */
public enum PingBankCode {

	POST("0100","邮政储蓄银行"),
	ICBC("0102","工商银行"),
	ABC("0103","农业银行"),
	BOC("0104","中国银行"),
	CCB("0105","建设银行"),
	BCOM("0301","交通银行"),
	CITIC("0302","中信银行"),
	CEB("0303","光大银行"),
	HXB("0304","华夏银行"),
	CMBC("0305","民生银行"),
	CGB("0306","广发银行"),
	CMB("0308","招商银行"),
	CIB("0309","兴业银行"),
    SPDB("0310","浦发银行"),
	EGB("0311","恒丰银行"),
	A("0313","临沂市商业银行"),
	CZB("0316","浙商银行"),
	SBHB("0317","渤海银行"),
	PAB("0318","平安银行"),
	B("0328","新韩银行"),
	C("0329","韩亚银行"),
	D("0336","企业银行"),
	SHBANK("0401","上海银行"),
	E("0402","厦门银行"),
	F("0403","北京银行"),
	G("0404","烟台市商业银行"),
	H("0405","福建海峡银行"),
	I("0406","吉林银行"),
	J("0408","宁波银行"),
	K("0412","温州银行"),
	L("0413","广州银行"),
	M("0414","汉口银行"),
	O("0418","洛阳银行"),
	P("0420","大连银行"),
	Q("0422","河北银行"),
	R("0423","杭州商业银行"),
	S("0424","南京银行"),
    T("0427","乌鲁木齐市商业银行"),
	U("0428","绍兴银行"),
	V("0433","葫芦岛市商业银行"),
	W("0434","天津银行"),
	X("0435","郑州银行"),
	Y("0436","宁夏银行"),
	Z("0438","齐商银行"),
	A1("0439","锦州银行"),
	B1("0440","徽商银行"),
	C1("0441","重庆银行"),
	D1("0442","哈尔滨银行"),
	E1("0443","贵阳银行"),
	F1("0447","兰州银行"),
	G1("0448","南昌银行"),
	H1("0449","晋商银行"),
	I1("0450","青岛银行"),
	J1("0455","日照市商业银行"),
	K1("0456","鞍山银行"),
	L1("0458","青海银行"),
	M1("0459","台州银行"),
	N1("0461","长沙银行"),
	O1("0463","赣州银行"),
	P1("0465","营口银行"),
	Q1("0467","阜新银行"),
	R1("0474","内蒙古银行"),
	S1("0475","湖州市商业银行"),
	U1("0476","沧州银行"),
	V1("0479","包商银行"),
	W1("0481","威海商业银行"),
	X1("0483","攀枝花市商业银行"),
	Y1("0485","绵阳市商业银行"),
	Z1("0490","张家口市商业银行"),
	A2("0492","龙江银行"),
	B2("0495","柳州银行"),
	C2("0497","莱商银行"),
	D2("0498","德阳银行"),
	E2("0503","晋城银行"),
	F2("0505","东莞商行"),
	G2("0508","江苏银行"),
	H2("0513","承德市商业银行"),
	I2("0515","德州银行"),
	J2("0517","邯郸市商业银行"),
	K2("0525","浙江民泰商业银行"),
	L2("0526","上饶市商业银行"),
	M2("0527","东营银行"),
	N2("0528","泰安市商业银行"),
	O2("0530","浙江稠州商业银行"),
	P2("0534","鄂尔多斯银行"),
	Q2("0537","济宁银行"),
	R2("0547","昆仑银行"),
	S2("0554","邢台银行"),
	U2("0556","漯河商行"),
	V2("1401","上海农商银行"),
	W2("1402","昆山农信社"),
	X2("1403","常熟市农村商业银行"),
	Y2("1404","深圳农村商业银行"),
	Z2("1405","广州农村商业银行"),
	A3("1408","佛山顺德农村商业银行"),
	B3("1409","昆明农村信用社联合社"),
	C3("1410","湖北农信社"),
	D3("1415","东莞农村商业银行"),
	E3("1416","张家港农村商业银行"),
	F3("1417","福建省农村信用社联合社"),
	G3("1418","北京农村商业银行"),
	H3("1419","天津农村商业银行"),
	I3("1420","宁波鄞州农村合作银行"),
	J3("1424","江苏省农村信用社联合社"),
	K3("1428","江苏吴江农村商业银行"),
	L3("1430","苏州银行"),
	M3("1443","广西农村信用社联合社"),
	N3("1446","黄河农村商业银行"),
	O3("1447","安徽省农村信用社联合社"),
	P3("1448","海南省农村信用社联合社"),
	Q3("1513","重庆农村商业银行"),
	R3("6462","潍坊市商业银行"),
    S3("6466","富滇银行"),
	U3("6473","浙江泰隆商业银行"),
	V3("6478","广西北部湾银行"),
	W3("6567","商丘商行");
	
	public String code;
	public String desc;
	PingBankCode(String code,String desc){
		this.code=code;
		this.desc=desc;
	}
	public String getCode(){
		return code;
	}
	public String getDesc(){
		return desc;
	}
}
