public class Hero
{
  private String name;
  private int health;
  private int originalHealth;
  private int shield;
  private boolean alive = true;
  private int strength;
  private boolean blocking = false;
  private String[] inventory = new String[6];

  public Hero(Hero x){
    this.name = x.name;
    this.health = x.health;
    this.originalHealth = x.originalHealth;
    this.shield = x.shield;
    this.strength = x.strength;
    this.alive = x.alive;
    for(int i =0; i < this.inventory.length; i++){
      this.inventory[i] = x.inventory[i];
    }
  }

  public Hero(int health, int shield, int strength, String name, int playerClass){
    this.health = health;
    this.originalHealth = health;
    this.shield = shield;
    this.strength = strength;
    this.name = name;
    this.inventory[0] = "Health Potion"; //adds health
    this.inventory[1] = "Buckler"; //adds shield
    this.inventory[2] = "Fire Bomb"; //sets target on fire: small damage every turn
    this.inventory[3] = "Adrenaline Potion"; // increases strength for a turn
    this.inventory[4] = "Smoke Bomb"; //increases enemy chance to miss
    this.inventory[5] = "Exit";


  }

  public void loseHealth(int damage){
    //damage is dealt to shield first. Shield is weaker than health --> diminished quicker
      if(this.shield > 0){
        int overDamage = 0;

        this.shield -= damage * (1.5);

        if(this.shield <= 0){
          overDamage = Math.abs((int)((damage * 1.5) - damage));

          this.shield = 0;
          this.health -= overDamage;

        }
        typeMessage(getName() + " took " + damage *1.5 + " shield damage and " + overDamage + " health damage!\n");

    }else{
      typeMessage(getName() + " took " + damage + " damage!\n");
      this.health -= damage;
      if(this.health <= 0){
        setAlive(false);
      }
    }


  }

  public void block(){

  }

  public double basicAttack(){//Damage variance based on character strength.
    //chance to miss based on % health left.
    double baseDamage = (double)this.strength / 2.0;

    double attackModifier = (double)missRandom(0,(this.strength + (int)(this.strength / 2)));
  
    double damage;
    if(this.health > 0){
      int chance = missRandom(1,4);
      switch((int)(chanceToMiss() *100)){ //sets chanceToMiss to an integer value.
        case 25:  if(missRandom(1,4)== 1){
                  typeMessage(getName() + " missed!\n");
                  return 0.0;
                }
                break;
        case 50: if(missRandom(1, 2) == 1){
                  typeMessage(getName() + " missed!\n");
                  return 0.0;
                }
                break;
        case 75: if(chance == 1 || chance == 2 || chance == 3){
                  typeMessage(getName() + " missed!\n");
                  return 0.0;
                }
                break;
      }
      damage = baseDamage + attackModifier;
      typeMessage(getName() + " attacks for " + damage +"\n");
      if(attackModifier > this.strength*9/8){
        typeMessage("Critical Hit!");
      }
      return damage;

    }else{return 0;}
  }

  public double chanceToMiss(){ //determines based on health the %chance to miss an attack
    double threeFourthsHealth = (double)this.originalHealth * .75;
    double halfHealth = (double)this.originalHealth / 2.0;
    double quarterHealth = (double)this.originalHealth / 4.0;

    if((double)getHealth() <= quarterHealth){
      return 0.75;
    }else if((double)getHealth() > quarterHealth && (double)getHealth() <= halfHealth){
      return 0.50;
    }else if((double)getHealth() > halfHealth && (double)getHealth() <= threeFourthsHealth){
      return 0.25;
    }else{return 0.0;}
  }

  private int missRandom(int min, int max)
{
   int range = (max - min) + 1;
   return (int)(Math.random() * range) + min;
}

  public void playerStatus(){
    System.out.println(getName() + "-- Health: " + getHealth() + ", Shield: " + getShield() + ", Strength: " + getStrength() + ", Alive: " + getAlive());
  }

  public void typeMessage(String sentence){
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





  public String getInventory(int index){
    return inventory[index];
  }

  public boolean getBlocking(){
    return this.blocking;
  }

  public String getName(){
    return this.name;
  }
  public int getStrength(){
    return this.strength;
  }
  public boolean getAlive(){
    return this.alive;
  }
  public int getHealth(){
    return this.health;
  }
  public int getOriginalHealth(){
    return this.originalHealth;
  }
  public int getShield(){
    return this.shield;
  }
  public void setInventory(int index, String value){
    inventory[index] = value;
  }
  public void setBlocking(boolean b){
    this.blocking = b;
  }
  public void setName(String name){
    this.name = name;
  }
  public void setAlive(boolean a){
    this.alive = a;
  }
  public void setHealth(int h){
    this.health = h;
  }
  public void setOriginalHealth(int h){
    this.originalHealth = h;
  }
  public void setShield(int s){
    this.shield = s;
  }
  public void setStrength(int s){
    this.strength = s;
  }


}
