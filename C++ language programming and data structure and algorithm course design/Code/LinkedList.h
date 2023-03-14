#ifndef LINKEDLIST_H
#define LINKEDLIST_H
#include <iostream>
using namespace std;

template <class T>
struct LinkNode{
    T data;
    LinkNode *link;
    LinkNode(LinkNode<T> * ptr =NULL)
    {
        link = ptr;         }
    LinkNode( T &item , LinkNode<T> * ptr = NULL)
    {
        data = item;
        link = ptr;         }

};

template <class T>
class LinkedList
{
    public:
        LinkedList(){first=new LinkNode<T>;}
        LinkedList(const T&x){first = new LinkNode<T>(x);}
        ~LinkedList(){makeEmpty();}
        int ListLength();
        T getData(int index);
        bool setData(int index,T x);
        bool insert(int index,T x);
        bool remove(int index);
        bool isEmpty();
        void makeEmpty();
        void output();
        void upset();
        void deletemaxmin(T max,T min);
        void deletemaxmin2(T max,T min);


LinkNode<T>  * locate(int a){
    if(a<0)
        cerr << "位置不合法" << endl;
    LinkNode<T>* current = first;
    for(int i = 1;i<a && current!=NULL;i++)
        current=current->link;
    return current;}

LinkNode<T>* search(T x){
    LinkNode<T>* p =first->link;
    while(p!=NULL && p->data!=x)
        p=p->link;
    return p;    }

    
        LinkNode<T>* first;
   
};
#endif // LINKEDLIST_H
