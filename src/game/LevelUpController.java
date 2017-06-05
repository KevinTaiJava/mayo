package game;

import java.util.ArrayList;
import java.util.List;

public class LevelUpController {

  private static LevelUpOption[] options = new LevelUpOption[]{
    new LevelUpOption("Increased hit points"){
      public void invoke(Mob mob) { mob.gainMaxHp(); }
    },
    new LevelUpOption("Increased attack value"){
      public void invoke(Mob mob) { mob.gainAttackValue(); }
    },
    new LevelUpOption("Increased defense value"){
      public void invoke(Mob mob) { mob.gainDefenseValue(); }
    },
    new LevelUpOption("Increased vision"){
      public void invoke(Mob mob) { mob.gainVision(); }
    }
  };
  
  public void autoLevelUp(Mob mob){
	    options[(int)(Math.random() * options.length)].invoke(mob);
  }

  public ArrayList<String> getLevelUpOptions(){
		ArrayList<String> names = new ArrayList<String>();
		for (LevelUpOption option : options){
			names.add(option.name());
		}
		return names;
	}
	
  public LevelUpOption getLevelUpOption(String name){
	  for (LevelUpOption option : options){
		  if (option.name().equals(name))
			  return option;
		  }
		  return null;
	}
}
