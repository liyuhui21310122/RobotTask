package com.example.model;

public interface IDirectEditModel {
	public void setEditText(Object sel,String text);
	public String getEditText(Object sel);
	public boolean canEdit();
}
