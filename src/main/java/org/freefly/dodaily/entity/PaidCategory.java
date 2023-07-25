package org.freefly.dodaily.entity;

/**
 * Tool for Paid service
 * @usage PaidCategory.*
 *
 * specific paid category to use
 *
 * @author freefly365
 * @date 2023-07-25
 * */
public enum PaidCategory {
    Other,Breakfast,Lunch,Dinner,Snack,Water,Gas,HighRoad,Shopping,HouseRent,Park;

    @Override
    public String toString(){
        String res="";
        switch (this){
            case Other: res="Other";break;
            case Breakfast: res="Breakfast";break;
            case Lunch: res="Lunch";break;
            case Dinner: res="Dinner";break;
            case Snack: res="Snack";break;
            case Water: res="Water";break;
            case Gas: res="Gas";break;
            case HighRoad: res="HighRoad";break;
            case Shopping: res="Shopping";break;
            case HouseRent: res="HouseRent";break;
            case Park: res="Park";break;
            default:res="Other";
        }
        return res;
    }
}
