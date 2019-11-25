/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import TileMap.TileMap;

/**
 *
 * @author synophic
 */
public class Enemy extends MapObject{
    
    protected double health;
    protected double maxHealth;
    protected double ATK;
    protected double DEF;
    protected boolean dead;
    protected boolean hited;
    
    protected boolean invisible;
    protected long invisibleTimer;
    
    protected int enemyType;
    
    public static final int NORMAL = 0;
    public static final int BOSS = 0;
    
    public Enemy(TileMap tm) {
        super(tm);
    }

    public double getHealth() {
        return health;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public double getATK() {
        return ATK;
    }

    public double getDEF() {
        return DEF;
    }
    
    public int getType() {
        return enemyType;
    }

    public boolean isHited() {
        return hited;
    }

    public void setHited() {
        this.hited = false;
    }
    
    public boolean isDead() {
        return dead;
    }
    
    public void hit(double damage) {
        if(dead || invisible || health == 0) return;
        hited = true;
        damage *= 1 - (DEF * 0.35 / 100);
        health -= damage;
        if(health < 0) health = 0;
    }
    
    public void attack(double damage, Player player) {
        player.hit(damage);
    }
    
    public void update(Player p) {}
    protected void getNextPosition(Player p) {}
    
}
