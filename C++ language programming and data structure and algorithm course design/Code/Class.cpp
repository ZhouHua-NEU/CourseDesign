#include "Class.h"

void ticket::print()
	{
			cout << "�˴�:" << this->keywords << endl;
			cout << "����:" << this->month << "��" << this->day << "��" << endl;
			cout << "��ʼ��:" << this->start << endl;
			cout << "�յ�:" << this->end << endl;
			cout << "�۸�:" << this->price << endl;
			cout << "����:" << this->number << endl;
	}

bool ticket::ifkeywords(int n)
	{
		return n==this->keywords;
	}
	
void guest::print()
	{
	cout << "�𾴵�  " << this->name << "�û�" << endl;
	cout << "�������Ϊ��" << this->money << endl;
	cout << "������ʷ�������£�" << endl;;
	ticketsofguest.output();

	}







	







