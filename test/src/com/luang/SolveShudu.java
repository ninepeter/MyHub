package com.luang;

import java.util.ArrayList;

public class SolveShudu {
	public int[] InsertIntoEight(int[] subnum)
	{
		if(SubValidLength(subnum)!=8)
			return subnum;
		int emptyPosition=-1;
		int emptyValue=-1;
		for(int i=0;i<9;i++)
		{
			if(subnum[i]==0)
			{
				emptyPosition=i;
				break;
			}
		}
		
		for(int i=1;i<=9;i++)
		{
			for(int j=0;j<9&emptyValue==-1;j++)
			{
				if(subnum[j]==i)
					break;
				if(j==8)
					emptyValue=i;
			}
		}
		
		
		subnum[emptyPosition]=emptyValue;
		return subnum;
	}	
	public int SubValidLength(int[] subnum)
	{
		ArrayList<Integer> list=new ArrayList<Integer>(9);
		for(int i=0;i<9;i++)
		{
			if(subnum[i]==0)
				continue;
			list.add(subnum[i]);
		}
		return list.size();
	}
	public int[] TryEighrSolve(int[] number)
	{
		//横向添数

		for(int i=0;i<9;i++)
		{
			int subnumber[]=new int[9];
			System.arraycopy(number, i*9, subnumber, 0, 9);
			subnumber=InsertIntoEight(subnumber);
			System.arraycopy(subnumber, 0, number, i*9, 9);
		}
			//纵向添数
		for(int i=0;i<9;i++)
		{
			int subnumber[]=new int[9];
			for(int j=0;j<9;j++)
			{
				subnumber[j]=number[9*j+i];
			}
			subnumber=InsertIntoEight(subnumber);
			for(int j=0;j<9;j++)
			{
				number[9*j+i]=subnumber[j];
			}
		}
			//子方阵添数
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
				subnumber=InsertIntoEight(subnumber);
				for(int m=0;m<3;m++)
				{
					for(int n=0;n<3;n++)
					{
						number[(9*(m+3*i))+(n+3*j)]=subnumber[3*m+n];
					}
				}
			}
		}
		
		
		return number;
	}
}
