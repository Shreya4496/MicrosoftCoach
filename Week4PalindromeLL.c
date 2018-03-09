/*
Question: Function to check if a singly linked list is palindrome
Solution(Optimised Approach):

1. Given a linked list, find the middle element of the linked list
  1.1 if the number of elements in linked list is odd 
  		odd=true
  		else
  		odd=false
  		
2. Create a temporary variable Node* secondpart
   if odd
   		secondpart=mid->next;
   	else
   		secondpart=mid;
   		
3. Reverse the linked list with secondpart as the head
4. Compare the two lists for palindrome test
	head1=start
	head2=secondpart
	if true:
	linked list is palindrome
	else
	linked list is not a palindrome
5. Reverse the seconpart again to restore the linked list.

Time Complexity: O(n)
Space Complexity: O(1)
*/
#include<stdbool.h>
#include<stdio.h>
#include<stdlib.h>

struct Node
{
	char ele;
	struct Node *next;
}*start;

struct Node* createNode(char);
bool checkPalindrome(struct Node * );
struct Node* reverse(struct Node*);
bool compareLists(struct Node* , struct Node *);

void insert(char ele)
{
	if(!start)
		start=createNode(ele);
		
	else
	{
		struct Node * temp=start;
	
		while(temp->next)
			temp=temp->next;
		temp->next=createNode(ele);	
	}
}

struct Node* createNode(char ele)
{
	struct Node* newNode=(struct Node*)malloc(sizeof(struct Node));
	newNode->ele=ele;
	newNode->next=NULL;
	return newNode;
}

bool checkPalindrome(struct Node * start)
{
	if(!start || !start->next)
		return true;
 	
	bool odd=false;
	struct Node *fast_ptr=start;
	struct Node *slow_ptr=start;
	struct Node *prev_slow_ptr;
	
	while(fast_ptr && fast_ptr->next)
	{   prev_slow_ptr=slow_ptr;
		slow_ptr=slow_ptr->next;
		fast_ptr=fast_ptr->next->next;
	}

	if(fast_ptr)
	  odd=true;
   
	struct Node *prev_mid=prev_slow_ptr;
	struct Node* second_part;
	if(odd)
		second_part=prev_mid->next->next;
	else
	    second_part=prev_mid->next;
		
	struct Node *reverse_head=reverse(second_part);
	struct Node* tmp=prev_slow_ptr->next;
	prev_slow_ptr->next=NULL;
	bool res = compareLists(start, reverse_head);
	prev_slow_ptr->next=tmp;
	
	if(odd)
		prev_mid->next->next=reverse(reverse_head);
		
	else
		prev_mid->next=reverse(reverse_head);
		
	return res;
    
}

struct Node* reverse(struct Node* head_ref)
{
    struct Node* prev   = NULL;
    struct Node* current = head_ref;
    struct Node* next;
    while (current)
    {
        next  = current->next;
        current->next = prev;
        prev = current;
        current = next;
    }
    return prev;
    
}

bool compareLists(struct Node* head1, struct Node *head2)
{
    struct Node* temp1 = head1;
    struct Node* temp2 = head2;
    while (temp1 && temp2)
    {
       if (temp1->ele != temp2-> ele)
		   return false;
	   temp1 = temp1->next;
	   temp2 = temp2->next;
    }
 
    if (!temp1 && !temp2)
        return true;

    return false;
}

//TEST CASES

void createList(char *elements,struct Node *start)
{   
	start=NULL;
	int i;
    for (i = 0;elements[i]!='\0'; i++)
        insert(elements[i]);
}
bool checkPalindrome_EmptyString_True() 
{
	char elements[] = "";
    createList(elements,start);

    return checkPalindrome(start);
}

bool checkPalindrome_PalindromeString_True() 
{
	char elements[] = "$@*5*@$";
   createList(elements,start);

    return checkPalindrome(start);
}

bool checkPalindrome_CaseSensitivePalindromeString_True() 
{
	char elements[] = "AsBBsA";
    createList(elements,start);
    return checkPalindrome(start);
}

bool checkPalindrome_NotPalindromeString_False() 
{
	char elements[] = "acd$";
    createList(elements,start);

    return checkPalindrome(start);
}

bool checkPalindrome_NotPalindromeStringSingleLength_False() 
{
	char elements[] = "a";
    createList(elements,start);
    return checkPalindrome(start);
}
bool checkPalindrome_NotPalindromeStringOddLength_False() 
{
	char elements[] = "abc";
    createList(elements,start);
    return checkPalindrome(start);
}
int main() {
	char elements[]="abac";
	int i;
	createList(elements,start);
	if(checkPalindrome(start))
	   printf("Palindrome");
	else
	   printf("Not a Palindrome");
	   
	if(checkPalindrome_CaseSensitivePalindromeString_True()&&checkPalindrome_EmptyString_True()&&checkPalindrome_PalindromeString_True()&&!checkPalindrome_NotPalindromeString_False()&&checkPalindrome_NotPalindromeStringSingleLength_False()&&!checkPalindrome_NotPalindromeStringOddLength_False()  ) 
	   printf("All Test Cases passed") ;
	else
	   printf("Test Case Failed");
	return 0;
}


