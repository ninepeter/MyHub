package com.luang;

import java.util.Arrays;
import java.util.Random;

public class A {
	public boolean IsShuDu(int number[])
	{
		//数字数量
		if(number.length!=81)
		{
			System.out.println("数组长度不正确");
			return false;
		}
		//横向判断
		for(int i=0;i<9;i++)
		{
			int subnumber[]=new int[9];
			System.arraycopy(number, i*9, subnumber, 0, 9);
			Arrays.sort(subnumber);
			int num_compared[]={1,2,3,4,5,6,7,8,9};
			if(!Arrays.equals(subnumber,num_compared))
			{
				System.out.println("不是数独");
				return false;
			}
		}
		//纵向判断
		for(int i=0;i<9;i++)
		{
			int subnumber[]=new int[9];
			for(int j=0;j<9;j++)
			{
				subnumber[j]=number[9*j+i];
			}
			Arrays.sort(subnumber);
			int num_compared[]={1,2,3,4,5,6,7,8,9};
			if(!Arrays.equals(subnumber,num_compared))
			{
				System.out.println("不是数独");
				return false;
			}
		}
		//子方阵判断
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				int subnumber[]=new int[9];
				for(int m=0;m<3;m++)
				{
					for(int n=0;n<3;n++)
					{
						subnumber[3*m+n]=number[(9*(m+3*i))+(n+3*j)];
					}
				}
				Arrays.sort(subnumber);
				int num_compared[]={1,2,3,4,5,6,7,8,9};
				if(!Arrays.equals(subnumber,num_compared))
				{
					System.out.println("不是数独");
					return false;
				}
			}
		}
		System.out.println("是数独");
		return true;
	}
	public int[] CreateShuduTable()
	{
		int number[]=new int[81];
		int start[]=CreateTable();
	//	ShowShudu(start);
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				for(int m=0;m<3;m++)
				{
					for(int n=0;n<3;n++)
					{
						int num=Getmod(3*m+n+3*j,9)+i;

						int line=3*(m+1)+3*j;
						if(line>9)
						{
							line-=9;
						}
						if(num>=line)
						{
							num-=3;
						}	
						number[(3*i+j)*9+3*m+n]=start[num];
					}
				}
			}
		
		}
		return number;
	}
	public void ShowShudu(int[] number) 
	{
	
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				System.out.print(number[i*9+j]+" ");
			}
			System.out.println();
		}
	}
	public int[] CreateTable()
	{
		int number[]=new int[81];
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				number[i+j*9]=i+1;
			}
		}
		return number;
	}
	public int Getmod(int x,int y)
	{	
		return x-((int)x/y)*y;	
	}
	public int[] HorizontalMoveShudu(int[] number)
	{
		Random r=new Random();
		int randomnumber1=r.nextInt(9);
		int randomnumber2;
		switch((int)randomnumber1/3)
		{
			case 0:
				do{randomnumber2=r.nextInt(3);}
				while(randomnumber2==randomnumber1);
				break;
			case 1:
				do{randomnumber2=r.nextInt(3)+3;}
				while(randomnumber2==randomnumber1);
				break;
			case 2:
				do{randomnumber2=r.nextInt(3)+6;}
				while(randomnumber2==randomnumber1);
				break;
			default:
				randomnumber2=0;
		}

		//System.out.println(randomnumber1);
		//System.out.println(randomnumber2);
		int subnumber[]=new int[9];
		
		
		for(int i=0;i<9;i++)
		{
			subnumber[i]=number[randomnumber2+i*9];
			number[randomnumber2+i*9]=number[randomnumber1+i*9];
			number[randomnumber1+i*9]=subnumber[i];
		}
		
		
		return number;		
	}
	public int[] VerticalMoveShudu(int[] number)
	{
		Random r=new Random();
		int randomnumber1=r.nextInt(9);
		int randomnumber2;
		switch((int)randomnumber1/3)
		{
			case 0:
				do{randomnumber2=r.nextInt(3);}
				while(randomnumber2==randomnumber1);
				break;
			case 1:
				do{randomnumber2=r.nextInt(3)+3;}
				while(randomnumber2==randomnumber1);
				break;
			case 2:
				do{randomnumber2=r.nextInt(3)+6;}
				while(randomnumber2==randomnumber1);
				break;
			default:
				randomnumber2=0;
		}

		//System.out.println(randomnumber1);
		//System.out.println(randomnumber2);
		int subnumber[]=new int[9];
		for(int i=0;i<9;i++)
		{
			subnumber[i]=number[randomnumber2*9+i];
			number[randomnumber2*9+i]=number[randomnumber1*9+i];
			number[randomnumber1*9+i]=subnumber[i];
		}
		return number;		
	}
	public int[] MoveShuduTable(int x,int y,int[] number)
	{
		for(;x>0;x--)
			number=HorizontalMoveShudu(number);
		for(;y>0;y--)
			number=VerticalMoveShudu(number);
		return number;
	}
	public int[] CreateShuduQuestionTable(int[] number,int x){
		
		int RandomNum[]=new int[x];
		RandomNum=GetRandomInt(number.length,x);
		for(int i=0;i<x;i++)
		{
			number[RandomNum[i]]=0;
		}
		return number;
	}
	public int[] GetRandomInt(int length,int quantity){
		
		int number[]=new int[quantity];
		Random r=new Random();
		for(int i=0;i<quantity;i++)
		{
			number[i]=r.nextInt(length);
			for(int j=0;j<i;j++)
			{
				if(number[i]==number[j])
				{
					number[i]=r.nextInt(length);
					j=-1;
				}
			}
		}
		
		return number;
	}
	
	
	
	public static void main(String args[])
	{
		A a=new A();
		int number[]=a.CreateShuduTable();
		a.ShowShudu(number);
		a.IsShuDu(number);
		number=a.MoveShuduTable(100, 100, number);
		a.ShowShudu(number);
		a.IsShuDu(number);
		number=a.CreateShuduQuestionTable(number,20);
		a.ShowShudu(number);
		a.IsShuDu(number);
		
		SolveShudu solve=new SolveShudu();
		number=solve.TryEighrSolve(number);
		a.ShowShudu(number);
		a.IsShuDu(number);
		
	}
}
