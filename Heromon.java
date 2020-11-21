public class Heromon
{
  public static void main(String[] args)
  {
    typeMessage("Hello welcome to Heromon!");
    
    Hero hero = new Hero(createHero());
    Monster monster = new Monster(createMonster());

    typeMessage("You have run into a " + monster.getName() + "!\n");

    monster.monsterStatus();
    System.out.println();



    gameTest(hero, monster);


  }



  public static Hero createHero(){
    typeMessage("Create your hero!");

    typeMessage("What is your name? . . . ");

    String name = IO.readString();
    System.out.println();

    typeMessage("Type the number of your desired class: \n(1) Warrior (2)Thief");

    int playerClass = 0;
    while(playerClass != 1 && playerClass !=2){
      typeMessage("(Make sure you type a valid number)");

      playerClass = IO.readInt();
    }
    System.out.println();

    Hero hero = new Hero(0,0,0,"",playerClass);
    switch(playerClass){
      case 1: hero.setOriginalHealth(200);
              hero.setHealth(200);
              hero.setShield(0);
              hero.setStrength(25);
              hero.setName(name);
              break;
      case 2: hero.setOriginalHealth(100);
              hero.setHealth(100);
              hero.setShield(100);
              hero.setStrength(50);
              hero.setName(name);
              break;
    }

    hero.playerStatus();
    System.out.println();
    return hero;
  }


  public static Monster createMonster(){//random selection of premade monster
    Monster monster = new Monster(0,0,0,"");
    switch(monster.missRandom(1, 4)){
      case 1: monster.setName("Brute");
              monster.setHealth(100);
              monster.setOriginalHealth(100);
              monster.setShield(100);
              monster.setStrength(50);

              return monster;
      case 2: monster.setName("Stalker");
              monster.setHealth(50);
              monster.setOriginalHealth(50);
              monster.setShield(150);
              monster.setStrength(75);

              return monster;
      case 3: monster.setName("Gorgon");
              monster.setHealth(200);
              monster.setOriginalHealth(200);
              monster.setShield(0);
              monster.setStrength(50);

              return monster;
      case 4: monster.setName("Shell Back");
              monster.setHealth(50);
              monster.setOriginalHealth(50);
              monster.setShield(200);
              monster.setStrength(45);

              return monster;
    }

    monster.monsterStatus();
    System.out.println();
    return monster;
  }

  public static void statusUpdate(Hero hero, Monster monster){
    hero.playerStatus();
    monster.monsterStatus();
    System.out.println();
  }

  public static int attackMenu(){
    System.out.println("Attack Menu: (0)Exit (1)Attack (2)Defend");
    int x;
    do {
      x = IO.readInt();
    } while (x != 0 && x != 1 && x !=2); //checks that user input is valid
    switch(x){
      case 0: return 0;
      case 1: return 1;
      case 2: return 2;
    }
    return -1;
  }

  public static void monsterAttackTest(Monster monster){
    int choice;
    do {


      System.out.println(monster.chanceToMiss());
      monster.monsterStatus();
      monster.basicAttack();
      System.out.println("input a value: (0)exit (1)continue (2)damage monster");
      if(IO.readInt() == 2){
        monster.loseHealth(30);
      }
      choice = IO.readInt();
    } while (choice == 1);

  }

  public static void heroAttackTest(Hero hero){
    int choice;
    do {


      System.out.println(hero.chanceToMiss());
      hero.playerStatus();
      hero.basicAttack();
      System.out.println("input a value: (0)exit (1)continue (2)damage hero");
      if(IO.readInt() == 2){
        hero.loseHealth(30);
      }
      choice = IO.readInt();
    } while (choice == 1);

  }

  public static int checkEmpty(Hero hero){
    boolean empty = true;
    int value = inventoryMenu(hero);
    do {
    if(hero.getInventory(value).equals("Health Potion") || hero.getInventory(value).equals("Buckler") || hero.getInventory(value).equals("Fire Bomb") || hero.getInventory(value).equals("Adrenaline Potion") || hero.getInventory(value).equals("Smoke Bomb") || hero.getInventory(value).equals("Exit")){empty = false;}
      else{
        typeMessage("This slot is empty. . .");
        value = IO.readInt();
        empty = true;
      }
    } while (empty == true);
    return value;
  }

  public static void gameTest(Hero hero, Monster monster){
    boolean isIgnited = false;
    int tick =0;
    boolean isBuffed = false;
    int time =0;
    boolean isBlind = false;
    int timeBlind = 0;

    while(hero.getAlive() && monster.getAlive()){

      typeMessage("Choose an action. . .(0)Run Away -- (1)Attack Menu -- (2)Inventory ");
      int choice;
      do {
        choice = IO.readInt();
      } while (choice != 0 && choice != 1 && choice !=2); //checks that user input is valid

      switch(choice){
        case 0: typeMessage("Running Away. . . ");

                return;

        case 1: int x = attackMenu();
                if(x == 1){
                  double damage = hero.basicAttack();
                  hero.setBlocking(false);
                  if(damage != 0){
                    if(monster.getBlocking() == true){
                      typeMessage(monster.getName() + " blocked!");
                      monster.setBlocking(false);
                    }else{monster.loseHealth((int)damage);}
                  hero.setBlocking(false);
                  }
                }
                if(x == 2){
                  hero.setBlocking(true);
                }
                break;

        case 2: int value = checkEmpty(hero);

                if(value == 0 && hero.getInventory(0).equals("Health Potion")){
                  typeMessage(hero.getName() +" drinks a health potion and restores 100 health!");
                  if(hero.getHealth() + 100 > hero.getOriginalHealth()){
                    hero.setHealth(hero.getOriginalHealth());

                  }else{hero.setHealth(hero.getHealth() + 50);}
                  hero.setInventory(0, "Empty");
                }
                if(value == 1 && hero.getInventory(1).equals("Buckler")){
                  typeMessage(hero.getName() +" uses a buckler and gains 150 shield!\n");
                  hero.setShield(hero.getShield() + 150);
                  hero.setInventory(1, "Empty");
                }
                if(value == 2 && hero.getInventory(2).equals("Fire Bomb")){
                  typeMessage(hero.getName() +" throws a fire bomb and ignites the enemy!\n");
                  isIgnited = true;
                  hero.setInventory(2, "Empty");

                }
                if(value == 3 && hero.getInventory(3).equals("Adrenaline Potion")){
                  typeMessage(hero.getName() + " drinks an adrenaline potion and gains extra strength temporarily!\n");
                  isBuffed = true;
                  hero.setStrength(hero.getStrength() + 15);
                  hero.setInventory(3, "Empty");
                }
                if(value == 4 && hero.getInventory(4).equals("Smoke Bomb")){
                  typeMessage(hero.getName() + " throws a smoke bomb and blinds the enemy!");
                  isBlind = true;
                  hero.setInventory(4, "Empty");
                }
                break;


      }
      if(isBuffed ==true && time < 2){
        time ++;
        if(time == 2){
          isBuffed = false;
          hero.setStrength(hero.getStrength() - 15);
          typeMessage("The adrenaline has worn off. ");
        }
      }
      if(isIgnited == true && tick < 5){
        tick++;
        typeMessage(monster.getName() + " is on fire!");

        monster.loseHealth(10);
      }

      double damage = monsterAi(monster);
      if(damage != 0){
        if(hero.getBlocking() == true){
          if(isBlind ==true){timeBlind++;}
          typeMessage(hero.getName() + " Blocked!\n");
          hero.setBlocking(false);
        } else if(isBlind == true && timeBlind < 3){
          timeBlind++;
          if(timeBlind == 3){
            isBlind = false;
            typeMessage(monster.getName() + " is no longer blind");
            hero.loseHealth((int)damage);
          }else{typeMessage("but it's blind and misses!");}
        }else{hero.loseHealth((int)damage);}

      }
      statusUpdate(hero, monster);
    }

    if(!hero.getAlive()){
      typeMessage("YOU DIED. . . ");
    }else if(!monster.getAlive()){
      typeMessage("YOU'VE WON!");
    }
  }

  public static double monsterAi(Monster monster){
    double damage;
    int random;
    if(monster.getHealth() >= (monster.getOriginalHealth() /2)){
      random = monster.missRandom(1,4);
      if(random == 1){
        monster.setBlocking(true);
        return 0.0;
      }
    }

    if(monster.getHealth() > (monster.getOriginalHealth() /4) && monster.getHealth() < (monster.getOriginalHealth() /2)){
      random = monster.missRandom(1,3);
      if(random == 1){
        monster.setBlocking(true);
        return 0.0;
      }
    }

    if(monster.getHealth() <= (monster.getOriginalHealth() / 4)){
      random = monster.missRandom(1,4);
      switch(random){
        case 1: monster.setBlocking(true);
                return 0.0;
        case 2: monster.setAlive(false);
                if(monster.getHealth() < 0){
                  typeMessage(monster.getName() + " died!");
                }else{typeMessage(monster.getName() + " ran away!");}

                return 0.0;
      }
    }

    damage = monster.basicAttack();
    monster.setBlocking(false);
    return damage;
  }

  public static void typeMessage(String sentence){
    for(int i = 0; i < sentence.length(); i++){

      System.out.print(sentence.charAt(i));

      try {
        Thread.sleep(50);
      } catch (Exception e) {
        e.printStackTrace();
      }

    }
    System.out.println();
  }

  public static int inventoryMenu(Hero hero){
    typeMessage("Inventory:\n(0)" + hero.getInventory(0) + "\n(1)" + hero.getInventory(1) + "\n(2)" + hero.getInventory(2) + "\n(3)" + hero.getInventory(3) + "\n(4)" + hero.getInventory(4) +"\n(5)" + hero.getInventory(5));
    int choice;
    do {
      choice = IO.readInt();
    } while (choice != 0 && choice != 1 && choice !=2 && choice != 3 && choice !=4 && choice !=5); //checks that user input is valid
    switch(choice){
      case 0: return 0;
      case 1: return 1;
      case 2: return 2;
      case 3: return 3;
      case 4: return 4;
      case 5: return 5;

    }
    return -1;
  }






}
