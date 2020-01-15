package businesslogic.domain.map;

import businesslogic.client.StateGame;
import businesslogic.domain.entity.Item;
import businesslogic.domain.unit.Minion;
import businesslogic.domain.unit.Monster;
import businesslogic.server.FacadeServer;

public class AllTests {

	public static void main(String[] args) {

		FacadeServer f = new FacadeServer();
		StateGame stg = new StateGame();
		f.stateGame = stg;
		Monster monsterMunch = new Monster();
		f.stateGame.addMonster(monsterMunch);
		String monsterID = String.valueOf(monsterMunch.getIdUnit());
		
		
		//TEST1
		System.out.println();
		System.out.print("Test : changeDungeonColor to blue : ");
		f.changeDungeonColor("blue");
		System.out.println(f.stateGame.getMap().getDungeon().getColor().equals("blue"));
		System.out.println();
		
		//TEST2 
		System.out.print("Test : changeDungeonName to myDungeon : ");
		f.changeDungeonName("myDungeon");
		System.out.println(f.stateGame.getMap().getDungeon().getName().equals("myDungeon"));
		System.out.println();
		
		//TEST3
		System.out.print("Test : upgradeDungeon : ");
		int level = f.stateGame.getMap().getDungeon().getLevel();
		f.upgradeDungeon(monsterID);
		System.out.println(level+1 == f.stateGame.getMap().getDungeon().getLevel());
		System.out.println();
		
		//TEST4
		System.out.print("Test : select minion : ");
		Minion minion = new Minion();
		String minionID = String.valueOf(minion.getIdUnit()); 
		f.stateGame.getMap().getDungeon().addMinion(minion);
		f.selectMinion(monsterID, minionID);
		System.out.println(monsterMunch.getMinion().getIdUnit() ==  Integer.parseInt(minionID));
		System.out.println();
		
		//TEST5
		System.out.print("Test : unselect minion : ");
		f.stateGame.unSelectMinion(monsterID);
		System.out.println(f.stateGame.getMonsterByID(monsterID).getMinion() == null );
		System.out.println();
		
		//TEST6
		System.out.print("Test : return Dialogue : ");
		System.out.println(f.stateGame.returnDialogue(minionID));
		System.out.println();
		
		//TEST7
		System.out.print("Test : craftItem : ");
		f.craftItem(monsterID);
		System.out.println(f.stateGame.getMonsterByID(monsterID).getItems().size() > 0);
		System.out.println();
		
		//TEST8
		System.out.print("Test : upgradeItem : ");
		Item itemToUpgrade = f.stateGame.getMonsterByID(monsterID).getItems().get(0);
		String itemID = String.valueOf(itemToUpgrade.getIdItem());
		f.upgradeItem(monsterID, itemID);
		System.out.println(itemToUpgrade.getAttack() > 5);
		System.out.println();
		
		//TEST9
		System.out.print("Test : buyItem : ");
		f.buyItem(monsterID, "sword");
		System.out.println(f.stateGame.getMonsterByID(monsterID).getItems().size()>1);
		System.out.println();
				
		//TEST10
		System.out.print("Test : sellItem : ");
		Item ItemToSell = f.stateGame.getMonsterByID(monsterID).getItems().get(1);
		String itemIDToSell = String.valueOf(ItemToSell.getIdItem());
		f.sellItem(monsterID, itemIDToSell);
		System.out.println(f.stateGame.getMonsterByID(monsterID).getItemById(itemIDToSell) == null );
		System.out.println();
		
		//TEST11
		System.out.print("Test : storeItem : ");
		f.stateGame.createItem(monsterID);
		Item item = f.stateGame.getMonsterByID(monsterID).getItems().get(1);
		f.storeItem(monsterID, String.valueOf(item.getIdItem()));
		System.out.println(f.stateGame.getMap().getDungeon().getChest().size()>0);
		System.out.println();
		
		//TEST12
		System.out.print("Test : pickUpItem : ");
		f.pickUpItem(monsterID, String.valueOf(item.getIdItem()));
		System.out.println(f.stateGame.getMap().getDungeon().getChest().size()==0);
		System.out.println();
		
		
		//TEST13
		System.out.print("Test : editCharacteristic : ");
		System.out.println(f.editCharacteristic(monsterID, "health", "100"));
		System.out.println();
		
		//TEST14
		System.out.print("Test : editCharacteristic : ");
		System.out.println(f.editCharacteristic(monsterID, "attack", "50"));
		System.out.println();
		
		//TEST15
		System.out.print("Test : editCharacteristic : ");
		System.out.println(f.editCharacteristic(monsterID, "defense", "25"));
		System.out.println();

		//TEST16
		System.out.println("Test : seeCharacteristics : ");
		System.out.println(f.seeCharacteristics(monsterID));
		System.out.println();
		
	}
}
