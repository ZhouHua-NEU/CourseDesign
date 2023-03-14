#include "include.h"           //包含所有头文件的头文件
using namespace std;

//*******************控制台*********************
void setconsole()
{
	SetConsoleTitle(L"售票信息管理系统");
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), BACKGROUND_GREEN);
}

//********************主函数***************************
int main()
{
	setconsole();   //控制台修饰
	 View view;
	 view.input();  //输入数据
	 view.MainView();  //主界面
}