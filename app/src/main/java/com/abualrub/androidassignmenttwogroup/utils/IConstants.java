package com.abualrub.androidassignmenttwogroup.utils;

import com.abualrub.androidassignmenttwogroup.R;
import com.abualrub.androidassignmenttwogroup.domain.Item;

// ************************************** //
//    MADE BY OSID ABU-ALRUB (1183096)    //
// ************************************** //
public interface IConstants {
    public static final double TAX_RATE = 0.14;

    public static final Item[] db = {
            new Item("Orange Shoes", 4.5, 121.0, Category.SHOES, R.drawable.shoes1),
            new Item("Black Running Shoes", 3.2, 1000.0, Category.SHOES,R.drawable.shoes2),
            new Item("Square Shoes", 3.5, 59.0, Category.SHOES,R.drawable.shoes3),
            new Item("Red With Check Mark Shoes", 5.0, 39.99, Category.SHOES,R.drawable.shoes4),
            new Item("Adidas Shoes", 1.2, 150.0, Category.SHOES,R.drawable.shoes5),
            new Item("Skateboarding Shoes", 3.2, 300.0, Category.SHOES,R.drawable.shoes6),
            new Item("Weight Shoes", 4.73, 500.0, Category.SHOES,R.drawable.shoes7),

            new Item("Five Black Shirts", 5.0, 500.0, Category.SHIRTS, R.drawable.shirts1),
            new Item("White Sports Shirt", 3.4, 92.0, Category.SHIRTS,R.drawable.shirts2),
            new Item("White B Shirt", 3.5, 59.21, Category.SHIRTS,R.drawable.shirts3),
            new Item("Skeleton Running Wear", 2.1, 68.99, Category.SHIRTS,R.drawable.shirts4),
            new Item("Funny Shirt", 3, 1521.0, Category.SHIRTS,R.drawable.shirts5),
            new Item("Women Gym Shirt", 3.5, 123.0, Category.SHIRTS,R.drawable.shirts6),
            new Item("Three Greyscale Shirts", 1.6, 2000.0, Category.SHIRTS,R.drawable.shirts7),

            new Item("Grey Running Pants", 5.0, 123.0, Category.PANTS, R.drawable.pants1),
            new Item("Blue-ish Pants", 3.1, 17.99, Category.PANTS,R.drawable.pants2),
            new Item("Military Pants", 3.5, 55.0, Category.PANTS,R.drawable.pants3),
            new Item("Women Yoga Pants", 2.3, 13.5, Category.PANTS,R.drawable.pants4),
            new Item("Gym Pants", 4.3, 10.0, Category.PANTS,R.drawable.pants5),
            new Item("Workout Pants x2", 4.5, 25.0, Category.PANTS,R.drawable.pants6),
            new Item("Skinny Gym Pants", 2.3, 100.0, Category.PANTS,R.drawable.pants7),

            new Item("Black Boxing Gloves", 4.32, 244.99, Category.BOXING, R.drawable.boxing1),
            new Item("Red Boxing Gloves", 4.2, 50.0, Category.BOXING,R.drawable.boxing2),
            new Item("Boxing Bag", 2.0, 150.0, Category.BOXING,R.drawable.boxing3),
            new Item("Boxing Ring", 4.3, 2500, Category.BOXING,R.drawable.boxing4),
            new Item("Hand Protector", 4.5, 10.0, Category.BOXING,R.drawable.boxing5),
            new Item("Black Boxing Bag", 4.8, 199.0, Category.BOXING,R.drawable.boxing6),
            new Item("Hand & Teeth Protectors", 3.42, 291.0, Category.BOXING,R.drawable.boxing7),

            new Item("Pit Balls For Kids x10", 2.32, 15.99, Category.BALLS, R.drawable.balls1),
            new Item("Ping Pong Balls x2", 3.2, 100.0, Category.BALLS,R.drawable.balls2),
            new Item("Golf Balls x15", 4.0, 31.0, Category.BALLS,R.drawable.balls3),
            new Item("Tennis Ball x1", 1.23, 10, Category.BALLS,R.drawable.balls4),
            new Item("Football x1", 4.9, 300.0, Category.BALLS,R.drawable.balls5),

            new Item("Baseball Bat", 5.0, 600.99, Category.BATS, R.drawable.bats1),
            new Item("Cricket Bat", 3.2, 321.0, Category.BATS,R.drawable.bat2),
            new Item("PING Golf Stick", 4.32, 1200.0, Category.BATS,R.drawable.bat3),

            new Item("Tennis Hat", 3.0, 200.99, Category.HATS, R.drawable.tennis1),
            new Item("Gym Hat", 4.2, 12.0, Category.HATS,R.drawable.hats2),
            new Item("Swimming Hat", 3.75, 33.3, Category.HATS,R.drawable.hats3),
    };
}
