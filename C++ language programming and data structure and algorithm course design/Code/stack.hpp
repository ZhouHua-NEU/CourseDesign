#include <assert.h>
template<class T>
class stack {
    int top;
    T* elements;
    int maxSize;
public:
    stack(int sz = 50);
    bool Push(const T& x);
    bool Pop(T& x);
    bool getTop(T& x);
    bool IsEmpty()const { return (top == -1) ? true : false; }
    bool IsFull()const { return (top == maxSize - 1) ? true : false; }
    int getSize()const { return top + 1; }
    void MakeEmpty() { top = -1; }
};
template<class T>
stack<T>::stack(int sz):top(-1),maxSize(sz){
    elements = new T[maxSize];
    assert(elements != NULL);
}
template<class T>
bool stack<T>::Push(const T& x){
    if (IsFull() == true)return false;
    elements[++top] = x;
    return true;
}
template<class T>
bool stack<T>::Pop(T& x){
    if (IsEmpty() == true)return false;
    x = elements[top--];
    return true;
}
template<class T>
bool stack<T>::getTop(T& x){
    if (IsEmpty() == true)return false;
    x = elements[top];
    return true;
}