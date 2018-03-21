/*
Problem description: Design a vending machine.
Clarify: No more restrictions, just design a general vending machine we are familiar with.

Implementation of Java Vending Machine has following classes and interfaces:

1. Item: Class representing products to be sold
2. Cash: Class denoting legal Cash accepted in a country 
3. Stock<T> : Class denoting inventory of either products to be sold(Item) or money to be exchanged for the product(Cash)
4. CustomerRequest : A buyer class who buys product in exchange of money.
5. VendingMachineState : Interface for specifying multiple states a vending machine can be in.
6. CashInsertedState : A class denoting the state of Vending Machine when cash is inserted.
7. NoCashState : A class denoting the state of Vending Machine when cash is inserted.
8. NoItemState : A class denoting the state of Vending Machine when cash is inserted.
9. VendingMachine: Main Class implementing VendingMachineState

Please note this code represents all the important classes.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Item{
	
	public String item_id;
	public String item_name;
	public int item_quantity;
	public int item_price;

	public Item(String name,String itemId) {
		item_id = itemId;
		item_name = name;
		item_price = 0;
		item_quantity = 0;
	}

	public Item(String item_id, String item_name, int item_price, int item_quantity) {
		this.item_id = item_id;
		this.item_name = item_name;
		this.item_price = item_price;
		this.item_quantity = item_quantity;
	}
}

public class Cash {
    public String amt;
	public int quantity;
	
	public Cash(String amt){
        this.amt=amt;
        this.quantity=0;
    }
	
	public Cash(String amt,int quantity){
        this.amt=amt;
        this.quantity=quantity;
    }
   
    public int getValue(String amt){
        return Integer.parseInt(amt);
    } 
}    

public class Stock<T>{
	
	public HashMap<T, Integer> stock = new HashMap<T, Integer>();
	
	public void insert (T item,int quantity){
		stock.put(item, quantity);
	}
	
	public boolean hasItem(T item){ 
		return getItemQuantity(item) > 0; 	
	}
	
	public int getItemQuantity(T item){ 
		return (stock.get(item) != null?stock.get(item):0);	
	}
	
	public void addItem(T item){
		int cnt=stock.get(item);
		stock.put(item, cnt+1);
	}
	
	public void removeItem(T item){
		if (hasItem(item)) {
			int cnt = stock.get(item);
			stock.put(item, cnt - 1);
		}
	}  
}

public interface VendingMachineState
{
	 void selectProductAndInsertMoney(int amount, Item item);
	 void dispenseItem();
}


public class CashInsertedState implements VendingMachineState
{

	 @Override
	 public void selectProductAndInsertMoney( int amount,Item item )
	 {
		 Stock<Cash> cash_stock=new Stock<Cash>();
		 Cash cash=new Cash(Integer.toString(amount));
		 if(cash_stock.hasItem(cash)) {
			 System.out.println("Cash already inserted, wait for its completion ");
			 cash_stock.addItem(cash);
		 }
	 }

	 @Override
	 public void dispenseItem()
	 {
		 System.out.println("Item Dispensed");		 
	 }
}


public class NoCashState implements VendingMachineState
{
	 @Override
	 public void selectProductAndInsertMoney( int amount, Item item )
	 {
	    Stock<Cash> cash_stock=new Stock<Cash>();
	    Cash cash=new Cash(Integer.toString(amount));
	    if(!cash_stock.hasItem(cash))
	    	 System.out.println("Invalid Cash Entered");
	       
	 }

	 @Override
	 public void dispenseItem()
	 {
		System.out.println("Item cannot be dispensed, Cash not entered ");
	
	 }
}

public class NoItemState implements VendingMachineState
{
	 @Override
	 public void selectProductAndInsertMoney( int amount, Item item )
	 {
	    Stock<Item> item_stock=new Stock<Item>();
	    if(!item_stock.hasItem(item)||(item_stock.getItemQuantity(item)==0))
	    	 System.out.println("Selected Item is out of stock");
	       
	 }
	
	 @Override
	 public void dispenseItem()
	 {
	    System.out.println("Item cannot be dispensed");
	
	 }
}

public class VendingMachine implements VendingMachineState
{
	 private VendingMachineState vendingMachineState;
	
	 public VendingMachine()
	 {
		 vendingMachineState = new NoCashState();
	 }
	
	 public VendingMachineState getVendingMachineState()
	 {
		 return vendingMachineState;
	 }
	
	 public void setVendingMachineState( VendingMachineState vendingMachineState )
	 {
		 this.vendingMachineState = vendingMachineState;
	 }
	
	 @Override
	 public void selectProductAndInsertMoney( int amount, Item item )
	 {
		 vendingMachineState.selectProductAndInsertMoney(amount, item);
		 VendingMachineState cashInsertedState = new CashInsertedState();
	
		 if( vendingMachineState instanceof NoCashState )
				 setVendingMachineState(cashInsertedState);
	 }
	
	 @Override
	 public void dispenseItem()
	 {
		 VendingMachineState noCashState = new NoCashState();
		 vendingMachineState.dispenseItem();
		
		 if( vendingMachineState instanceof CashInsertedState )
			 	setVendingMachineState(noCashState);
	 }
}

public class CustomerRequest{
	public Item item_purchased;
	public int payment_made;
	VendingMachine vendingMachine;
	
	public CustomerRequest(Item item_purchased) {
		this.item_purchased=item_purchased;
		this.payment_made=0;
		this.vendingMachine = new VendingMachine();
	}
	public CustomerRequest(Item item_purchased,int payment_made) {
		this.item_purchased=item_purchased;
		this.payment_made=payment_made;
		if(vendingMachine==null)
			this.vendingMachine = new VendingMachine();
			
	}
	
	public void selectItemAndPay(Item purchased, int amt) {
		vendingMachine.dispenseItem();
		vendingMachine.selectProductAndInsertMoney(amt,purchased);
	}
}


