/*
 * Developed by szczypiorofix on 24.08.18 13:38.
 * Copyright (c) 2018. All rights reserved.
 *
 */

package com.szczypiorofix.sweetrolls.game.main.core;

import com.szczypiorofix.sweetrolls.game.enums.ObjectType;
import com.szczypiorofix.sweetrolls.game.enums.ResourceType;
import com.szczypiorofix.sweetrolls.game.main.MainClass;

import java.util.HashMap;

import static com.szczypiorofix.sweetrolls.game.enums.ResourceType.FOOD;
import static com.szczypiorofix.sweetrolls.game.enums.ResourceType.WATER;
import static com.szczypiorofix.sweetrolls.game.enums.ResourceType.WOOD;
import static com.szczypiorofix.sweetrolls.game.enums.ResourceType.IRON;

public class TerrainResources {


    private ObjectType objectType;
    private HashMap<ResourceType, Resource> resources;

    public TerrainResources(ObjectType objectType) {
        this.objectType = objectType;
        resources = new HashMap<>();
        switch (objectType) {
            case PLAINS: {
                resources.put(FOOD, new Resource(FOOD, MainClass.RANDOM.nextInt(7) + 3));
                resources.put(WATER, new Resource(WATER, MainClass.RANDOM.nextInt(7) + 3));
                resources.put(WOOD, new Resource(WOOD, MainClass.RANDOM.nextInt(7) + 3));
                resources.put(IRON, new Resource(IRON, MainClass.RANDOM.nextInt(7) + 3));
                break;
            }
            case FOREST: {
                resources.put(FOOD, new Resource(FOOD, MainClass.RANDOM.nextInt(7) + 3));
                resources.put(WATER, new Resource(WATER, MainClass.RANDOM.nextInt(7) + 3));
                resources.put(WOOD, new Resource(WOOD, MainClass.RANDOM.nextInt(7) + 3));
                resources.put(IRON, new Resource(IRON, MainClass.RANDOM.nextInt(7) + 3));
                break;
            }
            case MOUNTAINS: {
                resources.put(FOOD, new Resource(FOOD, MainClass.RANDOM.nextInt(7) + 3));
                resources.put(WATER, new Resource(WATER, MainClass.RANDOM.nextInt(7) + 3));
                resources.put(WOOD, new Resource(WOOD, MainClass.RANDOM.nextInt(7) + 3));
                resources.put(IRON, new Resource(IRON, MainClass.RANDOM.nextInt(7) + 3));
                break;
            }
            default: {
                resources.put(FOOD, new Resource(FOOD, 0));
                resources.put(WATER, new Resource(WATER, 0));
                resources.put(WOOD, new Resource(WOOD, 0));
                resources.put(IRON, new Resource(IRON, 0));
                break;
            }
        }
    }

    public HashMap<ResourceType, Resource> getResources() {
        return resources;
    }

    public void collectResources(ResourceType resourceType) {
        resources.get(resourceType).collect();
    }

    public ObjectType getObjectType() {
        return objectType;
    }
}
