//*************************************************售票信息***************************************************************

class ticket
{
public:
	int keywords;
	int month;
	int day;
	char start[10];
	char end[10];
	int price;
	int number;
public:
	bool ifkeywords(int n);
	void print();
	friend ostream& operator<<(ostream& os, const ticket& c);

};

//*************************************************账户信息***************************************************************
class guest
{
public:
	char name[10];
	char passward[15];
	int money;
	SeqList<ticket> ticketsofguest;
	int varity;
public:
	void print();
	
};

//*************************************************guest运算符重载***************************************************************
bool operator==(guest a, guest b)
{
//	return a.name == b.name && a.passward == b.passward;
	return (!strcmp(a.name, b.name) && !strcmp(a.passward, b.passward));
}

bool operator!=(guest& a, guest& b)
{
	return strcmp(a.name, b.name) || strcmp(a.passward, b.passward);
}


//*************************************************ticket运算符重载***************************************************************
void operator&=(ticket& a, ticket& b)
{
	a.keywords=b.keywords;
	a.month=b.month;
	a.day = b.day;
	strcpy_s(a.start,b.start);
	strcpy_s(a.end,b.end);
	a.price=b.price;
	a.number = 1;
	
}

 ostream&operator<<(ostream& os, const ticket & c)
{
	 os << "车次:" << c.keywords << endl;
	 os << "日期:" << c.month << "月" << c.day << "日" << endl;
	 os << "出发地:" << c.start << endl;
	 os << "终点:" << c.end << endl;
	 os << "价格:" << c.price << endl;
	return os;
}
