class View
{
public:
	View()
	{
		

	}

	~View()
	{

	}

public:
	void input();
	void MainView();
	void loginview();       //������
	void signinview();

	void guestview();
	void adminview();        //�˻�����

	void buytickets();         //��ͨvip�Ľ���
	void searchbuytickets();
	void addmoney();

	void searchtickets();
	void addticketsview();      //����Ա�Ľ���
	void deleteticketsview();

	void keywordsearchview();
	void startandendsearchview();    //��ѯ����
	void timesearchview();

	void shutdownview();


private:
	LinkedList<guest> guests;           //����û��˺���Ϣ
	LinkedList<ticket> tickets;			//�����Ʊ��Ϣ
	LinkNode<guest>* current;			//��ǰ�˻���ָ��

};






