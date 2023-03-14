#ifndef SEQLIST_H
#define SEQLIST_H

template <class T>
class SeqList
{
private:
	T * array;
	int max;
	int last;
public:
	SeqList(int max);
	SeqList();
	SeqList(SeqList & L);
	~SeqList();
	void output();
	bool insert(T & num );
	bool remove(int position , T & x);

};

#endif
