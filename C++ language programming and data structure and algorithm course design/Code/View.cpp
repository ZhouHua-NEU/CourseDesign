#include "View.h"
#include "stack.hpp"
#include "AVL.hpp"

//*****************************************************************开始界面*********************************************************************
void View::MainView()
{
	system("cls");
	cout << "****************请先登录************************" << endl;
	cout << "   		1.登录" << endl;
	cout << "   		2.注册" << endl;
	cout << "   		3.退出" << endl;
	cout << "***************************************************" << endl;
	int n;
	cin >> n;

	switch (n)
	{
	case 1:loginview(); break;
	case 2:signinview(); break;
	case 3:shutdownview(); break;
	default:MainView();
	}

}

//*****************************************************************登录界面*********************************************************************
void View::loginview()
{
	
	int change = 1;
	while (change)
	{
		system("cls");
		cout << "请输入账号" << endl;
		char n[10];
		cin >> n;

		cout << "请输入密码" << endl;
		char p[15] ;
		cin >> p;
                guest idenity;
		strcpy_s(idenity.name,n);
		strcpy_s(idenity.passward,p);

		if(guests.search(idenity)==NULL)
		{
		cout << "账号或密码错误" << endl;
		cout << "   	1.返回主界面" << endl;
		cout << "   	2.重新输入" << endl;
		cout << "   	3.退出" << endl;
		cout << "********************************************" << endl;
		int n;
		cin >> n;

		switch (n)
		{
		case 1:MainView(); break;
		case 2:loginview(); break;
		case 3:shutdownview(); break;
		default:loginview();
		}

		change = 0;
		}

		else if(guests.search(idenity)->data.varity==1)
		{
			current = guests.search(idenity);
			guestview();
			change = 0;
		}

		else if(guests.search(idenity)->data.varity == 2)
		{
			current = guests.search(idenity);
			adminview();
			change = 0;
		}


	}
}

//*****************************************************************注册界面*********************************************************************
void View::signinview()
{
	while(1)
	{
	system("cls");
	cout << "请输入账号" << endl;
	char a[10];
	char b[15];
	char c[15];
	cin >> a;
	cout << "请输入密码" << endl;
	cin >> b;
	cout << "确认密码" << endl;
	cin >> c;
	if (!(strcmp(b,c)))
	{
		cout << "请选择注册类型" << endl;
		cout << "   			1.普通游客" << endl;
		cout << "   			2.后台管理员" << endl;
		int d;
		cin >> d;
		guest current;
		strcpy_s(current.name,a);
		strcpy_s(current.passward, b);
		current.varity = d;
		guests.insert(0,current);
	
	
		system("cls");
		cout << "********************注册成功!************************" << endl;
		cout << "   		1.返回主界面" << endl;
		cout << "   		2.登录" << endl;
		cout << "   		3.退出" << endl;
		cout << "***************************************************" << endl;
		int n;
		cin >> n;

		switch (n)
		{
		case 1:MainView(); break;
		case 2:loginview(); break;
		case 3:shutdownview();
		default:signinview();
		}
	


		break;
	}
	else
	{
		system("cls");
		cout << "********************两次输入密码不一致，是否重新注册************************" << endl;
		cout << "   			1.重新注册" << endl;
		cout << "   			2.返回主界面" << endl;
		cout << "   			3.退出" << endl;
		cout << "***************************************************" << endl;
		int n;
		cin >> n;

		switch (n)
		{
		case 1:signinview(); break;
		case 2:MainView(); break;
		case 3:shutdownview();
		default:loginview();
		}
		
	}
	}
	
	
}	

//*****************************************************************普通vip界面*********************************************************************
void View::guestview()
{
	system("cls");
	current->data.print();
	cout << "********************普通游客************************" << endl;
	cout << "   			1.查询车票" << endl;
	cout << "   			2.购买车票" << endl;
	cout << "   			3.查看已购车票" << endl;
	cout << "   			4.充值" << endl;
	cout << "   			5.返回" << endl;
	cout << "   			6.退出" << endl;
	cout << "***************************************************" << endl;
	int n;
	cin >> n;
	switch (n)
	{
	case 1: searchtickets(); break;
	case 2: buytickets(); break;
	case 3: searchbuytickets(); break;
	case 4: addmoney(); break;
	case 5:MainView(); break;
	case 6:shutdownview();
	default:guestview();
	}

}


//*****************************************************************充值界面*********************************************************************
void View::addmoney() {
	cout << "********************普通vip界面 * ******************" << endl;
	cout << "请输入要充值的金额" << endl;
	int y;
	cin >> y;
	current->data.money += y;
	cout << "充值成功" << endl;

	system("pasue");
	system("cls");
	current->data.print();
	cout << "********************充值页面************************" << endl;
	cout << "   			1.继续充值" << endl;
	cout << "   			2.返回" << endl;
	cout << "   			3.退出" << endl;
	int n;
	cin >> n;
	switch (n)
	{
	case 1: addmoney(); break;
	case 2: guestview(); break;
	case 3:shutdownview();
	default:addmoney();
	}







}

//*****************************************************************管理员界面*********************************************************************
void View::adminview()
{
	system("cls");
	cout << "********************管理员界面************************" << endl;
	cout << "   			1.添加票价信息" << endl;
	cout << "   			2.删除票价信息" << endl;
	cout << "   			3.查询票价信息" << endl;
	cout << "   			4.退出登录" << endl;
	cout << "   			5.退出系统" << endl;
	cout << "***************************************************" << endl;
	int n;
	cin >> n;
	switch (n)
	{
	case 1: addticketsview(); break;
	case 2: deleteticketsview(); break;
	case 3: searchtickets(); break;
	case 4: MainView(); break;
	case 5: shutdownview();
	default:adminview();
	}
}

//*****************************************************************添加售票信息*********************************************************************
void View::addticketsview()
{
	system("cls");
	cout << "********************添加售票信息************************" << endl;
	ticket current1;
	cout << "趟次" << endl;
	cin >> current1.keywords;
	cout << "日期月份" << endl;
	cin >> current1.month;
	cout << "日期号数" << endl;
	cin >> current1.day;
	cout << "起始地" << endl;
	cin >> current1.start;
	cout << "终点" << endl;
	cin >> current1.end;
	cout << "价格" << endl;
	cin >> current1.price;
	cout << "数量" << endl;
	cin >> current1.number;

	cout << "趟次:" << current1.keywords << endl;
	cout << "日期:" << current1.month << "月" << current1.day << "日" << endl;
	cout << "起始地:" << current1.start << endl;
	cout << "终点:" << current1.end << endl;
	cout << "价格:" << current1.price << endl;
	cout << "数量:" << current1.number << endl;

	cout << "是否插入(y/n)" << endl;
	string n;
	cin >> n;
	if (n == "y" | n == "Y")
	{
		tickets.insert(0, current1);
		cout << "添加成功" << endl;
	}
	else
		cout << "取消添加成功" << endl;

	system("cls");
	cout << "********************添加售票信息************************" << endl;
	cout << "   			1.继续添加票价信息" << endl;
	cout << "   			2.返回" << endl;
	cout << "   			3.退出" << endl;
	cout << "***************************************************" << endl;
	int m;
	cin >> m;



	switch (m)
	{
	case 1: addticketsview(); break;
	case 2: adminview(); break;
	case 3: shutdownview();
	default:addticketsview();
	}
}

//**************************************************************删除售票信息************************************************************************
void View::deleteticketsview()
{
	
	system("cls");
	cout << "********************删除售票信息************************" << endl;
	cout << "请输入要删除售票信息的趟次，如不确定，请先查询" << endl;
	int n;
	cin >> n;

	int ifexist = 0;
	for (int i = 0; tickets.locate(i) != NULL; i++)
	{
		if (tickets.locate(i)->data.keywords == n)
		{
			ifexist = 1;
			tickets.locate(i)->data.print();
			cout << "是否删除(y/n)" << endl;
			string b;
			cin >> b;
			if (b == "y" | b == "Y")
			{
				tickets.remove(i);
				cout << "删除成功" << endl;
			}
			break;
		}
	}
	if(ifexist ==0)
		cout << "不存在该车次" << endl;
	    system("pause");

	system("cls");
	cout << "********************删除车次信息************************" << endl;
	cout << "   			1.继续删除车次信息" << endl;
	cout << "   			2.返回" << endl;
	cout << "   			3.退出" << endl;
	cout << "***************************************************" << endl;
	int m;
	cin >> m;

	switch (m)
	{
	case 1: deleteticketsview(); break;
	case 2: adminview(); break;
	case 3: shutdownview();
	default:deleteticketsview();
	}




















}


//**************************************************************查询售票信息************************************************************************
void View::searchtickets()
{
	system("cls");
	cout << "*******************请选择查询方式************************" << endl;
	cout << "   			1.车次查询" << endl;
	cout << "   			2.初始地终点地查询" << endl;
	cout << "   			3.按日期查询" << endl;
	cout << "   			4.返回" << endl;
	cout << "   			5.退出" << endl;

	int n;
	cin >> n;

	switch (n)
	{
	case 1:keywordsearchview(); break;
	case 2:startandendsearchview(); break;
	case 3:timesearchview(); break;
	case 4:
		if (current->data.varity == 2) adminview();
		else  guestview();
		break;
	case 5:shutdownview();
	default:searchtickets();



	}
}


//*****************************************************************买票*********************************************************************
void View::buytickets()
{
	cout << "请输入你想买的车次" << endl;
	int m;
	cin >> m;

	int ifexist = 0;
	for (int i = 0; tickets.locate(i) != NULL; i++)
	{
		if (tickets.locate(i)->data.keywords == m)
		{
			ifexist = 1;
			tickets.locate(i)->data.print();
			cout << "是否购买(y/n)" << endl;
			string b;
			cin >> b;
			if (b == "y" | b == "Y")
			{
				if (current->data.money < 0)
					cout << "你的余额不足" << endl;
				else {
					guests.search(current)->data.ticketsofguest.insert(tickets.locate(i)->data);
					current->data.money -= tickets.locate(i)->data.price;
					tickets.locate(i)->data.number--;
					current = current->link;
					cout << "购买成功" << endl;
				}
			}
			break;
		}
	}
	if (ifexist == 0)
		cout << "不存在该车次" << endl;
	system("pause");

	system("cls");
	cout << "********************买票************************" << endl;
	cout << "   			1.继续买票" << endl;
	cout << "   			2.返回" << endl;
	cout << "   			3.退出" << endl;
	cout << "***************************************************" << endl;
	int k;
	cin >> k;

	switch (k)
	{
	case 1: buytickets(); break;
	case 2: adminview(); break;
	case 3: shutdownview();
	default:buytickets();
	}


















}

//**************************************************************查询已购买的车票************************************************************************
void View::searchbuytickets()
{
	cout << "************************历史订单********************" << endl;
	current->data.ticketsofguest.output();

	system("pause");

	system("cls");
	cout << "********************历史订单************************" << endl;
	cout << "   			1.返回" << endl;
	cout << "   			2.退出" << endl;
	cout << "***************************************************" << endl;
	int k;
	cin >> k;

	switch (k)
	{
	case 1: guestview(); break;
	case 2: shutdownview();
	default:searchbuytickets();
	}


















}

//**************************************************************关键词查询************************************************************************
void View::keywordsearchview()
{
	cout << "************************关键词查询********************" << endl;
	cout << "请输入要查询的车次" << endl;
	int k;
	cin >> k;

	AVL avl;
	for (int i = 1; tickets.locate(i) != NULL; i++)
		avl.Insert(tickets.locate(i)->data);
	if (avl.search(k) == NULL)
	{
		cout << "不存在该车次" << endl;
		system("pause");
		system("cls");
		cout << "************************关键词查询********************" << endl;
		cout << "   			1.继续查询" << endl;
		cout << "   			2.返回" << endl;
		cout << "   			3.退出" << endl;
		cout << "***************************************************" << endl;
		int m;
		cin >> m;

		switch (m)
		{
		case 1: keywordsearchview(); break;
		case 2: searchtickets(); break;
		case 3: shutdownview();
		default:keywordsearchview();
		}

	}
	else
	{
		avl.search(k)->data.print();
		system("pause");
		system("cls");
		cout << "************************关键词查询********************" << endl;
		cout << "   			1.继续查询" << endl;
		cout << "   			2.返回" << endl;
		cout << "   			3.退出" << endl;
		cout << "***************************************************" << endl;
		int m;
		cin >> m;

		switch (m)
		{
		case 1: keywordsearchview(); break;
		case 2: searchtickets(); break;
		case 3: shutdownview();
		default:keywordsearchview();
		}
	}












/*          普通查询方式   
	int ifexist = 0;
	for (int i = 0; tickets.locate(i) != NULL; i++)
	{
		if (tickets.locate(i)->data.keywords == k)
		{
			ifexist = 1;
			tickets.locate(i)->data.print();
		}

	}
	if (ifexist == 0)
		cout << "不存在该车次" << endl;
	system("pause");
	system("cls");
	cout << "************************关键词查询********************" << endl;
	cout << "   			1.继续查询" << endl;
	cout << "   			2.返回" << endl;
	cout << "   			3.退出" << endl;
	cout << "***************************************************" << endl;
	int m;
	cin >> m;

	switch (m)
	{
	case 1: keywordsearchview(); break;
	case 2: searchtickets(); break;
	case 3: shutdownview();
	default:keywordsearchview();
	}











*/






}

//**************************************************************初始地和目的地查询************************************************************************
void View::startandendsearchview()
{
	cout << "************************初始地和目的地查询********************" << endl;
	cout << "请输入要查询的起始地" << endl;
	char s[10];
	cin >> s;
	cout << "请输入要查询的目的地" << endl;
	char e[10];
	cin >> e;

	int ifexist = 0;
	for (int i = 0; tickets.locate(i) != NULL; i++)
	{
		if (!strcmp(tickets.locate(i)->data.start,s)  && !strcmp(tickets.locate(i)->data.end, e))
		{
			ifexist = 1;
			tickets.locate(i)->data.print();
		}

	}
	if (ifexist == 0)
		cout << "不存在该车次" << endl;
	system("pause");
	system("cls");
	cout << "************************关键词查询********************" << endl;
	cout << "   			1.继续查询" << endl;
	cout << "   			2.返回" << endl;
	cout << "   			3.退出" << endl;
	cout << "***************************************************" << endl;
	int m;
	cin >> m;

	switch (m)
	{
	case 1: startandendsearchview(); break;
	case 2: searchtickets(); break;
	case 3: shutdownview();
	default:startandendsearchview();
	}
}

//**************************************************************日期查询************************************************************************
void View::timesearchview()
{
	cout << "************************日期查询********************" << endl;
	cout << "请输入要查询的日期" << endl;
	int m, d;
	cin >> m;
	cin >> d;

	int ifexist = 0;
	for (int i = 0; tickets.locate(i) != NULL; i++)
	{
		if (tickets.locate(i)->data.month == m && tickets.locate(i)->data.day == d)
		{
			ifexist = 1;
			tickets.locate(i)->data.print();
		}

	}
	if (ifexist == 0)
		cout << "不存在该车次" << endl;
	system("pause");
	system("cls");
	cout << "************************日期查询********************" << endl;
	cout << "   			1.继续查询" << endl;
	cout << "   			2.返回" << endl;
	cout << "   			3.退出" << endl;
	cout << "***************************************************" << endl;
	int t;
	cin >> t;

	switch (t)
	{
	case 1: timesearchview(); break;
	case 2: searchtickets(); break;
	case 3: shutdownview();
	default:timesearchview();
	}
}


//**************************************************************退出函数************************************************************************
void View::shutdownview() 
{
	
	fstream ticketout("ticket.dat", ios::out | ios::binary);

	for (int i = 1; tickets.locate(i)->link != NULL; i++)
	{
		ticketout.write(reinterpret_cast<char*>((tickets.locate(i))), sizeof(tickets.getData(i)));
		tickets.locate(i)->data.print();
	}
	system("pause");

		


	fstream guestout("guest.dat", ios::out | ios::binary);
	for (int i = 1; guests.locate(i)->link != NULL; i++)
	{
		guestout.write(reinterpret_cast<char*>((guests.locate(i))), sizeof(guests.getData(i)));
		guests.locate(i)->data.print();
	}

	



	ticketout.close();
	guestout.close();


	
	exit(0);

}

void View::input(){
	fstream ticketin("ticket.dat", ios::in | ios::binary);
		ticket current2;
		guest current3;
		for(int i = 1; !ticketin.eof(); i++)
		{
			
			ticketin.read(reinterpret_cast<char*>(&current2), sizeof(ticket));
			if (ticketin.eof())break;
			tickets.insert(i, current2);
	//		tickets.locate(i + 1)->data.print();
			system("pause");
		}

		fstream guestin("guest.dat", ios::in | ios::binary);
		for (int i = 1; !guestin.eof(); i++)
		{
			
			guestin.read(reinterpret_cast<char*>(&current3), sizeof(guest));
			if (guestin.eof())break;
			guests.insert(i, current3);
	//		guests.locate(i + 1)->data.print();
			system("pause");
		}


		ticketin.close();
		guestin.close();
}