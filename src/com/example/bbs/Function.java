package com.example.bbs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.widget.Toast;

public class Function {

	public Function() {
		// TODO Auto-generated constructor stub
	}
	private static String Recognize_Head = "WyBlack:";
	public static boolean Is_Legel (String Code) {
		if (Code.length() >= Recognize_Head.length() && Code.startsWith(Recognize_Head)){
			int Num_Of_Cmd;
			String Code1 = Code.substring(Recognize_Head.length());
			Num_Of_Cmd = Code1.split("&").length;
			String[] Cmd_Array = new String[Num_Of_Cmd];
			Cmd_Array = Code1.split("&");
			//Code1是分割后的逐条命令，接着对他们逐一审查即可
			for (int i=0; i<Num_Of_Cmd; i++) {
				if (Is_Find_And_Link(Cmd_Array[i])){
					continue;
				}
				if (Is_Power(Cmd_Array[i])){
					continue;
				}
				if (Is_Board(Cmd_Array[i])){
					continue;
				}
				//new_kind_of_CMD
				return false;
			}
			//所有的命令均可被识别，则返回true
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean Is_Find_And_Link(String x) {
		if (! x.startsWith("Find(")) return false;
		String x1 = x.substring("Find(".length());
		//接受第一个参数
		if (x1.indexOf(',') == -1) return false;
		String Find_P1_Str = x1.substring(0,x1.indexOf(','));
		if (Find_P1_Str.length()!=2) return false; //必须是两位十六进制的单位
		//Show(Find_P1_Str);
		
		String x2 = x1.substring(x1.indexOf(',')+1);
		//接受第二个参数
		if (x2.indexOf(',') == -1) return false;
		
		String Find_P2_Str = x2.substring(0,x2.indexOf(','));
		if (!isNumeric(Find_P2_Str)) return false;
		int Find_P2 = Integer.parseInt(Find_P2_Str);
		//Show(Find_P2_Str);
		
		String x3 = x2.substring(x2.indexOf(',')+1);
		//接受第三个参数
		if (x3.indexOf(',') == -1) return false;
		String Find_P3_Str = x3.substring(0,x3.indexOf(','));
		if (!isNumeric(Find_P3_Str)) return false;
		int Find_P3 = Integer.parseInt(Find_P3_Str);
		//Show(Find_P3_Str);
		
		String x4 = x3.substring(x3.indexOf(',')+1);
		//接受第四个参数
		if (x4.indexOf(')') == -1) return false;
		String Find_P4_Str = x4.substring(0,x4.indexOf(')'));
		if (!isNumeric(Find_P4_Str)) return false;
		int Find_P4 = Integer.parseInt(Find_P4_Str);
		//Show(Find_P4_Str);
		
		//Wy_print("x1is:" + Find_P1_Str + "  x2is:" + Find_P2_Str + "  x3is:"+ Find_P3_Str);
		
		String x5 = x4.substring(x4.indexOf(')')+1);
		//Wy_print(x5);
		
		if (! x5.startsWith("Link(")) return false;
		String x6 = x5.substring("Link(".length());
		//接受唯一的参数
		if (x6.indexOf(')') == -1) return false;
		String Link_Str = x6.substring(0, x6.indexOf(')'));
		if (Link_Str.length() != 4) return false; //必须是四位连接方式
		//Show(Link_Str);
		
		if (x6.indexOf(')') != x6.length()-1) return false;
		//Show("Is Find_And_Link");
		return true;
	}
	public static boolean Is_Power(String x) {
		if (! x.startsWith("Power(")) return false;
		String x1 = x.substring("Power(".length());
		//接受唯一的参数
		if (x1.indexOf(')') == -1) return false;
		if (x1.indexOf(')') != x1.length()-1) return false;
		String PowerMode = x1.substring(0,x1.indexOf(')'));
		//判断Mode是否为"Auto","All"中的一种
		if (PowerMode.equals("Auto")){
			return true;
		}
		else if (PowerMode.equals("All")){
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean Is_Board(String x) {
		if (! x.startsWith("Board(")) return false;
		String x1 = x.substring("Board(".length());
		//接受唯一的参数
		if (x1.indexOf(')') == -1) return false;
		if (x1.indexOf(')') != x1.length()-1) return false;
		String BoardMode = x1.substring(0,x1.indexOf(')'));
		//判断Mode是否为2
		if (BoardMode.length()!=2) return false;
		return true;
	}
	public static boolean isNumeric(String str){  
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() ){
			return false; 
		}
		return true;
	}
	public static int Get_Cmd_Num(String Scaned_Code) {
		int Num_Of_Cmd;
		String Code1 = Scaned_Code.substring(Recognize_Head.length());
		Num_Of_Cmd = Code1.split("&").length;
		return Num_Of_Cmd;
	}
	public static String[] Get_Cmd_Array(String Scaned_Code) {
		int Num_Of_Cmd;
		String Code1 = Scaned_Code.substring(Recognize_Head.length());
		Num_Of_Cmd = Code1.split("&").length;
		String[] Cmd_Array = new String[Num_Of_Cmd];
		Cmd_Array = Code1.split("&");
		return Cmd_Array;
	}
	public static void Clear_All_Generate(){
		Excute_Unit A = new Excute_Unit(Excute_Unit.Type_ClearAll);
		AmoComActivity.Excute.Append_Unit(A);
	}
	public static void Ask_All_Generate(){

		Excute_Unit A = new Excute_Unit(Excute_Unit.Type_Ask);
		A.Set_Ask_Unit("0");
		AmoComActivity.Excute.Append_Unit(A);
		Excute_Unit B = new Excute_Unit(Excute_Unit.Type_Ask);
		B.Set_Ask_Unit("1");
		AmoComActivity.Excute.Append_Unit(B);
		Excute_Unit C = new Excute_Unit(Excute_Unit.Type_Ask);
		C.Set_Ask_Unit("2");
		AmoComActivity.Excute.Append_Unit(C);
		Excute_Unit D = new Excute_Unit(Excute_Unit.Type_Ask);
		D.Set_Ask_Unit("3");
		AmoComActivity.Excute.Append_Unit(D);
		Excute_Unit E = new Excute_Unit(Excute_Unit.Type_Ask);
		E.Set_Ask_Unit("4");
		AmoComActivity.Excute.Append_Unit(E);
		Excute_Unit F = new Excute_Unit(Excute_Unit.Type_Ask);
		F.Set_Ask_Unit("5");
		AmoComActivity.Excute.Append_Unit(F);
		Excute_Unit G = new Excute_Unit(Excute_Unit.Type_Ask);
		G.Set_Ask_Unit("6");
		AmoComActivity.Excute.Append_Unit(G);
		Excute_Unit H = new Excute_Unit(Excute_Unit.Type_Ask);
		H.Set_Ask_Unit("7");
		AmoComActivity.Excute.Append_Unit(H);
		Excute_Unit I = new Excute_Unit(Excute_Unit.Type_Ask);
		I.Set_Ask_Unit("8");
		AmoComActivity.Excute.Append_Unit(I);
		Excute_Unit J = new Excute_Unit(Excute_Unit.Type_Ask);
		J.Set_Ask_Unit("9");
		AmoComActivity.Excute.Append_Unit(J);
		Excute_Unit K = new Excute_Unit(Excute_Unit.Type_Ask);
		K.Set_Ask_Unit("A");
		AmoComActivity.Excute.Append_Unit(K);
		Excute_Unit L = new Excute_Unit(Excute_Unit.Type_Ask);
		L.Set_Ask_Unit("B");
		AmoComActivity.Excute.Append_Unit(L);
		Excute_Unit M = new Excute_Unit(Excute_Unit.Type_Ask);
		M.Set_Ask_Unit("C");
		AmoComActivity.Excute.Append_Unit(M);
	}
	
	public static Excute_Unit Get_Unit_Find_And_Link1(String x){
		String x1 = x.substring("Find(".length());
		String Find_P1_Str = x1.substring(0,x1.indexOf(','));
		String x2 = x1.substring(x1.indexOf(',')+1);
		String Find_P2_Str = x2.substring(0,x2.indexOf(','));
		int Find_P2 = Integer.parseInt(Find_P2_Str);
		String x3 = x2.substring(x2.indexOf(',')+1);
		String Find_P3_Str = x3.substring(0,x3.indexOf(','));
		int Find_P3 = Integer.parseInt(Find_P3_Str);
		String x4 = x3.substring(x3.indexOf(',')+1);
		String Find_P4_Str = x4.substring(0,x4.indexOf(')'));
		int Find_P4 = Integer.parseInt(Find_P4_Str);
		String x5 = x4.substring(x4.indexOf(')')+1);
		String x6 = x5.substring("Link(".length());
		String Link_Str = x6.substring(0, x6.indexOf(')'));
		Excute_Unit A = new Excute_Unit(Excute_Unit.Type_Find);
		if (A.Set_Find_Unit(Find_P1_Str, Find_P2, Find_P3, Find_P4)){
			//Show("Get_Unit_Find_And_Link1 Wrong");
			Toast.makeText(null, "Get_Unit_Find_And_Link1 Wrong", Toast.LENGTH_LONG).show();
		}
		return A;
	}
	public static Excute_Unit Get_Unit_Find_And_Link2(String x){

		String x1 = x.substring("Find(".length());
		String Find_P1_Str = x1.substring(0,x1.indexOf(','));
		String x2 = x1.substring(x1.indexOf(',')+1);
		String Find_P2_Str = x2.substring(0,x2.indexOf(','));
		int Find_P2 = Integer.parseInt(Find_P2_Str);
		String x3 = x2.substring(x2.indexOf(',')+1);
		String Find_P3_Str = x3.substring(0,x3.indexOf(','));
		int Find_P3 = Integer.parseInt(Find_P3_Str);
		String x4 = x3.substring(x3.indexOf(',')+1);
		String Find_P4_Str = x4.substring(0,x4.indexOf(')'));
		int Find_P4 = Integer.parseInt(Find_P4_Str);
		String x5 = x4.substring(x4.indexOf(')')+1);
		String x6 = x5.substring("Link(".length());
		String Link_Str = x6.substring(0, x6.indexOf(')'));
		Excute_Unit A = new Excute_Unit(Excute_Unit.Type_Link);
		if (A.Set_Link_Unit(Link_Str)){
			//Show("Get_Unit_Find_And_Link2 Wrong");
			Toast.makeText(null, "Get_Unit_Find_And_Link2 Wrong", Toast.LENGTH_LONG).show();
			//基本不会发生了
		}
		return A;
	}
	public static Excute_Unit Get_Unit_Power(String x, String[] Got_Port){
		//if (! x.startsWith("Power(")) return false;
		String x1 = x.substring("Power(".length());
		//接受唯一的参数
		//if (x1.indexOf(')') == -1) return false;
		//if (x1.indexOf(')') != x1.length()-1) return false;
		String PowerMode = x1.substring(0,x1.indexOf(')'));
		//判断Mode是否为"Auto","All"中的一种
		Excute_Unit A = new Excute_Unit(Excute_Unit.Type_Power);
		int PowerCmd = 0;
		if (PowerMode.equals("Auto")){
			for (int i=0; i<13; i++) {
				if (!Got_Port[i].equals("None")) {
					PowerCmd |= (1<<Translate_hardware.Char_To_Int(Got_Port[i].charAt(0)));
				}
			}
			//已经获得了PowerCmd序列，生成Excute_Unit选项
			A.Set_Power(PowerCmd);
		}
		else if (PowerMode.equals("All")){
			for (int i=0; i<13; i++) {
				PowerCmd |= (1<<i);
			}
			//生成了一个全部开启的命令
			A.Set_Power(PowerCmd);
		}
		else {
			//并不会发生，之前已经认证
		}		
		return A;
	}
	public static Excute_Unit Get_Unit_Board(String x) {
		//if (! x.startsWith("Board(")) return false;
		String x1 = x.substring("Board(".length());
		//接受唯一的参数
		//if (x1.indexOf(')') == -1) return false;
		//if (x1.indexOf(')') != x1.length()-1) return false;
		String BoardMode = x1.substring(0,x1.indexOf(')'));
		//判断Mode是否为2
		//if (BoardMode.length()!=2) return false;
		int BoardCmd = 16 * Translate_hardware.Char_To_Int(BoardMode.charAt(0)) +
				Translate_hardware.Char_To_Int(BoardMode.charAt(1));
		Excute_Unit A = new Excute_Unit(Excute_Unit.Type_Board);
		A.Set_Board(BoardCmd);
		return A;
	}
	public static long Wy_string_to_long (String source){
		long source_long = 0;
		for (int i=0; i<source.length() && source.charAt(i)!='\0'; i++){
			source_long *= 10;
			source_long += Wy_char_to_int(source.charAt(i));
		}
		return source_long;
	}
	public static char Wy_int_to_hex (int x){
		if (x>=0 && x<10){
			return (char) ('0' + x);
		}
		else if (x>9 && x<16){
			return (char) ('a' + x - 10);
		}
		else return (char) 0;
	}
	public static int Wy_char_to_int(char x){
		if (x-'0' >=0 && x-'0'<10){
			return x-'0';
		}
		else if (x-'a' >= 0 && x-'a'<6){
			return x-'a'+10;
		}
		else return 0;
	}
	public static String Wy_int_to_sting (int r){
		String a = "";
		String b = "%";
		a += b.replace('%', Wy_int_to_hex((r>>12) & 0x0f));
		a += b.replace('%', Wy_int_to_hex((r>>8) & 0x0f));
		a += b.replace('%', Wy_int_to_hex((r>>4) & 0x0f));
		a += b.replace('%', Wy_int_to_hex(r & 0x0f));
		return a;
	}
	public static String Wy_normal_to_special_basic_4_char(long source){
		int r = 0, j;
		for (j=0; source>1000; ++j)
		{
			source /= 2;
		}
		for (int i=9; i>-1; --i)
		{
			r = r << 1;
			r += ((source>>i)&1);
		}
		for (int i=5; i>-1; --i)
		{
			r = r << 1;
			r += ((j>>i)&1);
		}
		String a = Function.Wy_int_to_sting(r);
		return a;
	}
	public static String[] Find_Need_Port(String[] A) {	
		int Num_Of_Cmd = A.length;
		String[] Need_Port = new String[Num_Of_Cmd];
		for (int i=0; i<Num_Of_Cmd; i++) {
			Need_Port[i] = "";
		}
		for (int i=0; i<Num_Of_Cmd; i++) { //先解决指定类型的连接
			if (Is_Find_And_Link(A[i])) {
				Need_Port[i] = Function.Get_Unit_Find_And_Link1(A[i]).Find_Unit;
				//Show(Need_Port[i]);
			}
			if (Is_Power(A[i]) || Is_Board(A[i])) {
				Need_Port[i] = "None";
			}
			//new_kind_of_CMD
		}
		return Need_Port;
	}
	
}
