/*
Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s.
A subtree of s is a tree consists of a node in s and all of this node's descendants.

SOLUTION:
1. Create the trees s and t taking the level order traversal as the output.
2. Check for the NULL cases
	2.1 if s and t both are NULL
		return true
	2.2 if s is NULL
		return false
	2.3 if t is NULL
		return true
3. if s->val==t->val
	Repeat the comparision for the rest of the s and t trees.
4.  Check for subtree either on s->left or s->right

*/
#include <bits/stdc++.h>
using namespace std;

struct node
{
    int val;
    struct node* left;
    struct node* right;
}*head_s,*head_t;

struct node* newNode(int val)
{
    struct node* ptr = (struct node*)malloc(sizeof(node));
    ptr->val = val;
    ptr->left = ptr->right = NULL;
    return ptr;
}

node* insertLevelOrder(int arr[], node* root,
                       int i, int n)
{
    if (i < n)
    {
        node* temp = newNode(arr[i]);
        root = temp;
         
        root->left = insertLevelOrder(arr,
                   root->left, 2 * i + 1, n);
 
        root->right = insertLevelOrder(arr,
                  root->right, 2 * i + 2, n);
    }
    return root;
}

bool isSubtree(struct node* s, struct node* t) {
    
    if(s==NULL && t==NULL)
        return true;
    if(s==NULL || t==NULL)
        return false;
    
    if(s->val==t->val&& isSubtree(s->left,t->left)&&isSubtree(s->right,t->right))
        return true;
            
    return isSubtree(s->left,t) || isSubtree(s->right,t);
       
}

bool checkSubtree(struct node* s, struct node* t)
{
	if (t == NULL) {
       return true;
    }
    return isSubtree(s,t); 
}
//TEST CASES

bool checkSubtree_NullCheckString_True()
{
	int elementOfS[]={26,10,3,4,6};
    int n=sizeof(elementOfS)/sizeof(elementOfS[0]);
    head_s = insertLevelOrder(elementOfS, head_s,0,n);
    head_t=NULL;
    
    return checkSubtree(head_s,head_t);
}

bool checkSubtree_NullStrings_True()
{
    head_s=NULL;
    head_t=NULL;
    
    return checkSubtree(head_s,head_t);
}
bool checkSubtree_NullMainTree_False()
{
    head_s=NULL;
    int elementOfT[]={10};
	int n=sizeof(elementOfT)/sizeof(elementOfT[0]);
    head_t = insertLevelOrder(elementOfT, head_t,0,n);
    
    return checkSubtree(head_s,head_t);
}
bool checkSubtree_NormalTrees_True()
{
	int elementOfS[]={3,4,5,1,2};
    int n=sizeof(elementOfS)/sizeof(elementOfS[0]);
    head_s = insertLevelOrder(elementOfS, head_s,0,n);

	int elementOfT[]={4,1,2};
	n=sizeof(elementOfT)/sizeof(elementOfT[0]);
    head_t = insertLevelOrder(elementOfT, head_t,0,n);
 	
    return checkSubtree(head_s,head_t);
}

bool checkSubtree_NormalTrees_False()
{
	int elementOfS[]={3,4,5,1};
    int n=sizeof(elementOfS)/sizeof(elementOfS[0]);
    head_s = insertLevelOrder(elementOfS, head_s,0,n);

	int elementOfT[]={4,1,2};
	n=sizeof(elementOfT)/sizeof(elementOfT[0]);
    head_t = insertLevelOrder(elementOfT, head_t,0,n);
 	
    return checkSubtree(head_s,head_t);
}

bool checkSubtree_EqualTrees_True()
{
	int elementOfS[]={3,4,5,1};
    int n=sizeof(elementOfS)/sizeof(elementOfS[0]);
    head_s = insertLevelOrder(elementOfS, head_s,0,n);

	int elementOfT[]={3,4,5,1};
	n=sizeof(elementOfT)/sizeof(elementOfT[0]);
    head_t = insertLevelOrder(elementOfT, head_t,0,n);
 	
    return checkSubtree(head_s,head_t);
}
int main()
{
    // TREE 1
    /* Construct the following tree
              26
            /   \
          10     3
        /    \     
      4      6      
       
    */
   
    int elementOfS[]={26,10,3,4,6};
    int n=sizeof(elementOfS)/sizeof(elementOfS[0]);
    head_s = insertLevelOrder(elementOfS, head_s,0,n);
    
    // TREE 2
    /* Construct the following tree
          10
        /    \
      4      6
       
    */
	int elementOfT[]={10,4,6};
	n=sizeof(elementOfT)/sizeof(elementOfT[0]);
    head_t = insertLevelOrder(elementOfT, head_t,0,n);
 	
    if (checkSubtree(head_s,head_t))
       cout<<"Tree T is subtree of Tree S"<<endl;
    else
       cout<<"Tree T is not a subtree of Tree S"<<endl;
 
 	if(checkSubtree_NullCheckString_True()&&checkSubtree_NullStrings_True()&&!checkSubtree_NullMainTree_False()&&checkSubtree_NormalTrees_True()&&!checkSubtree_NormalTrees_False()&&checkSubtree_EqualTrees_True())
 	   cout<<"All test cases passed";
	 
    return 0;
}

