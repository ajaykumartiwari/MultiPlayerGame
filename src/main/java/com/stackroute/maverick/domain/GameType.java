package com.stackroute.maverick.domain;

public class GameType {
	private int gameTypeId;
    private String gameTypeName;
    private String gameTypeDescription;
	
    public int getGameTypeId() {
		return gameTypeId;
	}
	public void setGameTypeId(int gameTypeId) {
		this.gameTypeId = gameTypeId;
	}
	public String getGameTypeName() {
		return gameTypeName;
	}
	public void setGameTypeName(String gameTypeName) {
		this.gameTypeName = gameTypeName;
	}
	public String getGameTypeDescription() {
		return gameTypeDescription;
	}
	public void setGameTypeDescription(String gameTypeDescription) {
		this.gameTypeDescription = gameTypeDescription;
	}
    

}
