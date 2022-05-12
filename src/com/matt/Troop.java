package com.matt;

public class Troop {

    private int id;
    private int ammo;
    private double rations;
    private double water;
    private static int location;

    public Troop(int id, int ammo, double rations, double water, int location) {
        this.id = id;
        this.ammo = ammo;
        this.rations = rations;
        this.water = water;
        this.location = location;
    }




    public int getId() {
        return id;
    }

    public int getAmmo() {
        return ammo;
    }

    public double getRations() {
        return rations;
    }

    public double getWater() {
        return water;
    }

    public static int getLocation() {
        return location;
    }


    public static int move() {
        return getLocation() + 100;
    }

}