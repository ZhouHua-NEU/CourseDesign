#include "LinkedList.h"
#include <iostream>
using namespace std;

template <class T>
int LinkedList<T>::ListLength()  //求表长
{    LinkNode <T>*p=first->link;  //带头结点
    int j = 0;
    while(p!=NULL){
        j++;                //不空就加一
        p=p->link;    }     //指针移动
    return j;  }


template <class T>
void LinkedList <T>::makeEmpty()
{    LinkNode <T>* p;
    while(first->link!=NULL)
    {
        p=first->link;
        first->link =p->link;
        delete p;
    }}

template <class T>
T LinkedList <T>::getData(int index)
{    if(locate(index)==NULL||index<=0)
        exit(1);
    LinkNode<T>*p = locate(index);
         T x = p->data;
            return x;
}


template <class T>
bool LinkedList <T>::setData( int index , T  x )
{   if(locate(index)==NULL||index<=0)
        return false;
    LinkNode<T>* p = locate(index);
     p->data=x;
     return true;
}

template <class T>
bool LinkedList<T>::insert(int index,T x)   //
{
    LinkNode<T>* current = locate(index);
    LinkNode<T> * newNode = new LinkNode<T>(x);
    if(newNode==NULL||current==NULL)
    {cerr << "false" << endl;return false;}
    newNode->link = current->link;
    current->link = newNode;
    return true;

}

template <class T>
void LinkedList <T>::output()
{
    LinkNode<T>*p=first->link;
    for(int i=1;p!=NULL;i++)
    {
        cout << "#" << i << "   " << p->data << endl;
        p = p->link;
    }
}

template <class T>
bool LinkedList <T>::isEmpty()
{
    if(first->link==NULL)
        return true;
    else
        return false;
}

template <class T>
bool LinkedList <T>::remove( int index  )
{
    LinkNode<T>* current = locate(index-1);
    if (current == NULL || current->link == NULL)return false;

    LinkNode<T>*del = current->link;
    current->link=del->link;
    delete del;
    return true;    
}


template <class T>
void LinkedList <T>::upset()
{
    LinkNode<T> * temp1 = first->link;
    LinkNode<T> * temp2 = NULL;
    if(temp1!=NULL)
    {
        temp2=temp1->link;
        temp1->link=NULL;

    }
    while(temp2!=NULL)
    {
        temp1=temp2;
        temp2=temp1->link;
        temp1->link=first->link;
        first->link=temp1;
    }


}



template<class T>
void LinkedList <T>::deletemaxmin(T max,T min)
{
    LinkNode<T> * MAX=search(max);
    LinkNode<T> * MIN=search(min);     //定位指针
    LinkNode<T> * del=MIN;
    MIN->link=MAX;

    while(del->link!=MAX)   //删除空间
    {
        LinkNode<T> * current=del->link;
        del=del->link;
        delete current;
}

}




template<class T>
void LinkedList <T>::deletemaxmin2(T max,T min)//删除>max<min的数
{    LinkNode<T>* front = first;
    LinkNode<T>* rear = first->link;
    while(rear!=NULL)
        if(rear->data>max||rear->data<min)  //符合要求，删除
    {
        front->link=rear->link;
        delete rear;
        rear=front->link;
    }
    else                                //指针移动
    {
        front = rear;
        rear = rear->link;
    }



}


