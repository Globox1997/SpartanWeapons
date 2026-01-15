package net.spartanweapons.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@SuppressWarnings("unused")
@Config(name = "spartanweapons")
@Config.Gui.Background("minecraft:textures/block/stone.png")
public class SpartanWeaponsConfig implements ConfigData {
     @ConfigEntry.Gui.RequiresRestart
     public int battle_axe_attack_damage = 4;
     @ConfigEntry.Gui.RequiresRestart
     public float battle_axe_attack_speed = -2.5f;

     @ConfigEntry.Gui.RequiresRestart
     public int dagger_attack_damage = 0;
     @ConfigEntry.Gui.RequiresRestart
     public float dagger_attack_speed = -1.6f;
     @ConfigEntry.Gui.RequiresRestart
     public float dagger_range = -0.5f;

     @ConfigEntry.Gui.RequiresRestart
     public int glaive_attack_damage = 4;
     @ConfigEntry.Gui.RequiresRestart
     public float glaive_attack_speed = -2.6f;
     @ConfigEntry.Gui.RequiresRestart
     public float glaive_range = 2.0f;

     @ConfigEntry.Gui.RequiresRestart
     public int greatsword_attack_damage = 7;
     @ConfigEntry.Gui.RequiresRestart
     public float greatsword_attack_speed = -3.2f;
     @ConfigEntry.Gui.RequiresRestart
     public float greatsword_range = 2.0f;

     @ConfigEntry.Gui.RequiresRestart
     public int halberd_attack_damage = 6;
     @ConfigEntry.Gui.RequiresRestart
     public float halberd_attack_speed = -3.0f;
     @ConfigEntry.Gui.RequiresRestart
     public float halberd_range = 2.0f;

     @ConfigEntry.Gui.RequiresRestart
     public int katana_attack_damage = 4;
     @ConfigEntry.Gui.RequiresRestart
     public float katana_attack_speed = -2.3f;
     @ConfigEntry.Gui.RequiresRestart
     public float katana_range = 1.0f;

     @ConfigEntry.Gui.RequiresRestart
     public int mace_attack_damage = 9;
     @ConfigEntry.Gui.RequiresRestart
     public float mace_attack_speed = -3.2f;

     @ConfigEntry.Gui.RequiresRestart
     public int rapier_attack_damage = 1;
     @ConfigEntry.Gui.RequiresRestart
     public float rapier_attack_speed = -2.0f;
     @ConfigEntry.Gui.RequiresRestart
     public float rapier_range = 0.5f;

     @ConfigEntry.Gui.RequiresRestart
     public int scythe_attack_damage = 6;
     @ConfigEntry.Gui.RequiresRestart
     public float scythe_attack_speed = -3.0f;
     @ConfigEntry.Gui.RequiresRestart
     public float scythe_range = 1.5f;

     @ConfigEntry.Gui.RequiresRestart
     public int spear_attack_damage = 5;
     @ConfigEntry.Gui.RequiresRestart
     public float spear_attack_speed = -3.0f;
     @ConfigEntry.Gui.RequiresRestart
     public float spear_range = 2.5f;

     @ConfigEntry.Gui.RequiresRestart
     public int kunai_attack_damage = 0;
     @ConfigEntry.Gui.RequiresRestart
     public float kunai_attack_speed = -1.8f;
     @ConfigEntry.Gui.RequiresRestart
     public float kunai_range = -0.5f;

     @ConfigEntry.Gui.RequiresRestart
     public int javelin_attack_damage = 2;
     @ConfigEntry.Gui.RequiresRestart
     public float javelin_attack_speed = -2.4f;
     @ConfigEntry.Gui.RequiresRestart
     public float javelin_range = 1.0f;

     @ConfigEntry.Gui.RequiresRestart
     public int cutlass_attack_damage = 5;
     @ConfigEntry.Gui.RequiresRestart
     public float cutlass_attack_speed = -2.8f;
     @ConfigEntry.Gui.RequiresRestart
     public float cutlass_range = 1.0f;

     @ConfigEntry.Gui.RequiresRestart
     public int wooden_spear_attack_damage = 3;
     @ConfigEntry.Gui.RequiresRestart
     public float wooden_spear_attack_speed = -3.0f;
     @ConfigEntry.Gui.RequiresRestart
     public float wooden_spear_range = 2.5f;

     @ConfigEntry.Gui.RequiresRestart
     public int club_attack_damage = 4;
     @ConfigEntry.Gui.RequiresRestart
     public float club_attack_speed = -3.2f;

     @ConfigEntry.Gui.RequiresRestart
     public int staff_attack_damage = 2;
     @ConfigEntry.Gui.RequiresRestart
     public float staff_attack_speed = -2.4f;
     @ConfigEntry.Gui.RequiresRestart
     public float staff_range = 1.0f;
}