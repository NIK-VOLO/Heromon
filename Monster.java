public class Monster
{
  private String name;
  private int health;
  private int shield;
  private boolean alive = true;
  private int strength;
  private int originalHealth;
  private boolean blocking = false;

  public Monster(Monster x){
    this.name = x.name;
    this.health = x.health;
    this.originalHealth = x.originalHealth;
    this.shield = x.shield;
    this.strength = x.strength;
    this.alive = x.alive;
  }

  public Monster(int health, int shield, int strength, String name){
    this.health = health;
    this.originalHealth = this.health;
    this.shield = shield;
    this.strength = strength;
    this.name = name;

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

  public double basicAttack(){
    //chance to miss based on % health left.
    double baseDamage = (double)this.strength / 2.0;
    //System.out.println(baseDamage);
    double attackModifier = (double)missRandom(0,(this.strength + (int)(this.strength / 2)));
    //System.out.println(attackModifier);
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
    double threeFourthsHealth = (double)originalHealth * 0.75;
    double halfHealth = (double)originalHealth / 2.0;
    double quarterHealth = (double)originalHealth / 4.0;


    if((double)health <= quarterHealth){
      return 0.75;
    }else if((double)health > quarterHealth && (double)health <= halfHealth){
      return 0.5;
    }else if((double)health > halfHealth && (double)health <= threeFourthsHealth){
      return 0.25;
    }else{return 0.0;}
  }

  public int missRandom(int min, int max)
{
   int range = (max - min) + 1;
   return (int)(Math.random() * range) + min;
}

public void monsterStatus(){
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




  public boolean getBlocking(){
    return this.blocking;
  }
  public int originalHealth(){
    return this.originalHealth;
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
  public void setBlocking(boolean b){
    this.blocking = b;
  }
}
