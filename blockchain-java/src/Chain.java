import java.util.ArrayList;
import com.google.gson.*;

import models.Block;

public class Chain 
{
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 7;
	
	public static void main(String[] args) 
	{
		blockchain.add( new Block("Hi I am the first block", "0") );
		System.out.println("Trying to mine block 1 ... ");
		blockchain.get(0).mineBlock(difficulty);
		
		blockchain.add( new Block("Hi I am the second block", blockchain.get(blockchain.size()-1).hash) );
		System.out.println("Trying to mine block 2 ... ");
		blockchain.get(1).mineBlock(difficulty);
		
		blockchain.add( new Block("Hi I am the third block", blockchain.get(blockchain.size()-1).hash) );
		System.out.println("Trying to mine block 3 ... ");
		blockchain.get(2).mineBlock(difficulty);
		
		
		System.out.println("\nBlockchain is Valid: " + isChainValid());
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);		
		System.out.println( "The block chain: " );
		System.out.println(blockchainJson);
				
	}
	
	public static Boolean isChainValid()
	{
		Block currentBlock;
		Block previousBlock;
		
		for(int i=1; i < blockchain.size(); i++)
		{
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			
			// Compare registered hash and calculated one
			if(!currentBlock.hash.equals( currentBlock.calculateHash() ))
			{
				System.out.println("Current hashes not equal");
				return false;
			}
			
			// Compare previous hash and registered previous hash
			if( !previousBlock.hash.equals( currentBlock.previousHash ) )
			{
				System.out.println("Previous hash not equal");
				return false;
			}
		}
		
		return true;
	}

}
