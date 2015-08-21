
public class C_queue {

	int front, rear, size;
	Call a[];
	
	
	int getSize(){
		return (a.length);
		//return (this.size);
	}

	/*C_queue()
	{
		a = new int [5];
		front = rear = 1;
	}*/

	C_queue(int length){		
		size=length;
		front = rear = -1;
		a = new Call[size];
		for(int i=0;i<size;i++)
			a[i]= new Call();
	}

	int insert(Call call){
		int p,status;
		p = (rear+1)% a.length;

		if(p == front){
			status=1;
			System.out.println("Queue Overflow ");
			return status;
		}
		else{
			status=0;
			rear = p;
			a[rear] = call;
			
			if(front == -1)
				front = 0;
			return status;
		}
	}


	boolean empty(){
		if(front == -1)
			return true;
		else
			return false;
	}


	Call delete(){		//normal queue element deletion from front
		Call x = a[front];
		if(front == rear)
			front = rear = -1;
		else
			front =(front+1)% a.length;
		return x;
	}

	Call delete(int index){		//Delete this element and rearrange(move forward)	the element whose call has been completed
		int i=0;
		Call x = a[index];
		if(front == rear)
			front = rear = -1;
		else{
			//front =(front+1)% a.length;
			for (i = index; i < rear; i++) {
				a[i]=a[i+1];
			}
			rear=rear-1;
		}
		return x;
	}
	

	/*void display()
	{
		if(front == -1)
			System.out.println("Queue underflow");
		else
		{
			System.out.println("Elements of Queue are");
			int i = front;
			while(i != rear)
			{
				System.out.println(a[i]);
				i = (i+1)% a.length;
			}

			System.out.println(a[i]);

		}
	}*/


	void destroy(){
		front = rear=-1;
	}
}




/*
Circular Queue using array.....
Program for Circular Queue using Array



package datastructures;

import java.util.Scanner;
public class CircularQueue
{
 public static void main(String args[])
 {
    Queue q = new Queue(); //Queue of size 5
    int ch;
    Scanner sc =new Scanner(System.in);
    do
    {
    System.out.println("Menu\n1.insert\n2,delete\n3.display\n4.destroy\n5.exit\n");
    System.out.println("Enter choice :");

    ch=sc.nextInt();
    switch(ch)
    {
        case 1:
            System.out.println("Enter data to insert");
        int x=sc.nextInt();
        q.insert(x);
        break;
        case 2:
            if(q.empty())
            System.out.println("Queue underflow");
            else
            {
                int z =q.delete();
                System.out.println("data deleted =" + z );
            }
            break;
        case 3: q.display();
            break;

        case 4: q.destroy();
            break;
        case 5: break;

        default : System.out.println("Wrong Choice");
    }
    }while(ch!=5);
 }//end main
}//end CircularQueue
 */