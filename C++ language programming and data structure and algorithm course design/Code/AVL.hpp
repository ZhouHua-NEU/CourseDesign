class TreeNode{
public:
    int bf;
    ticket data;
    TreeNode* lc;
    TreeNode* rc;
    TreeNode():lc(NULL),rc(NULL),bf(0){}
    TreeNode(ticket& L, TreeNode* a = NULL, TreeNode* b = NULL) : data(L), lc(a), rc(b),bf(0){}
};


class AVL{
    friend class View;
    TreeNode* root;
    TreeNode* search(int x,TreeNode*r);
    bool Insert(ticket& T, TreeNode*& ptr);
    bool Remove(int x, ticket& el,TreeNode*& ptr);
    void RotateL(TreeNode*& ptr);   //×óµ¥Ðý
    void RotateR(TreeNode*& ptr);   //ÓÒµ¥Ðý
    void RotateLR(TreeNode*& ptr);  //×óÓÒË«Ðý
    void RotateRL(TreeNode*& ptr);  //ÓÒ×óË«Ðý
public:
    AVL():root(NULL){}
    TreeNode* search(int x) { return search(x, root); }
    bool Insert(ticket& T) { return Insert(T, root); }
    bool Remove(int x,ticket& el) { return Remove(x,el,root); }
};
TreeNode* AVL::search(int x, TreeNode* r){
    if (root == NULL)return NULL;
    else if (x < r->data.keywords)return search(x, r->lc);
    else if (x > r->data.keywords)return search(x, r->rc);
    else return r;
}
bool AVL::Insert(ticket& el, TreeNode*& ptr){
    TreeNode* pr = NULL, * p = ptr, * q; int d;
    stack<TreeNode*>st;
    while (p != NULL) {
        if (el.keywords == p->data.keywords)return false;
        pr = p; st.Push(pr);
        if (el.keywords < p->data.keywords)p = p->lc;
        else p = p->rc;
    }
    p = new TreeNode(el);
    if (p == NULL) { cerr << "´æ´¢¿Õ¼ä²»×ã£¡" << endl;system("pause"); exit(1); }
    if (pr == NULL) { ptr = p; return true; }
    if (el.keywords < pr->data.keywords)pr->lc = p;
    else pr->rc = p;
    while (st.IsEmpty() == false) {
        st.Pop(pr);
        if (p == p->lc)pr->bf--;
        else pr->bf++;
        if (pr->bf == 0)break;
        if (pr->bf == 1 || pr->bf == -1)p = pr;
        else {
            d = (pr->bf < 0) ? -1 : 1;
            if (p->bf == d) {
                if (d == -1)RotateR(pr);
                else RotateL(pr);
            }
            else {
                if (d == -1)RotateLR(pr);
                else RotateRL(pr);
            }
            break;
        }
    }
    if (st.IsEmpty() == true)ptr = pr;
    else {
        st.getTop(q);
        if (q->data.keywords > pr->data.keywords)q->lc = pr;
        else q->rc = pr;
    }
    return true;
}
bool AVL::Remove(int x, ticket& el, TreeNode*& ptr){
    TreeNode* pr = NULL, * p = ptr, * q, * ppr =NULL; int d, dd = 0;
    stack<TreeNode*>st;
    while (p != NULL) {
        if (x == p->data.keywords)break;
        pr = p; st.Push(pr);
        if (x < p->data.keywords)p = p->lc;
        else p = p->rc;
    }
    if (p == NULL)return false;
    if (p->lc != NULL && p->lc != NULL) {
        pr = p; st.Push(pr);
        q = p->lc;
        while (q->rc != NULL) {
            pr = q; st.Push(pr); q = q->rc;
        }
        p->data = q->data;
        p = q;
    }
    if (p->lc != NULL)q = p->lc;
    else q = p->rc;
    if (pr == NULL)ptr = q;
    else {
        if (pr->lc == p)pr->lc = q;
        else pr->rc = q;
        while (st.IsEmpty() == false) {
            st.Pop(pr);
            if (pr->rc == q)pr->bf--;
            else pr->bf++;
            if (st.IsEmpty() == false) {
                st.getTop(ppr);
                dd = (ppr->lc == pr) ? -1 : 1;
            }
            else dd = 0;
            if (pr->bf == 1 || pr->bf == -1)break;
            if (pr->bf != 0) {
                if (pr->bf < 0) { d = -1; q = pr->lc; }
                else { d = 1; q = pr->rc; }
                if (q->bf == 0) {
                    if (d == -1) {RotateR(pr); pr->bf = 1; pr->lc->bf = -1;}
                    else { RotateL(pr); pr->bf = -1; pr->rc->bf = 1; }
                    break;
                }
                if (q->bf == d) {
                    if (d == -1)RotateR(pr);
                    else RotateL(pr);
                }
                else {
                    if (d == -1)RotateLR(pr);
                    else RotateRL(pr);
                }
                if (dd == -1)ppr->lc = pr;
                else if (dd == 1)ppr->rc = pr;
            }
            q = pr;
        }
        if (st.IsEmpty() == true)ptr = pr;
    }
    delete p; return true;
}
void AVL::RotateL(TreeNode*& ptr){
    TreeNode* subL = ptr;
    ptr = subL->rc;
    subL->rc = ptr->lc;
    ptr->lc = subL;
    ptr->bf = subL->bf = 0;
}
void AVL::RotateR(TreeNode*& ptr){
    TreeNode* subR = ptr;
    ptr = subR->lc;
    subR->lc = ptr->rc;
    ptr->rc = subR;
    ptr->bf = subR->bf = 0;
}
void AVL::RotateRL(TreeNode*& ptr){
    TreeNode* subR = ptr, * subL = subR->lc;
    ptr = subL->rc;
    subL->rc = ptr->lc;
    ptr->lc = subL;
    if (ptr->bf <= 0)subL->bf = 0;
    else subL->bf = -1;
    subR->lc = ptr->rc;
    ptr->rc = subR;
    if (ptr->bf == -1)subR->bf = 1;
    else subR->bf = 0;
    ptr->bf = 0;
}
void AVL::RotateLR(TreeNode*& ptr){
    TreeNode* subL = ptr, * subR = subL->rc;
    ptr = subR->lc;
    subR->lc = ptr->rc;
    ptr->rc = subR;
    if (ptr->bf <= 0)subR->bf = 0;
    else subR->bf = -1;
    subL->rc = ptr->lc;
    ptr->lc = subR;
    if (ptr->bf == -1)subL->bf = 1;
    else subL->bf = 0;
    ptr->bf = 0;
}