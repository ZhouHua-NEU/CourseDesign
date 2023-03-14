#include "Class.h"

void ticket::print()
	{
			cout << "趟次:" << this->keywords << endl;
			cout << "日期:" << this->month << "月" << this->day << "日" << endl;
			cout << "起始地:" << this->start << endl;
			cout << "终点:" << this->end << endl;
			cout << "价格:" << this->price << endl;
			cout << "数量:" << this->number << endl;
	}

bool ticket::ifkeywords(int n)
	{
		return n==this->keywords;
	}
	
void guest::print()
	{
	cout << "尊敬的  " << this->name << "用户" << endl;
	cout << "您的余额为：" << this->money << endl;
	cout << "您的历史订单如下：" << endl;;
	ticketsofguest.output();

	}







	







