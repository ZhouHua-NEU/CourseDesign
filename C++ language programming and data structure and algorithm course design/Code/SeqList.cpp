#ifndef SEQLIST_CPP
#define SEQLIST_CPP

	#include <iostream>
#include "SeqList.h"
	using namespace std;

	template <class T>
	SeqList<T>::SeqList(int max)
	{
		this ->array = new T[max];
		if(array == NULL)
		{
			cerr << "´æ´¢·ÖÅä´íÎó£¡"<< endl;
			exit(1);
		}
		this ->max = max;
		this ->last = -1;
	}

	template <class T>
	SeqList<T>::SeqList()
	{
		int max = 20;
		this->array = new T[max];
		if (array == NULL)
		{
			cerr << "´æ´¢·ÖÅä´íÎó£¡" << endl;
			exit(1);
		}
		this->max = max;
		this->last = -1;
	}

	template <class T>
	SeqList<T>::SeqList(SeqList & L)
	{
		this ->max = L.max;
		this ->last = L.last;
		this ->array = new T[max];
		if(array == NULL)
		{
			cerr << "´æ´¢·ÖÅä´íÎó£¡"<< endl;
			exit(1);
		}
		for(int i = 0; i <=last; i++)
		{
			this ->array[i] = L.array[i];
		}
	}

	template <class T>
	SeqList<T>::~SeqList()
	{
		
	}

	template <class T>
	void SeqList<T>::output()
	{
		for(int i = 0; i <= last; i++)
		{
			cout << array[i] << " ";
		}
		cout << endl;
	}

	template <class T>
	bool SeqList<T>::insert(T & num )
	{
		if(last == max - 1)return false;
		if(last < 0 )return false;
		array[last+1] = num;
		last ++;
		return true;
	}

	template <class T>
	bool SeqList<T>::remove(int position , T & x)
	{
		if(last == -1)return false;
		if(position < 0 || position > last)return false;
		x = array[position];
		for(int i = position; i < last; i++)
		{
			array[i] = array[i + 1];
		}
		last --;
	}

#endif