package p1;

import java.util.Random;
import java.util.Scanner;


public class Main {
	Random rand = new Random();
	Scanner input = new Scanner(System.in);
	String hero_text;
	int replay;
	int mMod;
	int gold;
	int HP;
	int maxHP;
	int level;
	int XP;
	int pAttack;
	int mAttack;
	int miscRand;
	int mHP;
	int STR;
	int DEX;
	int WIS;
	int damage;
	int mDamage;
	int mSTR;
	int weapon;
	int keys;
	int weaponCost;
	int armorCost;
	int armor;
	int lastRoom;
	int tempArmor;
	int lifeCost;
	int holyGrail;
	boolean ran;
	String monster;

	public void intro(){

		hero_text = input.nextLine();
		System.out.println("Hero: " + hero_text );
		switch(hero_text)
		{
		case "yes":
			System.out.println("Dungeon Boss: WELCOME TO THE DUNGEON");	
			entrance();
			break;
		case "no":
			if(replay == 1){
				System.out.println("Dungeon Boss: YOU WILL GET A PRIZE :)");	
				replay = 0;
				intro();
			}
			else{
				System.out.println("YOU WIN");
			}
			break;
		case "debug125":
			System.out.println("enter max HP");
			maxHP = input.nextInt();
			System.out.println(maxHP + " max HP");
			System.out.println("enter weapon power");
			weapon = input.nextInt();
			System.out.println(weapon + " weapon power");
			System.out.println("enter armor");
			armor = input.nextInt();
			System.out.println(armor + " armor");
			System.out.println("enter level");
			level = input.nextInt();
			System.out.println("you are level " + level);
			System.out.println("enter STR");
			STR = input.nextInt();
			System.out.println(STR + " STR");
			System.out.println("enter DEX");
			DEX = input.nextInt();
			System.out.println(DEX + " DEX");
			System.out.println("enter WIS");
			WIS = input.nextInt();
			System.out.println(WIS + " WIS");	
			System.out.println("enter gold");
			gold = input.nextInt();
			System.out.println(gold + " gold");
			entrance();
		default:
			System.out.println("Dungeon Boss: WHAT DID YOU SAY (say yes or no)");
			intro();
			break; }
	}




	public void entrance()  {
		System.out.println("returned to entrance healing to max HP");
		HP = maxHP;
		System.out.println("inventory: you have " + gold + " gold, " + armor + " armor, and a level " + weapon + " weapon");
		System.out.println("stats: you have " + HP + " out of " + maxHP + " HP remaning, "  + XP + " XP, " + STR + " STR, " + DEX + " DEX, " + WIS + " WIS, and are level " + level);		
		System.out.println("press n to go to next room or s to go to the shop, ");
		hero_text = input.nextLine();
		switch(hero_text)
		{
		case "n":
			nextRoom(rand.nextInt(8));
			break;
		case "s":
			shop();
			break;
		default:
			entrance();
			break;


		}

	}
	public void nextRoom(int roomGen){
		switch(roomGen)
		{
		case 0:
			for(int i = 0; i < armor; i ++){
				miscRand = rand.nextInt(3);
				switch (miscRand){
				case 0:
					tempArmor ++;
					break;
				default:
					break;
				}
			}
			miscRand = rand.nextInt(2 + level - (rand.nextInt(WIS) - 1));
			miscRand = miscRand + 1;
			System.out.println("the room is trapped - " + (miscRand-tempArmor) + "HP");
			HP -= (miscRand - tempArmor);
			roomCleared();
			break;
		case 1:
			System.out.println("you see a empty room press s to search the room or n move on");
			search();
			break;
		case 2:
			miscRand = rand.nextInt(3 + (level * 2) + rand.nextInt(WIS));
			miscRand ++;
			System.out.println("you find a tresure chest + " +  miscRand + " gold");
			gold += miscRand;
			roomCleared();
			break;
		case 3:
			System.out.println("you are getting stronger from your time in the dungeon +1 XP");
			XP ++;
			roomCleared();
			break;
		case 4:
			miscRand = rand.nextInt(level);
			miscRand ++;
			System.out.println("you find a healing potion + " +  miscRand + " HP");
			HP += miscRand;
			roomCleared();
			break;
		case 5:
			rareRoom();
			break;
		case 100:
			System.out.println("you have cleared this room");
			roomCleared();
			break;
		default:
			System.out.println("a monster is in the room");
			encounter();
			break;
		}
	}

	public void rareRoom(){
		miscRand = rand.nextInt(5);
		switch (miscRand){
		case 0:
			System.out.println("you find a hidden path out of the dungeon");
			entrance();
			break;
		case 1:
			System.out.println("a monster is in the room");
			mMod = 1;
			encounter();
			break;
		case 2:
			System.out.println("you are hit with a deadly curse - " + (HP-1) + " HP");
			HP = 1;
			roomCleared();
			break;
		case 3:
			switch (keys){
			case 0:
				System.out.println("you find a locked iron door and have no key");
				roomCleared();
				break;
			default:
				miscRand = rand.nextInt(10 + (level * 2) + rand.nextInt(WIS));
				miscRand = miscRand + 1;
				miscRand = miscRand * 2;
				System.out.println("you find a locked iron door and open it +" + miscRand + " gold");
				gold += miscRand;
				roomCleared();
				break;
			}
			break;
		case 4:
			legendaryRoom();
		default:
			break;
		}
	}

	public void legendaryRoom() {
		miscRand = rand.nextInt(5);
		switch (miscRand){
		case 1:
			miscRand = rand.nextInt(3);
			switch (miscRand){
			case 1:
				System.out.println("you find a magic well +1 STR");
				STR ++;
				break;
			case 2:
				DEX ++;
				break;
			case 3:
				System.out.println("you find a magic well +1 WIS");
				WIS ++;
				break;
			}
			roomCleared();
			break;
		case 2:
			System.out.println("you feel very weak -1 level -1 WIS -1 DEX -" + gold + " gold");
			level --;
			WIS --;
			DEX --;
			gold = 0;
			roomCleared();
			break;
		case 3:
			System.out.println("you find 100 gold");
			gold += 100;
			roomCleared();
			break;
		case 4:
			System.out.println("you find a magic armory +1 weapon power +1 armor and +5 maxHP");
			weapon += 1;
			armor += 1;
			maxHP += 5;
			roomCleared();
			break;
		case 5:
			System.out.println("you find a lich who drains your life force - 1 STR -5 max HP");
			STR --;
			maxHP -= 5;
			HP -= 5;
			roomCleared();
			break;
		default:
			break;
		}
	}




	public void roomCleared(){
		if(XP >= ((level + 1) * 2)){
			System.out.println("LEVEL UP");
			System.out.println("+2 max HP returning to entrance");
			maxHP += 2;	
			XP = 0;
			level ++;
			levelUp();
		}
		else{
			if(HP>0){
				if (HP > maxHP){
					HP = maxHP;
				}
				System.out.println("you have " + HP + " out of " + maxHP + " HP remaning " + gold + " gold level " + weapon + " equipment "  + XP + " XP and are level " + level);
				System.out.println("the room is now empty press l to return to entrance (heals to max HP and allows going to shop) (-" + ((level + 1) - DEX) + " gold) or n for next room " );
				hero_text = input.nextLine();
				switch(hero_text){
				case "l":
					if(gold>level - DEX){
						gold -= (level + 1) - DEX;
						entrance();
					}
					else{
						System.out.println("you don't have enough gold going to next room");
						nextRoom(rand.nextInt(8));
					}

					break;
				case "n":
					nextRoom(rand.nextInt(8));
					break;
				default:
					System.out.println("do l or n");
					roomCleared();
					break;
				}
			}
			else{
				System.out.println("YOU HAVE FALLEN");
			}
		}

	}


	public void levelUp() {
		System.out.println("what stat do you want to upgrade");
		System.out.println("press 1 to upgrade your STR (improved chance of hitting monsters)");
		System.out.println("press 2 to upgrade your DEX (leaving the dungeon costs less gold)");
		System.out.println("press 3 to upgrade your WIS (improved finds in rooms)");
		hero_text = input.nextLine();
		switch (hero_text){
		case "1":
			System.out.println("+1 STR");
			STR ++;
			break;
		case "2":
			System.out.println("+1 DEX");
			DEX ++;
			break;	
		case "3":
			System.out.println("+1 WIS");
			WIS ++;
			break;
		default:
			levelUp();
			break;	
		}
		entrance();		
	}




	public void search(){
		hero_text = input.nextLine();
		switch(hero_text){
		case "s":
			miscRand = rand.nextInt(10);
			switch(miscRand){
			case 0:
				miscRand = rand.nextInt(2 + level + rand.nextInt(WIS));
				miscRand ++;
				System.out.println("you find a scroll with valuable knowledge + " + miscRand + " XP");
				XP += miscRand;
				roomCleared();
				break;
			case 1:
				System.out.println("a monster was hiding in the room and he attacks");
				mMod = 2;
				encounter();
				break;
			case 2:
				miscRand = rand.nextInt(10 + (level * 2) + rand.nextInt(WIS));
				miscRand = miscRand + 1;
				miscRand = miscRand * 2;
				System.out.println("a large amount of gold was hidden in the room +" + miscRand + " gold");
				gold += miscRand;
				roomCleared();
				break;
			case 3:
				miscRand = rand.nextInt(5 + (level * 2));
				miscRand = miscRand + 1;
				System.out.println("you fall in a trap door -" + miscRand + " HP");	
				HP -= miscRand;
				roomCleared();
				break;
			case 4:
				switch (keys){
				case 0:
					System.out.println("you find a locked golden door and have no key");
					roomCleared();
					break;
				default:
					System.out.println("you find a locked golden door and open it +100 gold and level up");
					gold += 100;
					XP += 999999999;
					roomCleared();
					break;
				}
			case 5:
				legendaryRoom();
				break;
			case 6:
				rareFind();
				break;
			default:
				miscRand = rand.nextInt(2 + level + rand.nextInt(WIS));
				miscRand = miscRand + 1;
				System.out.println("you find " + miscRand + " gold");
				gold += miscRand;
				roomCleared();
				break;
			}
			break;
		case "n":
			roomCleared();
			break;
		default:
			System.out.println("do s or n");
			search();
			break;
		}

	}
	public void rareFind() {
		miscRand = rand.nextInt(5);	
		switch (miscRand){
		case 0:
			rareRoom();
			break;
		case 1:
			legendaryRoom();
			break;
		case 2:
			switch (keys){
			case 0:
				System.out.println("you find a locked platinum door and have no key");
				roomCleared();
				break;
			default:
				System.out.println("you find a locked platinum door and open it +2 WIS +250 gold and level up");
				gold += 250;
				XP += 999999;
				WIS += 2;
				roomCleared();
				break;
			}
			break;
		case 3:
			switch (keys){
			case 0:
				System.out.println("a portcullis closes trapping you in the dungeon YOU LOSE");
				break;
			default:
				System.out.println("a portcullis closes but you open it with your key");
				roomCleared();
				break;
			}
			break;
		case 4:
			switch (holyGrail){
			case 0:
				System.out.println("wow the room really is empty");
				roomCleared();
				break;
			case 1:
				System.out.println("you find the holy grail +1 STR +1 DEX +1 WIS +25 maxHP and level up");
				STR ++;
				DEX ++;
				WIS ++;
				maxHP += 25;
				XP += 9999999;
				holyGrail = 0;
				roomCleared();
				break;
			}
			break;
		default:
			break;
		}
	}




	public void encounter(){
		mSTR = rand.nextInt(3);
		mSTR += level -1;
		mSTR += mMod;
		mHP = ((mSTR + 1) * 2);  
		mMod = 0;

		switch (mSTR){
		case 0:
			monster = "Goblin";
			break;
		case 1:
			monster = "Zombie";
			break;
		case 2:
			monster = "Orc";
			break;
		case 3:
			monster = "Ogre";	
			break;
		case 4:
			monster = "Troll";
			break;
		case 5:
			monster = "Wright";
			break;
		case 6:
			monster = "Iron Golem";
			break;
		case 7:
			monster = "Wyvern";
			break;
		case 8:
			monster = "Mind flayer";
			break;
		case 9:
			monster = "Juggernaught";
			break;
		case 10:
			monster = "White Dragon";
			break;
		case 11:
			monster = "Green Dragon";
			break;
		case 12:
			monster = "Red Dragon";
			break;
		default:
			monster = "Ancient Red Dragon";
			break;

		}
		System.out.println("you see a " + monster + " press a to attack or r to run away");
		switch (mSTR - level){
		case -1:
			System.out.println("the monster looks easy");
			break;
		case 0:
			System.out.println("you think you can take the monster");
			break;
		case 1:
			System.out.println("the monster looks very hard you think you should run away");
			break;
		default:
			System.out.println("you have no chance against the monster");
			break;
		}
		System.out.println("you have " + HP + " out of " + maxHP + " HP remaining");
		combat();

	}
	public void combat(){

		hero_text = input.nextLine();
		ran = false;
		switch (hero_text){
		case "a":
			pAttack = rand.nextInt(6 + STR);
			mAttack = rand.nextInt(4 + mSTR);
			pAttack ++;
			mAttack ++;
			pAttack += level;
			mAttack += mSTR;
			if (pAttack >  mAttack){
				miscRand = rand.nextInt(5);
				damage = weapon;
				mDamage = 0;
				switch (miscRand){
				case 0:
					crit();
					break;
				default:
					rCombat();
					break;
				}

			}
			else{
				tempArmor = 0;
				for(int i = 0; i < armor; i ++){
					miscRand = rand.nextInt(2);
					switch (miscRand){
					case 0:
						tempArmor ++;
						break;
					default:
						break;
					}
				}
				mDamage = (mSTR + 1) - tempArmor;
				damage = 0;
				rCombat();
			}
			break;
		case "r":
			miscRand = rand.nextInt(3);
			switch (miscRand){
			case 0:
				System.out.println("you ran away from the " + monster);
				roomCleared();
				break; 
			default:
				tempArmor = 0;
				for(int i = 0; i < armor; i ++){
					miscRand = rand.nextInt(2);
					switch (miscRand){
					case 0:
						tempArmor ++;
						break;
					default:
						break;
					}
				}
				System.out.println("you failed to run away from the " + monster + " - " + ((mSTR + 1) - tempArmor) + " HP");
				HP -= (mSTR + 1) - tempArmor;
				ran = true;
				rCombat();
				break;
			}
			break;
		default:
			System.out.println("do a or r");
			combat();
			break;
		}
	}
	public void rCombat(){
		mHP -= damage;
		HP -= mDamage;
		if (mHP > 0 && HP > 0){
			if (ran){
				System.out.println("press a to attack or r to try to run away");
			}
			else if (pAttack > mAttack) {
				System.out.println("you hit the " + monster + " for " + damage + " damage press a to keep fighting or r to run away");

			}
			else{
				System.out.println("the " + monster + " hits you for " + mDamage + " damage press a to keep fighting or r to run away");
			}
			System.out.println("you have " + HP + " out of " + maxHP + "  HP remaining");
			combat();
		}
		else if (mHP < 1 && HP > 0){
			switch (monster){
			case "BOSS":
				System.out.println("YOU HAVE SLAIN THE BOSS YOU WIN");
				break;
			default:
				System.out.println("YOU HAVE SLAIN THE " + monster + " + " + (mSTR + 1) + " XP + " + ((mSTR +1) * 2) + " gold");
				XP += mSTR + 1;
				gold += (mSTR + 1) * 2;
				roomCleared();
				break;
			}
		}
		else{
			System.out.println("YOU HAVE FALLEN TO A " + monster);
			switch (monster){
			case "Troll":
				System.out.println("trololololololol");
				break;
			default:
				break;
			}
		}
	}
	public void crit(){
		miscRand = rand.nextInt(5);
		switch (miscRand){
		case 0:
			rareCrit();
			break;
		case 1:
			System.out.println("you hit the monster really hard and bruse your hand - " + mSTR + " HP + " + weapon + " damage");
			HP -= mSTR;
			damage += weapon; 
			rCombat();
			break;
		case 2:
			System.out.println("you get a good hit on the monster and feel very happy + " + mSTR + " XP + 1 damage");
			XP += mSTR;
			damage ++;
			rCombat();
			break;
		case 3:
			System.out.println("you miss");
			damage = 0;	
			rCombat();
			break;
		case 4:
			System.out.println(" wow a normal crit " + (level  + weapon) + " damage" );
			damage += (level + weapon);
			rCombat();
			break;
		}
	}
	public void rareCrit(){
		miscRand = rand.nextInt(3);
		switch (miscRand){
		case 0:
			System.out.println("as you are attacking the monster you find some gold on the ground and pick it up instead of attacking " + (mSTR * 2) + " gold");
			gold += (mSTR * 2);
			damage = 0;
			rCombat();
			break;
		case 1:
			System.out.println("you kill the monster");
			damage = mHP;
			rCombat();
			break;
		case 2:
			System.out.println("the monster runs away");
			roomCleared();
		case 3:
			System.out.println("you miss and the monster counter attacks - " + (mSTR*2) + " HP");
			damage = 0;
			HP -= (mSTR * 2);
			rCombat();
			break;
		case 4:
			System.out.println("the monster gets a hit on your weapon - 1 weapon power");
			damage = 0;
			weapon --;
			rCombat();
			break;

		}
	}


	public void shop(){
		System.out.println("welcome to the shop you have " + gold + " gold press 6 to return to entrance");
		System.out.println("press 1 to upgrade your armor for " + armorCost +" gold" );
		System.out.println("press 2 to buy a key for 25 gold");
		System.out.println("press 3 to upgrade your weapon for " + weaponCost +" gold" );
		System.out.println("press 4 to upgrade your maxHP for " + lifeCost +" gold" );
		System.out.println("press 5 to buy the boss key and fight the boss for 50 gold (recommended level 13)");
		hero_text = input.nextLine();
		switch (hero_text){
		case "1":
			if (gold > (armorCost - 1)){
				System.out.println("you upgraded your armor");
				armor ++;
				gold -= armorCost;
				armorCost += 3;
				shop();	
			}
			else{
				System.out.println("YOU DON'T HAVE ENOUGH GOLD");
				shop();
			}			
			break;
		case "2":
			switch (keys){
			case 0:
				if (gold > 24){
					System.out.println("you now can open locked doors");
					keys ++;;
					gold -= 25;
					shop();
				}
				else{
					System.out.println("YOU DON'T HAVE ENOUGH GOLD");
					shop();
				}
				break;				
			default:
				System.out.println("YOU ALREADY HAVE A KEY");
				shop();
				break;
			}

			break;
		case "3":
			if (gold > (weaponCost - 1)){
				System.out.println("you upgraded your weapon");
				weapon ++;
				gold -= weaponCost;
				weaponCost += 5;
				shop();
			}
			else{
				System.out.println("YOU DON'T HAVE ENOUGH GOLD");
				shop();
			}			
			break;
		case "4":
			if (gold > (lifeCost - 1)){
				System.out.println("+1 maxHP");
				maxHP ++;
				gold -= lifeCost;
				lifeCost ++;
				shop();
			}
			else{
				System.out.println("YOU DON'T HAVE ENOUGH GOLD");
				shop();
			}	
			break;
		case "5":
			if (gold > 49){
				gold -= 50;
				bossKey();
			}
			else{
				System.out.println("YOU DON'T HAVE ENOUGH GOLD");
				shop();
			}	
			break;
		case "6":
			entrance();
			break;
		default:
			shop();
			break;
		}
	}
	public void bossKey (){
		System.out.println("are you sure you want to fight the boss press a if you are sure press s to cancel");
		hero_text = input.nextLine();
		switch (hero_text){
		case "a":
			System.out.println("THE BOSS FIGHT HAS BEGUN");
			System.out.println("do a to attack or r to run away (ends boss fight)");
			bossFight();
			break;
		case "s":
			gold += 50;
			shop();
			break;
		default:
			System.out.println("do a or s");
			bossKey();
			break;
		}
	}
	public void bossFight(){
		monster = "BOSS";
		mHP = 100;
		mSTR = 10;
		combat();
	}
	public static void main(String args[])
	{
		Main r = new Main();
		r.gold = 0;
		r.maxHP = 10;
		r.HP = r.maxHP;
		r.replay = 1;
		r.level = 1;
		r.XP = 0;
		r.STR = 1;
		r.DEX = 1;
		r.WIS = 1;
		r.weapon = 1;
		r.keys = 0;
		r.weaponCost = 10;
		r.armorCost = 6;
		r.mMod = 0;
		r.armor = 0;
		r.tempArmor = 0;
		r.lifeCost = 3;
		r.ran = false;
		r.holyGrail = 1;
		System.out.println("Dungeon Boss: DO YOU WISH TO ENTER THE DUNGEON");
		r.intro();


	}


}
