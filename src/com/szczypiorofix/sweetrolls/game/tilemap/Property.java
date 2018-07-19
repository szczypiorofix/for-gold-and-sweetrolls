package com.szczypiorofix.sweetrolls.game.tilemap;

public class Property {

    private String name;
    private PropertyType type;
    private String value;


    public Property(String name, String type, String value) {

        this.name = name;
        this.value = value;
        switch (type) {
            case "bool": {
                this.type = PropertyType.BOOLEAN;
                break;
            }
            case "int": {
                this.type = PropertyType.INTEGER;
                break;
            }
            case "string": {
                this.type = PropertyType.STRING;
                break;
            }
            case "file": {
                this.type = PropertyType.FILE;
                break;
            }
            case "float": {
                this.type = PropertyType.FLOAT;
                break;
            }
            case "color": {
                this.type = PropertyType.COLOR;
                break;
            }
        }
    }

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    public PropertyType getType() {
        return type;
    }

//    public void setType(PropertyType type) {
//        this.type = type;
//    }

    public String getValue() {
        return value;
    }
//
//    public void setValue(String value) {
//        this.value = value;
//    }
}
