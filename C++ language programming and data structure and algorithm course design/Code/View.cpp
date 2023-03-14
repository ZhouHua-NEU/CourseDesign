#include "View.h"
#include "stack.hpp"
#include "AVL.hpp"

//*****************************************************************��ʼ����*********************************************************************
void View::MainView()
{
	system("cls");
	cout << "****************���ȵ�¼************************" << endl;
	cout << "   		1.��¼" << endl;
	cout << "   		2.ע��" << endl;
	cout << "   		3.�˳�" << endl;
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

//*****************************************************************��¼����*********************************************************************
void View::loginview()
{
	
	int change = 1;
	while (change)
	{
		system("cls");
		cout << "�������˺�" << endl;
		char n[10];
		cin >> n;

		cout << "����������" << endl;
		char p[15] ;
		cin >> p;
                guest idenity;
		strcpy_s(idenity.name,n);
		strcpy_s(idenity.passward,p);

		if(guests.search(idenity)==NULL)
		{
		cout << "�˺Ż��������" << endl;
		cout << "   	1.����������" << endl;
		cout << "   	2.��������" << endl;
		cout << "   	3.�˳�" << endl;
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

//*****************************************************************ע�����*********************************************************************
void View::signinview()
{
	while(1)
	{
	system("cls");
	cout << "�������˺�" << endl;
	char a[10];
	char b[15];
	char c[15];
	cin >> a;
	cout << "����������" << endl;
	cin >> b;
	cout << "ȷ������" << endl;
	cin >> c;
	if (!(strcmp(b,c)))
	{
		cout << "��ѡ��ע������" << endl;
		cout << "   			1.��ͨ�ο�" << endl;
		cout << "   			2.��̨����Ա" << endl;
		int d;
		cin >> d;
		guest current;
		strcpy_s(current.name,a);
		strcpy_s(current.passward, b);
		current.varity = d;
		guests.insert(0,current);
	
	
		system("cls");
		cout << "********************ע��ɹ�!************************" << endl;
		cout << "   		1.����������" << endl;
		cout << "   		2.��¼" << endl;
		cout << "   		3.�˳�" << endl;
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
		cout << "********************�����������벻һ�£��Ƿ�����ע��************************" << endl;
		cout << "   			1.����ע��" << endl;
		cout << "   			2.����������" << endl;
		cout << "   			3.�˳�" << endl;
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

//*****************************************************************��ͨvip����*********************************************************************
void View::guestview()
{
	system("cls");
	current->data.print();
	cout << "********************��ͨ�ο�************************" << endl;
	cout << "   			1.��ѯ��Ʊ" << endl;
	cout << "   			2.����Ʊ" << endl;
	cout << "   			3.�鿴�ѹ���Ʊ" << endl;
	cout << "   			4.��ֵ" << endl;
	cout << "   			5.����" << endl;
	cout << "   			6.�˳�" << endl;
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


//*****************************************************************��ֵ����*********************************************************************
void View::addmoney() {
	cout << "********************��ͨvip���� * ******************" << endl;
	cout << "������Ҫ��ֵ�Ľ��" << endl;
	int y;
	cin >> y;
	current->data.money += y;
	cout << "��ֵ�ɹ�" << endl;

	system("pasue");
	system("cls");
	current->data.print();
	cout << "********************��ֵҳ��************************" << endl;
	cout << "   			1.������ֵ" << endl;
	cout << "   			2.����" << endl;
	cout << "   			3.�˳�" << endl;
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

//*****************************************************************����Ա����*********************************************************************
void View::adminview()
{
	system("cls");
	cout << "********************����Ա����************************" << endl;
	cout << "   			1.���Ʊ����Ϣ" << endl;
	cout << "   			2.ɾ��Ʊ����Ϣ" << endl;
	cout << "   			3.��ѯƱ����Ϣ" << endl;
	cout << "   			4.�˳���¼" << endl;
	cout << "   			5.�˳�ϵͳ" << endl;
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

//*****************************************************************�����Ʊ��Ϣ*********************************************************************
void View::addticketsview()
{
	system("cls");
	cout << "********************�����Ʊ��Ϣ************************" << endl;
	ticket current1;
	cout << "�˴�" << endl;
	cin >> current1.keywords;
	cout << "�����·�" << endl;
	cin >> current1.month;
	cout << "���ں���" << endl;
	cin >> current1.day;
	cout << "��ʼ��" << endl;
	cin >> current1.start;
	cout << "�յ�" << endl;
	cin >> current1.end;
	cout << "�۸�" << endl;
	cin >> current1.price;
	cout << "����" << endl;
	cin >> current1.number;

	cout << "�˴�:" << current1.keywords << endl;
	cout << "����:" << current1.month << "��" << current1.day << "��" << endl;
	cout << "��ʼ��:" << current1.start << endl;
	cout << "�յ�:" << current1.end << endl;
	cout << "�۸�:" << current1.price << endl;
	cout << "����:" << current1.number << endl;

	cout << "�Ƿ����(y/n)" << endl;
	string n;
	cin >> n;
	if (n == "y" | n == "Y")
	{
		tickets.insert(0, current1);
		cout << "��ӳɹ�" << endl;
	}
	else
		cout << "ȡ����ӳɹ�" << endl;

	system("cls");
	cout << "********************�����Ʊ��Ϣ************************" << endl;
	cout << "   			1.�������Ʊ����Ϣ" << endl;
	cout << "   			2.����" << endl;
	cout << "   			3.�˳�" << endl;
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

//**************************************************************ɾ����Ʊ��Ϣ************************************************************************
void View::deleteticketsview()
{
	
	system("cls");
	cout << "********************ɾ����Ʊ��Ϣ************************" << endl;
	cout << "������Ҫɾ����Ʊ��Ϣ���˴Σ��粻ȷ�������Ȳ�ѯ" << endl;
	int n;
	cin >> n;

	int ifexist = 0;
	for (int i = 0; tickets.locate(i) != NULL; i++)
	{
		if (tickets.locate(i)->data.keywords == n)
		{
			ifexist = 1;
			tickets.locate(i)->data.print();
			cout << "�Ƿ�ɾ��(y/n)" << endl;
			string b;
			cin >> b;
			if (b == "y" | b == "Y")
			{
				tickets.remove(i);
				cout << "ɾ���ɹ�" << endl;
			}
			break;
		}
	}
	if(ifexist ==0)
		cout << "�����ڸó���" << endl;
	    system("pause");

	system("cls");
	cout << "********************ɾ��������Ϣ************************" << endl;
	cout << "   			1.����ɾ��������Ϣ" << endl;
	cout << "   			2.����" << endl;
	cout << "   			3.�˳�" << endl;
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


//**************************************************************��ѯ��Ʊ��Ϣ************************************************************************
void View::searchtickets()
{
	system("cls");
	cout << "*******************��ѡ���ѯ��ʽ************************" << endl;
	cout << "   			1.���β�ѯ" << endl;
	cout << "   			2.��ʼ���յ�ز�ѯ" << endl;
	cout << "   			3.�����ڲ�ѯ" << endl;
	cout << "   			4.����" << endl;
	cout << "   			5.�˳�" << endl;

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


//*****************************************************************��Ʊ*********************************************************************
void View::buytickets()
{
	cout << "������������ĳ���" << endl;
	int m;
	cin >> m;

	int ifexist = 0;
	for (int i = 0; tickets.locate(i) != NULL; i++)
	{
		if (tickets.locate(i)->data.keywords == m)
		{
			ifexist = 1;
			tickets.locate(i)->data.print();
			cout << "�Ƿ���(y/n)" << endl;
			string b;
			cin >> b;
			if (b == "y" | b == "Y")
			{
				if (current->data.money < 0)
					cout << "�������" << endl;
				else {
					guests.search(current)->data.ticketsofguest.insert(tickets.locate(i)->data);
					current->data.money -= tickets.locate(i)->data.price;
					tickets.locate(i)->data.number--;
					current = current->link;
					cout << "����ɹ�" << endl;
				}
			}
			break;
		}
	}
	if (ifexist == 0)
		cout << "�����ڸó���" << endl;
	system("pause");

	system("cls");
	cout << "********************��Ʊ************************" << endl;
	cout << "   			1.������Ʊ" << endl;
	cout << "   			2.����" << endl;
	cout << "   			3.�˳�" << endl;
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

//**************************************************************��ѯ�ѹ���ĳ�Ʊ************************************************************************
void View::searchbuytickets()
{
	cout << "************************��ʷ����********************" << endl;
	current->data.ticketsofguest.output();

	system("pause");

	system("cls");
	cout << "********************��ʷ����************************" << endl;
	cout << "   			1.����" << endl;
	cout << "   			2.�˳�" << endl;
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

//**************************************************************�ؼ��ʲ�ѯ************************************************************************
void View::keywordsearchview()
{
	cout << "************************�ؼ��ʲ�ѯ********************" << endl;
	cout << "������Ҫ��ѯ�ĳ���" << endl;
	int k;
	cin >> k;

	AVL avl;
	for (int i = 1; tickets.locate(i) != NULL; i++)
		avl.Insert(tickets.locate(i)->data);
	if (avl.search(k) == NULL)
	{
		cout << "�����ڸó���" << endl;
		system("pause");
		system("cls");
		cout << "************************�ؼ��ʲ�ѯ********************" << endl;
		cout << "   			1.������ѯ" << endl;
		cout << "   			2.����" << endl;
		cout << "   			3.�˳�" << endl;
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
		cout << "************************�ؼ��ʲ�ѯ********************" << endl;
		cout << "   			1.������ѯ" << endl;
		cout << "   			2.����" << endl;
		cout << "   			3.�˳�" << endl;
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












/*          ��ͨ��ѯ��ʽ   
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
		cout << "�����ڸó���" << endl;
	system("pause");
	system("cls");
	cout << "************************�ؼ��ʲ�ѯ********************" << endl;
	cout << "   			1.������ѯ" << endl;
	cout << "   			2.����" << endl;
	cout << "   			3.�˳�" << endl;
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

//**************************************************************��ʼ�غ�Ŀ�ĵز�ѯ************************************************************************
void View::startandendsearchview()
{
	cout << "************************��ʼ�غ�Ŀ�ĵز�ѯ********************" << endl;
	cout << "������Ҫ��ѯ����ʼ��" << endl;
	char s[10];
	cin >> s;
	cout << "������Ҫ��ѯ��Ŀ�ĵ�" << endl;
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
		cout << "�����ڸó���" << endl;
	system("pause");
	system("cls");
	cout << "************************�ؼ��ʲ�ѯ********************" << endl;
	cout << "   			1.������ѯ" << endl;
	cout << "   			2.����" << endl;
	cout << "   			3.�˳�" << endl;
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

//**************************************************************���ڲ�ѯ************************************************************************
void View::timesearchview()
{
	cout << "************************���ڲ�ѯ********************" << endl;
	cout << "������Ҫ��ѯ������" << endl;
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
		cout << "�����ڸó���" << endl;
	system("pause");
	system("cls");
	cout << "************************���ڲ�ѯ********************" << endl;
	cout << "   			1.������ѯ" << endl;
	cout << "   			2.����" << endl;
	cout << "   			3.�˳�" << endl;
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


//**************************************************************�˳�����************************************************************************
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