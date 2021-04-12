package models;

import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class Person implements Comparable<Person>{
    private String name;
    private int id;
    private String role;
    private int level;
    private String request;

    public String getRequest() {
        return request;
    }

    public Person(int id, String name, String role){
        this.id = id;
        this.name = name;
        this.role = role;


        if(role.equalsIgnoreCase("Teacher")){
            this.level = 17;
        }

    }

    @Override
    public int compareTo(Person person) {
        if(this.level < person.level || this.level == person.level)
            return 1;
        else return -1;
    }

    public String setLevel(String level){
        String message;
        if(!this.levelValidity(level)){
            message ="Failed to set level";
        } else {
            int lastDigit = Integer.parseInt(level.charAt(level.length()-1)+"");
            String newLevel = level.toLowerCase();
            switch (lastDigit){
                case 1:
                    assignLevel(newLevel, 1);
                    break;
                case 2:
                    assignLevel(newLevel, 2);
                    break;
                case 3:
                    assignLevel(newLevel, 3);
            }
            message = "Successfully set you level";
        }
        return  message;
    }

    private void assignLevel(String newLevel, int classNo){
        if(newLevel.indexOf("j") != -1){
            classNo++;
            this.level = classNo;
        }else{
            classNo++;
            this.level = classNo * classNo;
        }
    }

    private boolean levelValidity(String studentClass){
        boolean validLevel = Pattern.compile("^[J]?([S]*)[1-3]$",
                Pattern.CASE_INSENSITIVE).matcher(studentClass).find();
        return validLevel;
    }

    public void updateName(String newName){
        this.name = newName;
    }

    public String updateLevel(int newLevel) {
        String message;
        if (!role.equalsIgnoreCase("Teacher")){
            this.level = newLevel;
            message = "Successfully updated level";
        } else {
            message = "Can't update your level";
        }

        return message;
    }

    public int getLevel(){
        return level;
    }

    public String getRole(){
        return role;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", role='" + role + '\'' +
                ", level=" + level +
                '}';
    }
}
