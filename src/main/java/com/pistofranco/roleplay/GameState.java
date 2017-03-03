package com.pistofranco.roleplay;


public enum  GameState {
    WAITING,STARTING,STARTED,RESTARTING,CREATING,CHOSING;

    private static GameState currentState;

    public static GameState getState(){
        return currentState;
    }
    public static void setState(GameState gameState){
        currentState = gameState;
    }
    public static boolean isState(GameState gameState){
        if (gameState == currentState){
            return true;
        }
        else return false;
    }
}
