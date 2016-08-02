package com.example.wy_black_develop;

public class Excute_Unit {

	public static final int Type_Null = 0; //������ɹ����г��ִ��������ʾ�����
	public static final int Type_Find = 1;
	public static final int Type_Link = 2;
	public static final int Type_Shortcut = 3;
	public static final int Type_Ask = 4;
	public static final int Type_Clear = 5;
	
	public String Re_Ask = "";
	public String Re_Ask_Sub = "";
	public String Re_Find0 = "";
	public String Re_Find1 = "";
	public String Re_Link = "";
	
	private int Type;
	private String Link;
	
	public static final String Unit_Resistance = "21";
	public static final String Unit_Capacity = "22";
	public static final String Unit_Inductance = "23";
	public static final String Unit_Dioxide = "24";
	
	private int Order;
	
	private String Send_String;
	public String Port;
	
	public String Find_Unit;
	private int Find_Low;
	private int Find_Target;
	private int Find_High;
	
	Excute_Unit(int Type_) {
		Type = Type_;
		Send_String = "";
		Order = 0;
		Find_Unit = "";
		Find_Low = -1;
		Find_Target = -1;
		Find_High = -1;
		Port = "";
	}
	//����true�����
	boolean Set_Find_Unit(String Unit_, int mLow, int mTarget, int mHigh) { 
		if (Type!=Type_Find) return true;
		if (Unit_.length()!=2) return true;
		if (Unit_.equals(Unit_Resistance) || Unit_.equals(Unit_Capacity) || Unit_.equals(Unit_Inductance)
				|| Unit_.equals(Unit_Dioxide)) {
			Find_Unit = Unit_;
			Find_Low = mLow;
			Find_Target = mTarget;
			Find_High = mHigh;
			return false;
		}
		return true;
	}
	public void Generate_Find_Unit(){
		Send_String = "#" + "Find:" + Translate_hardware.Long_To_Standard(Find_Target) + Find_Unit + "@" + Port + "$";
	}
	boolean Set_Link_Unit(String Link_) {
		if (Type==Type_Link && Link_.length()==4) {
			Link = Link_;
			return false;
		}
		return true;
	}
	public void Generate_Link_Unit(){
		Send_String = "#" + "Link:" + Link + "@" + Port + "$";
	}
	boolean Set_Ask_Unit(String mPort) {
		if (Type==Type_Ask && mPort.length()==1) {
			Send_String = "#" + "Ask" + "@" + mPort + "$";
			return false;
		}
		return true;
	}
	boolean Set_Clear_Unit(String mPort) {
		if (Type==Type_Clear && mPort.length()==1) {
			Send_String = "#" + "Clear" + "@" + mPort + "$";
			return false;
		}
		return true;
	}
	public boolean Set_Port(String mPort){
		if (mPort.length()==1) {
			Port = mPort;
			return false;
		}
		return true;
	}
	
	static public final int Wrong = 0;
	static public final int Stay = 1;
	static public final int Next = 2;
	int Manifest_Callback(String x) {
		if (Type == Type_Null) return Wrong;
		if (Type == Type_Find) {
			//����Ͷ����ˣ���Order++
			if (x.indexOf(':') != -1 && Order==0 && x.substring(0, x.indexOf(':')).equals("Found")) {
				Re_Find0 = x;
				if (Handle_Find_Receive(x.substring(x.indexOf(':') + 1))) return Wrong;
				Order = 1;
				Send_String = "#" + "Confirm" + "@" + Port + "$";
				return Stay;
			}
			else if (Order==1 && x.equals("Confirmed")) {
				Re_Find1 = x;
				return Next;
			}
		}
		if (Type == Type_Ask) {
			Re_Ask = x;
			if (x.indexOf(':')!=-1 && x.substring(0,x.indexOf(':')).equals("Info")) {
				Re_Ask_Sub = x.substring(x.indexOf(':') + 1);
				return Next;
			}
			//���������Ƕ˿ڲ����ڻ������豸�������ùܣ�ͳͳ��NULL
			if (x.equals("GoStart_Wrong") || x.equals("Port_Not_Exist")) {
				Re_Ask_Sub = "NULL00";
				return Next;
			}
		}
		if (Type == Type_Link){
			if (! x.equals("Link_Finished")){
				return Wrong;
			}
			return Next;
		}
		if (Type == Type_Clear) {
			if (x.equals("Clear_Finished")) {
				return Next;
			}
			//���������Ƕ˿ڲ����ڻ������豸�������ù�
			if (x.equals("GoStart_Wrong") || x.equals("Port_Not_Exist")) {
				return Next;
			}
			//ʣ�µ��ǲ�������Ļش�
		}
		return Wrong;
	}
	//�������͵�ָ���Ҫ���жϷ���ֵ֪����������
	private boolean Handle_Find_Receive(String x) {
		//���Ҳ��ÿ��ǱȽϴ�С�����⣬���бȽϴ�Сȫ�����ձ�׼����
		if (Type == Type_Find) {
			long re = Translate_hardware.Standard_To_Long(x.substring(0,4));
			//AmoComActivity.Show("Handle_Receive long = " + re);
			if (re >= Find_Low && re <= Find_High) {
				return false;
			}
			else return true; //�����趨��Χ��
		}
		return true;
	}
	
	private void SendWrongInfo(String x) {
		//AmoComActivity.Wy_print_public(x);
	}
	
	public String Get_Cmd() {
		if (Type == Type_Find) {
			//����Ͷ����ˣ���Order++
			return Send_String;
		}
		if (Type == Type_Ask) {
			return Send_String;
		}
		if (Type == Type_Link) {
			return Send_String;
		}
		if (Type == Type_Clear) {
			return Send_String;
		}
		return "";
	}
	
	public void ShowInfo() {
		AmoComActivity.Show("Type is " + Type);
		if (Type == Type_Ask) {
			AmoComActivity.Show(Send_String);
			AmoComActivity.Show(Re_Ask);
		}
		else if (Type == Type_Find) {
			AmoComActivity.Show("Port is:" + Port);
			AmoComActivity.Show(Send_String);
			AmoComActivity.Show(Re_Find0);
			AmoComActivity.Show(Re_Find1);
		}
		else if (Type == Type_Link) {
			AmoComActivity.Show("Port is:" + Port);
			AmoComActivity.Show(Send_String);
			AmoComActivity.Show(Re_Link);
		}
	}
}
