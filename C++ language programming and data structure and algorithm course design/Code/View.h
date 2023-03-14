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
	void loginview();       //主界面
	void signinview();

	void guestview();
	void adminview();        //账户界面

	void buytickets();         //普通vip的界面
	void searchbuytickets();
	void addmoney();

	void searchtickets();
	void addticketsview();      //管理员的界面
	void deleteticketsview();

	void keywordsearchview();
	void startandendsearchview();    //查询界面
	void timesearchview();

	void shutdownview();


private:
	LinkedList<guest> guests;           //存放用户账号信息
	LinkedList<ticket> tickets;			//存放售票信息
	LinkNode<guest>* current;			//当前账户的指针

};






