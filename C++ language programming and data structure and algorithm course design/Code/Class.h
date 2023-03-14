//*************************************************��Ʊ��Ϣ***************************************************************

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

//*************************************************�˻���Ϣ***************************************************************
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

//*************************************************guest���������***************************************************************
bool operator==(guest a, guest b)
{
//	return a.name == b.name && a.passward == b.passward;
	return (!strcmp(a.name, b.name) && !strcmp(a.passward, b.passward));
}

bool operator!=(guest& a, guest& b)
{
	return strcmp(a.name, b.name) || strcmp(a.passward, b.passward);
}


//*************************************************ticket���������***************************************************************
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
	 os << "����:" << c.keywords << endl;
	 os << "����:" << c.month << "��" << c.day << "��" << endl;
	 os << "������:" << c.start << endl;
	 os << "�յ�:" << c.end << endl;
	 os << "�۸�:" << c.price << endl;
	return os;
}
