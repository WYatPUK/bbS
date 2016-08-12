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
		}
		return A;
	}
}
